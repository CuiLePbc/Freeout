package com.learn.cui19.freeout.dgr.module;

import android.app.Application;
import android.content.Context;

import com.learn.cui19.freeout.MyApplication;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by cui19 on 2016/11/30.
 */
@Module
public final class ApplicationModule {
    private final Context mContext;
    public ApplicationModule(MyApplication application) {
        this.mContext = application.getApplicationContext();
    }

    @Singleton
    @Provides
    public Context provideContext() {
        return mContext;
    }
}
