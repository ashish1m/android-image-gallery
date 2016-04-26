package com.helloapp.imageviewer;

import android.net.Uri;
import android.os.Parcel;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.Spinner;

import com.bumptech.glide.Glide;
import com.eftimoff.viewpagertransformers.AccordionTransformer;
import com.eftimoff.viewpagertransformers.BackgroundToForegroundTransformer;
import com.eftimoff.viewpagertransformers.CubeInTransformer;
import com.eftimoff.viewpagertransformers.CubeOutTransformer;
import com.eftimoff.viewpagertransformers.DefaultTransformer;
import com.eftimoff.viewpagertransformers.DepthPageTransformer;
import com.eftimoff.viewpagertransformers.DrawFromBackTransformer;
import com.eftimoff.viewpagertransformers.FlipHorizontalTransformer;
import com.eftimoff.viewpagertransformers.FlipVerticalTransformer;
import com.eftimoff.viewpagertransformers.ForegroundToBackgroundTransformer;
import com.eftimoff.viewpagertransformers.ParallaxPageTransformer;
import com.eftimoff.viewpagertransformers.RotateDownTransformer;
import com.eftimoff.viewpagertransformers.RotateUpTransformer;
import com.eftimoff.viewpagertransformers.StackTransformer;
import com.eftimoff.viewpagertransformers.TabletTransformer;
import com.eftimoff.viewpagertransformers.ZoomInTransformer;
import com.eftimoff.viewpagertransformers.ZoomOutSlideTransformer;
import com.eftimoff.viewpagertransformers.ZoomOutTranformer;
import com.helloapp.imageviewer.adapter.ImagePagerAdapter;
import com.helloapp.imageviewer.helper.Utils;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class FullImageActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener{

    ViewPager viewPager;
    Utils utils;
    List<String> imagePath = new ArrayList<>();
    ImagePagerAdapter pagerAdapter;
    List<String> transformers = new ArrayList<>();
    Spinner spinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_full_image);

        viewPager = (ViewPager)findViewById(R.id.pager);
        spinner = (Spinner)findViewById(R.id.spn_transformer);
        utils = new Utils(this);
        imagePath = utils.getImagesPath(this);

        transformers.add("Accordion");
        transformers.add("BackgroundToForeground");
        transformers.add("CubeIn");
        transformers.add("CubeOut");
        transformers.add("Default");
        transformers.add("DepthPage");
        transformers.add("DrawFromBack");
        transformers.add("FlipHorizontal");
        transformers.add("FlipVertical");
        transformers.add("ForegroundToBackground");
        transformers.add("RotateDown");
        transformers.add("RotateUp");
        transformers.add("Stack");
        transformers.add("Tablet");
        transformers.add("ZoomIn");
        transformers.add("ZoomOutSlide");
        transformers.add("ZoomOut");

        /*
        Available transformers

        AccordionTransformer
        BackgroundToForegroundTransformer
        CubeInTransformer
        CubeOutTransformer
        DefaultTransformer
        DepthPageTransformer
        DrawFromBackTransformer
        FlipHorizontalTransformer
        FlipVerticalTransformer
        ForegroundToBackgroundTransformer
        ParallaxPageTransformer
        RotateDownTransformer
        RotateUpTransformer
        StackTransformer
        TabletTransformer
        ZoomInTransformer
        ZoomOutSlideTransformer
        ZoomOutTranformer
*/



        ArrayAdapter<String> spinAdapter = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_spinner_item, transformers);
        spinAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        pagerAdapter = new ImagePagerAdapter(this, imagePath);

        spinner.setAdapter(spinAdapter);
        viewPager.setAdapter(pagerAdapter);
        spinner.setOnItemSelectedListener(this);

//        switch(1){
//            case 0:
//                viewPager.setPageTransformer(true, new AccordionTransformer());
//                break;
//
//            case 1:
//                viewPager.setPageTransformer(true, new BackgroundToForegroundTransformer());
//                break;
//
//            case 2:
//                viewPager.setPageTransformer(true, new CubeInTransformer());
//                break;
//
//            case 3:
//                viewPager.setPageTransformer(true, new CubeOutTransformer());
//                break;
//
//            case 4:
//                viewPager.setPageTransformer(true, new DefaultTransformer());
//                break;
//
//            case 5:
//                viewPager.setPageTransformer(true, new DepthPageTransformer());
//                break;
//
//            case 6:
//                viewPager.setPageTransformer(true, new DrawFromBackTransformer());
//                break;
//
//            case 7:
//                viewPager.setPageTransformer(true, new FlipHorizontalTransformer());
//                break;
//
//            case 8:
//                viewPager.setPageTransformer(true, new FlipVerticalTransformer());
//                break;
//
//            case 9:
//                viewPager.setPageTransformer(true, new ForegroundToBackgroundTransformer());
//                break;
//
//            case 10:
//                //viewPager.setPageTransformer(true, new ParallaxPageTransformer(1));
//                break;
//
//            case 11:
//                viewPager.setPageTransformer(true, new RotateDownTransformer());
//                break;
//
//            case 12:
//                viewPager.setPageTransformer(true, new RotateUpTransformer());
//                break;
//
//            case 13:
//                viewPager.setPageTransformer(true, new StackTransformer());
//                break;
//
//            case 14:
//                viewPager.setPageTransformer(true, new TabletTransformer());
//                break;
//
//            case 15:
//                viewPager.setPageTransformer(true, new ZoomInTransformer());
//                break;
//
//            case 16:
//                viewPager.setPageTransformer(true, new ZoomOutTranformer());
//                break;
//
//            case 17:
//                viewPager.setPageTransformer(true, new ZoomOutSlideTransformer());
//                break;
//
//            default:
//                break;
//        }

        //viewPager.setPageTransformer(true, new AccordionTransformer());
        Bundle bundle = getIntent().getExtras();
        viewPager.setCurrentItem(bundle.getInt("itemPosition", -1));



//        String filepath = bundle.getString("filepath");
//
//        Uri uri = Uri.fromFile(new File(filepath));
//
//        //Glide.with(this).load(uri).into(imageView);
//
//        imageView.setImageURI(uri);
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

        switch(position){
            case 0:
                viewPager.setPageTransformer(true, new AccordionTransformer());
                break;

            case 1:
                viewPager.setPageTransformer(true, new BackgroundToForegroundTransformer());
                break;

            case 2:
                viewPager.setPageTransformer(true, new CubeInTransformer());
                break;

            case 3:
                viewPager.setPageTransformer(true, new CubeOutTransformer());
                break;

            case 4:
                viewPager.setPageTransformer(true, new DefaultTransformer());
                break;

            case 5:
                viewPager.setPageTransformer(true, new DepthPageTransformer());
                break;

            case 6:
                viewPager.setPageTransformer(true, new DrawFromBackTransformer());
                break;

            case 7:
                viewPager.setPageTransformer(true, new FlipHorizontalTransformer());
                break;

            case 8:
                viewPager.setPageTransformer(true, new FlipVerticalTransformer());
                break;

            case 9:
                viewPager.setPageTransformer(true, new ForegroundToBackgroundTransformer());
                break;

//            case 10:
//                //viewPager.setPageTransformer(true, new ParallaxPageTransformer(1));
//                break;

            case 10:
                viewPager.setPageTransformer(true, new RotateDownTransformer());
                break;

            case 11:
                viewPager.setPageTransformer(true, new RotateUpTransformer());
                break;

            case 12:
                viewPager.setPageTransformer(true, new StackTransformer());
                break;

            case 13:
                viewPager.setPageTransformer(true, new TabletTransformer());
                break;

            case 14:
                viewPager.setPageTransformer(true, new ZoomInTransformer());
                break;

            case 15:
                viewPager.setPageTransformer(true, new ZoomOutTranformer());
                break;

            case 16:
                viewPager.setPageTransformer(true, new ZoomOutSlideTransformer());
                break;

            default:
                break;
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    
}
