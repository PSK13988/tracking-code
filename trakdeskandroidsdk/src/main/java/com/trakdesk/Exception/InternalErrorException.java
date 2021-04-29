package com.trakdesk.Exception;

/**
 * Internal error exception
 * Thrown when there is an error on Trakdesk's side
 */
public class InternalErrorException extends ApiException {

    public InternalErrorException(String message, String statusCode) {
        super(message, statusCode);
    }
}
