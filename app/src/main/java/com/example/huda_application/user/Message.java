package com.example.huda_application.user;

import java.io.Serializable;

public class Message implements Serializable {

    private String date;
    private String message;
    private boolean read;

   public  Message(String date , String message, Boolean read){
        this.date = date;
        this.message = message;
        this.read =read;
    }


    public String getDate() {
        return date;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getMessage() {
        return message;
    }

    public boolean isRead() {
        return read;
    }

    public void setRead(boolean flag) {
        this.read = flag;
    }
}
