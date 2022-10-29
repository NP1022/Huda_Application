package com.example.huda_application.user;

import java.util.UUID;

public class Appointment {

    private transient final UUID appointmentId = UUID.randomUUID();
    private String time;
    private String date;
    private AppointmentStatus status;
    private boolean checkedIn;

    public Appointment(String time, String date, AppointmentStatus status, boolean checkedIn) {
        this.time = time;
        this.date = date;
        this.status = status;
        this.checkedIn = checkedIn;
    }

    public Appointment(String time, String date, AppointmentStatus status) {
        this(time, date, status, false);
    }

    public UUID getAppointmentId() {
        return appointmentId;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public AppointmentStatus getStatus() {
        return status;
    }

    public void setStatus(AppointmentStatus status) {
        this.status = status;
    }

    public boolean isCheckedIn() {
        return checkedIn;
    }

    public void setCheckedIn(boolean checkedIn) {
        this.checkedIn = checkedIn;
    }
}
