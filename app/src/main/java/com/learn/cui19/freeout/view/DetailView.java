package com.learn.cui19.freeout.view;

import com.learn.cui19.freeout.model.FreeGoDetailBean;

/**
 * Created by cui19 on 2016/11/22.
 */

public interface DetailView {
    void showProgressBar();
    void hideProgressBar();
    void showData(FreeGoDetailBean detailBean);
}
