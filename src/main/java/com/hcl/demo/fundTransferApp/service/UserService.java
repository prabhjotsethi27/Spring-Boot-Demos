package com.hcl.demo.fundTransferApp.service;

import com.hcl.demo.fundTransferApp.entity.User;
import com.hcl.demo.fundTransferApp.model.UserDto;

import java.util.List;

public interface UserService {

    User save(UserDto user);
    List<User> findAll();
    void delete(int id);

    User findOne(String username);

    User findById(int id);

    UserDto update(UserDto userDto);
}
