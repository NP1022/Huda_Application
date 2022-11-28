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
        firstName = findViewById(R.id.name);
        email = findViewById(R.id.et_email);
        DOB = findViewById(R.id.et_date_of_birth);
        updateEmail = (FloatingActionButton) findViewById(R.id.floatingEmail);
        deleteAcc = findViewById(R.id.deleteUserButton);
        signOut = findViewById(R.id.logoutButton);
        updatePass = findViewById(R.id.updatePassword);
        backbutton = findViewById(R.id.backButton_10);

        backbutton.setOnClickListener(this); // Wiring all variables to View.OnClickListener in order to click on
        signOut.setOnClickListener(this);
        deleteAcc.setOnClickListener(this);
        updateEmail.setOnClickListener(this);
        updatePass.setOnClickListener(this);

        User user = UserManager.getInstance().getCurrentUser(); // Fetch data of current user object
        header.setText(String.format("%s", "Welcome, " + user.getFirstName() + "!")); // Set header text to welcome the user, fetching user's first name from database
        firstName.setText(String.format("%s", user.getFirstName() + " " + user.getLastName())); // Sets name string to display user's first and last name
        // lastName.setText(String.format("%s", user.getLastName()));
        email.setText(String.format("%s", user.getEmailAddress())); // Sets email string to display user's email address
        DOB.setText(String.format("%s", user.getBirthday())); // Sets date of birth string to display user's date of birth

        mAuth = FirebaseAuth.getInstance();
        FirebaseUser fbUser = mAuth.getCurrentUser(); // Get current user authorized
    }

    @Override
    public void onClick(View v) // onClick function to respond to user input of click, redirecting user
    {
        if (v.getId() == R.id.backButton_10) // If user clicks on back button
        {
            startActivity(new Intent(this, MainApplication.class)); // User is redirected to previous page, set to return to Main Menu
        } else if (v.getId() == R.id.logoutButton) // If user clicks on logout button
        {
            UserManager.getInstance().removeCurrentUser(); // User's instance is removed
            mAuth.signOut(); // Firebase Authenticator signs the user out
            startActivity(new Intent(this, MainActivity.class)); // The user is redirected to the account login page, after signing out
        } else if (v.getId() == R.id.updatePassword) // If the user clicks on the update password button
        {
            startActivity(new Intent(this, updatePassword.class)); // The user is redirected to the update password page
        } else if (v.getId() == R.id.floatingEmail) // If the user clicks on the update email function
        {
            startActivity(new Intent(this, updateEmail.class)); // The user is redirected to the update email page
        } else if (v.getId() == R.id.deleteUserButton) // If the user clicks on the delete account button
        {
            startActivity(new Intent(this, deleteUserAccount.class)); // The user is redirected to the delete account page
        }
    }
}
