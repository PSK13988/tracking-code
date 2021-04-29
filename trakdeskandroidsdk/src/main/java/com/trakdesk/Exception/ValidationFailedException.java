package com.trakdesk.Exception;

/**
 * Validation failed exception
 * Thrown when the Trakdesk API returns a 400 status code
 */
public class ValidationFailedException extends ApiException {

    public ValidationFailedException(String message, String statusCode) {
        super(message, statusCode);
    }
}
