package com.cs2340.donationtracker.model;

/**
 * Provides controllers access to interact with user model data.
 */
public final class UserServiceFacade {

    //Connects this facade to the model
    private final UserManager userManager;

    /**
     * Creates a new UserServiceFacade.
     */
    public UserServiceFacade() {
        userManager = new UserManager();
    }

    /**
     * Check to see if username and password are valid.
     *
     * @param username user's username
     * @param password user's password
     * @param callback callback used to signal whether check was valid or not
     */
    public void checkLogin(String username, String password, IFirebaseCallback callback) {
        userManager.checkLogin(username, password, callback);
    }

    /**
     * Called to log out the currently logged in user.
     */
    public void logout() {
        userManager.logout();
    }

    /**
     * Register a new user.
     *
     * @param username user's username
     * @param realName user's real name
     * @param password user's password
     * @param userType user's access level
     */
    public void register(String username, String realName, String password, String userType) {
        userManager.addUser(username, realName, password, userType);
    }

    /**
     * @return the real name of the currently logged in user
     */
    public String getCurrentUserRealname() {
        return userManager.getCurrentUserRealname();
    }
}
