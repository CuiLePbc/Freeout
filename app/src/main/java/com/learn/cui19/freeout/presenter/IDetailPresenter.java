package com.learn.cui19.freeout.presenter;

import com.learn.cui19.freeout.model.FreeGoDetailBean;

/**
 * Created by cui19 on 2016/11/22.
 */

public interface IDetailPresenter {
    void loadDataSuccess(FreeGoDetailBean detailBean);
    void loadDataFailure();
}
