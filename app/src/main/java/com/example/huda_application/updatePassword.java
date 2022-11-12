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

public class updatePassword extends AppCompatActivity implements View.OnClickListener
{
    private static final Pattern PASSWORD_PATTERN =
            Pattern.compile("^" +
                    "(?=.*[0-9])" +         //at least 1 digit
                    "(?=.*[a-z])" +         //at least 1 lower case letter
                    "(?=.*[A-Z])" +         //at least 1 upper case letter
                    "(?=.*[!*@#$%^&+=])" +    //at least 1 special character
                    "(?=\\S+$)" +           //no white spaces
                    ".{8,}" +               //at least 8 characters
                    "$");

    FirebaseAuth firebaseAuth;
    FirebaseUser user;
    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_password);

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
            EditText newPassword = findViewById(R.id.password2);
            EditText conNewPassword = findViewById(R.id.confirmPass);

            String emailTxt = email.getText().toString().trim(); // Converted the EditText views to a String variable
            String currentPassTxt = currentPass.getText().toString().trim();
            String newPassTxt = newPassword.getText().toString().trim();
            String conNewPassTxt = conNewPassword.getText().toString().trim();


                // if statement that checks if the email text box is empty
                if(TextUtils.isEmpty(emailTxt))
                {
                    Toast.makeText(updatePassword.this, "Email cannot be empty", Toast.LENGTH_LONG).show();
                    email.setError("Email is required");
                    email.requestFocus();
                }
                else if(!Patterns.EMAIL_ADDRESS.matcher(emailTxt).matches()) // else if statement that checks if the Email input is the correct format
                {
                    Toast.makeText(updatePassword.this, "Must be a valid email", Toast.LENGTH_LONG).show();
                    email.setError("Email is required");
                    email.requestFocus();
                }
                else if(TextUtils.isEmpty(currentPassTxt))   // else if statement that checks if the current password text box is empty
                {
                    Toast.makeText(updatePassword.this, "Current password cannot be empty", Toast.LENGTH_LONG).show();
                    currentPass.setError("Password is required");
                    currentPass.requestFocus();
                }
                else if(TextUtils.isEmpty(newPassTxt))   // else if statement that checks if the new password text box is empty
                {
                    Toast.makeText(updatePassword.this, "Current password cannot be empty", Toast.LENGTH_LONG).show();
                    newPassword.setError("Password is required");
                    newPassword.requestFocus();
                }
                else if(!PASSWORD_PATTERN.matcher(newPassTxt).matches()) // Else if statement that checks if the new Password matches
                    // the pattern necessary for the password minimum security
                {
                    Toast.makeText(updatePassword.this, "New password must meet security requirement", Toast.LENGTH_LONG).show();
                    newPassword.setError("Password format is required");
                    newPassword.requestFocus();
                }
                else if(currentPassTxt.equals(newPassTxt))   // else if statement that checks to see if the current
                    // and new pass word, and they cannot match
                {
                    Toast.makeText(updatePassword.this, "New password cannot be the same as previous", Toast.LENGTH_LONG).show();
                    newPassword.setError("New password is required");
                    newPassword.requestFocus();
                }
                else if(TextUtils.isEmpty(conNewPassTxt)) // else if statement that checks if the confirm new password text
                    // box is empty or not
                {
                    Toast.makeText(updatePassword.this, "Confirmation password cannot be empty", Toast.LENGTH_LONG).show();
                    conNewPassword.setError("Confirmation Password is required");
                    conNewPassword.requestFocus();
                }
                else if(!conNewPassTxt.equals(newPassTxt)) // Else if statement using that equals function to compare
                    // if new password and new password confirmation are equal or not
                {
                    Toast.makeText(updatePassword.this, "Passwords must match", Toast.LENGTH_LONG).show();
                    conNewPassword.setError("Confirmation match is required");
                    conNewPassword.requestFocus();
                }
                else // once every check is successful then the else block shall be executed
                {
                    // Call method to update the password for the user
                   updatePass(emailTxt,currentPassTxt,newPassTxt);
                }
        }
    }

    // update password method that takes 3 String parameters, email, old password, and new password
    private void updatePass(String emailStr, String oldPassStr, String newPassStr)
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
                        user.updatePassword(newPassStr).addOnSuccessListener(new OnSuccessListener<Void>()
                        {
                            @Override
                            public void onSuccess(Void unused)
                            {
                                Toast.makeText(updatePassword.this,"Password updated successfully  ",Toast.LENGTH_SHORT).show();
                                startActivity(new Intent(updatePassword.this , MainActivity.class));
                            }
                        }).addOnFailureListener(new OnFailureListener()
                        {
                            @Override
                            public void onFailure(@NonNull Exception e)
                            {
                                // Failed to update password, display to user
                                Toast.makeText(updatePassword.this,""+e.getMessage(),Toast.LENGTH_SHORT).show();
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
                        Toast.makeText(updatePassword.this,""+e.getMessage(),Toast.LENGTH_SHORT).show();
                    }
                });
        }
}