package com.cs2340.donationtracker;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.support.v7.app.AppCompatActivity;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

/**
 * Class created to represent registration page of app
 */
public class RegistrationPage extends AppCompatActivity implements View.OnClickListener{

    private Button registerButton;
    private Button cancelButton;
    private EditText editPW;
    private EditText editUser;
    private EditText editName;
    private Spinner userType;
    private DatabaseReference mDatabase;
    //public ArrayList<User> usersList;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);

        //this will look through the activity xml and find the View that matches editUser
        //and editPW, and will set it to the variables on the left.
        editUser = findViewById(R.id.usernameField);
        editPW = findViewById(R.id.passwordField);
        editName = findViewById(R.id.nameField);

        registerButton = findViewById(R.id.registerButton);
        cancelButton = findViewById(R.id.cancelButton);

        userType = findViewById(R.id.spinner);
        mDatabase = FirebaseDatabase.getInstance().getReference();

        String[] userTypes = {"User", "Location Employee", "Admin"};
        ArrayAdapter<String> adapter = new ArrayAdapter(this,android.R.layout.simple_spinner_item, userTypes);
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

            User newUser = new User(editName.getText().toString(),
                                    editPW.getText().toString(),
                                    userType.getSelectedItem().toString());
            mDatabase.child("users").child(editUser.getText().toString()).setValue(newUser);

            Intent openApp = new Intent(this, AppActivity.class);
            startActivity(openApp);

        }
        //if cancel button is hit, go back to the welcome page
        if (v.getId() == R.id.cancelButton) {
            Intent goBack = new Intent(this, LoginPage.class);
            startActivity(goBack);

        }
    }
}
