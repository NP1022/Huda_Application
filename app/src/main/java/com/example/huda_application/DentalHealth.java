package com.example.huda_application;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class DentalHealth extends AppCompatActivity implements  View.OnClickListener{
    private ImageView backButton;
                                                // Class that is used to show the Dental health services



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dental_health);
        backButton = (ImageView) findViewById(R.id.backButton);      // Back button to take the patient back to the main page of the application
                                                                     // Back button for the application
        backButton.setOnClickListener((View.OnClickListener) this);
    }
    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.backButton) {                          // on click for the back button of the application
            Intent prev = new Intent(this, NewLogin.class);
            startActivity(prev);
        }
    }
}