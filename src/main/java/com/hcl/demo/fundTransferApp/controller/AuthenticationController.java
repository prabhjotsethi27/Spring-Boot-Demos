package com.hcl.demo.fundTransferApp.controller;

import com.hcl.demo.fundTransferApp.config.security.JwtTokenUtil;
import com.hcl.demo.fundTransferApp.entity.User;
import com.hcl.demo.fundTransferApp.model.AuthToken;
import com.hcl.demo.fundTransferApp.model.GenericApiResponse;
import com.hcl.demo.fundTransferApp.model.LoginUser;
import com.hcl.demo.fundTransferApp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("api/v1/demoApp/token")
public class AuthenticationController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Autowired
    private UserService userService;

    @PostMapping(value = "/generateToken")
    public ResponseEntity<String> register(@RequestBody LoginUser loginUser) throws AuthenticationException {

        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginUser.getUsername(), loginUser.getPassword()));
        final User user = userService.findOne(loginUser.getUsername());
        final String token = jwtTokenUtil.generateToken(user);
        return new ResponseEntity<>(new GenericApiResponse(HttpStatus.OK.value(), "Token Generation is Successful!", new AuthToken(token, user.getUsername())).toString(), HttpStatus.OK);
    }

}
