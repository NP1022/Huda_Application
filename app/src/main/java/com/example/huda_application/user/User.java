package com.example.huda_application.user;

import com.example.huda_application.Patient;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class User implements Serializable {

    private String userId;                                                      // Class used to store the All the User information that is needed to store in the database
    private String firstName;
    private String lastName;
    private String emailAddress;                                                // The object that is being stored has a list of messages and a list of appointment for that specific user of the application
    private UserType userType;
    private Patient patient;
    private String birthday;
    private final List<Appointment> appointments;
    private final List<Message> messages = new ArrayList<>();

    public User(String firstName, String lastName, String emailAddress,String birthday, UserType userType, List<Appointment> appointmentList) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.emailAddress = emailAddress;                                       //Constructor that is used to store all the the information of the user that is stored in the database
        this.birthday = birthday;
        this.userType = userType;
        this.appointments = appointmentList;
    }

    public User(String firstName, String lastName, String emailAddress,String birthday, String userType) {
        this(firstName, lastName, emailAddress,birthday, UserType.valueOf(userType), new ArrayList<>());
    }                                                                                                                   // 2 different constructors that either made for the admin or a normal user of the application
    public User(String firstName, String lastName, String emailAddress,String birthday, UserType userType) {
        this(firstName, lastName, emailAddress,birthday, userType, new ArrayList<>());
    }



    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {                            //setter and getter for the first name that is being stored
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;                                                        //setter and getter for the last name that is being stored
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmailAddress() {
        return emailAddress;
    }                                                                           //setter and getter for the Email that is being stored

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public UserType getUserType() {
        return userType;
    }
                                                                                  //setter and getter for the UserType that is being stored
    public void setUserType(UserType userType) {
        this.userType = userType;
    }

    public String getUserId() {
        return userId;
    }                                                                              //setter and getter for the Userid that is being stored

    public Patient getPatient() {
        return patient;
    }

    public List<Message> getMessages(){
        return messages;
    }
    public void addMessages(Message message){
        messages.add(message);                                                      //setter and getter for the Messages sent to the patient that is being stored

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
                                                                                    //setter and getter for the appointments for the User that is being stored

    public void addAppointment(Appointment appointment) {
        this.appointments.add(appointment);
    }                                                                               //Function to add an appointment for the User

    public void removeAppointment(Appointment appointment) {
        appointments.remove(appointment);
    }                                                                               //Function to Remove an appointment for the User

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }                                                                                // Function to set the birthday of the User
}