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

    private FirebaseAuth mAuth;
    private TextView Partner_button, Contact_Us, Our_story;
    private TextView logout = findViewById(R.id.logoutButton);

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_application);

        Partner_button  = (TextView) findViewById(R.id.partnersPage);
        Contact_Us = (TextView) findViewById(R.id.contactUsPage);
        Our_story = (TextView) findViewById(R.id.ourStoryPage);
        Partner_button.setOnClickListener(this);
        Contact_Us.setOnClickListener(this);
        Our_story.setOnClickListener(this);

        mAuth = FirebaseAuth.getInstance();
        logout.setOnClickListener(new View.OnClickListener()  // onclick for signing user out
        {
            @Override
            public void onClick(View view)
            {
                mAuth.signOut();
            }
        });

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

    }
}