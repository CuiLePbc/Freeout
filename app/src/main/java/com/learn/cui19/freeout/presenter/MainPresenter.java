package com.learn.cui19.freeout.presenter;

import com.learn.cui19.freeout.model.FreeGoBean;
import com.learn.cui19.freeout.model.FreeGoModel;
import com.learn.cui19.freeout.view.MainView;

/**
 * Created by cui19 on 2016/11/14.
 */

public class MainPresenter implements Presenter<MainView>, IMainPresenter {
    private MainView mMainView;
    private FreeGoModel mFreeGoModel;

    public MainPresenter(MainView mainView) {
        attachView(mainView);
        mFreeGoModel = new FreeGoModel();
    }

    @Override
    public void loadDataSuccess(FreeGoBean freeGoBean) {

    }

    @Override
    public void loadDataFailure() {

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
