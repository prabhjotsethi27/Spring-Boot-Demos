package com.hcl.demo.fundTransferApp.controller;

import com.hcl.demo.fundTransferApp.model.FundTransferRequest;
import com.hcl.demo.fundTransferApp.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/v1/demoApp/transfer")
public class TransactionController {

    @Autowired
    private TransactionService transactionService;

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> performFundTransfer(@Valid @RequestBody FundTransferRequest fundTransferRequest) {
        return transactionService.performFundTransfer(fundTransferRequest);
    }

    @GetMapping(value = "/history/{customerId}")
    public ResponseEntity<String> getTransactionHistory(@PathVariable(name = "customerId") Integer customerId){
        return transactionService.getTransactionHistory(customerId);
    }
}
