package com.cs2340.donationtracker;

import android.content.Intent;
import android.os.Bundle;
import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Search_Results extends AppCompatActivity implements View.OnClickListener {

    ListView listView;
    //List<String> listo;
    DatabaseReference mDatabase;
    ArrayAdapter<String> arrayAdapter;
    List<String> arrayList;
    Button goBack;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search__results);

        listView = (ListView) findViewById(R.id.list_view);
        arrayList = new ArrayList<>();
        goBack = (Button) findViewById(R.id.goBack);
        Bundle extras = getIntent().getExtras();
        String location = extras.getString("EXTRA_LOCATION");
        String itemType = extras.getString("EXTRA_ITEM");
        String text = extras.getString("EXTRA_TEXT");

        searchDB(location, itemType, text);


    }

    /**
     * @param loc
     * @param itemT
     * @param texto
     */
    public void searchDB(final String loc, final String itemT, final String texto) {
        mDatabase = FirebaseDatabase.getInstance().getReference();

        mDatabase.child("donations").addListenerForSingleValueEvent(new ValueEventListener() {
            String location = loc;
            String itemType = itemT;
            String text = texto;
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                //checks for NOT ALL LOCATIONS
                if (!location.equals("All Locations")) {
                    for (DataSnapshot childSnapshot : dataSnapshot.getChildren()) {
                        if (childSnapshot.child("location").getValue().toString().equals(location) &&
                        (childSnapshot.child("category").getValue().toString().equals(itemType))){
                            if (childSnapshot.child("name").getValue().toString().contains(text)) {
                                arrayList.add(childSnapshot.child("name").getValue().toString());
                            }
                        }
                    }
                } else if (location.equals("All Locations")) {
                    for (DataSnapshot childSnapshot : dataSnapshot.getChildren()) {
                        if (childSnapshot.child("name").getValue().toString().contains(text) &&
                                (childSnapshot.child("category").getValue().toString()
                                        .equals(itemType))) {

                            arrayList.add(childSnapshot.child("name").getValue().toString());

                        }
                    }

                }
                finit();

            }


            @Override
            public void onCancelled(DatabaseError databaseError) {}
        });

    }

    /**
     * method just arranges the results properly
     */
    public void finit() {
        if(arrayList.size() == 0) {
            String noResults = "No Results Found";
            arrayList.add(noResults);
        }
        String[] locationsArray = arrayList.toArray(new String[arrayList.size()]);
        ArrayAdapter<String> arrayAdapter;
        arrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,
                locationsArray);
        listView.setAdapter(arrayAdapter);
        goBack.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.goBack) {
            Intent searchPage = new Intent(this, SearchPage.class);
            arrayList.clear();
            startActivity(searchPage);
            return;
        }

    }
}
