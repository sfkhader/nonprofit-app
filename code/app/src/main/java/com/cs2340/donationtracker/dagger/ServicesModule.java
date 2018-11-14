package com.cs2340.donationtracker.dagger;

import com.cs2340.donationtracker.model.DonationServiceFacade;
import com.cs2340.donationtracker.model.LocationServiceFacade;
import com.cs2340.donationtracker.model.UserServiceFacade;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
class ServicesModule {
    @Provides
    @Singleton
    UserServiceFacade provideUserServiceFacade() {
        return new UserServiceFacade();
    }

    @Provides
    @Singleton
    DonationServiceFacade provideDonationServiceFacade() {
        return new DonationServiceFacade();
    }

    @Provides
    @Singleton
    LocationServiceFacade provideLocationServiceFacade() {
        return new LocationServiceFacade();
    }
}
