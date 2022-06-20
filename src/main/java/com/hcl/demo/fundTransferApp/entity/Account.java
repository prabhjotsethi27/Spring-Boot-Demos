package com.hcl.demo.fundTransferApp.entity;

import com.hcl.demo.fundTransferApp.util.JacksonUtil;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.math.BigDecimal;

@Entity(name = "accounts")
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Account {

    @Id
    @GeneratedValue(generator = "sequence-generator")
    @GenericGenerator(
            name = "sequence-generator",
            strategy = "org.hibernate.id.enhanced.SequenceStyleGenerator",
            parameters = {
                    @Parameter(name = "sequence_name", value = "account_id_sequence"),
                    @Parameter(name = "initial_value", value = "1000001"),
                    @Parameter(name = "increment_size", value = "1")
            }
    )
    private long accountNo;

    @NotEmpty(message = "accountType is mandatory")
    private String accountType;

    @PositiveOrZero(message = "Account balance should be >= 0")
    private Double balance;

    @NotEmpty(message = "Account Holder's Name is Mandatory")
    private String accountHolderName;

    @NotNull(message = "Account Holder's customerId is Mandatory")
    @Positive
    private Integer customerId;

//    private Customer customer;

    @Override
    public String toString() {
        return JacksonUtil.writeObjectAsString(this);
    }
}
