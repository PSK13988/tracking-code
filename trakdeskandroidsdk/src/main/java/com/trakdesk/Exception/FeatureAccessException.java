package com.trakdesk.Exception;

/**
 * Feature access exception
 * Thrown when a subscription does not have access to the requested feature
 */
public class FeatureAccessException extends ApiException{

    public FeatureAccessException(String message, String statusCode) {
        super(message, statusCode);
    }
}
