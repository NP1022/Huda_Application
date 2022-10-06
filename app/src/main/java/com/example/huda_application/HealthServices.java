package com.example.huda_application;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Button;

public class HealthServices extends AppCompatActivity {
    TextView PrimaryCareButton, MentalHealthButton, VisionCareButton, NewPatientsButton, DentalCareButton, SpecialtyCareButton;
    String primaryCareText = "Primary Care/Internal Medicine\nPrimary care providers are typically the first line\n" + "of contact for patients; treating general health \n" + "concerns, providing education, medications and \n" + "specialty referrals as needed.\n" + "HUDA aims to bring primary care and specialized\n" + "services to uninsured and under-insured in the Metro " + "Detroit area. We provide preventative primary care\n services in a person-focused community health clinic, our goal is to make Michigan healthier!  The HUDA clinic provides FREE medications to our patients, and also provides laboratory services at no-cost to our patients. ";
    String mentalHealthText = "";
    String visionCareText = "";
    String newPatientsText = "";
    String dentalCare = "";
    String specialtyCare = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_health_services);
        PrimaryCareButton = (TextView) findViewById(R.id.primaryCareButton);
        MentalHealthButton = (TextView) findViewById(R.id.primaryCareButton);
        VisionCareButton = (TextView) findViewById(R.id.primaryCareButton);
        NewPatientsButton = (TextView) findViewById(R.id.primaryCareButton);
        DentalCareButton = (TextView) findViewById(R.id.primaryCareButton);
        SpecialtyCareButton = (TextView) findViewById(R.id.primaryCareButton);

        HealthServicesButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (view.getId() == R.id.prim)
                HealthServicesClick.setText(primaryCareText);
                if(HealthServicesClick.getVisibility() == View.GONE) {
                    HealthServicesClick.setVisibility(View.VISIBLE);
                }
                else {
                    HealthServicesClick.setVisibility(View.GONE);
                }
            }
        });
    }
}