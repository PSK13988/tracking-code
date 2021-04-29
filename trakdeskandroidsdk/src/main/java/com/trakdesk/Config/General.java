package com.trakdesk.Config;

import android.os.Handler;
import android.os.Looper;
import com.trakdesk.Exception.ApiException;
import com.trakdesk.Trakdesk;
import org.json.JSONException;
import org.json.JSONObject;
import java.io.IOException;
import java.util.HashMap;
import javax.net.ssl.SSLHandshakeException;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Headers;
import okhttp3.HttpUrl;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;

public class General {
    private static JSONObject finalObj = null;
    private static final MediaType MediaTypeJSON = MediaType.parse("application/json; charset=utf-8");

    /**
     * This generates the API endpoint URL
     *
     * @param endpoint {@link String}
     * @return String endpoint
     */
    private static String endpoint(String endpoint) {
        return Trakdesk.baseUrl + endpoint;
    }

    /**
     * @param endpoint {@link String}
     * @param params   {@link   JSONObject}
     * @param callback {@link ResponseCallback}
     */
    public static void create(String endpoint, JSONObject params, ResponseCallback callback) {
        requestAction("POST", endpoint(endpoint), params, callback);
    }

    /**
     * @param endpoint {@link String}
     * @param options  {@link HashMap}
     * @param callback {@link ResponseCallback}
     */
    public static void get(String endpoint, HashMap<String, String> options, ResponseCallback callback) {
        String url;
        if (options != null) {
            HttpUrl.Builder urlBuilder = HttpUrl.parse(endpoint(endpoint)).newBuilder();
            for (String key : options.keySet()) {
                urlBuilder.addQueryParameter(key, options.get(key));
            }
            url = urlBuilder.build().toString();
        } else {
            url = endpoint(endpoint);
        }
        requestAction("GET", url, null, callback);
    }

    /**
     * @param endpoint {@link String}
     * @param params   {@link   JSONObject}
     * @param callback {@link ResponseCallback}
     */
    public static void update(String endpoint, JSONObject params, ResponseCallback callback) {
        requestAction("PUT", endpoint(endpoint), params, callback);
    }

    /**
     * @param endpoint {@link String}
     * @param callback {@link ResponseCallback}
     */
    public static void delete(String endpoint, ResponseCallback callback) {
        requestAction("DELETE", endpoint(endpoint), null, callback);
    }

    /**
     * Request action
     * This performs the actual API request
     *
     * @param method   {@link String}
     * @param url      {@link String}
     * @param params   {@link JSONObject}
     * @param callback {@link ResponseCallback}
     */
    private static void requestAction(String method, String url, JSONObject params, final ResponseCallback callback) {
        HashMap<String, String> headerMap = new HashMap<>();
        headerMap.put("Content-Type", "application/json");
        headerMap.put("Authorization", Trakdesk.auth);
        Headers headers = Headers.of(headerMap);
        HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
        logging.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient client = new OkHttpClient.Builder().addInterceptor(logging).build();
        Request request = null;
        switch (method) {
            case "GET":
                request = new Request.Builder().headers(headers).url(url).get().build();
                break;
            case "POST":
                request = new Request.Builder().headers(headers).url(url).post(RequestBody.create(MediaTypeJSON, params != null ? params.toString() : "")).build();
                break;
            case "PUT":
                request = new Request.Builder().headers(headers).url(url).put(RequestBody.create(MediaTypeJSON, params != null ? params.toString() : "")).build();
                break;
            case "DELETE":
                request = new Request.Builder().headers(headers).url(url).delete().build();
                break;
        }
        if (request != null) {
            client.newCall(request).enqueue(new Callback() {
                Handler mainHandler = new Handler(Looper.getMainLooper());
                @Override
                public void onFailure(Call call, IOException e) {
                    mainHandler.post(new Runnable() {
                        @Override
                        public void run() {
                            String message = e.getClass().getName() + " " + e.getMessage();
                            String statusCode = e instanceof SSLHandshakeException ? "ssl_failure" : "io_failure";
                            ApiException exception = new ApiException(message, statusCode);
                            callback.onResponse(exception.setError());
                        }
                    });
                }
                @Override
                public void onResponse(Call call, Response res) throws IOException {
                    if (res != null) {
                        JSONObject object;
                        JSONObject extra = new JSONObject();
                        com.trakdesk.Config.Response response = new com.trakdesk.Config.Response(res);
                        try {
                            extra.put("success", res.isSuccessful());
                            if (res.isSuccessful()) {
                                JSONObject meta = new JSONObject();
                                meta.put("status_code", response.getStatusCode());
                                if (response.getHeaders().length() > 0) {
                                    meta.put("headers", response.getHeaders());
                                }
                                extra.put("meta", meta);
                            }
                            object = response.getBody();
                            if (object.length() != 0) {
                                finalObj = new JSONObject(extra.toString().substring(0, extra.toString().length() - 1).concat("," + object.toString().substring(1)));
                            } else {
                                finalObj = extra;
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                            finalObj = extra;
                        }
                    }
                    mainHandler.post(new Runnable() {
                        @Override
                        public void run() {
                            callback.onResponse(finalObj);
                        }
                    });
                }
            });
        }
    }
}
