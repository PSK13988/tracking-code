package com.trakdesk.Exception;

/**
 * Not modified exception
 */
public class NotModifiedException extends ApiException {

    public NotModifiedException(String message, String statusCode) {
        super(message, statusCode);
    }
}
