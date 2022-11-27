package com.example.huda_application;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.huda_application.appointment.AppointmentManager;
import com.example.huda_application.user.Appointment;
import com.example.huda_application.user.AppointmentStatus;
import com.example.huda_application.user.User;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.wdullaer.materialdatetimepicker.date.DatePickerDialog;

import java.sql.Time;
import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

public class AvailableAppointments extends AppCompatActivity implements DatePickerDialog.OnDateSetListener, View.OnClickListener {

    private Button dateButton;                                          // Available appointment class used to show the available appointment times for the Admin to allow them
    private AppointmentManager appointmentManager;                      // to remove specific appointment times for the patients of the application
    private List<Time> availableTimes = new ArrayList<>();              // The removed times will be stored in the database and filtered when displayed for the patient
    private PatientViewAdapter viewAdapter;
    private ImageView backbutton;
    private ValueEventListener hoursListener;
    private ValueEventListener dbListener;
    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_available_appointments);

        recyclerView = findViewById(R.id.usersView6);                                               // Setting the recycler view to show all the appointment times for the admin to remove from
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        backbutton = findViewById(R.id.backButton_7);                                               //back button and the date button
        dateButton = findViewById(R.id.dateText2);

        dateButton.setOnClickListener(v -> {
            Calendar now = Calendar.getInstance();
            com.wdullaer.materialdatetimepicker.date.DatePickerDialog dialog = com.wdullaer.materialdatetimepicker.date.DatePickerDialog.newInstance(
                    this,
                    now.get(Calendar.YEAR),                  // The date button is used as the to show the date picker dialog for the application
                    now.get(Calendar.MONTH),
                    now.get(Calendar.DAY_OF_MONTH)
            );
            dialog.setAccentColor(0x043670);
            dialog.setMinDate(Calendar.getInstance());

            List<Calendar> days = new ArrayList<>();                    // Create the limited days for the calendar in the datepicker object for the application
            for (int i = 0; i < 90; i++) {
                Calendar day = Calendar.getInstance();
                day.add(Calendar.DAY_OF_MONTH, i);

                if (day.get(Calendar.DAY_OF_WEEK) != Calendar.TUESDAY &&            // Eliminate the following days from the Datepicker for the Days
                        day.get(Calendar.DAY_OF_WEEK) != Calendar.THURSDAY &&
                        day.get(Calendar.DAY_OF_WEEK) != Calendar.SATURDAY) {
                    days.add(day);
                }
            }                                                                             // setting the disabled days for the application

            dialog.setDisabledDays(days.toArray(new Calendar[0]));
            dialog.show(getSupportFragmentManager(), "Available Times");
        });
        backbutton.setOnClickListener( this);

    }

    @Override
    public void onClick(View v) {
        if(v.getId() == R.id.backButton_7)
            startActivity(new Intent(this, Adminpanel.class));
    }



    private class PatientViewAdapter extends RecyclerView.Adapter<PatientViewHolder>  {

        private LayoutInflater inflater;                                         // Variables used by adapter to show the items in the recycler view in the application.

        private final Context context;
        private List<Time> times;

        public PatientViewAdapter(Context context, List<Time> times) {
            this.context = context;
            this.times = times;                                             // Constructor used to set the variables equal to the parameters for the recycler adapter
            this.inflater = LayoutInflater.from(context);
        }

        @NonNull
        @Override
        public PatientViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view = inflater.inflate(R.layout.available_appointments_item, parent, false);                                      // over ride method to check the view of the Adapter for the recycle view


            return new PatientViewHolder(view);
        }

        @Override
        public void onBindViewHolder(@NonNull PatientViewHolder holder, int position) {          // Holder for each Item that is used in the recycle view of page which use the holder object
            Time time = times.get(position);
            holder.Time.setText(time.toString());
            holder.Remove.setOnClickListener(view -> {
                AppointmentManager.createAppointment(AvailableAppointments.this.dateButton.getText().toString(), time.toString(), "Admin");
                Toast.makeText(AvailableAppointments.this,"Appointment Time "+times.get(position) + " time slot has been removed",Toast.LENGTH_SHORT).show(); // The remove created an appointment at that specific time to take it off the view for the patients
            });
        }

        @Override
        public int getItemCount() {
            return times.size();
        }           // Returns the amount of items in the recycler view

        @Override
        public int getItemViewType(int position) {
            return position;
        }
    }

    public static class PatientViewHolder extends RecyclerView.ViewHolder {

        private final TextView Time;                                                     // Holder variables to set equal to the position that is found on the UI of the
                                                                                          // application Each one of the holder variables will be connected to
                                                                                          //the Item list that is used in the recycle view of the application
        private final TextView Remove;

        public PatientViewHolder(@NonNull View itemView) {
            super(itemView);
            this.Time = itemView.findViewById(R.id.Time_appointment);
            this.Remove = itemView.findViewById(R.id.Remove);
        }

        public TextView getTime() {
            return Time;
        }

        public TextView getRemove() {
            return Remove;
        }
    }

    @Override
    public void onDateSet(DatePickerDialog view, int year, int monthOfYear, int dayOfMonth) {
        Calendar c = Calendar.getInstance();
        c.set(Calendar.YEAR, year);                                                                      // Once the date is set in the date picker the current date will be taken and set

        c.set(Calendar.MONTH, monthOfYear);
        c.set(Calendar.DAY_OF_MONTH, dayOfMonth);

        String currentDateString = DateFormat.getDateInstance(DateFormat.FULL).format(c.getTime());
        TextView textView = findViewById(R.id.dateText2);
        dateButton.setText(currentDateString);

        Date date = c.getTime();                                                                        // Sets the current date in the text view of the application

        String key = String.format("%d-%d-%d", date.getMonth() + 1, date.getDate(), date.getYear() + 1900);
        textView.setText(key);


        appointmentManager = new AppointmentManager(key);

        if (hoursListener != null) {
            FirebaseDatabase.getInstance().getReference("ClinicHours").child(key).removeEventListener(hoursListener);   // If the picked day isn't empty it will remove the listener for that
                                                                                                                             // day that is displayed
            FirebaseDatabase.getInstance().getReference("Appointment").child(key).removeEventListener(dbListener);
        }

        hoursListener = new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                appointmentManager.createTimesOpen(snapshot.getValue(Float.class));                 // create the available times for that specific days of the patients by checking if
                                                                                                    // there was any modifications in the database

                dbListener = new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        for (DataSnapshot child : snapshot.getChildren()) {                         // on data exchange to get the available times after filtering appointments that already exist
                            appointmentManager.convertAppointment(child);
                        }

                        availableTimes = appointmentManager.getAvailableTimes();

                        viewAdapter = new PatientViewAdapter(AvailableAppointments.this, availableTimes);
                        recyclerView.setAdapter(viewAdapter);
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {
                    }
                };

                FirebaseDatabase.getInstance().getReference("Appointment").child(key).addValueEventListener(dbListener);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        };

        FirebaseDatabase.getInstance().getReference("ClinicHours").child(key).addValueEventListener(hoursListener);
    }
}