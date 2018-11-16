package com.cs2340.donationtracker.model;

import android.support.annotation.NonNull;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class FirebaseUserDatabase{

    //Instance of Firebase
    private final DatabaseReference userDatabase;

    public FirebaseUserDatabase() {
        userDatabase = FirebaseDatabase.getInstance().getReference().child("users");
    }

    public void addUser(String username, User user) {

        if (username !=null) {
            userDatabase.child(username).setValue(user);
        }
    }

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
