package com.learn.cui19.freeout.network.convert;

import com.learn.cui19.freeout.model.FreeGoBean;
import com.learn.cui19.freeout.model.FreeGoListBean;
import com.learn.cui19.freeout.jsoup.JsoupUtils;

import java.io.IOException;
import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Converter;

/**
 * Created by cui19 on 2016/11/21.
 */

public class MyFreeGoListConvert implements Converter<ResponseBody, FreeGoListBean> {
    public static final MyFreeGoListConvert INSTANCE = new MyFreeGoListConvert();
    @Override
    public FreeGoListBean convert(ResponseBody value) throws IOException {

        String html = new String(value.bytes(), "utf-8");
        List<FreeGoBean> list = JsoupUtils.getFreeGoBeansFromXieChen(html);

        return new FreeGoListBean(list);
    }
}
