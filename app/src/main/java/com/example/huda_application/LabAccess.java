package com.example.huda_application;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.huda_application.firebase.FirebaseClient;
import com.example.huda_application.user.PatientFormData;
import com.example.huda_application.user.UserManager;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;

public class LabAccess extends AppCompatActivity
{
    private PatientFormData data;
    private FirebaseAuth mAuth;
    DatabaseReference ref;

    private TextView patientPortal;



    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lab_access);
        Intent extras = getIntent();

        DAOPatient dao = new DAOPatient();

        Button buttonSubmit = findViewById(R.id.returnPatientPortal); // set variable for button action

        // if statement to check if the extras are null, if it is null
        // then set data to the extras.getSerializableExtra();
        if (extras != null)
        {
            data =  (PatientFormData)  extras.getSerializableExtra("patientdata");
        }


        buttonSubmit.setOnClickListener(new View.OnClickListener()
        {
            @Override

            public void onClick(View view)
            {

                // data access object dad called the .add method which takes everything that is stored in the
                // data object and pushes it to the database.
                dao.add(data).addOnSuccessListener(suc->
                {
                    Toast.makeText(LabAccess.this,"Patient has been inserted",Toast.LENGTH_LONG).show();
                }).addOnFailureListener(er->
                {
                    Toast.makeText(LabAccess.this,""+er.getMessage(),Toast.LENGTH_LONG).show();
                });
                Intent intent = new Intent(LabAccess.this, confirmFormCompletion.class);
                startActivity(intent);

            }
        });


    }

}
