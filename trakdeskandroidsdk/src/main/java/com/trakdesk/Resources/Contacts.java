package com.trakdesk.Resources;

import com.trakdesk.Config.General;
import com.trakdesk.Config.ResponseCallback;
import com.trakdesk.Traits.ViewTrait;
import com.trakdesk.Traits.ListTrait;
import com.trakdesk.Traits.CreateTrait;
import com.trakdesk.Traits.UpdateTrait;
import com.trakdesk.Traits.DeleteTrait;

import org.json.JSONObject;

/**
 * Contacts resource
 * This provides access to Trakdesk contacts API
 *
 * @see <a href="https://developers.trakdesk.com/api/#contacts"> Contacts </a>
 */

public class Contacts extends AbstractLayer implements ViewTrait, ListTrait, CreateTrait, UpdateTrait, DeleteTrait {

    public Contacts() {
        super();
        super.endpoint = "/contacts";
    }

    /**
     * @param id       {@link int}
     * @param params   {@link JSONObject}
     * @param callback {@link ResponseCallback}
     */
    public void convert(int id, JSONObject params, ResponseCallback callback) {
        General.update(this.endpoint(id) + "/convert", params, callback);
    }

    /**
     * @param id       {@link int}
     * @param params   {@link JSONObject}
     * @param callback {@link ResponseCallback}
     */
    public void sendVerification(int id, JSONObject params, ResponseCallback callback) {
        General.update(this.endpoint(id) + "/send_verification", params, callback);
    }
}
