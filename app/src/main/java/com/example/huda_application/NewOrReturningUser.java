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


    FirebaseAuth mAuth = FirebaseAuth.getInstance();
    FirebaseUser mUser = mAuth.getCurrentUser();

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_or_returning_user);

        Button newUserBtn = findViewById(R.id.newUser);
        Button returningUserBtn = findViewById(R.id.returningUser);

            newUserBtn.setOnClickListener(new View.OnClickListener()
            {

                @Override
                public void onClick(View view)
                {
                    startActivity(new Intent(NewOrReturningUser.this,PatientForm.class));
                }
            });
            returningUserBtn.setOnClickListener(new View.OnClickListener()
            {
                @Override
                public void onClick(View view)
                {
                    startActivity(new Intent(NewOrReturningUser.this,MainActivity.class));
                }
            });
        }
}