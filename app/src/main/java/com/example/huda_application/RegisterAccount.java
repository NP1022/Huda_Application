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
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.regex.Pattern;

public class RegisterAccount extends AppCompatActivity
{

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

        DAOUser dao = new DAOUser();


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

                User user = new User(firstNameTxt,lastNameTxt,emailTxt,passwordTxt);

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
                    dao.add(user).addOnSuccessListener(suc->
                    {
                        Toast.makeText(RegisterAccount.this,"Record is inserted",Toast.LENGTH_LONG).show();
                    }).addOnFailureListener(er->
                    {
                        Toast.makeText(RegisterAccount.this,""+er.getMessage(),Toast.LENGTH_LONG).show();
                    });

                    FirebaseAuth mAuth = FirebaseAuth.getInstance();
                    mAuth.createUserWithEmailAndPassword(emailTxt,passwordTxt).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if(task.isSuccessful())
                            {
//                                FirebaseUser user = mAuth.getCurrentUser();
//                                user.sendEmailVerification().addOnSuccessListener(new OnSuccessListener<Void>() // sending verification email to user
//                                {
//                                    @Override
//                                    public void onSuccess(Void unused)
//                                    {
//                                        Toast.makeText(RegisterAccount.this,"Verification email",Toast.LENGTH_LONG).show(); // toast meessage to user
//                                    }
//                                }).addOnFailureListener(new OnFailureListener()
//                                {
//                                    @Override
//                                    public void onFailure(@NonNull Exception e)
//                                    {
//                                        Log.d(TAG,"onFailure: Email not sent " + e.getMessage());
//                                    }
//                                });
//                                Toast.makeText(RegisterAccount.this,"Task is successful",Toast.LENGTH_LONG).show();
//                                startActivity(new Intent(RegisterAccount.this,MainActivity.class));
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
