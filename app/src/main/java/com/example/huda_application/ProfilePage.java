package com.example.huda_application;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import com.example.huda_application.firebase.FirebaseClient;
import com.example.huda_application.user.User;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class ProfilePage extends AppCompatActivity {
    private TextView firstName;
    private TextView email;
    private TextView lastname;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_page);

        Intent intent = getIntent();
        User user = (User) intent.getSerializableExtra("user");

        this.firstName = findViewById(R.id.et_first_name);

        firstName.setText(String.format("%s", user.getFirstName()));

        FirebaseDatabase.getInstance().getReference("User").child(user.getUserId()).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                // User updatedUser = FirebaseClient.convertToUser(snapshot);
                User user = snapshot.getValue(User.class);
                String userFirstName = user.getFirstName();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
            }
        });

        this.lastname = findViewById(R.id.last_name);
        this.email = findViewById(R.id.email);
    }

        public TextView getFirstName() {
            return firstName;
        }

        public TextView getEmail() {
            return email;
        }

        public TextView getLastname() {
            return lastname;
        }
}
