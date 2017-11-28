package com.ambits.widgetlib;

import android.content.Context;

import com.facebook.drawee.backends.pipeline.Fresco;

/**
 * Created by Administrator on 2017/11/28.
 */

public class WidgetLibManager {
    public static void initWidgetLib(Context context) {
        Fresco.initialize(context);
    }
}
