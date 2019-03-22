package com.example.spacextracker.Controller;
import android.content.Context;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.util.Log;
import android.view.View;

import com.example.spacextracker.View.LaunchActivity;
import com.example.spacextracker.Model.Launches;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.content.Context.MODE_PRIVATE;

public class LaunchController {
    private LaunchActivity view;

    private List<Launches> launchList;

    private Context context;
    private SharedPreferences sharedPreferences;
    private String dataCache = "dataCache";
    private String launchData;

    private Gson gson = new Gson();

    private SpaceXAPIInterface restApi = ApiManager.getInstance();

    public LaunchController(LaunchActivity launchActivity) {
        this.view = launchActivity;
    }

    private void putDataInListCache(int launchType){
        Boolean reverse = false;
        if (launchType == 1){
            launchData = "launchDataPast";
            reverse = true;
        }else {
            launchData = "launchDataFuture";
        }
        String launchJson = sharedPreferences.getString(launchData,"");
        Type launchListType = new TypeToken<ArrayList<Launches>>(){}.getType();
        launchList = gson.fromJson(launchJson, launchListType);
        if (reverse) Collections.reverse(launchList);
        view.showList(launchList, launchType == 1);
    }

    public void getData(Boolean checkCache, final int launchType){
        if (launchType == 1){
            launchData = "launchDataPast";
        }else {
            launchData = "launchDataFuture";
        }
        sharedPreferences = LaunchActivity.getAppContext().getSharedPreferences(dataCache, MODE_PRIVATE);
        Call<List<Launches>> call;
        if (sharedPreferences.contains(launchData) && checkCache) {
            putDataInListCache(launchType);
        } else {
            view.showProgress();
            if (launchType == 1){
                call = restApi.getPastLaunches();
            }else {
                call = restApi.getFutureLaunches();
            }
            call.enqueue(new Callback<List<Launches>>() {
                @Override
                public void onResponse(Call<List<Launches>> call, Response<List<Launches>> response) {
                    launchList = response.body();
                    view.showList(launchList, launchType == 1);
                    sharedPreferences.edit()
                            .putString(launchData,gson.toJson(launchList))
                            .apply();
                }

                @Override
                public void onFailure(Call<List<Launches>> call, Throwable t) {
                    // toast load error
                    Log.d("ERROR", "Api Error");
                    if (sharedPreferences.contains(launchData)){
                        putDataInListCache(launchType);
                    }
                }
            });
        }
    }

    public List<Launches> getListLaunches(){
        return launchList;
    }

    public View.OnClickListener getRefreshButtonListenerPast(){
        return new RefreshButtonListenerPast();
    }

    public class RefreshButtonListenerPast implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            getData(false,1);
        }
    }

    public View.OnClickListener getRefreshButtonListenerFuture(){
        return new RefreshButtonListenerFuture();
    }

    public class RefreshButtonListenerFuture implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            getData(false,2);
        }
    }

    // Check connection
    public static boolean isOnline() {
        final ConnectivityManager connectivityManager = ((ConnectivityManager) LaunchActivity.getAppContext().getSystemService(Context.CONNECTIVITY_SERVICE));
        return connectivityManager.getActiveNetworkInfo() != null && connectivityManager.getActiveNetworkInfo().isConnected();
    }
}
