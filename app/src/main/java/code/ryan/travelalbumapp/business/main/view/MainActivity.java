package code.ryan.travelalbumapp.business.main.view;

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
    private DrawerLayout mDrawer;
    private NavigationView mNavigationView;     // View of DrawerLayout's left.

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
    }

    private void initDrawer() {
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, mDrawer, mToolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        mDrawer.addDrawerListener(toggle);
        toggle.syncState();
        // Monitor the NavigationView open or close.
        mNavigationView.setNavigationItemSelectedListener(this);
    }

    private void findView() {
        mToolbar = findViewById(R.id.toolbar);
        mDrawer = findViewById(R.id.drawer_layout);
        mNavigationView = findViewById(R.id.nav_view);
    }

    private void initToolbar() {
        setSupportActionBar(mToolbar);

        // Makes the status bar be able to show the background photo.
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
        // Set the toolbar topMargin is statusBar's height.
        LinearLayout.LayoutParams param = (LinearLayout.LayoutParams) mToolbar.getLayoutParams();
        param.setMargins(0, DimensionUtil.getStatusBarHeight(getApplicationContext()), 0, 0);
        mToolbar.setLayoutParams(param);
    }


    @Override
    public void onBackPressed() {
        // Handle back key pressed
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_camera) {
            // Handle the camera action
        } else if (id == R.id.nav_gallery) {

        } else if (id == R.id.nav_slideshow) {

        } else if (id == R.id.nav_manage) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
