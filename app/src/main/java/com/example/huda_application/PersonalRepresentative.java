package com.example.huda_application;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.huda_application.user.PatientFormData;

import java.util.Locale;
import java.util.regex.Pattern;

public class PersonalRepresentative extends AppCompatActivity implements View.OnClickListener
{
    private static final Pattern DATE_PATTERN = Pattern.compile(
            "^((0[1-9]|1[012])-(0[1-9]|[12][0-9]|3[01])-(19|2[0-9])[0-9]{2})$"); // date pattern match

    private static Pattern LETTERS_PATTERN = Pattern.compile("^[A-Za-z\\s]{1,}[\\.]{0,1}[A-Za-z\\s]{0,}$"); // Letters pattern match

    private static final Pattern ZIPCODE_PATTERN = Pattern.compile("^\\d{5}$"); // zipcode

    private static final Pattern PHONE_PATTERN = Pattern.compile("^[2-9]\\d{2}-\\d{3}-\\d{4}$"); // pattern to make sure phone numbers are valid format

    private PatientFormData data;
    private TextView StressForm;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_personal_representative);
        Intent extras = getIntent();

        EditText personalRepDOB = findViewById(R.id.personalRepDOB);

        // Listener that autofills the "-' in the personalRepDOB Textfield
        personalRepDOB.addTextChangedListener(new TextWatcher()
        {
            int prevL = 0;

            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                prevL = personalRepDOB.getText().toString().length();
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
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


        StressForm = (TextView) findViewById(R.id.nextForm4);
        StressForm.setOnClickListener(this);
    }


    @Override
    public void onClick(View view)
    {
        if (view.getId() == R.id.nextForm4)
        {
            // setting the EditText variable equal to the ID from the XML
            final EditText patientSigned4 = findViewById(R.id.personalRepPatientName);
            final EditText patientDOB4 = findViewById(R.id.personalRepDOB);
            final EditText patientHomeAdd = findViewById(R.id.personalRepPatientAddress);
            final EditText patientCity = findViewById(R.id.personalRepPatientCity);
            final EditText patientState = findViewById(R.id.personalRepPatientState);
            final EditText patientZip = findViewById(R.id.personalRepPatientZip);
            final EditText patientHomeNumber = findViewById(R.id.personalRepPatientHome);
            final EditText personalRep1 = findViewById(R.id.personalRep1);
            final EditText patientRelation1 = findViewById(R.id.personalRepRelation1);
            final EditText patientRelationContact1 = findViewById(R.id.personalRepNumber);

            final EditText personalRep2 = findViewById(R.id.personalRep2);
            final EditText patientRelation2 = findViewById(R.id.personalRepRelation2);
            final EditText patientRelationContact2 = findViewById(R.id.personalRepNumber2);

            final EditText personalRep3 = findViewById(R.id.personalRep3);
            final EditText patientRelation3 = findViewById(R.id.personalRepRelation3);
            final EditText patientRelationContact3 = findViewById(R.id.personalRepNumber3);

            final EditText patientSignature4 = findViewById(R.id.patientSignaturePersonalRep);
            final EditText patientDate4 = findViewById(R.id.personalRepDate);

            // convert the EditText to a String type
            final String patientSignedTxt4 = patientSigned4.getText().toString().trim();
            final String patientDOB4Txt4 = patientDOB4.getText().toString().trim();
            final String patientHomeAddTxt4 = patientHomeAdd.getText().toString().trim();
            final String patientCityTxt4 = patientCity.getText().toString().trim();
            final String PatientStateTxt4 = patientState.getText().toString().trim();
            final String patientZipTxt4 = patientZip.getText().toString().trim();
            final String patientHomeNumberTxt4 = patientHomeNumber.getText().toString().trim();

            final String personRep1Txt = personalRep1.getText().toString().trim();
            final String patientRelation1Txt = patientRelation1.getText().toString().trim();
            final String patientRelationContact1Txt = patientRelationContact1.getText().toString().trim();

            final String personRep2Txt = personalRep2.getText().toString().trim();
            final String patientRelation2Txt = patientRelation2.getText().toString().trim();
            final String patientRelationContact2Txt = patientRelationContact2.getText().toString().trim();

            final String personRep3Txt = personalRep3.getText().toString().trim();
            final String patientRelation3Txt = patientRelation3.getText().toString().trim();
            final String patientRelationContact3Txt = patientRelationContact3.getText().toString().trim();

            final String patientSig4Txt = patientSignature4.getText().toString().trim();
            final String patientDate4Txt = patientDate4.getText().toString().trim();


            // If statement that uses TextUtils.isEmpty() to check if the string is empty,
            // if it is empty, an error will be displayed.
            if(TextUtils.isEmpty(patientSignedTxt4))
            {
                Toast.makeText(PersonalRepresentative.this, "Signed name cannot be empty", Toast.LENGTH_LONG).show();
                patientSigned4.setError("Signed name is required");
                patientSigned4.requestFocus();
            }
            // Else if statement check if the string length is less than 30 characters, if > 30 then
            // another error will be thrown
            else if(patientSignedTxt4.length() > 30)
            {
                Toast.makeText(PersonalRepresentative.this, "Name cannot be > 30 characters", Toast.LENGTH_LONG).show();
                patientSigned4.setError("Character limit reached");
                patientSigned4.requestFocus();
            }
            // Else if statement to check if the string matches the pattern within the else if() and if it does not,
            // an error will be displayed
            else if(!LETTERS_PATTERN.matcher(patientSignedTxt4).matches())
            {
                Toast.makeText(PersonalRepresentative.this, "Name can only contain letters", Toast.LENGTH_LONG).show();
                patientSigned4.setError("Name format is required");
                patientSigned4.requestFocus();
            }
            // If statement that uses TextUtils.isEmpty() to check if the string is empty,
            // if it is empty, an error will be displayed.
            else if(TextUtils.isEmpty(patientDOB4Txt4))
            {
                Toast.makeText(PersonalRepresentative.this, "Date of birth cannot be empty", Toast.LENGTH_LONG).show();
                patientDOB4.setError("Date of birth is required");
                patientDOB4.requestFocus();
            }
            // Else if statement to check if the string matches the pattern within the else if() and if it does not,
            // an error will be displayed
            else if(!DATE_PATTERN.matcher(patientDOB4Txt4).matches())
            {
                Toast.makeText(PersonalRepresentative.this, "Date must be MM-DD-YYYY", Toast.LENGTH_LONG).show();
                patientDOB4.setError("Date format is required");
                patientDOB4.requestFocus();
            }
            // If statement that uses TextUtils.isEmpty() to check if the string is empty,
            // if it is empty, an error will be displayed.
            else if(TextUtils.isEmpty(patientHomeAddTxt4))
            {
                Toast.makeText(PersonalRepresentative.this, "Home address cannot be empty", Toast.LENGTH_LONG).show();
                patientHomeAdd.setError("Address is required");
                patientHomeAdd.requestFocus();
            }
            // Else if statement check if the string length is less than 40 characters, if > 40 then
            // another error will be thrown
            else if(patientHomeAddTxt4.length() > 40)
            {
                Toast.makeText(PersonalRepresentative.this, "Text cannot be > 40 characters", Toast.LENGTH_LONG).show();
                patientHomeAdd.setError("Max character limit reached");
                patientHomeAdd.requestFocus();
            }
            // If statement that uses TextUtils.isEmpty() to check if the string is empty,
            // if it is empty, an error will be displayed.
            else if(TextUtils.isEmpty(patientCityTxt4))
            {
                Toast.makeText(PersonalRepresentative.this, "City cannot be empty", Toast.LENGTH_LONG).show();
                patientCity.setError("City is required");
                patientCity.requestFocus();
            }
            // Else if statement to check if the string length is less than 20 characters, if > 20 then
            // another error will be thrown
            else if(patientCityTxt4.length() > 20)
            {
                Toast.makeText(PersonalRepresentative.this, "Text cannot be > 20 characters", Toast.LENGTH_LONG).show();
                patientCity.setError("Max character limit reached");
                patientCity.requestFocus();
            }
            // Else if statement to check if the string matches the pattern within the else if() and if it does not,
            // an error will be displayed
            else if(!LETTERS_PATTERN.matcher(patientCityTxt4).matches())
            {
                Toast.makeText(PersonalRepresentative.this, "City must contain only letters", Toast.LENGTH_LONG).show();
                patientCity.setError("City format is required");
                patientCity.requestFocus();
            }
            // If statement that uses TextUtils.isEmpty() to check if the string is empty,
            // if it is empty, an error will be displayed.
            else if(TextUtils.isEmpty(PatientStateTxt4))
            {
                Toast.makeText(PersonalRepresentative.this, "State cannot be empty", Toast.LENGTH_LONG).show();
                patientState.setError("State is required");
                patientState.requestFocus();
            }
            // Else if statement check if the string length is less than 20 characters, if > 20 then
            // another error will be thrown
            else if(PatientStateTxt4.length() > 20)
            {
                Toast.makeText(PersonalRepresentative.this, "Text cannot be > 20 characters", Toast.LENGTH_LONG).show();
                patientState.setError("Max character limit reached");
                patientState.requestFocus();
            }
            // Else if statement to check if the string matches the pattern within the else if() and if it does not,
            // an error will be displayed
            else if(!LETTERS_PATTERN.matcher(PatientStateTxt4).matches())
            {
                Toast.makeText(PersonalRepresentative.this, "State must contain only letters", Toast.LENGTH_LONG).show();
                patientState.setError("State format is required");
                patientState.requestFocus();
            }
            // If statement that uses TextUtils.isEmpty() to check if the string is empty,
            // if it is empty, an error will be displayed.
            else if(TextUtils.isEmpty(patientZipTxt4))
            {
                Toast.makeText(PersonalRepresentative.this, "Zipcode cannot be empty", Toast.LENGTH_LONG).show();
                patientZip.setError("Zipcode is required");
                patientZip.requestFocus();
            }
            // Else if statement to check if the string matches the pattern within the else if() and if it does not,
            // an error will be displayed
            else if(!ZIPCODE_PATTERN.matcher(patientZipTxt4).matches())
            {
                Toast.makeText(PersonalRepresentative.this, "Zipcode must contain only numbers", Toast.LENGTH_LONG).show();
                patientZip.setError("Zipcode format is required");
                patientZip.requestFocus();
            }
            // If statement that uses TextUtils.isEmpty() to check if the string is empty,
            // if it is empty, an error will be displayed.
            else if(TextUtils.isEmpty(patientHomeNumberTxt4))
            {
                Toast.makeText(PersonalRepresentative.this, "Number cannot be empty", Toast.LENGTH_LONG).show();
                patientHomeNumber.setError("Phone number is required");
                patientHomeNumber.requestFocus();
            }
            // Else if statement to check if the string matches the pattern within the else if() and if it does not,
            // an error will be displayed
            else if(!PHONE_PATTERN.matcher(patientHomeNumberTxt4).matches())
            {
                Toast.makeText(PersonalRepresentative.this, "Number must be 123-123-1234", Toast.LENGTH_LONG).show();
                patientHomeNumber.setError("Phone number format is required");
                patientHomeNumber.requestFocus();
            }
            // If statement that uses TextUtils.isEmpty() to check if the string is empty,
            // if it is empty, an error will be displayed.
            else if(TextUtils.isEmpty(personRep1Txt)) // First Representative
            {
                Toast.makeText(PersonalRepresentative.this, "Name cannot be empty", Toast.LENGTH_LONG).show();
                personalRep1.setError("Name is required");
                personalRep1.requestFocus();
            }
            // ELse if statement to check if the string length is less than 20 characters, if > 20 then
            // another error will be thrown
            else if(personRep1Txt.length() > 20) // First Representative
            {
                Toast.makeText(PersonalRepresentative.this, "Name cannot be > 20 characters", Toast.LENGTH_LONG).show();
                personalRep1.setError("Max character limit reached");
                personalRep1.requestFocus();
            }
            // Else if statement to check if the string matches the pattern within the else if() and if it does not,
            // an error will be displayed
            else if(!LETTERS_PATTERN.matcher(personRep1Txt).matches())
            {
                Toast.makeText(PersonalRepresentative.this, "Name must contain only letters", Toast.LENGTH_LONG).show();
                personalRep1.setError("Name format is required");
                personalRep1.requestFocus();
            }
            // If statement that uses TextUtils.isEmpty() to check if the string is empty,
            // if it is empty, an error will be displayed.
            // || operator to also check if the string length is less than 20 characters, if > 20 then
            // another error will be thrown
            else if(TextUtils.isEmpty(patientRelation1Txt) || patientRelation1Txt.length() > 20)
            {
                Toast.makeText(PersonalRepresentative.this, "Relationship cannot be empty", Toast.LENGTH_LONG).show();
                patientRelation1.setError("Relationship is required");
                patientRelation1.requestFocus();
            }
            // Else if statement to check if the string matches the pattern within the else if() and if it does not,
            // an error will be displayed
            else if(!LETTERS_PATTERN.matcher(patientRelation1Txt).matches())
            {
                Toast.makeText(PersonalRepresentative.this, "Relation must contain only letters", Toast.LENGTH_LONG).show();
                patientRelation1.setError("Relation format is required");
                patientRelation1.requestFocus();
            }
            // If statement that uses TextUtils.isEmpty() to check if the string is empty,
            // if it is empty, an error will be displayed.
            else if(TextUtils.isEmpty(patientRelationContact1Txt))
            {
                Toast.makeText(PersonalRepresentative.this, "Number cannot be empty", Toast.LENGTH_LONG).show();
                patientRelationContact1.setError("Phone number is required");
                patientRelationContact1.requestFocus();
            }
            // Else if statement to check if the string matches the pattern within the else if() and if it does not,
            // an error will be displayed
            else if(!PHONE_PATTERN.matcher(patientRelationContact1Txt).matches())
            {
                Toast.makeText(PersonalRepresentative.this, "Number must be 123-123-1234", Toast.LENGTH_LONG).show();
                patientRelationContact1.setError("Phone number format is required");
                patientRelationContact1.requestFocus();
            }
            // If statement that uses TextUtils.isEmpty() to check if the string is empty,
            // if it is empty, an error will be displayed.
            else if(TextUtils.isEmpty(personRep2Txt)) // 2nd Representative
            {
                Toast.makeText(PersonalRepresentative.this, "Name cannot be empty", Toast.LENGTH_LONG).show();
                personalRep2.setError("Name is required");
                personalRep2.requestFocus();
            }
            // Else if statement to check if the string length is less than 20 characters, if > 20 then
            // another error will be thrown
            else if(personRep2Txt.length() > 20) // 2nd Representative
            {
                Toast.makeText(PersonalRepresentative.this, "Name cannot be > 20 characters", Toast.LENGTH_LONG).show();
                personalRep2.setError("Max character limit reached");
                personalRep2.requestFocus();
            }
            // Else if statement to check if the string matches the pattern within the else if() and if it does not,
            // an error will be displayed
            else if(!LETTERS_PATTERN.matcher(personRep2Txt).matches())
            {
                Toast.makeText(PersonalRepresentative.this, "Relation must contain only letters", Toast.LENGTH_LONG).show();
                personalRep2.setError("Name format is required");
                personalRep2.requestFocus();
            }
            // If statement that uses TextUtils.isEmpty() to check if the string is empty,
            // if it is empty, an error will be displayed.
            // || operator to also check if the string length is less than 20 characters, if > 20 then
            // another error will be thrown
            else if(TextUtils.isEmpty(patientRelation2Txt) || patientRelation2Txt.length() > 20)
            {
                Toast.makeText(PersonalRepresentative.this, "Relationship cannot be empty", Toast.LENGTH_LONG).show();
                patientRelation2.setError("Relationship is required");
                patientRelation2.requestFocus();
            }
            // Else if statement to check if the string matches the pattern within the else if() and if it does not,
            // an error will be displayed
            else if(!LETTERS_PATTERN.matcher(patientRelation2Txt).matches())
            {
                Toast.makeText(PersonalRepresentative.this, "Relation must contain only letters", Toast.LENGTH_LONG).show();
                patientRelation2.setError("Relation format is required");
                patientRelation2.requestFocus();
            }
            // If statement that uses TextUtils.isEmpty() to check if the string is empty,
            // if it is empty, an error will be displayed.
            else if(TextUtils.isEmpty(patientRelationContact2Txt))
            {
                Toast.makeText(PersonalRepresentative.this, "Number cannot be empty", Toast.LENGTH_LONG).show();
                patientRelationContact2.setError("Phone number is required");
                patientRelationContact2.requestFocus();
            }
            // Else if statement to check if the string matches the pattern within the else if() and if it does not,
            // an error will be displayed
            else if(!PHONE_PATTERN.matcher(patientRelationContact2Txt).matches())
            {
                Toast.makeText(PersonalRepresentative.this, "Number must be 123-123-1234", Toast.LENGTH_LONG).show();
                patientRelationContact2.setError("Phone number format is required");
                patientRelationContact2.requestFocus();
            }
            // If statement that uses TextUtils.isEmpty() to check if the string is empty,
            // if it is empty, an error will be displayed.
            else if(TextUtils.isEmpty(personRep3Txt)) // 3rd Representative
            {
                Toast.makeText(PersonalRepresentative.this, "Name cannot be empty", Toast.LENGTH_LONG).show();
                personalRep3.setError("Name is required");
                personalRep3.requestFocus();
            }
            // Else if statement to check if the string length is less than 20 characters, if > 20 then
            // another error will be thrown
            else if(personRep3Txt.length() > 20) // 3rd Representative
            {
                Toast.makeText(PersonalRepresentative.this, "Name cannot be > 20 characters", Toast.LENGTH_LONG).show();
                personalRep3.setError("Max character limit reached");
                personalRep3.requestFocus();
            }
            // Else if statement to check if the string matches the pattern within the else if() and if it does not,
            // an error will be displayed
            else if(!LETTERS_PATTERN.matcher(personRep3Txt).matches())
            {
                Toast.makeText(PersonalRepresentative.this, "Name must contain only letters", Toast.LENGTH_LONG).show();
                personalRep3.setError("Name format is required");
                personalRep3.requestFocus();
            }
            // If statement that uses TextUtils.isEmpty() to check if the string is empty,
            // if it is empty, an error will be displayed.
            // || operator to also check if the string length is less than 20 characters, if > 20 then
            // another error will be thrown
            else if(TextUtils.isEmpty(patientRelation3Txt) || patientRelation3Txt.length() > 20)
            {
                Toast.makeText(PersonalRepresentative.this, "Relationship cannot be empty", Toast.LENGTH_LONG).show();
                patientRelation3.setError("Relationship is required");
                patientRelation3.requestFocus();
            }
            // Else if statement to check if the string matches the pattern within the else if() and if it does not,
            // an error will be displayed
            else if(!LETTERS_PATTERN.matcher(patientRelation3Txt).matches())
            {
                Toast.makeText(PersonalRepresentative.this, "Relation must contain only letters", Toast.LENGTH_LONG).show();
                patientRelation3.setError("Relation format is required");
                patientRelation3.requestFocus();
            }
            // If statement that uses TextUtils.isEmpty() to check if the string is empty,
            // if it is empty, an error will be displayed.
            else if(TextUtils.isEmpty(patientRelationContact3Txt))
            {
                Toast.makeText(PersonalRepresentative.this, "Phone number cannot be empty", Toast.LENGTH_LONG).show();
                patientRelationContact3.setError("Phone number is required");
                patientRelationContact3.requestFocus();
            }
            // Else if statement to check if the string matches the pattern within the else if() and if it does not,
            // an error will be displayed
            else if(!PHONE_PATTERN.matcher(patientRelationContact3Txt).matches())
            {
                Toast.makeText(PersonalRepresentative.this, "Number must be 123-123-1234", Toast.LENGTH_LONG).show();
                patientRelationContact3.setError("Phone number format is required");
                patientRelationContact3.requestFocus();
            }
            // If statement that uses TextUtils.isEmpty() to check if the string is empty,
            // if it is empty, an error will be displayed.
            else if(TextUtils.isEmpty(patientSig4Txt))
            {
                Toast.makeText(PersonalRepresentative.this, "Signature name cannot be empty", Toast.LENGTH_LONG).show();
                patientSigned4.setError("Signature is required");
                patientSigned4.requestFocus();
            }
            // Else if statement to check if the string length is less than 20 characters, if > 20 then
            // another error will be thrown.
            else if(patientSig4Txt.length() > 30)
            {
                Toast.makeText(PersonalRepresentative.this, "Signature name cannot be empty", Toast.LENGTH_LONG).show();
                patientSigned4.setError("Signature is required");
                patientSigned4.requestFocus();
            }
            // Else if statement to check if the string matches the pattern within the else if() and if it does not,
            // an error will be displayed
            else if(!LETTERS_PATTERN.matcher(patientSig4Txt).matches())
            {
                Toast.makeText(PersonalRepresentative.this, "Name can only contain letters", Toast.LENGTH_LONG).show();
                patientSignature4.setError("Name format is required");
                patientSignature4.requestFocus();
            }
            // If statement that uses TextUtils.isEmpty() to check if the string is empty,
            // if it is empty, an error will be displayed.
            else if(!patientSig4Txt.equals(patientSignedTxt4))
            {
                Toast.makeText(PersonalRepresentative.this, "Signature must match name", Toast.LENGTH_LONG).show();
                patientSignature4.setError("Name match is required");
                patientSignature4.requestFocus();
            }
            // If statement that uses TextUtils.isEmpty() to check if the string is empty,
            // if it is empty, an error will be displayed.
            else if(TextUtils.isEmpty(patientDate4Txt))
            {
                Toast.makeText(PersonalRepresentative.this, "Date cannot be empty", Toast.LENGTH_LONG).show();
                patientDate4.setError("Date is required");
                patientDate4.requestFocus();
            }
            // Else if statement to check if the string matches the pattern within the else if() and if it does not,
            // an error will be displayed
            else if(!DATE_PATTERN.matcher(patientDate4Txt).matches())
            {
                Toast.makeText(PersonalRepresentative.this, "Date must be MM-DD-YYYY", Toast.LENGTH_LONG).show();
                patientDate4.setError("Date format is required");
                patientDate4.requestFocus();
            }
            // Else block is executed if all the inputs previously are valid. The inputs will be set using a setter to the data object
            // that will save the information and move it to the next form
            else
            {
                data.setPatientSignedForm4(patientSignedTxt4);
                data.setDateOfBirthForm4(patientDOB4Txt4);
                data.setHomeAddressForm4(patientHomeAddTxt4);
                data.setCityForm4(patientCityTxt4);
                data.setStateForm4(PatientStateTxt4);
                data.setZipCodeForm4(patientZipTxt4);
                data.setPhoneNumberForm4(patientHomeNumberTxt4);
                data.setPersonalRep1(personRep1Txt);
                data.setRelationship1(patientRelation1Txt);
                data.setPersonalRepPhone1(patientRelationContact1Txt);
                data.setPersonalRep2(personRep2Txt);
                data.setRelationship2(patientRelation2Txt);
                data.setPersonalRepPhone2(patientRelationContact2Txt);
                data.setPersonalRep3(personRep3Txt);
                data.setRelationship3(patientRelation3Txt);
                data.setPersonalRepPhone3(patientRelationContact3Txt);
                data.setPatientSignatureForm4(patientSig4Txt);
                data.setConsentDateForm4(patientDate4Txt);
                Intent StressForm = new Intent(this, StressForm.class);
                StressForm.putExtra("patientdata",data);
                startActivity(StressForm);
            }

        }
    }
}