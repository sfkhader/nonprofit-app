package com.cs2340.donationtracker;

import android.provider.ContactsContract;

import java.util.List;


public class DataServiceFacade {
    private static DataServiceFacade INSTANCE = new DataServiceFacade();
    public static DataServiceFacade getInstance() { return INSTANCE; }

    private DataManager theData = new DataManager();

    private Location theLastAddedElement;


    private DataServiceFacade() {

    }

    /**
     * get a list of all the data
     * @return  the full list of data
     */
    public List<Location> getData() { return theData.getData();}

    /**
     * Add a new data element to the model
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
     */
    public void addDataElement(String name, double latitude, double longitude, String streetAddress, String city, String state, String zip, String type, String phone, String website) {
        Location loc = new Location(name, latitude, longitude, streetAddress, city, state, zip, type, phone,website);
        theData.addReport(loc);
        theLastAddedElement = loc;
    }

    public void addDataElement(Location loc) {
        theData.addReport(loc);
        theLastAddedElement = loc;

    }
    /**
     * Return the last element added.  This method is mainly to support UI
     * @return the last element added to the model.
     */
    public Location getLastElementAdded() {
        return theLastAddedElement;
    }
}