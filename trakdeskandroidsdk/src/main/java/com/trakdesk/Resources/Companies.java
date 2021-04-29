package com.trakdesk.Resources;

import com.trakdesk.Traits.ViewTrait;
import com.trakdesk.Traits.ListTrait;
import com.trakdesk.Traits.CreateTrait;
import com.trakdesk.Traits.UpdateTrait;
import com.trakdesk.Traits.DeleteTrait;

/**
 * Companies resource
 * This provides access to Trakdesk companies API
 *
 * @see <a href="https://developers.trakdesk.com/api/#companies"> Companies </a>
 */

public class Companies extends AbstractLayer implements ViewTrait, ListTrait, CreateTrait, UpdateTrait, DeleteTrait {

    public Companies() {
        super();
        super.endpoint = "/companies";
    }
}
