package com.example.huda_application;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.huda_application.user.PatientFormData;

import java.util.regex.Pattern;

public class AuthorizationForm extends AppCompatActivity implements View.OnClickListener
{

    private PatientFormData data; //  private PatientFormData data;
    private TextView PersonalRepresentative;
    private ImageView backButton;

    private static final Pattern DATE_PATTERN = Pattern.compile(
            "^((0[1-9]|1[012])-(0[1-9]|[12][0-9]|3[01])-(19|2[0-9])[0-9]{2})$"); // date pattern match

    private static Pattern LETTERS_PATTERN = Pattern.compile("^[A-Za-z\\s]{1,}[\\.]{0,1}[A-Za-z\\s]{0,}$"); // Letters pattern match

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_authorization_form);
        Intent extras = getIntent();

        EditText consentDate = findViewById(R.id.consentFormDate3);

        // Listener that autofills the "-' in the consentDate Textfield
        consentDate.addTextChangedListener(new TextWatcher()
        {
            int prevL = 0;

            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2)
            {
                prevL = consentDate.getText().toString().length();
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


        // if statement to check if the extras are null, if it is null
        // then set data to the extras.getSerializableExtra();
        if (extras != null)
        {
            data =  (PatientFormData)  extras.getSerializableExtra("patientdata");
        }


        PersonalRepresentative = (TextView) findViewById(R.id.nextForm3);
        PersonalRepresentative.setOnClickListener(this);

    }

    @Override
    public void onClick(View view)
    {
        if (view.getId() == R.id.nextForm3)
        {
            // setting the EditText variable equal to the ID from the XML
            final EditText patientSigned3 = findViewById(R.id.patientNamePrinted3);
            final EditText patientSig3 = findViewById(R.id.patientNameSignature3);
            final EditText consentDateSign3 = findViewById(R.id.consentFormDate3);

            // convert the EditText to a String type
            final String patientSignedText3 = patientSigned3.getText().toString().trim();
            final String patientSignatureText3 = patientSig3.getText().toString().trim();
            final String consentDateTxt3 = consentDateSign3.getText().toString().trim();

            // If statement that uses TextUtils.isEmpty() to check if the string is empty,
            // if it is empty, an error will be displayed.
            if(TextUtils.isEmpty(consentDateTxt3))
            {
                Toast.makeText(AuthorizationForm.this, "Date cannot be empty", Toast.LENGTH_LONG).show();
                consentDateSign3.setError("Date is required");
                consentDateSign3.requestFocus();
            }
            else if(!DATE_PATTERN.matcher(consentDateTxt3).matches())
            {
                Toast.makeText(AuthorizationForm.this, "Date format MM-DD-YYYY", Toast.LENGTH_LONG).show();
                consentDateSign3.setError("Date format is required");
                consentDateSign3.requestFocus();
            }
            // Else if statement to check if the string matches the DATE_PATTERN and if it does not,
            // an error will be displayed
            else if(TextUtils.isEmpty(patientSignedText3) || patientSignedText3.length() > 30)
            {
                Toast.makeText(AuthorizationForm.this, "Name cannot be empty", Toast.LENGTH_LONG).show();
                patientSigned3.setError("Signed name is required");
                patientSigned3.requestFocus();
            }
            // Else if statement to check if the string matches the pattern within the else if() and if it does not,
            // an error will be displayed
            else if(!LETTERS_PATTERN.matcher(patientSignedText3).matches())
            {
                Toast.makeText(AuthorizationForm.this, "Name must contain only letters", Toast.LENGTH_LONG).show();
                patientSigned3.setError("Name format is required");
                patientSigned3.requestFocus();
            }
            // else if statement that uses TextUtils.isEmpty() to check if the string is empty,
            // if it is empty, an error will be displayed.
            // || operator to also check if the string length is less than 30 characters, if > 30 then
            // another error will be thrown
            else if(TextUtils.isEmpty(patientSignatureText3) || patientSignatureText3.length() > 30)
            {
                Toast.makeText(AuthorizationForm.this, "Signature cannot be empty", Toast.LENGTH_LONG).show();
                patientSig3.setError("Signature is required");
                patientSig3.requestFocus();
            }
            // Else if statement to check if the string matches the pattern within the else if() and if it does not,
            // an error will be displayed
            else if(!LETTERS_PATTERN.matcher(patientSignatureText3).matches())
            {
                Toast.makeText(AuthorizationForm.this, "Signature must contain only letters", Toast.LENGTH_LONG).show();
                patientSigned3.setError("Name format is required");
                patientSigned3.requestFocus();
            }
            // Else if statement to check if the patient signature matches the signed name, if it does not
            // then an error will be displayed
            else if(!patientSignatureText3.equals(patientSignedText3))
            {
                Toast.makeText(AuthorizationForm.this, "Signature must match Signed name", Toast.LENGTH_LONG).show();
                patientSig3.setError("Name match is required");
                patientSig3.requestFocus();
            }
            // Else block is executed if all the inputs previously are valid. The inputs will be set using a setter to the data object
            // that will save the information and move it to the next form
            else
            {
                data.setConsentDateForm3(consentDateTxt3);
                data.setPatientSignatureForm2(patientSignatureText3);
                data.setPatientSignedForm2(patientSignedText3);
                Intent PersonalRepresentative = new Intent (AuthorizationForm.this, PersonalRepresentative.class);
                PersonalRepresentative.putExtra("patientdata",data);
                startActivity(PersonalRepresentative);
            }

        }

    }
}