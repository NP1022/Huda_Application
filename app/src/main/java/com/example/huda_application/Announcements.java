package com.example.huda_application;



import androidx.appcompat.app.AppCompatActivity;


import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.ImageView;

import android.webkit.WebView;
import android.webkit.WebViewClient;

import java.util.Locale;


public class Announcements extends AppCompatActivity implements View.OnClickListener
{

                                                                // Webview object used to show the calender from the embedded link for the google calendar
    private WebView webView;                                    // The class will show the calendar that is being used for the clinic
    private ImageView backbutton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Saved_language();
        setContentView(R.layout.activity_announcements);

        webView = findViewById(R.id.calendarWebView);            // Webview set equal to the webview that is shown in the application
        webView.setWebViewClient(new WebViewClient());
        webView.getSettings().setJavaScriptEnabled(true);


        String googleHTML = "https://embed.styledcalendar.com/#my4zA7wGz2FkThs3z3eJ";
        webView.loadUrl(googleHTML);                                // loading the URL for the webview for the google calendar of the application
        backbutton = findViewById(R.id.backButton_10);              // back button for going back to the main application page
        backbutton.setOnClickListener(this);
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
    @Override
    public void onClick(View v) {
        if(v.getId() == R.id.backButton_10)                         // onclick for the back button on the application
            startActivity( new Intent(this , MainApplication.class));
    }
}