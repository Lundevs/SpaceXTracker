package com.example.spacextracker.View;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.example.spacextracker.Controller.MainController;
import com.example.spacextracker.Model.Launches;
import com.example.spacextracker.R;
import com.google.gson.Gson;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;

    private MainController controller;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mRecyclerView = (RecyclerView) findViewById(R.id.launchPastRecicleView);

        controller = new MainController(this);
        controller.onCreate();
    }

    public void showList(List<Launches> launchList) {
        mRecyclerView.setHasFixedSize(true);

        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);

        mAdapter = new AdapterLaunchMin(launchList, new ListenerLaunchesList());
        mRecyclerView.setAdapter(mAdapter);
    }

    public class ListenerLaunchesList implements View.OnClickListener {

        @Override
        public void onClick(View v) {
            Intent intent = new Intent(MainActivity.this, DetailedLaunchActivity.class);
            int itemPosition = mRecyclerView.getChildLayoutPosition(v);
            Gson gson = new Gson();
            intent.putExtra("flightNumber", itemPosition+1);
            intent.putExtra("jsonData",gson.toJson(controller.getListLaunches().get(itemPosition)));
            startActivity(intent);
        }
    }
}
