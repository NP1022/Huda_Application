package com.example.huda_application;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.TextView;

public class PersonalRepresentative extends AppCompatActivity implements View.OnClickListener{

    private TextView StressForm;
    private String lastname;
    private String visitReasonTxt,firstNameTxt , lastNameTxt,patientSexTxt,patientDOBTxt,patientHomeNumTxt,patientCellNumTxt,patientAddTxt,
            patientCityTxt,patientStateTxt,patientZipCodeTxt,patientPrefNumberTxt,patientConsentCallTxt,patientConsentTextTxt,patientInsuranceTxt,
            patientEmailTxt,prefLangTxt,translatorTxt,maritalTxt,houseIncomeTxt,houseHoldTxt,occupationTxt,veteranTxt,emergencyNameTxt,relationshipTxt,
            contactPhoneTxt,patientConsentName,patientSignedText,patientSignatureText,consentDateTxt,patientRaceTxt,patientEthnicityTxt,patientIncomeTxt,
            patientEmpTxt,dateTxt,patientSSNTxt;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_personal_representative);
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
        //Log.i("info  ", "The user name in the application is   " + lastNameTxt + firstNameTxt );
        StressForm = (TextView) findViewById(R.id.nextForm4);
        StressForm.setOnClickListener(this);
    }


    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.nextForm4) {
            Intent StressForm = new Intent(this, StressForm.class);
            StressForm.putExtra("lastNameTxt" ,lastNameTxt );
            StressForm.putExtra("dateTxt" ,dateTxt );
            StressForm.putExtra("firstNameTxt" ,firstNameTxt );
            StressForm.putExtra("visitReasonTxt" ,visitReasonTxt );
            StressForm.putExtra("patientSexTxt" ,patientSexTxt );
//AuthorizatStressForm
            StressForm.putExtra("patientDOBTxt" ,patientDOBTxt );
            StressForm.putExtra("patientSSNTxt" ,firstNameTxt );
            StressForm.putExtra("patientHomeNumTxt" ,patientHomeNumTxt );
            StressForm.putExtra("patientCellNumTxt" ,patientCellNumTxt );
            StressForm.putExtra("patientAddTxt" ,patientAddTxt );
            StressForm.putExtra("patientCityTxt" ,patientCityTxt );
            StressForm.putExtra("patientStateTxt" ,patientStateTxt );
            StressForm.putExtra("patientZipCodeTxt" ,patientZipCodeTxt );
            StressForm.putExtra("patientPrefNumberTxt" ,patientPrefNumberTxt );
            StressForm.putExtra("patientConsentCallTxt" ,patientConsentCallTxt );
            StressForm.putExtra("patientConsentTextTxt" ,patientConsentTextTxt );
            StressForm.putExtra("patientInsuranceTxt" ,patientInsuranceTxt );
            StressForm.putExtra("patientEmailTxt" ,patientEmailTxt );
            StressForm.putExtra("prefLangTxt" ,prefLangTxt );
            StressForm.putExtra("translatorTxt" ,translatorTxt );
            StressForm.putExtra("maritalTxt" ,maritalTxt );
            StressForm.putExtra("houseIncomeTxt" ,houseIncomeTxt );
            StressForm.putExtra("houseHoldTxt" ,houseHoldTxt );
            StressForm.putExtra("occupationTxt" ,occupationTxt );
            StressForm.putExtra("veteranTxt" ,veteranTxt );
            StressForm.putExtra("emergencyNameTxt" ,emergencyNameTxt );
            StressForm.putExtra("relationshipTxt" ,relationshipTxt );
            StressForm.putExtra("contactPhoneTxt" ,contactPhoneTxt );
            StressForm.putExtra("patientConsentName" ,patientConsentName );
            StressForm.putExtra("patientSignedText" ,patientSignedText );
            StressForm.putExtra("patientSignatureText" ,patientSignatureText );
            StressForm.putExtra("consentDateTxt" ,consentDateTxt );

            startActivity(StressForm);
        }
    }
}