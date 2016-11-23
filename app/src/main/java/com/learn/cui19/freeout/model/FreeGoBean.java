package com.learn.cui19.freeout.model;

/**
 * Created by cui19 on 2016/11/14.
 */

public class FreeGoBean {

    private String mTitle;
    private String mHref;
    private String mInfo;
    private String mImgHref;
    private String mAuthor;
    private String mTips;

    public FreeGoBean() {
    }

    public FreeGoBean(String title, String href, String info, String imgHref, String author,
            String tips) {
        mTitle = title;
        mHref = href;
        mInfo = info;
        mImgHref = imgHref;
        mAuthor = author;
        mTips = tips;
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

    public String getImgHref() {
        return mImgHref;
    }

    public void setImgHref(String imgHref) {
        mImgHref = imgHref;
    }

    public String getAuthor() {
        return mAuthor;
    }

    public void setAuthor(String author) {
        mAuthor = author;
    }

    public String getTips() {
        return mTips;
    }

    public void setTips(String tips) {
        mTips = tips;
    }

    @Override
    public String toString() {
        StringBuffer sb = new StringBuffer();
        sb.append("Title:").append(mTitle)
                .append("\r\nAuthor:").append(mAuthor)
                .append("\r\nInfo:").append(mInfo)
                .append("\r\nTips:").append(mTips)
                .append("\r\nHref:").append(mHref)
                .append("\r\nImagHrefs:")
                .append(mImgHref);
        return sb.toString();
    }
}
