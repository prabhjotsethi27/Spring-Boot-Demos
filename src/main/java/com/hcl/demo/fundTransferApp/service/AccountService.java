package com.hcl.demo.fundTransferApp.service;

import com.hcl.demo.fundTransferApp.entity.Account;
import com.hcl.demo.fundTransferApp.model.GenericApiResponse;
import com.hcl.demo.fundTransferApp.repository.AccountRepository;
import com.hcl.demo.fundTransferApp.util.JacksonUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class AccountService {

    @Autowired
    private AccountRepository accountRepository;

    public ResponseEntity<String> getTestAccountList() {
        List<Account> accountList = new ArrayList<>();

        Account acc1 = Account.builder().customerId(1).accountHolderName("Mark Evans").accountNo(12001l).accountType("Savings").balance(1000d).build();
        Account acc2 = Account.builder().customerId(2).accountHolderName("Kiran Kaur").accountNo(12002l).accountType("Savings").balance(2000d).build();

        accountList.addAll(Arrays.asList(acc1, acc2));
        String jsonString = JacksonUtil.writeObjectAsString(accountList);

        return new ResponseEntity<String>(jsonString, HttpStatus.OK);

    }

    public ResponseEntity<String> createNewAccount(Account newAccountRequest) {
        if(newAccountRequest != null) {
            Account savedNewAccount = accountRepository.save(newAccountRequest);
            if (savedNewAccount != null && savedNewAccount.getAccountNo() != 0l)
                return new ResponseEntity<>(new GenericApiResponse(HttpStatus.CREATED.value(),"Great! New Account Created for Customer ID: " + newAccountRequest.getCustomerId()+ " and Account No: "+ newAccountRequest.getAccountNo(), savedNewAccount.toString()).toString(), HttpStatus.CREATED);
        }
        return new ResponseEntity<>(new GenericApiResponse(HttpStatus.BAD_REQUEST.value(), "Error while adding a New Account.", null).toString(), HttpStatus.BAD_REQUEST);
    }

    public ResponseEntity<String> getAllAccountList() {

        List<Account> allAccountList = accountRepository.findAll();
        allAccountList.sort(Comparator.comparing(Account::getAccountNo));
        String jsonString = JacksonUtil.writeObjectAsString(allAccountList);
        return new ResponseEntity<String>(jsonString, HttpStatus.OK);

    }

    public ResponseEntity<String> getAccountByAccountNo(Long accountNo) {

        Optional<Account> foundAccount = accountRepository.findByAccountNo(accountNo);
        if(foundAccount.isPresent()) {
            String jsonString = JacksonUtil.writeObjectAsString(foundAccount.get());
            return new ResponseEntity<String>(jsonString, HttpStatus.OK);
        }
        else
            return new ResponseEntity<String>(new GenericApiResponse(HttpStatus.NOT_FOUND.value(), "Sorry! No Account Details found with Account No: " + accountNo, null).toString(), HttpStatus.NOT_FOUND);
    }

    public ResponseEntity<String> getAccountByCustId(Integer custId) {

        List<Account> foundAccounts = accountRepository.findByCustomerId(custId).get();
        if(foundAccounts.size() > 0) {
            String jsonString = JacksonUtil.writeObjectAsString(foundAccounts);
            return new ResponseEntity<String>(jsonString, HttpStatus.OK);
        }
        else
            return new ResponseEntity<String>(new GenericApiResponse(HttpStatus.NOT_FOUND.value(), "Sorry! No Account Details found with Customer Id: " + custId, null).toString(), HttpStatus.NOT_FOUND);
    }


}
