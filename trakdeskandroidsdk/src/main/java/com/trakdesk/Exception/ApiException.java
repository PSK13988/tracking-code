package com.trakdesk.Exception;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * General Exception
 * This throws exception when the Trakdesk API returns an HTTP error code
 */
public class ApiException extends Exception {

    public String message;
    public String statusCode;

    public ApiException(String message, String statusCode) {
        super(message);
        this.message = message;
        this.statusCode = statusCode;
    }

    public JSONObject setError() {
        ApiException exception = getException();
        JSONObject object = new JSONObject();
        try {
            object.put("success", false);
            JSONArray array = new JSONArray();
            JSONObject jsonObject = new JSONObject();
            if (exception.message != null) {
                if (exception.statusCode != null && !exception.statusCode.isEmpty()) {
                    jsonObject.put("code", exception.statusCode);
                }
                jsonObject.put("message", exception.message);
                array.put(jsonObject);
                object.put("errors", array);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return object;
    }

    private ApiException getException() {
        switch (this.statusCode) {
            case "method_not_allowed":
                return new MethodNotAllowedException(this.message, this.statusCode);
            case "access_denied":
                return new AccessDeniedException(this.message, this.statusCode);
            case "account_state":
                return new AccountStateException(this.message, this.statusCode);
            case "duplicate_value":
                return new DuplicateValueException(this.message, this.statusCode);
            case "feature_access":
                return new FeatureAccessException(this.message, this.statusCode);
            case "internal_error":
                return new InternalErrorException(this.message, this.statusCode);
            case "invalid_credentials":
                return new InvalidCredentialsException(this.message, this.statusCode);
            case "invalid_field":
                return new InvalidFieldException(this.message, this.statusCode);
            case "invalid_json":
                return new InvalidJsonException(this.message, this.statusCode);
            case "invalid_parameter":
                return new InvalidParameterException(this.message, this.statusCode);
            case "invalid_url":
                return new InvalidUrlException(this.message, this.statusCode);
            case "invalid_value":
                return new InvalidValueException(this.message, this.statusCode);
            case "max_record_limit":
                return new MaxRecordLimitException(this.message, this.statusCode);
            case "no_content_required":
                return new NoContentRequiredException(this.message, this.statusCode);
            case "not_found":
                return new NotFoundException(this.message, this.statusCode);
            case "not_modified":
                return new NotModifiedException(this.message, this.statusCode);
            case "operation_not_allowed":
                return new OperationNotAllowedException(this.message, this.statusCode);
            case "rate_limit_reached":
                return new RateLimitReachedException(this.message, this.statusCode);
            case "ssl_required":
                return new SslRequiredException(this.message, this.statusCode);
            case "io_failure":
                //return new IoFailureException(this.message, this.statusCode);
            case "ssl_failure":
                //return new SslFailureException(this.message, this.statusCode);
            default:
                return new ValidationFailedException(this.message, this.statusCode);
        }
    }
}