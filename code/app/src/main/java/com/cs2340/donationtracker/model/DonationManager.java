package com.cs2340.donationtracker.model;

import java.util.ArrayList;
import java.util.Set;

public class DonationManager {

    //Instance of Firebase location database
    private final FirebaseDonationDatabase donationDatabase;

    public DonationManager() {
        donationDatabase = new FirebaseDonationDatabase();
    }
    public DonationManager(FirebaseDonationDatabase databaseReference) {
        donationDatabase = databaseReference;
    }

    public void addDonation(String name, String shortDescription, String fullDescription,
                            String value, String category, String timeStamp, String location) {

        donationDatabase.addDonation(new Donation (name, shortDescription, fullDescription,
                value, category, timeStamp, location));
    }

    public ArrayList<String> getDonationNames() {

        ArrayList<String> returnedList = donationDatabase.getDonationNames();
        if (returnedList.isEmpty()) {
            throw new IllegalStateException("Nothing Found");
        } else {
            return returnedList;
        }
    }

    public Set<String> getDonationCategories() {
        return donationDatabase.getDonationCategories();
    }

    public ArrayList<String> searchByName(String donationName) {
        if (donationName == null) {
            throw new IllegalArgumentException("Null parameter received.");
        }
        if ("".equals(donationName)) {
            throw new IllegalArgumentException("Empty string passed as parameter.");
        }
        return donationDatabase.searchByName(donationName);
    }

    public ArrayList<String> searchByCategory(String donationCategory) {
        if (donationCategory == null) {
            throw new IllegalArgumentException("Null parameter received.");
        }
        if ("".equals(donationCategory)) {
            throw new IllegalArgumentException("Empty string passed as parameter.");
        }
        return donationDatabase.searchByCategory(donationCategory);
    }

    public Donation getDonation(String donationName) {
        if (donationName == null) {
            throw new IllegalArgumentException("Null parameter received.");
        }
        if ("".equals(donationName)) {
            throw new IllegalArgumentException("Empty string passed as parameter.");
        }

        return donationDatabase.getDonation(donationName);
    }
}
