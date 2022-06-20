package com.hcl.demo.fundTransferApp.controller;

import com.hcl.demo.fundTransferApp.entity.User;
import com.hcl.demo.fundTransferApp.model.GenericApiResponse;
import com.hcl.demo.fundTransferApp.model.UserDto;
import com.hcl.demo.fundTransferApp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/v1/demoApp")
public class HomeController {
    @Autowired
    private UserService userService;

    @GetMapping(value = "/home")
    public ResponseEntity<String> defaultMappingCall() {
        return new ResponseEntity<String>("Welcome to Fund Transfer App...", HttpStatus.OK);
    }

    @PostMapping(value = "/signup")
    public ResponseEntity<String> signup(@RequestBody UserDto user) {

        User savedUser = userService.save(user);
        if(savedUser != null && savedUser.getId() != 0)
            return new ResponseEntity<>(new GenericApiResponse(HttpStatus.CREATED.value(), "User Registration is successful.", savedUser).toString(),HttpStatus.CREATED);
        return new ResponseEntity<>(new GenericApiResponse(HttpStatus.BAD_REQUEST.value(), "Sorry! User Registration failed!", null).toString(),HttpStatus.BAD_REQUEST);
    }

}
