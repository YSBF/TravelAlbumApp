package code.ryan.mvplib.view;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import code.ryan.mvplib.presenter.IBasePrensenter;


public abstract class BaseFragment<V, T extends IBasePrensenter<V>> extends Fragment {
    public T mPresenter;

    public BaseFragment() {
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(getResId(), container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        //initPresenter()是抽象方法，让view初始化自己的presenter
        mPresenter = setPresenter();
        //presenter和view的绑定
        mPresenter.attachView((V) this);
        //初始化Fragment
        initFragment(view, savedInstanceState);
    }

    @Override
    public void onResume() {
        if (mPresenter != null) {
            mPresenter.resumeView();
        }
        super.onResume();
    }

    @Override
    public void onDetach() {
        if (mPresenter != null) {
            mPresenter.dettachView();
        }
        super.onDetach();
    }

    /**
     * 显示Toast
     *
     * @param text
     * @param isLengthShort
     */
    public void toastText(String text, boolean isLengthShort) {
        if (isLengthShort) {
            Toast.makeText(getActivity(), text, Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(getActivity(), text, Toast.LENGTH_LONG).show();
        }
    }


    public abstract T setPresenter();

    public abstract int getResId();

    public abstract void initFragment(View view, Bundle savedInstanceState);

}
