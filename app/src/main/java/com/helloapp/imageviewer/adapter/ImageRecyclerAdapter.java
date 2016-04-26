package com.helloapp.imageviewer.adapter;

import android.content.Context;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.helloapp.imageviewer.R;

import java.io.File;
import java.util.List;

/**
 * Created by GoodWorkLabs on 23-03-2016.
 */
public class ImageRecyclerAdapter extends RecyclerView.Adapter<ImageRecyclerAdapter.ViewHolder> {

    private Context context;
    List<String> filePaths;
    private int imageWidth;
    LayoutInflater inflater;

    public ImageRecyclerAdapter(Context context, List<String> filePaths, int imageWidth){
        this.context = context;
        this.filePaths = filePaths;
        this.imageWidth = imageWidth;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        ViewHolder holder;
//        inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
//        View view = inflater.inflate(R.layout.layout_item, parent, false);
//        SimpleDraweeView draweeView = new SimpleDraweeView(context);
//        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(imageWidth, imageWidth);
//        draweeView.setLayoutParams(params);
//
//        holder = new ViewHolder(draweeView);

        return null;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        File file = new File(filePaths.get(position));
        Uri uri = Uri.fromFile(file);
//        holder.getDraweeView().setImageURI(uri);
    }

    @Override
    public int getItemCount() {
        return filePaths.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{
//        private SimpleDraweeView draweeView;
        //ImageView img_dp;
        public ViewHolder(View itemView) {
            super(itemView);
            //img_dp = (ImageView)itemView.findViewById(R.id.img_dp);
//            draweeView = (SimpleDraweeView)itemView;
        }

//        public SimpleDraweeView getDraweeView(){
//            return draweeView;
//        }
    }

}
