package com.example.huda_application;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;
// import android.app.DatePickerDialog;

import com.example.huda_application.appointment.AppointmentManager;
import com.example.huda_application.firebase.FirebaseClient;
import com.example.huda_application.user.Appointment;
import com.example.huda_application.user.AppointmentStatus;
import com.example.huda_application.user.UserManager;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.wdullaer.materialdatetimepicker.date.DatePickerDialog;

import java.sql.Time;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class ApptRequest extends AppCompatActivity implements View.OnClickListener, DatePickerDialog.OnDateSetListener {



    private EditText reason;
    private Button checkin, phoneButton;
    private TextView time, char_Count, dateButton;

    private ImageView backButton;
    private String selectedTime = "";                                    // Two of the variables are the available time list for the patient and the selected time that is used
    private List<Time> availableTimes;                                  // Appointment request variables that will store the date and the required information
                                                                        // for the appointment that is being requested

    private TextView date;
    private TextView textView;
    private AlertDialog.Builder options;                                // Alert builder that is used to show all the available appointments
    private AlertDialog menu;
    private AppointmentManager appointmentManager;                        // Instance of the appointment manager that will be used to filter the appointments

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_appt_request);

        dateButton = findViewById(R.id.dateText);
        dateButton.setOnClickListener(v -> {
            Calendar now = Calendar.getInstance();
            DatePickerDialog dialog = DatePickerDialog.newInstance(
                    this,
                    now.get(Calendar.YEAR),                             // The date button is used as the to show the date picker dialog for the application
                    now.get(Calendar.MONTH),
                    now.get(Calendar.DAY_OF_MONTH)
            );
            dialog.setAccentColor(0x043670);
            dialog.setMinDate(Calendar.getInstance());

            List<Calendar> days = new ArrayList<>();                        // Create the limited days for the calendar in the datepicker object for the application
            for (int i = 0; i < 120; i++) {
                Calendar day = Calendar.getInstance();
                day.add(Calendar.DAY_OF_MONTH, i);

                if (day.get(Calendar.DAY_OF_WEEK) != Calendar.TUESDAY &&            // Eliminate the following days from the Datepicker for the Days
                        day.get(Calendar.DAY_OF_WEEK) != Calendar.THURSDAY &&
                        day.get(Calendar.DAY_OF_WEEK) != Calendar.SATURDAY) {
                    days.add(day);
                }
            }

            dialog.setDisabledDays(days.toArray(new Calendar[0]));                   // setting the disabled days for the application
            dialog.show(getSupportFragmentManager(), "Available Times");
        });

        phoneButton = findViewById(R.id.callClinic);

        reason = findViewById(R.id.email_checkin);
        time = findViewById(R.id.appointmentTime);
        checkin = findViewById(R.id.checkin);
        char_Count = findViewById(R.id.charCount);
        backButton = (ImageView) findViewById(R.id.backButton);                     //All the Textview items used and button to request an appointment
        time.setOnClickListener(this);                                              // The time and the checkin button will be set only to the textview
        backButton.setOnClickListener(this);
        char_Count.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                int length = reason.length();
                String str = String.valueOf(length);
                char_Count.setText(str);
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                int length = reason.length();
                String str = String.valueOf(length);
                char_Count.setText(str);
            }

            @Override
            public void afterTextChanged(Editable s) {
                int length = reason.length();
                String str = String.valueOf(length);
                char_Count.setText(str);
            }
        });

        options = new AlertDialog.Builder(ApptRequest.this);
        options.setTitle("Available Times");
                                                                                    // Alert Dialog to show the available times for the patient
        time.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                create_menu(options);
            }
        });


        phoneButton.setOnClickListener(this);
        reason.setOnClickListener(this);                                        // Setting all the buttons for the onclicklistener to be used in the application
        checkin.setOnClickListener(this);

    }

    public void create_menu(AlertDialog.Builder opts) {
        menu = opts.create();                                                           // Create the dialog for the available times in the application
        menu.show();
    }


    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.checkin) {                     // The request button that is used to request an appointment
            boolean temp = true;
            String date = dateButton.getText().toString().replace("Date: ", "");
            String currentDate = new SimpleDateFormat("MM-d-yyyy", Locale.getDefault()).format(new Date());
            SimpleDateFormat sdf = new SimpleDateFormat("MM-d-yyyy");
            try {                                                                   // parse the current date that picked from the date button
                temp = sdf.parse(currentDate).before(sdf.parse(date));
            } catch (ParseException e) {
                e.printStackTrace();
            }

            if (temp == true){
            Appointment appointment = new Appointment(
                    selectedTime,                                       //create the appointment object from the request page of the application
                    date,
                    reason.getText().toString(),
                    AppointmentStatus.PENDING
            );

            UserManager.getInstance().getCurrentUser().addAppointment(appointment);
            AppointmentManager.createAppointment(date, selectedTime, UserManager.getInstance().getCurrentUser().getUserId());       // added the appointment to the current user and pushed the object to the database
            FirebaseClient.updateUser(UserManager.getInstance().getCurrentUser());
            Intent appointmentsIntent = new Intent(getApplicationContext(), Appointments.class);
            startActivity(appointmentsIntent);}
            else
                Toast.makeText(ApptRequest.this,"Appointments can't be made for previous days",Toast.LENGTH_LONG).show();
        }

        else if (view.getId() == R.id.backButton)
        {
            Intent prev = new Intent(this, Appointments.class);
            startActivity(prev);
        }
                                                                                // Two buttons used to call the clinic and back button
        else if (view.getId() == R.id.callClinic)
        {
            Intent HUDACall = new Intent(Intent.ACTION_DIAL);
            HUDACall.setData(Uri.parse("tel:3138658446"));
            startActivity(HUDACall);
        }
    }

    @Override
    public void onDateSet(DatePickerDialog view, int year, int month, int dayOfMonth) {
        Calendar c = Calendar.getInstance();
        c.set(Calendar.YEAR, year);
        c.set(Calendar.MONTH, month);                                                                // Once the date is set in the date picker the current date will be taken and set
        c.set(Calendar.DAY_OF_MONTH, dayOfMonth);

        String currentDateString = DateFormat.getDateInstance(DateFormat.FULL).format(c.getTime());
        TextView textView = findViewById(R.id.dateText);
        dateButton.setText(currentDateString);

        Date date = c.getTime();                                                                     // Sets the current date in the text view of the application

        String key = String.format("%d-%d-%d", date.getMonth() + 1, date.getDate(), date.getYear() + 1900);
        textView.setText(String.format("Date: %s", key));


        appointmentManager = new AppointmentManager(key);

        FirebaseDatabase.getInstance().getReference("ClinicHours").child(key).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                appointmentManager.createTimesOpen(snapshot.getValue(Float.class));  // create the available times for that specific days of the patients by checking if there was any modifications in the database

                FirebaseDatabase.getInstance().getReference("Appointment").child(key).addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        for (DataSnapshot child : snapshot.getChildren()) {
                            appointmentManager.convertAppointment(child);
                        }                                                               // on data exchange to get the available times after filtering appointments that already exist
                        availableTimes = appointmentManager.getAvailableTimes();
                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {


                            options.setSingleChoiceItems(appointmentManager.getAvailableTimes().stream().map(Time::toString).collect(Collectors.toList()).toArray(new String[0]), -1, new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface Diag_options, int opts) {
                                    Time appointmentTime = availableTimes.get(opts);
                                    time.setText(appointmentTime.toString());                       // adding the Available times for in the dialog alert that is shown to the patient
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

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}