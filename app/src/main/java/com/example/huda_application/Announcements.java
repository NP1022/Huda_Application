package com.example.huda_application;

import static com.example.huda_application.CalUtilities.daysInMonthArray;
import static com.example.huda_application.CalUtilities.monthYearFromDate;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import java.time.LocalDate;
import java.util.ArrayList;

import android.webkit.WebView;
import android.webkit.WebViewClient;


public class Announcements extends AppCompatActivity implements View.OnClickListener {

    // web view var
    private WebView webView;
    private ImageView backbutton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_announcements);

        webView = findViewById(R.id.calendarWebView);
        webView.setWebViewClient(new WebViewClient());
        webView.getSettings().setJavaScriptEnabled(true);

        // <iframe src=\"https://calendar.google.com/calendar/embed?src=c_tns32v5e01q5kjs1jj1rcuehao%40group.calendar.google.com&ctz=America%2FDetroit\" style=\"border: 0\" width=\"390\" height=\"350\" frameborder=\"0\" scrolling=\"no\"></iframe>"
        //String googleHTML = "<iframe src=\"https://embed.styledcalendar.com/#my4zA7wGz2FkThs3z3eJ\" title=\"Styled Calendar\" class=\"styled-calendar-container\" style=\"width= 390px; height=\"350\" border: none;\" data-cy=\"calendar-embed-iframe\"></iframe>";
        //String googleHTML = "<iframe src=\"https://calendar.google.com/calendar/embed?src=c_tns32v5e01q5kjs1jj1rcuehao%40group.calendar.google.com&ctz=America%2FDetroit\" style=\"width=\"390\" height=\"350\"></iframe>";
        String googleHTML = "https://embed.styledcalendar.com/#my4zA7wGz2FkThs3z3eJ";
        webView.loadUrl(googleHTML);
        backbutton = findViewById(R.id.backButton_10);
        backbutton.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if(v.getId() == R.id.backButton_10)
            startActivity( new Intent(this , MainApplication.class));
    }
}

//public class Announcements extends AppCompatActivity
//{
//    private TextView monthYearText;
//    private RecyclerView calRecycler;
//
//    protected void onCreate(Bundle savedInstanceState)
//    {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_announcements);
//        //initializeWidgets();
//    }

//    private void initializeWidgets()
//    {
//        calRecycler = findViewById(R.id.calRecycler);
//        monthYearText = findViewById(R.id.monthYearTV);
//        CalUtilities.selectedDate = LocalDate.now();
//        setMonthView();
//    }
//
//    private void setMonthView()
//    {
//        monthYearText.setText(monthYearFromDate(CalUtilities.selectedDate));
//        ArrayList<LocalDate> daysInMonth = daysInMonthArray(CalUtilities.selectedDate);
//
//        CalAdapter calAdapter = new CalAdapter(daysInMonth, this);
//        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(getApplicationContext(), 7);
//        calRecycler.setLayoutManager(layoutManager);
//        calRecycler.setAdapter(calAdapter);
//    }
//
//    public void navigatePrevMonth(View view)
//    {
//        // goes back one month
//        CalUtilities.selectedDate = CalUtilities.selectedDate.minusMonths(1);
//        setMonthView();
//    }
//
//    public void navigateNextMonth(View view)
//    {
//        // forward one month
//        CalUtilities.selectedDate = CalUtilities.selectedDate.plusMonths(1);
//        setMonthView();
//    }
//
//    @Override
//    //toast message when item is clicked
//    // change this
//    public void onItemClick(int position, LocalDate date)
//    {
//        if(date != null)
//        {
//            CalUtilities.selectedDate = date;
//            setMonthView();
//        }
//    }
//
//    public void weeklyView(View view)
//    {
//        startActivity(new Intent(this, WeeklyCal.class));
//    }

//}