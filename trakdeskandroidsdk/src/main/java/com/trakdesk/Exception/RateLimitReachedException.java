package com.trakdesk.Exception;

/**
 * Rate limit reached exception
 * Thrown when you've exhausted your API requests for the current window
 */
public class RateLimitReachedException extends ApiException {

    public RateLimitReachedException(String message, String statusCode) {
        super(message, statusCode);
    }
}
