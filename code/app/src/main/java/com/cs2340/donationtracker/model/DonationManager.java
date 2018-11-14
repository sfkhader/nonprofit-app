package com.cs2340.donationtracker.model;

import java.util.ArrayList;
import java.util.Set;

class DonationManager {

    //Instance of Firebase location database
    private final FirebaseDonationDatabase donationDatabase;

    public DonationManager() {
        donationDatabase = new FirebaseDonationDatabase();
    }

    public void addDonation(String name, String shortDescription, String fullDescription,
                            String value, String category, String timeStamp, String location) {

        donationDatabase.addDonation(new Donation (name, shortDescription, fullDescription,
                value, category, timeStamp, location));
    }

    public ArrayList<String> getDonationNames() {
        return donationDatabase.getDonationNames();
    }

    public Set<String> getDonationCategories() {
        return donationDatabase.getDonationCategories();
    }

    public ArrayList<String> searchAllByName(String donationName) {
        return donationDatabase.searchAllByName(donationName);
    }

    public ArrayList<String> searchByCategory(String donationCategory) {
        return donationDatabase.searchByCategory(donationCategory);
    }

    public Donation getDonation(String donationName) {
        return donationDatabase.getDonation(donationName);
    }
}
