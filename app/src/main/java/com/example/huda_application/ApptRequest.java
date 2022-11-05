package com.example.huda_application;

import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;

import com.example.huda_application.appointment.AppointmentManager;
import com.example.huda_application.firebase.FirebaseClient;
import com.example.huda_application.user.Appointment;
import com.example.huda_application.user.AppointmentStatus;
import com.example.huda_application.user.UserManager;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.sql.Time;
import java.text.DateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class ApptRequest extends AppCompatActivity implements View.OnClickListener, DatePickerDialog.OnDateSetListener {

    private static final Pattern DATE_PATTERN = Pattern.compile(
            "^((0[1-9]|1[012])-(0[1-9]|[12][0-9]|3[01])-(19|2[0-9])[0-9]{2})$"); // date pattern match

    private static final Pattern TIME_PATTERN = Pattern.compile("(1[012]|[1-9]):[0-5][0-9](\\s)?(?i)(am|pm)");

    private EditText birthday, reason;
    private Button checkin, dateButton;
    private TextView time;
    private String selectedTime = "";
    private List<Time> availableTimes;
    private TextView date;
    private TextView textView;
    private AlertDialog.Builder options;
    private AlertDialog menu;
    private AppointmentManager appointmentManager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_appt_request);

        dateButton = findViewById(R.id.dateText);
        dateButton.setOnClickListener(v -> {
            DialogFragment datePicker = new DatePickerFragment();

            datePicker.show(getSupportFragmentManager(), "Available Times");
        });

        // date = findViewById(R.id.dateText);
        birthday = findViewById(R.id.birthday);
        reason = findViewById(R.id.email_checkin);
        time = findViewById(R.id.appointmentTime);
        checkin = findViewById(R.id.checkin);
        time.setOnClickListener(this);

        options = new AlertDialog.Builder(ApptRequest.this);
        options.setTitle("Available Times");

        time.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                create_menu(options);
            }
        });
//        adapter = new ArrayAdapter(CheckIn.this, android.R.layout.simple_spinner_item, new ArrayList<String>());
//        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
//        time.setAdapter(adapter);

        //date.setOnClickListener(this);
        birthday.setOnClickListener(this);
        reason.setOnClickListener(this);

        checkin.setOnClickListener(this);
    }

    public void create_menu(AlertDialog.Builder opts) {

        menu = opts.create();
        menu.show();
    }


    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.checkin) {
            String date = dateButton.getText().toString().replace("Date: ", "");
            Date currentTime = Calendar.getInstance().getTime();
            System.out.println(currentTime);
            System.out.println(date);
            Appointment appointment = new Appointment(
                    selectedTime,
                    date,
                    reason.getText().toString(),
                    AppointmentStatus.PENDING
            );

            UserManager.getInstance().getCurrentUser().addAppointment(appointment);
            AppointmentManager.createAppointment(date, selectedTime, UserManager.getInstance().getCurrentUser().getUserId());
            FirebaseClient.updateUser(UserManager.getInstance().getCurrentUser());
            Intent appointmentsIntent = new Intent(getApplicationContext(), Appointments.class);
            startActivity(appointmentsIntent);
        }
    }

    @Override
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {

        Calendar c = Calendar.getInstance();
        c.set(Calendar.YEAR, year);
        c.set(Calendar.MONTH, month);
        c.set(Calendar.DAY_OF_MONTH, dayOfMonth);

        String currentDateString = DateFormat.getDateInstance(DateFormat.FULL).format(c.getTime());
        TextView textView = findViewById(R.id.dateText);
        dateButton.setText(currentDateString);

        Date date = c.getTime();

        String key = String.format("%d-%d-%d", date.getMonth() + 1, date.getDate(), date.getYear() + 1900);
        textView.setText(String.format("Date: %s", key));
        appointmentManager = new AppointmentManager(key);
        FirebaseDatabase.getInstance().getReference("Appointment").child(key).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot child : snapshot.getChildren()) {
                    appointmentManager.convertAppointment(child);
                }
                availableTimes = appointmentManager.getAvailableTimes();
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {


                    options.setSingleChoiceItems(appointmentManager.getAvailableTimes().stream().map(Time::toString).collect(Collectors.toList()).toArray(new String[0]), -1, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface Diag_options, int opts) {
                            Time appointmentTime = availableTimes.get(opts);
                            time.setText(appointmentTime.toString());
                            selectedTime = appointmentTime.toString();
                            Diag_options.dismiss();
                        }
                    });

                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
            }
        });
    }
}