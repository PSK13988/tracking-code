package com.trakdesk.Traits;

import com.trakdesk.Config.General;
import com.trakdesk.Config.ResponseCallback;
import java.util.HashMap;

/**
 * View Trait
 * This gets a single object
 */
public interface ViewTrait {

    /**
     * view Method
     *
     * @param id       {@link int}
     * @param options  {@link HashMap}
     * @param callback {@link ResponseCallback}
     */
    default void view(int id, HashMap<String, String> options, ResponseCallback callback) {
        General.get(endpoint(id), options, callback);
    }

    /**
     * Get endpoint
     *
     * @return endpoint
     */
    String endpoint(Integer id);
}
