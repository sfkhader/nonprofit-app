package com.cs2340.donationtracker;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;


public class LocationInfoActivity extends AppCompatActivity implements View.OnClickListener{

    Button donations;
    TextView textName;
    TextView textType;
    TextView textLongitude;
    TextView textLatitude;
    TextView textPhone;
    TextView textAddress;

    DatabaseReference mDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_location_information);

        donations = findViewById(R.id.donations);
        textName = findViewById(R.id.locationName2);
        textType = findViewById(R.id.locationType);
        textLongitude = findViewById(R.id.longitude);
        textLatitude = findViewById(R.id.latitude);
        textPhone = findViewById(R.id.phone);
        textAddress = findViewById(R.id.address);

        donations.setOnClickListener(this);

        mDatabase = FirebaseDatabase.getInstance().getReference();

        mDatabase.child("locations").child(Integer.toString(getIntent().
                getIntExtra("EXTRA_LOCATION", 0))).
                addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                textName.setText(dataSnapshot.child("name").getValue().toString());
                textType.setText(dataSnapshot.child("type").getValue().toString());
                textLongitude.setText(dataSnapshot.child("longitude").getValue().toString());
                textLatitude.setText(dataSnapshot.child("latitude").getValue().toString());
                textPhone.setText(dataSnapshot.child("phone").getValue().toString());
                textAddress.setText(dataSnapshot.child("streetAddress").getValue().toString());

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {}
        });
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.donations) {
            Intent donationPage = new Intent(this, AllDonations.class);
            startActivity(donationPage);
        }
    }
}
