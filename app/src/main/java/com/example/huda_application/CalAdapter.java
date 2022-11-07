package com.example.huda_application;

import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.time.LocalDate;
import java.util.ArrayList;

public class CalAdapter extends RecyclerView.Adapter<CalViewHolder>
{
    private final ArrayList<LocalDate> days;
    private final OnItemListener onItemListener;

    public CalAdapter(ArrayList<LocalDate> days, OnItemListener onItemListener)
    {
        this.days = days;
        this.onItemListener = onItemListener;
    }

    @NonNull
    @Override
    public CalViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
    {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.activity_calendar_item, parent, false);
        ViewGroup.LayoutParams layoutParams = view.getLayoutParams();

        // month view
        if(days.size() > 15)
            layoutParams.height = (int) (parent.getHeight() * 0.166666666);

        // week view
        else
            layoutParams.height = (int) (parent.getHeight());

        return new CalViewHolder(view, onItemListener);
    }

    @Override
    public void onBindViewHolder(@NonNull CalViewHolder holder, int position)
    {
        final LocalDate date = days.get(position);

        if(date == null)
            holder.day.setText("");
        else
        {
            holder.day.setText(String.valueOf(date.getDayOfMonth()));
            if (date.equals(CalUtilities.selectedDate))
                holder.parentView.setBackgroundColor(Color.LTGRAY);
        }
    }

    @Override
    public int getItemCount() {
        return days.size();
    }

    public interface OnItemListener
    {
        void onItemClick(int position, String dayText);
    }
}
