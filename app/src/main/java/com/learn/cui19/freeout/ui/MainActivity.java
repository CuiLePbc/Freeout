package com.learn.cui19.freeout.ui;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.learn.cui19.freeout.R;
import com.learn.cui19.freeout.model.FreeGoBean;
import com.learn.cui19.freeout.presenter.MainPresenter;
import com.learn.cui19.freeout.ui.adapter.MyMainContentAdapter;
import com.learn.cui19.freeout.view.MainView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener, MainView {
    private MainPresenter mMainPresenter;
    private MyMainContentAdapter myMainContentAdapter;
    private List<FreeGoBean> list;

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.fab)
    FloatingActionButton fab;
    @BindView(R.id.drawer_layout)
    DrawerLayout drawer;
    @BindView(R.id.nav_view)
    NavigationView navigationView;
    @BindView(R.id.content_main)
    RecyclerView mainRecycleView;

    /* 当前栏目序号 */
    private int currentLanmu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        mMainPresenter = new MainPresenter(this);

        initView();
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    /**
     * 初始化控件
     */
    private void initView() {
        initToolbarAndLeftMenu();

        initMainContent();

        //设置浮动按钮点击
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
    }

    /**
     * 初始化主界面显示内容
     * 即初始化RecyclerView
     */
    private void initMainContent() {
        mainRecycleView.setHasFixedSize(true);

        mainRecycleView.setLayoutManager(new LinearLayoutManager(this));

        list = new ArrayList<FreeGoBean>();
        myMainContentAdapter = new MyMainContentAdapter(list);
        mainRecycleView.setAdapter(myMainContentAdapter);
    }

    /**
     * 初始化toolbar和侧滑菜单
     */
    private void initToolbarAndLeftMenu() {
        //设置toolbar
        setSupportActionBar(toolbar);

        //设置侧滑抽屉菜单展示效果
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        //设置侧滑抽屉菜单点击效果
        navigationView.setNavigationItemSelectedListener(this);

        //默认选中栏目一
        navigationView.getMenu().getItem(0).setChecked(true);
        chooseLanmu(1, navigationView.getMenu().getItem(0).getTitle().toString());
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        //刷新当前页面，即重新加载数据
        if (id == R.id.action_refresh) {
            mMainPresenter.loadData(currentLanmu);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.nav_lanmuyi) {
            chooseLanmu(1, item.getTitle().toString());
        } else if (id == R.id.nav_lanmuer) {
            chooseLanmu(2, item.getTitle().toString());
        } else if (id == R.id.nav_lanmusan) {
            chooseLanmu(3, item.getTitle().toString());
        } else if (id == R.id.nav_manage) {
            // TODO: 2016/11/15 跳转到设置界面 
        } else if (id == R.id.nav_info) {
            // TODO: 2016/11/15 跳转到关于界面 
        } else if (id == R.id.nav_send) {
            // TODO: 2016/11/15 跳转到发送邮件界面或者联系方式界面 
        }

        //选择某项菜单项之后，关闭侧拉菜单
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    /**
     * 选中栏目
     * @param lanmuNum 栏目序号
     * @param lanmuTitle 栏目标题
     */
    private void chooseLanmu(int lanmuNum, String lanmuTitle) {
        currentLanmu = lanmuNum;
        getSupportActionBar().setTitle(lanmuTitle);
        mMainPresenter.loadData(currentLanmu);
    }

    @Override
    public void showProgressBar() {
        // TODO: 2016/11/15 显示加载数据时候的进度条 
    }

    @Override
    public void hideProgressBar() {
        // TODO: 2016/11/15 隐藏或取消加载数据时候的进度条 
    }

    @Override
    public void showData(List<FreeGoBean> freeGoBeans) {
        // TODO: 2016/11/15 将获得的freeGoBeans数据显示到主界面上, 目前freeGoBeans已经获得。
        Snackbar.make(getCurrentFocus(),freeGoBeans.size() + "", Snackbar.LENGTH_LONG).show();
        myMainContentAdapter.setList(freeGoBeans);
        myMainContentAdapter.notifyDataSetChanged();
    }
}
