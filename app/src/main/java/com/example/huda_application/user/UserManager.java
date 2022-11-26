package com.example.huda_application.user;

public class UserManager {

    private static final UserManager INSTANCE = new UserManager();    // Storage class which returns a static instance of the specific class

    private User currentUser = null;

    public void setCurrentUser(User currentUser) {
        this.currentUser = currentUser;
    }                                                                // Set the current user in the application to that specific used

    public boolean isAdmin() {
        return currentUser != null && currentUser.getUserType() == UserType.ADMIN;      // function to check if the user is an admin
    }

    public void removeCurrentUser() {
        currentUser = null;
    }                                                                                  // function to remove the current user in the application

    public User getCurrentUser() {
        return currentUser;
    }

    public static UserManager getInstance() {                                          // returns the instance of the class for the user object
        return INSTANCE;
    }
}
