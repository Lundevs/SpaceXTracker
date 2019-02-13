package com.example.spacextracker;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetroFit {
    public static final String BASE_URL = "https://api.spacexdata.com/v3/";

    public static SpaceXAPIInterface getInstance(){
        Gson gson = new GsonBuilder()
                .setLenient()
                .create();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();

        SpaceXAPIInterface spaceXAPIInterface = retrofit.create(SpaceXAPIInterface.class);
        return spaceXAPIInterface;
    }

}
