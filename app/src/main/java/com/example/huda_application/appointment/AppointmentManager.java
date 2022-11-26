package com.example.huda_application.appointment;

import android.os.Build;

import androidx.annotation.NonNull;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.sql.Time;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class AppointmentManager {

    private static final String TIME_PATTERN = "%s:%s:00";
    private final List<Time> availableTimes = new ArrayList<>();                   // Variables used to manage the appointments
    private final Map<Time, String> appointments = new HashMap<>();                //Map which will take the day as the key and the value will be the appointment time
    private final String date;

    public AppointmentManager(String date) {                                       // function to set the date for the appointment manager
        this.date = date;
    }

    public void createTimesOpen(Float hours) {
        if (hours == null) {
            hours = 5f;
        }                                                                          // Function takes in the operation our as the input and output all the available appointment times for that day


        for (float i = 0.5f; i < hours + 1f; i += 0.5f) {
            int hour = 8 + (int) i;                                                // For loop will take the hours of operation and created the availabletimes list
            String minute = i != (int) i ? "30" : "00";
            String timeStr = String.format(TIME_PATTERN, hour < 10 ? "0" + hour : hour, minute);
            availableTimes.add(Time.valueOf(timeStr));
        }
    }

    public List<Time> getAvailableTimes() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            return availableTimes.stream().filter(time -> !appointments.containsKey(time)).collect(Collectors.toList());        //List returns that available times for a specific day as a list
        }

        return new ArrayList<>();
    }

    public static void createAppointment(String date, String time, String userId) {
        FirebaseDatabase.getInstance().getReference("Appointment").child(date).child(time).setValue(userId);            // Function that will create the appointment and push it to firebase
    }

    public static void deleteAppointment (String date , String time){
        FirebaseDatabase.getInstance().getReference("Appointment").child(date).child(time).removeValue();               // Function takes the date and time of an appointment and will delete it from the database
    }

    public void convertAppointment(DataSnapshot snapshot) {
        Time time = Time.valueOf(snapshot.getKey());                                                                         // Function that converts the appointment to an appointment and add it to the list of appointment for that object
        String userId = snapshot.getValue(String.class);

        appointments.put(time, userId);
    }
}