package com.example.huda_application;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.TextView;

public class PersonalRepresentative extends AppCompatActivity implements View.OnClickListener{

    private TextView StressForm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_personal_representative);

        StressForm = (TextView) findViewById(R.id.nextForm4);
        StressForm.setOnClickListener(this);
    }


    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.nextForm4) {
            Intent StressForm = new Intent(this, StressForm.class);
            startActivity(StressForm);
        }
    }
}