package com.example.spacextracker.Model;

import java.util.Date;

public class Launches {

    private Date launch_date_utc;
    private int flight_number;
    private String mission_name;
    private Rocket rocket;

    public String getMission_name(){
        return mission_name;
    }

    public Date getLaunch_date_utc() {
        return launch_date_utc;
    }

    public int getFlight_number(){
        return flight_number;
    }


}
