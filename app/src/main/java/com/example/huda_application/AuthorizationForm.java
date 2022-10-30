package com.example.huda_application;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.regex.Pattern;

public class AuthorizationForm extends AppCompatActivity implements View.OnClickListener
{
    private static final Pattern DATE_PATTERN = Pattern.compile(
            "^((0[1-9]|1[012])-(0[1-9]|[12][0-9]|3[01])-(19|2[0-9])[0-9]{2})$"); // date pattern match

    private static final Pattern SSN_PATTERN = Pattern.compile("^(?!666|000|9\\d{2})\\d{3}"
            + "-(?!00)\\d{2}-"
            +"(?!0{4})\\d{4}$");  // SSN pattern match

    private static final Pattern ZIPCODE_PATTERN = Pattern.compile("^\\d{5}$"); // zipcode

    private static final Pattern PHONE_PATTERN = Pattern.compile("^[2-9]\\d{2}-\\d{3}-\\d{4}$"); // pattern to make sure phone numbers are valid format

    private static Pattern PATIENTSEX_PATTERN = Pattern.compile("^(?:m|M|male|Male|f|F|female|Female)$"); // pattern to check patient sex input

    private static Pattern CONTACTPREF_PATTERN = Pattern.compile("^(?:home|Home|mobile|Mobile)$");

    private static Pattern CONSENT_PATTERN = Pattern.compile("^(?:yes|Yes|No|no)$");

    private static Pattern MARITAL_PATTERN = Pattern.compile("^(?:Single|single|Married|married|partner|Partner|Separated|separated|Divorced|divorced|Widowed|widowed)$");

    private TextView PersonalRepresentative;
    private String lastname;
    private String visitReasonTxt,firstNameTxt , lastNameTxt,patientSexTxt,patientDOBTxt,patientHomeNumTxt,patientCellNumTxt,patientAddTxt,
            patientCityTxt,patientStateTxt,patientZipCodeTxt,patientPrefNumberTxt,patientConsentCallTxt,patientConsentTextTxt,patientInsuranceTxt,
            patientEmailTxt,prefLangTxt,translatorTxt,maritalTxt,houseIncomeTxt,houseHoldTxt,occupationTxt,veteranTxt,emergencyNameTxt,relationshipTxt,
            contactPhoneTxt,patientConsentName,patientSignedText,patientSignatureText,consentDateTxt,patientRaceTxt,patientEthnicityTxt,patientIncomeTxt,
            patientEmpTxt,dateTxt,patientSSNTxt;

    final EditText patientName = findViewById(R.id.patientNamePrinted3);
    final EditText patientSignature = findViewById(R.id.patientNameSignature3);
    final EditText consentDate = findViewById(R.id.consentFormDate3);

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_authorization_form);
        Bundle extras = getIntent().getExtras();

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
        //Log.i("info  ", "The user name in the application is   " + lastNameTxt + firstNameTxt );
        PersonalRepresentative = (TextView) findViewById(R.id.nextForm3);
        PersonalRepresentative.setOnClickListener(this);

    }

    @Override
    public void onClick(View view)
    {
        final String patientNameStr = patientName.getText().toString().trim();
        final String patientSigStr = patientSignature.getText().toString().trim();
        final String patientConDateStr = consentDate.getText().toString().trim();


        if(patientNameStr.length() > 30 || TextUtils.isEmpty(patientNameStr))
        {
            Toast.makeText(AuthorizationForm.this,"Please enter your name",Toast.LENGTH_LONG).show();
            patientName.setError("Name is required");
            patientName.requestFocus();
        }
        else if(patientSigStr.length() > 30 || TextUtils.isEmpty(patientSigStr))
        {
            Toast.makeText(AuthorizationForm.this,"Please enter your signature",Toast.LENGTH_LONG).show();
            patientSignature.setError("Signature is required");
            patientSignature.requestFocus();
        }
        else if(!DATE_PATTERN.matcher(patientConDateStr).matches())
        {
            Toast.makeText(AuthorizationForm.this,"Please use MM-DD-YYYY",Toast.LENGTH_LONG).show();
            consentDate.setError("Format is required");
            consentDate.requestFocus();
        }
        else if(TextUtils.isEmpty(patientConDateStr))
        {
            Toast.makeText(AuthorizationForm.this,"Please fill in date",Toast.LENGTH_LONG).show();
            consentDate.setError("Date is required");
            consentDate.requestFocus();
        }
        else
        {
            if (view.getId() == R.id.nextForm3)
            {
                //AuthorizatPersonalRepresentative
                Intent PersonalRepresentative = new Intent (this, PersonalRepresentative.class);
                PersonalRepresentative.putExtra("lastNameTxt" ,lastNameTxt );
                PersonalRepresentative.putExtra("dateTxt" ,dateTxt );
                PersonalRepresentative.putExtra("firstNameTxt" ,firstNameTxt );
                PersonalRepresentative.putExtra("visitReasonTxt" ,visitReasonTxt );
                PersonalRepresentative.putExtra("patientSexTxt" ,patientSexTxt );
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
}