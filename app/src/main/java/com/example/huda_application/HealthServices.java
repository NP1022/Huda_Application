package com.example.huda_application;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class HealthServices extends AppCompatActivity {
    TextView primaryCareHeader, primaryCareText1, primaryCareText2;
    Button primaryCareButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_health_services);
    }
    @Override
    public <T extends View> T findViewById(int id) {
        return super.findViewById(id);
    }

    primaryCareHeader = (TextView)findViewById(R.id.primaryCareHeader);


}