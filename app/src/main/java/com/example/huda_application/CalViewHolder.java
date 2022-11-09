package com.example.huda_application;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.time.LocalDate;
import java.util.ArrayList;

public class CalViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

    private final ArrayList<LocalDate> days;
    public final View parentView;
    public final TextView day;
    private final CalAdapter.OnItemListener onItemListener;
    public CalViewHolder(@NonNull View itemView, CalAdapter.OnItemListener onItemListener, ArrayList<LocalDate> days)
    {
        super(itemView);
        this.parentView = itemView.findViewById(R.id.calendarDayText);
        day = itemView.findViewById(R.id.calendarDayText);
        this.onItemListener = onItemListener;
        this.days = days;
        itemView.setOnClickListener(this);
    }

    public void onClick(View view)
    {
        onItemListener.onItemClick(getAdapterPosition(), days.get(getAdapterPosition()));
    }
}
