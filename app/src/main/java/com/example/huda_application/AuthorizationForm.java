package com.example.huda_application;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class AuthorizationForm extends AppCompatActivity implements View.OnClickListener{

    private TextView PersonalRepresentative;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_authorization_form);

        PersonalRepresentative = (TextView) findViewById(R.id.nextForm3);
        PersonalRepresentative.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.nextForm3){
            Intent PersonalRepresentative = new Intent (this, PersonalRepresentative.class);
            startActivity(PersonalRepresentative);
        }

    }
}