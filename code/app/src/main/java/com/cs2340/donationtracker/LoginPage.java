package com.cs2340.donationtracker;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.app.Activity;

public class LoginPage extends AppCompatActivity implements View.OnClickListener{
    Button login;
    Button register;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_page);

        login = (Button) findViewById(R.id.login);
        register = (Button) findViewById(R.id.register);

        login.setOnClickListener(this);
        register.setOnClickListener(this);


    }


    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.login) {
            //what happens when the login button is clicked
//            navigateUpTo(new Intent(this, Login.class));
            Intent openLogin = new Intent(this, Login.class);
            startActivity(openLogin);
            return;
        } else if (v.getId() == R.id.register) {
            //what happens when the register button is clicked
//            navigateUpTo(new Intent(this, Login.class));
            Intent openRegister = new Intent(this, RegistrationPage.class);
            startActivity(openRegister);

            return;
        }
    }
}
