package com.trakdesk.Resources;

import com.trakdesk.Traits.ViewTrait;
import com.trakdesk.Traits.ListTrait;

/**
 * Brands resource
 * This provides access to Trakdesk brands API
 *
 * @see <a href="https://developers.trakdesk.com/api/#brands"> Brands </a>
 */

public class Brands extends AbstractLayer implements ViewTrait, ListTrait {

    public Brands() {
        super();
        super.endpoint = "/brands";
    }
}
