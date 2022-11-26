package com.example.huda_application.user;

import java.io.Serializable;

public class Message implements Serializable {

    private String date;
    private String message;                             // Message storage class that is used to be communicated to firebase the 3 fields are required.
    private boolean read;

   public  Message(String date , String message, Boolean read){
        this.date = date;
        this.message = message;                         // Message constructor that is being used once the fields are added to them
        this.read =read;
    }


    public String getDate() {
        return date;
    }
                                                                        //setter and getters for the date of the message that is being sent to the admin
    public void setMessage(String message) {
        this.message = message;
    }
                                                                            //setters and getters for the message that is being communicated to the database of the application
    public void setDate(String date) {
        this.date = date;
    }

    public String getMessage() {
        return message;
    }

    public boolean isRead() {
        return read;
    }
                                                                            // The isRead field is used to check if the message is being send to the patient in the application
    public void setRead(boolean flag) {
        this.read = flag;
    }
}
