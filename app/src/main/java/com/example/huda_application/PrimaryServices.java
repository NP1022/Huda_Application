package com.example.huda_application;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class PrimaryServices extends AppCompatActivity {

    private ImageView backButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_primary_services);

//        backButton = (ImageView) findViewById(R.id.backButton);
//        backButton.setOnClickListener((View.OnClickListener) this);
    }

//    public void onClick(View view) {
//        if (view.getId() == R.id.backButton) {
//            Intent prev = new Intent(this, NewLogin.class);
//            startActivity(prev);
//        }
//    }
}