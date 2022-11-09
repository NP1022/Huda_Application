package com.example.huda_application;

import android.content.Context;
import android.provider.CalendarContract;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;

import org.w3c.dom.Text;

import java.util.List;

public class EventAdapter extends ArrayAdapter<Event>
{

    public EventAdapter(@NonNull Context context, List<Event> events) {
        super(context, 0, events);
    }

    public View getView(int position, View insertView, ViewGroup parent)
    {
        Event event = getItem(position);

        if (insertView == null)

            insertView = LayoutInflater.from(getContext()).inflate(R.layout.event_item, parent, false);

        TextView eventItem = insertView.findViewById(R.id.eventItem);
        String eventTitle = event.getName() + " " + CalUtilities.timeFormatted(event.getTime());
        eventItem.setText(eventTitle);
        return insertView;

    }
}
