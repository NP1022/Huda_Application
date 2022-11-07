package com.example.huda_application;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.huda_application.firebase.FirebaseClient;
import com.example.huda_application.user.Appointment;
import com.example.huda_application.user.AppointmentStatus;
import com.example.huda_application.user.Message;
import com.example.huda_application.user.User;
import com.example.huda_application.user.UserManager;
import com.example.huda_application.user.UserType;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class PatientMessages extends AppCompatActivity {

    private PatientViewAdapter viewAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_patient_messages);
        RecyclerView recyclerView = findViewById(R.id.usersView5);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        for(int i=0; i< UserManager.getInstance().getCurrentUser().getMessages().size(); i++){

            UserManager.getInstance().getCurrentUser().getMessages().get(i).setRead(true);

        }
        FirebaseClient.updateUser(UserManager.getInstance().getCurrentUser());

        viewAdapter =  new PatientViewAdapter(this, UserManager.getInstance().getCurrentUser());
        recyclerView.setAdapter(viewAdapter);
    }

    private static class PatientViewAdapter extends RecyclerView.Adapter<PatientMessages.PatientViewHolder> implements View.OnClickListener {

        private LayoutInflater inflater;
        private final User user;
        private final Context context;

        public PatientViewAdapter(Context context, User user) {
            this.context = context;
            this.user = user;
            this.inflater = LayoutInflater.from(context);
        }

        @NonNull
        @Override
        public PatientViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view = inflater.inflate(R.layout.patient_message_item, parent, false);
            return new PatientViewHolder(view);
        }

        @Override
        public void onBindViewHolder(@NonNull PatientViewHolder holder, int position) {
            Message message = user.getMessages().get(position);
            holder.date.setText(message.getDate());
            holder.message.setText(message.getMessage());
        }

        @Override
        public int getItemCount() {
            return user.getMessages().size();
        }

        @Override
        public void onClick(View v) {

        }
    }

    public static class PatientViewHolder extends RecyclerView.ViewHolder {

        private final TextView date;
        private final TextView message;

        public PatientViewHolder(@NonNull View itemView) {
            super(itemView);
            this.date = itemView.findViewById(R.id.DateMessage);
            this.message = itemView.findViewById(R.id.Message3);

        }

        public TextView getDate() {
            return date;
        }

        public TextView getMessage() {
            return message;
        }
    }
}
