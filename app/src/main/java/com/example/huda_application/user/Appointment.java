package com.example.huda_application.user;

import java.io.Serializable;

public class Appointment implements Serializable {

    private String time;
    private String date;
    private String reason;
    private String checkedInTime;
    private String adminActionTime;
    private AppointmentStatus status;
    private boolean checkedIn;

    public Appointment(String time, String date, String reason, String checkedInTime,String adminActionTime,AppointmentStatus status, boolean checkedIn) {
        this.time = time;
        this.date = date;
        this.reason = reason;
        this.checkedInTime = checkedInTime;
        this.adminActionTime = adminActionTime;
        this.status = status;
        this.checkedIn = checkedIn;
    }

    public Appointment(String time, String date ,String reason,AppointmentStatus status) {
        this(time, date, reason,"" ,"",status, false);
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

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
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

    public String getAdminActionTime() {
        return adminActionTime;
    }

    public void setCheckedInTime(String checkedInTime) {
        this.checkedInTime = checkedInTime;
    }

    public void setAdminActionTime(String adminActionTime) {
        this.adminActionTime = adminActionTime;
    }

    public String getCheckedInTime() {
        return checkedInTime;
    }
}