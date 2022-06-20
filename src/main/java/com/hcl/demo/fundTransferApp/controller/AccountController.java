package com.hcl.demo.fundTransferApp.controller;

import com.hcl.demo.fundTransferApp.entity.Account;
import com.hcl.demo.fundTransferApp.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/v1/demoApp/accounts")
public class AccountController {

    @Autowired
    private AccountService accountService;

    @GetMapping(value= "/test")
    public ResponseEntity<String> getTestAccountList() {
        return accountService.getTestAccountList();
    }

    @GetMapping
    public ResponseEntity<String> getAllAccounts() {
        return accountService.getAllAccountList();
    }

    @GetMapping(value = "/{accountNo}")
    public ResponseEntity<String> getAccountByAccountNo(@PathVariable(name = "accountNo") Long accountNo){
        return accountService.getAccountByAccountNo(accountNo);
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> postAccounts(@Valid @RequestBody Account accountRequest){
        return accountService.createNewAccount(accountRequest);
    }

    @GetMapping(value = "/findByCustId")
    public ResponseEntity<String> getAccountByCustomerId(@RequestParam (name = "custId") Integer custId){
        return accountService.getAccountByCustId(custId);
    }
}
