package com.trakdesk.Resources;

import com.trakdesk.Traits.ViewTrait;
import com.trakdesk.Traits.ListTrait;

/**
 * Email Accounts resource
 * This provides access to Trakdesk email accounts API
 *
 * @see <a href="https://developers.trakdesk.com/api/#email-accounts"> Email Accounts </a>
 */

public class EmailAccounts extends AbstractLayer implements ViewTrait, ListTrait {

    public EmailAccounts() {
        super();
        super.endpoint = "/email_accounts";
    }
}
