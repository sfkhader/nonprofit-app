package com.cs2340.donationtracker.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.cs2340.donationtracker.R;
import com.cs2340.donationtracker.model.Donation;
import com.cs2340.donationtracker.dagger.DonationApplication;
import com.cs2340.donationtracker.model.DonationServiceFacade;

import javax.inject.Inject;


/**
 * Activity to edit/add a donation in the database.
 */
public class EditDonationActivity extends AppCompatActivity implements View.OnClickListener{

    @Inject
    DonationServiceFacade donationServiceFacade;

    private TextView textName;
    private TextView textShortDescription;
    private TextView textFullDescription;
    private TextView textValue;
    private TextView textCategory;
    private TextView textTimeStamp;
    private TextView textLocation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_donation);

        ((DonationApplication) getApplication()).getServicesComponent().inject(this);

        textName = findViewById(R.id.name);
        textFullDescription = findViewById(R.id.description);
        textShortDescription = findViewById(R.id.shortDescr);
        textValue = findViewById(R.id.value);
        textCategory = findViewById(R.id.category);
        textTimeStamp = findViewById(R.id.stamp);
        textLocation = findViewById(R.id.location);

        Button save = findViewById(R.id.save);

        save.setOnClickListener(this);

        //Is this an edit? If so, populate text boxes with items current info
        if (!"none".equals(getIntent().getStringExtra("EXTRA_DONATION"))) {
            Donation donation = donationServiceFacade.getDonation(
                    getIntent().getStringExtra("EXTRA_DONATION"));
            textName.setText(donation.getName());
            textFullDescription.setText(donation.getFullDescription());
            textShortDescription.setText(donation.getShortDescription());
            textValue.setText(donation.getValue());
            textCategory.setText(donation.getCategory());
            textTimeStamp.setText(donation.getTimeStamp());
            textLocation.setText(donation.getLocation());
        }
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.save) {

            // Add/save the donation based on the values now in the text boxes
            donationServiceFacade.addDonation(textName.getText().toString(),
                    textShortDescription.getText().toString(),
                    textFullDescription.getText().toString(),
                    textValue.getText().toString(),
                    textCategory.getText().toString(),
                    textTimeStamp.getText().toString(),
                    textLocation.getText().toString());

            Intent goback = new Intent(this, DonationInfoActivity.class);
            if ("none".equals(getIntent().getStringExtra("EXTRA_DONATION"))) {
                goback.putExtra("EXTRA_DONATION", textName.getText().toString());
            } else {
                goback.putExtra("EXTRA_DONATION", getIntent().getStringExtra("EXTRA_DONATION"));
            }

            finish();
            startActivity(goback);
        }
    }
}
