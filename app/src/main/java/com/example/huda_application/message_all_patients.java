package com.example.huda_application;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.huda_application.firebase.FirebaseClient;
import com.example.huda_application.user.Message;
import com.example.huda_application.user.User;
import com.example.huda_application.user.UserType;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class message_all_patients extends AppCompatActivity implements View.OnClickListener {
    private EditText Message_feild;                                                             // Class used to send the Message using the user that is selected from the message page
                                                                                                // The object of the user is brought through a serializable object that is passed through
                                                                                                // once the message is added to the object it is pushed to the database
    private User user;
    private ImageView backbotton;
    private List<User> users;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_message_all_patients);
        FirebaseDatabase.getInstance().getReference("User").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                users = new ArrayList<>();
                for (DataSnapshot child : snapshot.getChildren()) {              // Variables used to get the information that is brought for the message


                    User user = FirebaseClient.convertToUser(child);
                    if (user.getUserType() != UserType.ADMIN)
                        users.add(user);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
            }
        });
        Message_feild = findViewById(R.id.Message_all);
        AppCompatButton sendButton = findViewById(R.id.send_all); // On click listener for the  message field the send button
        backbotton = findViewById(R.id.backButton_message_all);

        sendButton.setOnClickListener(this);

        Message_feild.setOnClickListener(this);
        backbotton.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        if(v.getId() == R.id.send_all){
            // The send button will take the current time of the message
            // Minimum character are 15 characters required

            String date = new SimpleDateFormat("MM-d-yyyy", Locale.getDefault()).format(new Date());
            String message = Message_feild.getText().toString().trim();
            Message m = new Message(date, message, false);
            if(message.length() < 15){

                Toast.makeText(message_all_patients.this, "Message has to be more than 15 characters.", Toast.LENGTH_LONG).show();
            }
            else {
                for (int i = 0; i < users.size(); i++) {
                    users.get(i).addMessages(m);
                    FirebaseClient.updateUser(users.get(i));                // Added the message to all the users in the application to be stored in the database of the application
                }

                startActivity(new Intent(this, Messagepage.class));
                Toast.makeText(message_all_patients.this, "Message successfully sent.", Toast.LENGTH_LONG).show();
            }
        }
        else if(v.getId() == R.id.backButton_message_all){
            startActivity( new Intent(this ,Messagepage.class ));

        }
    }
}