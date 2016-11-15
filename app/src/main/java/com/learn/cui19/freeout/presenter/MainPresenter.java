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
     * 加载数据
     * @param lanmuNum 栏目序号
     */
    public void loadData(int lanmuNum) {
        mMainView.showProgressBar();
        mFreeGoModel.loadData(lanmuNum);
    }

    @Override
    public void loadDataSuccess(List<FreeGoBean> freeGoBeans) {
        mMainView.showData(freeGoBeans);
        mMainView.hideProgressBar();
    }

    @Override
    public void loadDataFailure() {
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
