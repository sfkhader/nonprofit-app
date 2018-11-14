package com.cs2340.donationtracker.activities;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import com.cs2340.donationtracker.R;
import com.cs2340.donationtracker.dagger.DonationApplication;
import com.cs2340.donationtracker.model.UserServiceFacade;

import javax.inject.Inject;

/**
 * Class created to represent registration page of app
 */
public class RegistrationActivity extends AppCompatActivity implements View.OnClickListener{

    @Inject
    UserServiceFacade userServiceFacade;

    private EditText editPW;
    private EditText editUser;
    private EditText editName;
    private Spinner userType;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);

        ((DonationApplication) getApplication()).getServicesComponent().inject(this);

        //this will look through the activity xml and find the View that matches editUser
        //and editPW, and will set it to the variables on the left.
        editUser = findViewById(R.id.usernameField);
        editPW = findViewById(R.id.passwordField);
        editName = findViewById(R.id.nameField);

        Button registerButton = findViewById(R.id.registerButton);
        Button cancelButton = findViewById(R.id.cancelButton);

        userType = findViewById(R.id.spinner);

        String[] userTypes = {"User", "Location Employee", "Admin"};
        ArrayAdapter<String> adapter =
                new ArrayAdapter(this,android.R.layout.simple_spinner_item, userTypes);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        userType.setAdapter(adapter);

        registerButton.setOnClickListener(this);
        cancelButton.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        //issue with this method is that it goes off if we have many on click listeners, and
        //we don't know why it went off. the if statement takes care of that
        //handles for all buttons, have to use if to see which is used
        if (v.getId() == R.id.registerButton) {
            userServiceFacade.register(editUser.getText().toString(),
                    editName.getText().toString(),editPW.getText().toString(),
                    userType.getSelectedItem().toString());
            Toast.makeText(RegistrationActivity.this,
                    "Successfully registered.",
                    Toast.LENGTH_LONG).show();
            finish();
        }
        //if cancel button is hit, go back to the welcome page
        if (v.getId() == R.id.cancelButton) {
            finish();
        }
    }
}
