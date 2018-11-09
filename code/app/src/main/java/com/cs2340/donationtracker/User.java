package com.cs2340.donationtracker;

import com.google.firebase.database.IgnoreExtraProperties;

@IgnoreExtraProperties
public class User {
    private String realName;
    private String password;
    private String userType;

    //Default constructor w/ no args is needed for Firebase

    /**
     * Constructor for User
     */
    User() {
    }

    /**
     * Constructor for User
     * @param realName entered name
     * @param password entered pw
     * @param userType entered type
     */
    User(String realName, String password, String userType) {
        this.realName = realName;
        this.password = password;
        this.userType = userType;
    }

    //Public getters are needed for Firebase to accept our object

    /**
     *
     * @return name
     */
    public String getRealName() {
        return realName;
    }

    /**
     *
     * @return pw
     */
    public String getPassword() {
        return password;
    }

    /**
     *
     * @return type
     */
    public String getUserType() {
        return userType;
    }
}
