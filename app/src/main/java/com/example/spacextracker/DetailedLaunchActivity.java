package com.example.spacextracker;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;

import com.example.spacextracker.Model.Launches;
import com.squareup.picasso.Picasso;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DetailedLaunchActivity extends AppCompatActivity {

    private Launches launchData;
    private ImageView launchImg;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detailed_launch);

        launchImg = findViewById(R.id.launchImg);

        Intent intent = getIntent();
        SpaceXAPIInterface restApi = RetroFit.getInstance();
        Call<Launches> call = restApi.getLaunchByNumber(intent.getIntExtra("flightNumber",0));
        call.enqueue(new Callback<Launches>() {
            @Override
            public void onResponse(Call<Launches> call, Response<Launches> response) {
                launchData = response.body();
                Picasso.get()
                        .load(launchData.getPatchURL())
                        .placeholder(R.drawable.ic_launcher_foreground)
                        .into(launchImg);
            }

            @Override
            public void onFailure(Call<Launches> call, Throwable t) {
                Log.d("ERROR", "Api Error");
            }
        });
    }
}
