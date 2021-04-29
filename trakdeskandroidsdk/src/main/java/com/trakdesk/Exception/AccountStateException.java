package com.trakdesk.Exception;

/**
 * Account state exception
 * Thrown when an account is in an inactive state
 */
public class AccountStateException extends ApiException {

    public AccountStateException(String message, String statusCode) {
        super(message, statusCode);
    }
}
