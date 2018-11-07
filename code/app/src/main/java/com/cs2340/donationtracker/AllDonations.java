package com.cs2340.donationtracker;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Button;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;


public class AllDonations extends AppCompatActivity implements View.OnClickListener{
    Button donationInfo;
    Button addDonationButton;
    Spinner donations;

    DatabaseReference mDatabase;
    List<String> donationsList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_donations);

        donationInfo = (Button) findViewById(R.id.donationInfo);
        addDonationButton = (Button) findViewById(R.id.addDonation);
        donations = (Spinner) findViewById(R.id.spinner);

        mDatabase = FirebaseDatabase.getInstance().getReference();
        donationsList = new ArrayList<>();
        mDatabase.child("donations").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot childSnapshot: dataSnapshot.getChildren()) {
                    addToList(childSnapshot.child("name").getValue().toString());
                }
                finishInit();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {}
        });
    }

    /**
     * arranges the donations in the spinner
     */
    public void finishInit () {
        String[] locationsArray = donationsList.toArray(new String[donationsList.size()]);

        donations.setAdapter(new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item,
                locationsArray));

        donationInfo.setOnClickListener(this);
        addDonationButton.setOnClickListener(this);
    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.donationInfo) {
            Intent items = new Intent(this, DonationInfoActivity.class);
            items.putExtra("EXTRA_DONATION", donations.getSelectedItem().toString());
            startActivity(items);
            return;
        }
        if (v.getId() == R.id.addDonation) {
            Intent addDonation = new Intent(this, EditDonationInfo.class);
            addDonation.putExtra("EXTRA_DONATION", "none");
            startActivity(addDonation);
        }
    }

    /**
     * adds a donation to the list
     * @param data
     */
    public void addToList(String data) {
        donationsList.add(data);
    }
}
