package com.learn.cui19.freeout.network.api;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by cui19 on 2016/11/16.
 */

public interface FreeGoBeanApi {
    @GET("/gonglve")
    Call<String> getHtml();
}
