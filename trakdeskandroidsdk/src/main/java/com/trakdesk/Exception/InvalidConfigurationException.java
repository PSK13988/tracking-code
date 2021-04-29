package com.trakdesk.Exception;

/**
 * Invalid configuration exception
 * Thrown when there the API key/sub-domain is missing
 */
public class InvalidConfigurationException extends ApiException {

    public InvalidConfigurationException(String message) {
        super(message, null);
    }
}
