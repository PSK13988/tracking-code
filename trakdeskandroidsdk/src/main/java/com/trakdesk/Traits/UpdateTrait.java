package com.trakdesk.Traits;

import com.trakdesk.Config.General;
import com.trakdesk.Config.ResponseCallback;
import org.json.JSONObject;

/**
 * Update Trait
 * This updates existing resources
 */
public interface UpdateTrait {

    /**
     * update method
     *
     * @param id       {@link int}
     * @param params   {@link JSONObject}
     * @param callback {@link ResponseCallback}
     */
    default void update(int id, JSONObject params, ResponseCallback callback) {
        General.update(endpoint(id), params, callback);
    }

    /**
     * Get endpoint
     *
     * @return endpoint
     */
    String endpoint(Integer id);
}
