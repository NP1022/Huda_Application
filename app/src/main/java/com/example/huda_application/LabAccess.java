package com.example.huda_application;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class LabAccess extends AppCompatActivity implements View.OnClickListener{

    private TextView patientPortal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lab_access);

        patientPortal = (TextView) findViewById(R.id.returnPatientPortal);
        patientPortal.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.returnPatientPortal) {
            Intent patientPortal = new Intent(this, PatientsPage.class);
            startActivity(patientPortal);
        }
    }
}