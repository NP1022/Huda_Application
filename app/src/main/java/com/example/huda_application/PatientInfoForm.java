package com.example.huda_application;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.huda_application.user.PatientFormData;

public class PatientInfoForm extends AppCompatActivity implements View.OnClickListener {

    private PatientFormData data;
    private TextView LabAccess;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_patient_info_form);
        Intent extras = getIntent();
        if (extras != null)
        {
            data =  (PatientFormData)  extras.getSerializableExtra("patientdata");
        }
        // Log.i("info  ", "The user name in the application is   " + lastNameTxt + firstNameTxt );
        LabAccess = (TextView) findViewById(R.id.nextForm6);
        LabAccess.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.nextForm6)
        {
            Intent LabAccess = new Intent(this, LabAccess.class);
            LabAccess.putExtra("patientdata",data);
            startActivity(LabAccess);
        }
    }
}