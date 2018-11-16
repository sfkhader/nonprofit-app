package com.cs2340.donationtracker.model;

import android.support.annotation.Nullable;

class UserManager {

    //Instance of Firebase user database
    private final FirebaseUserDatabase userDatabase;

    //Currently logged in user (null if none)
    @Nullable
    private User currentUser;

    public UserManager() {
        userDatabase = new FirebaseUserDatabase();
    }

    public void addUser(String username, String realName, String password, String userType) {
        userDatabase.addUser(username, new User(realName, password, userType));
    }

    public void checkLogin(String username, final String password,
                           final IFirebaseCallback callback) {
        //Pass a new callback to user database to get the password
        userDatabase.getUserPassword(username, new IFirebaseCallback() {
            @Override
            public void onSuccess(Object data) {
                User fetchedUser = (User)data;
                //Password matches
                if ((fetchedUser != null) && fetchedUser.getPassword().equals(password)) {
                    //Set the current user to newly logged in user
                    currentUser = fetchedUser;
                    //Pass null as data because not needed here
                    callback.onSuccess(null);
                } else {
                    //Password didn't match or user not found
                    callback.onFail();
                }
            }

            @Override
            public void onFail() {

            }
        });
    }

    public void logout() {
        currentUser = null;
    }

    public String getCurrentUserRealname() {
        return currentUser.getRealName();
    }
}
