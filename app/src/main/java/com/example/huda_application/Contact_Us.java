package com.example.huda_application;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class Contact_Us extends AppCompatActivity implements View.OnClickListener {

    TextView HUDAnumber, HUDAaddresss;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact_us);
        HUDAnumber = (TextView) findViewById(R.id.HUDAnumber);
        HUDAaddresss = (TextView) findViewById(R.id.HUDAaddress);

        HUDAnumber.setOnClickListener((View.OnClickListener) this);
        HUDAaddresss.setOnClickListener((View.OnClickListener) this);
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.HUDAnumber) {
            Intent HUDACall = new Intent(Intent.ACTION_DIAL);
            HUDACall.setData(Uri.parse("tel:3138658446"));
            startActivity(HUDACall);
        }
        else if (view.getId() == R.id.HUDAaddress) {
            Uri HUDAAddress = Uri.parse("google.navigation:q=13240+Woodrow+Wilson+St+Detroit+MI+48238");
            Intent HUDAMap = new Intent(Intent.ACTION_VIEW, HUDAAddress);
            HUDAMap.setPackage("com.google.android.apps.maps");
            startActivity(HUDAMap);
        }
    }
}
