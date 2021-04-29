package com.trakdesk.Exception;

/**
 * Not Found Exception
 * Thrown when the Trakdesk API returns a 404 status code.
 * This is returned when the request contains an invalid resource ID or endpoint path.
 * For example, querying an invalid ticket ID will return this error
 */
public class NotFoundException extends ApiException {

    public NotFoundException(String message, String statusCode) {
        super(message, statusCode);
    }
}
