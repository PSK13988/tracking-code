package com.trakdesk.Resources;

import com.trakdesk.Config.General;
import com.trakdesk.Config.ResponseCallback;
import com.trakdesk.Traits.ViewTrait;
import com.trakdesk.Traits.ListTrait;
import com.trakdesk.Traits.CreateTrait;
import com.trakdesk.Traits.UpdateTrait;

/**
 * Agents resource
 * This provides access to Trakdesk agents API
 *
 * @see <a href="https://developers.trakdesk.com/api/#agents"> Agents </a>
 */
public class Agents extends AbstractLayer implements ViewTrait, ListTrait, CreateTrait, UpdateTrait  {

    public Agents() {
        super();
        super.endpoint = "/agents";
    }

    /**
     * Get the current authenticated agent details
     *
     * @param callback {@link ResponseCallback}
     */
    public void current(ResponseCallback callback) {
        General.get(this.endpoint + "/current", null, callback);
    }

    /**
     * This soft deletes an agent
     *
     * @param id       {@link int}
     * @param callback {@link ResponseCallback}
     */
    public void softDelete(int id, ResponseCallback callback) {
        General.delete(this.endpoint(id), callback);
    }

    /**
     * This restores a deleted agent
     *
     * @param id       {@link int}
     * @param callback {@link ResponseCallback}
     */
    public void restore(int id, ResponseCallback callback) {
        General.update(this.endpoint(id) + "/restore", null, callback);
    }
}
