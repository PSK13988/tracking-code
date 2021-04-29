package com.trakdesk.Exception;

/**
 * No content required exception
 * Thrown when content is sent for PUT requests that does not require any JSON content
 */
public class NoContentRequiredException extends ApiException {

    public NoContentRequiredException(String message, String statusCode) {
        super(message, statusCode);
    }
}
