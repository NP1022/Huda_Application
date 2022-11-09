package com.example.huda_application;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.huda_application.user.UserManager;
import com.google.firebase.auth.FirebaseAuth;

public class Adminpanel extends AppCompatActivity implements View.OnClickListener {
    private TextView Appointments, Messagepage, signout;
    private FirebaseAuth mAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adminpanel);

        Appointments = (TextView) findViewById(R.id.Appointments);
        Messagepage =(TextView) findViewById(R.id.MessagePatient);
        signout = findViewById(R.id.logoutButton_admin);
        Appointments.setOnClickListener(this);
        Messagepage.setOnClickListener(this);
        signout.setOnClickListener(this);
        mAuth = FirebaseAuth.getInstance();

    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.Appointments){
            Intent appointmentpage = new Intent(this , AdminPage.class);
            startActivity(appointmentpage);
        }

        else if  (view.getId() == R.id.MessagePatient){
            Intent Message_Patient = new Intent(this , Messagepage.class);
            startActivity(Message_Patient);
        }
        else if(view.getId() == R.id.logoutButton_admin) {

            mAuth.signOut();
            startActivity(new Intent(Adminpanel.this, MainActivity.class));}
    }
}