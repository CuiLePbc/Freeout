package com.learn.cui19.freeout.model;

import java.util.List;

/**
 * Created by cui19 on 2016/11/21.
 */

public class FreeGoListBean {
    public List<FreeGoBean> mFreeGoBeans;

    public FreeGoListBean(){}

    public FreeGoListBean(List<FreeGoBean> freeGoBeans) {
        mFreeGoBeans = freeGoBeans;
    }

    public List<FreeGoBean> getFreeGoBeans() {
        return mFreeGoBeans;
    }

    public void setFreeGoBeans(List<FreeGoBean> freeGoBeans) {
        mFreeGoBeans = freeGoBeans;
    }
}
