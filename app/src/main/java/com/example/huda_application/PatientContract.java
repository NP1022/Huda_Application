package com.example.huda_application;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

public class PatientContract extends AppCompatActivity implements View.OnClickListener
{

    private TextView AuthorizationForm;
    private String visitReasonTxt,firstNameTxt , lastNameTxt,patientSexTxt,patientDOBTxt,patientHomeNumTxt,patientCellNumTxt,patientAddTxt,
            patientCityTxt,patientStateTxt,patientZipCodeTxt,patientPrefNumberTxt,patientConsentCallTxt,patientConsentTextTxt,patientInsuranceTxt,
            patientEmailTxt,prefLangTxt,translatorTxt,maritalTxt,houseIncomeTxt,houseHoldTxt,occupationTxt,veteranTxt,emergencyNameTxt,relationshipTxt,
            contactPhoneTxt,patientConsentName,patientSignedText,patientSignatureText,consentDateTxt,patientRaceTxt,patientEthnicityTxt,patientIncomeTxt,
            patientEmpTxt,dateTxt,patientSSNTxt;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_patient_contract);
        Bundle extras = getIntent().getExtras();

        if (extras != null) {
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
       // Log.i("info  ", "The user name in the application is   " + lastNameTxt + firstNameTxt );
        AuthorizationForm = (TextView) findViewById(R.id.nextForm2);
        AuthorizationForm.setOnClickListener(this);
    }

    @Override
    public void onClick(View view)
    {
        if (view.getId() == R.id.nextForm2)
        {
            Intent AuthorizationForm = new Intent (this, AuthorizationForm.class);
            AuthorizationForm.putExtra("lastNameTxt" ,lastNameTxt );
            AuthorizationForm.putExtra("dateTxt" ,dateTxt );
            AuthorizationForm.putExtra("firstNameTxt" ,firstNameTxt );
            AuthorizationForm.putExtra("visitReasonTxt" ,visitReasonTxt );
            AuthorizationForm.putExtra("patientSexTxt" ,patientSexTxt );
//AuthorizatAuthorizationForm
            AuthorizationForm.putExtra("patientDOBTxt" ,patientDOBTxt );
            AuthorizationForm.putExtra("patientSSNTxt" ,firstNameTxt );
            AuthorizationForm.putExtra("patientHomeNumTxt" ,patientHomeNumTxt );
            AuthorizationForm.putExtra("patientCellNumTxt" ,patientCellNumTxt );
            AuthorizationForm.putExtra("patientAddTxt" ,patientAddTxt );
            AuthorizationForm.putExtra("patientCityTxt" ,patientCityTxt );
            AuthorizationForm.putExtra("patientStateTxt" ,patientStateTxt );
            AuthorizationForm.putExtra("patientZipCodeTxt" ,patientZipCodeTxt );
            AuthorizationForm.putExtra("patientPrefNumberTxt" ,patientPrefNumberTxt );
            AuthorizationForm.putExtra("patientConsentCallTxt" ,patientConsentCallTxt );
            AuthorizationForm.putExtra("patientConsentTextTxt" ,patientConsentTextTxt );
            AuthorizationForm.putExtra("patientInsuranceTxt" ,patientInsuranceTxt );
            AuthorizationForm.putExtra("patientEmailTxt" ,patientEmailTxt );
            AuthorizationForm.putExtra("prefLangTxt" ,prefLangTxt );
            AuthorizationForm.putExtra("translatorTxt" ,translatorTxt );
            AuthorizationForm.putExtra("maritalTxt" ,maritalTxt );
            AuthorizationForm.putExtra("houseIncomeTxt" ,houseIncomeTxt );
            AuthorizationForm.putExtra("houseHoldTxt" ,houseHoldTxt );
            AuthorizationForm.putExtra("occupationTxt" ,occupationTxt );
            AuthorizationForm.putExtra("veteranTxt" ,veteranTxt );
            AuthorizationForm.putExtra("emergencyNameTxt" ,emergencyNameTxt );
            AuthorizationForm.putExtra("relationshipTxt" ,relationshipTxt );
            AuthorizationForm.putExtra("contactPhoneTxt" ,contactPhoneTxt );
            AuthorizationForm.putExtra("patientConsentName" ,patientConsentName );
            AuthorizationForm.putExtra("patientSignedText" ,patientSignedText );
            AuthorizationForm.putExtra("patientSignatureText" ,patientSignatureText );
            AuthorizationForm.putExtra("consentDateTxt" ,consentDateTxt );
            startActivity(AuthorizationForm);
        }
    }
}