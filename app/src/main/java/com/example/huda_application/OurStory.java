package com.example.huda_application;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class OurStory extends AppCompatActivity implements View.OnClickListener{
                                                                                            // Class to show our story page for the application
    private ImageView backButton;                                                           // this will show a static page that is displayed

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_our_story);

        backButton = (ImageView) findViewById(R.id.backButton);                               // Back button used to take back to the main application page
        backButton.setOnClickListener((View.OnClickListener) this);
    }
    public void onClick(View view) {
        if (view.getId() == R.id.backButton) {
            Intent Menu = new Intent(this, MainApplication.class);                  // when the back button is clicked the main application page will open
            startActivity(Menu);
        }
    }
}