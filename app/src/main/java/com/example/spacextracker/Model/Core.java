package com.example.spacextracker.Model;

class Core {
    public String flight;
    public int block;
    public boolean reused;
    public boolean landing_intent;
    public boolean isLanding_success;

    public String getFlight() {
        return flight;
    }

    public int getBlock() {
        return block;
    }

    public boolean isReused() {
        return reused;
    }

    public boolean isLanding_intent() {
        return landing_intent;
    }

    public boolean isLanding_success() {
        return isLanding_success;
    }
}
