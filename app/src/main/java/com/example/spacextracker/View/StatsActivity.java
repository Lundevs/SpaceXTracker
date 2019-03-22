package com.example.spacextracker.View;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.spacextracker.Controller.StatsController;
import com.example.spacextracker.Model.Launches;
import com.example.spacextracker.R;

import java.util.List;

public class StatsActivity extends AppCompatActivity {

    List<Launches> dataLaunches;

    StatsController controller;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stats);
        controller = new StatsController(this);

    }

    public void showData(List<Launches> launchList){

    }
}
