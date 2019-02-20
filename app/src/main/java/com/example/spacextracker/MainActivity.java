package com.example.spacextracker;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;

import com.example.spacextracker.Model.Launches;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.support.v4.content.ContextCompat.startActivity;

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
        Call<List<Launches>> call = restApi.getAllLaunches();
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

        mAdapter = new AdapterLaunchMin(launchList, new ListenerLaunchesList());
        mRecyclerView.setAdapter(mAdapter);
    }

    public class ListenerLaunchesList implements View.OnClickListener {

        @Override
        public void onClick(View v) {
            Intent intent = new Intent(MainActivity.this, DetailedLaunchActivity.class);
            int itemPosition = mRecyclerView.getChildLayoutPosition(v);
            intent.putExtra("flightNumber", itemPosition+1);
            startActivity(intent);
        }
    }
}
