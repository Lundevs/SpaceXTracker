package com.example.spacextracker;

import com.example.spacextracker.Model.Launches;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface SpaceXAPIInterface {

    @GET("launches")
    Call<List<Launches>> getAllLaunches();

    @GET("launches/{launchNumber}")
    Call<Launches> getLaunchByNumber(@Path("launchNumber") int launchNumber);

}
