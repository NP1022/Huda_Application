package com.example.huda_application.firebase;

import androidx.annotation.NonNull;

import com.example.huda_application.user.Appointment;
import com.example.huda_application.user.AppointmentStatus;
import com.example.huda_application.user.User;
import com.example.huda_application.user.UserType;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class FirebaseClient {

    private static final FirebaseDatabase database = FirebaseDatabase.getInstance();



    public static User convertToUser(DataSnapshot snapshot) {
        User user = new User(
                snapshot.child("firstName").getValue(String.class),
                snapshot.child("lastName").getValue(String.class),
                snapshot.child("emailAddress").getValue(String.class),
                snapshot.child("userType").getValue(String.class)
        );

        if (snapshot.hasChild("appointments")) {
            System.out.println(snapshot.child("appointments").getValue());
        }

        return user;
    }
    public static List<User> getAllUsers() {
        List<User> users = new ArrayList<>();
        if (User.isAdmin()) {
            users.add(new User(
                    "First-Name",
                    "Last-Name",
                    "test@gmail.com",
                    UserType.PATIENT,
                    List.of(new Appointment(
                            "10:30",
                            "October 31, 2022",
                            AppointmentStatus.PENDING
                    ))
            ));
        }

        return users;
    }
}
