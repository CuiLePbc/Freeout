package com.learn.cui19.freeout.network.convert;

import com.learn.cui19.freeout.model.FreeGoBean;

import java.lang.annotation.Annotation;
import java.lang.reflect.Type;
import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Converter;
import retrofit2.Retrofit;

/**
 * Created by cui19 on 2016/11/21.
 */

public class MyFreeGoListConvertFactory extends Converter.Factory {
    public static final MyFreeGoListConvertFactory INSTANCE = new MyFreeGoListConvertFactory();

    public static MyFreeGoListConvertFactory create() {
        return INSTANCE;
    }

    @Override
    public Converter<ResponseBody, ?> responseBodyConverter(Type type, Annotation[] annotations, Retrofit retrofit) {
            return MyFreeGoListConvert.INSTANCE;
    }
}
