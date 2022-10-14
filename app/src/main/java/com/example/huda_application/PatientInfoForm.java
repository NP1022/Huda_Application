package com.example.huda_application;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class PatientInfoForm extends AppCompatActivity implements View.OnClickListener {

    private TextView LabAccess;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_patient_info_form);

        LabAccess = (TextView) findViewById(R.id.nextForm6);
        LabAccess.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.nextForm6) {
            Intent LabAccess = new Intent(this, LabAccess.class);
            startActivity(LabAccess);
        }
    }
}