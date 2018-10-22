package com.cs2340.donationtracker;

import com.google.firebase.database.IgnoreExtraProperties;

@IgnoreExtraProperties
public class Donation {
    private String name;
    private String description;

    //Default constructor w/ no args is needed for firebase
    Donation() {
    }

    Donation(String name, String description) {
        this.name = name;
        this.description = description;
    }

    //Public getters are needed for firebase to accept our object
    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }
}