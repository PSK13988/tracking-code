package com.trakdesk.Exception;

/**
 * SSL required exception
 * Thrown when a request contains http:// instead of https:// protocol
 */
public class SslRequiredException extends ApiException {

    public SslRequiredException(String message, String statusCode) {
        super(message, statusCode);
    }
}
