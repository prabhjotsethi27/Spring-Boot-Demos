package com.hcl.demo.fundTransferApp.model;

import lombok.Data;

@Data
public class UserDto {

    private int id;
    private String firstName;
    private String lastName;
    private String username;
    private String password;
    private String emailId;

}
