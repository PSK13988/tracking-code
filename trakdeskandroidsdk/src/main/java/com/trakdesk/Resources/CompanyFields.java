package com.trakdesk.Resources;

import com.trakdesk.Traits.ListTrait;

/**
 * Company fields resource
 * This provides access to Trakdesk company fields API
 *
 * @see <a href="https://developers.trakdesk.com/api/#company-fields"> Company Fields </a>
 */

public class CompanyFields extends AbstractLayer implements ListTrait {

    public CompanyFields() {
        super();
        super.endpoint = "/company_fields";
    }
}



