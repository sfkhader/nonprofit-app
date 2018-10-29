package com.cs2340.donationtracker;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.content.Intent;
import android.util.Log;
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

public class SearchPage extends AppCompatActivity implements View.OnClickListener {

    Spinner selectLocation;
    Spinner selectItem;
    EditText searchText;
    Button bSearch;
    Button bBack;

    DatabaseReference mDatabase;
    List<String> locationsList;
    List<String> itemsList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_page);

        selectItem = (Spinner) findViewById(R.id.selectItem);
        selectLocation = (Spinner) findViewById(R.id.selectLocation);
        searchText = (EditText) findViewById(R.id.searchText);
        bSearch = (Button) findViewById(R.id.bSearch);
        bBack = (Button) findViewById(R.id.bBack);
        bSearch.setOnClickListener(this);
        bBack.setOnClickListener(this);

        mDatabase = FirebaseDatabase.getInstance().getReference();
        //filteredItemsList = new ArrayList<>();
        itemsList = new ArrayList<>();
        addItemToList("Name");
        addItemToList("Category");
        locationsList = new ArrayList<>();
        mDatabase.child("locations").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                addLocToList("All Locations");
                for (DataSnapshot childSnapshot: dataSnapshot.getChildren()) {
                    addLocToList(childSnapshot.child("name").getValue().toString());
                }
                finishInit();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {}
        });
    }

    //Function called once all locations have actually loaded from firebase
    public void finishInit () {
        String[] locationsArray = locationsList.toArray(new String[locationsList.size()]);
        String[] itemsArray = itemsList.toArray(new String[itemsList.size()]);

        ArrayAdapter<String> locAdapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item,
                locationsArray);
        ArrayAdapter<String> itemAdapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item,
                itemsArray);

        selectLocation.setAdapter(locAdapter);
        selectItem.setAdapter(itemAdapter);
        bSearch.setOnClickListener(this);
    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {
    }

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
            return;
        } else if (v.getId() == R.id.bBack) {
            Intent goBack = new Intent(this, AppActivity.class);
            startActivity(goBack);
            return;

        }
    }

    public void addLocToList(String data) {
        locationsList.add(data);
    }
    public void addItemToList(String data) {
        itemsList.add(data);
    }
}


