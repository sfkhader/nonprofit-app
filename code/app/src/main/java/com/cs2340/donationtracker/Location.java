package com.cs2340.donationtracker;

import com.google.firebase.database.IgnoreExtraProperties;

@IgnoreExtraProperties
public class Location {
    private String name;
    private String latitude;
    private String longitude;
    private String streetAddress;
    private String city;
    private String state;
    private String zip;
    private String type;
    private String phone;
    private String website;

    //Default constructor w/ no args is needed for firebase
    Location() {
    }

    Location(String name, String latitude, String longitude,
             String streetAddress, String city, String state, String zip,
             String type, String phone, String website) {
        this.name = name;
        this.latitude = latitude;
        this.longitude = longitude;
        this.streetAddress = streetAddress;
        this.city = city;
        this.state = state;
        this.zip = zip;
        this.type = type;
        this.phone = phone;
        this.website = website;
    }

    //Public getters are needed for firebase to accept our object
    public String getName() {
        return name;
    }
    public String getLatitude() {
        return latitude;
    }
    public String getLongitude() {
        return longitude;
    }
    public String getStreetAddress() {
        return streetAddress;
    }
    public String getCity() {
        return city;
    }
    public String getState() {
        return state;
    }
    public String getZip() {
        return zip;
    }
    public String getType() {
        return type;
    }
    public String getPhone() {
        return phone;
    }
    public String getWebsite() {
        return website;
    }
}