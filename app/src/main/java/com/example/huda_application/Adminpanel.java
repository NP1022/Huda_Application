package com.example.huda_application;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class Adminpanel extends AppCompatActivity implements View.OnClickListener {
    private TextView Appointments, Messagepage;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adminpanel);

        Appointments = (TextView) findViewById(R.id.Appointments);
        Messagepage =(TextView) findViewById(R.id.MessagePatient);

        Appointments.setOnClickListener(this);
        Messagepage.setOnClickListener(this);

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
    }
}