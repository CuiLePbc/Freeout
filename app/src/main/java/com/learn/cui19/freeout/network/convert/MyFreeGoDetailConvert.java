package com.learn.cui19.freeout.network.convert;

import com.learn.cui19.freeout.model.FreeGoDetailBean;

import java.io.IOException;

import okhttp3.ResponseBody;
import retrofit2.Converter;

/**
 * Created by cui19 on 2016/11/21.
 */

public class MyFreeGoDetailConvert implements Converter<ResponseBody, FreeGoDetailBean> {
    public static final MyFreeGoDetailConvert INSTANCE = new MyFreeGoDetailConvert();
    @Override
    public FreeGoDetailBean convert(ResponseBody value) throws IOException {
        String html = new String(value.bytes(), "utf-8");
        return new FreeGoDetailBean(html);
    }
}
