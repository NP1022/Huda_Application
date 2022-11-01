package com.example.huda_application;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.TextView;

import com.example.huda_application.user.PatientFormData;

public class PersonalRepresentative extends AppCompatActivity implements View.OnClickListener{

    private PatientFormData data;
    private TextView StressForm;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_personal_representative);
        Intent extras = getIntent();

        if (extras != null)
        {
            data =  (PatientFormData)  extras.getSerializableExtra("patientdata");
        }
        //Log.i("info  ", "The user name in the application is   " + lastNameTxt + firstNameTxt );
        StressForm = (TextView) findViewById(R.id.nextForm4);
        StressForm.setOnClickListener(this);
    }


    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.nextForm4)
        {
            Intent StressForm = new Intent(this, StressForm.class);
            StressForm.putExtra("patientdata",data);
            startActivity(StressForm);
        }
    }
}