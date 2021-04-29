package com.trakdesk.Resources;

import com.trakdesk.Config.General;
import com.trakdesk.Config.ResponseCallback;
import com.trakdesk.Traits.ViewTrait;
import com.trakdesk.Traits.ListTrait;
import com.trakdesk.Traits.UpdateTrait;
import com.trakdesk.Traits.DeleteTrait;

import org.json.JSONObject;

/**
 * Conversations resource
 * This provides access to Trakdesk conversations API
 *
 * @see <a href="https://developers.trakdesk.com/api/#conversations"> Conversations </a>
 */

public class Conversations extends AbstractLayer implements ViewTrait, ListTrait, UpdateTrait, DeleteTrait {

    private String tickets_endpoint;

    public Conversations() {
        super();
        super.endpoint = "/conversations";
        this.tickets_endpoint = "/tickets";
    }

    /**
     * @param id {@link int}
     * @return endpoint {@link String}
     */
    private String ticketsEndpoint(int id) {
        return this.tickets_endpoint + "/" + id;
    }

    /**
     * This creates ticket replies
     *
     * @param ticket_id {@link int}
     * @param params    {@link JSONObject}
     * @param callback  {@link ResponseCallback}
     */
    public void createReply(int ticket_id, JSONObject params, ResponseCallback callback) {
        General.create(this.ticketsEndpoint(ticket_id) + "/reply", params, callback);
    }

    /**
     * This creates ticket replies
     *
     * @param ticket_id {@link int}
     * @param params    {@link JSONObject}
     * @param callback  {@link ResponseCallback}
     */
    public void createNote(int ticket_id, JSONObject params, ResponseCallback callback) {
        General.create(this.ticketsEndpoint(ticket_id) + "/note", params, callback);
    }
}
