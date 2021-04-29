package com.trakdesk.Resources.KnowledgeBase;

import com.trakdesk.Resources.AbstractLayer;
import com.trakdesk.Traits.CreateTrait;
import com.trakdesk.Traits.DeleteTrait;
import com.trakdesk.Traits.ListTrait;
import com.trakdesk.Traits.UpdateTrait;
import com.trakdesk.Traits.ViewTrait;

/**
 * Knowledgebase Folder resource
 * This provides access to Trakdesk knowledgebae folders API
 *
 * @see <a href="https://developers.trakdesk.com/api/#folders"> Folders </a>
 */
public class Folders extends AbstractLayer implements CreateTrait, ViewTrait, UpdateTrait, ListTrait, DeleteTrait {

    public Folders() {
        super();
        super.endpoint = "/knowledgebase/folders";
    }
}
