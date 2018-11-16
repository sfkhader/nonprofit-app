package com.cs2340.donationtracker.model;

import android.support.annotation.NonNull;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

/**
 * Implements Firebase operations to retreive User information.
 */
public class FirebaseUserDatabase{

    //Instance of Firebase
    private final DatabaseReference userDatabase;

    /**
     * Creates a new FirebasedUserDatabase object.
     */
    public FirebaseUserDatabase() {
        userDatabase = FirebaseDatabase.getInstance().getReference().child("users");
    }

    /**
     * Adds a new user to the system.
     *
     * @param username user's desired username
     * @param user User object containing user's real name and desired password
     */
    public void addUser(String username, User user) {

        if (username !=null) {
            userDatabase.child(username).setValue(user);
        }
    }

    /**
     * Looks for a user's password in database, and makes a callback based on whether it was found.
     *
     * @param username user to look for password in
     * @param callback callback to inform of event success or failure
     */
    public void getUserPassword(String username, final IFirebaseCallback callback) {
        userDatabase.child(username).addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        callback.onSuccess(dataSnapshot.getValue(User.class));
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {
                        callback.onFail();
                    }
                });
    }
}
