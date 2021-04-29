package com.trakdesk.Resources;

import com.trakdesk.Traits.ViewTrait;
import com.trakdesk.Traits.ListTrait;

/**
 * Business Hours resource
 * This provides access to Trakdesk business hours API
 *
 * @see <a href="https://developers.trakdesk.com/api/#business-hours"> Business Hours </a>
 */

public class BusinessHours extends AbstractLayer implements ViewTrait, ListTrait {

    public BusinessHours() {
        super();
        super.endpoint = "/business_hours";
    }
}
