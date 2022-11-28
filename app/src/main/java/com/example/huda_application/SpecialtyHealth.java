package com.example.huda_application;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class SpecialtyHealth extends AppCompatActivity implements View.OnClickListener{
                                                                        // Class that is used to show the speciality health services
    private ImageView backButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_specialty_health);
                                                                        // Back button to take the patient back to the main page of the application
        backButton = (ImageView) findViewById(R.id.backButton);
        backButton.setOnClickListener((View.OnClickListener) this);
    }
    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.backButton) {
            Intent prev = new Intent(this, NewLogin.class);             // Back button for the application
            startActivity(prev);
        }
    }
}