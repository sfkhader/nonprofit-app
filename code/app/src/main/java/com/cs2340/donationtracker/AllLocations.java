package com.cs2340.donationtracker;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.content.Intent;
import android.view.View;
import android.widget.Spinner;
import android.widget.Button;
import android.widget.EditText;


public class AllLocations extends AppCompatActivity implements View.OnClickListener{
    Button information;
    Spinner locations;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_locations);

        information = (Button) findViewById(R.id.information);
        locations = (Spinner) findViewById(R.id.spinner);

        information.setOnClickListener(this);
    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.bLogout) {

            Intent goback = new Intent(this, LoginPage.class);
            startActivity(goback);
            return;
        }
    }
}
