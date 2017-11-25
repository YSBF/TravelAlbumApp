package code.ryan.mvplib.model;


import code.ryan.mvplib.presenter.IBasePrensenter;

/**
 * Created by RyanLee on 2017/11/18.
 */

public abstract class BaseProxyImpl<T extends IBasePrensenter> {
    public T mPresenter;

    public BaseProxyImpl(T mPresenter) {
        this.mPresenter = mPresenter;
    }
}
