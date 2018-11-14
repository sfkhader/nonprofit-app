package com.cs2340.donationtracker.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.cs2340.donationtracker.R;
import com.cs2340.donationtracker.dagger.DonationApplication;
import com.cs2340.donationtracker.model.Location;
import com.cs2340.donationtracker.model.LocationServiceFacade;

import javax.inject.Inject;

/**
 * Class created to display the information of each location
 */
public class LocationInfoActivity extends AppCompatActivity implements View.OnClickListener{

    @Inject
    LocationServiceFacade locationServiceFacade;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_location_info);

        ((DonationApplication) getApplication()).getServicesComponent().inject(this);

        TextView textName = findViewById(R.id.locationName2);
        TextView textType = findViewById(R.id.locationType);
        TextView textLongitude = findViewById(R.id.longitude);
        TextView textLatitude = findViewById(R.id.latitude);
        TextView textPhone = findViewById(R.id.phone);
        TextView textAddress = findViewById(R.id.address);

        Button donations = findViewById(R.id.donations);
        donations.setOnClickListener(this);

        Location location = locationServiceFacade.getLocation(
                getIntent().getIntExtra("EXTRA_LOCATION", 0));

        textName.setText(location.getName());
        textType.setText(location.getType());
        textLongitude.setText(location.getLongitude());
        textLatitude.setText(location.getLatitude());
        textPhone.setText(location.getPhone());
        textAddress.setText(location.getStreetAddress());
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.donations) {
            Intent donationPage = new Intent(this, DonationListActivity.class);
            startActivity(donationPage);
        }
    }
}
