package com.hcl.demo.fundTransferApp.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class FundTransferResponse {

    private String transactionId;
    private String transactionStatus;
    private String transactionDateTime;
    private double transferAmt;
    //private long transactionRefNo;
}
