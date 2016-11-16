package com.learn.cui19.freeout.model;

import android.util.Log;

import com.learn.cui19.freeout.network.api.FreeGoBeanApi;
import com.learn.cui19.freeout.presenter.IMainPresenter;
import com.learn.cui19.freeout.utils.JsoupContact;
import com.learn.cui19.freeout.utils.JsoupUtils;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import rx.Observable;
import rx.Scheduler;
import rx.Single;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by cui19 on 2016/11/14.
 */

public class FreeGoModel {
    private IMainPresenter mIMainPresenter;

    public FreeGoModel(IMainPresenter iMainPresenter) {
        this.mIMainPresenter = iMainPresenter;
    }

    public void loadData(int num) {
        Call<String> call = new Retrofit.Builder()
                .baseUrl(JsoupContact.MA_FENG_WANG_LV_YOU_BASE_URL)
                .build().create(FreeGoBeanApi.class)
                .getHtml();
        call.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                if (response.isSuccessful()) {
                    String html = response.body();
                    mIMainPresenter.loadDataSuccess(JsoupUtils.getFreeGoBeansFromMaFen(html));
                }
            }
            @Override
            public void onFailure(Call<String> call, Throwable t) {
                Log.e("NetUtil", t.getMessage());
            }
        });
    }
}
