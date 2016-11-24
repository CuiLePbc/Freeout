package com.learn.cui19.freeout.ui;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;

import com.learn.cui19.freeout.R;
import com.learn.cui19.freeout.model.FreeGoBean;
import com.learn.cui19.freeout.presenter.MainPresenter;
import com.learn.cui19.freeout.ui.adapter.MyMainContentAdapter;
import com.learn.cui19.freeout.utils.JsoupContact;
import com.learn.cui19.freeout.view.MainView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener, MainView {
    private MainPresenter mMainPresenter;
    private MyMainContentAdapter myMainContentAdapter;

    /* 传给详情页面的url的key */
    public static final String DETAIL_URL = "detail_url";

    /* 将要刷新的剩余条数 */
    public static final int VISIBLE_THRESHOLD = 2;

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
    @BindView(R.id.content_main_swipe_refresh_layout)
    SwipeRefreshLayout mSwipeRefreshLayout;


    ImageView leftMenuPersonHeadIV;

    /* 当前栏目序号 */
    private int currentLanmu;

    /* 当前加载页数 */
    private int page;
    private String city;

    private boolean loading = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        mMainPresenter = new MainPresenter(this);
        page = 1;
        city = JsoupContact.GUANGZHOU;

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
        initListener();
    }

    /**
     * 初始化界面上一些控件的点击事件
     */
    private void initListener() {
        //设置浮动按钮点击
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        //用户头像点击
        leftMenuPersonHeadIV = (ImageView) navigationView.getHeaderView(0).findViewById(
                R.id.img_view_main_left_menu);
        leftMenuPersonHeadIV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Snackbar.make(getCurrentFocus(), "login page or person page",
                        Snackbar.LENGTH_LONG).show();
            }
        });

        //列表单击事件
        myMainContentAdapter.setOnItemClickListener(new MyMainContentAdapter.MyItemClickListener() {
            @Override
            public void onItemClick(View v, int position) {
                Log.i("MainActivity", position + "");
                FreeGoBean fgb = myMainContentAdapter.getItemData(position);
                Intent intent = new Intent(MainActivity.this, FreeGoDetailActivity.class);
                intent.putExtra(DETAIL_URL, fgb.getHref());
                startActivity(intent);
            }
        });

        //下拉刷新
        mSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                page = 1;
                myMainContentAdapter.clearDatas();
                mMainPresenter.loadData(city);
            }
        });

        //滑动监听，用以上拉加载更多
        mainRecycleView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                LinearLayoutManager layoutManager =
                        (LinearLayoutManager) mainRecycleView.getLayoutManager();
                int totalItemCount = layoutManager.getItemCount();
                int lastVisibleItem = layoutManager.findLastVisibleItemPosition();
                if ((!loading) && totalItemCount > 1 && totalItemCount < (lastVisibleItem + VISIBLE_THRESHOLD)) {
                    loading = true;
                    page += 1;
                    mMainPresenter.addData(page, city);
                }

            }
        });
    }

    /**
     * 初始化主界面显示内容
     * 即初始化RecyclerView
     */
    private void initMainContent() {
        //初始化下拉刷新颜色
        mSwipeRefreshLayout.setColorSchemeColors(Color.BLUE);

        //初始化列表
        mainRecycleView.setHasFixedSize(true);
        mainRecycleView.setLayoutManager(new LinearLayoutManager(this));
        myMainContentAdapter = new MyMainContentAdapter(new ArrayList<FreeGoBean>(), this);
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
                this, drawer, toolbar, R.string.navigation_drawer_open,
                R.string.navigation_drawer_close);
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
            myMainContentAdapter.clearDatas();
            page = 1;
            mMainPresenter.loadData(city);
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
     *
     * @param lanmuNum   栏目序号
     * @param lanmuTitle 栏目标题
     */
    private void chooseLanmu(int lanmuNum, String lanmuTitle) {
        currentLanmu = lanmuNum;
        getSupportActionBar().setTitle(lanmuTitle);
        page = 1;
        if (myMainContentAdapter != null && myMainContentAdapter.getItemCount() > 1) {
            myMainContentAdapter.clearDatas();
        }
        mMainPresenter.loadData(city);
    }

    @Override
    public void showProgressBar() {
        // TODO: 2016/11/15 显示加载数据时候的进度条 
    }

    @Override
    public void hideProgressBar() {
        // TODO: 2016/11/15 隐藏或取消加载数据时候的进度条
        // 下拉刷新的进度条
        if (mSwipeRefreshLayout != null) {
            mSwipeRefreshLayout.setRefreshing(false);
        }
    }

    @Override
    public void showData(List<FreeGoBean> freeGoBeans) {
        // TODO: 2016/11/15 将获得的freeGoBeans数据显示到主界面上, 目前freeGoBeans已经获得。
        Snackbar.make(getCurrentFocus(), freeGoBeans.size() + "", Snackbar.LENGTH_LONG).show();

        myMainContentAdapter.setList(freeGoBeans);
    }

    @Override
    public void addData(List<FreeGoBean> freeGoBeens) {
        loading = false;
        myMainContentAdapter.addList(freeGoBeens);
    }
}
