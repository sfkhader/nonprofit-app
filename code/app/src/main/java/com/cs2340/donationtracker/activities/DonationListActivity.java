package com.cs2340.donationtracker.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.content.Intent;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Button;

import com.cs2340.donationtracker.R;
import com.cs2340.donationtracker.dagger.DonationApplication;
import com.cs2340.donationtracker.model.DonationServiceFacade;

import java.util.ArrayList;

import javax.inject.Inject;


/**
 * Activity to view a list of all donations.
 */
public class DonationListActivity extends AppCompatActivity implements View.OnClickListener{

    @Inject
    DonationServiceFacade donationServiceFacade;

    private Spinner donations;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_donation_list);

        ((DonationApplication) getApplication()).getServicesComponent().inject(this);

        Button donationInfo = findViewById(R.id.donationInfo);
        Button addDonationButton = findViewById(R.id.addDonation);
        donations = findViewById(R.id.spinner);

        ArrayList<String> donationsList = donationServiceFacade.getDonationNames();
        String[] donationsArray = donationsList.toArray(new String[donationsList.size()]);

        donations.setAdapter(new ArrayAdapter<>(this,
                android.R.layout.simple_spinner_item,
                donationsArray));

        donationInfo.setOnClickListener(this);
        addDonationButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.donationInfo) {
            Intent items = new Intent(this, DonationInfoActivity.class);
            items.putExtra("EXTRA_DONATION", donations.getSelectedItem().toString());
            startActivity(items);
        }
        if (v.getId() == R.id.addDonation) {
            Intent addDonation = new Intent(this, EditDonationActivity.class);
            addDonation.putExtra("EXTRA_DONATION", "none");
            startActivity(addDonation);
        }
    }
}
