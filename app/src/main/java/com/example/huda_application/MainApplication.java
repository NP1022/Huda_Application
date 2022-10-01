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


    private TextView Partner_button, Contact_Us, Our_story, signOut;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_application);

        Partner_button  = (TextView) findViewById(R.id.partnersPage);
        Contact_Us = (TextView) findViewById(R.id.contactUsPage);
        Our_story = (TextView) findViewById(R.id.ourStoryPage);
        signOut = (TextView) findViewById(R.id.logoutButton);
        Partner_button.setOnClickListener(this);
        Contact_Us.setOnClickListener(this);
        Our_story.setOnClickListener(this);
        signOut.setOnClickListener(this);

        mAuth = FirebaseAuth.getInstance();
        FirebaseUser fbUser = mAuth.getCurrentUser();

        if(fbUser != null && !fbUser.isEmailVerified())  // if user is not null and their email is not verified
        {
            startActivity(new Intent(MainApplication.this,MainActivity.class)); // send them to the login activity
        }

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
        else if (view.getId() == R.id.ourStoryPage){

            Intent OurStory = new Intent(this , OurStory.class);
            startActivity(OurStory);
        }
        else if (view.getId() == R.id.logoutButton)
        {
            mAuth.signOut();
            startActivity(new Intent(MainApplication.this , MainActivity.class));

        }

    }
}