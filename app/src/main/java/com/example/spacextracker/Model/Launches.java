package com.example.spacextracker.Model;

import java.util.Date;

public class Launches {

    private Date launch_date_utc;
    private int flight_number;
    private String mission_name;
    private Rocket rocket;
    private Telem telemetry;
    private LaunchSite launch_site;
    private boolean launch_success;
    private Links links;
    private String details;

    public String getRocket_name(){return rocket.getRocket_name();}

    public String getMission_name(){
        return mission_name;
    }

    public Date getLaunch_date_utc() {
        return launch_date_utc;
    }

    public int getFlight_number(){
        return flight_number;
    }

    public String getPatchURL(){ return links.getMission_patch(); }

    public String getSmallPatchURL(){return links.getMission_patch_small();}
}
