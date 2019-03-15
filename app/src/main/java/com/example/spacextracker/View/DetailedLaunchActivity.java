package com.example.spacextracker.View;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.spacextracker.Controller.MainController;
import com.example.spacextracker.Model.Launches;
import com.example.spacextracker.R;
import com.google.gson.Gson;
import com.squareup.picasso.Picasso;

import java.text.DateFormat;
import java.util.ResourceBundle;

public class DetailedLaunchActivity extends AppCompatActivity {

    private Launches launchData;
    private ImageView launchImg;
    private TextView launchDate;
    private TextView missionName;
    private TextView launchRocket;
    private TextView launchSite;
    private TextView launchResult;
    private TextView launchDescription;

    private RecyclerView imgRecyclerView;
    private RecyclerView.Adapter imgAdapter;
    private RecyclerView.LayoutManager imgLayoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent intent = getIntent();

        setContentView(R.layout.activity_detailed_launch);

        launchImg = findViewById(R.id.launchImg);
        launchDate = findViewById(R.id.launchDate);
        missionName = findViewById(R.id.missionName);
        launchRocket = findViewById(R.id.launchRocket);
        launchSite = findViewById(R.id.launchSite);
        launchResult = findViewById(R.id.launchResult);
        launchDescription = findViewById(R.id.launchDescription);
        imgRecyclerView = (RecyclerView)findViewById(R.id.imgRecView);


        Gson gson = new Gson();
        launchData = gson.fromJson(intent.getStringExtra("jsonData"), Launches.class);

        if (MainController.isOnline()){
            showImgList();
        }else {
            imgRecyclerView.setVisibility(View.GONE);
        }

        Picasso.get()
                .load(launchData.getSmallPatchURL())
                .placeholder(R.drawable.ic_launcher_foreground)
                .resize(0,256)
                .into(launchImg);
        launchDate.setText(DateFormat.getDateInstance(DateFormat.FULL).format(launchData.getLaunch_date_utc()));
        missionName.setText(launchData.getMission_name());
        launchRocket.setText(launchData.getRocket_name());
        launchSite.setText(launchData.getLaunchSite());
        if (launchData.getLaunchResult()){
            launchResult.setText("Successful Launch! \uD83D\uDE80");
        }else {
            launchResult.setText("Launch Failure");
        }
        launchDescription.setText(launchData.getLaunchDescription());
    }

    private void showImgList(){
        imgRecyclerView.setHasFixedSize(true);

        imgLayoutManager = new GridLayoutManager(this,2);
        imgRecyclerView.setLayoutManager(imgLayoutManager);

        imgAdapter = new ImgAdapter(launchData.getListImage());
        imgRecyclerView.setAdapter(imgAdapter);
    }
}
