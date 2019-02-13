package com.example.spacextracker;

import java.util.Date;

public class Launches {

    private String flight_id;
    private Date launch_date_utc;
    private int flight_number;
    private String rocket_id;
    private String rocket_name;
    private boolean landing_intent;
    private boolean landing_type;
    private String site_id;
    private String site_name;
    private String payload_type;

    public String getFlight_id() {
        return flight_id;
    }

    public Date getLaunch_date_utc() {
        return launch_date_utc;
    }

    public int getFlight_number() {
        return flight_number;
    }

    public String getRocket_id() {
        return rocket_id;
    }

    public String getRocket_name() {
        return rocket_name;
    }

    public boolean isLanding_intent() {
        return landing_intent;
    }

    public boolean isLanding_type() {
        return landing_type;
    }

    public String getSite_id() {
        return site_id;
    }

    public String getSite_name() {
        return site_name;
    }

    public String getPayload_type() {
        return payload_type;
    }
}
