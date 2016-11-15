package com.learn.cui19.freeout.presenter;

/**
 * Created by cui19 on 2016/11/14.
 */

public interface Presenter<V> {
    void attachView(V v);
    void detachView();
}
