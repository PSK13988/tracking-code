package com.trakdesk.Resources;

import com.trakdesk.Traits.ListTrait;

/**
 * Ticket fields resource
 * This provides access to Trakdesk ticket fields API
 *
 * @see <a href="https://developers.trakdesk.com/api/#ticket-fields"> Ticket Fields </a>
 */

public class TicketFields extends AbstractLayer implements ListTrait {

    public TicketFields() {
        super();
        super.endpoint = "/ticket_fields";
    }
}



