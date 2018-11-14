package com.cs2340.donationtracker.model;

import java.util.List;

class LocationManager {

    //Instance of Firebase location database
    private final FirebaseLocationDatabase locationDatabase;

    public LocationManager() {
        locationDatabase = new FirebaseLocationDatabase();
    }

    public List<String> getLocationNames() {
        return locationDatabase.getLocationNames();
    }

    public Location getLocation(int locationID) {
        return locationDatabase.getLocation(locationID);
    }
}
