package com.example.spacextracker;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface SpaceXAPIInterface {

    @GET("launches")
    Call<List<Launches>> getAllLaunchs();

}
