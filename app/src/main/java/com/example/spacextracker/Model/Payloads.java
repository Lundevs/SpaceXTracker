package com.example.spacextracker.Model;

class Payloads {
    private String payload_id;
    private String nationality;
    private String payload_type;
    private String payload_mass_kg;
    private Orbit_params orbit_params;

    public String getPayload_id() {
        return payload_id;
    }

    public String getNationality() {
        return nationality;
    }

    public String getPayload_type() {
        return payload_type;
    }

    public String getPayload_mass_kg() {
        return payload_mass_kg;
    }

    public Orbit_params getOrbit_params() {
        return orbit_params;
    }
}
