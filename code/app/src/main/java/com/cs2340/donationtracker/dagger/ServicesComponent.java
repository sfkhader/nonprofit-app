package com.cs2340.donationtracker.dagger;

import com.cs2340.donationtracker.activities.DonationInfoActivity;
import com.cs2340.donationtracker.activities.DonationListActivity;
import com.cs2340.donationtracker.activities.EditDonationActivity;
import com.cs2340.donationtracker.activities.ItemSearchActivity;
import com.cs2340.donationtracker.activities.ItemSearchResultsActivity;
import com.cs2340.donationtracker.activities.LocationInfoActivity;
import com.cs2340.donationtracker.activities.LocationListActivity;
import com.cs2340.donationtracker.activities.LoginActivity;
import com.cs2340.donationtracker.activities.MainHubActivity;
import com.cs2340.donationtracker.activities.RegistrationActivity;
import com.cs2340.donationtracker.activities.StartupActivity;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Component used by Dagger2 for dependency injection.
 */
@Singleton
@Component(modules =  {AppModule.class, ServicesModule.class})
public interface ServicesComponent {
    void inject (DonationInfoActivity activity);
    void inject (DonationListActivity activity);
    void inject (EditDonationActivity activity);
    void inject (ItemSearchActivity activity);
    void inject (ItemSearchResultsActivity activity);
    void inject (LocationInfoActivity activity);
    void inject (LocationListActivity activity);
    void inject (LoginActivity activity);
    void inject (MainHubActivity activity);
    void inject (RegistrationActivity activity);
    void inject (StartupActivity activity);
}
