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

import com.example.huda_application.user.PatientFormData;

import java.util.regex.Pattern;

public class PatientContract extends AppCompatActivity implements View.OnClickListener
{

    private static final Pattern DATE_PATTERN = Pattern.compile(
            "^((0[1-9]|1[012])-(0[1-9]|[12][0-9]|3[01])-(19|2[0-9])[0-9]{2})$"); // date pattern match

    final EditText patientSigned = findViewById(R.id.patientNamePrinted2);
    final EditText patientSig = findViewById(R.id.patientNameSignature2);
    final EditText consentDateSign = findViewById(R.id.consentFormDate2);

    final String patientSignedText2 = patientSigned.getText().toString().trim();
    final String patientSignatureText2 = patientSig.getText().toString().trim();
    final String consentDateTxt2 = consentDateSign.getText().toString().trim();


    private PatientFormData data;
    private TextView AuthorizationForm;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_patient_contract);
        Intent extras = getIntent();

        if (extras != null)
        {
            data =  (PatientFormData)  extras.getSerializableExtra("patientdata");
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
            if(TextUtils.isEmpty(patientSignedText2) || patientSignedText2.length() > 30)
            {
                Toast.makeText(PatientContract.this, "Name cannot be empty", Toast.LENGTH_LONG).show();
                patientSigned.setError("Signed name is required");
                patientSigned.requestFocus();
            }
            else if(TextUtils.isEmpty(patientSignatureText2) || patientSignatureText2.length() > 30)
            {
                Toast.makeText(PatientContract.this, "Signature cannot be empty", Toast.LENGTH_LONG).show();
                patientSig.setError("Signature is required");
                patientSig.requestFocus();
            }
            else if(TextUtils.isEmpty(consentDateTxt2))
            {
                Toast.makeText(PatientContract.this, "Date cannot be empty", Toast.LENGTH_LONG).show();
                consentDateSign.setError("Date is required");
                consentDateSign.requestFocus();
            }
            else if(!DATE_PATTERN.matcher(consentDateTxt2).matches())
            {
                Toast.makeText(PatientContract.this, "Date format MM-DD-YYYY", Toast.LENGTH_LONG).show();
                consentDateSign.setError("Date format is required");
                consentDateSign.requestFocus();
            }
            else
            {
                Intent AuthorizationForm = new Intent (this, AuthorizationForm.class);
                startActivity(AuthorizationForm);
            }

        }
    }
}