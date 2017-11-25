package code.ryan.mvplib.view;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import code.ryan.mvplib.presenter.IBasePrensenter;


/**
 * Created by RyanLee on 2017/11/18.
 */

public abstract class BaseActivity<V, T extends IBasePrensenter<V>> extends AppCompatActivity {
    public T mPresenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //绑定布局ID
        setContentView(getResId());

        //initPresenter()是抽象方法，让view初始化自己的presenter
        mPresenter = setPresenter();

        //presenter和view的绑定
        mPresenter.attachView((V) this);

        //initActtivity是抽象方法，让view完成自身各种控件的初始化
        initActivity(savedInstanceState);
    }



    @Override
    protected void onResume() {
        if (mPresenter != null) {
            mPresenter.resumeView();
        }
        super.onResume();
    }


    @Override
    protected void onDestroy() {
        if (mPresenter != null) {
            mPresenter.dettachView();
        }
        super.onDestroy();
    }

    /**
     * 显示Toast
     * @param text
     * @param isLengthShort
     */
    public void toastText(String text, boolean isLengthShort) {
        if (isLengthShort) {
            Toast.makeText(getApplicationContext(), text, Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(getApplicationContext(), text, Toast.LENGTH_LONG).show();
        }
    }

    public abstract T setPresenter();

    public abstract int getResId();

    public abstract void initActivity(Bundle savedInstanceState);
}
