package com.ryan.widgetlib.base.util;

import android.content.res.Resources;

/**
 * Created by RyanLee on 2017/11/29.
 */

public class OsUtil {
    public static int dpToPx(int dp) {
        return (int) (dp * Resources.getSystem().getDisplayMetrics().density + 0.5f);
    }

    public static int pxToDp(int px) {
        return (int) (px / Resources.getSystem().getDisplayMetrics().density + 0.5f);
    }
}
