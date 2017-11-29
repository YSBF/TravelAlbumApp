package code.ryan.travelalbumapp.business.main.model;

import code.ryan.mvplib.model.BaseProxyImpl;
import code.ryan.mvplib.presenter.IBasePrensenter;

/**
 * Created by RyanLee on 2017/11/29.
 */

public class MainProxyImpl extends BaseProxyImpl<IBasePrensenter> implements IMainProxy {

    public MainProxyImpl(IBasePrensenter mPresenter) {
        super(mPresenter);
    }
}
