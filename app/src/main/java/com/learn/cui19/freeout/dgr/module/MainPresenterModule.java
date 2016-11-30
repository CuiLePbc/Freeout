package com.learn.cui19.freeout.dgr.module;

import com.learn.cui19.freeout.dgr.scope.PerActivity;
import com.learn.cui19.freeout.model.FreeGoModel;
import com.learn.cui19.freeout.presenter.MainPresenter;

import dagger.Module;
import dagger.Provides;

/**
 * Created by cui19 on 2016/11/30.
 */
@Module
public class MainPresenterModule {
    private MainPresenter mMainPresenter;
    public MainPresenterModule(MainPresenter mainPresenter) {
        this.mMainPresenter = mainPresenter;
    }

    @Provides
    @PerActivity
    MainPresenter providePresenter() {
        return mMainPresenter;
    }


    @Provides
    @PerActivity
    FreeGoModel provideModel(){
        return new FreeGoModel(mMainPresenter);
    }
}
