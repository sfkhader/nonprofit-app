package com.cs2340.donationtracker.model;

import java.util.List;

/**
 * Provides controllers a way to interact with locations.
 */
public final class LocationServiceFacade {

    //Connects this facade to the model
    private final LocationManager locationManager;

    /**
     * Creates a new LocationServiceFacade.
     */
    public LocationServiceFacade() {
        locationManager = new LocationManager();
    }

    /**
     * @return list containing names of all donation centers
     */
    public List<String> getLocationNames() {
        return locationManager.getLocationNames();
    }

    /**
     * Gets a specified location.
     *
     * @param locationID which location's data is being requested
     * @return the location object that matched the ID
     */
    public Location getLocation(int locationID) {
        return locationManager.getLocation(locationID);
    }
}
