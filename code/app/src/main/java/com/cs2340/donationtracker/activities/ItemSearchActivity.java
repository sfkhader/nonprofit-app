package com.cs2340.donationtracker.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.content.Intent;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Button;

import com.cs2340.donationtracker.R;
import com.cs2340.donationtracker.dagger.DonationApplication;
import com.cs2340.donationtracker.model.DonationServiceFacade;
import com.cs2340.donationtracker.model.LocationServiceFacade;

import java.util.List;
import java.util.Set;

import javax.inject.Inject;

/**
 * Class created to represent the activity of searching for items
 */
public class ItemSearchActivity extends AppCompatActivity implements View.OnClickListener {

    @Inject
    LocationServiceFacade locationServiceFacade;
    @Inject
    DonationServiceFacade donationServiceFacade;

    private Spinner selectLocation;
    private Spinner selectItem;
    private EditText searchText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item_search);

        ((DonationApplication) getApplication()).getServicesComponent().inject(this);

        selectItem = findViewById(R.id.selectItem);
        selectLocation = findViewById(R.id.selectLocation);
        searchText = findViewById(R.id.searchText);
        Button bSearch = findViewById(R.id.bSearch);
        Button bBack = findViewById(R.id.bBack);
        bSearch.setOnClickListener(this);
        bBack.setOnClickListener(this);

        List<String> locationsList = locationServiceFacade.getLocationNames();
        Set<String> categoriesSet = donationServiceFacade.getDonationCategories();

        String[] locationsArray = locationsList.toArray(new String[locationsList.size()]);
        String[] categoriesArray = categoriesSet.toArray(new String[categoriesSet.size()]);

        selectLocation.setAdapter(new ArrayAdapter<>(this,
                android.R.layout.simple_spinner_item,
                locationsArray));
        selectItem.setAdapter(new ArrayAdapter<>(this,
                android.R.layout.simple_spinner_item,
                categoriesArray));
        bSearch.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.bSearch) {
            Intent toResults = new Intent(this, ItemSearchResultsActivity.class);
            Bundle extras = new Bundle();
            int locSpinner = selectLocation.getSelectedItemPosition();
            int itemSpinner = selectItem.getSelectedItemPosition();
            extras.putString("EXTRA_LOCATION",
                    (selectLocation.getItemAtPosition(locSpinner)).toString());
            extras.putString("EXTRA_ITEM", (selectItem.getItemAtPosition(itemSpinner)).toString());
            extras.putString("EXTRA_TEXT", searchText.getText().toString());
            toResults.putExtras(extras);
            startActivity(toResults);
        } else if (v.getId() == R.id.bBack) {
            Intent goBack = new Intent(this, MainHubActivity.class);
            startActivity(goBack);
        }
    }
}


