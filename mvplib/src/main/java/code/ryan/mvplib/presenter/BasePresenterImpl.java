package code.ryan.mvplib.presenter;

/**
 * Created by RyanLee on 2017/11/18.
 */

public abstract class BasePresenterImpl<T, V> {
    public T mView;
    public V mProxy;

    public void attachView(T mView) {
        this.mView = mView;
        mProxy = setmProxy();
        onFinishAttach();
    }

    public void dettachView() {
        mView = null;
        mProxy = null;
        onDettachView();
    }

    public void resumeView() {
        onResume();
    }

    public abstract V setmProxy();

    public void onFinishAttach() {
    }

    public void onResume() {
    }

    public void onDettachView() {
    }

}
