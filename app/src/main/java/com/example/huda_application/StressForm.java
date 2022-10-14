package com.example.huda_application;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class StressForm extends AppCompatActivity implements View.OnClickListener{

    private TextView PatientInfoForm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stress_form);

        PatientInfoForm = (TextView) findViewById(R.id.nextForm5);
        PatientInfoForm.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.nextForm5) {
            Intent PatientInfoForm = new Intent(this, PatientInfoForm.class);
            startActivity(PatientInfoForm);
        }
    }
}