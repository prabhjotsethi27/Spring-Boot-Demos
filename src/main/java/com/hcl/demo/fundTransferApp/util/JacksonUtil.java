package com.hcl.demo.fundTransferApp.util;

import com.fasterxml.jackson.databind.ObjectMapper;

public class JacksonUtil {

    JacksonUtil(){}

    private static ObjectMapper objectMapper = new ObjectMapper();

    public static String writeObjectAsString(Object o) {
        try {
            return objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(o);
        } catch (Exception e) {
            return "Error occurred while JSON conversion to String." + e.getMessage();
        }
    }

}
