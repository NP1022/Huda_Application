package com.example.huda_application.user;

import com.example.huda_application.Patient;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class User implements Serializable {

    private String userId;
    private String firstName;
    private String lastName;
    private String emailAddress;
    private UserType userType;
    private Patient patient;
    private String birthday;
    private final List<Appointment> appointments;
    private final List<Message> messages = new ArrayList<>();

    public User(String firstName, String lastName, String emailAddress,String birthday, UserType userType, List<Appointment> appointmentList) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.emailAddress = emailAddress;
        this.birthday = birthday;
        this.userType = userType;
        this.appointments = appointmentList;
    }

    public User(String firstName, String lastName, String emailAddress,String birthday, String userType) {
        this(firstName, lastName, emailAddress,birthday, UserType.valueOf(userType), new ArrayList<>());
    }
    public User(String firstName, String lastName, String emailAddress,String birthday, UserType userType) {
        this(firstName, lastName, emailAddress,birthday, userType, new ArrayList<>());
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

    public Patient getPatient() {
        return patient;
    }

    public List<Message> getMessages(){
        return messages;
    }
    public void addMessages(Message message){
        messages.add(message);

    }

    public void setPatient(Patient patient) {
        this.patient = patient;
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

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }
}