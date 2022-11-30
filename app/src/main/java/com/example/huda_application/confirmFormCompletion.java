package com.example.huda_application;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class confirmFormCompletion extends AppCompatActivity
{

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {                                                                   // Class used for the confirmation button
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_confirm_form_completion);

        Button redirectUser = findViewById(R.id.login); // set variable for button action

        redirectUser.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                Intent intent = new Intent(confirmFormCompletion.this, MainActivity.class);
                startActivity(intent);
            }
        });

    }
}