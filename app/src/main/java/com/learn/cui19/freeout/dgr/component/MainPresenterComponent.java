package com.learn.cui19.freeout.dgr.component;

import com.learn.cui19.freeout.dgr.module.MainModule;
import com.learn.cui19.freeout.dgr.module.MainPresenterModule;
import com.learn.cui19.freeout.dgr.scope.PerActivity;
import com.learn.cui19.freeout.presenter.MainPresenter;

import dagger.Component;

/**
 * Created by cui19 on 2016/11/30.
 */
@PerActivity
@Component(modules = MainPresenterModule.class)
public interface MainPresenterComponent {
    void inject(MainPresenter mainPresenter);
}
