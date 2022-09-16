package com.example.huda_application;

import static android.content.ContentValues.TAG;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.regex.Pattern;

public class RegisterAccount extends AppCompatActivity
{
    // create database object
    DatabaseReference dbRef = FirebaseDatabase.getInstance().getReferenceFromUrl("https://huda-app-44bdb-default-rtdb.firebaseio.com/");

    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        mAuth = FirebaseAuth.getInstance();

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_account);

        final EditText firstName = findViewById(R.id.firstName);
        final EditText lastName = findViewById(R.id.lastName);
        final EditText email = findViewById(R.id.email);
        final EditText password = findViewById(R.id.password);
        final EditText conPassword = findViewById(R.id.confirmPass);


        Button buttonRegister = findViewById(R.id.registerButton); // set variable for button action

        buttonRegister.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view) // function for register button
            {

                final String firstNameTxt = firstName.getText().toString().trim(); // convert the EditText to a String type
                final String lastNameTxt = lastName.getText().toString().trim();
                final String emailTxt = email.getText().toString().trim();
                final String passwordTxt = password.getText().toString().trim();
                final String conPasswordTxt = conPassword.getText().toString().trim();

                if (TextUtils.isEmpty(firstNameTxt) || TextUtils.isEmpty(lastNameTxt)) // check if the firstname and lastname are empty
                {
                    Toast.makeText(RegisterAccount.this,"Please enter first and last name",Toast.LENGTH_LONG).show();
                    firstName.setError("First Name is required");
                    lastName.setError("Last Name is required");
                    firstName.requestFocus();
                    lastName.requestFocus();
                }
                else if(TextUtils.isEmpty(emailTxt)) // check if email is empty
                {
                    Toast.makeText(RegisterAccount.this,"Please enter Email",Toast.LENGTH_LONG).show();
                    email.setError("Email is required");
                    email.requestFocus();
                }
                else if(!Patterns.EMAIL_ADDRESS.matcher(emailTxt).matches())
                {
                    Toast.makeText(RegisterAccount.this,"Please re-enter Email",Toast.LENGTH_LONG).show();
                    email.setError("Valid email is required");
                    email.requestFocus();
                }
                else if(TextUtils.isEmpty(passwordTxt))
                {
                    Toast.makeText(RegisterAccount.this,"Please password",Toast.LENGTH_LONG).show();
                    password.setError("Password is required");
                    password.requestFocus();
                }
                else if(passwordTxt.length() < 8)
                {
                    Toast.makeText(RegisterAccount.this,"Password should be at least 8 characters",Toast.LENGTH_LONG).show();
                    password.setError("Password too weak");
                    password.requestFocus();
                }
                else if(TextUtils.isEmpty(conPasswordTxt))
                {
                    Toast.makeText(RegisterAccount.this,"Please confirm password",Toast.LENGTH_LONG).show();
                    conPassword.setError("Password confirmation is required");
                    conPassword.requestFocus();
                }
                else if(!passwordTxt.equals(conPasswordTxt))
                {
                    Toast.makeText(RegisterAccount.this,"Please make passwords match",Toast.LENGTH_LONG).show();
                    conPassword.setError("Password match is required");
                    conPassword.requestFocus();
                    password.clearComposingText();
                    conPassword.clearComposingText();
                }
                else
                {
                    FirebaseAuth mAuth = FirebaseAuth.getInstance();
                    mAuth.createUserWithEmailAndPassword(emailTxt,passwordTxt).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if(task.isSuccessful())
                            {
                                Toast.makeText(RegisterAccount.this,"Task is successful",Toast.LENGTH_LONG).show();
                                startActivity(new Intent(RegisterAccount.this,MainActivity.class));
                            }
                            else
                            {
                                Toast.makeText(RegisterAccount.this,"Registration failed: " + task.getException().getMessage(),Toast.LENGTH_LONG).show();
                            }
                        }
                    });

                }
            }

        });
    }


}
