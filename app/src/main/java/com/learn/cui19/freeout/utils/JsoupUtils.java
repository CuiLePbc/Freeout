package com.learn.cui19.freeout.utils;

import com.learn.cui19.freeout.model.FreeGoBean;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.Response;


/**
 * Created by cui19 on 2016/11/15.
 */

public class JsoupUtils {
    private static List<Elements> elementsList = new ArrayList<Elements>();
    public static List<FreeGoBean> getFreeGoBeans (String htmlStr) {
        List<FreeGoBean> freeGoBeenList = new ArrayList<FreeGoBean>();

        Document document = Jsoup.parse(htmlStr);
//        Elements elements = document.getElementsByClass("tn-item clearfix");
        Elements elements = document.select("div.tn-item clearfix");
        System.out.println(elements.size());

        return freeGoBeenList;
    }

    public static void main(String[] args){
        try {
            Response response = NetUtil.getResponse("http://www.mafengwo.cn/");
            if (response != null) {
                String html = new String(response.body().bytes(), "utf-8");
                getFreeGoBeans(html);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}


