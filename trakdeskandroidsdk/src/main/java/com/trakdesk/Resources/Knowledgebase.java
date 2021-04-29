package com.trakdesk.Resources;

import com.trakdesk.Resources.KnowledgeBase.Articles;
import com.trakdesk.Resources.KnowledgeBase.Categories;
import com.trakdesk.Resources.KnowledgeBase.Folders;

/**
 * Knowledgebase resource
 * This provides access to Trakdesk knowledgebase API
 *
 * @see <a href="https://developers.trakdesk.com/api/#knowledgebase"> Knowledgebase </a>
 */
public class Knowledgebase {

    public Folders folders;
    public Categories categories;
    public Articles articles;

    public Knowledgebase() {

        articles = new Articles();
        categories = new Categories();
        folders = new Folders();
    }
}
