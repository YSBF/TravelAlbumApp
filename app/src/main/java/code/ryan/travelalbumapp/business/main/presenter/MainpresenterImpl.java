package code.ryan.travelalbumapp.business.main.presenter;

import code.ryan.mvplib.presenter.BasePresenterImpl;
import code.ryan.travelalbumapp.business.main.model.IMainProxy;
import code.ryan.travelalbumapp.business.main.model.MainProxyImpl;
import code.ryan.travelalbumapp.business.main.view.IMainView;

/**
 * Created by RyanLee on 2017/11/29.
 */

public class MainpresenterImpl extends BasePresenterImpl<IMainView, IMainProxy> implements IMainPresenter{
    @Override
    public IMainProxy setmProxy() {
        return new MainProxyImpl(this);
    }
}
