package com.cs2340.donationtracker.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import com.cs2340.donationtracker.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

/**
 * Class created to represent results when searching for a donation item
 */
public class ItemSearchResultsActivity extends AppCompatActivity implements View.OnClickListener {

    private ListView listView;
    private List<String> arrayList;
    private Button goBack;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item_search_results);

        listView = findViewById(R.id.list_view);
        arrayList = new ArrayList<>();
        goBack = findViewById(R.id.goBack);
        Bundle extras = getIntent().getExtras();
        if(extras == null) {
            finit(false);
        }
        String location = extras.getString("EXTRA_LOCATION");
        String itemType = extras.getString("EXTRA_ITEM");
        String text = extras.getString("EXTRA_TEXT");
        boolean setupWorked = setupSearch(location, itemType, text);
        if(!setupWorked) {
            finit(false);
        }
        searchDB(location, itemType, text);


    }

    /**
     *
     * @param loc the location to search
     * @param item the category to search
     * @param text the searching text
     * @return whether the params passed in are valid
     */
    public boolean setupSearch(String loc, String item, String text){
        //extras = extras;
        return (loc != null) &&
                (item != null) &&
                (text != null);
    }

    /**
     * @param loc
     * @param itemT
     * @param texto
     */
    private void searchDB(final String loc, final String itemT, final String texto) {
        //List<String> listo;
        DatabaseReference mDatabase = FirebaseDatabase.getInstance().getReference();

        mDatabase.child("donations").addListenerForSingleValueEvent(new ValueEventListener() {
            final String location = loc;
            final String itemType = itemT;
            final String text = texto;
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                //checks for NOT ALL LOCATIONS
                if (!"All Locations".equals(location)) {
                    for (DataSnapshot childSnapshot : dataSnapshot.getChildren()) {
                        if (childSnapshot.child("location").getValue().toString().equals(location) &&
                                (childSnapshot.child("category").getValue().toString().equals(itemType))){
                            if (childSnapshot.child("name").getValue().toString().contains(text)) {
                                arrayList.add(childSnapshot.child("name").getValue().toString());
                            }
                        }
                    }
                } else if ("All Locations".equals(location)) {
                    for (DataSnapshot childSnapshot : dataSnapshot.getChildren()) {
                        if (childSnapshot.child("name").getValue().toString().contains(text) &&
                                (childSnapshot.child("category").getValue().toString()
                                        .equals(itemType))) {

                            arrayList.add(childSnapshot.child("name").getValue().toString());

                        }
                    }

                }
                finit(true);

            }


            @Override
            public void onCancelled(DatabaseError databaseError) {}
        });

    }

    /**
     * method just arranges the results properly
     */
    private void finit(boolean searched) {
        if(!searched){
            String errResults = "Try a different search";
            arrayList.add(errResults);
        }
        if(arrayList.isEmpty() && searched) {
            String noResults = "No Results Found";
            arrayList.add(noResults);
        }
        String[] locationsArray = arrayList.toArray(new String[arrayList.size()]);
        ArrayAdapter<String> arrayAdapter;
        arrayAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1,
                locationsArray);
        listView.setAdapter(arrayAdapter);
        goBack.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.goBack) {
            Intent searchPage = new Intent(this, ItemSearchActivity.class);
            arrayList.clear();
            startActivity(searchPage);
        }

    }
}
