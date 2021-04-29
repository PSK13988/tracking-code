package com.trakdesk.Exception;

/**
 * Max record limit exception
 * Thrown when you have reached the maximum allowed number of records
 * For example, Attempting to add more than 30 groups will result in this error
 */
public class MaxRecordLimitException extends ApiException {

    public MaxRecordLimitException(String message, String statusCode) {
        super(message, statusCode);
    }
}
