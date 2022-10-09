package com.example.huda_application;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class Contact_Us extends AppCompatActivity {

    TextView HUDAnumber;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact_us);
        HUDAnumber = (TextView) findViewById(R.id.HUDAnumber);
        HUDAnumber.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent HUDACall = new Intent(Intent.ACTION_DIAL);
                HUDACall.setData(Uri.parse("tel:3138658446"));
                startActivity(HUDACall);
            }
        });
    }
}