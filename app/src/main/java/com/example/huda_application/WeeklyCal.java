package com.example.huda_application;

import static com.example.huda_application.CalUtilities.daysInWeekArray;
import static com.example.huda_application.CalUtilities.monthYearFromDate;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.example.huda_application.firebase.FirebaseClient;
import com.example.huda_application.user.User;
import com.example.huda_application.user.UserType;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.annotations.Nullable;

public class WeeklyCal extends AppCompatActivity implements CalAdapter.OnItemListener{

    private TextView monthYearText;
    private RecyclerView calRecycler;
    private ListView eventListView;

    ArrayList<Event> eventArrayList;
    DatabaseReference reference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weekly_cal);

        // initializing widgets
        calRecycler = findViewById(R.id.calRecycler);
        monthYearText = findViewById(R.id.monthYearTV);
        eventListView = findViewById(R.id.eventListView);

        setWeeklyView();
        initializeListView();
    }

    private void initializeListView()
    {
        // creating a new array adapter for our list view.
        final ArrayAdapter<Event> adapter = new ArrayAdapter<Event>(this, android.R.layout.simple_dropdown_item_1line, eventArrayList);

        reference = FirebaseDatabase.getInstance().getReference();

        reference.addChildEventListener(new ChildEventListener()
        {
            @Override
            public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName)
            {
                eventArrayList.add(snapshot.getValue(Event.class));
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot snapshot, @Nullable String previousChildName)
            {
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot snapshot)
            {
                eventArrayList.remove(snapshot.getValue(String.class));
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot snapshot, @Nullable String previousChildName)
            {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error)
            {

            }
        });
    }


    private void setWeeklyView()
    {
        monthYearText.setText(monthYearFromDate(CalUtilities.selectedDate));
        ArrayList<LocalDate> days = daysInWeekArray(CalUtilities.selectedDate);

        CalAdapter calAdapter = new CalAdapter(days, this);
        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(getApplicationContext(), 7);
        calRecycler.setLayoutManager(layoutManager);
        calRecycler.setAdapter(calAdapter);
        setEventAdapter();
    }

    // next week and previous week actions
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
    public void onItemClick(int position, LocalDate date)
    {
        CalUtilities.selectedDate = date;
        setWeeklyView();
    }

    public void addEvent(View view)
    {
        startActivity (new Intent(this, AddEvent.class));
    }

    protected void onResume()
    {
        super.onResume();
        setEventAdapter();
    }

    private void setEventAdapter()
    {
        ArrayList<Event> dailyEvents = Event.eventsPerDay(CalUtilities.selectedDate);
        EventAdapter eventAdapter = new EventAdapter(getApplicationContext(), dailyEvents);
        eventListView.setAdapter(eventAdapter);
    }

}