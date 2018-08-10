package com.example.marius.hellogridview;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

public class ImageAdapter extends BaseAdapter {
    private Context mContext;
    public ImageAdapter(Context c)
    {
        mContext = c;
    }
    @Override
    public int getCount() {
        return mThumbIds.length;
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ImageView imageView;
        if (convertView == null)
        {
            imageView = new ImageView(mContext);
            imageView.setLayoutParams(new ViewGroup.LayoutParams(85,85));
            imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
            imageView.setPadding(8,8,8,8);
        } else {
            imageView = (ImageView) convertView;
        }
        imageView.setImageResource(mThumbIds[position]);
        return imageView;
    }
    private Integer[] mThumbIds = {
            R.drawable.s_2, R.drawable.s_3,
            R.drawable.s_4, R.drawable.s_5,
            R.drawable.s_6, R.drawable.s_7,
            R.drawable.s_0, R.drawable.s_1,
            R.drawable.s_2, R.drawable.s_3,
            R.drawable.s_4, R.drawable.s_5,
            R.drawable.s_6, R.drawable.s_7,
            R.drawable.s_0, R.drawable.s_1,
            R.drawable.s_2, R.drawable.s_3,
            R.drawable.s_4, R.drawable.s_5,
            R.drawable.s_6, R.drawable.s_7
    };
}
