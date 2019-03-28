package com.example.spacextracker.Controller;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import com.example.spacextracker.Model.Launches;
import com.example.spacextracker.View.LaunchActivity;
import com.example.spacextracker.View.StatsActivity;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.content.Context.MODE_PRIVATE;

public class StatsController {

    private StatsActivity view;

    private Context context;
    private SharedPreferences sharedPreferences;
    private String dataCache = "dataCache";
    private String launchData = "launchTotal";

    private SpaceXAPIInterface restApi = ApiManager.getInstance();

    private List<Launches> launchList;

    private Gson gson = new Gson();

    public StatsController (StatsActivity statsActivity){
        this.view = statsActivity;
    }

    private void putDataCache(){
        String launchJson = sharedPreferences.getString(launchData,"");
        Type launchListType = new TypeToken<ArrayList<Launches>>(){}.getType();
        launchList = gson.fromJson(launchJson, launchListType);
        view.showData(launchList);
    }

    public void getData(Boolean checkCache){
        sharedPreferences = StatsActivity.getAppContext().getSharedPreferences(dataCache, MODE_PRIVATE);
        Call<List<Launches>> call;
        if (sharedPreferences.contains(launchData) && checkCache) {
            putDataCache();
        } else {
            //view.showProgress();
            call = restApi.getAllLaunches();
            call.enqueue(new Callback<List<Launches>>() {
                @Override
                public void onResponse(Call<List<Launches>> call, Response<List<Launches>> response) {
                    launchList = response.body();
                    view.showData(launchList);
                    sharedPreferences.edit()
                            .putString(launchData,gson.toJson(launchList))
                            .apply();
                }

                @Override
                public void onFailure(Call<List<Launches>> call, Throwable t) {
                    // toast load error
                    Log.d("ERROR", "Api Error");
                    if (sharedPreferences.contains(launchData)){
                        putDataCache();
                    }
                }
            });
        }
    }
}
