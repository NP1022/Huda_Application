package com.example.huda_application;

import com.example.huda_application.user.User;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class DAOUser {                      // Class used to store the child which is the user in the database and it als returns that child

    private final DatabaseReference dbRef;

    public DAOUser() {
        FirebaseDatabase db = FirebaseDatabase.getInstance();
        dbRef = db.getReference(User.class.getSimpleName());
    }

    public Task<Void> add(User user) {
        return dbRef.child(user.getUserId()).setValue(user);
    }
}
