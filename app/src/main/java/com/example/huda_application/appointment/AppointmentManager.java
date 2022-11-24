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
    private final List<Time> availableTimes = new ArrayList<>();
    private final Map<Time, String> appointments = new HashMap<>();
    private final String date;

    public AppointmentManager(String date) {
        this.date = date;
    }

    public void createTimesOpen(Float hours) {
        if (hours == null) {
            hours = 5f;
        }


        for (float i = 0.5f; i < hours + 1f; i += 0.5f) {
            int hour = 8 + (int) i;
            String minute = i != (int) i ? "30" : "00";
            String timeStr = String.format(TIME_PATTERN, hour < 10 ? "0" + hour : hour, minute);
            availableTimes.add(Time.valueOf(timeStr));
        }
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