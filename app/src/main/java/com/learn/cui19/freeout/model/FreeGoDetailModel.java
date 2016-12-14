package com.learn.cui19.freeout.model;

import com.learn.cui19.freeout.jsoup.JsoupContact;
import com.learn.cui19.freeout.network.api.FreeGoDetailBeanApi;
import com.learn.cui19.freeout.network.convert.MyFreeGoDetailConvertFactory;
import com.learn.cui19.freeout.presenter.IDetailPresenter;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by cui19 on 2016/11/22.
 */

public class FreeGoDetailModel {
    private IDetailPresenter mDetailPresenter;
    public FreeGoDetailModel(IDetailPresenter iDetailPresenter) {
        this.mDetailPresenter = iDetailPresenter;
    }

    public void loadData(String url) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(JsoupContact.XIECHENG_BASE_URL)
                .addConverterFactory(MyFreeGoDetailConvertFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build();
        retrofit.create(FreeGoDetailBeanApi.class)
                .getXieChenDetail(url)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Subscriber<FreeGoDetailBean>() {
                    @Override
                    public void onCompleted() {}

                    @Override
                    public void onError(Throwable e) {
                        mDetailPresenter.loadDataFailure();
                    }

                    @Override
                    public void onNext(FreeGoDetailBean freeGoDetailBean) {
                        mDetailPresenter.loadDataSuccess(freeGoDetailBean);
                    }
                });
    }
}
