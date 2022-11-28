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

        // if statement to check if the extras are null, if it is null
        // then set data to the extras.getSerializableExtra();
        if (extras != null)
        {
            data =  (PatientFormData)  extras.getSerializableExtra("patientdata");
        }

        LabAccess = (TextView) findViewById(R.id.nextForm6);
        LabAccess.setOnClickListener(this);
    }

    @Override
    public void onClick(View view)
    {
        // if user hits nextForm6 button, then they will be taken to the next form
        // no inputs on this page
        if (view.getId() == R.id.nextForm6)
        {
            Intent LabAccess = new Intent(this, LabAccess.class);
            LabAccess.putExtra("patientdata",data);
            startActivity(LabAccess);
        }
    }
}