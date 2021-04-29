package com.trakdesk.Traits;

import com.trakdesk.Config.General;
import com.trakdesk.Config.ResponseCallback;
import org.json.JSONObject;

/**
 * Create Trait
 * This creates new objects
 */
public interface CreateTrait {

    /**
     * create Method
     *
     * @param params   {@link JSONObject}
     * @param callback ({@link ResponseCallback}
     */
    default void create(JSONObject params, ResponseCallback callback) {
        General.create(endpoint(null), params, callback);
    }

    /**
     * Get endpoint
     *
     * @return endpoint
     */
    String endpoint(Integer id);
}
