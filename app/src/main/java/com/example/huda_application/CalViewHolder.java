package com.example.huda_application;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class CalViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

    public final View parentView;
    public final TextView day;
    private final CalAdapter.OnItemListener onItemListener;
    public CalViewHolder(@NonNull View itemView, CalAdapter.OnItemListener onItemListener)
    {
        super(itemView);
        this.parentView = itemView.findViewById(R.id.calendarDayText);
        day = itemView.findViewById(R.id.calendarDayText);
        this.onItemListener = onItemListener;

        itemView.setOnClickListener(this);
    }

    public void onClick(View view)
    {
        onItemListener.onItemClick(getAdapterPosition(), (String) day.getText());
    }
}
