package com.learn.cui19.freeout.model;

import com.learn.cui19.freeout.network.api.FreeGoBeanApi;
import com.learn.cui19.freeout.network.convert.MyFreeGoListConvertFactory;
import com.learn.cui19.freeout.presenter.IMainPresenter;
import com.learn.cui19.freeout.jsoup.JsoupContact;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
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

    /**
     * 加载数据，每页九条
     * @param page 页码
     */
    public void loadData(final int page, String city) {

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(JsoupContact.XIECHENG_YOUJI_BASE_URLS)
                .addConverterFactory(MyFreeGoListConvertFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build();
        retrofit.create(FreeGoBeanApi.class)
                .getXieChenHtml(city, Integer.toString(page))
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Subscriber<FreeGoListBean>() {
                    @Override
                    public void onCompleted() {}
                    @Override
                    public void onError(Throwable e) {
                        if (page == 1) {
                            mIMainPresenter.loadDataFailure();
                        } else {
                            mIMainPresenter.addDataFailure();
                        }
                        e.printStackTrace();
                    }
                    @Override
                    public void onNext(FreeGoListBean bean) {
                        if (page == 1) {
                            mIMainPresenter.loadDataSuccess(bean.getFreeGoBeans());
                        } else {
                            mIMainPresenter.addDataSuccess(bean.getFreeGoBeans());
                        }
                    }
                });
    }
}
