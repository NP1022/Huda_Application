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
    private EditText Message_feild;
    private User user;
    private ImageView backbotton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_message_send_page);
        Intent temp = getIntent();

        user = (User) temp.getSerializableExtra("user");
        Message_feild = findViewById(R.id.Message);
        AppCompatButton sendButton = findViewById(R.id.send);
        backbotton = findViewById(R.id.backButton_message);

        sendButton.setOnClickListener(this);
        Message_feild.setOnClickListener(this);
        backbotton.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        if(view.getId() == R.id.send){


            String date = new SimpleDateFormat("MM-d-yyyy", Locale.getDefault()).format(new Date());
            String message = Message_feild.getText().toString().trim();
            Message m = new Message(date, message, false);
            user.addMessages(m);
            FirebaseClient.updateUser(user);
            startActivity( new Intent(this , Messagepage.class));

        }
        else if(view.getId() == R.id.backButton_message){
            startActivity( new Intent(this ,Messagepage.class ));

        }
    }
}