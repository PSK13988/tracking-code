package com.trakdesk.Exception;

/**
 * Invalid URL exception
 * Thrown when the request contains an invalid endpoint URL
 */
public class InvalidUrlException extends ApiException {

    public InvalidUrlException(String message, String statusCode) {
        super(message, statusCode);
    }

}
