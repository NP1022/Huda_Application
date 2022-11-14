package com.example.huda_application;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.huda_application.appointment.AppointmentManager;
import com.example.huda_application.firebase.FirebaseClient;
import com.example.huda_application.user.Message;
import com.example.huda_application.user.User;
import com.example.huda_application.user.UserManager;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class MainApplication extends AppCompatActivity implements View.OnClickListener {


    private TextView Partner_button, Contact_Us, Our_story, signOut, Announcements, PatientPortal ,Message_tab;
    private ImageView profile, Inbox;
    private FirebaseAuth mAuth;
    private TextView name;
    private TextView email;
    private TextView DOB;
    NavigationView profileMenu;
    View headerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.new_profile_menu);
        Message_tab = findViewById(R.id.messages_tab);
        PatientPortal = findViewById(R.id.Patients);
        Partner_button = findViewById(R.id.partnersPage);
        Contact_Us = findViewById(R.id.contactUsPage);
        Our_story = findViewById(R.id.ourStoryPage);
        signOut = findViewById(R.id.logoutButton);
        Inbox = findViewById(R.id.inbox);
        Announcements = findViewById(R.id.announcementsPage);
        profile = (ImageView) findViewById(R.id.profileIcon);
        profileMenu = findViewById(R.id.nav_view);
        Partner_button.setOnClickListener(this);
        Contact_Us.setOnClickListener(this);
        Our_story.setOnClickListener(this);
        signOut.setOnClickListener(this);
        Announcements.setOnClickListener(this);
        PatientPortal.setOnClickListener(this);
        Message_tab.setOnClickListener(this);
        profile.setOnClickListener(this);
        Inbox.setOnClickListener(this);

        mAuth = FirebaseAuth.getInstance();
        FirebaseUser fbUser = mAuth.getCurrentUser();

        // if user is not null and their email is not verified
        if (fbUser != null && !fbUser.isEmailVerified()) {
            startActivity(new Intent(MainApplication.this, MainActivity.class)); // send them to the login activity
        }

        if (fbUser != null) {
            FirebaseDatabase.getInstance().getReference(User.class.getSimpleName()).child(fbUser.getUid()).addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    UserManager.getInstance().setCurrentUser(FirebaseClient.convertToUser(snapshot));

                    if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.N) {
                        int read_count = (int) UserManager.getInstance().getCurrentUser().getMessages().stream().filter(message -> !message.isRead()).count();
                        Message_tab.setText(String.valueOf(read_count)); }
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {
                }
            });
        }

    }
    @Override
    public void onBackPressed() {
// super.onBackPressed();
// Not calling **super**, disables back button in current screen.
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
        else if (view.getId() == R.id.messages_tab) {
            Intent Messages_page = new Intent(this, PatientMessages.class);
            startActivity(Messages_page);
        }
        else if (view.getId() == R.id.profileIcon) {
            Intent profilePage = new Intent(this, ProfilePage.class);
            startActivity(profilePage);
        }
        else if (view.getId() == R.id.inbox) {
            Intent profilePage = new Intent(this, PatientMessages.class);
            startActivity(profilePage);
        }
    }
}