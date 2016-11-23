package com.learn.cui19.freeout.presenter;

import com.learn.cui19.freeout.model.FreeGoBean;

import java.util.List;

/**
 * Created by cui19 on 2016/11/14.
 */

public interface IMainPresenter {
    void loadDataSuccess(List<FreeGoBean> freeGoBeans);
    void addDataSuccess(List<FreeGoBean> freeGoBeens);
    void loadDataFailure();
    void addDataFailure();
}
