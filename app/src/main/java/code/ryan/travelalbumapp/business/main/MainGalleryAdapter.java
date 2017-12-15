package code.ryan.travelalbumapp.business.main;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import java.util.List;

import code.ryan.travelalbumapp.R;

/**
 * Created by RyanLee on 2017/12/7.
 */

public class MainGalleryAdapter extends RecyclerView.Adapter<MainGalleryAdapter.MyHolder> {
    private final String TAG = "MainGalleryAdapter";
    private Context mContext;
    private List<Integer> mDatas;
    private ViewGroup mParent;


    public MainGalleryAdapter(Context mContext, List<Integer> mDatas) {
        this.mContext = mContext;
        this.mDatas = mDatas;
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        Log.d(TAG, "onAttachedToRecyclerView");
        this.mParent = recyclerView;
        super.onAttachedToRecyclerView(recyclerView);
    }

    @Override
    public MyHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Log.d(TAG, "onCreateViewHolder" + " width = " + parent.getWidth());
        View itemView = LayoutInflater.from(mContext).inflate(R.layout.item_gallery, parent, false);
        return new MyHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyHolder holder, int position) {
        Log.d(TAG, "onBindViewHolder" + "--> position = " + position);
        // 需要增加此代码修改每一页的宽高
        //GalleryAdapterHelper.newInstance().setItemLayoutParams(mParent, holder.itemView, position, getItemCount());
        holder.mView.setImageResource(mDatas.get(position));
    }

    @Override
    public int getItemCount() {
        return mDatas.size();
    }

    class MyHolder extends RecyclerView.ViewHolder {
        public final ImageView mView;

        public MyHolder(View itemView) {
            super(itemView);

            Log.d(TAG, "MyHolder");

            mView = itemView.findViewById(R.id.iv_photo);
        }
    }

    /**
     * 获取position位置的resId
     * @param position
     * @return
     */
    public int getResId(int position) {
        return mDatas == null ? 0 : mDatas.get(position);
    }
}
