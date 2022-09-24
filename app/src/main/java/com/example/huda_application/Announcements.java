package com.example.huda_application;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.webkit.WebView;
import android.webkit.WebViewClient;


public class Announcements extends AppCompatActivity {

    // web view var
    private WebView webView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_announcements);

        // web view instance and enable JS
        webView = new WebView(this);
        webView.getSettings().setJavaScriptEnabled(true);
        webView.setWebViewClient(new WebViewClient());

        String googleHTML = "<iframe src=\"https://calendar.google.com/calendar/embed?src=c_tns32v5e01q5kjs1jj1rcuehao%40group.calendar.google.com&ctz=America%2FDetroit\" style=\"border: 0\" width=\"390\" height=\"350\" frameborder=\"0\" scrolling=\"no\"></iframe>";
        webView.loadData(googleHTML, "text/html", null);
        setContentView(webView);
    }
}
