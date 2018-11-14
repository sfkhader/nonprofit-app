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
 * Activity to look at a Donation's info
 */
public class DonationInfoActivity extends AppCompatActivity implements View.OnClickListener{

    @Inject
    DonationServiceFacade donationServiceFacade;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_donation_info);

        ((DonationApplication) getApplication()).getServicesComponent().inject(this);

        TextView textName = findViewById(R.id.name);
        TextView textShortDescription = findViewById(R.id.shortDescription);
        TextView textFullDescription = findViewById(R.id.fullDescription);
        TextView textValue = findViewById(R.id.value);
        TextView textCategory = findViewById(R.id.category);
        TextView textTimeStamp = findViewById(R.id.timeStamp);
        TextView textLocation = findViewById(R.id.location);
        Button edit = findViewById(R.id.edit);

        edit.setOnClickListener(this);

        Donation donation = donationServiceFacade.getDonation(
                getIntent().getStringExtra("EXTRA_DONATION"));

        textName.setText(donation.getName());
        textShortDescription.setText(donation.getShortDescription());
        textFullDescription.setText(donation.getFullDescription());
        textValue.setText(donation.getValue());
        textCategory.setText(donation.getCategory());
        textTimeStamp.setText(donation.getTimeStamp());
        textLocation.setText(donation.getLocation());
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.edit) {
            Intent editDonation = new Intent(this, EditDonationActivity.class);
            editDonation.putExtra("EXTRA_DONATION", getIntent().getStringExtra("EXTRA_DONATION"));
            startActivity(editDonation);
        }
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(getApplicationContext(), DonationListActivity.class);
        intent.putExtra("EXTRA_DONATION", getIntent().getStringExtra("EXTRA_DONATION"));
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
    }
}
