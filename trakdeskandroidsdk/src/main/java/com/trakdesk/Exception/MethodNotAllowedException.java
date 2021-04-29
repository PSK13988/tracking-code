package com.trakdesk.Exception;

/**
 * Method not allowed exception
 * Thrown when the request contains an invalid method/verb
 */
public class MethodNotAllowedException extends ApiException {

    public MethodNotAllowedException(String message, String statusCode) {
        super(message, statusCode);
    }
}
