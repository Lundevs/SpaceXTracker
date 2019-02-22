package com.example.spacextracker.Model;

class Core {
    private String flight;
    private int block;
    private boolean reused;
    private boolean landing_intent;
    private boolean isLanding_success;

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
