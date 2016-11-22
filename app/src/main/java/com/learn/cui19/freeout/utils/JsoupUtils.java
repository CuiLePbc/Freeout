package com.learn.cui19.freeout.utils;

import com.learn.cui19.freeout.model.FreeGoBean;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by cui19 on 2016/11/15.
 * 使用Json来爬得网上自由行攻略
 */

public class JsoupUtils {

    /**
     * 从蚂蜂网旅游攻略页面获取内容
     *
     * @param html 蚂蜂窝旅游攻略页面内容。
     * @return 攻略信息列表
     */
    public static List<FreeGoBean> getFreeGoBeansFromMaFen(String html) {
        List<FreeGoBean> freeGoBeenList = new ArrayList<FreeGoBean>();

        Document document = Jsoup.parse(html);
        Elements elements = document.select("[class$=_j_feed_item]");
        for (Element element : elements) {
            FreeGoBean fgb = new FreeGoBean();

            //获得链接
            Elements hrefs = element.select("a");
            if (hrefs.size() > 0) {
                fgb.setHref(hrefs.get(0).attr("href"));
            }

            //获得标题
            Elements titles = element.select(".title");
            if (titles.size() > 0) {
                fgb.setTitle(titles.get(0).text());
            }

            //获得文字描述
            Elements infos = element.select(".info");
            if (infos.size() > 0) {
                fgb.setInfo(infos.get(0).text());
            }

            //获得图片地址
            Elements imgs = element.select("img");
            if (imgs.size() > 0) {
                String[] imgHrefs = new String[imgs.size()];
                for (int i = 0; i < imgs.size(); i++) {
                    imgHrefs[i] = imgs.get(i).attr("src");
                }
                fgb.setImgHref(imgHrefs);
            }

            freeGoBeenList.add(fgb);
        }
        return freeGoBeenList;
    }

    /**
     * 从携程网旅游攻略页面获取内容
     *
     * @param html 携程旅游攻略页面内容。
     * @return 攻略信息列表
     */
    public static List<FreeGoBean> getFreeGoBeansFromXieChen(String html) {
        List<FreeGoBean> freeGoBeenList = new ArrayList<FreeGoBean>();

        Document document = Jsoup.parse(html);
        Elements elements = document.select("[$class=journal-item]");
        for (Element element : elements) {
        }
        return freeGoBeenList;
    }

}


