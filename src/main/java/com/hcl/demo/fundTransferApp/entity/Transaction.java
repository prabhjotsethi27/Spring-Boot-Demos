package com.hcl.demo.fundTransferApp.entity;

import com.hcl.demo.fundTransferApp.util.JacksonUtil;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity(name = "transactions")
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Transaction implements Serializable {

    @Id
    private String transactionId;
    private long sourceAccNo;
    private long destAccNo;
    private Double transferAmount;
    private String transactionStatus;
    private LocalDateTime transactionDateTime;

    @Override
    public String toString() {
        return JacksonUtil.writeObjectAsString(this);
    }
}
