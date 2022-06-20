package com.hcl.demo.fundTransferApp.service;

import com.hcl.demo.fundTransferApp.entity.Account;
import com.hcl.demo.fundTransferApp.entity.Transaction;
import com.hcl.demo.fundTransferApp.model.FundTransferRequest;
import com.hcl.demo.fundTransferApp.model.FundTransferResponse;
import com.hcl.demo.fundTransferApp.model.GenericApiResponse;
import com.hcl.demo.fundTransferApp.model.TransactionRecordResponse;
import com.hcl.demo.fundTransferApp.repository.AccountRepository;
import com.hcl.demo.fundTransferApp.repository.TransactionRepository;
import com.hcl.demo.fundTransferApp.model.Constants;
import com.hcl.demo.fundTransferApp.util.DateTimeUtil;
import com.hcl.demo.fundTransferApp.util.JacksonUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class TransactionService {

    @Autowired
    private TransactionRepository transactionRepository;

    @Autowired
    private AccountRepository accountRepository;

    @Transactional
    public ResponseEntity<String> performFundTransfer(FundTransferRequest fundTransferRequest) {

        Optional<Account> sourceAccountOpt = accountRepository.findByAccountNo(fundTransferRequest.getSourceAccNo());
        Optional<Account> destAccountOpt = accountRepository.findByAccountNo(fundTransferRequest.getDestAccNo());
        double transferAmt = fundTransferRequest.getTransferAmount();
        if (!sourceAccountOpt.isPresent() || !destAccountOpt.isPresent())
            return new ResponseEntity<>(new GenericApiResponse(HttpStatus.BAD_REQUEST.value(), "Sorry, transfer not possible! Invalid Source or Destination account No...", null).toString(), HttpStatus.BAD_REQUEST);
        boolean checkSourceBalance = sourceAccountOpt.get().getBalance() - transferAmt >= 0;
        if (!checkSourceBalance)
            return new ResponseEntity<>(new GenericApiResponse(HttpStatus.BAD_REQUEST.value(), "Sorry, transfer not possible! Insufficient Account Balance...", null).toString(), HttpStatus.BAD_REQUEST);
        if (sourceAccountOpt.isPresent() && destAccountOpt.isPresent() && checkSourceBalance) { // transfer can take place

            Account sourceAcc = sourceAccountOpt.get();
            Account destAcc = destAccountOpt.get();
            double sourceCurrentBalance = sourceAcc.getBalance();
            double destCurrentBalance = sourceAcc.getBalance();

            // updating balances in two accounts
            sourceAcc.setBalance(sourceCurrentBalance - transferAmt);
            destAcc.setBalance(destCurrentBalance + transferAmt);

            accountRepository.save(sourceAcc);
            accountRepository.save(destAcc);

            UUID transactionId = UUID.randomUUID();
            LocalDateTime transDateTime = LocalDateTime.now();
            Transaction transactionObject = Transaction.builder()
                    .transactionId(transactionId.toString())
                    .sourceAccNo(sourceAccountOpt.get().getAccountNo())
                    .destAccNo(destAccountOpt.get().getAccountNo())
                    .transactionDateTime(transDateTime)
                    .transferAmount(transferAmt)
                    .transactionStatus(Constants.SUCCESS_TRANSACTION_STATUS)
                    .build();

            transactionRepository.save(transactionObject);

            FundTransferResponse fundTransferResponse = FundTransferResponse.builder()
                    .transactionId(transactionId.toString())
                    .transactionDateTime(DateTimeUtil.convertDateTimeToISOString(transDateTime))
                    .transactionStatus(Constants.SUCCESS_TRANSACTION_STATUS)
                    .transferAmt(transferAmt)
                    //.transactionRefNo(transactionObject.getTransactionRefNo())
                    .build();
            return new ResponseEntity<>(new GenericApiResponse(HttpStatus.CREATED.value(), "Transaction Details Fetched successfully", fundTransferResponse.toString()).toString(), HttpStatus.CREATED);
        }
        return new ResponseEntity<>(new GenericApiResponse(HttpStatus.BAD_REQUEST.value(), "Sorry, transfer not possible! Some error occurred...", null).toString(), HttpStatus.BAD_REQUEST);
    }


    public ResponseEntity<String> getTransactionHistory(Integer custId) {

        List<Account> custAccountList = accountRepository.findByCustomerId(custId).get();
        List<Transaction> transactionData = new ArrayList<>();
        List<Long> accountsNosListByCustId;
        List<TransactionRecordResponse> transactionHistoryRecords = new ArrayList<>();
        if (custAccountList.size() > 0) {
            accountsNosListByCustId = custAccountList.stream().map(Account::getAccountNo).collect(Collectors.toList());
            for (Long accNo : accountsNosListByCustId) {
                List<Transaction> allTransactionsList = transactionRepository.findBySourceAccNoOrDestAccNo(accNo, accNo).get();
                if (allTransactionsList.size() > 0) {
                    transactionData.addAll(allTransactionsList);
                }
            }
            if (transactionData.size() > 0) {
                for (Transaction trans : transactionData) {
                    String transactionType = (accountsNosListByCustId.contains(trans.getSourceAccNo())) ? Constants.WITHDRAWAL_TRANSACTION_TYPE : Constants.DEPOSIT_TRANSACTION_TYPE;
                    TransactionRecordResponse newTransactionHistory = TransactionRecordResponse.builder()
                            .customerId(custId)
                            .accountNo(transactionType == Constants.WITHDRAWAL_TRANSACTION_TYPE ? trans.getSourceAccNo() : trans.getDestAccNo())
                            .transactionAmount(trans.getTransferAmount())
                            .transactionId(trans.getTransactionId())
                            //.transactionRefNo(trans.getTransactionRefNo())
                            .transactionType(transactionType)
                            .build();
                    transactionHistoryRecords.add(newTransactionHistory);
                }

            }
            String jsonString = JacksonUtil.writeObjectAsString(transactionHistoryRecords);
            return new ResponseEntity<String>(jsonString, HttpStatus.OK);
        } else
            return new ResponseEntity<String>(new GenericApiResponse(HttpStatus.NOT_FOUND.value(), "Sorry! No Transaction History found with Customer ID: " + custId, null).toString(), HttpStatus.NOT_FOUND);
    }

}
