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
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;


public class DonationInfoActivity extends AppCompatActivity implements View.OnClickListener{

    Button edit;
    TextView textName;
    TextView textShortDescription;
    TextView textFullDescription;
    TextView textValue;
    TextView textCategory;
    TextView textTimeStamp;
    TextView textLocation;

    DatabaseReference mDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_donation_information);


        textName = findViewById(R.id.name);
        textShortDescription = findViewById(R.id.shortDescription);
        textFullDescription = findViewById(R.id.fullDescription);
        textValue = findViewById(R.id.value);
        textCategory = findViewById(R.id.category);
        textTimeStamp = findViewById(R.id.timeStamp);
        textLocation = findViewById(R.id.location);
        edit = findViewById(R.id.edit);

        mDatabase = FirebaseDatabase.getInstance().getReference();

        edit.setOnClickListener(this);

        mDatabase.child("donations").child(getIntent().getStringExtra("EXTRA_DONATION")).
                addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                textName.setText(dataSnapshot.child("name").getValue().toString());
                textShortDescription.setText(dataSnapshot.child("shortDescription").getValue().toString());
                textFullDescription.setText(dataSnapshot.child("fullDescription").getValue().toString());
                textValue.setText(dataSnapshot.child("value").getValue().toString());
                textCategory.setText(dataSnapshot.child("category").getValue().toString());
                textTimeStamp.setText(dataSnapshot.child("timeStamp").getValue().toString());
                textLocation.setText(dataSnapshot.child("location").getValue().toString());
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {}
        });
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.edit) {
            Intent editDonation = new Intent(this, EditDonationInfo.class);
            editDonation.putExtra("EXTRA_DONATION", getIntent().getStringExtra("EXTRA_DONATION"));
            startActivity(editDonation);
            return;
        }
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(getApplicationContext(), AllDonations.class);
        intent.putExtra("EXTRA_DONATION", getIntent().getStringExtra("EXTRA_DONATION"));
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
    }
}
