package com.cs2340.donationtracker;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.content.Intent;
import android.view.View;
import android.widget.Button;

/**
 * Class created to represent activity on the first page when logged into app
 */
public class AppActivity extends AppCompatActivity implements View.OnClickListener{
    private Button bLogout;
    private Button bLocations;
    private Button bGoToSearch;
    private Button bMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_app);

        bLogout = findViewById(R.id.bLogout);
        bLocations = findViewById(R.id.locations);
        bGoToSearch = findViewById(R.id.bGoToSearch);
        bMap = findViewById(R.id.map);

        bGoToSearch.setOnClickListener(this);
        bMap.setOnClickListener(this);
        bLogout.setOnClickListener(this);
        bLocations.setOnClickListener(this);
    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.bLogout:
                Intent bLogout = new Intent(this, LoginPage.class);
                startActivity(bLogout);
                break;
            case R.id.locations:
                Intent bLocations = new Intent(this, AllLocations.class);
                startActivity(bLocations);
                break;
            case R.id.bGoToSearch:
                Intent bGoToSearch = new Intent(this, SearchPage.class);
                startActivity(bGoToSearch);
                break;
            case R.id.map:
                Intent bMap = new Intent(this, MapsActivity.class);
                startActivity(bMap);
                break;
        }
    }
}
