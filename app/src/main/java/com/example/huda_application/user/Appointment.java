package com.example.huda_application.user;

import java.io.Serializable;

public class Appointment implements Serializable {          // class object used to store for the appointments class

    private String time;
    private String date;
    private String reason;                                  // fields needs for the appointment that will be collected and shared to the database
    private String checkedInTime;
    private String adminActionTime;
    private AppointmentStatus status;
    private boolean checkedIn;

    public Appointment(String time, String date, String reason, String checkedInTime,String adminActionTime,AppointmentStatus status, boolean checkedIn) {
        this.time = time;
        this.date = date;
        this.reason = reason;
        this.checkedInTime = checkedInTime;
        this.adminActionTime = adminActionTime;                  // constructor used to store the information needed for an appointment that takes in all the private fields for storage
        this.status = status;
        this.checkedIn = checkedIn;
    }

    public Appointment(String time, String date ,String reason,AppointmentStatus status) {
        this(time, date, reason,"" ,"",status, false); // constructor used to store the information needed for an appointment that takes in all the private fields for storage if the fields are empty.
    }

    public String getTime() {
        return time;
    }
                                                                                        // setter and getter for time of the appointment
    public void setTime(String time) {
        this.time = time;
    }

    public String getDate() {
        return date;
    }
                                                                                        // setter and getter for the date of the appointment
    public void setDate(String date) {
        this.date = date;
    }

    public String getReason() {
        return reason;
    }
                                                                                         // setter and getter for the reason related for the appointment
    public void setReason(String reason) {
        this.reason = reason;
    }

    public AppointmentStatus getStatus() {
        return status;
    }
                                                                                        // setter and getter for the status of the appointment
    public void setStatus(AppointmentStatus status) {
        this.status = status;
    }

    public boolean isCheckedIn() {
        return checkedIn;
    }
                                                                                         //setter and getter for the check-in status of the appointment
    public void setCheckedIn(boolean checkedIn) {
        this.checkedIn = checkedIn;
    }

    public String getAdminActionTime() {
        return adminActionTime;
    }

    public void setCheckedInTime(String checkedInTime) {
        this.checkedInTime = checkedInTime;
    }
                                                                                        // setter and getters for the admin action time and the check-in time for the patient of the application
    public void setAdminActionTime(String adminActionTime) {
        this.adminActionTime = adminActionTime;
    }

    public String getCheckedInTime() {
        return checkedInTime;
    }
}