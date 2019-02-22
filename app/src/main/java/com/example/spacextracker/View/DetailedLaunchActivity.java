package com.example.spacextracker.View;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.spacextracker.Model.Launches;
import com.example.spacextracker.R;
import com.google.gson.Gson;
import com.squareup.picasso.Picasso;

import java.text.DateFormat;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DetailedLaunchActivity extends AppCompatActivity {

    private Launches launchData;
    private ImageView launchImg;
    private TextView launchDate;
    private TextView missionName;
    private TextView launchRocket;
    private TextView launchSite;
    private TextView launchResult;
    private TextView launchDescription;
    private TextView firstStageBlock;
    private TextView firstTageDetails;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detailed_launch);

        launchImg = findViewById(R.id.launchImg);
        launchDate = findViewById(R.id.launchDate);
        missionName = findViewById(R.id.missionName);
        launchRocket = findViewById(R.id.launchRocket);
        launchSite = findViewById(R.id.launchSite);
        launchResult = findViewById(R.id.launchResult);
        launchDescription = findViewById(R.id.launchDescription);
        firstStageBlock = findViewById(R.id.firstStageBlock);
        firstTageDetails = findViewById(R.id.firstStageDetails);

        Gson gson = new Gson();
        Intent intent = getIntent();
        Launches launchData = gson.fromJson(intent.getStringExtra("jsonData"), Launches.class);

        Picasso.get()
                .load(launchData.getSmallPatchURL())
                .placeholder(R.drawable.ic_launcher_foreground)
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
        firstStageBlock.append(new Integer(launchData.getFistStageBlock()).toString());
    }
}
