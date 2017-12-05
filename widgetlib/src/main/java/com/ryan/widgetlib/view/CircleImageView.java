package com.ryan.widgetlib.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;

import com.facebook.drawee.drawable.ScalingUtils;
import com.facebook.drawee.generic.GenericDraweeHierarchy;
import com.facebook.drawee.generic.RoundingParams;
import com.facebook.drawee.view.SimpleDraweeView;
import com.ryan.widgetlib.R;

/**
 * Created by RyanLee on 2017/12/4.
 */

public class CircleImageView extends SimpleDraweeView {

    private int mDefaultRes;

    public CircleImageView(Context context) {
        this(context, null);
    }

    public CircleImageView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public CircleImageView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);

        initTypedArray(context, attrs, defStyle);
        setDefaultImageResId(getDefaultContactIcon());
        setIsAsCircle(true);
    }

    private void initTypedArray(Context context, AttributeSet attrs, int defStyle) {
        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.CircleImageView, defStyle, 0);
        mDefaultRes = a.getResourceId(R.styleable.CircleImageView_default_img, 0);
        a.recycle();
    }

    protected int getDefaultContactIcon() {
        return mDefaultRes;
    }

    public void setDefaultImageResId(int defaultImageId) {
        if (defaultImageId != 0) {
            // 如果没有设置默认图片
            getHierarchy().setPlaceholderImage(getContext().getResources().getDrawable(defaultImageId), ScalingUtils.ScaleType.FIT_CENTER);
        }
    }

    public void setIsAsCircle(boolean asCircle) {
        GenericDraweeHierarchy dh = getHierarchy();
        if (dh == null) {
            return;
        }
        RoundingParams rp = dh.getRoundingParams();
        if (rp == null) {
            if (asCircle == true) {
                rp = new RoundingParams();
                rp.setRoundAsCircle(asCircle);
                getHierarchy().setRoundingParams(rp);
            }
        } else {
            rp.setRoundAsCircle(asCircle);
        }
    }
}
