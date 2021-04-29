package com.trakdesk.Resources;

import com.trakdesk.Traits.ViewTrait;
import com.trakdesk.Traits.ListTrait;
import com.trakdesk.Traits.CreateTrait;
import com.trakdesk.Traits.UpdateTrait;
import com.trakdesk.Traits.DeleteTrait;

/**
 * Announcements resource
 * This provides access to Trakdesk announcements API
 *
 * @see <a href="https://developers.trakdesk.com/api/#announcements"> Announcements </a>
 */

public class Announcements extends AbstractLayer implements ViewTrait, ListTrait, CreateTrait, UpdateTrait, DeleteTrait {

    public Announcements() {
        super();
        super.endpoint = "/announcements";
    }
}
