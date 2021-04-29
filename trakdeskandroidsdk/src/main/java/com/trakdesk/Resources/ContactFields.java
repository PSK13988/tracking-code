package com.trakdesk.Resources;

import com.trakdesk.Traits.ListTrait;

/**
 * Contact fields resource
 * This provides access to Trakdesk contact fields API
 *
 * @see <a href="https://developers.trakdesk.com/api/#contact-fields"> Contact Fields </a>
 */

public class ContactFields extends AbstractLayer implements ListTrait {

    public ContactFields() {
        super();
        super.endpoint = "/ticket_fields";
    }
}



