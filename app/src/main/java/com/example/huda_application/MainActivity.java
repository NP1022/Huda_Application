package com.example.huda_application;

import android.content.Intent;
import android.os.Bundle;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.snackbar.Snackbar;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.view.View;

import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.example.huda_application.databinding.ActivityMainBinding;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private AppBarConfiguration appBarConfiguration;
    private ActivityMainBinding binding;
    private TextView registration_button, forgot_pass, login;
    private EditText Password_Text , Email_Text;

    private FirebaseAuth mAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        FirebaseAuth mAuth = FirebaseAuth.getInstance();
        FirebaseUser mUser = mAuth.getCurrentUser();

        if(mUser != null && mUser.isEmailVerified())
        {
            startActivity(new Intent(MainActivity.this, MainApplication.class));
        }


        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(R.layout.activity_main);

        mAuth = FirebaseAuth.getInstance();


        int Registration_ID = R.id.registration;
        int forgot_password_ID = R.id.forgotpass;

        Password_Text = findViewById(R.id.password);
        Email_Text = findViewById(R.id.email);
        forgot_pass = (TextView) findViewById(forgot_password_ID);
        registration_button = (TextView) findViewById(Registration_ID);
        login = (Button) findViewById(R.id.login);

        login.setOnClickListener(this);
        registration_button.setOnClickListener(this);
        forgot_pass.setOnClickListener(this);

    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }
    @Override
    public void onClick(View view) {

        final String emailTxt = Email_Text.getText().toString().trim();
        final String passwordTxt = Password_Text.getText().toString().trim();
        if(view.getId() == R.id.registration){

            Intent Register_Activity = new Intent(this ,RegisterAccount.class);
            startActivity(Register_Activity);

        }
        if(view.getId() == R.id.forgotpass){

            Intent password_Activity = new Intent(this ,ForgotPassword.class);
            startActivity(password_Activity);

        }

        if (view.getId() == R.id.login){

            login(emailTxt, passwordTxt);

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
            Password_Text.setError("Password is less than 6 Character! ");
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
                            startActivity(new Intent(MainActivity.this , MainApplication.class));
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

    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onSupportNavigateUp()
    {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_main);
        return NavigationUI.navigateUp(navController, appBarConfiguration)
                || super.onSupportNavigateUp();
    }




}