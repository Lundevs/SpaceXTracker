package com.example.spacextracker.Controller;
import android.util.Log;

import com.example.spacextracker.View.MainActivity;
import com.example.spacextracker.Model.Launches;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainController {
    private MainActivity view;

    private List<Launches> launchList;

    public MainController(MainActivity mainActivity) {
        this.view = mainActivity;
    }

    public void onCreate() {

        //Pour ceux qui veulent aller plus loin
        //Il faut créer ces objets avec des singletons.
        // Voir le cours de Génie Logiciel -> Singleton
        //Pour ceux qui veulent encore aller plus loin
        //Voir Injection de dépendances

        SpaceXAPIInterface restApi = ApiManager.getInstance();
        Call<List<Launches>> call = restApi.getAllLaunches();
        call.enqueue(new Callback<List<Launches>>() {
            @Override
            public void onResponse(Call<List<Launches>> call, Response<List<Launches>> response) {
                launchList = response.body();
                view.showList(launchList);
            }

            @Override
            public void onFailure(Call<List<Launches>> call, Throwable t) {
                Log.d("ERROR", "Api Error");
            }
        });

    }

    public List<Launches> getListLaunches(){
        return launchList;
    }
}
