package com.trakdesk.Exception;

/**
 * IO failure exception
 * Thrown when there is a failed or interrupted I/O operation
 */
public class IoFailureException extends ApiException{

    public String message;

    public IoFailureException(String message, String statusCode) {
        super(message, statusCode);
        this.message = "whoops! something went wrong";
    }
}
