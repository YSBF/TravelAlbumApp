package code.ryan.travelalbumapp.common;

import android.app.Application;

import com.ryan.widgetlib.WidgetLibManager;

/**
 * Created by Administrator on 2017/11/28.
 */

public class MyApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();

        // init WidgetLib
        WidgetLibManager.initWidgetLib(this);
    }
}
