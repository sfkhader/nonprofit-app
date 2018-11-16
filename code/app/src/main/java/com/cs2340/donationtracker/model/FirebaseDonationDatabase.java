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

public class FirebaseDonationDatabase {

    //Instance of Firebase
    private final DatabaseReference donationDatabase;

    //Up to date data snapshot of locations
    private DataSnapshot donationsData;

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

    public void addDonation(Donation donation) {
        donationDatabase.child(donation.getName()).setValue(donation);
    }

    public ArrayList<String> getDonationNames() {
        ArrayList<String> donations = new ArrayList<>();

        for (DataSnapshot childSnapshot : donationsData.getChildren()) {
            donations.add(childSnapshot.child("name").getValue().toString());
        }

        return donations;
    }

    public Set<String> getDonationCategories() {
        Set<String> categories = new HashSet<>();

        for (DataSnapshot childSnapshot : donationsData.getChildren()) {
            categories.add(childSnapshot.child("category").getValue().toString());
        }

        return categories;
    }

    public ArrayList<String> searchAllByName(String donationName) {

        //This function can return multiple results, but as it is currently coded,
        //it will only return exact matches, so for us this means only one donation.

        ArrayList<String> results = new ArrayList<>();
        if (donationName == null) return results;
        for (DataSnapshot childSnapshot : donationsData.getChildren()) {
            if (childSnapshot.child("name").getValue().toString().equals(donationName)) {
                results.add(childSnapshot.child("name").getValue().toString());
            }
        }

        return  results;
    }

    public ArrayList<String> searchByCategory(String donationCategory) {
        ArrayList<String> results = new ArrayList<>();

        for (DataSnapshot childSnapshot : donationsData.getChildren()) {
            if (childSnapshot.child("category").getValue().toString().equals(donationCategory)) {
                results.add(childSnapshot.child("name").getValue().toString());
            }
        }

        return results;
    }

    public Donation getDonation(String itemName) {
        return donationsData.child(itemName).getValue(Donation.class);
    }
}
