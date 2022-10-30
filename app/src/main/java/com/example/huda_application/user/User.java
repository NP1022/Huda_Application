package com.example.huda_application.user;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class User implements Serializable {

    private String userId;
    private String firstName;
    private String lastName;
    private String emailAddress;
    private UserType userType;


    private final List<Appointment> appointments;

    public User(String firstName, String lastName, String emailAddress, UserType userType, List<Appointment> appointmentList) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.emailAddress = emailAddress;
        this.userType = userType;
        this.appointments = appointmentList;
    }

    public User(String firstName, String lastName, String emailAddress, String userType) {
        this(firstName, lastName, emailAddress, UserType.valueOf(userType), new ArrayList<>());
    }

    public User(String firstName, String lastName, String emailAddress) {
        this(firstName, lastName, emailAddress, UserType.PATIENT, new ArrayList<>());
    }


    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public UserType getUserType() {
        return userType;
    }

    public void setUserType(UserType userType) {
        this.userType = userType;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public List<Appointment> getAppointments() {
        return appointments;
    }

    public void addAppointment(Appointment appointment) {
        this.appointments.add(appointment);
    }

    public void removeAppointment(Appointment appointment) {
        appointments.remove(appointment);
    }
}