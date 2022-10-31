package com.example.huda_application;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.huda_application.firebase.FirebaseClient;
import com.example.huda_application.user.Appointment;
import com.example.huda_application.user.AppointmentStatus;
import com.example.huda_application.user.User;
import com.example.huda_application.user.UserManager;

import java.util.List;
import java.util.stream.Collectors;

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

        AppCompatButton createButton = findViewById(R.id.create);

        createButton.setOnClickListener(view -> {
            Intent createAppointmentIntent = new Intent(this, CheckIn.class);
            startActivity(createAppointmentIntent);
        });
    }

    private static class AppointmentViewAdapter extends RecyclerView.Adapter<Appointments.AppointmentViewHolder> {

        private LayoutInflater inflater;
        private List<Appointment> appointments;

        public AppointmentViewAdapter(Context context) {
            this.inflater = LayoutInflater.from(context);
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                appointments = UserManager.getInstance().getCurrentUser().getAppointments().stream()
                        .filter(appointment -> appointment.getStatus() != AppointmentStatus.CANCELED).collect(Collectors.toList());
            }
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
            Appointment appointment = appointments.get(position);
            holder.time.setText(appointment.getTime());
            holder.date.setText(appointment.getDate());
            holder.status.setText(appointment.getStatus().name().toLowerCase());

            holder.cancel.setOnClickListener(view -> {
                for (Appointment userAppointment : user.getAppointments()) {
                    if (userAppointment == appointment) {
                        userAppointment.setStatus(AppointmentStatus.CANCELED);
                    }
                }

                appointments.get(position).setStatus(AppointmentStatus.CANCELED);
                holder.status.setText(appointment.getStatus().name().toLowerCase());
                FirebaseClient.updateUser(user);
            });
        }

        @Override
        public int getItemCount() {
            return appointments.size();
        }
    }

    private static class AppointmentViewHolder extends RecyclerView.ViewHolder {

        private final TextView time;
        private final TextView date;
        private final TextView status;
        private final AppCompatButton cancel;

        public AppointmentViewHolder(@NonNull View itemView) {
            super(itemView);
            this.time = itemView.findViewById(R.id.time);
            this.date = itemView.findViewById(R.id.appointmentDate);
            this.status = itemView.findViewById(R.id.Status);
            this.cancel = itemView.findViewById(R.id.cancelAppointment);
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

        public AppCompatButton getCancel() {
            return cancel;
        }
    }
}