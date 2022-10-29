package com.example.huda_application.user;

public class UserManager {

    private static final UserManager INSTANCE = new UserManager();

    private User currentUser = null;

    public void setCurrentUser(User currentUser) {
        this.currentUser = currentUser;
    }

    public boolean isAdmin() {
        return currentUser != null && currentUser.getUserType() == UserType.ADMIN;
    }

    public void removeCurrentUser() {
        currentUser = null;
    }

    public User getCurrentUser() {
        return currentUser;
    }

    public static UserManager getInstance() {
        return INSTANCE;
    }
}
