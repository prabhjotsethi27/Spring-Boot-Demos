package com.hcl.demo.fundTransferApp.model;

public class Constants {

    Constants(){}

    public final static String SUCCESS_TRANSACTION_STATUS = "SUCCESS";
    public final static String FAILED_TRANSACTION_STATUS = "FAILED";
    public final static String WITHDRAWAL_TRANSACTION_TYPE = "WITHDRAWAL";
    public final static String DEPOSIT_TRANSACTION_TYPE = "DEPOSIT";
    public static final long ACCESS_TOKEN_VALIDITY_SECONDS = 5*60*60;
    public static final String SIGNING_KEY = "hcl123";
    public static final String BEARER_TOKEN_PREFIX = "Bearer ";
    public static final String HEADER_AUTHORISATION_STRING = "Authorization";
    public static final String ADMIN_ROLE_CLAIMS = "ROLE_ADMIN";
}
