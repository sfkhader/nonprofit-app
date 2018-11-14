package com.cs2340.donationtracker.model;

import java.util.ArrayList;
import java.util.Set;

/**
 * Singleton which connects activities to model for Donation services.
 */
public final class DonationServiceFacade {

    //Connects this facade to the model
    private final DonationManager donationManager;

    /**
     * Creates a new DonationServiceFacade.
     */
    public DonationServiceFacade() {
        donationManager = new DonationManager();
    }

    /**
     * Add a donation to the donations database.
     *
     * @param name donation name
     * @param shortDescription short description of donation
     * @param fullDescription full description of donation
     * @param value cost of donation
     * @param category category of donation
     * @param timeStamp when donation arrived
     * @param location where donation is located
     */
    public void addDonation(String name, String shortDescription, String fullDescription,
                            String value, String category, String timeStamp, String location) {

        donationManager.addDonation(name, shortDescription, fullDescription,
                value, category, timeStamp, location);
    }

    /**
     * @return names of all donations in database
     */
    public ArrayList<String> getDonationNames() {
        return donationManager.getDonationNames();
    }

    /**
     * @return list of all categories for donations
     */
    public Set<String> getDonationCategories() {
        return donationManager.getDonationCategories();
    }


    /**
     * @param donationName name of donation to retrieve info
     * @return requested donation object, or null if doesn't exist
     */
    public Donation getDonation(String donationName) {
        return donationManager.getDonation(donationName);
    }
}
