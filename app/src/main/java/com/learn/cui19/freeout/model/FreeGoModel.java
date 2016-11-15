package com.learn.cui19.freeout.model;

import com.learn.cui19.freeout.presenter.IMainPresenter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by cui19 on 2016/11/14.
 */

public class FreeGoModel {
    private IMainPresenter mIMainPresenter;

    public FreeGoModel(IMainPresenter iMainPresenter) {
        this.mIMainPresenter = iMainPresenter;
    }

    public void loadData(int num) {
        List<FreeGoBean> freeGoBeans = new ArrayList<FreeGoBean>();

        mIMainPresenter.loadDataSuccess(freeGoBeans);
    }
}
