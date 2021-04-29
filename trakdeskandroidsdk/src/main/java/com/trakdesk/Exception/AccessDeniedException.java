package com.trakdesk.Exception;

/**
 * Access denied exception
 * Thrown when an agent has Insufficient privileges to perform this action
 * For example: An agent trying to access a module that he/she does not have access to will result in this error
 */
public class AccessDeniedException extends ApiException {

    public AccessDeniedException(String message, String statusCode) {
        super(message, statusCode);
    }
}
