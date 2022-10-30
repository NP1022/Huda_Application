package com.example.huda_application;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.huda_application.user.Appointment;
import com.example.huda_application.user.User;
import com.example.huda_application.user.UserManager;

public class Appointments extends AppCompatActivity {

    private AppointmentViewAdapter viewAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_appointments);

        RecyclerView recyclerView = findViewById(R.id.appointmentsView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        viewAdapter = new AppointmentViewAdapter(Appointments.this);
        recyclerView.setAdapter(viewAdapter);
    }

    private static class AppointmentViewAdapter extends RecyclerView.Adapter<Appointments.AppointmentViewHolder> {

        private LayoutInflater inflater;

        public AppointmentViewAdapter(Context context) {
            this.inflater = LayoutInflater.from(context);
        }

        @NonNull
        @Override
        public AppointmentViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view = inflater.inflate(R.layout.appointment_list_item, parent, false);
            return new AppointmentViewHolder(view);
        }

        @Override
        public void onBindViewHolder(@NonNull AppointmentViewHolder holder, int position) {
            User user = UserManager.getInstance().getCurrentUser();
            Appointment appointment = user.getAppointments().get(position);
            holder.time.setText(appointment.getTime());
            holder.date.setText(appointment.getDate());
            holder.status.setText(appointment.getStatus().name().toLowerCase());
        }

        @Override
        public int getItemCount() {
            return UserManager.getInstance().getCurrentUser().getAppointments().size();
        }
    }

    private static class AppointmentViewHolder extends RecyclerView.ViewHolder {

        private final TextView time;
        private final TextView date;
        private final TextView status;

        public AppointmentViewHolder(@NonNull View itemView) {
            super(itemView);
            this.time = itemView.findViewById(R.id.time);
            this.date = itemView.findViewById(R.id.appointmentDate);
            this.status = itemView.findViewById(R.id.Status);
        }

        public TextView getDate() {
            return date;
        }

        public TextView getTime() {
            return time;
        }

        public TextView getStatus() {
            return status;
        }
    }
}