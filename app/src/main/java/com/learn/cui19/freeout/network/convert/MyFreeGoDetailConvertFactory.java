package com.learn.cui19.freeout.network.convert;

import java.lang.annotation.Annotation;
import java.lang.reflect.Type;

import okhttp3.ResponseBody;
import retrofit2.Converter;
import retrofit2.Retrofit;

/**
 * Created by cui19 on 2016/11/21.
 */

public class MyFreeGoDetailConvertFactory extends Converter.Factory {
    public static final MyFreeGoDetailConvertFactory INSTANCE = new MyFreeGoDetailConvertFactory();

    public static MyFreeGoDetailConvertFactory create() {
        return INSTANCE;
    }

    @Override
    public Converter<ResponseBody, ?> responseBodyConverter(Type type, Annotation[] annotations, Retrofit retrofit) {
            return MyFreeGoDetailConvert.INSTANCE;
    }
}
