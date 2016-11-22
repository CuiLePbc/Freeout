package com.learn.cui19.freeout.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.learn.cui19.freeout.R;
import com.learn.cui19.freeout.model.FreeGoDetailBean;
import com.learn.cui19.freeout.presenter.DetailPresenter;
import com.learn.cui19.freeout.view.DetailView;

import butterknife.BindView;
import butterknife.ButterKnife;

public class FreeGoDetailActivity extends AppCompatActivity implements DetailView{
    private DetailPresenter mDetailPresenter;

    @BindView(R.id.textView2)
    TextView mTextView2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_free_go_detail);
        ButterKnife.bind(this);

        mDetailPresenter = new DetailPresenter(this);

        Intent intent = getIntent();
        String url = intent.getStringExtra(MainActivity.DETAIL_URL);

        mTextView2.setText(url);

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
