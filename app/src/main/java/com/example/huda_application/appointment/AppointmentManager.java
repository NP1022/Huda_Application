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

    private final List<Time> availableTimes = List.of(
            Time.valueOf("08:30:00"),
            Time.valueOf("09:00:00"),
            Time.valueOf("09:30:00"),
            Time.valueOf("10:00:00"),
            Time.valueOf("10:30:00"),
            Time.valueOf("11:00:00"),
            Time.valueOf("11:30:00"),
            Time.valueOf("12:00:00"),
            Time.valueOf("12:30:00"),
            Time.valueOf("13:00:00"),
            Time.valueOf("13:30:00")
    );

    private final Map<Time, String> appointments = new HashMap<>();
    private final String date;

    public AppointmentManager(String date) {
        this.date = date;
    }

    public List<Time> getAvailableTimes() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            return availableTimes.stream().filter(time -> !appointments.containsKey(time)).collect(Collectors.toList());
        }

        return new ArrayList<>();
    }

    public static void createAppointment(String date, String time, String userId) {
        FirebaseDatabase.getInstance().getReference("Appointment").child(date).child(time).setValue(userId);
    }

    public static void deleteAppointment (String date , String time){
        FirebaseDatabase.getInstance().getReference("Appointment").child(date).child(time).removeValue();
    }

    public void convertAppointment(DataSnapshot snapshot) {
        Time time = Time.valueOf(snapshot.getKey());
        String userId = snapshot.getValue(String.class);

        appointments.put(time, userId);
    }
}