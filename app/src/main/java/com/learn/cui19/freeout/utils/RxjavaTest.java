package com.learn.cui19.freeout.utils;


import com.learn.cui19.freeout.model.FreeGoBean;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

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
//        Observable<String> sender = Observable.create(new Observable.OnSubscribe<String>() {
//            @Override
//            public void call(Subscriber<? super String> subscriber) {
//                subscriber.onNext("Hi RxJava!");
//                subscriber.onNext("2- Hi Rxjava!");
//            }
//        });
//
//        Observable<String> senderJust = Observable.just("just1", "just2", "just3");
//
//        Observable<String> senderFrom = Observable.from(new String[]{"from1", "from2", "from3"});
//
//        Observable<String> senderDefer = Observable.defer(new Func0<Observable<String>>() {
//            @Override
//            public Observable<String> call() {
//                return Observable.just("defer1").repeat(5);
//            }
//        });
//
//        Observable<Integer> observableRange = Observable.range(12, 5);
//
//        Observable observableInterval = Observable.interval(1, TimeUnit.SECONDS);
//
//        Observable observableTimer = Observable.timer(2, TimeUnit.SECONDS);
//
//        Observer<String> receiver = new Observer<String>() {
//            @Override
//            public void onCompleted() {
//                System.out.println("Completed");
//            }
//
//            @Override
//            public void onError(Throwable e) {
//
//            }
//
//            @Override
//            public void onNext(String s) {
//                System.out.println(s + "observer");
//            }
//        };
//        sender.subscribe(receiver);
//        System.out.println("=================");
//        senderJust.subscribe(receiver);
//        System.out.println("=================");
//        senderFrom.subscribe(receiver);
//        System.out.println("=================");
//        senderDefer.subscribe(receiver);
//        System.out.println("=================");
//        observableRange.subscribe(new Action1<Integer>() {
//            @Override
//            public void call(Integer integer) {
//                System.out.println(integer);
//            }
//        });
//        System.out.println("=================");
//        System.out.println("=================");
//        observableTimer.subscribe(new Action1() {
//            @Override
//            public void call(Object o) {
//                System.out.println("timer");
//            }
//        });
//
//        System.out.println("==================================");

//        Observable<String> observable = Observable.just("hello").repeat(3);
//        observable.subscribe(new Action1<String>() {
//            @Override
//            public void call(String s) {
//                System.out.println(s);
//            }
//        });
//
//        AsyncSubject<String> asyncSubject = AsyncSubject.create();
//        asyncSubject.onNext("asyncSubject1");
//        asyncSubject.onNext("asyncSubject2");
//        asyncSubject.onNext("asyncSubject3");
//        asyncSubject.onCompleted();
//        asyncSubject.subscribe(new Action1<String>() {
//            @Override
//            public void call(String s) {
//                System.out.println(s);
//            }
//        });
//
//        BehaviorSubject<String> behaviorSubject = BehaviorSubject.create();
////        behaviorSubject.onNext("behaviorSubject1");
////        behaviorSubject.onNext("behaviorSubject2");
////        behaviorSubject.onNext("behaviorSubject3");
//        behaviorSubject.subscribe(new Subscriber<String>() {
//            @Override
//            public void onCompleted() {
//                System.out.println("Completed");
//            }
//
//            @Override
//            public void onError(Throwable e) {
//
//            }
//
//            @Override
//            public void onNext(String s) {
//                System.out.println(s);
//            }
//        });
//        behaviorSubject.onNext("behaviorSubject4");
//        behaviorSubject.onNext("behaviorSubject5");
//        Observable<String> observable = Observable.create(new Observable.OnSubscribe<String>() {
//            @Override
//            public void call(Subscriber<? super String> subscriber) {
//                subscriber.onNext("i am Observable");
//                subscriber.onCompleted();
//            }
//        });
//        PublishSubject<String> publishSubject = PublishSubject.create();
//        Observable.create(new Observable.OnSubscribe<String>() {
//            @Override
//            public void call(Subscriber<? super String> subscriber) {
//                subscriber.onNext("as bridge");
//                subscriber.onCompleted();
//            }
//        }).subscribe(publishSubject);
//
//        publishSubject.subscribe(new Subscriber<String>() {
//            @Override
//            public void onCompleted() {
//                System.out.println("publishSubject compeleted");
//            }
//
//            @Override
//            public void onError(Throwable e) {
//
//            }
//
//            @Override
//            public void onNext(String s) {
//                System.out.println(s);
//            }
//        });
        List<FreeGoBean> list = new ArrayList<FreeGoBean>();
        for (int i = 0; i < 5; i++) {
            String[] imgs = new String[]{"img" + i + "-1", "img" + i + "-2", "img" + i + "-3"};
            FreeGoBean freeGoBean = new FreeGoBean("title" + i,"href" + i, "info" + i, imgs);
            list.add(freeGoBean);
        }

        Observable.from(list).map(new Func1<FreeGoBean, String>() {
            @Override
            public String call(FreeGoBean freeGoBean) {
                return freeGoBean.getTitle();
            }
        }).subscribe(new Action1<String>() {
            @Override
            public void call(String s) {
                System.out.println(s);
            }
        });
        System.out.println("==================================");
        Observable.from(list).flatMap(new Func1<FreeGoBean, Observable<String>>() {
            @Override
            public Observable<String> call(FreeGoBean freeGoBean) {

                return Observable.from(freeGoBean.getImgHref());
            }
        }).subscribe(new Action1<String>() {
            @Override
            public void call(String s) {
                System.out.println(s);
            }
        });
        System.out.println("==================================");

        Observable.from(list)
                .map(new Func1<FreeGoBean, FreeGoBean>() {
                    @Override
                    public FreeGoBean call(FreeGoBean freeGoBean) {
                        freeGoBean.setTitle("TTTTT");
                        return freeGoBean;
                    }
                })
                .buffer(list.size())
                .subscribe(new Action1<List<FreeGoBean>>() {
                    @Override
                    public void call(List<FreeGoBean> lists) {
                        System.out.println(lists.size());
                    }
        });
        System.out.println("====================================================================");
        Observable.range(10, 6)
                .map(new Func1<Integer, FreeGoBean>() {
                    @Override
                    public FreeGoBean call(Integer i) {
                        String[] imgs = new String[]{"img" + i + "-1", "img" + i + "-2", "img" + i + "-3"};
                        FreeGoBean freeGoBean = new FreeGoBean("title" + i,"href" + i, "info" + i, imgs);
                        return freeGoBean;
                    }
                })
                .flatMap(new Func1<FreeGoBean, Observable<String>>() {
                    @Override
                    public Observable<String> call(FreeGoBean freeGoBean) {
                        return Observable.from(freeGoBean.getImgHref());
                    }
                })
                .subscribe(new Action1<String>() {
                    @Override
                    public void call(String s) {
                        System.out.println(s);
                    }
                });

    }
}
