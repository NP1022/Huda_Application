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
            "^((0[1-9]|1[012])-(0[1-9]|[12][0-9]|3[01])-(19|2[0-9])[0-9]{2})$"); // password pattern match


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_patient_form);

        final EditText todayDate = findViewById(R.id.todaysDate);

        Button buttonSubmit = findViewById(R.id.submitPatient); // set variable for button action

        buttonSubmit.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                final String dateTxt = todayDate.getText().toString().trim(); // convert the EditText to a String type

                if (TextUtils.isEmpty(dateTxt)) // check if date is empty
                {
                    Toast.makeText(PatientForm.this,"Please enter today's date",Toast.LENGTH_LONG).show();
                    todayDate.setError("Date is required");
                    todayDate.requestFocus();
                }
                else if(!DATE_PATTERN.matcher(dateTxt).matches())
                {
                    Toast.makeText(PatientForm.this,"Must be mm-dd-yyyy",Toast.LENGTH_LONG).show();
                    todayDate.setError("Format required");
                    todayDate.requestFocus();
                }
                else
                {
                    Toast.makeText(PatientForm.this,"Success!",Toast.LENGTH_LONG).show();
                }
            }
        });

    }
}