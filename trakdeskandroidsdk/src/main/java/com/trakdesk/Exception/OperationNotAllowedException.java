package com.trakdesk.Exception;

/**
 * Operation not allowed exception
 * Thrown when you are attempting to alter a record/attribute that cannot be modified
 */
public class OperationNotAllowedException extends ApiException {

    public OperationNotAllowedException(String message, String statusCode) {
        super(message, statusCode);
    }
}
