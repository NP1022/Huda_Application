package com.example.huda_application;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.huda_application.user.UserManager;
import com.google.firebase.auth.FirebaseAuth;

public class Adminpanel extends AppCompatActivity implements View.OnClickListener {
    private TextView Appointments, Messagepage, signout, AppointmentManager , AppointmentManager2;
    private FirebaseAuth mAuth;// Variables definition for the textviews on the admin panel page
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adminpanel);
        AppointmentManager = findViewById(R.id.Appointments_Manager);
        Appointments = (TextView) findViewById(R.id.Appointments);                                  // setting up all the textview equal to the postion that is found in the xml
        Messagepage =(TextView) findViewById(R.id.MessagePatient);
        signout = findViewById(R.id.logoutButton_admin);
        AppointmentManager2 = findViewById(R.id.Appointments_Manager2);


        Appointments.setOnClickListener(this);
        Messagepage.setOnClickListener(this);                                                       //set on click listener for clicking on the textview on the page
        signout.setOnClickListener(this);
        AppointmentManager.setOnClickListener(this);
        AppointmentManager2.setOnClickListener(this);
        mAuth = FirebaseAuth.getInstance();

    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.Appointments){
            Intent appointmentpage = new Intent(this , AdminPage.class);               //if the appointments button is clicked the appointments page will open
            startActivity(appointmentpage);
        }

        else if  (view.getId() == R.id.MessagePatient){
            Intent Message_Patient = new Intent(this , Messagepage.class);             //if the Message Patient button is clicked the Message page will open
            startActivity(Message_Patient);
        }
        else if(view.getId() == R.id.logoutButton_admin) {

            mAuth.signOut();
            startActivity(new Intent(Adminpanel.this, MainActivity.class));             //if the Logout button is clicked the Login page will open
        }
        else if(view.getId() == R.id.Appointments_Manager) {

            startActivity(new Intent(Adminpanel.this, AvailableAppointments.class));    //if the Appointment manager button is clicked the Login page will open
        }
        else if(view.getId() == R.id.Appointments_Manager2) {

            mAuth.signOut();                                                                        // Customize button to redirect to the customize page
            startActivity(new Intent(Adminpanel.this, CustomizeTime.class));
        }
    }
}