package com.example.huda_application;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.content.res.Configuration;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.huda_application.user.User;
import com.example.huda_application.user.UserManager;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.Locale;

public class ProfilePage extends AppCompatActivity implements View.OnClickListener {
    private TextView header; // Initializing all variables that appear and are referenced in activity_profile_page XML
    private TextView firstName;
    private TextView email;
    private TextView DOB;
    private ImageView backbutton;
    private Button updatePass, signOut, deleteAcc;
    private FirebaseAuth mAuth;
    private FloatingActionButton updateEmail;

    private Button language_Button;
    public AlertDialog menu;

    @Override
    protected void onCreate(Bundle savedInstanceState) // Defining all variables to connect to activity_profile_page XML
    {
        super.onCreate(savedInstanceState);
        Saved_language();
        setContentView(R.layout.activity_profile_page);

        String welcome = getString(R.string.welcomeUser);
        header = findViewById(R.id.tv_heading); // Finding header View ID from associated XML
        firstName = findViewById(R.id.name); // Finding first name TextView ID from associated XML
        email = findViewById(R.id.et_email); // Finding email TextView ID from associated XML
        DOB = findViewById(R.id.et_date_of_birth); // Finding date of birth TextView ID from associated XML
        updateEmail = (FloatingActionButton) findViewById(R.id.floatingEmail); // Finding update email Button ID from associated XML

        deleteAcc = findViewById(R.id.deleteUserButton); // 
        signOut = findViewById(R.id.logoutButton);
        updatePass = findViewById(R.id.updatePassword);
        backbutton = findViewById(R.id.backButton_10);
        backbutton.setOnClickListener(this);
        signOut.setOnClickListener(this);
        deleteAcc.setOnClickListener(this);
        updateEmail.setOnClickListener(this);
        updatePass.setOnClickListener(this);


        User user = UserManager.getInstance().getCurrentUser(); // Fetch data of current user object
        header.setText(String.format("%s", welcome + " " + user.getFirstName() + "!")); // Set header text to welcome the user, fetching user's first name from database
        firstName.setText(String.format("%s", user.getFirstName() + " " + user.getLastName())); // Sets name string to display user's first and last name
        email.setText(String.format("%s", user.getEmailAddress()));
        DOB.setText(String.format("%s", user.getBirthday()));

        mAuth = FirebaseAuth.getInstance();
        FirebaseUser fbUser = mAuth.getCurrentUser();

        final String[] langs_options = {"English", "عربي" , "español", "français", "اردو" , "বাংলা"};

        AlertDialog.Builder options = new AlertDialog.Builder(ProfilePage.this);
        options.setTitle("Languages");
        language_Button = findViewById(R.id.language3);
        language_Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {                                                    // Used to change the language in the application

                options.setSingleChoiceItems(langs_options, -1, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface Diag_options, int opts ) {

                        switch(opts) {

                            case (0):
                                picklanguage("en");
                                recreate();
                                Toast.makeText(ProfilePage.this,"Current Language: English",Toast.LENGTH_SHORT).show();
                                break;

                            case(1):

                                picklanguage("ar");
                                recreate();
                                Toast.makeText(ProfilePage.this,"اللغة الحالية: العربية",Toast.LENGTH_SHORT).show();
                                break;
                            case(2):

                                picklanguage("es");
                                recreate();
                                Toast.makeText(ProfilePage.this,"Idioma actual: español",Toast.LENGTH_SHORT).show();
                                break;
                            case(3):

                                picklanguage("fr");
                                recreate();
                                Toast.makeText(ProfilePage.this,"Langue actuelle: français",Toast.LENGTH_SHORT).show();
                                break;

                            case(4):

                                picklanguage("ur");
                                recreate();
                                Toast.makeText(ProfilePage.this,"موجودہ زبان: اردو",Toast.LENGTH_SHORT).show();
                                break;
                            case(5):

                                picklanguage("bn");
                                recreate();
                                Toast.makeText(ProfilePage.this,"বর্তমান ভাষা: বাংলা",Toast.LENGTH_SHORT).show();
                                break;
                        }
                        Diag_options.dismiss();
                    }
                });
                create_menu(options);
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

    public void create_menu(AlertDialog.Builder opts){

        menu = opts.create();
        menu.show();
    }

    public void Saved_language(){
        SharedPreferences saved_language =getSharedPreferences("langauge", MODE_MULTI_PROCESS);
        picklanguage(saved_language.getString("prev_language" , ""));           // choose the saved language from the application
    }



    public void language_swtich(String l , DisplayMetrics m , Locale lang) {

        Locale.setDefault(lang);

        Configuration page = new Configuration();                                                   // Switch the language
        page.locale = lang;

        getBaseContext().getResources().updateConfiguration(page, m);

    }
    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.backButton_10) {
            startActivity(new Intent(this, MainApplication.class));
        } else if (v.getId() == R.id.logoutButton) {
            UserManager.getInstance().removeCurrentUser();
            mAuth.signOut();
            startActivity(new Intent(this, MainActivity.class));
        } else if (v.getId() == R.id.updatePassword) {
            startActivity(new Intent(this, updatePassword.class));
        } else if (v.getId() == R.id.floatingEmail) {
            startActivity(new Intent(this, updateEmail.class));
        } else if (v.getId() == R.id.deleteUserButton) {
            startActivity(new Intent(this, deleteUserAccount.class));
        }
    }
}
