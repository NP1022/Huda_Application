package com.example.huda_application;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.CalendarView;
import android.widget.EditText;
import android.widget.TextView;

import java.time.LocalTime;

public class AddEvent extends AppCompatActivity
{

    private TextView eventDate, eventTime;
    private EditText eventName;

    private LocalTime time;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_event);

        initializeWidgets();

        time = LocalTime.now();

        eventDate.setText("Date: " + CalUtilities.dateFormatted(CalUtilities.selectedDate));
        eventTime.setText("Time: " + CalUtilities.timeFormatted(time));
    }

    // This function binds all the views with their IDs
    private void initializeWidgets()
    {
        eventName = findViewById(R.id.eventName);
        eventDate = findViewById(R.id.eventDate);
        eventTime = findViewById(R.id.eventTime);
    }

    public void saveEvent(View view) {

        String nameEvent = eventName.getText().toString();
        Event newEvent = new Event(nameEvent, CalUtilities.selectedDate, time);
        Event.eventsList.add(newEvent);
        finish();
    }
}