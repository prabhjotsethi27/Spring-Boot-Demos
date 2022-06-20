package com.hcl.demo.fundTransferApp.controller;

import com.hcl.demo.fundTransferApp.entity.Account;
import com.hcl.demo.fundTransferApp.entity.Customer;
import com.hcl.demo.fundTransferApp.service.AccountService;
import com.hcl.demo.fundTransferApp.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/demoApp/customers")
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    @GetMapping(value= "/test")
    public ResponseEntity<String> getTestCustomerList() {
        return customerService.getTestCustomerList();
    }

    @GetMapping()
    public ResponseEntity<String> getAllCustomers() {
        return customerService.getAllCustomerList();
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> postCustomers(@Valid @RequestBody Customer customerRequest){
        return customerService.createNewCustomer(customerRequest);
    }

    @GetMapping(value = "/{custId}")
    public ResponseEntity<String> getCustomerByCustId(@PathVariable(name = "custId") Integer customerId){
        return customerService.getCustomerByCustId(customerId);
    }

}
