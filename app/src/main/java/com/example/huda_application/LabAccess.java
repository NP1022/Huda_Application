package com.example.huda_application;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.huda_application.firebase.FirebaseClient;
import com.example.huda_application.user.UserManager;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;

public class LabAccess extends AppCompatActivity
{

    private FirebaseAuth mAuth;
    DatabaseReference ref;

    private TextView patientPortal;
    private String visitReasonTxt,firstNameTxt , lastNameTxt,patientSexTxt,patientDOBTxt,patientHomeNumTxt,patientCellNumTxt,patientAddTxt,
            patientCityTxt,patientStateTxt,patientZipCodeTxt,patientPrefNumberTxt,patientConsentCallTxt,patientConsentTextTxt,patientInsuranceTxt,
            patientEmailTxt,prefLangTxt,translatorTxt,maritalTxt,houseIncomeTxt,houseHoldTxt,occupationTxt,veteranTxt,emergencyNameTxt,relationshipTxt,
            contactPhoneTxt,patientConsentName,patientSignedText,patientSignatureText,consentDateTxt,patientRaceTxt,patientEthnicityTxt,patientIncomeTxt,
            patientEmpTxt,dateTxt,patientSSNTxt;


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lab_access);
        Bundle extras = getIntent().getExtras();

        Button buttonSubmit = findViewById(R.id.returnPatientPortal); // set variable for button action

        if (extras != null)
        {
            dateTxt = extras.getString( "dateTxt");
            lastNameTxt = extras.getString( "lastNameTxt");
            firstNameTxt = extras.getString( "firstNameTxt");
            visitReasonTxt = extras.getString( "visitReasonTxt");
            patientSexTxt = extras.getString( "patientSexTxt");
            patientSSNTxt = extras.getString( "patientSSNTxt");
            patientDOBTxt = extras.getString( "patientDOBTxt");
            patientHomeNumTxt = extras.getString( "patientHomeNumTxt");
            patientCellNumTxt = extras.getString( "patientCellNumTxt");
            patientAddTxt = extras.getString( "patientAddTxt");
            patientCityTxt = extras.getString( "patientCityTxt");
            patientStateTxt = extras.getString( "patientStateTxt");
            patientZipCodeTxt = extras.getString( "patientZipCodeTxt");
            patientPrefNumberTxt = extras.getString( "patientPrefNumberTxt");
            patientConsentCallTxt = extras.getString( "patientConsentCallTxt");
            patientConsentTextTxt = extras.getString( "patientConsentTextTxt");
            patientInsuranceTxt = extras.getString( "patientInsuranceTxt");
            patientEmailTxt = extras.getString( "patientEmailTxt");
            prefLangTxt = extras.getString( "prefLangTxt");
            translatorTxt = extras.getString( "translatorTxt");
            maritalTxt = extras.getString( "maritalTxt");
            houseIncomeTxt = extras.getString( "houseIncomeTxt");
            houseHoldTxt = extras.getString( "houseHoldTxt");
            occupationTxt = extras.getString( "occupationTxt");
            veteranTxt = extras.getString( "veteranTxt");
            emergencyNameTxt = extras.getString( "emergencyNameTxt");
            relationshipTxt = extras.getString( "relationshipTxt");
            contactPhoneTxt = extras.getString( "contactPhoneTxt");
            patientConsentName = extras.getString( "patientConsentName");
            patientSignedText = extras.getString( "patientSignedText");
            patientSignatureText = extras.getString( "patientSignatureText");
            consentDateTxt = extras.getString( "consentDateTxt");
            patientRaceTxt = extras.getString( "patientRaceTxt");
            patientEthnicityTxt = extras.getString( "patientEthnicityTxt");
            patientIncomeTxt = extras.getString( "patientIncomeTxt");
            patientEmpTxt = extras.getString( "patientEmpTxt");

        }

        DAOPatient dao = new DAOPatient();

        buttonSubmit.setOnClickListener(new View.OnClickListener()
        {
            @Override

            public void onClick(View view)
            {
                Patient patient = new Patient(dateTxt,visitReasonTxt,lastNameTxt,firstNameTxt,patientSexTxt,patientDOBTxt,patientAddTxt,patientCityTxt,patientStateTxt,patientZipCodeTxt,
                        patientSSNTxt,patientHomeNumTxt,patientCellNumTxt,patientPrefNumberTxt,patientConsentCallTxt,patientConsentTextTxt,patientInsuranceTxt,patientEmailTxt,
                        patientRaceTxt, patientEthnicityTxt, translatorTxt, maritalTxt,houseIncomeTxt, patientIncomeTxt, houseHoldTxt, patientEmpTxt,occupationTxt, veteranTxt, emergencyNameTxt,
                        relationshipTxt, contactPhoneTxt, patientConsentName, patientSignedText, patientSignatureText, consentDateTxt);

                UserManager.getInstance().getCurrentUser().setPatient(patient);
                FirebaseClient.updateUser(UserManager.getInstance().getCurrentUser()).addOnSuccessListener(suc->
                {
                    Toast.makeText(LabAccess.this,"User in RealTime database inserted",Toast.LENGTH_LONG).show();
                }).addOnFailureListener(er->
                {
                    Toast.makeText(LabAccess.this,""+er.getMessage(),Toast.LENGTH_LONG).show();
                });
            }
        });

        //Log.i("info  ", "The user name in the application is   " + lastNameTxt + firstNameTxt );

    }

}

//    @Override
//    public void onClick(View view) {
//        if (view.getId() == R.id.returnPatientPortal)
//        {
//            Intent patientPortal = new Intent(this, PatientsPage.class);
//            startActivity(patientPortal);
//        }
//    }