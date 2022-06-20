package com.hcl.demo.fundTransferApp.entity;

import com.hcl.demo.fundTransferApp.util.JacksonUtil;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.*;

@Entity(name = "Customers")
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer customerId;

    @NotBlank(message = "firstName is Mandatory")
    @Size(min = 3, max = 50, message = "Size must be between 3 and 50 characters.")
    private String firstName;

    private String lastName;

    @NotNull(message = "Age cannot be blank")
    @Min(18)
    @Positive
    private Integer age;

    @Size(min = 10, max = 11, message = "Mobile Number must be between 10 and 11 characters")
    private String mobileNo;

    @NotEmpty(message = "Gender is Mandatory")
    private String gender;

    private String city;

    @Transient
    private String custName;

//    @OneToOne
//    @JoinColumn(name = "account_no")
//    private Account accountDetails;

    public String getCustName() {
        StringBuilder fullName = new StringBuilder();
        fullName.append(this.firstName+" "+ this.lastName);
        return fullName.toString();
    }

    @Override
    public String toString() {
        return JacksonUtil.writeObjectAsString(this);
    }
}
