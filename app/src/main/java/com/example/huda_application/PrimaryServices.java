package com.example.huda_application;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.net.Uri;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import java.util.Locale;

public class PrimaryServices extends AppCompatActivity implements View.OnClickListener
{
    private ImageView backButton;
                                                  // Class that is used to show the Primary  health services



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Saved_language();
        setContentView(R.layout.activity_primary_services);
                                                                  // Back button to take the patient back to the main page of the application
                                                                  // Back button for the application
        backButton = (ImageView) findViewById(R.id.backButton);
        backButton.setOnClickListener(this);
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
    public void onClick(View view)
    {
        if (view.getId() == R.id.backButton)
        {                                                                   // on click for the back button on the application
            Intent prev = new Intent(this, NewLogin.class);
            startActivity(prev);
        }
    }
}