package com.example.huda_application;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainApplication extends AppCompatActivity {

    private Button announcements;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_application);

        int announcements_ID = R.id.announcementsPage;
        announcements = (Button) findViewById(R.id.announcementsPage);
        announcements.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openAnnouncementsView();
            }
        });
    }

    protected void openAnnouncementsView(){
        Intent Announcements_Activity = new Intent(this , Announcements.class);
        startActivity(Announcements_Activity);
    }

}