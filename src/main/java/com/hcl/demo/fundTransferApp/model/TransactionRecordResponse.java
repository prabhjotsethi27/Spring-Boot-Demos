package com.hcl.demo.fundTransferApp.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TransactionRecordResponse {

    private Integer customerId;
    private String transactionId;
    private long accountNo;
    //private long transactionRefNo;
    private String transactionType;
    private double transactionAmount;

}
