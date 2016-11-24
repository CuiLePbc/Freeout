package com.learn.cui19.freeout.utils;


import android.util.Log;

import com.learn.cui19.freeout.model.FreeGoBean;
import com.learn.cui19.freeout.model.FreeGoModel;
import com.learn.cui19.freeout.network.api.FreeGoBeanApi;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import okhttp3.ResponseBody;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import rx.Observable;
import rx.Observer;
import rx.Subscriber;
import rx.functions.Action1;
import rx.functions.Func0;
import rx.functions.Func1;
import rx.subjects.AsyncSubject;
import rx.subjects.BehaviorSubject;
import rx.subjects.PublishSubject;

/**
 * Created by cui19 on 2016/11/18.
 */

public class RxjavaTest {
    public static void main(String[] args) {
    }
}
