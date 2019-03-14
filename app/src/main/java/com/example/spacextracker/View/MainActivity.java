package com.example.spacextracker.View;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.spacextracker.Controller.MainController;
import com.example.spacextracker.Model.Launches;
import com.example.spacextracker.R;
import com.google.gson.Gson;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;

    private Button refreshButton;
    private ProgressBar progressBar;

    private Toast failAPIToast;

    private MainController controller;

    private static Context appContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        appContext = getApplicationContext();
        setContentView(R.layout.activity_main);
        refreshButton = (Button) findViewById(R.id.refreshButton);
        mRecyclerView = (RecyclerView) findViewById(R.id.launchPastRecicleView);
        progressBar = findViewById(R.id.progressBar);

        controller = new MainController(this);
        controller.onCreate();
        refreshButton.setOnClickListener(controller.getRefreshButtonListener());
        failAPIToast = Toast.makeText(getApplicationContext(),"Failed to load API",Toast.LENGTH_SHORT);
    }

    public void showList(List<Launches> launchList) {
        mRecyclerView.setVisibility(View.VISIBLE);
        progressBar.setVisibility(View.GONE);
        mRecyclerView.setHasFixedSize(true);

        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);

        mAdapter = new AdapterLaunchMin(launchList, new ListenerLaunchesList());
        mRecyclerView.setAdapter(mAdapter);
    }

    public void showProgress(){
        mRecyclerView.setVisibility(View.GONE);
        progressBar.setVisibility(View.VISIBLE);
    }

    public class ListenerLaunchesList implements View.OnClickListener {

        @Override
        public void onClick(View v) {
            Intent intent = new Intent(MainActivity.this, DetailedLaunchActivity.class);
            int itemPosition = mRecyclerView.getChildLayoutPosition(v);
            Gson gson = new Gson();
            intent.putExtra("flightNumber", itemPosition+1);
            intent.putExtra("jsonData", gson.toJson(controller.getListLaunches().get(itemPosition)));
            startActivity(intent);
        }
    }

    public static Context getAppContext() {
        return appContext;
    }
}
