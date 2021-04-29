package com.trakdesk.Traits;

import com.trakdesk.Config.General;
import com.trakdesk.Config.ResponseCallback;
import java.util.HashMap;

/**
 * List Trait
 * This gets a list of existing objects
 */
public interface ListTrait {

    /**
     * This list agents
     *
     * @param options  {@link HashMap}
     * @param callback {@link ResponseCallback}
     */
    default void list(HashMap<String, String> options, ResponseCallback callback) {
        General.get(this.endpoint(null), options, callback);
    }

    /**
     * Get endpoint
     *
     * @return endpoint
     */
    String endpoint(Integer id);
}
