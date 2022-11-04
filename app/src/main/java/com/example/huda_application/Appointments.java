package com.example.huda_application;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.huda_application.appointment.AppointmentManager;
import com.example.huda_application.firebase.FirebaseClient;
import com.example.huda_application.user.Appointment;
import com.example.huda_application.user.AppointmentStatus;
import com.example.huda_application.user.User;
import com.example.huda_application.user.UserManager;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;



import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

public class Appointments extends AppCompatActivity implements View.OnClickListener {

    private AppointmentViewAdapter viewAdapter;
    private ImageView backButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_appointments);


        RecyclerView recyclerView = findViewById(R.id.appointmentsView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        FirebaseDatabase.getInstance().getReference("User").child(UserManager.getInstance().getCurrentUser().getUserId())
                .addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        User user = FirebaseClient.convertToUser(snapshot);
                        viewAdapter = new AppointmentViewAdapter(Appointments.this, user);
                        recyclerView.setAdapter(viewAdapter);
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {
                    }
                });

        AppCompatButton createButton = findViewById(R.id.create);

        createButton.setOnClickListener(view -> {
            Intent createAppointmentIntent = new Intent(this, CheckIn.class);
            startActivity(createAppointmentIntent);

        });

        backButton = findViewById(R.id.backButton);
        backButton.setOnClickListener(this);


    }

    @Override
    public void onClick(View view) {
        if(view.getId() == R.id.backButton)
        {

            Intent Main = new Intent(this ,MainApplication.class);
            startActivity(Main);

        }
    }


    private class AppointmentViewAdapter extends RecyclerView.Adapter<Appointments.AppointmentViewHolder> {

        private LayoutInflater inflater;
        private List<Appointment> appointments;
        private User user;

        public AppointmentViewAdapter(Context context, User user) {
            this.inflater = LayoutInflater.from(context);
            this.user = user;
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                appointments = user.getAppointments().stream()
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
            Appointment appointment = appointments.get(position);
            holder.time.setText(appointment.getTime());
            holder.date.setText(appointment.getDate());
            holder.status.setText(appointment.getStatus().name().toLowerCase());

            holder.checkedInText.setVisibility(View.GONE);
            if ((appointment.getStatus() != AppointmentStatus.PENDING && appointment.getStatus() != AppointmentStatus.APPROVED) || appointment.isCheckedIn()) {
                holder.cancel.setVisibility(View.GONE);
            }

            if (appointment.getStatus() != AppointmentStatus.APPROVED || appointment.isCheckedIn()) {
                holder.checkInButton.setVisibility(View.GONE);

            }

            if (appointment.isCheckedIn()) {
                holder.checkedInText.setVisibility(View.VISIBLE);
            }

            holder.cancel.setOnClickListener(view -> {
                for (Appointment userAppointment : user.getAppointments()) {
                    if (userAppointment == appointment) {
                        userAppointment.setStatus(AppointmentStatus.CANCELED);
                        AppointmentManager.deleteAppointment(appointment.getDate(),appointment.getTime());
                    }

                }

                appointments.get(position).setStatus(AppointmentStatus.CANCELED);
                holder.status.setText(appointment.getStatus().name().toLowerCase());
                FirebaseClient.updateUser(user);
                UserManager.getInstance().setCurrentUser(user);
            });

            holder.checkInButton.setOnClickListener(view -> {
                String currentDate = new SimpleDateFormat("MM-d-yyyy", Locale.getDefault()).format(new Date());
                System.out.println(currentDate);

                String currentTime = new SimpleDateFormat("h:mm a", Locale.getDefault()).format(new Date());

                System.out.println(currentTime);


                System.out.println(appointment.getTime().toUpperCase());
                String appointment_time = appointment.getTime().toUpperCase();

                System.out.println(appointment.getDate());
                if(appointment.getDate().equals(currentDate))
                {
                    if(currentTime.substring(currentTime.length()-2).equals(appointment_time.substring(appointment_time.length()-2))){
                        String startTime = currentTime.substring(0,4);
                        String endTime = appointment_time.substring(0,4);
                        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
                        Date d1 = null;
                        try {
                            d1 = sdf.parse(startTime);
                        } catch (ParseException e) {
                            e.printStackTrace();
                        }
                        Date d2 = null;
                        try {
                            d2 = sdf.parse(endTime);
                        } catch (ParseException e) {
                            e.printStackTrace();
                        }
                        long elapsed = d2.getTime() - d1.getTime();
                        System.out.println(elapsed);
                        if (elapsed < 1500000) {

                        }

                    }

                    appointments.get(position).setCheckedIn(true);
                    holder.cancel.setVisibility(View.GONE);
                    holder.checkInButton.setVisibility(View.GONE);
                    holder.checkedInText.setVisibility(View.VISIBLE);
                    FirebaseClient.updateUser(user);
                    UserManager.getInstance().setCurrentUser(user);
                }
                else
                    Toast.makeText(Appointments.this,"Check-in on allowed on the same date! ",Toast.LENGTH_LONG).show();
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
        private final TextView checkedInText;
        private final AppCompatButton cancel;
        private final AppCompatButton checkInButton;

        public AppointmentViewHolder(@NonNull View itemView) {
            super(itemView);
            this.time = itemView.findViewById(R.id.time);
            this.date = itemView.findViewById(R.id.appointmentDate);
            this.status = itemView.findViewById(R.id.Status);
            this.checkedInText = itemView.findViewById(R.id.checkedInText);
            this.cancel = itemView.findViewById(R.id.cancelAppointment);
            this.checkInButton = itemView.findViewById(R.id.checkInAppointment);
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

        public AppCompatButton getCheckInButton() {
            return checkInButton;
        }

        public TextView getCheckedInText() {
            return checkedInText;
        }
    }
}