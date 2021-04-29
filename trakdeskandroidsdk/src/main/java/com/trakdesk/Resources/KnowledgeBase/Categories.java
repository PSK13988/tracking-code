package com.trakdesk.Resources.KnowledgeBase;

import com.trakdesk.Config.General;
import com.trakdesk.Config.ResponseCallback;
import com.trakdesk.Resources.AbstractLayer;
import com.trakdesk.Traits.DeleteTrait;
import com.trakdesk.Traits.UpdateTrait;
import com.trakdesk.Traits.ViewTrait;

import org.json.JSONObject;

import java.util.HashMap;

/**
 * Knowledgebase categories resource
 * This provides access to Trakdesk knowledgebae categories API
 *
 * @see <a href="https://developers.trakdesk.com/api/categories"> Categories </a>
 */

public class Categories extends AbstractLayer implements ViewTrait, UpdateTrait, DeleteTrait {

    private String folders_endpoint;

    public Categories() {
        super();
        super.endpoint = "/knowledgebase/categories";
        this.folders_endpoint = "/knowledgebase/folders";
    }

    /**
     * @param id int id
     * @return String endpoint
     */
    private String folderCategoriesEndpoint(int id) {
        return this.folders_endpoint + "/" + id + "/categories";
    }

    /**
     * @param folder_id {@link int}
     * @param options      {@link HashMap}
     * @param callback     {@link ResponseCallback}
     */
    public void list(int folder_id, HashMap<String, String> options, ResponseCallback callback) {
        General.get(this.folderCategoriesEndpoint(folder_id), options, callback);
    }

    /**
     * @param folder_id {@link int}
     * @param params       {@link JSONObject}
     * @param callback     {@link ResponseCallback}
     */
    public void create(int folder_id, JSONObject params, ResponseCallback callback) {
        General.create(this.folderCategoriesEndpoint(folder_id), params, callback);
    }

}
