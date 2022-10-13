package com.example.huda_application;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class PatientsPage extends AppCompatActivity implements View.OnClickListener {

    private TextView NewPatient;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_patients_page);

        NewPatient = (TextView) findViewById(R.id.Forms);
        NewPatient.setOnClickListener(this);


    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.Forms){
            Intent NewPatient = new Intent(this ,PatientForm.class);
            startActivity(NewPatient);

        }
        
        if (view.getId() == R.id.nextForm){
            Intent PatientContract = new Intent (this, PatientContract.class);
            startActivity(PatientContract);
        }

    }
}