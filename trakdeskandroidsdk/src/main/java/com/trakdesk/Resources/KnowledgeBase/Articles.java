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
 * knowledgeBase Article resource
 * This provides access to Trakdesk knowledgeBase article API
 *
 * @see <a href="https://developers.trakdesk.com/api/#articles"> Articals </a>
 */

public class Articles extends AbstractLayer implements ViewTrait, UpdateTrait, DeleteTrait {

    private String categories_endpoint;

    public Articles() {
        super();
        super.endpoint = "/knowledgebase/articles";
        this.categories_endpoint = "/knowledgebase/categories";
    }

    /**
     * @param id {@link int}
     * @return endpoint {@link String}
     */
    private String categoryArticlesEndpoint(int id) {
        return this.categories_endpoint + "/" + id + "/articles";
    }

    /**
     * This lists knowledgebase articles
     *
     * @param category_id {@link int}
     * @param options     {@link HashMap}
     * @param callback    {@link ResponseCallback}
     */
    public void list(int category_id, HashMap<String, String> options, ResponseCallback callback) {
        General.get(this.categoryArticlesEndpoint(category_id), options, callback);
    }

    /**
     * This creates knowledgebase articles
     *
     * @param category_id {@link int}
     * @param params      {@link JSONObject}
     * @param callback    {@link ResponseCallback}
     */
    public void create(int category_id, JSONObject params, ResponseCallback callback) {
        General.create(this.categoryArticlesEndpoint(category_id), params, callback);
    }

}
