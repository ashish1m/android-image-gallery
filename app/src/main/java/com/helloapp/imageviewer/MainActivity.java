package com.helloapp.imageviewer;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.res.Resources;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.TypedValue;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;

import com.helloapp.imageviewer.adapter.GridViewAdapter;
import com.helloapp.imageviewer.adapter.ImageRecyclerAdapter;
import com.helloapp.imageviewer.helper.AppConstant;
import com.helloapp.imageviewer.helper.Utils;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private Utils utils;
    private GridViewAdapter adapter;
    private GridView gridView;
    List<String> imagePath = new ArrayList<>();
    private int columnWidth = 2;
    ProgressDialog progressDialog = null;
    //RecyclerView recycler_view;
    //ImageRecyclerAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        progressDialog = new ProgressDialog(this);
        progressDialog.setIndeterminate(true);
        progressDialog.setMessage("Please wait... while loading");
        progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        progressDialog.setCancelable(false);
        progressDialog.show();
        //recycler_view = (RecyclerView)findViewById(R.id.recycler_view);
        //recycler_view.setLayoutManager(new GridLayoutManager(this, columnWidth));
        //recycler_view.setHasFixedSize(true);
        gridView = (GridView)findViewById(R.id.grid_view);

        utils = new Utils(this);

        initializingGridLayout();

        imagePath = utils.getImagesPath(this);

        //adapter = new ImageRecyclerAdapter(this, imagePath, columnWidth);
        //recycler_view.setAdapter(adapter);

        adapter = new GridViewAdapter(this, imagePath, columnWidth);

        gridView.setAdapter(adapter);
        progressDialog.dismiss();
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String filepath = imagePath.get(position);
                Intent intent = new Intent(MainActivity.this, FullImageActivity.class);
                intent.putExtra("filepath", filepath);
                intent.putExtra("itemPosition", position);
                startActivity(intent);
            }
        });
    }

    private void initializingGridLayout() {

        Resources resources = getResources();
        float padding = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, AppConstant.GRID_PADDING, resources.getDisplayMetrics());
        columnWidth = (int) ((utils.getScreenWidth() - ((AppConstant.NUM_OF_COLUMNS + 1)* padding)) / AppConstant.NUM_OF_COLUMNS);

        gridView.setNumColumns(AppConstant.NUM_OF_COLUMNS);
        gridView.setColumnWidth(columnWidth);
        gridView.setStretchMode(GridView.NO_STRETCH);

        gridView.setPadding((int)padding, (int)padding, (int)padding, (int)padding);
        gridView.setHorizontalSpacing((int)padding);
        gridView.setVerticalSpacing((int) padding);
    }

}
