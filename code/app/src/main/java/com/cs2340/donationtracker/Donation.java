package com.cs2340.donationtracker;

import com.google.firebase.database.IgnoreExtraProperties;

@IgnoreExtraProperties
public class Donation {
    private String name;
    private String shortDescription;
    private String fullDescription;
    private String value;
    private String category;
    private String timeStamp;
    private String location;

    //Default constructor w/ no args is needed for firebase
    Donation() {
    }

    Donation(String name, String shortDescription, String fullDescription,
             String value, String category, String timeStamp, String location) {
        this.name = name;
        this.shortDescription = shortDescription;
        this.fullDescription = fullDescription;
        this.value = value;
        this.category = category;
        this.timeStamp = timeStamp;
        this.location = location;
    }

    //Public getters are needed for firebase to accept our object
    public String getName() {
        return name;
    }
    public String getShortDescription() { return shortDescription; }
    public String getFullDescription() { return fullDescription; }
    public String getValue() { return value; }
    public String getCategory() { return category; }
    public String getTimeStamp() { return timeStamp; }
    public String getLocation() { return location; }
}