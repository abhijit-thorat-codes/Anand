package com.example.dummy.anand;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Gallery;
import android.widget.ImageView;

/**
 * Created by Dummy on 05/05/2017.
 */

public class GalaryAdapter extends BaseAdapter {

    private Context mContext;



    public GalaryAdapter(Context context)
    {
        mContext = context;
    }

    public int getCount() {
        return mImageIds.length;
    }

    public Object getItem(int position) {
        return position;
    }

    public long getItemId(int position) {
        return position;
    }


    // Override this method according to your need
    public View getView(int index, View view, ViewGroup viewGroup)
    {
        // TODO Auto-generated method stub
        ImageView i = new ImageView(mContext);

        i.setImageResource(mImageIds[index]);
        i.setLayoutParams(new Gallery.LayoutParams(200, 200));

        i.setScaleType(ImageView.ScaleType.FIT_XY);


        return i;
    }

    public Integer[] mImageIds = {
            R.drawable.bg28,
            R.drawable.bg29,
            R.drawable.bg30,
            R.drawable.bg31,
            R.drawable.bg32,
            R.drawable.logo_light_small
    };

}



