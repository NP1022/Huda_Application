package com.example.huda_application;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.regex.Pattern;

public class PatientForm extends AppCompatActivity
{

    private static final Pattern DATE_PATTERN = Pattern.compile(
            "^((0[1-9]|1[012])-(0[1-9]|[12][0-9]|3[01])-(19|2[0-9])[0-9]{2})$"); // date pattern match

    private static final Pattern SSN_PATTERN = Pattern.compile("^(?!666|000|9\\d{2})\\d{3}"
            + "-(?!00)\\d{2}-"
            +"(?!0{4})\\d{4}$");  // SSN pattern match

    private static final Pattern ZIPCODE_PATTERN = Pattern.compile("^\\d{5}$"); // zipcode

    private static final Pattern PHONE_PATTERN = Pattern.compile("^[2-9]\\d{2}-\\d{3}-\\d{4}$"); // pattern to make sure phone numbers are valid format

    private static Pattern PATIENTSEX_PATTERN = Pattern.compile("^(?:m|M|male|Male|f|F|female|Female)$"); // pattern to check patient sex input


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_patient_form);

        final EditText todayDate = findViewById(R.id.todaysDate);
        final EditText visitReason = findViewById(R.id.visitReason);
        final EditText firstName = findViewById(R.id.firstName);
        final EditText lastName = findViewById(R.id.lastName);
        final EditText patientSex = findViewById(R.id.patientSex);
        final EditText patientSsn = findViewById(R.id.patientSSN);
        final EditText patientDOB = findViewById(R.id.patientDob);
        final EditText patientHome = findViewById(R.id.patientHomeNum);
        final EditText patientCell = findViewById(R.id.patientCellNum);
        final EditText patientAdd = findViewById(R.id.patientAddress);
        final EditText patientCity = findViewById(R.id.patientCity);
        final EditText patientState = findViewById(R.id.patientState);
        final EditText patientZip = findViewById(R.id.patientZipCode);

        Button buttonSubmit = findViewById(R.id.submitPatient); // set variable for button action

        buttonSubmit.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                final String dateTxt = todayDate.getText().toString().trim(); // convert the EditText to a String type
                final String visitReasonTxt = visitReason.getText().toString().trim();
                final String firstNameTxt = firstName.getText().toString().trim();
                final String lastNameTxt = lastName.getText().toString().trim();
                final String patientSexTxt = patientSex.getText().toString().trim();
                final String patientSSNTxt = patientSsn.getText().toString().trim();
                final String patientDOBTxt = patientDOB.getText().toString().trim();
                final String patientHomeNumTxt = patientHome.getText().toString().trim();
                final String patientCellNumTxt = patientCell.getText().toString().trim();
                final String patientAddTxt = patientAdd.getText().toString().trim();
                final String patientCityTxt = patientCity.getText().toString().trim();
                final String patientStateTxt = patientState.getText().toString().trim();
                final String patientZipCodeTxt = patientZip.getText().toString().trim();

                if (TextUtils.isEmpty(dateTxt)) // check if date is empty
                {
                    Toast.makeText(PatientForm.this,"Please enter today's date",Toast.LENGTH_LONG).show();
                    todayDate.setError("Date is required");
                    todayDate.requestFocus();
                }
                else if(!DATE_PATTERN.matcher(dateTxt).matches()) // check if the date is pattern matched
                {
                    Toast.makeText(PatientForm.this,"Must be mm-dd-yyyy",Toast.LENGTH_LONG).show();
                    todayDate.setError("Format required");
                    todayDate.requestFocus();
                }
                else if(TextUtils.isEmpty(visitReasonTxt)) // check if visit reason is empty
                {
                    Toast.makeText(PatientForm.this,"Please enter a visit reason",Toast.LENGTH_LONG).show();
                    visitReason.setError("Visit reason is required");
                    visitReason.requestFocus();
                }
                else if(TextUtils.isEmpty(firstNameTxt)) // check if name is empty
                {
                    Toast.makeText(PatientForm.this,"Please enter first name",Toast.LENGTH_LONG).show();
                    firstName.setError("Name is required");
                    firstName.requestFocus();

                }
                else if(TextUtils.isEmpty(lastNameTxt))
                {
                    Toast.makeText(PatientForm.this,"Please enter last name",Toast.LENGTH_LONG).show();
                    lastName.setError("Name is required");
                    lastName.requestFocus();
                }
                else if(TextUtils.isEmpty(patientSexTxt))
                {
                    Toast.makeText(PatientForm.this,"Please enter a sex (Male or Female)",Toast.LENGTH_LONG).show();
                    patientSex.setError("Sex is required");
                    patientSex.requestFocus();
                }
                else if(!PATIENTSEX_PATTERN.matcher(patientSexTxt).matches())
                {
                    Toast.makeText(PatientForm.this,"Please enter a sex (Male or Female)",Toast.LENGTH_LONG).show();
                    patientSex.setError("Sex is required");
                    patientSex.requestFocus();
                }
                else if(TextUtils.isEmpty(patientDOBTxt))
                {
                    Toast.makeText(PatientForm.this,"Please enter a Date of birth",Toast.LENGTH_LONG).show();
                }
                else if(!DATE_PATTERN.matcher(patientDOBTxt).matches())
                {
                    Toast.makeText(PatientForm.this,"Must be mm-dd-yyyy",Toast.LENGTH_LONG).show();
                    patientDOB.setError("Format required");
                    patientDOB.requestFocus();
                }
                else if(TextUtils.isEmpty(patientAddTxt))
                {
                    Toast.makeText(PatientForm.this,"Address cannot be empty",Toast.LENGTH_LONG).show();
                    patientAdd.setError("Address required");
                    patientAdd.requestFocus();
                }
                else if(TextUtils.isEmpty(patientCityTxt))
                {
                    Toast.makeText(PatientForm.this,"City cannot be empty",Toast.LENGTH_LONG).show();
                    patientCity.setError("City required");
                    patientCity.requestFocus();
                }
                else if(TextUtils.isEmpty(patientStateTxt))
                {
                    Toast.makeText(PatientForm.this,"State cannot be empty",Toast.LENGTH_LONG).show();
                    patientState.setError("Address required");
                    patientState.requestFocus();
                }
                else if(TextUtils.isEmpty(patientZipCodeTxt))
                {
                    Toast.makeText(PatientForm.this,"Zipcode cannot be empty",Toast.LENGTH_LONG).show();
                    patientZip.setError("Zipcode required");
                    patientZip.requestFocus();
                }
                else if(!ZIPCODE_PATTERN.matcher(patientZipCodeTxt).matches())
                {
                    Toast.makeText(PatientForm.this,"Zipcode must be 12345",Toast.LENGTH_LONG).show();
                    patientZip.setError("Format required");
                    patientZip.requestFocus();
                }
                else if(TextUtils.isEmpty(patientSSNTxt))
                {
                    Toast.makeText(PatientForm.this,"Please enter a SSN",Toast.LENGTH_LONG).show();
                }
                else if(!SSN_PATTERN.matcher(patientSSNTxt).matches())
                {
                    Toast.makeText(PatientForm.this,"Must be 123-12-1234",Toast.LENGTH_LONG).show();
                    patientSsn.setError("Format required");
                    patientSsn.requestFocus();
                }
                else if(TextUtils.isEmpty(patientCellNumTxt))
                {
                    Toast.makeText(PatientForm.this,"Number cannot be empty",Toast.LENGTH_LONG).show();
                    patientCell.setError("Cell number required");
                    patientCell.requestFocus();
                }
                else if(!PHONE_PATTERN.matcher(patientCellNumTxt).matches())
                {
                    Toast.makeText(PatientForm.this,"Number must be 123-123-1234",Toast.LENGTH_LONG).show();
                    patientCell.setError("Format is required");
                    patientCell.requestFocus();
                }
                else if(TextUtils.isEmpty(patientHomeNumTxt))
                {
                    Toast.makeText(PatientForm.this,"Number cannot be empty",Toast.LENGTH_LONG).show();
                    patientHome.setError("Home phone number required");
                    patientHome.requestFocus();
                }
                else if(!PHONE_PATTERN.matcher(patientHomeNumTxt).matches())
                {
                    Toast.makeText(PatientForm.this,"Number must be 123-123-1234",Toast.LENGTH_LONG).show();
                    patientHome.setError("Format is required");
                    patientHome.requestFocus();
                }
                else
                {
                    Toast.makeText(PatientForm.this,"Success!",Toast.LENGTH_LONG).show();
                }
            }
        });

    }
}