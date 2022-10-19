package com.example.huda_application;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.regex.Pattern;



public class StressForm extends AppCompatActivity
{
    private static Pattern RESPONSE_PATTERN = Pattern.compile("^(?:yes|Yes|No|no)$");
    private static Pattern RANGE_PATTERN = Pattern.compile("^(?:0|1|2|3)$");
    private static Pattern SUM_PATTERN = Pattern.compile("([1-9]|1[0-9]|2[0-4])$");

    private TextView PatientInfoForm;
    private String visitReasonTxt,firstNameTxt , lastNameTxt,patientSexTxt,patientDOBTxt,patientHomeNumTxt,patientCellNumTxt,patientAddTxt,
            patientCityTxt,patientStateTxt,patientZipCodeTxt,patientPrefNumberTxt,patientConsentCallTxt,patientConsentTextTxt,patientInsuranceTxt,
            patientEmailTxt,prefLangTxt,translatorTxt,maritalTxt,houseIncomeTxt,houseHoldTxt,occupationTxt,veteranTxt,emergencyNameTxt,relationshipTxt,
            contactPhoneTxt,patientConsentName,patientSignedText,patientSignatureText,consentDateTxt,patientRaceTxt,patientEthnicityTxt,patientIncomeTxt,
            patientEmpTxt,dateTxt,patientSSNTxt;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stress_form);
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
        // section 1 questions
        final EditText q1s1 = findViewById(R.id.q1section1);
        final EditText q2s1 = findViewById(R.id.q2section1);
        final EditText q3s1 = findViewById(R.id.q3section1);
        final EditText q4s1 = findViewById(R.id.q4section1);
        final EditText q5s1 = findViewById(R.id.q5section1);
        final EditText q6s1 = findViewById(R.id.q6section1);
        final EditText q7s1 = findViewById(R.id.q7section1);
        final EditText q8s1 = findViewById(R.id.q8section1);

        // section 1 totals
        final EditText s1Totals = findViewById(R.id.section1Totals);

        // section 2 questions
        final EditText q1s2 = findViewById(R.id.q1section2);
        final EditText q2s2 = findViewById(R.id.q2section2);
        final EditText q3s2 = findViewById(R.id.q3section2);
        final EditText q4s2 = findViewById(R.id.q4section2);
        final EditText q5s2 = findViewById(R.id.q5section2);
        final EditText q6s2 = findViewById(R.id.q6section2);
        final EditText q7s2 = findViewById(R.id.q7section2);

        // section 2 totals
        final EditText s2Totals = findViewById(R.id.section2Totals);

        // section 3 questions
        final EditText q1s3 = findViewById(R.id.q1section3);
        final EditText q2s3 = findViewById(R.id.q2section3);
        final EditText q3s3 = findViewById(R.id.q3section3);
        final EditText q4s3 = findViewById(R.id.q4section3);

        Button buttonForm5 = findViewById(R.id.nextForm5); // set variable for button action

        buttonForm5.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                // convert EditText to String section 1
                final String q1s1Text = q1s1.getText().toString().trim();
                final String q2s1Text = q2s1.getText().toString().trim();
                final String q3s1Text = q3s1.getText().toString().trim();
                final String q4s1Text = q4s1.getText().toString().trim();
                final String q5s1Text = q5s1.getText().toString().trim();
                final String q6s1Text = q6s1.getText().toString().trim();
                final String q7s1Text = q7s1.getText().toString().trim();
                final String q8s1Text = q8s1.getText().toString().trim();

                // convert EditText to String section 2
                final String q1s2Text = q1s2.getText().toString().trim();
                final String q2s2Text = q2s2.getText().toString().trim();
                final String q3s2Text = q3s2.getText().toString().trim();
                final String q4s2Text = q4s2.getText().toString().trim();
                final String q5s2Text = q5s2.getText().toString().trim();
                final String q6s2Text = q6s2.getText().toString().trim();
                final String q7s2Text = q7s2.getText().toString().trim();

                // convert EditText to String section 3
                final String q1s3Text = q1s3.getText().toString().trim();
                final String q2s3Text = q2s3.getText().toString().trim();
                final String q3s3Text = q3s3.getText().toString().trim();
                final String q4s3Text = q4s3.getText().toString().trim();

                // totals
                final String s1TotalsText = s1Totals.getText().toString().trim();
                final String s2TotalsText = s2Totals.getText().toString().trim();

                if(TextUtils.isEmpty(q1s1Text) || !RANGE_PATTERN.matcher(q1s1Text).matches())
                {
                    Toast.makeText(StressForm.this,"Must be 0,1,2 or 3",Toast.LENGTH_LONG).show();
                    q1s1.setError("Input is required");
                    q1s1.requestFocus();
                }
                else if(TextUtils.isEmpty(q2s1Text) || !RANGE_PATTERN.matcher(q2s1Text).matches())
                {
                    Toast.makeText(StressForm.this,"Must be 0,1,2 or 3",Toast.LENGTH_LONG).show();
                    q2s1.setError("Input is required");
                    q2s1.requestFocus();
                }
                else if(TextUtils.isEmpty(q3s1Text) || !RANGE_PATTERN.matcher(q3s1Text).matches())
                {
                    Toast.makeText(StressForm.this,"Must be 0,1,2 or 3",Toast.LENGTH_LONG).show();
                    q3s1.setError("Input is required");
                    q3s1.requestFocus();
                }
                else if(TextUtils.isEmpty(q4s1Text) || !RANGE_PATTERN.matcher(q4s1Text).matches())
                {
                    Toast.makeText(StressForm.this,"Must be 0,1,2 or 3",Toast.LENGTH_LONG).show();
                    q4s1.setError("Input is required");
                    q4s1.requestFocus();
                }
                else if(TextUtils.isEmpty(q5s1Text) || !RANGE_PATTERN.matcher(q5s1Text).matches())
                {
                    Toast.makeText(StressForm.this,"Must be 0,1,2 or 3",Toast.LENGTH_LONG).show();
                    q5s1.setError("Input is required");
                    q5s1.requestFocus();
                }
                else if(TextUtils.isEmpty(q6s1Text) || !RANGE_PATTERN.matcher(q6s1Text).matches())
                {
                    Toast.makeText(StressForm.this,"Must be 0,1,2 or 3",Toast.LENGTH_LONG).show();
                    q6s1.setError("Input is required");
                    q6s1.requestFocus();
                }
                else if(TextUtils.isEmpty(q7s1Text) || !RANGE_PATTERN.matcher(q7s1Text).matches())
                {
                    Toast.makeText(StressForm.this,"Must be 0,1,2 or 3",Toast.LENGTH_LONG).show();
                    q7s1.setError("Input is required");
                    q7s1.requestFocus();
                }
                else if(TextUtils.isEmpty(q8s1Text) || !RANGE_PATTERN.matcher(q8s1Text).matches())
                {
                    Toast.makeText(StressForm.this,"Must be 0,1,2 or 3",Toast.LENGTH_LONG).show();
                    q8s1.setError("Input is required");
                    q8s1.requestFocus();
                }
                 else if(TextUtils.isEmpty(s1TotalsText) || !SUM_PATTERN.matcher(s1TotalsText).matches())
                {
                    Toast.makeText(StressForm.this,"Must be an accurate number",Toast.LENGTH_LONG).show();
                    s1Totals.setError("Number is required");
                    s1Totals.requestFocus();
                }


                // section 2 inputs
                else if(TextUtils.isEmpty(q1s2Text) || !RANGE_PATTERN.matcher(q1s2Text).matches())
                {
                    Toast.makeText(StressForm.this,"Must be 0,1,2 or 3",Toast.LENGTH_LONG).show();
                    q1s2.setError("Input is required");
                    q1s2.requestFocus();
                }
                else if(TextUtils.isEmpty(q2s2Text) || !RANGE_PATTERN.matcher(q2s2Text).matches())
                {
                    Toast.makeText(StressForm.this,"Must be 0,1,2 or 3",Toast.LENGTH_LONG).show();
                    q2s2.setError("Input is required");
                    q2s2.requestFocus();
                }
                else if(TextUtils.isEmpty(q3s2Text) || !RANGE_PATTERN.matcher(q3s2Text).matches())
                {
                    Toast.makeText(StressForm.this,"Must be 0,1,2 or 3",Toast.LENGTH_LONG).show();
                    q3s2.setError("Input is required");
                    q3s2.requestFocus();
                }
                else if(TextUtils.isEmpty(q4s2Text) || !RANGE_PATTERN.matcher(q4s2Text).matches())
                {
                    Toast.makeText(StressForm.this,"Must be 0,1,2 or 3",Toast.LENGTH_LONG).show();
                    q4s2.setError("Input is required");
                    q4s2.requestFocus();
                }
                else if(TextUtils.isEmpty(q5s2Text) || !RANGE_PATTERN.matcher(q5s2Text).matches())
                {
                    Toast.makeText(StressForm.this,"Must be 0,1,2 or 3",Toast.LENGTH_LONG).show();
                    q5s2.setError("Input is required");
                    q5s2.requestFocus();
                }
                else if(TextUtils.isEmpty(q6s2Text) || !RANGE_PATTERN.matcher(q6s2Text).matches())
                {
                    Toast.makeText(StressForm.this,"Must be 0,1,2 or 3",Toast.LENGTH_LONG).show();
                    q6s2.setError("Input is required");
                    q6s2.requestFocus();
                }
                else if(TextUtils.isEmpty(q7s2Text) || !RANGE_PATTERN.matcher(q7s2Text).matches())
                {
                    Toast.makeText(StressForm.this,"Must be 0,1,2 or 3",Toast.LENGTH_LONG).show();
                    q7s2.setError("Input is required");
                    q7s2.requestFocus();
                }
                else if(TextUtils.isEmpty(s1TotalsText) || !SUM_PATTERN.matcher(s2TotalsText).matches())
                {
                    Toast.makeText(StressForm.this,"Must be an accurate number",Toast.LENGTH_LONG).show();
                    s2Totals.setError("Number is required");
                    s2Totals.requestFocus();
                }

                //section 3 inputs
                else if(TextUtils.isEmpty(q1s3Text) || !RANGE_PATTERN.matcher(q1s3Text).matches())
                {
                    Toast.makeText(StressForm.this,"Must be 0,1,2 or 3",Toast.LENGTH_LONG).show();
                    q1s3.setError("Input is required");
                    q1s3.requestFocus();
                }
                else if(TextUtils.isEmpty(q2s3Text) || !RANGE_PATTERN.matcher(q2s3Text).matches())
                {
                    Toast.makeText(StressForm.this,"Must be 0,1,2 or 3",Toast.LENGTH_LONG).show();
                    q2s3.setError("Input is required");
                    q2s3.requestFocus();
                }
                else if(TextUtils.isEmpty(q3s3Text) || !RANGE_PATTERN.matcher(q3s3Text).matches())
                {
                    Toast.makeText(StressForm.this,"Must be 0,1,2 or 3",Toast.LENGTH_LONG).show();
                    q3s3.setError("Input is required");
                    q3s3.requestFocus();
                }
                else if(TextUtils.isEmpty(q4s3Text) || !RANGE_PATTERN.matcher(q4s3Text).matches())
                {
                    Toast.makeText(StressForm.this,"Must be 0,1,2 or 3",Toast.LENGTH_LONG).show();
                    q4s3.setError("Input is required");
                    q4s3.requestFocus();
                }
                else
                {
                    Intent PatientInfoForm = new Intent(StressForm.this, PatientInfoForm.class);
                    PatientInfoForm.putExtra("lastNameTxt" ,lastNameTxt );
                    PatientInfoForm.putExtra("dateTxt" ,dateTxt );
                    PatientInfoForm.putExtra("firstNameTxt" ,firstNameTxt );
                    PatientInfoForm.putExtra("visitReasonTxt" ,visitReasonTxt );
                    PatientInfoForm.putExtra("patientSexTxt" ,patientSexTxt );
//AuthorizatStressFoPatientInfoForm
                    PatientInfoForm.putExtra("patientDOBTxt" ,patientDOBTxt );
                    PatientInfoForm.putExtra("patientSSNTxt" ,firstNameTxt );
                    PatientInfoForm.putExtra("patientHomeNumTxt" ,patientHomeNumTxt );
                    PatientInfoForm.putExtra("patientCellNumTxt" ,patientCellNumTxt );
                    PatientInfoForm.putExtra("patientAddTxt" ,patientAddTxt );
                    PatientInfoForm.putExtra("patientCityTxt" ,patientCityTxt );
                    PatientInfoForm.putExtra("patientStateTxt" ,patientStateTxt );
                    PatientInfoForm.putExtra("patientZipCodeTxt" ,patientZipCodeTxt );
                    PatientInfoForm.putExtra("patientPrefNumberTxt" ,patientPrefNumberTxt );
                    PatientInfoForm.putExtra("patientConsentCallTxt" ,patientConsentCallTxt );
                    PatientInfoForm.putExtra("patientConsentTextTxt" ,patientConsentTextTxt );
                    PatientInfoForm.putExtra("patientInsuranceTxt" ,patientInsuranceTxt );
                    PatientInfoForm.putExtra("patientEmailTxt" ,patientEmailTxt );
                    PatientInfoForm.putExtra("prefLangTxt" ,prefLangTxt );
                    PatientInfoForm.putExtra("translatorTxt" ,translatorTxt );
                    PatientInfoForm.putExtra("maritalTxt" ,maritalTxt );
                    PatientInfoForm.putExtra("houseIncomeTxt" ,houseIncomeTxt );
                    PatientInfoForm.putExtra("houseHoldTxt" ,houseHoldTxt );
                    PatientInfoForm.putExtra("occupationTxt" ,occupationTxt );
                    PatientInfoForm.putExtra("veteranTxt" ,veteranTxt );
                    PatientInfoForm.putExtra("emergencyNameTxt" ,emergencyNameTxt );
                    PatientInfoForm.putExtra("relationshipTxt" ,relationshipTxt );
                    PatientInfoForm.putExtra("contactPhoneTxt" ,contactPhoneTxt );
                    PatientInfoForm.putExtra("patientConsentName" ,patientConsentName );
                    PatientInfoForm.putExtra("patientSignedText" ,patientSignedText );
                    PatientInfoForm.putExtra("patientSignatureText" ,patientSignatureText );
                    PatientInfoForm.putExtra("consentDateTxt" ,consentDateTxt );

                    startActivity(PatientInfoForm);

                }




            }
        });


    }
}


