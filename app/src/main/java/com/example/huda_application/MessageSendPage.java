package com.example.huda_application;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.huda_application.firebase.FirebaseClient;
import com.example.huda_application.user.Message;
import com.example.huda_application.user.User;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class MessageSendPage extends AppCompatActivity implements View.OnClickListener{
    private EditText Message_feild;                                 // Class used to send the Message using the user that is selected from the message page
    private User user;                                              // The object of the user is brought through a serializable object that is passed through
    private ImageView backbotton;                                   // once the message is added to the object it is pushed to the database
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_message_send_page);
        Intent temp = getIntent();

        user = (User) temp.getSerializableExtra("user");
        Message_feild = findViewById(R.id.Message);
        AppCompatButton sendButton = findViewById(R.id.send);               // Variables used to get the information that is brought for the message
        backbotton = findViewById(R.id.backButton_message);

        sendButton.setOnClickListener(this);                                // On click listener for the  message field the send button
        Message_feild.setOnClickListener(this);
        backbotton.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        if(view.getId() == R.id.send){                                                                      // The send button will take the current time of the message
                                                                                                            // Minimum character are 15 characters required
            String date = new SimpleDateFormat("MM-d-yyyy", Locale.getDefault()).format(new Date());
            String message = Message_feild.getText().toString().trim();
            if(message.length() < 15){

                Toast.makeText(MessageSendPage.this, "Message has to be more than 15 characters.", Toast.LENGTH_LONG).show();
            }
            else {
                Message m = new Message(date, message, false);
                user.addMessages(m);                                                                        // Created the message object that is added to the user to be pushed to the database
                FirebaseClient.updateUser(user);
                startActivity(new Intent(this, Messagepage.class));
                Toast.makeText(MessageSendPage.this, "Message successfully sent.", Toast.LENGTH_LONG).show();
            }
        }
        else if(view.getId() == R.id.backButton_message){
            startActivity( new Intent(this ,Messagepage.class ));

        }
    }
}