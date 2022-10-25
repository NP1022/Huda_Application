package com.example.huda_application;

import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;

import android.view.View;
import android.widget.Button;

import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.example.huda_application.databinding.ActivityReturningOrNewUserBinding;

public class ReturningOrNewUser extends AppCompatActivity
{

    private AppBarConfiguration appBarConfiguration;
    private ActivityReturningOrNewUserBinding binding;

    private Button rtnUser;
    private Button newUser;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);

        rtnUser = findViewById(R.id.returningUser);
        newUser = findViewById(R.id.newUser);

        rtnUser.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                startActivity(new Intent(ReturningOrNewUser.this,MainApplication.class));
            }
        });

        newUser.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                startActivity(new Intent(ReturningOrNewUser.this,PatientForm.class));
            }
        });


    }


}