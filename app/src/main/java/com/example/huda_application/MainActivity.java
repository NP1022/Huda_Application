package com.example.huda_application;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.os.Bundle;

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

import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Locale;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private AppBarConfiguration appBarConfiguration;
    private ActivityMainBinding binding;
    private TextView registration_button, forgot_pass, login;
    private EditText Password_Text , Email_Text;
    private Button language_Button;
    public AlertDialog menu;
    FirebaseAuth mAuth = FirebaseAuth.getInstance();
    FirebaseUser mUser = mAuth.getCurrentUser();



    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        Saved_language();

        if(mUser != null && mUser.isEmailVerified())
        {
            startActivity(new Intent(MainActivity.this, MainApplication.class));
        }

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(R.layout.activity_main);

        mAuth = FirebaseAuth.getInstance();


        int Registration_ID = R.id.registration;
        int forgot_password_ID = R.id.forgotpass;
        final String[] langs_options = {"English", "عربي" , "español", "français", "اردو" , "বাংলা"};

        language_Button = findViewById(R.id.language);
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

        language_Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                options.setSingleChoiceItems(langs_options, -1, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface Diag_options, int opts ) {

                        switch(opts) {

                            case (0):
                                picklanguage("en");
                                recreate();
                                Toast.makeText(MainActivity.this,"Current Language: English",Toast.LENGTH_SHORT).show();
                                break;

                            case(1):

                                picklanguage("ar");
                                recreate();
                                Toast.makeText(MainActivity.this,"اللغة الحالية: العربية",Toast.LENGTH_SHORT).show();
                                break;
                            case(2):

                                picklanguage("es");
                                recreate();
                                Toast.makeText(MainActivity.this,"Idioma actual: español",Toast.LENGTH_SHORT).show();
                                break;
                            case(3):

                                picklanguage("fr");
                                recreate();
                                Toast.makeText(MainActivity.this,"Langue actuelle: français",Toast.LENGTH_SHORT).show();
                                break;

                            case(4):

                                picklanguage("ur");
                                recreate();
                                Toast.makeText(MainActivity.this,"موجودہ زبان: اردو",Toast.LENGTH_SHORT).show();
                                break;
                            case(5):

                                picklanguage("bn");
                                recreate();
                                Toast.makeText(MainActivity.this,"বর্তমান ভাষা: বাংলা",Toast.LENGTH_SHORT).show();
                                break;
                        }
                        Diag_options.dismiss();
                    }
                });
                create_menu(options);
            }
        });
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

    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    public void language_swtich(String l , DisplayMetrics m , Locale lang) {

        Locale.setDefault(lang);

        Configuration page = new Configuration();
        page.locale = lang;

        getBaseContext().getResources().updateConfiguration(page, m);

    }


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
                            startActivity(new Intent(MainActivity.this , ReturningOrNewUser.class));
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