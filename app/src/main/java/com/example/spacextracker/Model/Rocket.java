package com.example.spacextracker.Model;

public class Rocket {

    private String rocket_id;
    private String rocket_name;
    private FirstStage first_stage;
    private SecondStage second_stage;

    public String getRocket_id() {
        return rocket_id;
    }

    public String getRocket_name() {
        return rocket_name;
    }

    public FirstStage getFirst_stage() {
        return first_stage;
    }

    public SecondStage getSecond_stage() {
        return second_stage;
    }
}
