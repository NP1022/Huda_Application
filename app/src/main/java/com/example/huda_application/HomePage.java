package com.example.huda_application;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class HomePage extends AppCompatActivity {


    FirebaseAuth mAuth; // Added firebase to homepage
    FirebaseUser mUser;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);

        mAuth = FirebaseAuth.getInstance(); // firebase variable to save instance
        mUser = FirebaseAuth.getInstance().getCurrentUser();
    }
    protected void onStart()
    {
        super.onStart();
        FirebaseUser user = mAuth.getCurrentUser(); // create firebase user
        if(user == null && user.isEmailVerified()) // check if user is verified
        {
            startActivity(new Intent(HomePage.this, MainActivity.class)); // check if current is logged in or off
        }
    }
}