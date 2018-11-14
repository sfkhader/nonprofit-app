package com.cs2340.donationtracker.model;

import com.google.firebase.database.IgnoreExtraProperties;

@IgnoreExtraProperties
class User {
    private String realName;
    private String password;
    private String userType;

    //Default constructor w/ no args is needed for firebase
    public User() {
    }

    User(String realName, String password, String userType) {
        this.realName = realName;
        this.password = password;
        this.userType = userType;
    }

    //Public getters are needed for firebase to accept our object
    public String getRealName() {
        return realName;
    }
    public String getPassword() {
        return password;
    }
    public String getUserType() {
        return userType;
    }
}
