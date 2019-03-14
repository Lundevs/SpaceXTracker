package com.example.spacextracker.Controller;
import android.content.Context;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.example.spacextracker.View.MainActivity;
import com.example.spacextracker.Model.Launches;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.lang.reflect.Type;
import java.net.InetAddress;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.content.Context.MODE_PRIVATE;

public class MainController {
    private MainActivity view;

    private List<Launches> launchList;

    private Context context;
    private SharedPreferences sharedPreferences;
    private String dataCache = "dataCache";
    private String launchData = "launchData";

    private Gson gson = new Gson();

    private SpaceXAPIInterface restApi = ApiManager.getInstance();

    public MainController(MainActivity mainActivity) {
        this.view = mainActivity;
    }

    public void onCreate() {

        //Pour ceux qui veulent aller plus loin
        //Il faut créer ces objets avec des singletons.
        // Voir le cours de Génie Logiciel -> Singleton
        //Pour ceux qui veulent encore aller plus loin
        //Voir Injection de dépendances

        getData(true);

    }

    private void putDataInListCache(){
        String launchJson = sharedPreferences.getString(launchData,"");
        Type LaunchListType = new TypeToken<ArrayList<Launches>>(){}.getType();
        launchList = gson.fromJson(launchJson, LaunchListType);
        view.showList(launchList);
    }

    public void getData(Boolean checkCache){
        sharedPreferences = MainActivity.getAppContext().getSharedPreferences(dataCache, MODE_PRIVATE);

        if (sharedPreferences.contains(launchData) && checkCache) {
            putDataInListCache();
        } else {
            view.showProgress();
            Call<List<Launches>> call = restApi.getAllLaunches();
            call.enqueue(new Callback<List<Launches>>() {
                @Override
                public void onResponse(Call<List<Launches>> call, Response<List<Launches>> response) {
                    launchList = response.body();
                    view.showList(launchList);
                    sharedPreferences.edit()
                            .putString(launchData,gson.toJson(launchList))
                            .apply();
                }

                @Override
                public void onFailure(Call<List<Launches>> call, Throwable t) {
                    // toast load error
                    Log.d("ERROR", "Api Error");
                    if (sharedPreferences.contains(launchData)){
                        putDataInListCache();
                    }
                }
            });
        }
    }

    public List<Launches> getListLaunches(){
        return launchList;
    }

    public View.OnClickListener getRefreshButtonListener(){
        return new RefreshButtonListener();
    }

    public class RefreshButtonListener implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            getData(false);
        }
    }

    // ICMP
    public static boolean isOnline() {
        final ConnectivityManager connectivityManager = ((ConnectivityManager) MainActivity.getAppContext().getSystemService(Context.CONNECTIVITY_SERVICE));
        return connectivityManager.getActiveNetworkInfo() != null && connectivityManager.getActiveNetworkInfo().isConnected();
    }
}
