package com.trakdesk.Traits;

import com.trakdesk.Config.General;
import com.trakdesk.Config.ResponseCallback;

/**
 * Delete Trait
 * This deletes a specified resource
 */
public interface DeleteTrait {

    /**
     * delete Method
     *
     * @param id       {@link int}
     * @param callback {@link ResponseCallback}
     */
    default void delete(int id, ResponseCallback callback) {
        General.delete(this.endpoint(id), callback);
    }

    /**
     * Get endpoint
     *
     * @return endpoint
     */
    String endpoint(Integer id);
}
