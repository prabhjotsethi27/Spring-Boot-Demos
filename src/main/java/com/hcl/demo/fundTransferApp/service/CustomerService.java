package com.hcl.demo.fundTransferApp.service;

import com.hcl.demo.fundTransferApp.entity.Customer;
import com.hcl.demo.fundTransferApp.model.GenericApiResponse;
import com.hcl.demo.fundTransferApp.repository.CustomerRepository;
import com.hcl.demo.fundTransferApp.util.JacksonUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class CustomerService {

    @Autowired
    private CustomerRepository customerRepository;

    public ResponseEntity<String> getTestCustomerList() {
        List<Customer> customerList = new ArrayList<>();

        Customer cust1 = Customer.builder().customerId(1).firstName("Mark").lastName("Evans").age(30).city("New York").gender("M").mobileNo("+1-78929922").build();
        Customer cust2 = Customer.builder().customerId(2).firstName("Kiran").lastName("Kaur").age(25).city("New Jersey").gender("F").mobileNo("+1-69869922").build();

        customerList.addAll(Arrays.asList(cust1, cust2));
        String jsonString = JacksonUtil.writeObjectAsString(customerList);

        return new ResponseEntity<String>(jsonString, HttpStatus.OK);

    }

    public ResponseEntity<String> createNewCustomer(Customer customerRequest) {
        if(customerRequest != null) {
            Customer savedNewCustomer = customerRepository.save(customerRequest);
            if (savedNewCustomer != null && savedNewCustomer.getCustomerId() != 0)
                return new ResponseEntity<>(new GenericApiResponse(HttpStatus.CREATED.value(), "Great! New Customer Created with Customer ID: " + savedNewCustomer.getCustomerId(), savedNewCustomer.toString()).toString(), HttpStatus.CREATED);
        }
        return new ResponseEntity<>(new GenericApiResponse(HttpStatus.BAD_REQUEST.value(), "Error while adding a New Customer.", null).toString(), HttpStatus.BAD_REQUEST);
    }

    public ResponseEntity<String> getAllCustomerList() {

        List<Customer> allCustomerList = customerRepository.findAll();
        allCustomerList.sort(Comparator.comparing(Customer::getCustomerId));
        String jsonString = JacksonUtil.writeObjectAsString(allCustomerList);
        return new ResponseEntity<String>(jsonString, HttpStatus.OK);

    }

    public ResponseEntity<String> getCustomerByCustId(Integer custId) {

        Optional<Customer> customer = customerRepository.findByCustomerId(custId);
        if(customer.isPresent()) {
            String jsonString = JacksonUtil.writeObjectAsString(customer.get());
            return new ResponseEntity<String>(jsonString, HttpStatus.OK);
        }
        else
            return new ResponseEntity<String>(new GenericApiResponse(HttpStatus.NOT_FOUND.value(), "Sorry! No customer found with Customer ID: " + custId, null).toString(), HttpStatus.NOT_FOUND);
    }

}
