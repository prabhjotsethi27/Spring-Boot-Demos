package com.hcl.demo.fundTransferApp.model;

import com.hcl.demo.fundTransferApp.util.JacksonUtil;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class GenericApiResponse {

    private Integer statusCode;
    private String message;
    private Object result;

    @Override
    public String toString() {
        return JacksonUtil.writeObjectAsString(this);
    }
}
