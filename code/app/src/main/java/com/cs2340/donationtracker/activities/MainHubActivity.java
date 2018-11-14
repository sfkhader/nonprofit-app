package com.cs2340.donationtracker.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.cs2340.donationtracker.R;
import com.cs2340.donationtracker.dagger.DonationApplication;
import com.cs2340.donationtracker.model.UserServiceFacade;

import javax.inject.Inject;

/**
 * Class created to represent activity on the first page when logged into app
 */
public class MainHubActivity extends AppCompatActivity implements View.OnClickListener{

    @Inject
    UserServiceFacade userServiceFacade;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_hub);

        ((DonationApplication) getApplication()).getServicesComponent().inject(this);

        Button bLogout = findViewById(R.id.bLogout);
        Button bLocations = findViewById(R.id.locations);
        Button bGoToSearch = findViewById(R.id.bGoToSearch);
        Button bMap = findViewById(R.id.bMap);
        TextView nameBanner = findViewById(R.id.nameBanner);

        bGoToSearch.setOnClickListener(this);
        bLogout.setOnClickListener(this);
        bLocations.setOnClickListener(this);
        bMap.setOnClickListener(this);

        nameBanner.setText("Hello " +
        userServiceFacade.getCurrentUserRealname());
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.bLogout:
                Intent bLogout = new Intent(this, LoginActivity.class);
                startActivity(bLogout);
                break;
            case R.id.locations:
                Intent bLocations = new Intent(this, LocationListActivity.class);
                startActivity(bLocations);
                break;
            case R.id.bGoToSearch:
                Intent bGoToSearch = new Intent(this, ItemSearchActivity.class);
                startActivity(bGoToSearch);
                break;
            case R.id.bMap:
                Intent bMap = new Intent(this, MapsActivity.class);
                startActivity(bMap);
                break;
        }
    }
}
