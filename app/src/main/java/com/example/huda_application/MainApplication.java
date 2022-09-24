package com.example.huda_application;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainApplication extends AppCompatActivity implements View.OnClickListener
{


    private TextView Partner_button, Contact_Us, Announcements_Button;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_application);

        Partner_button  = (TextView) findViewById(R.id.partnersPage);
        Contact_Us = (TextView) findViewById(R.id.contactUsPage);
        Announcements_Button = (TextView) findViewById(R.id.announcementsPage);

        Partner_button.setOnClickListener(this);
        Contact_Us.setOnClickListener(this);
        Announcements_Button.setOnClickListener(this);


    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.partnersPage){
            Intent Partners = new Intent(this ,Partners.class);
            startActivity(Partners);

        }
        else if (view.getId() == R.id.contactUsPage){

            Intent Contact_Us = new Intent(this ,Contact_Us.class);
            startActivity(Contact_Us);

        }

        else if (view.getId() == R.id.announcementsPage){
            Intent Announcements = new Intent (this, Announcements.class);
            startActivity(Announcements);
        }

    }
}