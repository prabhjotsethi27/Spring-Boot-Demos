package com.hcl.demo.fundTransferApp.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.hcl.demo.fundTransferApp.util.JacksonUtil;
import lombok.AllArgsConstructor;
import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "users")
@Data
public class User {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private int id;
    private String firstName;
    private String lastName;
    private String username;
    @JsonIgnore
    private String password;
    @Column(columnDefinition = "varchar(255) default 'test@test.com'")
    private String emailId;

    @Override
    public String toString() {
        return JacksonUtil.writeObjectAsString(this);
    }
}
