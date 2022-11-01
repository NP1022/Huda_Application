package com.example.huda_application;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class NewLogin extends AppCompatActivity implements View.OnClickListener {

    TextView login, signUp;
    TextView PrimaryCare_Button, MentalHealth_Button, VisionCare_Button, NewPatients_Button, DentalCare_Button, SpecialtyCare_Button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_login);

        login = (TextView) findViewById(R.id.LoginButton);
        signUp = (TextView) findViewById(R.id.SignUpButton);

        login.setOnClickListener((View.OnClickListener) this);
        signUp.setOnClickListener((View.OnClickListener) this);

        PrimaryCare_Button = (TextView) findViewById(R.id.PrimaryHeader);
        MentalHealth_Button = (TextView) findViewById(R.id.MentalHeader);
        VisionCare_Button = (TextView) findViewById(R.id.VisionHeader);
        DentalCare_Button = (TextView) findViewById(R.id.DentalHeader);
        SpecialtyCare_Button = (TextView) findViewById(R.id.SpecialtyHeader);
        PrimaryCare_Button.setOnClickListener(this);
        MentalHealth_Button.setOnClickListener(this);
        VisionCare_Button.setOnClickListener(this);
        DentalCare_Button.setOnClickListener(this);
        SpecialtyCare_Button.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.LoginButton) {
            Intent LoginPage = new Intent(this, MainActivity.class);
            startActivity(LoginPage);
        } else if (view.getId() == R.id.SignUpButton) {
            Intent RegPage = new Intent(this, RegisterAccount.class);
            startActivity(RegPage);
        }
        else if (view.getId() == R.id.PrimaryHeader) {
            Intent Primary = new Intent(this, PrimaryServices.class);
            startActivity(Primary);
        }
        else if (view.getId() == R.id.MentalHeader) {
            Intent Mental = new Intent(this, MentalHealth.class);
            startActivity(Mental);
        }
        else if (view.getId() == R.id.VisionHeader) {
            Intent Vision = new Intent(this, VisionHealth.class);
            startActivity(Vision);
        }
//
        else if (view.getId() == R.id.DentalHeader) {
            Intent Dental = new Intent(this, DentalHealth.class);
            startActivity(Dental);
        }
        else if (view.getId() == R.id.SpecialtyHeader) {
            Intent Specialty = new Intent(this, SpecialtyHealth.class);
            startActivity(Specialty);
        }
    }
}