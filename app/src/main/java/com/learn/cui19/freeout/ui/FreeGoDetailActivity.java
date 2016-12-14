package com.learn.cui19.freeout.ui;

import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.webkit.DownloadListener;
import android.webkit.WebResourceRequest;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;

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

    @BindView(R.id.toolbar_detail)
    Toolbar mToolbar;

    @BindView(R.id.detail_loading_progressbar)
    ProgressBar mProgressBar;

    private String totalUrl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_free_go_detail);
        ButterKnife.bind(this);

        mDetailPresenter = new DetailPresenter(this);

        //设置toolbar
        setSupportActionBar(mToolbar);
        mToolbar.setNavigationIcon(R.drawable.ic_arrow_back_white_24dp);
        mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });


        //或取网页链接
        Intent intent = getIntent();
        String url = intent.getStringExtra(MainActivity.DETAIL_URL);

        //拼装完整链接，先用webview打开
        totalUrl = "http://you.ctrip.com" + url;

        //WebView 设置
        if (Build.VERSION.SDK_INT > Build.VERSION_CODES.KITKAT) {
            mWebView.getSettings().setMixedContentMode(WebSettings.MIXED_CONTENT_ALWAYS_ALLOW);
        }
        mWebView.getSettings().setBlockNetworkImage(false);
        mWebView.loadUrl(totalUrl);
        mWebView.setWebViewClient(new WebViewClient(){
            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
                hideProgressBar();
            }
        });

//        mDetailPresenter.loadData(url);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.free_go_detail, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.options_menu_free_go_detail:
                if (totalUrl != null && !"".equals(totalUrl)) {
                    Intent intent = new Intent(Intent.ACTION_VIEW);
                    intent.setData(Uri.parse(totalUrl));
                    startActivity(intent);
                }
                break;
            default:
                break;
        }
        return true;
    }

    @Override
    public void showProgressBar() {
        if (mProgressBar.getVisibility() != ProgressBar.VISIBLE)
            mProgressBar.setVisibility(ProgressBar.VISIBLE);
    }

    @Override
    public void hideProgressBar() {
        if (mProgressBar.getVisibility() == ProgressBar.VISIBLE)
            mProgressBar.setVisibility(ProgressBar.INVISIBLE);
    }

    @Override
    public void showData(FreeGoDetailBean detailBean) {
        mWebView.loadData(detailBean.getHtml(), "text/html", "utf-8");
    }
}
