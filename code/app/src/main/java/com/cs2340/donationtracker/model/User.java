package com.cs2340.donationtracker.model;

import com.google.firebase.database.IgnoreExtraProperties;

/**
 * User information holder.
 */
@IgnoreExtraProperties
public class User {
    private String realName;
    private String password;
    private String userType;

    /**
     * Creates a default User object.
     */
    //Default constructor w/ no args is needed for firebase
    public User() {
    }

    /**
     * Creates a new User object.
     *
     * @param realName user's real name
     * @param password user's desired password
     * @param userType account type of user
     */
    public User(String realName, String password, String userType) {
        this.realName = realName;
        this.password = password;
        this.userType = userType;
    }

    //Public getters are needed for firebase to accept our object

    /**
     * @return user's real name
     */
    public String getRealName() {
        return realName;
    }

    /**
     * @return user's password
     */
    public String getPassword() {
        return password;
    }

    /**
     * @return user's access type
     */
    public String getUserType() {
        return userType;
    }
}
