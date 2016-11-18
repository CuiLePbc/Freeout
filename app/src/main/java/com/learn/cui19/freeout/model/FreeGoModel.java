package com.learn.cui19.freeout.model;

import android.util.Log;

import com.learn.cui19.freeout.network.api.FreeGoBeanApi;
import com.learn.cui19.freeout.presenter.IMainPresenter;
import com.learn.cui19.freeout.network.JsoupContact;
import com.learn.cui19.freeout.network.JsoupUtils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.functions.Func1;
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

        Observable.just(JsoupContact.MA_FENG_WANG_LV_YOU_BASE_URLS[num - 1])
                .map(new Func1<String, List<FreeGoBean>>() {
                        @Override
                        public List<FreeGoBean> call(String s) {
                            try {
                                OkHttpClient client = new OkHttpClient();
                                Request request = new Request.Builder().url(s).build();
                                okhttp3.Response response = client.newCall(request).execute();
                                return JsoupUtils.getFreeGoBeansFromMaFen(response.body().string());
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                            return new ArrayList<FreeGoBean>();
                        }
                    })
                .filter(new Func1<List<FreeGoBean>, Boolean>() {
                    @Override
                    public Boolean call(List<FreeGoBean> datas) {
                        return datas.size() > 0;
                    }
                })
                .observeOn(Schedulers.io())
                .subscribeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<List<FreeGoBean>>() {
                    @Override
                    public void call(List<FreeGoBean> datas) {
                        mIMainPresenter.loadDataSuccess(datas);
                    }
                });

    }
}
