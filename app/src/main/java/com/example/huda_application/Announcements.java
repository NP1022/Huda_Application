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

public class Announcements extends AppCompatActivity implements CalAdapter.OnItemListener, View.OnClickListener
{
    private TextView monthYearText;
    private RecyclerView calRecycler;
    private ImageView backButton;

    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_announcements);
        initializeWidgets();
        backButton = (ImageView) findViewById(R.id.backButton);
        backButton.setOnClickListener((View.OnClickListener) this);
    }

    private void initializeWidgets()
    {
        calRecycler = findViewById(R.id.calRecycler);
        monthYearText = findViewById(R.id.monthYearTV);
        CalUtilities.selectedDate = LocalDate.now();
        setMonthView();
    }

    private void setMonthView()
    {
        monthYearText.setText(monthYearFromDate(CalUtilities.selectedDate));
        ArrayList<LocalDate> daysInMonth = daysInMonthArray(CalUtilities.selectedDate);

        CalAdapter calAdapter = new CalAdapter(daysInMonth, this);
        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(getApplicationContext(), 7);
        calRecycler.setLayoutManager(layoutManager);
        calRecycler.setAdapter(calAdapter);
    }

    public void navigatePrevMonth(View view)
    {
        // goes back one month
        CalUtilities.selectedDate = CalUtilities.selectedDate.minusMonths(1);
        setMonthView();
    }

    public void navigateNextMonth(View view)
    {
        // forward one month
        CalUtilities.selectedDate = CalUtilities.selectedDate.plusMonths(1);
        setMonthView();
    }

    @Override
    //toast message when item is clicked
    // change this
    public void onItemClick(int position, String dayText)
    {
        if(dayText.equals(""))
        {
            String message = "Selected Date" + dayText + " " + monthYearFromDate(CalUtilities.selectedDate);
        }
    }

    public void weeklyView(View view)
    {
        startActivity(new Intent(this, weeklyCalendar.class));
    }

    @Override
    public void onClick(View view) {
        if(view.getId() == R.id.backButton)
        {

            Intent Main = new Intent(this ,MainApplication.class);
            startActivity(Main);

        }
    }
}