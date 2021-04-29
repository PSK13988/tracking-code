package com.trakdesk.Exception;

/**
 * Invalid parameter exception
 * Thrown when a request contains invalid URL parameters
 */
public class InvalidParameterException extends ApiException {

    public InvalidParameterException(String message, String statusCode) {
        super(message, statusCode);
    }
}
