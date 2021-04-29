package com.trakdesk.Exception;

/**
 * Invalid JSON exception
 * Thrown when the request contains invalid JSON encoded content
 */
public class InvalidJsonException extends ApiException {

    public InvalidJsonException(String message, String statusCode) {
        super(message, statusCode);
    }
}
