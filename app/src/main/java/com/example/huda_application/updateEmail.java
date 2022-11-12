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
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.EmailAuthProvider;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Locale;
import java.util.Objects;
import java.util.regex.Pattern;

public class updateEmail extends AppCompatActivity implements View.OnClickListener
{

    FirebaseAuth firebaseAuth;
    FirebaseUser user;
    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_email);

        Button updatePassBtn = findViewById(R.id.registerButton);
        updatePassBtn.setOnClickListener(this);

    }

    @Override
    public void onClick(View view)
    {
        if (view.getId() == R.id.registerButton)
        {

            EditText email = findViewById(R.id.email); // Setting variable for the EditText views
            EditText currentPass = findViewById(R.id.password);
            EditText newEmail = findViewById(R.id.email2);
            EditText newEmailConfirm = findViewById(R.id.confirmEmail);

            String emailTxt = email.getText().toString().trim(); // Converted the EditText views to a String variable
            String currentPassTxt = currentPass.getText().toString().trim();
            String newEmailTxt = newEmail.getText().toString().trim();
            String conNewEmailTxt = newEmailConfirm.getText().toString().trim();


            // if statement that checks if the email text box is empty
            if(TextUtils.isEmpty(emailTxt))
            {
                Toast.makeText(updateEmail.this, "Email cannot be empty", Toast.LENGTH_LONG).show();
                email.setError("Email is required");
                email.requestFocus();
            }
            else if(!Patterns.EMAIL_ADDRESS.matcher(emailTxt).matches()) // else if statement that checks if the Email input is the correct format
            {
                Toast.makeText(updateEmail.this, "Must be a valid email", Toast.LENGTH_LONG).show();
                email.setError("Email is required");
                email.requestFocus();
            }
            else if(TextUtils.isEmpty(currentPassTxt))   // else if statement that checks if the current password text box is empty
            {
                Toast.makeText(updateEmail.this, "Current password cannot be empty", Toast.LENGTH_LONG).show();
                currentPass.setError("Password is required");
                currentPass.requestFocus();
            }
            else if(TextUtils.isEmpty(newEmailTxt))   // else if statement that checks if the new email text box is empty
            {
                Toast.makeText(updateEmail.this, "New email cannot be empty", Toast.LENGTH_LONG).show();
                newEmail.setError("Email is required");
                newEmail.requestFocus();
            }
            else if(!Patterns.EMAIL_ADDRESS.matcher(newEmailTxt).matches()) // Else if statement that checks if the new Password matches
            // the pattern necessary for the password minimum security
            {
                Toast.makeText(updateEmail.this, "New email must be example@domain.com", Toast.LENGTH_LONG).show();
                newEmail.setError("Email format is required");
                newEmail.requestFocus();
            }
            else if(emailTxt.equals(newEmailTxt))   // else if statement that checks to see if the current
            // and new pass word, and they cannot match
            {
                Toast.makeText(updateEmail.this, "New email cannot be the same as previous", Toast.LENGTH_LONG).show();
                email.setError("New email is required");
                email.requestFocus();
            }
            else if(TextUtils.isEmpty(conNewEmailTxt)) // else if statement that checks if the confirm new password text
            // box is empty or not
            {
                Toast.makeText(updateEmail.this, "Confirmation Email cannot be empty", Toast.LENGTH_LONG).show();
                newEmailConfirm.setError("Confirmation Email is required");
                newEmailConfirm.requestFocus();
            }
            else if(!conNewEmailTxt.equals(newEmailTxt)) // Else if statement using that equals function to compare
            // if new password and new password confirmation are equal or not
            {
                Toast.makeText(updateEmail.this, "Email must match", Toast.LENGTH_LONG).show();
                newEmailConfirm.setError("Confirmation match is required");
                newEmailConfirm.requestFocus();
            }
            else // once every check is successful then the else block shall be executed
            {
                // Call method to update the password for the user
                updateEmail(emailTxt,currentPassTxt,newEmailTxt);
            }
        }
    }

    // update password method that takes 3 String parameters, email, old password, and new password
    private void updateEmail(String emailStr, String oldPassStr, String newEmailStr)
    {
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

        // Set AuthCredential variable to get emailAuthProvider
        AuthCredential authCredential = EmailAuthProvider.getCredential(emailStr ,oldPassStr);
        user.reauthenticate(authCredential).addOnSuccessListener(new OnSuccessListener<Void>()
        {
            @Override
            public void onSuccess(Void unused)
            {
                // user object calls the update password function that takes the new password String
                user.updateEmail(newEmailStr).addOnSuccessListener(new OnSuccessListener<Void>()
                {
                    @Override
                    public void onSuccess(Void unused)
                    {
                        Toast.makeText(updateEmail.this,"Email updated successfully",Toast.LENGTH_SHORT).show();

                        // Send email verification to user
                        user.sendEmailVerification().addOnSuccessListener(new OnSuccessListener<Void>()
                        {
                            @Override
                            public void onSuccess(Void unused)
                            {
                                Toast.makeText(updateEmail.this,"Email verification sent, please verify",Toast.LENGTH_SHORT).show();
                                startActivity(new Intent(updateEmail.this , MainActivity.class));
                            }
                        }).addOnFailureListener(new OnFailureListener()
                        {
                            @Override
                            public void onFailure(@NonNull Exception e)
                            {
                                Toast.makeText(updateEmail.this,""+e.getMessage(),Toast.LENGTH_SHORT).show();
                            }
                        });
                        startActivity(new Intent(updateEmail.this , MainActivity.class));
                    }
                }).addOnFailureListener(new OnFailureListener()
                {
                    @Override
                    public void onFailure(@NonNull Exception e)
                    {
                        // Failed to update password, display to user
                        Toast.makeText(updateEmail.this,""+e.getMessage(),Toast.LENGTH_SHORT).show();
                    }
                });
            }
            // onFailure listener to check for failed attempt and display reason to user
        }).addOnFailureListener(new OnFailureListener()
        {
            @Override
            public void onFailure(@NonNull Exception e)
            {
                // Authentication failed, display reason to user
                Toast.makeText(updateEmail.this,""+e.getMessage(),Toast.LENGTH_SHORT).show();
            }
        });
    }
}