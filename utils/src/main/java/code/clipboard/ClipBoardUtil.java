package code.clipboard;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;

/**
 * Created by RyanLee on 2017/12/15.
 */

public class ClipBoardUtil {

    /**
     * 复制普通字符型到剪切板
     *
     * @param context
     * @param text
     */
    public static void copyTextToClipBoard(Context context, String text) {
        ClipboardManager mClipboardManager = (ClipboardManager) context.getSystemService(Context.CLIPBOARD_SERVICE);
        // 创建普通字符型ClipData
        ClipData mClipData = ClipData.newPlainText("Label", text);
        // 将ClipData内容放到系统剪贴板里。
        mClipboardManager.setPrimaryClip(mClipData);
    }

    /**
     * 复制URL型到剪切板
     *
     * @param context
     * @param url
     */
    public static void copyUrlToClipBoard(Context context, String url) {
        ClipboardManager mClipboardManager = (ClipboardManager) context.getSystemService(Context.CLIPBOARD_SERVICE);
        // 创建普通字符型ClipData
        ClipData mClipData = ClipData.newRawUri("Label", Uri.parse(url));
        // 将ClipData内容放到系统剪贴板里。
        mClipboardManager.setPrimaryClip(mClipData);
    }

    /**
     * 复制Intent型到剪切板
     *
     * @param context
     * @param intent
     */
    public static void copyIntentToClipBoard(Context context, Intent intent) {
        ClipboardManager mClipboardManager = (ClipboardManager) context.getSystemService(Context.CLIPBOARD_SERVICE);
        // 创建普通字符型ClipData
        ClipData mClipData = ClipData.newIntent("Label", intent);
        // 将ClipData内容放到系统剪贴板里。
        mClipboardManager.setPrimaryClip(mClipData);
    }

    /**
     * 从剪切板获取ClipData数据
     *
     * @param context
     * @return
     */
    public static ClipData getClicpData(Context context) {
        ClipboardManager mClipboardManager = (ClipboardManager) context.getSystemService(Context.CLIPBOARD_SERVICE);
        ClipData clipData = mClipboardManager.getPrimaryClip();
        return clipData;
    }
}
