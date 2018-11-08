package com.cs2340.donationtracker;

import java.util.ArrayList;
import java.util.List;


class DataManager {
    List<Location> locationList;

    DataManager() {
        locationList = new ArrayList<>();
        makeSomeData();
    }

    private void makeSomeData() {
        addReport(new Location("AFD Station 4", 33.75416, -84.37742, "309 EDGEWOOD AVE SE", "Atlanta", "Georgia", "30332", "Drop Off", "(404)-555-3456", "www.afd04.atl.ga"));
        //addReport(new Location("Pepsi", "Grandma Garage", new Location(33.8, -84.5)));
    }

    void addReport(Location loc) {
        locationList.add(loc);
    }


    List<Location> getData() { return locationList; }



}
