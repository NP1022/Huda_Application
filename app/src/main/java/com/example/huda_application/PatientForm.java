package com.example.huda_application;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class PatientForm extends AppCompatActivity
{
    private WebView wv; // webview variable

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_patient_form);

        wv = new WebView(this);
        wv.getSettings().setJavaScriptEnabled(true);
        wv.setWebViewClient(new WebViewClient());

        String embedLink = "<iframe src=\"https://docs.google.com/forms/d/e/1FAIpQLSdEx0YfOvHxaRkF4B_mpl5PtYNLIgeFMjCctXxRpmMG_mDVzg/viewform?embedded=true\" width=\"100%\" height=\"100%\" frameborder=\"0\" marginheight=\"0\" marginwidth=\"0\">Loading…</iframe>";
        wv.loadData(embedLink,"text/html",null);
        setContentView(wv);
    }
}