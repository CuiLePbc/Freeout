package com.learn.cui19.freeout.view;

import com.learn.cui19.freeout.model.FreeGoBean;

import java.util.List;

/**
 * Created by cui19 on 2016/11/14.
 * 处理业务需要的方法
 */

public interface MainView {
    void showProgressBar();
    void hideProgressBar();
    void showData(List<FreeGoBean> freeGoBeans);
    void addData(List<FreeGoBean> freeGoBeens);
}
