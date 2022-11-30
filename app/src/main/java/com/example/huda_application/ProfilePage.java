package com.example.huda_application;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.huda_application.user.User;
import com.example.huda_application.user.UserManager;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class ProfilePage extends AppCompatActivity implements View.OnClickListener {
    private TextView header; // Initializing all variables that appear and are referenced in activity_profile_page XML
    private TextView firstName;
    private TextView email;
    private TextView DOB;
    private ImageView backbutton;
    private Button updatePass, signOut, deleteAcc;
    private FirebaseAuth mAuth;
    private FloatingActionButton updateEmail;

    @Override
    protected void onCreate(Bundle savedInstanceState) // Defining all variables to connect to activity_profile_page XML
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_page);

        header = findViewById(R.id.tv_heading); // Finding header View ID from associated XML
        firstName = findViewById(R.id.name); // Finding first name TextView ID from associated XML
        email = findViewById(R.id.et_email); // Finding email TextView ID from associated XML
        DOB = findViewById(R.id.et_date_of_birth); // Finding date of birth TextView ID from associated XML
        updateEmail = (FloatingActionButton) findViewById(R.id.floatingEmail); // Finding update email Button ID from associated XML

        deleteAcc = findViewById(R.id.deleteUserButton); // 
        signOut = findViewById(R.id.logoutButton);
        updatePass = findViewById(R.id.updatePassword);
        backbutton = findViewById(R.id.backButton_10);
        backbutton.setOnClickListener(this);
        signOut.setOnClickListener(this);
        deleteAcc.setOnClickListener(this);
        updateEmail.setOnClickListener(this);
        updatePass.setOnClickListener(this);

        User user = UserManager.getInstance().getCurrentUser();
        header.setText(String.format("%s", "Welcome, " + user.getFirstName() + "!"));
        firstName.setText(String.format("%s", user.getFirstName() + " " + user.getLastName()));
        // lastName.setText(String.format("%s", user.getLastName()));
        email.setText(String.format("%s", user.getEmailAddress()));
        DOB.setText(String.format("%s", user.getBirthday()));

        mAuth = FirebaseAuth.getInstance();
        FirebaseUser fbUser = mAuth.getCurrentUser();
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.backButton_10) {
            startActivity(new Intent(this, MainApplication.class));
        } else if (v.getId() == R.id.logoutButton) {
            UserManager.getInstance().removeCurrentUser();
            mAuth.signOut();
            startActivity(new Intent(this, MainActivity.class));
        } else if (v.getId() == R.id.updatePassword) {
            startActivity(new Intent(this, updatePassword.class));
        } else if (v.getId() == R.id.floatingEmail) {
            startActivity(new Intent(this, updateEmail.class));
        } else if (v.getId() == R.id.deleteUserButton) {
            startActivity(new Intent(this, deleteUserAccount.class));
        }
    }
}
