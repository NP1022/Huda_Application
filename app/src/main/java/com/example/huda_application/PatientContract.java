package com.example.huda_application;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.huda_application.user.PatientFormData;

import java.util.regex.Pattern;

public class PatientContract extends AppCompatActivity implements View.OnClickListener
{

    private  PatientFormData data;
    private static final Pattern DATE_PATTERN = Pattern.compile(
            "^((0[1-9]|1[012])-(0[1-9]|[12][0-9]|3[01])-(19|2[0-9])[0-9]{2})$"); // date pattern match

    private static Pattern LETTERS_PATTERN = Pattern.compile("^[A-Za-z\\s]{1,}[\\.]{0,1}[A-Za-z\\s]{0,}$"); // Letters pattern match


    //  private PatientFormData data;
    private TextView AuthorizationForm;
    private EditText consentDateSign2;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_patient_contract);
        Intent extras = getIntent();

        // if statement to check if the extras are null, if it is null
        // then set data to the extras.getSerializableExtra();
        if (extras != null)
        {
            data =  (PatientFormData)  extras.getSerializableExtra("patientdata");
        }


        AuthorizationForm = (Button) findViewById(R.id.nextForm2);
        AuthorizationForm.setOnClickListener(this);
        consentDateSign2 = findViewById(R.id.consentFormDate2);


        // Listener that autofills the "-' in the consentDate2 Textfield
        consentDateSign2.addTextChangedListener(new TextWatcher()
        {
            int prevL = 0;

            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2)
            {
                prevL = consentDateSign2.getText().toString().length();
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2)
            {

            }

            @Override
            public void afterTextChanged(Editable editable)
            {
                int length = editable.length();
                if ((prevL < length) && (length == 2 || length == 5)) {
                    editable.append("-");
                }
            }
        });
    }

    @Override
    public void onClick(View view)
    {
        if (view.getId() == R.id.nextForm2)
        {
            // setting the EditText variable equal to the ID from the PatientContract XML
            final EditText patientSigned2 = findViewById(R.id.patientNamePrinted2);
            final EditText patientSig2 = findViewById(R.id.patientNameSignature2);

            // convert the EditText to a String type
            final String patientSignedText2 = patientSigned2.getText().toString().trim();
            final String patientSignatureText2 = patientSig2.getText().toString().trim();
            final String consentDateTxt2 = consentDateSign2.getText().toString().trim();

            // If statement that uses TextUtils.isEmpty() to check if the string is empty,
            // if it is empty, an error will be displayed.
            if(TextUtils.isEmpty(consentDateTxt2))
            {
                Toast.makeText(PatientContract.this, "Date cannot be empty", Toast.LENGTH_LONG).show();
                consentDateSign2.setError("Date is required");
                consentDateSign2.requestFocus();
            }
            // Else if statement to check if the string matches the DATE_PATTERN and if it does not,
            // an error will be displayed
            else if(!DATE_PATTERN.matcher(consentDateTxt2).matches())
            {
                Toast.makeText(PatientContract.this, "Date format MM-DD-YYYY", Toast.LENGTH_LONG).show();
                consentDateSign2.setError("Date format is required");
                consentDateSign2.requestFocus();
            }
            // else if statement that uses TextUtils.isEmpty() to check if the string is empty,
            // if it is empty, an error will be displayed.
            // || operator to also check if the string length is less than 30 characters, if > 30 then
            // another error will be thrown
            else if(TextUtils.isEmpty(patientSignedText2) || patientSignedText2.length() > 30)
            {
                Toast.makeText(PatientContract.this, "Name cannot be empty", Toast.LENGTH_LONG).show();
                patientSigned2.setError("Signed name is required");
                patientSigned2.requestFocus();
            }
            // Else if statement to check if the string matches the pattern within the else if() and if it does not,
            // an error will be displayed
            else if(!LETTERS_PATTERN.matcher(patientSignedText2).matches())
            {
                Toast.makeText(PatientContract.this, "Name must contain only letters", Toast.LENGTH_LONG).show();
                patientSigned2.setError("Name format is required");
                patientSigned2.requestFocus();
            }
            // else if statement that uses TextUtils.isEmpty() to check if the string is empty,
            // if it is empty, an error will be displayed.
            // || operator to also check if the string length is less than 30 characters, if > 30 then
            // another error will be thrown
            else if(TextUtils.isEmpty(patientSignatureText2) || patientSignatureText2.length() > 30)
            {
                Toast.makeText(PatientContract.this, "Signature cannot be empty", Toast.LENGTH_LONG).show();
                patientSig2.setError("Signature is required");
                patientSig2.requestFocus();
            }
            // Else if statement to check if the string matches the pattern within the else if() and if it does not,
            // an error will be displayed
            else if(!LETTERS_PATTERN.matcher(patientSignatureText2).matches())
            {
                Toast.makeText(PatientContract.this, "Signature must contain only letters", Toast.LENGTH_LONG).show();
                patientSig2.setError("Name format is required");
                patientSig2.requestFocus();
            }
            // Else if statement to check if the patient signature matches the signed name, if it does not
            // then an error will be displayed
            else if(!patientSignatureText2.equals(patientSignedText2))
            {
                Toast.makeText(PatientContract.this, "Signature must match Signed name", Toast.LENGTH_LONG).show();
                patientSig2.setError("Name match is required");
                patientSig2.requestFocus();
            }
            // Else block is executed if all the inputs previously are valid. The inputs will be set using a setter to the data object
            // that will save the information and move it to the next form
            else
            {
                data.setConsentDateForm2(consentDateTxt2);
                data.setPatientSignedForm2(patientSignedText2);
                data.setPatientSignatureForm2(patientSignatureText2);
                Intent AuthorizationForm = new Intent (this, AuthorizationForm.class);
                AuthorizationForm.putExtra("patientdata",data);
                startActivity(AuthorizationForm);

            }

        }
    }
}