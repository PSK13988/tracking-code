package com.trakdesk.Exception;

/**
 * SSL failure exception
 * Thrown when there are failed SSL-related operations
 */
public class SslFailureException extends ApiException{

    public String message;

    public SslFailureException(String message, String statusCode) {
        super(message, statusCode);
        this.message = "No internet connection";
    }
}
