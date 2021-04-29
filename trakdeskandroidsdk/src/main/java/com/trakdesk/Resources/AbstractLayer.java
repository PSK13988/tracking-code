package com.trakdesk.Resources;

public class AbstractLayer {

    public String endpoint;

    /**
     * Provide API endpoint
     *
     * @return Endpoint
     */
    public String endpoint(Integer id) {
        return id != null ? this.endpoint + "/" + id : this.endpoint;
    }
}

