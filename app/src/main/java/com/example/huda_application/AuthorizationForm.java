package com.example.huda_application;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

public class AuthorizationForm extends AppCompatActivity implements View.OnClickListener{

    private TextView PersonalRepresentative;
    private String lastname;
    private String visitReasonTxt,firstNameTxt , lastNameTxt,patientSexTxt,patientDOBTxt,patientHomeNumTxt,patientCellNumTxt,patientAddTxt,
            patientCityTxt,patientStateTxt,patientZipCodeTxt,patientPrefNumberTxt,patientConsentCallTxt,patientConsentTextTxt,patientInsuranceTxt,
            patientEmailTxt,prefLangTxt,translatorTxt,maritalTxt,houseIncomeTxt,houseHoldTxt,occupationTxt,veteranTxt,emergencyNameTxt,relationshipTxt,
            contactPhoneTxt,patientConsentName,patientSignedText,patientSignatureText,consentDateTxt,patientRaceTxt,patientEthnicityTxt,patientIncomeTxt,
            patientEmpTxt,dateTxt,patientSSNTxt;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_authorization_form);
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
        PersonalRepresentative = (TextView) findViewById(R.id.nextForm3);
        PersonalRepresentative.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.nextForm3){
            Intent PersonalRepresentative = new Intent (this, PersonalRepresentative.class);
            PersonalRepresentative.putExtra("lastNameTxt" ,lastNameTxt );
            PersonalRepresentative.putExtra("dateTxt" ,dateTxt );
            PersonalRepresentative.putExtra("firstNameTxt" ,firstNameTxt );
            PersonalRepresentative.putExtra("visitReasonTxt" ,visitReasonTxt );
            PersonalRepresentative.putExtra("patientSexTxt" ,patientSexTxt );
//AuthorizatPersonalRepresentative
            PersonalRepresentative.putExtra("patientDOBTxt" ,patientDOBTxt );
            PersonalRepresentative.putExtra("patientSSNTxt" ,firstNameTxt );
            PersonalRepresentative.putExtra("patientHomeNumTxt" ,patientHomeNumTxt );
            PersonalRepresentative.putExtra("patientCellNumTxt" ,patientCellNumTxt );
            PersonalRepresentative.putExtra("patientAddTxt" ,patientAddTxt );
            PersonalRepresentative.putExtra("patientCityTxt" ,patientCityTxt );
            PersonalRepresentative.putExtra("patientStateTxt" ,patientStateTxt );
            PersonalRepresentative.putExtra("patientZipCodeTxt" ,patientZipCodeTxt );
            PersonalRepresentative.putExtra("patientPrefNumberTxt" ,patientPrefNumberTxt );
            PersonalRepresentative.putExtra("patientConsentCallTxt" ,patientConsentCallTxt );
            PersonalRepresentative.putExtra("patientConsentTextTxt" ,patientConsentTextTxt );
            PersonalRepresentative.putExtra("patientInsuranceTxt" ,patientInsuranceTxt );
            PersonalRepresentative.putExtra("patientEmailTxt" ,patientEmailTxt );
            PersonalRepresentative.putExtra("prefLangTxt" ,prefLangTxt );
            PersonalRepresentative.putExtra("translatorTxt" ,translatorTxt );
            PersonalRepresentative.putExtra("maritalTxt" ,maritalTxt );
            PersonalRepresentative.putExtra("houseIncomeTxt" ,houseIncomeTxt );
            PersonalRepresentative.putExtra("houseHoldTxt" ,houseHoldTxt );
            PersonalRepresentative.putExtra("occupationTxt" ,occupationTxt );
            PersonalRepresentative.putExtra("veteranTxt" ,veteranTxt );
            PersonalRepresentative.putExtra("emergencyNameTxt" ,emergencyNameTxt );
            PersonalRepresentative.putExtra("relationshipTxt" ,relationshipTxt );
            PersonalRepresentative.putExtra("contactPhoneTxt" ,contactPhoneTxt );
            PersonalRepresentative.putExtra("patientConsentName" ,patientConsentName );
            PersonalRepresentative.putExtra("patientSignedText" ,patientSignedText );
            PersonalRepresentative.putExtra("patientSignatureText" ,patientSignatureText );
            PersonalRepresentative.putExtra("consentDateTxt" ,consentDateTxt );

            startActivity(PersonalRepresentative);
        }

    }
}