package com.cs2340.donationtracker.model;

import com.google.firebase.database.IgnoreExtraProperties;

/**
 * Class created to represent all the information regarding a location
 */
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

    /**
     * Location Constructor
     */
    Location() {
    }

    /**
     *  Location constructor
     * @param name name
     * @param latitude latitude
     * @param longitude longitude
     * @param streetAddress streetAddress
     * @param city city
     * @param state state
     * @param zip zip
     * @param type type
     * @param phone phone
     * @param website website
     *
     */
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

    /**
     *
     * @return name
     */
    public String getName() {
        return name;
    }

    /**
     *
     * @return latitude
     */
    public String getLatitude() {
        return latitude;
    }

    /**
     *
     * @return longitude
     */
    public String getLongitude() {
        return longitude;
    }

    /**
     *
     * @return streetAddress
     */
    public String getStreetAddress() {
        return streetAddress;
    }

    /**
     *
     * @return city
     */
    public String getCity() {
        return city;
    }

    /**
     *
     * @return state
     */
    public String getState() {
        return state;
    }

    /**
     *
     * @return zip
     */
    public String getZip() {
        return zip;
    }

    /**
     *
     * @return type
     */
    public String getType() {
        return type;
    }

    /**
     *
     * @return phone
     */
    public String getPhone() {
        return phone;
    }

    /**
     *
     * @return website
     */
    public String getWebsite() {
        return website;
    }
}