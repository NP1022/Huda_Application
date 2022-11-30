package com.example.huda_application;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.media.Image;
import android.os.Bundle;

import com.example.huda_application.firebase.FirebaseClient;
import com.example.huda_application.user.User;
import com.example.huda_application.user.UserManager;
import com.example.huda_application.user.UserType;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.snackbar.Snackbar;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.view.View;

import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.example.huda_application.databinding.ActivityMainBinding;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Locale;

public class MainActivity extends AppCompatActivity implements View.OnClickListener
{

    private AppBarConfiguration appBarConfiguration, mAppBarConfiguration;
    private ActivityMainBinding binding;
    private TextView registration_button, forgot_pass, login;
    private EditText Password_Text , Email_Text;
    private Button language_Button;
    private ImageView backButton2;
    public AlertDialog menu;
    FirebaseAuth mAuth = FirebaseAuth.getInstance();
    FirebaseUser mUser = mAuth.getCurrentUser();



    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        Saved_language();

        binding = ActivityMainBinding.inflate(getLayoutInflater()); // setting the inflater view
        setContentView(R.layout.activity_main);

        mAuth = FirebaseAuth.getInstance(); // creating a variable that is set the instance of the Firebase Auth



        int Registration_ID = R.id.registration; // set variable for the ID that is in the XML
        int forgot_password_ID = R.id.forgotpass;  // set variable for the ID that is in the XML
        final String[] langs_options = {"English", "عربي" , "español", "français", "اردو" , "বাংলা"}; // String that holds the different language options

        backButton2 = findViewById(R.id.backButton2); // set variable for the back button

        Password_Text = findViewById(R.id.password); // set variable for the ID that is in the XM
        Email_Text = findViewById(R.id.email); // set variable for the ID that is in the XM
        forgot_pass = (TextView) findViewById(forgot_password_ID); // set variable for the ID that is in the XM
        registration_button = (TextView) findViewById(Registration_ID); // set variable for the ID that is in the XM
        login = (Button) findViewById(R.id.login);

        AlertDialog.Builder options = new AlertDialog.Builder(MainActivity.this);
        options.setTitle("Languages");

        login.setOnClickListener(this); // onClickListener for login
        registration_button.setOnClickListener(this); // onClickListener for registration
        forgot_pass.setOnClickListener(this); // onClickListener for forgot password
        backButton2.setOnClickListener(this); // onClickListener for back button

    }


    @Override
    public void onBackPressed()
    {
        // super.onBackPressed();
        // Not calling **super**, disables back button in current screen.
    }

    // Private method for the language picker that takes in a single String type
    private void picklanguage(String l)
    {
        SharedPreferences.Editor Saver = getSharedPreferences("langauge", MODE_MULTI_PROCESS).edit();
        Locale language_option =  new Locale(l);
        DisplayMetrics metrics =  getBaseContext().getResources().getDisplayMetrics();
        language_swtich(l, metrics, language_option);

        Saver.putString("prev_language" ,l);
        Saver.apply();

    }

    // Private method for the create menu function
    public void create_menu(AlertDialog.Builder opts)
    {
        menu = opts.create();
        menu.show();
    }

    // Private method that saves the language preference selected by the user
    public void Saved_language()
    {
        SharedPreferences saved_language =getSharedPreferences("langauge", MODE_MULTI_PROCESS);
        picklanguage(saved_language.getString("prev_language" , ""));
    }

    // Private method that switches the language, that takes one String type, DisplayMetrics type and a Locale type
    public void language_swtich(String l , DisplayMetrics m , Locale lang)
    {
        Locale.setDefault(lang);

        Configuration page = new Configuration();
        page.locale = lang;

        getBaseContext().getResources().updateConfiguration(page, m);
    }


    // Main onClick method that lets the user sign into the application
    @Override
    public void onClick(View view)
    {
        // Converting the EditText to a string type
        final String emailTxt = Email_Text.getText().toString().trim();
        final String passwordTxt = Password_Text.getText().toString().trim();

        // if the the registration button is selected, the user will be brought to the registration activity
        if(view.getId() == R.id.registration)
        {
            Intent Register_Activity = new Intent(this ,RegisterAccount.class);
            startActivity(Register_Activity);

        }
        // if the forgot password button is selected then the user will be brought to the forgot password activity
        if(view.getId() == R.id.forgotpass)
        {

            Intent password_Activity = new Intent(this ,ForgotPassword.class);
            startActivity(password_Activity);

        }
        // if the user selects the login button then it will call the login method that takes a two Strings,
        // the email and password
        if (view.getId() == R.id.login)
        {
            login(emailTxt, passwordTxt);
        }
        // else if the user selects the back button then they will be taken to the NewLogin activity
        else if(view.getId() == R.id.backButton2)
        {
            Intent Newlogin = new Intent(this ,NewLogin.class);
            startActivity(Newlogin);
        }

    }

    // Private login method that takes in two parameters, the email and the password
    private void login(String Email, String password)
    {
        // If statement that uses TextUtils.isEmpty() to check if the string is empty,
        // if it is empty, an error will be displayed.
        if (TextUtils.isEmpty(Email))
        {
            Toast.makeText(MainActivity.this,"Email cannot be empty",Toast.LENGTH_LONG).show();
            Email_Text.setError("Email is required");
            Email_Text.requestFocus();
        }
        // Else if statement that uses TextUtils.isEmpty() to check if the string is empty,
        // if it is empty, an error will be displayed.
        else if (TextUtils.isEmpty(password))
        {
            Toast.makeText(MainActivity.this,"Password cannot be empty",Toast.LENGTH_LONG).show();
            Password_Text.setError("Password is required");
            Password_Text.requestFocus();
        }
        // Else if statement that uses the .length() to check if the length of the string is less than 8,
        // if it is, then an error message will be displayed
        else if (password.length() < 8)
        {
            Toast.makeText(MainActivity.this,"Password cannot be less than 8 characters long",Toast.LENGTH_LONG).show();
            Password_Text.setError("Password format is required");
            Password_Text.requestFocus();
        }
        // Else block is executed if all the input requirements are meant
        else
        {
            // sign in using member access operator that takes in two strings, the email and the password
            // which is used with the addOnCompleteListener
            mAuth.signInWithEmailAndPassword(Email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>()
            {
                // On complete method that checks the task
                @Override
                public void onComplete(@NonNull Task<AuthResult> task)
                {
                    // if the task is successful
                    if(task.isSuccessful())
                    {
                        // checks if the current user logged is verified
                        if(mAuth.getCurrentUser().isEmailVerified()) // email must be verified before sign in
                        {
                            // if they are verified
                            FirebaseDatabase.getInstance().getReference(User.class.getSimpleName()).child(mAuth.getCurrentUser().getUid()).addListenerForSingleValueEvent(new ValueEventListener()
                            {
                                @Override
                                public void onDataChange(@NonNull DataSnapshot snapshot)
                                {
                                    UserManager.getInstance().setCurrentUser(FirebaseClient.convertToUser(snapshot));

                                    // if the user is an admin then they will be taken to the admin panel
                                    if (UserManager.getInstance().isAdmin())
                                    {
                                        startActivity(new Intent(MainActivity.this , Adminpanel.class));
                                    }
                                    // else if they are a patient then they will be taken to the main application
                                    else
                                    {
                                        startActivity(new Intent(MainActivity.this , MainApplication.class));
                                    }
                                }

                                @Override
                                public void onCancelled(@NonNull DatabaseError error)
                                {

                                }
                            });

                        }
                        // if the user is not verified then a message will be displayed asking the user to verify the email
                        else
                        {
                            Toast.makeText(MainActivity.this, "Please verify your email address ", Toast.LENGTH_LONG).show();
                        }
                    }
                    // if the login information is incorrect then a failed login message will be displayed
                    else
                    {
                        Toast.makeText(MainActivity.this, "Failed To Login! ", Toast.LENGTH_LONG).show();
                    }
                }
            });
        }

    }

    @Override
    public void onConfigurationChanged(Configuration newConfig)
    {
        // TODO Auto-generated method stub
        super.onConfigurationChanged(newConfig);
    }

}