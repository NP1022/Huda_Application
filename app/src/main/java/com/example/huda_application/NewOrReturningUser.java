package com.example.huda_application;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class NewOrReturningUser extends AppCompatActivity
{


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