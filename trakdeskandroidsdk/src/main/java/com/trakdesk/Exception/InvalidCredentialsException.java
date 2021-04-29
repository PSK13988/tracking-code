package com.trakdesk.Exception;

/**
 * Invalid credentials exception
 * Thrown when the request contains an invalid API key
 */
public class InvalidCredentialsException extends ApiException {

    public InvalidCredentialsException(String message, String statusCode) {
        super(message, statusCode);
    }
}
