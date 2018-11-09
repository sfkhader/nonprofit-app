package com.cs2340.donationtracker;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.content.Intent;
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
import java.util.Objects;

/**
 * Class created to display all the locations
 */
public class AllLocations extends AppCompatActivity implements View.OnClickListener{
    private Button information;
    private Spinner locations;

    private DatabaseReference mDatabase;
    private List<String> locationsList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_locations);

        information = findViewById(R.id.information);
        locations = findViewById(R.id.spinner);



        mDatabase = FirebaseDatabase.getInstance().getReference();
        locationsList = new ArrayList<>();
        mDatabase.child("locations").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot childSnapshot: dataSnapshot.getChildren()) {
                    addToList(Objects.requireNonNull(childSnapshot.child("name").getValue()).toString());
                }
                finishInit();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {}
        });
    }

    /**
     * arranges the spinners to have locations
     */
    private void finishInit() {
        String[] locationsArray = locationsList.toArray(new String[locationsList.size()]);

        locations.setAdapter(new ArrayAdapter<>(this,
                android.R.layout.simple_spinner_item,
                locationsArray));

        information.setOnClickListener(this);
    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.information) {
            Intent locationInfo = new Intent(this, LocationInfoActivity.class);
            locationInfo.putExtra("EXTRA_LOCATION", locations.getSelectedItemPosition() + 1);
            startActivity(locationInfo);
        }
    }

    /**
     *
     * @param data takes in the data :)
     */
    private void addToList(String data) {
        locationsList.add(data);
    }
}
