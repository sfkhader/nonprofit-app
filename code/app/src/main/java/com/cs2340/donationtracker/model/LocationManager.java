package com.cs2340.donationtracker.model;

import com.google.firebase.database.DatabaseReference;

import java.util.List;

public class LocationManager {

    //Instance of Firebase location database
    private FirebaseLocationDatabase locationDatabase;

    public LocationManager() {
        locationDatabase = new FirebaseLocationDatabase();
    }
    public LocationManager(FirebaseLocationDatabase db) { locationDatabase = db; }

    public List<String> getLocationNames() {
        return locationDatabase.getLocationNames();
    }

    public Location getLocation(int locationID) {
        if (locationID <= 0) return null;
        else {
            Location toReturn = locationDatabase.getLocation(locationID);
            if (toReturn == null) return null;
            else return toReturn;
        }
    }
}
