package com.hcl.demo.fundTransferApp.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FundTransferRequest {

    @NotNull(message = "Source Account Number cannot be blank")
    @Positive(message = "Source Account Number should be a positive value")
    private long sourceAccNo;

    @NotNull(message = "Destination Account Number cannot be blank")
    @Positive(message = "Destination Account Number should be a positive value")
    private long destAccNo;

    @NotNull(message = "Transfer Amount cannot be blank")
    @Positive(message = "Transfer Amount should be a positive value")
    private Double transferAmount;
}
