package com.learn.cui19.freeout.dgr.component;

import com.learn.cui19.freeout.dgr.module.MainModule;
import com.learn.cui19.freeout.dgr.scope.PerActivity;
import com.learn.cui19.freeout.ui.MainActivity;

import dagger.Component;

/**
 * Created by cui19 on 2016/11/30.
 */
@PerActivity
@Component(modules = MainModule.class, dependencies = ApplicationComponent.class)
public interface MainComponent {
    void inject(MainActivity mainActivity);
}
