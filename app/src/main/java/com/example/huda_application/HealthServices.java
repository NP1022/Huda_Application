package com.example.huda_application;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;


public class HealthServices extends AppCompatActivity implements View.OnClickListener {
    TextView HealthService_Text, PrimaryCare_Button, MentalHealth_Button, VisionCare_Button, NewPatients_Button, DentalCare_Button, SpecialtyCare_Button;
    String primaryCareText = "Primary Care/Internal Medicine\nPrimary care providers are typically the first line\n" + "of contact for patients; treating general health \n" + "concerns, providing education, medications and \n" + "specialty referrals as needed.\n" + "HUDA aims to bring primary care and specialized\n" + "services to uninsured and under-insured in the Metro " + "Detroit area. We provide preventative primary care\n services in a person-focused community health clinic, our goal is to make Michigan healthier!  The HUDA clinic provides FREE medications to our patients, and also provides laboratory services at no-cost to our patients. ";
    String mentalHealthText = "We have psychiatrists on staff along with our licensed social workers to provide therapy and treatment for a wide range of mental health concerns. We offer free counseling and treatment to veterans, homeless, uninsured, and the working underinsured.  \n" + "\n" + "Since the onset of the Covid-19 pandemic our psychiatry services have transitioned to telemedicine. We currently have 2 Psychiatrists, a Mental Health Nurse Practitioner and a Therapist who all volunteer with HUDA Clinic allowing us to provide treatment for a wide range of mental health concerns.";
    String visionCareText = "Vision screenings is an effective method to identify individuals with visual impairment or eye conditions that are likely to lead to vision loss. We perform screenings in-house and provide no-cost eye wear and referrals to low-cost local ophthalmologists as needed.";
    String newPatientsText = "New patients are always welcome at the HUDA Clinic! You can schedule your initial appointment by visiting our clinic during our regular hours, or you may also come for a walk-in appointment before 10:30 AM, or until we reach our capacity on the days we offer walk-ins (see below). \n" + "\n" + "New patients must bring with them a valid photo ID, and must be at least 18 years old. ";
    String dentalCareText = "Many people wait to have necessary dental work done because they don’t have insurance, however small dental problems can quickly become incredibly painful if left alone. Come in to HUDA for cleanings, fillings, extractions and exams to keep your teeth healthy and receive a referral to a specialist if needed.";
    String specialtyCareText = "Within the field of medicine, there are many sub-specialties when coordinating care to patients. At HUDA, we provide a range of specialty care and consultations to our patients, with the aim of expanding access to healthcare services. We have created an integrated health model at HUDA through opening multiple specialty services creating a place where most patient needs can be met. All specialty services require a referral from a HUDA primary care provider.";

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_health_services);

        HealthService_Text = (TextView) findViewById(R.id.HealthServiceText);
        PrimaryCare_Button = (TextView) findViewById(R.id.primaryCareButton);
        MentalHealth_Button = (TextView) findViewById(R.id.mentalHealthButton);
        VisionCare_Button = (TextView) findViewById(R.id.visionCareButton);
        NewPatients_Button = (TextView) findViewById(R.id.newPatientsButton);
        DentalCare_Button = (TextView) findViewById(R.id.dentalCareButton);
        SpecialtyCare_Button = (TextView) findViewById(R.id.specialtyCareButton);
        PrimaryCare_Button.setOnClickListener(this);
        MentalHealth_Button.setOnClickListener(this);
        VisionCare_Button.setOnClickListener(this);
        NewPatients_Button.setOnClickListener(this);
        DentalCare_Button.setOnClickListener(this);
        SpecialtyCare_Button.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.primaryCareButton) {
            HealthService_Text.setText(primaryCareText);
            if (HealthService_Text.getVisibility() == View.GONE) {
                HealthService_Text.setVisibility(View.VISIBLE);
            } else {
                HealthService_Text.setVisibility(View.GONE);
            }
        }
        else if (view.getId() == R.id.mentalHealthButton) {
            HealthService_Text.setText(mentalHealthText);
            if (HealthService_Text.getVisibility() == View.GONE) {
                HealthService_Text.setVisibility(View.VISIBLE);
            } else {
                HealthService_Text.setVisibility(View.GONE);
            }
        }
        else if (view.getId() == R.id.visionCareButton) {
            HealthService_Text.setText(visionCareText);
            if (HealthService_Text.getVisibility() == View.GONE) {
                HealthService_Text.setVisibility(View.VISIBLE);
            } else {
                HealthService_Text.setVisibility(View.GONE);
            }
        }
        else if (view.getId() == R.id.newPatientsButton) {
            HealthService_Text.setText(newPatientsText);
            if (HealthService_Text.getVisibility() == View.GONE) {
                HealthService_Text.setVisibility(View.VISIBLE);
            } else {
                HealthService_Text.setVisibility(View.GONE);
            }
        }
        else if (view.getId() == R.id.dentalCareButton) {
            HealthService_Text.setText(dentalCareText);
            if (HealthService_Text.getVisibility() == View.GONE) {
                HealthService_Text.setVisibility(View.VISIBLE);
            } else {
                HealthService_Text.setVisibility(View.GONE);
            }
        }
        else if (view.getId() == R.id.specialtyCareButton) {
            HealthService_Text.setText(specialtyCareText);
            if (HealthService_Text.getVisibility() == View.GONE) {
                HealthService_Text.setVisibility(View.VISIBLE);
            } else {
                HealthService_Text.setVisibility(View.GONE);
            }
        }
    }
}