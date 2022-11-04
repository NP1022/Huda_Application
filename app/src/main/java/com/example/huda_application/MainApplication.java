package com.example.huda_application;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.huda_application.appointment.AppointmentManager;
import com.example.huda_application.firebase.FirebaseClient;
import com.example.huda_application.user.User;
import com.example.huda_application.user.UserManager;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class MainApplication extends AppCompatActivity implements View.OnClickListener {


    private TextView Partner_button, Contact_Us, Our_story, signOut, Announcements, PatientPortal;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_application);

        PatientPortal = findViewById(R.id.Patients);
        Partner_button = findViewById(R.id.partnersPage);
        Contact_Us = findViewById(R.id.contactUsPage);
        Our_story = findViewById(R.id.ourStoryPage);
        signOut = findViewById(R.id.logoutButton);
        Announcements = findViewById(R.id.announcementsPage);
        Partner_button.setOnClickListener(this);
        Contact_Us.setOnClickListener(this);
        Our_story.setOnClickListener(this);
        signOut.setOnClickListener(this);
        Announcements.setOnClickListener(this);
        PatientPortal.setOnClickListener(this);

        mAuth = FirebaseAuth.getInstance();
        FirebaseUser fbUser = mAuth.getCurrentUser();

        // if user is not null and their email is not verified
        if (fbUser != null && !fbUser.isEmailVerified()) {
            startActivity(new Intent(MainApplication.this, MainActivity.class)); // send them to the login activity
        }

        if (fbUser != null) {
            FirebaseDatabase.getInstance().getReference(User.class.getSimpleName()).child(fbUser.getUid()).addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    UserManager.getInstance().setCurrentUser(FirebaseClient.convertToUser(snapshot));
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {
                }
            });
        }
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.partnersPage) {
            Intent Partners = new Intent(this, Partners.class);
            startActivity(Partners);
        } else if (view.getId() == R.id.contactUsPage) {
            Intent Contact_Us = new Intent(this, Contact_Us.class);
            startActivity(Contact_Us);
        } else if (view.getId() == R.id.ourStoryPage) {
            Intent OurStory = new Intent(this, OurStory.class);
            startActivity(OurStory);
        } else if (view.getId() == R.id.logoutButton) {
            UserManager.getInstance().removeCurrentUser();
            mAuth.signOut();
            startActivity(new Intent(MainApplication.this, MainActivity.class));
        } else if (view.getId() == R.id.announcementsPage) {
            Intent Announcements = new Intent(this, Announcements.class);
            startActivity(Announcements);
        } else if (view.getId() == R.id.Patients) {
            Intent Patient_page = new Intent(this, Appointments.class);
            startActivity(Patient_page);
        }
    }
}