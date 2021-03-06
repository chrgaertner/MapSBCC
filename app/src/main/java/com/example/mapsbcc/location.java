package com.example.mapsbcc;

public class location {
    // Initialize variables
    String id, address, lat, lon;

    // Initialize constructor
    public location() {
    }

    // Constructor with variables as parameters and assigns the value to local variables
    public location(String id, String address, String lat, String lon) {
        this.id = id;
        this.address = address;
        this.lat = lat;
        this.lon = lon;
    }

    // Getters and setters created to retrieve and assign values from our location class.
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getLat() {
        return lat;
    }

    public void setLat(String lat) {
        this.lat = lat;
    }

    public String getLon() {
        return lon;
    }

    public void setLon(String lon) {
        this.lon = lon;
    }
}
