package com.example.huda_application;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.FaceDetector;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.rpc.Help;

public class Contact_Us extends AppCompatActivity implements View.OnClickListener {


    TextView HUDAnumber, HUDAaddresss; // Initializing variables that connect to activity_contact_us.xml
    private ImageView Facebook, Twitter, LinkedIn, Instagram, backButton; // ImageViews of social media logos to redirect on click


    @Override
    protected void onCreate(Bundle savedInstanceState) // onCreate function to set the page with correct parameters
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact_us); // Set view on patient end to activity_contact_us XML
        HUDAnumber = (TextView) findViewById(R.id.HUDAnumber); // Defining all variables to find associated View IDs from XML page
        HUDAaddresss = (TextView) findViewById(R.id.HUDAaddress);
        Facebook = (ImageView) findViewById(R.id.facebook);
        Twitter = (ImageView) findViewById(R.id.twitter);
        LinkedIn = (ImageView) findViewById(R.id.linkedIn);
        Instagram = (ImageView) findViewById(R.id.instagram);
        backButton = (ImageView) findViewById(R.id.backButton);

        HUDAnumber.setOnClickListener((View.OnClickListener) this); // Wiring all variables to View.OnClickListener in order to click on
        HUDAaddresss.setOnClickListener((View.OnClickListener) this);
        Facebook.setOnClickListener((View.OnClickListener) this);
        Twitter.setOnClickListener((View.OnClickListener) this);
        LinkedIn.setOnClickListener((View.OnClickListener) this);
        Instagram.setOnClickListener((View.OnClickListener) this);
        backButton.setOnClickListener((View.OnClickListener) this);
    }

    @Override
    public void onClick(View view) // onClick function, takes a view as input, outputs new Intent to start activity, redirecting to desired page/XML
    {
        if (view.getId() == R.id.HUDAnumber) // If the patient clicks on the phone number...
        {
            Intent HUDACall = new Intent(Intent.ACTION_DIAL);
            HUDACall.setData(Uri.parse("tel:3138658446")); // The HUDA phone number is set in the backend
            startActivity(HUDACall); // And the patient's phone dial pad
        }
        else if (view.getId() == R.id.HUDAaddress) // If patient clicks on HUDA clinic address...
        {
            Uri HUDAAddress = Uri.parse("google.navigation:q=13240+Woodrow+Wilson+St+Detroit+MI+48238"); // Reading/parsing string of HUDA clinic address
            Intent HUDAMap = new Intent(Intent.ACTION_VIEW, HUDAAddress); // Initializing new activity to open
            HUDAMap.setPackage("com.google.android.apps.maps"); // Setting Google Maps to open the activity
            startActivity(HUDAMap); // Starting activity, redirecting patient to Google Maps with address and route to HUDA clinic
        }
        else if (view.getId() == R.id.facebook) // If patient clicks on Facebook logo
        {
            startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.facebook.com/HudaClinic/"))); // Patient is redirected to HUDA's Facebook website page
        }
        else if (view.getId() == R.id.twitter) // If patient clicks on Twitter logo
        {
            startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://twitter.com/hudaclinic"))); // Patient is redirected to HUDA's Twitter website page
        }
        else if (view.getId() == R.id.linkedIn) // If patient clicks on LinkedIn logo
        {
            startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.linkedin.com/company/hudaclinic/"))); // Patient is redirected to HUDA's LinkedIn website page
        }
        else if (view.getId() == R.id.instagram) // If patient clicks on Instagram logo
        {
            startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.instagram.com/hudaclinic/"))); // Patient is redirected to HUDA's Instagram website page
        }
        else if (view.getId() == R.id.backButton) // If the patient clicks on the back button
        {
            Intent prev = new Intent(this, MainApplication.class);
            startActivity(prev); // Patient is redirected to previous page which is the Main Menu page
        }
    }
}
