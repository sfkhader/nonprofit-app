package com.cs2340.donationtracker;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;


public class AppActivity extends AppCompatActivity implements View.OnClickListener{
    Button bLogout;
    Button bLocations;
    Button bGoToSearch;
    Button bMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_app);

        bLogout = (Button) findViewById(R.id.bLogout);
        bLocations = (Button) findViewById(R.id.locations);
        bGoToSearch = (Button) findViewById(R.id.bGoToSearch);
        bMap = (Button) findViewById(R.id.map);

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
        if (v.getId() == R.id.bLogout) {
            Intent bLogout = new Intent(this, LoginPage.class);
            startActivity(bLogout);
            return;
        }else if (v.getId() == R.id.locations) {
            Intent bLocations = new Intent(this, AllLocations.class);
            startActivity(bLocations);
            return;
        } else if (v.getId() == R.id.bGoToSearch) {
            Intent bGoToSearch =  new Intent(this, SearchPage.class);
            startActivity(bGoToSearch);
            return;
        } else if (v.getId() == R.id.map) {
            Intent bMap =  new Intent(this, MapsActivity.class);
            startActivity(bMap);
            return;
        }
    }
}
