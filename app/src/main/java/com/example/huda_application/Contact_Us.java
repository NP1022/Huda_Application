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

    TextView HUDAnumber, HUDAaddresss;
    private ImageView Facebook, Twitter, LinkedIn, Instagram;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact_us);
        HUDAnumber = (TextView) findViewById(R.id.HUDAnumber);
        HUDAaddresss = (TextView) findViewById(R.id.HUDAaddress);
        Facebook = (ImageView) findViewById(R.id.facebook);
        Twitter = (ImageView) findViewById(R.id.twitter);
        LinkedIn = (ImageView) findViewById(R.id.linkedIn);
        Instagram = (ImageView) findViewById(R.id.instagram);



        HUDAnumber.setOnClickListener((View.OnClickListener) this);
        HUDAaddresss.setOnClickListener((View.OnClickListener) this);
        Facebook.setOnClickListener((View.OnClickListener) this);
        Twitter.setOnClickListener((View.OnClickListener) this);
        LinkedIn.setOnClickListener((View.OnClickListener) this);
        Instagram.setOnClickListener((View.OnClickListener) this);
    }

    @Override
    public void onClick(View view)
    {
        if (view.getId() == R.id.HUDAnumber)
        {
            Intent HUDACall = new Intent(Intent.ACTION_DIAL);
            HUDACall.setData(Uri.parse("tel:3138658446"));
            startActivity(HUDACall);
        }
        else if (view.getId() == R.id.HUDAaddress)
        {
            Uri HUDAAddress = Uri.parse("google.navigation:q=13240+Woodrow+Wilson+St+Detroit+MI+48238");
            Intent HUDAMap = new Intent(Intent.ACTION_VIEW, HUDAAddress);
            HUDAMap.setPackage("com.google.android.apps.maps");
            startActivity(HUDAMap);
        }
        else if (view.getId() == R.id.facebook)
        {
            startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.facebook.com/HudaClinic/")));

        }
        else if (view.getId() == R.id.twitter)
        {
            startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://twitter.com/hudaclinic")));
        }
        else if (view.getId() == R.id.linkedIn)
        {
            startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.linkedin.com/company/hudaclinic/")));

        }
        else if (view.getId() == R.id.instagram)
        {
            startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.instagram.com/hudaclinic/")));
        }
    }
}
