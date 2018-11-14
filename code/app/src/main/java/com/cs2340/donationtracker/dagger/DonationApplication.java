package com.cs2340.donationtracker.dagger;

import android.app.Application;

/**
 * Our applications class, created for use with Dagger2
 */
public class DonationApplication extends Application {

    private ServicesComponent servicesComponent;

    @Override
    public void onCreate() {
        super.onCreate();

        servicesComponent = DaggerServicesComponent.builder()
                .appModule(new AppModule(this))
                .servicesModule(new ServicesModule())
                .build();
    }

    /**
     * @return reference to ServicesComponent
     */
    public ServicesComponent getServicesComponent() {
        return servicesComponent;
    }
}
