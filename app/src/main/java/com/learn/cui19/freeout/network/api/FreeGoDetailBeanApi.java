package com.learn.cui19.freeout.network.api;

import com.learn.cui19.freeout.model.FreeGoDetailBean;

import retrofit2.http.GET;
import retrofit2.http.Path;
import rx.Observable;

/**
 * Created by cui19 on 2016/12/14.
 */

public interface FreeGoDetailBeanApi {
    @GET("{detailurl}")
    Observable<FreeGoDetailBean> getXieChenDetail(@Path("detailurl") String url);
}
