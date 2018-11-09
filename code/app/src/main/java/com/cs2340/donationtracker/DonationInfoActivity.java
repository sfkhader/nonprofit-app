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

import java.util.Objects;

/**
 * Class created to display the information of each donation item created
 */
public class DonationInfoActivity extends AppCompatActivity implements View.OnClickListener{

    private Button edit;
    private TextView textName;
    private TextView textShortDescription;
    private TextView textFullDescription;
    private TextView textValue;
    private TextView textCategory;
    private TextView textTimeStamp;
    private TextView textLocation;

    private DatabaseReference mDatabase;

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
                textName.setText(Objects.requireNonNull(dataSnapshot.child("name").getValue()).toString());
                textShortDescription.setText(Objects.requireNonNull(dataSnapshot.child("shortDescription").getValue()).toString());
                textFullDescription.setText(Objects.requireNonNull(dataSnapshot.child("fullDescription").getValue()).toString());
                textValue.setText(Objects.requireNonNull(dataSnapshot.child("value").getValue()).toString());
                textCategory.setText(Objects.requireNonNull(dataSnapshot.child("category").getValue()).toString());
                textTimeStamp.setText(Objects.requireNonNull(dataSnapshot.child("timeStamp").getValue()).toString());
                textLocation.setText(Objects.requireNonNull(dataSnapshot.child("location").getValue()).toString());
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
