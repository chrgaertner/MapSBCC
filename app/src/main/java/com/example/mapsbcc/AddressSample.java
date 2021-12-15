package com.example.mapsbcc;

public class AddressSample {
    private String address;
    private String lat;
    private String lon;



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

    @Override
    public String toString() {
        return address;
    }

    public String FullInformationtoString() {
        return "AddressSample{" +
                "address='" + address + '\'' +
                ", lat=" + lat +
                ", lon=" + lon +
                '}';
    }

    public AddressSample()
    {

    }

    public AddressSample (String adr, String lat, String lon){
        this.address = adr;
        this.lat = lat;
        this.lon = lon;
    }
}
