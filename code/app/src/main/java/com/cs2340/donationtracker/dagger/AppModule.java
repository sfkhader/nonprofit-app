package com.cs2340.donationtracker.dagger;

import android.app.Application;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
class AppModule {
    private final Application applicationReference;

    public AppModule(Application appReference) {
        applicationReference = appReference;
    }

    @Provides
    @Singleton
    Application provideApplication() {
        return applicationReference;
    }
}
