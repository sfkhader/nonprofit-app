package com.cs2340.donationtracker.model;

/**
 * Callback used for waiting for a response from Firebase.
 */
public interface IFirebaseCallback {
    /**
     * Called whenever event was successful.
     *
     * @param data Data that resulted from the event.
     */
    void onSuccess(Object data);

    /**
     * Called whenever event failed.
     */
    void onFail();
}
