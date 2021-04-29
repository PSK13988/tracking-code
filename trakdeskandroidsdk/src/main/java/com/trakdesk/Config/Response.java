package com.trakdesk.Config;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

class Response {

    private okhttp3.Response response;

    Response(okhttp3.Response response) {
        this.response = response;
        if (this.response == null) {
            return;
        }
    }

    /**
     * This Method is to get headers
     *
     * @return JSONObject
     */
    JSONObject getHeaders() {
        JSONObject headers = new JSONObject();
        for (String key : response.headers().names()) {
            try {
                String value = response.headers().get(key);
                if (value != null) {
                    headers.put(key.toLowerCase(), isNumeric(value) ? Integer.parseInt(value) : value);
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        return headers;
    }

    /**
     * This Method is to get body
     *
     * @return JSONObject
     */
    JSONObject getBody() {
        JSONObject object = new JSONObject();
        if (response.body() != null) {
            try {
                String body = response.body().string();
                JSONObject jsonObject = new JSONObject();

                // populate the array
                if (!body.isEmpty()) {
                    JSONArray jsonArray = new JSONArray(body);
                    jsonObject.put("data", jsonArray);
                    object = jsonObject;
                    Log.d("Response", "getBody: " + object);
                }

            } catch (IOException | JSONException e) {
                e.printStackTrace();
            }
        }
        return object;
    }

    /**
     * This Method is to get status code
     *
     * @return StatusCode
     */
    int getStatusCode() {
        return response.code();
    }

    /**
     * check if Value is int or not
     *
     * @param s {@link String}
     * @return Boolean
     */
    private boolean isNumeric(String s) {
        try {
            Integer.parseInt(s);
            return true;
        } catch (NumberFormatException ex) {
            return false;
        }
    }
}
