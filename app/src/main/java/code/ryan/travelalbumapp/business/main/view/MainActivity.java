package code.ryan.travelalbumapp.business.main.view;

import android.content.res.ColorStateList;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.WindowManager;
import android.widget.LinearLayout;

import code.ryan.mvplib.view.BaseActivity;
import code.ryan.travelalbumapp.R;
import code.ryan.travelalbumapp.business.main.presenter.IMainPresenter;
import code.ryan.travelalbumapp.business.main.presenter.MainpresenterImpl;
import code.ryan.utils.DimensionUtil;

public class MainActivity extends BaseActivity<IMainView, IMainPresenter> implements IMainView, NavigationView.OnNavigationItemSelectedListener {
    private Toolbar mToolbar;
    private DrawerLayout mDrawer;               // 抽屉布局
    private NavigationView mNavigationView;     // 抽屉布局打开的菜单栏

    @Override
    public IMainPresenter setPresenter() {
        return new MainpresenterImpl();
    }

    @Override
    public int getResId() {
        return R.layout.activity_main;
    }

    @Override
    public void initActivity(Bundle savedInstanceState) {
        findView();
        initToolbar();
        initDrawer();
        initNavigationMenu();
    }

    private void findView() {
        mToolbar = findViewById(R.id.toolbar);
        mDrawer = findViewById(R.id.drawer_layout);
        mNavigationView = findViewById(R.id.nav_view);
    }

    private void initToolbar() {
        setSupportActionBar(mToolbar);

        // 让背景图片穿透Toolbar和状态栏
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
        // 设置Toolbar的marginTop属性值为状态栏的高度
        LinearLayout.LayoutParams param = (LinearLayout.LayoutParams) mToolbar.getLayoutParams();
        param.setMargins(0, DimensionUtil.getStatusBarHeight(getApplicationContext()), 0, 0);
        mToolbar.setLayoutParams(param);
    }

    private void initDrawer() {
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, mDrawer, mToolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        mDrawer.addDrawerListener(toggle);
        toggle.syncState();
        // 监听NavigationMenu的点击
        mNavigationView.setNavigationItemSelectedListener(this);
    }


    private void initNavigationMenu() {
        ColorStateList csl = getBaseContext().getResources().getColorStateList(R.color.navigation_menu_item_color);

        // 设置NavigationBar文字的颜色
        mNavigationView.setItemTextColor(csl);
        // 设置NavigationBar按钮的颜色
        //mNavigationView.setItemIconTintList(csl);
        mNavigationView.setItemIconTintList(null);

    }

    @Override
    public void onBackPressed() {
        // 处理返回按键事件，关闭抽屉
        if (mDrawer.isDrawerOpen(GravityCompat.START)) {
            mDrawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // 处理Navation菜单栏的点击
        int id = item.getItemId();

        if (id == R.id.nav_my_paper) {

        } else if (id == R.id.nav_popular_paper) {

        } else if (id == R.id.nav_advanced) {

        }

        mDrawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
