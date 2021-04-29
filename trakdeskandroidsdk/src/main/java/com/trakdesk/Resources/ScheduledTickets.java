package com.trakdesk.Resources;

import com.trakdesk.Traits.ViewTrait;
import com.trakdesk.Traits.ListTrait;
import com.trakdesk.Traits.DeleteTrait;

/**
 * Scheduled Tickets resource
 * This provides access to Trakdesk agents API
 *
 * @see <a href="https://developers.trakdesk.com/api/#scheduled-tickets"> Scheduled Tickets </a>
 */

public class ScheduledTickets extends AbstractLayer implements ViewTrait, ListTrait, DeleteTrait {

    public ScheduledTickets() {
        super();
        super.endpoint = "/scheduled_tickets";
    }
}
