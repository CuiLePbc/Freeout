package com.learn.cui19.freeout.presenter;

import com.learn.cui19.freeout.model.FreeGoDetailBean;
import com.learn.cui19.freeout.model.FreeGoDetailModel;
import com.learn.cui19.freeout.view.DetailView;

/**
 * Created by cui19 on 2016/11/22.
 */

public class DetailPresenter implements Presenter<DetailView>, IDetailPresenter {

    private DetailView mDetailView;
    private FreeGoDetailModel mModel;

    public DetailPresenter(DetailView detailView) {
        attachView(detailView);
        mModel = new FreeGoDetailModel(this);
    }

    public void loadData(String url) {
        mDetailView.showProgressBar();
        mModel.loadData(url);
    }

    @Override
    public void attachView(DetailView detailView) {
        this.mDetailView = detailView;
    }

    @Override
    public void detachView() {
        this.mDetailView = null;
    }

    @Override
    public void loadDataSuccess(FreeGoDetailBean detailBean) {
        mDetailView.showData(detailBean);
        mDetailView.hideProgressBar();
    }

    @Override
    public void loadDataFailure() {
        mDetailView.hideProgressBar();
    }
}
