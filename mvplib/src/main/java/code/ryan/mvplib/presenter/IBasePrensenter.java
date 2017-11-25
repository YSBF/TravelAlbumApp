package code.ryan.mvplib.presenter;

/**
 * Created by RyanLee on 2017/11/18.
 */

public interface IBasePrensenter<T> {

    //绑定view
    void attachView(T mView);

    void resumeView();

    //解绑view
    void dettachView();
}
