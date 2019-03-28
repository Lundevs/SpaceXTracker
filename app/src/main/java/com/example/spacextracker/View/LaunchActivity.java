package com.example.spacextracker.View;

import android.app.ActivityOptions;
import android.content.Context;
import android.content.Intent;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.transition.Slide;
import android.view.Gravity;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.spacextracker.Controller.LaunchController;
import com.example.spacextracker.Model.Launches;
import com.example.spacextracker.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.gson.Gson;

import java.util.Collections;
import java.util.List;

public class LaunchActivity extends AppCompatActivity {
    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;

    private FloatingActionButton refreshButton;

    private ProgressBar progressBar;

    private Toast failAPIToast;

    private LaunchController controller;

    private int launchType;

    private TextView activityName;

    private static Context appContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent intent = getIntent();
        appContext = getApplicationContext();
        setContentView(R.layout.past_launch);

        refreshButton = findViewById(R.id.refreshButton);
        mRecyclerView = (RecyclerView) findViewById(R.id.launchPastRecicleView);
        progressBar = findViewById(R.id.progressBar);
        activityName = findViewById(R.id.activityName);

        controller = new LaunchController(this);
        launchType = intent.getIntExtra("launchType",1);
        if (launchType == 1){
            refreshButton.setOnClickListener(controller.getRefreshButtonListenerPast());
            activityName.setText("Past Launches");
        }else {
            refreshButton.setOnClickListener(controller.getRefreshButtonListenerFuture());
            activityName.setText("Future Launches");
        }
        controller.getData(true,launchType);
        failAPIToast = Toast.makeText(getApplicationContext(),"Failed to load API",Toast.LENGTH_SHORT);

        getWindow().setExitTransition(new Slide(Gravity.LEFT));
        getWindow().setEnterTransition(new Slide(Gravity.RIGHT));
        getWindow().setAllowEnterTransitionOverlap(false);
        getWindow().setAllowReturnTransitionOverlap(false);
    }

    public void showList(List<Launches> launchList, Boolean reverse) {
        if (reverse){
            Collections.reverse(launchList);
        }
        mRecyclerView.setVisibility(View.VISIBLE);
        progressBar.setVisibility(View.GONE);
        mRecyclerView.setHasFixedSize(true);

        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);

        mAdapter = new AdapterLaunchMin(launchList, new ListenerLaunchesList(), launchType);
        mRecyclerView.setAdapter(mAdapter);
    }

    public void showProgress(){
        mRecyclerView.setVisibility(View.GONE);
        progressBar.setVisibility(View.VISIBLE);
    }

    public class ListenerLaunchesList implements View.OnClickListener {

        @Override
        public void onClick(View v) {
            Intent intent = new Intent(LaunchActivity.this, DetailedLaunchActivity.class);
            int itemPosition = mRecyclerView.getChildLayoutPosition(v);
            Gson gson = new Gson();
            intent.putExtra("flightNumber", itemPosition+1);
            intent.putExtra("jsonData", gson.toJson(controller.getListLaunches().get(itemPosition)));
            startActivity(intent,
                    ActivityOptions.makeSceneTransitionAnimation(LaunchActivity.this).toBundle());
        }
    }

    public static Context getAppContext() {
        return appContext;
    }
}