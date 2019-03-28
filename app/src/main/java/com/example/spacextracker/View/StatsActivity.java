package com.example.spacextracker.View;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.style.ForegroundColorSpan;
import android.text.style.RelativeSizeSpan;

import com.example.spacextracker.Controller.StatsController;
import com.example.spacextracker.Model.Launches;
import com.example.spacextracker.Model.Payloads;
import com.example.spacextracker.R;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class StatsActivity extends AppCompatActivity {

    private List<Launches> dataLaunches;

    private StatsController controller;

    private PieChart pieChart;

    private static Context appContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stats);
        controller = new StatsController(this);
        pieChart = findViewById(R.id.TypeLaunchChart);
        appContext = getApplicationContext();
        controller.getData(true);
    }

    private void fillTypeLaunchChart(List<Launches> launchesList){
        Map<String,Integer> typeLaunchData = new HashMap<>();
        List<PieEntry> typeLaunch = new ArrayList<>();

        pieChart.setCenterText(generateCenterText());
        pieChart.setCenterTextSize(10f);

        // radius of the center hole in percent of maximum radius
        pieChart.setHoleRadius(45f);
        pieChart.setTransparentCircleRadius(50f);

        Legend l = pieChart.getLegend();
        l.setVerticalAlignment(Legend.LegendVerticalAlignment.TOP);
        l.setHorizontalAlignment(Legend.LegendHorizontalAlignment.RIGHT);
        l.setOrientation(Legend.LegendOrientation.VERTICAL);
        l.setDrawInside(false);

        for (Launches launch : launchesList){
            for (Payloads payload : launch.getPayloads()){
                if (typeLaunchData.containsKey(payload.getPayload_type())){
                    typeLaunchData.put(payload.getPayload_type(),typeLaunchData.get(payload.getPayload_type())+1);
                } else {
                    typeLaunchData.put(payload.getPayload_type(),1);
                }
            }
        }
        int totalPayloads = 0;

        for (Map.Entry<String,Integer> entry : typeLaunchData.entrySet()){
            totalPayloads += entry.getValue();
        }

        for (Map.Entry<String,Integer> entry : typeLaunchData.entrySet()){
            typeLaunch.add(new PieEntry(((float)entry.getValue()/(float)totalPayloads)*100f,entry.getKey()));
        }

        PieDataSet set = new PieDataSet(typeLaunch, "Payloads type");
        PieData data = new PieData(set);

        pieChart.setData(data);
        pieChart.invalidate();
    }

    private SpannableString generateCenterText() {
        SpannableString s = new SpannableString("Revenues\nQuarters 2015");
        s.setSpan(new RelativeSizeSpan(2f), 0, 8, 0);
        s.setSpan(new ForegroundColorSpan(Color.GRAY), 8, s.length(), 0);
        return s;
    }

    public void showData(List<Launches> launchList){
        fillTypeLaunchChart(launchList);
    }
    public static Context getAppContext() {
        return appContext;
    }

}
