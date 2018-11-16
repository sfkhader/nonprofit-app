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
    /**
     * Required for Dagger2.
     * @param activity the activity to inject to
     */
    void inject (DonationInfoActivity activity);
    /**
     * Required for Dagger2.
     * @param activity the activity to inject to
     */
    void inject (DonationListActivity activity);
    /**
     * Required for Dagger2.
     * @param activity the activity to inject to
     */
    void inject (EditDonationActivity activity);
    /**
     * Required for Dagger2.
     * @param activity the activity to inject to
     */
    void inject (ItemSearchActivity activity);
    /**
     * Required for Dagger2.
     * @param activity the activity to inject to
     */
    void inject (ItemSearchResultsActivity activity);
    /**
     * Required for Dagger2.
     * @param activity the activity to inject to
     */
    void inject (LocationInfoActivity activity);
    /**
     * Required for Dagger2.
     * @param activity the activity to inject to
     */
    void inject (LocationListActivity activity);
    /**
     * Required for Dagger2.
     * @param activity the activity to inject to
     */
    void inject (LoginActivity activity);
    /**
     * Required for Dagger2.
     * @param activity the activity to inject to
     */
    void inject (MainHubActivity activity);
    /**
     * Required for Dagger2.
     * @param activity the activity to inject to
     */
    void inject (RegistrationActivity activity);
    /**
     * Required for Dagger2.
     * @param activity the activity to inject to
     */
    void inject (StartupActivity activity);
}
