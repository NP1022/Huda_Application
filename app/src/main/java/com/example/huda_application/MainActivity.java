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

//        if(mUser != null && mUser.isEmailVerified())
//        {
//            startActivity(new Intent(MainActivity.this, MainApplication.class));
//        }

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(R.layout.activity_main);

        mAuth = FirebaseAuth.getInstance();



        int Registration_ID = R.id.registration;
        int forgot_password_ID = R.id.forgotpass;
        final String[] langs_options = {"English", "عربي" , "español", "français", "اردو" , "বাংলা"};

        backButton2 = findViewById(R.id.backButton2);

        Password_Text = findViewById(R.id.password);
        Email_Text = findViewById(R.id.email);
        forgot_pass = (TextView) findViewById(forgot_password_ID);
        registration_button = (TextView) findViewById(Registration_ID);
        login = (Button) findViewById(R.id.login);

        AlertDialog.Builder options = new AlertDialog.Builder(MainActivity.this);
        options.setTitle("Languages");


        login.setOnClickListener(this);
        registration_button.setOnClickListener(this);
        forgot_pass.setOnClickListener(this);
        backButton2.setOnClickListener(this);

    }


    @Override
    public void onBackPressed()
    {
        // super.onBackPressed();
        // Not calling **super**, disables back button in current screen.
    }

    private void picklanguage(String l) {
        SharedPreferences.Editor Saver = getSharedPreferences("langauge", MODE_MULTI_PROCESS).edit();
        Locale language_option =  new Locale(l);
        DisplayMetrics metrics =  getBaseContext().getResources().getDisplayMetrics();
        language_swtich(l, metrics, language_option);


        Saver.putString("prev_language" ,l);
        Saver.apply();


    }

    public void create_menu(AlertDialog.Builder opts){

        menu = opts.create();
        menu.show();
    }

    public void Saved_language(){
        SharedPreferences saved_language =getSharedPreferences("langauge", MODE_MULTI_PROCESS);

        picklanguage(saved_language.getString("prev_language" , ""));
    }



    public void language_swtich(String l , DisplayMetrics m , Locale lang) {

        Locale.setDefault(lang);

        Configuration page = new Configuration();
        page.locale = lang;

        getBaseContext().getResources().updateConfiguration(page, m);

    }
//    @Override
//    public boolean onCreateOptionsMenu(Menu menu)
//    {
//        // Inflate the menu; this adds items to the action bar if it is present.
//        getMenuInflater().inflate(R.menu.menu_main, menu);
//        return true;
//    }

    @Override
    public void onClick(View view)
    {

        final String emailTxt = Email_Text.getText().toString().trim();
        final String passwordTxt = Password_Text.getText().toString().trim();
        if(view.getId() == R.id.registration)
        {

            Intent Register_Activity = new Intent(this ,RegisterAccount.class);
            startActivity(Register_Activity);

        }
        if(view.getId() == R.id.forgotpass)
        {

            Intent password_Activity = new Intent(this ,ForgotPassword.class);
            startActivity(password_Activity);

        }

        if (view.getId() == R.id.login)
        {
            login(emailTxt, passwordTxt);
        }

        else if(view.getId() == R.id.backButton2){
            Intent Newlogin = new Intent(this ,NewLogin.class);
            startActivity(Newlogin);
        }


    }

    private void login(String Email, String password)
    {
        if (Email.isEmpty())
        {
            Email_Text.setError("Email is Empty! ");
            Email_Text.requestFocus();
        }
        else if (password.isEmpty())
        {
            Password_Text.setError("Password is Empty! ");
            Password_Text.requestFocus();
        }
        else if (password.length() < 8)
        {
            Password_Text.setError("Password is less than 8 Character! ");
            Password_Text.requestFocus();
        }
        else
        {

            mAuth.signInWithEmailAndPassword(Email , password).addOnCompleteListener(new OnCompleteListener<AuthResult>()
            {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task)
                {
                    if(task.isSuccessful())
                    {
                        if(mAuth.getCurrentUser().isEmailVerified()) // email must be verified before sign in
                        {

                            FirebaseDatabase.getInstance().getReference(User.class.getSimpleName()).child(mAuth.getCurrentUser().getUid()).addListenerForSingleValueEvent(new ValueEventListener() {
                                @Override
                                public void onDataChange(@NonNull DataSnapshot snapshot) {
                                    UserManager.getInstance().setCurrentUser(FirebaseClient.convertToUser(snapshot));

                                    if (UserManager.getInstance().isAdmin()) {
                                        startActivity(new Intent(MainActivity.this , Adminpanel.class));
                                    }else {
                                        startActivity(new Intent(MainActivity.this , MainApplication.class));
                                    }
                                }

                                @Override
                                public void onCancelled(@NonNull DatabaseError error)
                                {

                                }
                            });


                        }
                        else
                        {
                            Toast.makeText(MainActivity.this, "Please verify your email address ", Toast.LENGTH_LONG).show();

                        }
                    }
                    else
                    {
                        Toast.makeText(MainActivity.this, "Failed To Login! ", Toast.LENGTH_LONG).show();
                    }
                }
            });
        }



    }


}