package com.learn.cui19.freeout.dgr.component;

import android.app.Application;
import android.content.Context;

import com.learn.cui19.freeout.MyApplication;
import com.learn.cui19.freeout.dgr.module.ApplicationModule;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by cui19 on 2016/11/30.
 */
@Singleton
@Component(modules = ApplicationModule.class)
public interface ApplicationComponent {
    Context getApplicationContext();
}
