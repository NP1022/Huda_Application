package com.example.huda_application;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.huda_application.user.PatientFormData;

import java.util.Locale;

public class PersonalRepresentative extends AppCompatActivity implements View.OnClickListener{

    private PatientFormData data;
    private TextView StressForm;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_personal_representative);
        Intent extras = getIntent();

        if (extras != null)
        {
            data =  (PatientFormData)  extras.getSerializableExtra("patientdata");
        }
        //Log.i("info  ", "The user name in the application is   " + lastNameTxt + firstNameTxt );
        StressForm = (TextView) findViewById(R.id.nextForm4);
        StressForm.setOnClickListener(this);
    }


    @Override
    public void onClick(View view)
    {
        if (view.getId() == R.id.nextForm4)
        {
            // EditText
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

            // Strings
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



            if(TextUtils.isEmpty(patientSignedTxt4) || patientSignedTxt4.length() > 30)
            {
                Toast.makeText(PersonalRepresentative.this, "Signed name cannot be empty", Toast.LENGTH_LONG).show();
                patientSigned4.setError("Date is required");
                patientSigned4.requestFocus();
            }
            else
            {
                Intent StressForm = new Intent(this, StressForm.class);
                StressForm.putExtra("patientdata",data);
                startActivity(StressForm);
            }

        }
    }
}