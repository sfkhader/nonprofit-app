package com.cs2340.donationtracker;

import com.google.firebase.database.IgnoreExtraProperties;


@IgnoreExtraProperties
/**
 * Class created to represent all the information regarding a donation item
 */
public class Donation {
    private String name;
    private String shortDescription;
    private String fullDescription;
    private String value;
    private String category;
    private String timeStamp;
    private String location;

    /**
     * constructor for a donation item
     */
    Donation() {
    }

    /**
     * Donation constructor
     * @param name name
     * @param shortDescription the short description
     * @param fullDescription the full description
     * @param value value
     * @param category category
     * @param timeStamp timestamp
     * @param location location
     */
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
     * the inputted name
     * @return name the inputted name
     */
    public String getName() {
        return name;
    }
    /**
     * the inputted shortDescription
     * @return shortDescription
     */
    public String getShortDescription() { return shortDescription; }
    /**
     * the inputted fullDescription
     * @return fullDescription
     */
    public String getFullDescription() { return fullDescription; }
    /**
     * the inputted value
     * @return value
     */
    public String getValue() { return value; }
    /**
     * the inputted category
     * @return category
     */
    public String getCategory() { return category; }
    /**
     *
     * @return timeStamp
     */
    public String getTimeStamp() { return timeStamp; }
    /**
     * the inputted location
     * @return location
     */
    public String getLocation() { return location; }
}