package com.trakdesk.Resources;

import com.trakdesk.Traits.ViewTrait;
import com.trakdesk.Traits.ListTrait;

/**
 * SLA Policies resource
 * This provides access to Trakdesk sla policies API
 *
 * @see <a href="https://developers.trakdesk.com/api/#sla-policies"> SLA Policies </a>
 */

public class SlaPolicies extends AbstractLayer implements ViewTrait, ListTrait {

    public SlaPolicies() {
        super();
        super.endpoint = "/sla_policies";
    }
}
