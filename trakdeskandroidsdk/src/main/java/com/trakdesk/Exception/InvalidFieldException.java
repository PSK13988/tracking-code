package com.trakdesk.Exception;

/**
 * Invalid field exception
 * Thrown when the request contains an invalid field
 */
public class InvalidFieldException extends ApiException {

    public InvalidFieldException(String message, String statusCode) {
        super(message, statusCode);
    }
}
