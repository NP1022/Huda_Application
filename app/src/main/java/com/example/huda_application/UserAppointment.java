package com.example.huda_application;

import android.content.Context;
import android.content.Intent;
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

public class UserAppointment extends AppCompatActivity {

    private AppointmentViewAdapter viewAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_appointment);

        Intent intent = getIntent();
        User user = (User) intent.getSerializableExtra("user");

        TextView name = findViewById(R.id.firstName);
        TextView birthday = findViewById(R.id.birthday);

        name.setText(String.format("%s %s", user.getFirstName(), user.getLastName()));
        birthday.setText("Birthday Here");

        RecyclerView recyclerView = findViewById(R.id.appointmentsView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        viewAdapter = new AppointmentViewAdapter(UserAppointment.this, user);
        recyclerView.setAdapter(viewAdapter);
    }

    private static class AppointmentViewAdapter extends RecyclerView.Adapter<AppointmentViewHolder> {

        private LayoutInflater inflater;
        private final User user;

        public AppointmentViewAdapter(Context context, User user) {
            this.user = user;
            this.inflater = LayoutInflater.from(context);
        }

        @NonNull
        @Override
        public AppointmentViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view = inflater.inflate(R.layout.appointment_user_item, parent, false);
            return new AppointmentViewHolder(view);
        }

        @Override
        public void onBindViewHolder(@NonNull AppointmentViewHolder holder, int position) {
            Appointment appointment = user.getAppointments().get(position);
            holder.time.setText(appointment.getTime());
            holder.date.setText(appointment.getDate());
            holder.status.setText(appointment.getStatus().name().toLowerCase());
            holder.Reason.setText(appointment.getReason());

            if(appointment.getStatus() == AppointmentStatus.APPROVED )
            {
                holder.approve.setVisibility(View.GONE);
                holder.deny.setVisibility(View.GONE);

            }
            if(appointment.getStatus() == AppointmentStatus.DENIED )
            {
                holder.approve.setVisibility(View.GONE);
                holder.deny.setVisibility(View.GONE);

            }
            if(appointment.getStatus() == AppointmentStatus.CANCELED )
            {
                holder.approve.setVisibility(View.GONE);
                holder.deny.setVisibility(View.GONE);

            }

            holder.approve.setOnClickListener(listener -> {
                user.getAppointments().get(position).setStatus(AppointmentStatus.APPROVED);
                FirebaseClient.updateUser(user);
                holder.approve.setVisibility(View.GONE);
                holder.deny.setVisibility(View.GONE);

                holder.status.setText(appointment.getStatus().name().toLowerCase());

            });

            holder.deny.setOnClickListener(listener -> {
                user.getAppointments().get(position).setStatus(AppointmentStatus.DENIED);
                FirebaseClient.updateUser(user);

                holder.approve.setVisibility(View.GONE);
                holder.deny.setVisibility(View.GONE);
                holder.status.setText(appointment.getStatus().name().toLowerCase());

            });
        }

        @Override
        public int getItemCount() {
            return user.getAppointments().size();
        }
    }

    public static class AppointmentViewHolder extends RecyclerView.ViewHolder {

        private final TextView time;
        private final TextView date;
        private final TextView status;
        private final TextView Reason;

        private final AppCompatButton approve;
        private final AppCompatButton deny;

        public AppointmentViewHolder(@NonNull View itemView) {
            super(itemView);
            time = itemView.findViewById(R.id.time);
            date = itemView.findViewById(R.id.appointmentDate);
            status = itemView.findViewById(R.id.Status);
            Reason = itemView.findViewById(R.id.Reason);
            approve = itemView.findViewById(R.id.approve);
            deny = itemView.findViewById(R.id.decline);
        }

        public TextView getTime() {
            return time;
        }

        public TextView getDate() {
            return date;
        }

        public TextView getStatus() {
            return status;
        }
        public TextView getReason() {
            return Reason;
        }
    }
}