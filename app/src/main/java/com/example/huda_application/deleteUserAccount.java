package com.example.huda_application;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.EmailAuthProvider;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.regex.Pattern;

public class deleteUserAccount extends AppCompatActivity implements View.OnClickListener
{

    FirebaseAuth firebaseAuth;
    FirebaseUser user;
    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;
    private ImageView backbutton;

    private static Pattern CONSENT_PATTERN = Pattern.compile("^(?:yes|Yes|No|no)$");

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delete_user_account);

        Button deleteUserBtn = findViewById(R.id.deleteUserButton); // set variable button for ID from the XML
        deleteUserBtn.setOnClickListener(this); // set onClick for the button

        backbutton = findViewById(R.id.backButton_10); // set variable button for ID from the XML
        backbutton.setOnClickListener(this); // set onClick for the button
    }

    // public method for the onClick
    @Override
    public void onClick(View view)
    {
        // if the user clicks the delete button
        if (view.getId() == R.id.deleteUserButton)
        {
            EditText email = findViewById(R.id.email); // Setting variable for the EditText views
            EditText currentPass = findViewById(R.id.password);
            EditText yesOrNoDelete = findViewById(R.id.yesOrNo);
            EditText conYesOrNoDelete = findViewById(R.id.confirmYesOrNo);

            String emailTxt = email.getText().toString().trim(); // Converted the EditText views to a String variable
            String currentPassTxt = currentPass.getText().toString().trim();
            String yesOrNoText = yesOrNoDelete.getText().toString().trim();
            String conYesOrNoTxt = conYesOrNoDelete.getText().toString().trim();

            // if statement that uses the TextUtils.isEmpty() to check if a string is empty
            if(TextUtils.isEmpty(emailTxt))
            {
                Toast.makeText(deleteUserAccount.this, "Email cannot be empty", Toast.LENGTH_LONG).show();
                email.setError("Email is required");
                email.requestFocus();
            }
            // else if statement that checks if the Email input is the correct format
            else if(!Patterns.EMAIL_ADDRESS.matcher(emailTxt).matches())
            {
                Toast.makeText(deleteUserAccount.this, "Must be a valid email", Toast.LENGTH_LONG).show();
                email.setError("Email is required");
                email.requestFocus();
            }
            // else if statement that checks if the current password text box is empty
            else if(TextUtils.isEmpty(currentPassTxt))
            {
                Toast.makeText(deleteUserAccount.this, "Current password cannot be empty", Toast.LENGTH_LONG).show();
                currentPass.setError("Password is required");
                currentPass.requestFocus();
            }
            // else if statement that checks if the Yes or No text box is empty
            else if(TextUtils.isEmpty(yesOrNoText))
            {
                Toast.makeText(deleteUserAccount.this, "Field cannot be empty", Toast.LENGTH_LONG).show();
                yesOrNoDelete.setError("Response is required");
                yesOrNoDelete.requestFocus();
            }
            // Else if statement that checks if the response matches
            // the pattern necessary for the function, Yes or NO or no or yes
            else if(!CONSENT_PATTERN.matcher(yesOrNoText).matches())
            {
                Toast.makeText(deleteUserAccount.this, "Must be a yes or no", Toast.LENGTH_LONG).show();
                yesOrNoDelete.setError("Response is required");
                yesOrNoDelete.requestFocus();
            }
            // else if statement that checks if the Yes or No text box is empty
            else if(TextUtils.isEmpty(conYesOrNoTxt))
            {
                Toast.makeText(deleteUserAccount.this, "Field cannot be empty", Toast.LENGTH_LONG).show();
                conYesOrNoDelete.setError("Response is required");
                conYesOrNoDelete.requestFocus();
            }
            // else if statement to check if the response match, if they do not then an error
            // will be displayed
            else if(!conYesOrNoTxt.equals(yesOrNoText))
            {
                Toast.makeText(deleteUserAccount.this, "Response must match", Toast.LENGTH_LONG).show();
                conYesOrNoDelete.setError("Response match is required");
                conYesOrNoDelete.requestFocus();
            }
            else // once every check is successful then the else block shall be executed
            {
                // Call method to delete the user
                // takes in String values: email, password and yes or no
                if(yesOrNoText.equals("Yes") || yesOrNoText.equals("yes"))
                {
                    deleteUser(emailTxt,currentPassTxt);
                }
            }

        }
        else if (view.getId() == R.id.backButton_10)
        {
            startActivity(new Intent(this, ProfilePage.class));
        }
    }

    // update password method that takes 2 String parameters, email, old password
    private void deleteUser(String emailStr, String oldPassStr)
    {
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser(); // get current Firebase user

        AuthCredential authCredential = EmailAuthProvider.getCredential(emailStr ,oldPassStr); // re authentication
        // using AuthCredential, passing email and password into the EmailAuthProvider to verify user

        user.reauthenticate(authCredential).addOnSuccessListener(new OnSuccessListener<Void>()
            // on Successful authentication
        {
            @Override
            public void onSuccess(Void unused)
            {
                // successful authentication
                user.delete().addOnSuccessListener(new OnSuccessListener<Void>()
                {
                    @Override
                    public void onSuccess(Void unused)
                    {
                        // Toast message to let the user know the account was deleted
                        // Take me the user back to register page to create a new account
                        Toast.makeText(deleteUserAccount.this,"Account deleted successfully",Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(deleteUserAccount.this , RegisterAccount.class));
                    }
                }).addOnFailureListener(new OnFailureListener()
                {
                    @Override
                    public void onFailure(@NonNull Exception e)
                    {
                        // Failed to delete, display reason
                        Toast.makeText(deleteUserAccount.this,""+e.getMessage(),Toast.LENGTH_SHORT).show();
                    }
                });

            }
        }).addOnFailureListener(new OnFailureListener()
        {
            @Override
            public void onFailure(@NonNull Exception e)
            {
                // failed authentication message will be displayed to the user
                Toast.makeText(deleteUserAccount.this,""+e.getMessage(),Toast.LENGTH_SHORT).show();
            }
        });

    }
}