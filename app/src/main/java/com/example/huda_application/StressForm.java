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

import com.example.huda_application.user.PatientFormData;

import java.util.regex.Pattern;



public class StressForm extends AppCompatActivity
{
    private static Pattern RESPONSE_PATTERN = Pattern.compile("^(?:yes|Yes|No|no)$");
    private static Pattern RANGE_PATTERN = Pattern.compile("^(?:0|1|2|3)$");
    private static Pattern SUM_PATTERN = Pattern.compile("([0-9]|1[0-9]|2[0-4])$");

    private PatientFormData data;
    private TextView PatientInfoForm;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stress_form);
        Intent extras = getIntent();

        // if statement to check if the extras are null, if it is null
        // then set data to the extras.getSerializableExtra();
        if (extras != null)
        {
            data =  (PatientFormData)  extras.getSerializableExtra("patientdata");
        }

        // setting the EditText variable equal to the ID from the XML

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

                // If statement that uses TextUtils.isEmpty() to check if the string is empty,
                // if it is empty, an error will be displayed.
                if(TextUtils.isEmpty(q1s1Text))
                {
                    Toast.makeText(StressForm.this,"Response cannot be empty",Toast.LENGTH_LONG).show();
                    q1s1.setError("Input is required");
                    q1s1.requestFocus();
                }
                // Else if statement to check if the string matches the pattern within the else if() and if it does not,
                // an error will be displayed
                else if(!RANGE_PATTERN.matcher(q1s1Text).matches())
                {
                    Toast.makeText(StressForm.this,"Must be 0,1,2 or 3",Toast.LENGTH_LONG).show();
                    q1s1.setError("Valid input is required");
                    q1s1.requestFocus();
                }
                else if(TextUtils.isEmpty(q2s1Text))
                {
                    Toast.makeText(StressForm.this,"Response cannot be empty",Toast.LENGTH_LONG).show();
                    q2s1.setError("Input is required");
                    q2s1.requestFocus();
                }
                else if(!RANGE_PATTERN.matcher(q2s1Text).matches())
                {
                    Toast.makeText(StressForm.this,"Must be 0,1,2 or 3",Toast.LENGTH_LONG).show();
                    q2s1.setError("Valid input is required");
                    q2s1.requestFocus();
                }
                else if(TextUtils.isEmpty(q3s1Text))
                {
                    Toast.makeText(StressForm.this,"Response cannot be empty",Toast.LENGTH_LONG).show();
                    q3s1.setError("Input is required");
                    q3s1.requestFocus();
                }
                else if(!RANGE_PATTERN.matcher(q3s1Text).matches())
                {
                    Toast.makeText(StressForm.this,"Must be 0,1,2 or 3",Toast.LENGTH_LONG).show();
                    q3s1.setError("Valid input is required");
                    q3s1.requestFocus();
                }
                else if(TextUtils.isEmpty(q4s1Text))
                {
                    Toast.makeText(StressForm.this,"Response cannot be empty",Toast.LENGTH_LONG).show();
                    q4s1.setError("Input is required");
                    q4s1.requestFocus();
                }
                else if(!RANGE_PATTERN.matcher(q4s1Text).matches())
                {
                    Toast.makeText(StressForm.this,"Must be 0,1,2 or 3",Toast.LENGTH_LONG).show();
                    q4s1.setError("Valid input is required");
                    q4s1.requestFocus();
                }
                else if(TextUtils.isEmpty(q5s1Text))
                {
                    Toast.makeText(StressForm.this,"Response cannot be empty",Toast.LENGTH_LONG).show();
                    q5s1.setError("Input is required");
                    q5s1.requestFocus();
                }
                else if(!RANGE_PATTERN.matcher(q5s1Text).matches())
                {
                    Toast.makeText(StressForm.this,"Must be 0,1,2 or 3",Toast.LENGTH_LONG).show();
                    q5s1.setError("Valid input is required");
                    q5s1.requestFocus();
                }
                else if(TextUtils.isEmpty(q6s1Text))
                {
                    Toast.makeText(StressForm.this,"Response cannot be empty",Toast.LENGTH_LONG).show();
                    q6s1.setError("Input is required");
                    q6s1.requestFocus();
                }
                else if(!RANGE_PATTERN.matcher(q6s1Text).matches())
                {
                    Toast.makeText(StressForm.this,"Must be 0,1,2 or 3",Toast.LENGTH_LONG).show();
                    q6s1.setError("Valid input is required");
                    q6s1.requestFocus();
                }
                else if(TextUtils.isEmpty(q7s1Text))
                {
                    Toast.makeText(StressForm.this,"Response cannot be empty",Toast.LENGTH_LONG).show();
                    q7s1.setError("Input is required");
                    q7s1.requestFocus();
                }
                else if(!RANGE_PATTERN.matcher(q7s1Text).matches())
                {
                    Toast.makeText(StressForm.this,"Must be 0,1,2 or 3",Toast.LENGTH_LONG).show();
                    q7s1.setError("Valid input is required");
                    q7s1.requestFocus();
                }
                else if(TextUtils.isEmpty(q8s1Text))
                {
                    Toast.makeText(StressForm.this,"Response cannot be empty",Toast.LENGTH_LONG).show();
                    q8s1.setError("Input is required");
                    q8s1.requestFocus();
                }
                else if(!RANGE_PATTERN.matcher(q8s1Text).matches())
                {
                    Toast.makeText(StressForm.this,"Must be 0,1,2 or 3",Toast.LENGTH_LONG).show();
                    q8s1.setError("Valid input is required");
                    q8s1.requestFocus();
                }
                 else if(TextUtils.isEmpty(s1TotalsText))
                {
                    Toast.makeText(StressForm.this,"Total cannot be empty",Toast.LENGTH_LONG).show();
                    s1Totals.setError("Total sum is required");
                    s1Totals.requestFocus();
                }
                 else if (!SUM_PATTERN.matcher(s1TotalsText).matches())
                 {
                     Toast.makeText(StressForm.this,"Total must be within valid range",Toast.LENGTH_LONG).show();
                     s1Totals.setError("Valid sum is required");
                     s1Totals.requestFocus();
                }


                // section 2 inputs
                else if (TextUtils.isEmpty(q1s2Text))
                {
                    Toast.makeText(StressForm.this, "Response cannot be empty", Toast.LENGTH_LONG).show();
                    q1s2.setError("Input is required");
                    q1s2.requestFocus();
                }
                else if(!RANGE_PATTERN.matcher(q1s2Text).matches())
                {
                    Toast.makeText(StressForm.this,"Must be 0,1,2 or 3",Toast.LENGTH_LONG).show();
                    q1s2.setError("Valid input is required");
                    q1s2.requestFocus();
                }
                else if (TextUtils.isEmpty(q2s2Text))
                {
                    Toast.makeText(StressForm.this, "Response cannot be empty", Toast.LENGTH_LONG).show();
                    q2s2.setError("Input is required");
                    q2s2.requestFocus();
                }
                else if(!RANGE_PATTERN.matcher(q2s2Text).matches())
                {
                    Toast.makeText(StressForm.this,"Must be 0,1,2 or 3",Toast.LENGTH_LONG).show();
                    q2s2.setError("Valid input is required");
                    q2s2.requestFocus();
                }
                else if (TextUtils.isEmpty(q3s2Text))
                {
                    Toast.makeText(StressForm.this, "Response cannot be empty", Toast.LENGTH_LONG).show();
                    q3s2.setError("Input is required");
                    q3s2.requestFocus();
                }
                else if(!RANGE_PATTERN.matcher(q3s2Text).matches())
                {
                    Toast.makeText(StressForm.this,"Must be 0,1,2 or 3",Toast.LENGTH_LONG).show();
                    q3s2.setError("Valid input is required");
                    q3s2.requestFocus();
                }
                else if (TextUtils.isEmpty(q4s2Text))
                {
                    Toast.makeText(StressForm.this, "Response cannot be empty", Toast.LENGTH_LONG).show();
                    q4s2.setError("Input is required");
                    q4s2.requestFocus();
                }
                else if(!RANGE_PATTERN.matcher(q4s2Text).matches())
                {
                    Toast.makeText(StressForm.this,"Must be 0,1,2 or 3",Toast.LENGTH_LONG).show();
                    q4s2.setError("Valid input is required");
                    q4s2.requestFocus();
                }
                else if (TextUtils.isEmpty(q5s2Text))
                {
                    Toast.makeText(StressForm.this, "Response cannot be empty", Toast.LENGTH_LONG).show();
                    q5s2.setError("Input is required");
                    q5s2.requestFocus();
                }
                else if(!RANGE_PATTERN.matcher(q5s2Text).matches())
                {
                    Toast.makeText(StressForm.this,"Must be 0,1,2 or 3",Toast.LENGTH_LONG).show();
                    q5s2.setError("Valid input is required");
                    q5s2.requestFocus();
                }
                else if (TextUtils.isEmpty(q6s2Text))
                {
                    Toast.makeText(StressForm.this, "Response cannot be empty", Toast.LENGTH_LONG).show();
                    q6s2.setError("Input is required");
                    q6s2.requestFocus();
                }
                else if(!RANGE_PATTERN.matcher(q6s2Text).matches())
                {
                    Toast.makeText(StressForm.this,"Must be 0,1,2 or 3",Toast.LENGTH_LONG).show();
                    q6s2.setError("Valid input is required");
                    q6s2.requestFocus();
                }
                else if (TextUtils.isEmpty(q7s2Text))
                {
                    Toast.makeText(StressForm.this, "Response cannot be empty", Toast.LENGTH_LONG).show();
                    q7s2.setError("Input is required");
                    q7s2.requestFocus();
                }
                else if(!RANGE_PATTERN.matcher(q7s2Text).matches())
                {
                    Toast.makeText(StressForm.this,"Must be 0,1,2 or 3",Toast.LENGTH_LONG).show();
                    q7s2.setError("Valid input is required");
                    q7s2.requestFocus();
                }
                else if (TextUtils.isEmpty(s2TotalsText))
                {
                    Toast.makeText(StressForm.this, "Total cannot be empty", Toast.LENGTH_LONG).show();
                    s2Totals.setError("Total sum is required");
                    s2Totals.requestFocus();
                }
                else if (!SUM_PATTERN.matcher(s2TotalsText).matches())
                {
                    Toast.makeText(StressForm.this,"Total cannot be empty",Toast.LENGTH_LONG).show();
                    s2Totals.setError("Total is required");
                    s2Totals.requestFocus();
                }


                //section 3 inputs
                else if (TextUtils.isEmpty(q1s3Text))
                {
                    Toast.makeText(StressForm.this, "Response cannot be empty", Toast.LENGTH_LONG).show();
                    q1s3.setError("Input is required");
                    q1s3.requestFocus();
                }
                else if(!RESPONSE_PATTERN.matcher(q1s3Text).matches())
                {
                    Toast.makeText(StressForm.this, "Must be Yes or No", Toast.LENGTH_LONG).show();
                    q1s3.setError("Input must be Yes or No");
                    q1s3.requestFocus();
                }
                else if (TextUtils.isEmpty(q2s3Text))
                {
                    Toast.makeText(StressForm.this, "Response cannot be empty", Toast.LENGTH_LONG).show();
                    q2s3.setError("Input is required");
                    q2s3.requestFocus();
                }
                else if(!RESPONSE_PATTERN.matcher(q2s3Text).matches())
                {
                    Toast.makeText(StressForm.this, "Must be Yes or No", Toast.LENGTH_LONG).show();
                    q2s3.setError("Input must be Yes or No");
                    q2s3.requestFocus();
                }
                else if (TextUtils.isEmpty(q3s3Text))
                {
                    Toast.makeText(StressForm.this, "Response cannot be empty", Toast.LENGTH_LONG).show();
                    q3s3.setError("Input is required");
                    q3s3.requestFocus();
                }
                else if(!RESPONSE_PATTERN.matcher(q3s3Text).matches())
                {
                    Toast.makeText(StressForm.this, "Must be Yes or No", Toast.LENGTH_LONG).show();
                    q3s3.setError("Input must be Yes or No");
                    q3s3.requestFocus();
                }
                else if (TextUtils.isEmpty(q4s3Text))
                {
                    Toast.makeText(StressForm.this, "Response cannot be empty", Toast.LENGTH_LONG).show();
                    q4s3.setError("Input is required");
                    q4s3.requestFocus();
                }
                else if(!RESPONSE_PATTERN.matcher(q4s3Text).matches())
                {
                    Toast.makeText(StressForm.this, "Must be Yes or No", Toast.LENGTH_LONG).show();
                    q4s3.setError("Input must be Yes or No");
                    q4s3.requestFocus();
                }
                // Else block is executed if all the inputs previously are valid. The inputs will be set using a setter to the data object
                // that will save the information and move it to the next form
                else
                {
                    data.setQuestion1Section1Form5(q1s1Text);
                    data.setQuestion2Section1Form5(q1s2Text);
                    data.setQuestion3Section1Form5(q3s1Text);
                    data.setQuestion4Section1Form5(q4s1Text);
                    data.setQuestion5Section1Form5(q5s1Text);
                    data.setQuestion6Section1Form5(q6s1Text);
                    data.setQuestion7Section1Form5(q7s1Text);
                    data.setQuestion8Section1Form5(q8s1Text);
                    data.setSection1TotalsForm5(s1TotalsText);

                    data.setQuestion1Section2Form5(q1s2Text);
                    data.setQuestion2Section2Form5(q2s2Text);
                    data.setQuestion3Section2Form5(q3s2Text);
                    data.setQuestion4Section2Form5(q4s2Text);
                    data.setQuestion5Section2Form5(q5s2Text);
                    data.setQuestion6Section2Form5(q6s2Text);
                    data.setQuestion7Section2Form5(q7s2Text);
                    data.setSection2TotalsForm5(s2TotalsText);

                    data.setQuestion1Section3Form5(q1s3Text);
                    data.setQuestion2Section3Form5(q2s3Text);
                    data.setQuestion3Section3Form5(q3s3Text);
                    data.setQuestion4Section3Form5(q4s3Text);

                    Intent PatientInfoForm = new Intent(StressForm.this, PatientInfoForm.class);
                    PatientInfoForm.putExtra("patientdata", data);
                    startActivity(PatientInfoForm);

                }

            }
        });

    }
}


