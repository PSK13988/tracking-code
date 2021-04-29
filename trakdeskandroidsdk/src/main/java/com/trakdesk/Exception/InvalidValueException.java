package com.trakdesk.Exception;

/**
 * Invalid value exception
 * Thrown when the request contains an invalid field value
 */
public class InvalidValueException extends ApiException {

    public InvalidValueException(String message, String statusCode) {
        super(message, statusCode);
    }
}
