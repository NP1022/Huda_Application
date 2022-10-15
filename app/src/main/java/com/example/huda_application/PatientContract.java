package com.example.huda_application;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class PatientContract extends AppCompatActivity implements View.OnClickListener{

    private TextView AuthorizationForm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_patient_contract);

        AuthorizationForm = (TextView) findViewById(R.id.nextForm2);
        AuthorizationForm.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.nextForm2){
            Intent AuthorizationForm = new Intent (this, AuthorizationForm.class);
            startActivity(AuthorizationForm);
        }
    }
}