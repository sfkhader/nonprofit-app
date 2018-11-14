package com.cs2340.donationtracker.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.content.Intent;
import android.view.View;
import android.widget.Button;

import com.cs2340.donationtracker.R;
import com.cs2340.donationtracker.dagger.DonationApplication;
import com.cs2340.donationtracker.model.DonationServiceFacade;
import com.cs2340.donationtracker.model.LocationServiceFacade;

import javax.inject.Inject;

/**
 * Class created to represent activity on welcome page of app
 */
public class StartupActivity extends AppCompatActivity implements View.OnClickListener{

    @Inject LocationServiceFacade locationServiceFacade;
    @Inject DonationServiceFacade donationServiceFacade;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_startup);

        //Not needed by this activity, injected so Firebase will begin collecting data
        ((DonationApplication) getApplication()).getServicesComponent().inject(this);

        Button login = findViewById(R.id.login);
        Button register = findViewById(R.id.register);

        login.setOnClickListener(this);
        register.setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.login) {
            //what happens when the login button is clicked
//            navigateUpTo(new Intent(this, LoginActivity.class));
            Intent openLogin = new Intent(this, LoginActivity.class);
            startActivity(openLogin);
        } else if (v.getId() == R.id.register) {
            //what happens when the register button is clicked
//            navigateUpTo(new Intent(this, LoginActivity.class));
            Intent openRegister = new Intent(this, RegistrationActivity.class);
            startActivity(openRegister);
        }
    }
}
