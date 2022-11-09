package com.example.huda_application;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import com.example.huda_application.firebase.FirebaseClient;
import com.example.huda_application.user.User;
import com.example.huda_application.user.UserManager;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class ProfilePage extends AppCompatActivity {
    private TextView firstName;
    private TextView email;
    private TextView lastName;
    private TextView DOB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_page);

        this.firstName = findViewById(R.id.et_first_name);
        this.lastName = findViewById(R.id.et_last_name);
        this.email = findViewById(R.id.et_email);
        this.DOB = findViewById(R.id.et_date_of_birth);

        User user = UserManager.getInstance().getCurrentUser();
        firstName.setText(String.format("%s", user.getFirstName()));
        lastName.setText(String.format("%s", user.getLastName()));
        email.setText(String.format("%s", user.getEmailAddress()));
        DOB.setText(String.format("%s", user.getBirthday()));

    }


}
