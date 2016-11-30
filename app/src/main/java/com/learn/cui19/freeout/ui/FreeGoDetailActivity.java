package com.learn.cui19.freeout.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.webkit.WebResourceRequest;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.learn.cui19.freeout.R;
import com.learn.cui19.freeout.model.FreeGoDetailBean;
import com.learn.cui19.freeout.presenter.DetailPresenter;
import com.learn.cui19.freeout.view.DetailView;

import butterknife.BindView;
import butterknife.ButterKnife;

public class FreeGoDetailActivity extends AppCompatActivity implements DetailView{
    private DetailPresenter mDetailPresenter;

    @BindView(R.id.webview_detail)
    WebView mWebView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_free_go_detail);
        ButterKnife.bind(this);

        mDetailPresenter = new DetailPresenter(this);

        Intent intent = getIntent();
        String url = intent.getStringExtra(MainActivity.DETAIL_URL);

        //拼装完整链接，先用webview打开
        final String totalUrl = "http://you.ctrip.com" + url;
        System.out.println(totalUrl);

        mWebView.loadUrl(totalUrl);
        mWebView.setWebViewClient(new WebViewClient(){
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {
                view.loadUrl(totalUrl);
                return true;
            }
        });

        mDetailPresenter.loadData(url);
    }

    @Override
    public void showProgressBar() {

    }

    @Override
    public void hideProgressBar() {

    }

    @Override
    public void showData(FreeGoDetailBean detailBean) {

    }
}
