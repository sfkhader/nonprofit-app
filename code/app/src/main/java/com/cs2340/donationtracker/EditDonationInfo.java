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
 * Class created to represent how to edit information of donation item
 */
public class EditDonationInfo extends AppCompatActivity implements View.OnClickListener{

    private Button save;
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
        setContentView(R.layout.activity_edit_donation);

        textName = findViewById(R.id.name);
        textFullDescription = findViewById(R.id.description);
        textShortDescription = findViewById(R.id.shortDescr);
        textValue = findViewById(R.id.value);
        textCategory = findViewById(R.id.category);
        textTimeStamp = findViewById(R.id.stamp);
        textLocation = findViewById(R.id.location);

        save = findViewById(R.id.save);

        mDatabase = FirebaseDatabase.getInstance().getReference();

        save.setOnClickListener(this);

        if (!"none".equals(getIntent().getStringExtra("EXTRA_DONATION"))) {
            mDatabase.child("donations").child(getIntent().
                    getStringExtra("EXTRA_DONATION")).
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
                        public void onCancelled(@NonNull DatabaseError databaseError) {
                        }
                    });
        }
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.save) {
            Donation newDonation = new Donation (textName.getText().toString(),
                    textShortDescription.getText().toString(),
                    textFullDescription.getText().toString(),
                    textValue.getText().toString(),
                    textCategory.getText().toString(),
                    textTimeStamp.getText().toString(),
                    textLocation.getText().toString());
            mDatabase.child("donations").child(textName.getText().toString()).setValue(newDonation);
            Intent goBack = new Intent(this, DonationInfoActivity.class);
            if ("none".equals(getIntent().getStringExtra("EXTRA_DONATION"))) {
                goBack.putExtra("EXTRA_DONATION", newDonation.getName());
            } else {
                goBack.putExtra("EXTRA_DONATION", getIntent().getStringExtra("EXTRA_DONATION"));
            }

            finish();
            startActivity(goBack);
        }
    }
}
