package com.trakdesk.Exception;

/**
 * Duplicate value exception
 * Thrown when the request contains duplicate values
 */
public class DuplicateValueException extends ApiException {

    public DuplicateValueException(String message, String statusCode) {
        super(message, statusCode);
    }
}
