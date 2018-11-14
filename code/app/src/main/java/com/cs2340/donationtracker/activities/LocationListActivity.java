package com.cs2340.donationtracker.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.content.Intent;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Button;

import com.cs2340.donationtracker.R;
import com.cs2340.donationtracker.dagger.DonationApplication;
import com.cs2340.donationtracker.model.LocationServiceFacade;
import java.util.List;

import javax.inject.Inject;

/**
 * Class created to display all the locations
 */
public class LocationListActivity extends AppCompatActivity implements View.OnClickListener{

    @Inject
    LocationServiceFacade locationServiceFacade;

    private Spinner locations;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_location_list);

        ((DonationApplication) getApplication()).getServicesComponent().inject(this);

        Button information = findViewById(R.id.information);
        locations = findViewById(R.id.spinner);

        List<String> locationsList = locationServiceFacade.getLocationNames();
        String[] locationsArray = locationsList.toArray(new String[locationsList.size()]);

        locations.setAdapter(new ArrayAdapter<>(this,
                android.R.layout.simple_spinner_item,
                locationsArray));

        information.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.information) {
            Intent locationInfo = new Intent(this, LocationInfoActivity.class);

            //The +1 is because our location ids start at 1, while spinner locations start at 0
            locationInfo.putExtra("EXTRA_LOCATION", locations.getSelectedItemPosition() + 1);

            startActivity(locationInfo);
        }
    }
}
