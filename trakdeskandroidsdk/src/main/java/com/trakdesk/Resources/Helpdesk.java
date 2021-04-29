package com.trakdesk.Resources;

import com.trakdesk.Config.General;
import com.trakdesk.Config.ResponseCallback;

/**
 * Helpdesk resource
 * This provides access to Trakdesk helpdesk API
 *
 * @see <a href="https://developers.trakdesk.com/api/#helpdesk"> Helpdesk </a>
 */

public class Helpdesk extends AbstractLayer  {

    public Helpdesk() {
        super();
        super.endpoint = "/helpdesk";
    }

    /**
     * This gets the Helpdesk Settings
     *
     * @param callback {@link ResponseCallback}
     */
    public void settings(ResponseCallback callback) {
        General.get(this.endpoint + "/settings", null, callback);
    }
}
