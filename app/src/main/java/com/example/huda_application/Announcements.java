package com.example.huda_application;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;


public class Announcements extends AppCompatActivity implements View.OnClickListener {

    // web view var
    private WebView webView;
    private ImageView backButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_announcements);

        backButton = (ImageView) findViewById(R.id.backButton);
        backButton.setOnClickListener((View.OnClickListener) this);

        webView = findViewById(R.id.calendarWebView);
        webView.setWebViewClient(new WebViewClient());
        webView.getSettings().setJavaScriptEnabled(true);


        String googleHTML = "<iframe src=\"https://calendar.google.com/calendar/embed?src=c_tns32v5e01q5kjs1jj1rcuehao%40group.calendar.google.com&ctz=America%2FDetroit\" style=\"border: 0\" width=\"390\" height=\"350\" frameborder=\"0\" scrolling=\"no\"></iframe>";
        webView.loadData(googleHTML, "text/html", null);

    }
    public void onClick(View view) {
        if (view.getId() == R.id.backButton) {
            Intent prev = new Intent(this, MainApplication.class);
            startActivity(prev);
        }
    }
}
