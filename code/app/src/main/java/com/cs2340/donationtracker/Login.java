package com.cs2340.donationtracker;

import android.content.Intent;
import android.renderscript.Sampler;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.os.Bundle;
import android.widget.EditText;
import android.app.Activity;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ChildEventListener;



public class Login extends AppCompatActivity implements View.OnClickListener{

    Button loginButton, bCancel;
    EditText editPW, editUser, error;
    DatabaseReference mDatabase;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        //this will look through the activity xml and find the View that matches editUser
        //and editPW, and will set it to the variables on the left.
        editUser = (EditText) findViewById(R.id.editUser);
        editPW = (EditText) findViewById(R.id.editPW);
        loginButton = (Button) findViewById(R.id.loginButton);
        bCancel = (Button) findViewById(R.id.bCancel);
        mDatabase = FirebaseDatabase.getInstance().getReference();

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
            //this is what happens when the login button is clicked
            //new intent for entering the app
            FirebaseDatabase database = FirebaseDatabase.getInstance();
            mDatabase.child("users").child("password").addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    String username = dataSnapshot.getKey();
                    if (username == editUser.getText().toString()) {
                        if (dataSnapshot.toString() == editPW.getText().toString()) {
                            Intent openApp = new Intent(Login.this, AppActivity.class);
                            startActivity(openApp);
                        } else if (dataSnapshot.toString() != editPW.getText().toString()) {
                            error.setError("wrong password");
                        }
                    } else if (username != editUser.getText().toString()){
                        error.setError("wrong username");
                    }

                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {}
            });
        }
        //if cancel button is hit, go back to the welcome page
        if (v.getId() == R.id.bCancel) {
            Intent goBack = new Intent(this, LoginPage.class);
            startActivity(goBack);
        }
    }
}
