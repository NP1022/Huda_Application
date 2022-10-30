package com.example.huda_application.firebase;

import com.example.huda_application.user.Appointment;
import com.example.huda_application.user.AppointmentStatus;
import com.example.huda_application.user.User;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Map;

public class FirebaseClient {

    private static final FirebaseDatabase database = FirebaseDatabase.getInstance();

    public static Task<Void> updateUser(User user) {
        return database.getReference(User.class.getSimpleName()).updateChildren(Map.of(user.getUserId(), user));
    }

    public static Task<Void> addUser(User user) {
        return database.getReference(User.class.getSimpleName()).child(user.getUserId()).setValue(user);
    }

    public static User convertToUser(DataSnapshot snapshot) {
        User user = new User(
                snapshot.child("firstName").getValue(String.class),
                snapshot.child("lastName").getValue(String.class),
                snapshot.child("emailAddress").getValue(String.class),
                snapshot.child("userType").getValue(String.class)
        );

        if (snapshot.hasChild("appointments")) {
            for (DataSnapshot appointmentData : snapshot.getChildren()) {
                for (DataSnapshot child : appointmentData.getChildren()) {
                    user.addAppointment(
                            new Appointment(
                                    child.child("time").getValue(String.class),
                                    child.child("date").getValue(String.class),
                                    child.child("reason").getValue(String.class),
                                    AppointmentStatus.valueOf(child.child("status").getValue(String.class)),
                                    child.hasChild("checkedIn") ? child.child("checkedIn").getValue(Boolean.class) : false
                            ));
                }
            }
        }

        user.setUserId(snapshot.child("userId").getValue(String.class));
        return user;
    }
}