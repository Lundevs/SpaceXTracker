package com.example.spacextracker.Controller;

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

    @GET("launches/past")
    Call<List<Launches>> getPastLaunches();

    @GET("launches/upcoming")
    Call<List<Launches>> getFutureLaunches();



}
