package com.cs2340.donationtracker.model;

import java.util.List;

/**
 * Delegates calls and information handling between location database
 */
public class LocationManager {

    //Instance of Firebase location database
    private final FirebaseLocationDatabase locationDatabase;

    /**
     * Creates a default LocationManager object
     */
    public LocationManager() {
        locationDatabase = new FirebaseLocationDatabase();
    }

    /**
     * Creates a LocationManager object with a specified database (for testing purposes)
     *
     * @param db database to link to
     */
    public LocationManager(FirebaseLocationDatabase db) { locationDatabase = db; }

    /**
     * @return a list of all location names in the system
     */
    public List<String> getLocationNames() {
        return locationDatabase.getLocationNames();
    }

    /**
     * Location lookup based on ID.
     *
     * @param locationID id of the location
     * @return Location object of location that matches given ID
     */
    public Location getLocation(int locationID) {
        if (locationID <= 0) {
            return null;
        } else {
            Location toReturn = locationDatabase.getLocation(locationID);
            if (toReturn == null) {
                return null;
            } else {
                return toReturn;
            }
        }
    }
}
