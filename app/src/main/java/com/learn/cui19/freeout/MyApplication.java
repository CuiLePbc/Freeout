package com.learn.cui19.freeout;

import android.app.Application;

import com.learn.cui19.freeout.dgr.component.ApplicationComponent;
import com.learn.cui19.freeout.dgr.component.DaggerApplicationComponent;
import com.learn.cui19.freeout.dgr.module.ApplicationModule;

import javax.inject.Inject;

/**
 * Created by cui19 on 2016/11/21.
 */

public class MyApplication extends Application {
    private ApplicationComponent applicationComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        applicationComponent = DaggerApplicationComponent
                .builder()
                .applicationModule(new ApplicationModule(this))
                .build();
    }

    public ApplicationComponent getApplicationComponent() {
        return applicationComponent;
    }
}
