package com.cs2340.donationtracker.model;

import android.support.annotation.NonNull;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

/**
 * Implements Firebase operations to retreive Donation information.
 */
public class FirebaseDonationDatabase {

    //Instance of Firebase
    private final DatabaseReference donationDatabase;

    //Up to date data snapshot of locations
    private DataSnapshot donationsData;

    /**
     * Creates a new FirebaseDonationDatabase object.
     */
    public FirebaseDonationDatabase() {
        donationDatabase = FirebaseDatabase.getInstance().getReference().child("donations");
        donationDatabase.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                donationsData = dataSnapshot;
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {}
        });
    }

    /**
     * Adds a donation to the database.
     *
     * @param donation donation to add to the database
     */
    public void addDonation(Donation donation) {
        donationDatabase.child(donation.getName()).setValue(donation);
    }

    /**
     * Retrieves all donation names in the database.
     *
     * @return list of all donation names in the database
     */
    public ArrayList<String> getDonationNames() {
        ArrayList<String> donations = new ArrayList<>();

        for (DataSnapshot childSnapshot : donationsData.getChildren()) {
            donations.add(childSnapshot.child("name").getValue().toString());
        }

        return donations;
    }

    /**
     * Retrieves all donation categories in the database.
     *
     * @return list of all donation category names in the database
     */
    public Set<String> getDonationCategories() {
        Set<String> categories = new HashSet<>();

        for (DataSnapshot childSnapshot : donationsData.getChildren()) {
            categories.add(childSnapshot.child("category").getValue().toString());
        }

        return categories;
    }

    /**
     * Searches for an item in the database based on name.
     *
     * @param donationName name of donation to search for
     * @return a list of all donations matching the search term
     */
    public ArrayList<String> searchByName(String donationName) {

        //This function can return multiple results, but as it is currently coded,
        //it will only return exact matches, so for us this means only one donation.

        ArrayList<String> results = new ArrayList<>();
        if (donationName == null) {
            return results;
        }
        for (DataSnapshot childSnapshot : donationsData.getChildren()) {
            if (childSnapshot.child("name").getValue().toString().equals(donationName)) {
                results.add(childSnapshot.child("name").getValue().toString());
            }
        }

        return  results;
    }

    /**
     * Gathers all donations in the database which fall under a specified category.
     *
     * @param donationCategory category to get items from
     * @return list of all Donations in the category
     */
    public ArrayList<String> searchByCategory(String donationCategory) {
        ArrayList<String> results = new ArrayList<>();

        for (DataSnapshot childSnapshot : donationsData.getChildren()) {
            if (childSnapshot.child("category").getValue().toString().equals(donationCategory)) {
                results.add(childSnapshot.child("name").getValue().toString());
            }
        }

        return results;
    }

    /**
     * Retrieves a donation from the database based on its name.
     *
     * @param itemName name of Donation to retrieve info for
     * @return Donation object of donation matching the given name
     */
    public Donation getDonation(String itemName) {
        return donationsData.child(itemName).getValue(Donation.class);
    }
}
