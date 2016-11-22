package com.learn.cui19.freeout.network.api;

import com.learn.cui19.freeout.model.FreeGoListBean;

import retrofit2.http.GET;
import rx.Observable;

/**
 * Created by cui19 on 2016/11/16.
 */

public interface FreeGoBeanApi {

    /* 蚂蜂网 */
    @GET("gonglve")
    Observable<FreeGoListBean> getHtml();

    /* 携程网 */
    @GET("shanghai1.html")
    Observable<FreeGoListBean> getBeiJinHtml();

}
