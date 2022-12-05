package com.example.huda_application;

import static android.content.ContentValues.TAG;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
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

    FirebaseAuth firebaseAuth;                                      // This class is used to updated the current email that is being used in the application
    FirebaseUser user;                                              // The input will be the previous email and the new email that is gana be used
    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;
    private ImageView backbutton;


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {

        super.onCreate(savedInstanceState);
        Saved_language();
        setContentView(R.layout.activity_update_email);

        Button updatePassBtn = findViewById(R.id.registerButton); // set variable button to the ID from the XML
        updatePassBtn.setOnClickListener(this); // set onClick for the button

        backbutton = findViewById(R.id.backButton_10); // set variable button to the ID from the XML
        backbutton.setOnClickListener(this);  // set onClick for the button

    }

    // onClick method
    @Override
    public void onClick(View view)
    {
        // if the button is clicked
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
            // else if statement that checks if the Email input is the correct format
            else if(!Patterns.EMAIL_ADDRESS.matcher(emailTxt).matches())
            {
                Toast.makeText(updateEmail.this, "Must be a valid email", Toast.LENGTH_LONG).show();
                email.setError("Email is required");
                email.requestFocus();
            }
            // else if statement that checks if the current password text box is empty
            else if(TextUtils.isEmpty(currentPassTxt))
            {
                Toast.makeText(updateEmail.this, "Current password cannot be empty", Toast.LENGTH_LONG).show();
                currentPass.setError("Password is required");
                currentPass.requestFocus();
            }
            // else if statement that checks if the new email text box is empty
            else if(TextUtils.isEmpty(newEmailTxt))
            {
                Toast.makeText(updateEmail.this, "New email cannot be empty", Toast.LENGTH_LONG).show();
                newEmail.setError("Email is required");
                newEmail.requestFocus();
            }
            // Else if statement that checks if the new Password matches
            // the pattern necessary for the password minimum security
            else if(!Patterns.EMAIL_ADDRESS.matcher(newEmailTxt).matches())
            {
                Toast.makeText(updateEmail.this, "New email must be example@domain.com", Toast.LENGTH_LONG).show();
                newEmail.setError("Email format is required");
                newEmail.requestFocus();
            }
            // else if statement that checks to see if the current
            // and new pass word, and they cannot match
            else if(emailTxt.equals(newEmailTxt))
            {
                Toast.makeText(updateEmail.this, "New email cannot be the same as previous", Toast.LENGTH_LONG).show();
                email.setError("New email is required");
                email.requestFocus();
            }
            // else if statement that checks if the confirm new password text
            // box is empty or not
            else if(TextUtils.isEmpty(conNewEmailTxt))
            {
                Toast.makeText(updateEmail.this, "Confirmation Email cannot be empty", Toast.LENGTH_LONG).show();
                newEmailConfirm.setError("Confirmation Email is required");
                newEmailConfirm.requestFocus();
            }
            // Else if statement using that equals function to compare
            // if new password and new password confirmation are equal or not
            else if(!conNewEmailTxt.equals(newEmailTxt))
            {
                Toast.makeText(updateEmail.this, "Email must match", Toast.LENGTH_LONG).show();
                newEmailConfirm.setError("Confirmation match is required");
                newEmailConfirm.requestFocus();
            }
            // once every check is successful then the else block shall be executed
            else
            {
                // Call method to update the password for the user
                updateEmail(emailTxt,currentPassTxt,newEmailTxt);
            }
        }
        // back button to the take the user back to the profile page
        else if (view.getId() == R.id.backButton_10)
        {
            startActivity(new Intent(this, ProfilePage.class));
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
                                // Display message that the verification email has been sent
                                // and the user is taken to the login page
                                Toast.makeText(updateEmail.this,"Email verification sent, please verify",Toast.LENGTH_SHORT).show();
                                startActivity(new Intent(updateEmail.this , MainActivity.class));
                            }
                        }).addOnFailureListener(new OnFailureListener()
                        {
                            @Override
                            public void onFailure(@NonNull Exception e)
                            {
                                // display error
                                Toast.makeText(updateEmail.this,""+e.getMessage(),Toast.LENGTH_SHORT).show();
                            }
                        });
                        // send the user to the login page
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
    private void picklanguage(String l)
    {
        SharedPreferences.Editor Saver = getSharedPreferences("langauge", MODE_MULTI_PROCESS).edit();
        Locale language_option =  new Locale(l);
        DisplayMetrics metrics =  getBaseContext().getResources().getDisplayMetrics();                  // Picks the locale after the language is picked from the dialog
        language_swtich(l, metrics, language_option);

        Saver.putString("prev_language" ,l);
        Saver.apply();
    }
    public void language_swtich(String l , DisplayMetrics m , Locale lang) {

        Locale.setDefault(lang);

        Configuration page = new Configuration();                                                   // Switch the language
        page.locale = lang;

        getBaseContext().getResources().updateConfiguration(page, m);

    }


    public void Saved_language(){
        SharedPreferences saved_language =getSharedPreferences("langauge", MODE_MULTI_PROCESS);
        picklanguage(saved_language.getString("prev_language" , ""));
        // choose the saved language from the application
    }
}