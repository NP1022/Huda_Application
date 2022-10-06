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
                else if(TextUtils.isEmpty(firstNameTxt) && TextUtils.isEmpty(lastNameTxt)) // check if name is empty
                {
                    Toast.makeText(PatientForm.this,"Please enter first and last name",Toast.LENGTH_LONG).show();
                    firstName.setError("Name is required");
                    firstName.requestFocus();
                    lastName.setError("Name is required");
                    lastName.requestFocus();
                }
                else if(TextUtils.isEmpty(patientSexTxt))
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
                else
                {
                    Toast.makeText(PatientForm.this,"Success!",Toast.LENGTH_LONG).show();
                }
            }
        });

    }
}