package com.learn.cui19.freeout.ui;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
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

import com.learn.cui19.freeout.MyApplication;
import com.learn.cui19.freeout.R;
import com.learn.cui19.freeout.dgr.component.DaggerMainComponent;
import com.learn.cui19.freeout.dgr.module.MainModule;
import com.learn.cui19.freeout.model.FreeGoBean;
import com.learn.cui19.freeout.presenter.MainPresenter;
import com.learn.cui19.freeout.ui.adapter.MyMainContentAdapter;
import com.learn.cui19.freeout.jsoup.JsoupContact;
import com.learn.cui19.freeout.view.MainView;
import com.lljjcoder.citypickerview.widget.CityPicker;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener, MainView {
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

    @Inject
    MainPresenter mMainPresenter;
    @Inject
    MyMainContentAdapter myMainContentAdapter;

    ImageView leftMenuPersonHeadIV;

    /* 当前加载页数 */
    private int page;

    /* 三级地址：0是洲 1是区域或国家 2是城市 3是对应地址字符 */
    private String[] city;

    private boolean loading = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        DaggerMainComponent
                .builder()
                .applicationComponent(((MyApplication)getApplication()).getApplicationComponent())
                .mainModule(new MainModule(this))
                .build()
                .inject(this);

        page = 1;

        //初始化为上海
        city = new String[]{"国内", "华东", "上海", JsoupContact.SHANGHAI};

        initView();

        changeCity(city);
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
                // TODO: 2016/11/30 跳转到用户信息界面，若未登陆，跳转至登陆界面
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
                changeCity(city);
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
                if ((!loading) && totalItemCount > 1 && totalItemCount < (lastVisibleItem
                        + VISIBLE_THRESHOLD)) {
                    loading = true;
                    page += 1;
                    mMainPresenter.addData(page, city[3]);
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

            CityPicker cityPicker = new CityPicker.Builder(this)
                    .textSize(20)
                    .itemPadding(12)
                    .visibleItemsCount(7)
                    .province(city[0])
                    .city(city[1])
                    .district(city[2])
                    .title("城市选择")
                    .titleBackgroundColor("#234Dfa")
                    .build();
            cityPicker.show();
            cityPicker.setOnCityItemClickListener(new CityPicker.OnCityItemClickListener() {
                @Override
                public void onSelected(String... citySelected) {

                    if (citySelected.length >= 3 && null != citySelected[3]
                            && (!"".equals(citySelected[3]))) {
                        city = new String[]{citySelected[0], citySelected[1], citySelected[2],
                                citySelected[3]};
                        changeCity(city);
                    }

                }
            });
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.nav_lanmuyi) {

        } else if (id == R.id.nav_lanmuer) {

        } else if (id == R.id.nav_lanmusan) {

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
     * @param city 城市,四项的数组
     */
    private void changeCity(String[] city) {
        getSupportActionBar().setTitle(city[2]);
        page = 1;
        if (myMainContentAdapter != null && myMainContentAdapter.getItemCount() > 1) {
            myMainContentAdapter.clearDatas();
        }
        mMainPresenter.loadData(city[3]);
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
        // 将获得的freeGoBeans数据显示到主界面上。
        myMainContentAdapter.setList(freeGoBeans);
    }

    @Override
    public void addData(List<FreeGoBean> freeGoBeens) {
        loading = false;
        myMainContentAdapter.addList(freeGoBeens);
    }
}
