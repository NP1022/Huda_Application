package com.example.huda_application;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class PatientsPage extends AppCompatActivity implements View.OnClickListener {

    private TextView NewPatient , Checkin;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_patients_page);

        NewPatient = (TextView) findViewById(R.id.Forms);
        Checkin = (TextView) findViewById(R.id.Checkin);

        NewPatient.setOnClickListener(this);
        Checkin.setOnClickListener(this);

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
       else if (view.getId() == R.id.Checkin){
            Intent check_in = new Intent(PatientsPage.this , ApptRequest.class);
            startActivity(check_in);

        }


    }
}