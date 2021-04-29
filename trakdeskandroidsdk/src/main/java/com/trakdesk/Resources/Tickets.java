package com.trakdesk.Resources;

import com.trakdesk.Traits.ViewTrait;
import com.trakdesk.Traits.ListTrait;
import com.trakdesk.Traits.CreateTrait;
import com.trakdesk.Traits.UpdateTrait;
import com.trakdesk.Traits.DeleteTrait;

/**
 * Tickets resource
 * This provides access to Trakdesk tickets API
 *
 * @see <a href="https://developers.trakdesk.com/api/#tickets"> Tickets </a>
 */

public class Tickets extends AbstractLayer implements ViewTrait, ListTrait, CreateTrait, UpdateTrait, DeleteTrait {

    public Tickets() {
        super();
        super.endpoint = "/tickets";
    }
}



