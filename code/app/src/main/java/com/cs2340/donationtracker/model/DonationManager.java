package com.cs2340.donationtracker.model;

import java.util.ArrayList;
import java.util.Set;

/**
 * Delegates calls and information handling between donation database
 */
public class DonationManager {

    //Instance of Firebase location database
    private final FirebaseDonationDatabase donationDatabase;

    /**
     * Creates a default DonationManager.
     */
    public DonationManager() {
        donationDatabase = new FirebaseDonationDatabase();
    }

    /**
     * Creates a donation manager with a given database reference (for testing purposes)
     * @param databaseReference the database to link to
     */
    public DonationManager(FirebaseDonationDatabase databaseReference) {
        donationDatabase = databaseReference;
    }

    /**
     * Adds a new donation to the system.
     *
     * @param name name of donation
     * @param shortDescription short description of item
     * @param fullDescription full description of item
     * @param value monetary value of item
     * @param category where this item belongs categorically
     * @param timeStamp when this item arrived
     * @param location where this item arrived
     */
    public void addDonation(String name, String shortDescription, String fullDescription,
                            String value, String category, String timeStamp, String location) {

        donationDatabase.addDonation(new Donation (name, shortDescription, fullDescription,
                value, category, timeStamp, location));
    }

    /**
     * @return a list of all donation names in the system
     */
    public ArrayList<String> getDonationNames() {

        ArrayList<String> returnedList = donationDatabase.getDonationNames();
        if (returnedList.isEmpty()) {
            throw new IllegalStateException("Nothing Found");
        } else {
            return returnedList;
        }
    }

    /**
     * @return a set of all category names in the system
     */
    public Set<String> getDonationCategories() {
        return donationDatabase.getDonationCategories();
    }

    /**
     * Looks through the system for a specific donation.
     *
     * @param donationName name of donation to search for
     * @return a list of donation names which match the search
     */
    public ArrayList<String> searchByName(String donationName) {
        if (donationName == null) {
            throw new IllegalArgumentException("Null parameter received.");
        }
        if ("".equals(donationName)) {
            throw new IllegalArgumentException("Empty string passed as parameter.");
        }
        return donationDatabase.searchByName(donationName);
    }

    /**
     * Looks through the system for a specific donation based on category.
     *
     * @param donationCategory category to search for items in
     * @return list of items which are in the category
     */
    public ArrayList<String> searchByCategory(String donationCategory) {
        if (donationCategory == null) {
            throw new IllegalArgumentException("Null parameter received.");
        }
        if ("".equals(donationCategory)) {
            throw new IllegalArgumentException("Empty string passed as parameter.");
        }
        return donationDatabase.searchByCategory(donationCategory);
    }

    /**
     * @param donationName name of donation to retreive
     * @return Donation object of donation matching the specified name
     */
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
