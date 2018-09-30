package com.cs2340.donationtracker;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.os.Bundle;
import android.widget.EditText;
import android.app.Activity;

import java.util.ArrayList;

public class RegistrationPage extends AppCompatActivity implements View.OnClickListener{

    Button registerButton, bCancel;
    EditText editPW, editUser, editName;
    Spinner userType;
    public ArrayList<User> users;

    class User {
        String username;
        String password;
        String userType;
        User(String username, String password, String userType) {
            this.username = username;
            this.password = password;
            this.userType = userType;
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);

        //this will look through the activity xml and find the View that matches editUser
        //and editPW, and will set it to the variables on the left.
        editUser = (EditText) findViewById(R.id.usernameField);
        editPW = (EditText) findViewById(R.id.passwordField);
        editName = (EditText) findViewById(R.id.nameField);

        registerButton = (Button) findViewById(R.id.registerButton);
        bCancel = (Button) findViewById(R.id.cancelButton);

        userType = (Spinner) findViewById(R.id.spinner);
        //now we set an on click listener, which watches for when the user hits the login button
        //notifies the onClick method below
        String[] userTypes = {"User", "Location Employee", "Admin"};
        ArrayAdapter<String> adapter = new ArrayAdapter(this,android.R.layout.simple_spinner_item, userTypes);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        userType.setAdapter(adapter);

        registerButton.setOnClickListener(this);
        bCancel.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        //issue with this method is that it goes off if we have many on click listeners, and
        //we don't know why it went off. the if statement takes care of that
        //handles for all buttons, have to use if to see which is used
        if (v.getId() == R.id.registerButton) {
            //this is what happens when the login button is clicked
            //new intent for entering the app
            Intent openApp = new Intent(this, AppActivity.class);
            User newUser = new User(editUser.getText().toString(), editPW.getText().toString(), userType.getSelectedItem().toString());
            users.add(newUser);


        }
        //if cancel button is hit, go back to the welcome page
        if (v.getId() == R.id.bCancel) {
            Intent goback = new Intent(this, LoginPage.class);
            startActivity(goback);

        }
        return;
    }
}
