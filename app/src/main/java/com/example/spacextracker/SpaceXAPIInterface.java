package com.example.spacextracker;

import com.example.spacextracker.Model.Launches;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface SpaceXAPIInterface {

    @GET("launches")
    Call<List<Launches>> getAllLaunches();

}
