package code.ryan.travelalbumapp.test;

import android.content.Context;
import android.content.res.TypedArray;

import java.util.ArrayList;
import java.util.List;

import code.ryan.travelalbumapp.R;

/**
 * Created by RyanLee on 2017/12/15.
 */

public class MainTest {

    /***
     * 测试数据
     * @return
     */
    public static List<Integer> getDatas(Context context) {
        TypedArray ar = context.getResources().obtainTypedArray(R.array.test_arr);
        final int[] resIds = new int[ar.length()];
        for (int i = 0; i < ar.length(); i++) {
            resIds[i] = ar.getResourceId(i, 0);
        }
        ar.recycle();
        List<Integer> tDatas = new ArrayList<>();
        for (int resId : resIds) {
            tDatas.add(resId);
        }
        return tDatas;
    }
}
