package code.ryan.travelalbumapp.business.main.view;

import android.content.res.ColorStateList;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.TransitionDrawable;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.WindowManager;
import android.widget.LinearLayout;

import com.ryan.rv_gallery.AnimManager;
import com.ryan.rv_gallery.GalleryRecyclerView;

import java.util.HashMap;
import java.util.Map;

import code.blur.BlurBitmapUtil;
import code.ryan.mvplib.view.BaseActivity;
import code.ryan.travelalbumapp.R;
import code.ryan.travelalbumapp.business.main.MainGalleryAdapter;
import code.ryan.travelalbumapp.business.main.presenter.IMainPresenter;
import code.ryan.travelalbumapp.business.main.presenter.MainpresenterImpl;
import code.ryan.travelalbumapp.test.MainTest;
import code.screen.DimensionUtil;

public class MainActivity extends BaseActivity<IMainView, IMainPresenter> implements IMainView, NavigationView.OnNavigationItemSelectedListener {
    private Toolbar mToolbar;
    private DrawerLayout mDrawer;               // 抽屉布局
    private NavigationView mNavigationView;     // 抽屉布局打开的菜单栏
    private GalleryRecyclerView mGallery;
    private Map<String, Drawable> mTSDraCacheMap = new HashMap<>();
    private static final String KEY_PRE_DRAW = "key_pre_draw";
    private LinearLayout mContainer;

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
        setUpGallery();
    }

    private void setUpGallery() {
        MainGalleryAdapter adapter = new MainGalleryAdapter(getApplicationContext(), MainTest.getDatas(this));
        mGallery.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        mGallery.setAdapter(adapter);

        mGallery.initFlingSpeed(5000)                                   // 设置滑动速度（像素/s）
                .initPageParams(10, 40)     // 设置页边距和左右图片的可见宽度，单位dp
                .setAnimFactor(0f)                                   // 设置切换动画的参数因子
                .setAnimType(AnimManager.ANIM_BOTTOM_TO_TOP);           // 设置切换动画类型，目前有AnimManager.ANIM_BOTTOM_TO_TOP和目前有AnimManager.ANIM_TOP_TO_BOTTOM

        // 背景高斯模糊 & 淡入淡出
        mGallery.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);

                if (newState == RecyclerView.SCROLL_STATE_IDLE) {
                    setBlurImage();
                }
            }
        });
        setBlurImage();
    }

    private void findView() {
        mToolbar = findViewById(R.id.toolbar_main);
        mDrawer = findViewById(R.id.drawer_layout);
        mNavigationView = findViewById(R.id.nav_view);
        mGallery = findViewById(R.id.rv_main_gallery);
        mContainer = findViewById(R.id.ll_main_container);
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

    /**
     * 设置背景高斯模糊
     */
    public void setBlurImage() {
        MainGalleryAdapter adapter = (MainGalleryAdapter) mGallery.getAdapter();

        if (adapter == null || mGallery == null) {
            return;
        }
        mGallery.post(new Runnable() {
            @Override
            public void run() {
                // 获取当前位置的图片资源ID
                int resourceId = ((MainGalleryAdapter) mGallery.getAdapter()).getResId(mGallery.getScrolledPosition());
                // 将该资源图片转为Bitmap
                Bitmap resBmp = BitmapFactory.decodeResource(getResources(), resourceId);
                // 将该Bitmap高斯模糊后返回到resBlurBmp
                Bitmap resBlurBmp = BlurBitmapUtil.blurBitmap(mGallery.getContext(), resBmp, 25f);
                // 再将resBlurBmp转为Drawable
                Drawable resBlurDrawable = new BitmapDrawable(resBlurBmp);
                // 获取前一页的Drawable
                Drawable preBlurDrawable = mTSDraCacheMap.get(KEY_PRE_DRAW) == null ? resBlurDrawable : mTSDraCacheMap.get(KEY_PRE_DRAW);

                /* 以下为淡入淡出效果 */
                Drawable[] drawableArr = {preBlurDrawable, resBlurDrawable};
                TransitionDrawable transitionDrawable = new TransitionDrawable(drawableArr);
                mContainer.setBackgroundDrawable(transitionDrawable);
                transitionDrawable.startTransition(500);

                // 存入到cache中
                mTSDraCacheMap.put(KEY_PRE_DRAW, resBlurDrawable);
            }
        });
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
