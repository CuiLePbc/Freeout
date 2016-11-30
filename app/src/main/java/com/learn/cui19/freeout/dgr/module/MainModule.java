package com.learn.cui19.freeout.dgr.module;

import com.learn.cui19.freeout.dgr.scope.PerActivity;
import com.learn.cui19.freeout.model.FreeGoBean;
import com.learn.cui19.freeout.presenter.MainPresenter;
import com.learn.cui19.freeout.ui.MainActivity;
import com.learn.cui19.freeout.ui.adapter.MyMainContentAdapter;

import java.util.ArrayList;

import dagger.Module;
import dagger.Provides;

/**
 * Created by cui19 on 2016/11/30.
 */
@Module
public class MainModule {
    private MainActivity mActivity;
    public MainModule(MainActivity activity) {
        this.mActivity = activity;
    }

    @PerActivity
    @Provides
    MainActivity provideActivity() {
        return mActivity;
    }

    @PerActivity
    @Provides
    MainPresenter providePresenter() {
        return new MainPresenter(mActivity);
    }

    @PerActivity
    @Provides
    MyMainContentAdapter provideAdapter() {
        return new MyMainContentAdapter(new ArrayList<FreeGoBean>(), mActivity);
    }
}
