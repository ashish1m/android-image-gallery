package com.helloapp.imageviewer.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Parcel;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.helloapp.imageviewer.R;

import java.io.File;
import java.util.List;

/**
 * Created by GoodWorkLabs on 18-03-2016.
 */
public class GridViewAdapter extends BaseAdapter {

    private Context context;
    List<String> filePaths;
    private int imageWidth;
    //private Animation animation;
    private int lastPosition = -1;

    public GridViewAdapter(Context context, List<String> filePaths, int imageWidth) {
        this.context = context;
        this.filePaths = filePaths;
        this.imageWidth = imageWidth;
        //animation = AnimationUtils.loadAnimation(context, R.anim.animation_move);
    }

    @Override
    public int getCount() {
        return filePaths.size();
    }

    @Override
    public Object getItem(int position) {
        return filePaths.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        //SimpleDraweeView draweeView;
        ImageView imageView;
        if (convertView == null) {
            //draweeView = new SimpleDraweeView(context);
            imageView = new ImageView(context);
        } else {
            //draweeView = (SimpleDraweeView) convertView;
            imageView = (ImageView)convertView;
        }

        File file = new File(filePaths.get(position));
        Uri uri = Uri.fromFile(file);
//        Bitmap image = decodeFile(filePaths.get(position), imageWidth, imageWidth);
        imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
        imageView.setLayoutParams(new GridView.LayoutParams(imageWidth, imageWidth));

        Glide.with(context).load(uri).placeholder(R.drawable.default_img).centerCrop().into(imageView);
        Animation animation = AnimationUtils.loadAnimation(context, (position > lastPosition) ? R.anim.up_from_bottom : R.anim.down_from_top);
        //Animation animation = AnimationUtils.loadAnimation(context, R.anim.up_from_bottom);
        imageView.startAnimation(animation);
        //imageView.setImageURI(uri);
        lastPosition = position;

        return imageView;
    }

    public Bitmap decodeFile(String filePath, int reqWidth, int reqHeight) {

        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeFile(filePath);

        options.inSampleSize = calculateInSampleSize(options, reqWidth, reqHeight);

        options.inJustDecodeBounds = false;

        return BitmapFactory.decodeFile(filePath);
    }

    public int calculateInSampleSize(BitmapFactory.Options options, int reqWidth, int reqHeight) {

        final int width = options.outWidth;
        final int height = options.outHeight;
        int inSampleSize = 1;

        if (width > reqWidth || height > reqHeight) {

            int halfWidth = width / 2;
            int halfHeight = height / 2;

            while (((halfWidth / inSampleSize) > reqWidth) && ((halfHeight / inSampleSize) > reqHeight)) {
                inSampleSize *= 2;
            }

        }

        return inSampleSize;
    }
}
