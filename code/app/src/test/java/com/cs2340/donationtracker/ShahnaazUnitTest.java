package com.cs2340.donationtracker;

import android.support.annotation.NonNull;

import org.junit.Test;
import com.cs2340.donationtracker.model.FirebaseDonationDatabase;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ShahnaazUnitTest {
    private DatabaseReference database;
    private DataSnapshot donationsData;
    @Test
    public void searchAllByName_isCorrect() {
        database = FirebaseDatabase.getInstance().getReference().child("donations");
        database.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                donationsData = dataSnapshot;
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {}
        });
        FirebaseDonationDatabase test = new FirebaseDonationDatabase();
        assertEquals(test.searchAllByName("NOT A DONATION"), new ArrayList<String>());

    }
}