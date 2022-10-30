package com.example.huda_application.user;


import java.io.Serializable;

public class PatientFormData implements Serializable {

    private String firstName;
    private String lastName;
    private String date;

    public PatientFormData(String firstName, String lastName, String date) {
        this.lastName = lastName;
        this.firstName = firstName;
        this.date = date;
    }

    public PatientFormData() {
        this("", "", "");
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

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}