package com.cs2340.donationtracker.model;

import com.google.firebase.database.IgnoreExtraProperties;

/**
 * Information holder for Donations.
 */
@IgnoreExtraProperties
public class Donation {
    private String name;
    private String shortDescription;
    private String fullDescription;
    private String value;
    private String category;
    private String timeStamp;
    private String location;

    /**
     * Default constructor, needed for Firebase to accept
     */
    public Donation() {
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

    /**
     * @return name of donation
     */
    public String getName() {
        return name;
    }

    /**
     * @return short description of donation
     */
    public String getShortDescription() { return shortDescription; }

    /**
     * @return full description of donation
     */
    public String getFullDescription() { return fullDescription; }

    /**
     * @return value of donation
     */
    public String getValue() { return value; }

    /**
     * @return category of donation
     */
    public String getCategory() { return category; }

    /**
     * @return time stamp of donation
     */
    public String getTimeStamp() { return timeStamp; }

    /**
     * @return location where donation resides
     */
    public String getLocation() { return location; }
}