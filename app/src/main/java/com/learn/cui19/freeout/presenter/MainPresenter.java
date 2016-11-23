package com.learn.cui19.freeout.presenter;

import com.learn.cui19.freeout.model.FreeGoBean;
import com.learn.cui19.freeout.model.FreeGoModel;
import com.learn.cui19.freeout.view.MainView;

import java.util.List;

/**
 * Created by cui19 on 2016/11/14.
 */

public class MainPresenter implements Presenter<MainView>, IMainPresenter {
    private MainView mMainView;
    private FreeGoModel mFreeGoModel;

    public MainPresenter(MainView mainView) {
        attachView(mainView);
        mFreeGoModel = new FreeGoModel(this);
    }

    /**
     * 加载数据,默认加载第一页
     */
    public void loadData(String city) {
        mMainView.showProgressBar();
        mFreeGoModel.loadData(1, city);
    }

    /**
     * 添加数据
     * @param page 添加的页码数
     */
    public void addData(int page, String city) {
        mMainView.showProgressBar();
        mFreeGoModel.loadData(page, city);
    }

    @Override
    public void loadDataSuccess(List<FreeGoBean> freeGoBeans) {
        mMainView.showData(freeGoBeans);
        mMainView.hideProgressBar();
    }

    @Override
    public void addDataSuccess(List<FreeGoBean> freeGoBeens) {
        mMainView.addData(freeGoBeens);
        mMainView.hideProgressBar();
    }

    @Override
    public void loadDataFailure() {
        mMainView.hideProgressBar();
    }

    @Override
    public void addDataFailure() {
        mMainView.hideProgressBar();
    }

    @Override
    public void attachView(MainView view) {
        this.mMainView = view;
    }

    @Override
    public void detachView() {
        this.mMainView = null;
    }
}
