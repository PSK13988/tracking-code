package com.trakdesk.Resources;

import com.trakdesk.Traits.ViewTrait;
import com.trakdesk.Traits.ListTrait;
import com.trakdesk.Traits.CreateTrait;
import com.trakdesk.Traits.UpdateTrait;
import com.trakdesk.Traits.DeleteTrait;

/**
 * Roles resource
 * This provides access to Trakdesk roles API
 *
 * @see <a href="https://developers.trakdesk.com/api/#roles"> Roles </a>
 */

public class Roles extends AbstractLayer implements ViewTrait, ListTrait, CreateTrait, UpdateTrait, DeleteTrait {

    public Roles() {
        super();
        super.endpoint = "/roles";
    }
}
