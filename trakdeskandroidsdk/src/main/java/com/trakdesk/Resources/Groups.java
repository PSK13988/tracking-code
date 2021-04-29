package com.trakdesk.Resources;

import com.trakdesk.Traits.ViewTrait;
import com.trakdesk.Traits.ListTrait;
import com.trakdesk.Traits.CreateTrait;
import com.trakdesk.Traits.UpdateTrait;
import com.trakdesk.Traits.DeleteTrait;

/**
 * Groups resource
 * This provides access to Trakdesk groups API
 *
 * @see <a href="https://developers.trakdesk.com/api/#groups"> Groups </a>
 */

public class Groups extends AbstractLayer implements ViewTrait, ListTrait, CreateTrait, UpdateTrait, DeleteTrait {

    public Groups() {
        super();
        super.endpoint = "/groups";
    }
}
