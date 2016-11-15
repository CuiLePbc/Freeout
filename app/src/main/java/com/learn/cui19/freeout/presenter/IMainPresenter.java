package com.learn.cui19.freeout.presenter;

import com.learn.cui19.freeout.model.FreeGoBean;

/**
 * Created by cui19 on 2016/11/14.
 */

public interface IMainPresenter {
    void loadDataSuccess(FreeGoBean freeGoBean);
    void loadDataFailure();
}
