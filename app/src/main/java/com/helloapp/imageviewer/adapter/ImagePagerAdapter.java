package com.helloapp.imageviewer.adapter;

import android.content.Context;
import android.net.Uri;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.bumptech.glide.Glide;
import com.helloapp.imageviewer.R;
import com.helloapp.imageviewer.helper.TouchImageView;

import java.io.File;
import java.util.List;

/**
 * Created by GoodWorkLabs on 23-03-2016.
 */
public class ImagePagerAdapter extends PagerAdapter {

    List<String> filePaths;
    private Context context;
    LayoutInflater inflater;

    public ImagePagerAdapter(Context context, List<String> filePaths){
        this.context = context;
        this.filePaths = filePaths;
        inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }
    @Override
    public int getCount() {
        return filePaths.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view ==  ((LinearLayout)object);
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {

        View itemView = inflater.inflate(R.layout.pager_item, container, false);

        ImageView imageView = (TouchImageView)itemView.findViewById(R.id.img_full_dp);

        //ImageView imageView = (ImageView)itemView.findViewById(R.id.img_full_dp);

        Uri uri = Uri.fromFile(new File(filePaths.get(position)));

        Glide.with(context).load(uri).into(imageView);

        //imageView.setImageURI(uri);

        container.addView(itemView);

        return itemView;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        //super.destroyItem(container, position, object);
        container.removeView((LinearLayout) object);
    }
}
