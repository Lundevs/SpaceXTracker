package com.example.spacextracker;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private List<Launches> launchList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mRecyclerView = (RecyclerView) findViewById(R.id.launchPastRecicleView);

        SpaceXAPIInterface restApi = RetroFit.getInstance();
        Call<List<Launches>> call = restApi.getAllLaunchs();
        call.enqueue(new Callback<List<Launches>>() {
            @Override
            public void onResponse(Call<List<Launches>> call, Response<List<Launches>> response) {
                launchList = response.body();
                showList(launchList);
            }

            @Override
            public void onFailure(Call<List<Launches>> call, Throwable t) {
                Log.d("ERROR", "Api Error");
            }
        });



    }

    private void showList(List<Launches> launchList) {
        mRecyclerView.setHasFixedSize(true);

        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);

        mAdapter = new AdapterLaunchMin(launchList);
        mRecyclerView.setAdapter(mAdapter);
    }
}
