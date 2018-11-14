package com.cs2340.donationtracker.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.Toast;

import com.cs2340.donationtracker.R;
import com.cs2340.donationtracker.dagger.DonationApplication;
import com.cs2340.donationtracker.model.IFirebaseCallback;
import com.cs2340.donationtracker.model.UserServiceFacade;

import javax.inject.Inject;

/**
 * Class used to represent activity for login page of app
 */
public class LoginActivity extends AppCompatActivity implements View.OnClickListener{

    @Inject
    UserServiceFacade userServiceFacade;

    private EditText editPW;
    private EditText editUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        ((DonationApplication) getApplication()).getServicesComponent().inject(this);

        //this will look through the activity xml and find the View that matches editUser
        //and editPW, and will set it to the variables on the left.
        editUser = findViewById(R.id.editUser);
        editPW = findViewById(R.id.editPW);
        Button loginButton = findViewById(R.id.loginButton);
        Button bCancel = findViewById(R.id.bCancel);

        //now we set an on click listener, which watches for when the user hits the login button
        //notifies the onClick method below
        loginButton.setOnClickListener(this);
        bCancel.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        //issue with this method is that it goes off if we have many on click listeners, and
        //we don't know why it went off. the if statement takes care of that
        //handles for all buttons, have to use if to see which is used
        if (v.getId() == R.id.loginButton) {
            userServiceFacade.checkLogin(editUser.getText().toString(),
                    editPW.getText().toString(), new IFirebaseCallback() {
                        @Override
                        public void onSuccess(Object data) {
                            login();
                        }

                        @Override
                        public void onFail() {
                            Toast.makeText(LoginActivity.this,
                                    "Wrong username or password.",
                                    Toast.LENGTH_LONG).show();
                        }
                    });
        }

        //if cancel button is hit, go back to the welcome page
        if (v.getId() == R.id.bCancel) {
            finish();
        }
    }

    private void login() {
        Intent intent = new Intent(this, MainHubActivity.class);
        startActivity(intent);
    }
}
