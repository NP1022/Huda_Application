package com.example.huda_application;

import static com.example.huda_application.CalUtilities.daysInWeekArray;
import static com.example.huda_application.CalUtilities.monthYearFromDate;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.time.LocalDate;
import java.util.ArrayList;

public class weeklyCalendar extends AppCompatActivity implements CalAdapter.OnItemListener{

    private TextView monthYearText;
    private RecyclerView calRecycler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weekly_calendar);

        initializeWidgets();
        setWeeklyView();
    }

    private void initializeWidgets()
    {
        calRecycler = findViewById(R.id.calRecycler);
        monthYearText = findViewById(R.id.monthYearTV);
    }

    private void setWeeklyView()
    {
        monthYearText.setText(monthYearFromDate(CalUtilities.selectedDate));
        ArrayList<LocalDate> days = daysInWeekArray(CalUtilities.selectedDate);

        CalAdapter calAdapter = new CalAdapter(days, this);
        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(getApplicationContext(), 7);
        calRecycler.setLayoutManager(layoutManager);
        calRecycler.setAdapter(calAdapter);
    }

    public void navigatePrevWeek(View view) {
        // goes back one month
        CalUtilities.selectedDate = CalUtilities.selectedDate.minusWeeks(1);
        setWeeklyView();
    }

    public void navigateNextWeek(View view) {
        // forward one month
        CalUtilities.selectedDate = CalUtilities.selectedDate.plusWeeks(1);
        setWeeklyView();
    }

    @Override
    //toast message when item is clicked
    // change this
    public void onItemClick(int position, String dayText)
    {

        String message = "Selected Date" + dayText + " " + monthYearFromDate(CalUtilities.selectedDate);
        Toast.makeText(this, message, Toast.LENGTH_LONG).show();
    }

}