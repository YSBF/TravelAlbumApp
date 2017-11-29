package com.ryan.widgetlib;

import android.content.Context;

import com.facebook.drawee.backends.pipeline.Fresco;

/**
 * Created by RyanLee on 2017/11/28.
 */

public class WidgetLibManager {
    public static void initWidgetLib(Context context) {
        Fresco.initialize(context);
    }
}
