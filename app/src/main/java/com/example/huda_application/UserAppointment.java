package com.example.huda_application;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
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
import com.example.huda_application.user.UserType;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class UserAppointment extends AppCompatActivity implements View.OnClickListener {

    private AppointmentViewAdapter viewAdapter;
    private ImageView backbutton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_appointment);

        Intent intent = getIntent();
        User user = (User) intent.getSerializableExtra("user");

        TextView name = findViewById(R.id.firstName);
        TextView birthday = findViewById(R.id.birthday);

        name.setText(String.format("%s %s", user.getFirstName(), user.getLastName()));


        RecyclerView recyclerView = findViewById(R.id.appointmentsView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        backbutton = findViewById(R.id.backButton_8);
        FirebaseDatabase.getInstance().getReference("User").child(user.getUserId()).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                User updatedUser = FirebaseClient.convertToUser(snapshot);
                viewAdapter = new AppointmentViewAdapter(UserAppointment.this, updatedUser);
                recyclerView.setAdapter(viewAdapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
            }
        });
        backbutton.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.backButton_8)
            startActivity(new Intent(UserAppointment.this , AdminPage.class));
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

            holder.checkedIn.setVisibility(View.GONE);
            holder.checkedInText.setVisibility(View.GONE);
            if(appointment.getStatus() == AppointmentStatus.APPROVED)
            {
                holder.approve.setVisibility(View.GONE);
                holder.deny.setVisibility(View.GONE);


                holder.checkedIn.setText(appointment.isCheckedIn() ? "Yes" : "No");
                holder.checkedIn.setVisibility(View.VISIBLE);
                holder.checkedInText.setVisibility(View.VISIBLE);
            }
            if(appointment.getStatus() == AppointmentStatus.DENIED )
            {
                holder.approve.setVisibility(View.GONE);
                holder.deny.setVisibility(View.GONE);

            }
            if(appointment.getStatus() == AppointmentStatus.PENDING )
            {
                holder.approve.setVisibility(View.VISIBLE);
                holder.deny.setVisibility(View.VISIBLE);

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
                holder.checkedIn.setText(appointment.isCheckedIn() ? "Yes" : "No");
                holder.checkedIn.setVisibility(View.VISIBLE);
                holder.checkedInText.setVisibility(View.VISIBLE);

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
        private final TextView checkedIn;
        private final TextView checkedInText;

        private final AppCompatButton approve;
        private final AppCompatButton deny;

        public AppointmentViewHolder(@NonNull View itemView) {
            super(itemView);
            time = itemView.findViewById(R.id.time);
            date = itemView.findViewById(R.id.appointmentDate);
            status = itemView.findViewById(R.id.Status);
            Reason = itemView.findViewById(R.id.Reason);
            approve = itemView.findViewById(R.id.Approve);
            deny = itemView.findViewById(R.id.decline);
            checkedIn = itemView.findViewById(R.id.checkedIn);
            checkedInText = itemView.findViewById(R.id.checkedInText);
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

        public TextView getCheckedIn() {
            return checkedIn;
        }

        public TextView getCheckedInText() {
            return checkedInText;
        }
    }
}