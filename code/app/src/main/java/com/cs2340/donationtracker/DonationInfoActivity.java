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
    TextView textDescription;

    DatabaseReference mDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_donation_information);


        textName = findViewById(R.id.name);
        textDescription = findViewById(R.id.description);

        mDatabase = FirebaseDatabase.getInstance().getReference();

        edit.setOnClickListener(this);

        mDatabase.child("donations").child(Integer.toString(getIntent().
                getIntExtra("EXTRA_DONATION", 0))).
                addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                textName.setText(dataSnapshot.child("name").getValue().toString());
                textDescription.setText(dataSnapshot.child("description").getValue().toString());
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {}
        });
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.edit) {
            Intent editDonation = new Intent(this, EditDonationInfo.class);
            startActivity(editDonation);
            return;
        }
    }
}
