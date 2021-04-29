package com.trakdesk.Resources;

import com.trakdesk.Config.General;
import com.trakdesk.Config.ResponseCallback;
import com.trakdesk.Traits.ViewTrait;
import com.trakdesk.Traits.ListTrait;
import com.trakdesk.Traits.UpdateTrait;
import com.trakdesk.Traits.DeleteTrait;
import org.json.JSONObject;

/**
 * Time Entries resource
 * This provides access to Trakdesk time entries API
 *
 * @see <a href="https://developers.trakdesk.com/api/#time-entries"> Time Entries </a>
 */

public class TimeEntries extends AbstractLayer implements ViewTrait, ListTrait, UpdateTrait, DeleteTrait {

    public String tickets_endpoint;

    public TimeEntries() {
        super();
        super.endpoint = "/time_entries";
        this.tickets_endpoint = "/tickets";
    }

    /**
     * @param id {@link int}
     * @return String endpoint
     */
    private String ticketTimeEntriesEndpoint(int id) {
        return this.tickets_endpoint + "/" + id + this.endpoint;
    }

    /**
     * @param ticket_id {@link int}
     * @param params    {@link JSONObject}
     * @param callback  {@link ResponseCallback}
     */
    public void create(int ticket_id, JSONObject params, ResponseCallback callback) {
        General.create(this.ticketTimeEntriesEndpoint(ticket_id), params, callback);
    }
}
