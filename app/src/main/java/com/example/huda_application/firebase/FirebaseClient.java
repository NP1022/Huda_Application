package com.example.huda_application.firebase;

import com.example.huda_application.Patient;
import com.example.huda_application.user.Appointment;
import com.example.huda_application.user.AppointmentStatus;
import com.example.huda_application.user.Message;
import com.example.huda_application.user.User;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Map;

public class FirebaseClient {

    private static final FirebaseDatabase database = FirebaseDatabase.getInstance();                // Storage Class used to store the Current user from firebase

    public static Task<Void> updateUser(User user) {
        return database.getReference(User.class.getSimpleName()).updateChildren(Map.of(user.getUserId(), user)); // Function used to updated the current user in the database takes a User object
    }

    public static Task<Void> addUser(User user) {
        return database.getReference(User.class.getSimpleName()).child(user.getUserId()).setValue(user);         // Function used to add a user to the database using an object
    }

    public static User convertToUser(DataSnapshot snapshot) {
        User user = new User(
                snapshot.child("firstName").getValue(String.class),
                snapshot.child("lastName").getValue(String.class),
                snapshot.child("emailAddress").getValue(String.class),
                snapshot.child("birthday").getValue(String.class),
                snapshot.child("userType").getValue(String.class)                                    // Function used to convert the DataSnapshot from the database to a object for the class
        );
        if (snapshot.hasChild("patient")) {
            user.setPatient(snapshot.child("patient").getValue(Patient.class));
        }


        if (snapshot.hasChild("appointments")) {
            for (DataSnapshot child : snapshot.child("appointments").getChildren()) {
                user.addAppointment(
                        new Appointment(

                                child.child("time").getValue(String.class),
                                child.child("date").getValue(String.class),                         // The following field are the what is brought from the datasnapshot from firebase
                                child.child("reason").getValue(String.class),
                                child.child("checkedInTime").getValue(String.class),
                                child.child("adminActionTime").getValue(String.class),
                                AppointmentStatus.valueOf(child.child("status").getValue(String.class)),
                                child.hasChild("checkedIn") ? child.child("checkedIn").getValue(Boolean.class) : false
                        ));
            }
        }

        if (snapshot.hasChild("messages")) {
            for (DataSnapshot child : snapshot.child("messages").getChildren()) {
                user.addMessages(new Message(
                        child.child("date").getValue(String.class),
                        child.child("message").getValue(String.class),                              // Checks if message field is inside the database and if its there
                        child.child("read").getValue(Boolean.class)

                ));
            }
        }

        user.setUserId(snapshot.child("userId").getValue(String.class));
        return user;
    }
}