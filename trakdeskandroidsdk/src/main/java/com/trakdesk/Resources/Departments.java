package com.trakdesk.Resources;

import com.trakdesk.Traits.ViewTrait;
import com.trakdesk.Traits.ListTrait;
import com.trakdesk.Traits.CreateTrait;
import com.trakdesk.Traits.UpdateTrait;
import com.trakdesk.Traits.DeleteTrait;

/**
 * Departments resource
 * This provides access to Trakdesk departments API
 *
 * @see <a href="https://developers.trakdesk.com/api/#departments"> Departments </a>
 */

public class Departments extends AbstractLayer implements ViewTrait, ListTrait, CreateTrait, UpdateTrait, DeleteTrait {

    public Departments() {
        super();
        super.endpoint = "/departments";
    }
}
