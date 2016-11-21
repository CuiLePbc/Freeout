package com.learn.cui19.freeout.model;

import com.learn.cui19.freeout.network.api.FreeGoBeanApi;
import com.learn.cui19.freeout.network.convert.MyFreeGoListConvertFactory;
import com.learn.cui19.freeout.presenter.IMainPresenter;
import com.learn.cui19.freeout.utils.JsoupContact;
import com.learn.cui19.freeout.utils.JsoupUtils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

/**
 * Created by cui19 on 2016/11/14.
 */

public class FreeGoModel {
    private IMainPresenter mIMainPresenter;

    public FreeGoModel(){}

    public FreeGoModel(IMainPresenter iMainPresenter) {
        this.mIMainPresenter = iMainPresenter;
    }

    public void loadData(int num) {

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(JsoupContact.MA_FENG_WANG_LV_YOU_BASE_URLS[num - 1])
                .addConverterFactory(MyFreeGoListConvertFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build();
        retrofit.create(FreeGoBeanApi.class)
                .getHtml()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Subscriber<FreeGoListBean>() {
                    @Override
                    public void onCompleted() {}
                    @Override
                    public void onError(Throwable e) {
                        mIMainPresenter.loadDataFailure();
                        e.printStackTrace();
                    }
                    @Override
                    public void onNext(FreeGoListBean bean) {
                        System.out.println(bean.getFreeGoBeans().size());
                        mIMainPresenter.loadDataSuccess(bean.getFreeGoBeans());
                    }
                });
    }
}
