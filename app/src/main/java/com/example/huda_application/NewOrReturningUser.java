package com.example.huda_application;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class NewOrReturningUser extends AppCompatActivity
{

                                                                            // Class used for the new or returning user for the application
    FirebaseAuth mAuth = FirebaseAuth.getInstance();                        // The new patient button will take the user to the forms
    FirebaseUser mUser = mAuth.getCurrentUser();                            // The returning user will take them to the login page of the application

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_or_returning_user);

        Button newUserBtn = findViewById(R.id.newUser);
        Button returningUserBtn = findViewById(R.id.returningUser);

            newUserBtn.setOnClickListener(new View.OnClickListener()
            {

                @Override                                                                           // If the new user button is clicked then the patient forms of the application
                public void onClick(View view)
                {
                    startActivity(new Intent(NewOrReturningUser.this,PatientForm.class));
                }
            });
            returningUserBtn.setOnClickListener(new View.OnClickListener()
            {
                @Override
                public void onClick(View view)                                                          // If a returning user button is clicked then the main activity page will open
                {
                    startActivity(new Intent(NewOrReturningUser.this,MainActivity.class));
                }
            });
        }
}