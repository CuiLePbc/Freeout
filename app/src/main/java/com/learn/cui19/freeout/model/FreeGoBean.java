package com.learn.cui19.freeout.model;

/**
 * Created by cui19 on 2016/11/14.
 */

public class FreeGoBean{

    private String mTitle;
    private String mHref;
    private String mInfo;
    private String[] mImgHref;

    public FreeGoBean(){}

    public FreeGoBean(String title, String href, String info, String[] imgHref) {
        mTitle = title;
        mHref = href;
        mInfo = info;
        mImgHref = imgHref;
    }

    public String getTitle() {
        return mTitle;
    }

    public void setTitle(String title) {
        mTitle = title;
    }

    public String getHref() {
        return mHref;
    }

    public void setHref(String href) {
        mHref = href;
    }

    public String getInfo() {
        return mInfo;
    }

    public void setInfo(String info) {
        mInfo = info;
    }

    public String[] getImgHref() {
        return mImgHref;
    }

    public void setImgHref(String[] imgHref) {
        mImgHref = imgHref;
    }

    @Override
    public String toString() {
        StringBuffer sb = new StringBuffer();
        sb.append("Title:").append(mTitle).append("\r\nInfo:").append(mInfo).append("\r\nHref:").append(mHref);
        sb.append("\r\nImagHrefs:");
        for (int i = 0; i < mImgHref.length; i++) {
            sb.append(mImgHref[i]).append(" ");
        }
        return sb.toString();
    }
}
