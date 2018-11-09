package com.cs2340.donationtracker;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.content.Intent;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
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
 * Class created to represent the activity of searching for items
 */
public class SearchPage extends AppCompatActivity implements View.OnClickListener {

    private Spinner selectLocation;
    private Spinner selectItem;
    private EditText searchText;
    private Button bSearch;
    private Button bBack;

    private DatabaseReference mDatabase;
    private List<String> locationsList;
    private List<String> itemsList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_page);

        selectItem = findViewById(R.id.selectItem);
        selectLocation = findViewById(R.id.selectLocation);
        searchText = findViewById(R.id.searchText);
        bSearch = findViewById(R.id.bSearch);
        bBack = findViewById(R.id.bBack);
        bSearch.setOnClickListener(this);
        bBack.setOnClickListener(this);

        mDatabase = FirebaseDatabase.getInstance().getReference();
        //filteredItemsList = new ArrayList<>();
        itemsList = new ArrayList<>();
        mDatabase.child("donations").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot childSnapshot: dataSnapshot.getChildren()) {
                    if(!itemsList.contains(Objects.requireNonNull(childSnapshot.child("category").getValue()).toString())){
                        addItemToList(Objects.requireNonNull(childSnapshot.child("category").getValue()).toString());
                    }
                }
                finishInit();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {}
        });
        locationsList = new ArrayList<>();
        mDatabase.child("locations").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                addLocToList("All Locations");
                for (DataSnapshot childSnapshot: dataSnapshot.getChildren()) {
                    addLocToList(Objects.requireNonNull(childSnapshot.child("name").getValue()).toString());
                }
                finishInit();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {}
        });
    }

    //Function called once all locations have actually loaded from Firebase

    /**
     * arranges the spinners on the search page
     */
    private void finishInit() {
        String[] locationsArray = locationsList.toArray(new String[locationsList.size()]);
        String[] itemsArray = itemsList.toArray(new String[itemsList.size()]);

        ArrayAdapter<String> locAdapter = new ArrayAdapter<>(this,
                android.R.layout.simple_spinner_item,
                locationsArray);
        ArrayAdapter<String> itemAdapter = new ArrayAdapter<>(this,
                android.R.layout.simple_spinner_item,
                itemsArray);

        selectLocation.setAdapter(locAdapter);
        selectItem.setAdapter(itemAdapter);
        bSearch.setOnClickListener(this);
    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {
    }
    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.bSearch) {
            Intent toResults = new Intent(this, Search_Results.class);
            Bundle extras = new Bundle();
            int locSpinner = selectLocation.getSelectedItemPosition();
            int itemSpinner = selectItem.getSelectedItemPosition();
            extras.putString("EXTRA_LOCATION", (selectLocation.getItemAtPosition(locSpinner)).toString());
            extras.putString("EXTRA_ITEM", (selectItem.getItemAtPosition(itemSpinner)).toString());
            extras.putString("EXTRA_TEXT", searchText.getText().toString());
            toResults.putExtras(extras);
            startActivity(toResults);
        } else if (v.getId() == R.id.bBack) {
            Intent goBack = new Intent(this, AppActivity.class);
            startActivity(goBack);

        }
    }

    /**
     * adds data to location list
     * @param data all locations
     */
    private void addLocToList(String data) {
        locationsList.add(data);
    }

    /**
     *  adds an item
     * @param data all items
     */
    private void addItemToList(String data) {
        itemsList.add(data);
    }
}


