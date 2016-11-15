package com.learn.cui19.freeout.utils;

import java.io.IOException;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;


/**
 * Created by cui19 on 2016/11/15.
 */

public class NetUtil {
    public static Response getResponse(String baseUrl)throws IOException {
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder().url(baseUrl).build();
        Response response = client.newCall(request).execute();
        if (response.isSuccessful()) {
            return response;
        }
        return null;
    }
}
