package com.example.huda_application;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.huda_application.firebase.FirebaseClient;
import com.example.huda_application.user.Appointment;
import com.example.huda_application.user.AppointmentStatus;
import com.example.huda_application.user.User;
import com.example.huda_application.user.UserManager;
import com.example.huda_application.user.UserType;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

public class AdminPage extends AppCompatActivity {

    private PatientViewAdapter viewAdapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_page);

        RecyclerView recyclerView = findViewById(R.id.usersView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        if (UserManager.getInstance().isAdmin()) {
            FirebaseDatabase.getInstance().getReference("User").addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    List<User> users = new ArrayList<>();
                    for (DataSnapshot child : snapshot.getChildren()) {
                        User user = FirebaseClient.convertToUser(child);
                        if (user.getUserType() != UserType.ADMIN)
                            users.add(user);
                    }

                    viewAdapter = new PatientViewAdapter(AdminPage.this, users);
                    recyclerView.setAdapter(viewAdapter);
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {
                }
            });
        }
    }

    private static class PatientViewAdapter extends RecyclerView.Adapter<PatientViewHolder> implements View.OnClickListener {

        private LayoutInflater inflater;
        private final List<User> users;
        private final Context context;

        public PatientViewAdapter(Context context, List<User> users) {
            this.context = context;
            this.users = users;
            this.inflater = LayoutInflater.from(context);
        }

        @NonNull
        @Override
        public PatientViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view = inflater.inflate(R.layout.admin_user_list_item, parent, false);
            return new PatientViewHolder(view);
        }

        @Override
        public void onBindViewHolder(@NonNull PatientViewHolder holder, int position) {
            final int pos = position;
            User user = users.get(position);
            FirebaseDatabase.getInstance().getReference("User").child(user.getUserId()).addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    users.set(pos, FirebaseClient.convertToUser(snapshot));
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {
                }
            });

            List<Appointment> appointments = user.getAppointments();
            int Pending_count =0;
            for (int i =0; i< appointments.size(); i ++){
                if (appointments.get(i).getStatus() == AppointmentStatus.PENDING){
                    Pending_count++;

                }
            }
            holder.name.setText(String.format("%s", user.getFirstName()));
            holder.lastname.setText(String.format("%s", user.getLastName()));
            holder.email.setText(user.getEmailAddress());
            holder.appointments.setText("Appointments "+ Pending_count);

            holder.appointments.setOnClickListener(view -> {
                Intent intent = new Intent(context, UserAppointment.class);
                intent.putExtra("user", user);
                context.startActivity(intent);
            });
        }

        @Override
        public int getItemCount() {
            return users.size();
        }

        @Override
        public void onClick(View v) {

        }
    }

    public static class PatientViewHolder extends RecyclerView.ViewHolder {

        private final TextView name;
        private final TextView email;
        private final TextView lastname;
        private final TextView appointments;

        public PatientViewHolder(@NonNull View itemView) {
            super(itemView);
            this.name = itemView.findViewById(R.id.name);
            this.lastname = itemView.findViewById(R.id.last_name);
            this.email = itemView.findViewById(R.id.email);
            this.appointments = itemView.findViewById(R.id.newUser);

        }

        public TextView getName() {
            return name;
        }

        public TextView getEmail() {
            return email;
        }

        public TextView getLastname() {
            return lastname;
        }

        public TextView getAppointments() {
            return appointments;
        }
    }
}