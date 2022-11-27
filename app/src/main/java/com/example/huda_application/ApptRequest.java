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

    private static final Pattern DATE_PATTERN = Pattern.compile(
            "^((0[1-9]|1[012])-(0[1-9]|[12][0-9]|3[01])-(19|2[0-9])[0-9]{2})$"); // date pattern match

    private static final Pattern TIME_PATTERN = Pattern.compile("(1[012]|[1-9]):[0-5][0-9](\\s)?(?i)(am|pm)");

    private EditText reason;
    private Button checkin, dateButton, phoneButton;
    private TextView time, char_Count;

    private ImageView backButton;
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
            Calendar now = Calendar.getInstance();
            DatePickerDialog dialog = DatePickerDialog.newInstance(
                    this,
                    now.get(Calendar.YEAR),
                    now.get(Calendar.MONTH),
                    now.get(Calendar.DAY_OF_MONTH)
            );
            dialog.setAccentColor(0x043670);
            dialog.setMinDate(Calendar.getInstance());

            List<Calendar> days = new ArrayList<>();
            for (int i = 0; i < 90; i++) {
                Calendar day = Calendar.getInstance();
                day.add(Calendar.DAY_OF_MONTH, i);

                if (day.get(Calendar.DAY_OF_WEEK) != Calendar.TUESDAY &&
                        day.get(Calendar.DAY_OF_WEEK) != Calendar.THURSDAY &&
                        day.get(Calendar.DAY_OF_WEEK) != Calendar.SATURDAY) {
                    days.add(day);
                }
            }

            dialog.setDisabledDays(days.toArray(new Calendar[0]));
            dialog.show(getSupportFragmentManager(), "Available Times");
        });

        phoneButton = findViewById(R.id.callClinic);

        // date = findViewById(R.id.dateText);
        // birthday = findViewById(R.id.birthday);

        reason = findViewById(R.id.email_checkin);
        char_Count = findViewById(R.id.charCount);
        char_Count.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @SuppressLint("SetTextI18n")
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                char_Count.setText(reason.getText().toString());
            }

            @Override
            public void afterTextChanged(Editable s) {
                char_Count.setText(reason.getText().toString());

            }
        });
        time = findViewById(R.id.appointmentTime);
        checkin = findViewById(R.id.checkin);
        backButton = (ImageView) findViewById(R.id.backButton);
        time.setOnClickListener(this);
        backButton.setOnClickListener(this);

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
//        birthday.setOnClickListener(v -> {
//            Calendar now = Calendar.getInstance();
//            DatePickerDialog dialog = DatePickerDialog.newInstance(
//                    this,
//                    now.get(Calendar.YEAR),
//                    now.get(Calendar.MONTH),
//                    now.get(Calendar.DAY_OF_MONTH)
//            );
//            dialog.setAccentColor(0x043670);
//            dialog.setMaxDate(Calendar.getInstance());
//
//            List<Calendar> days = new ArrayList<>();
//            for (int i = 0; i < 90; i++) {
//                Calendar day = Calendar.getInstance();
//                day.add(Calendar.DAY_OF_MONTH, i);
//
//            }
//
//            dialog.setDisabledDays(days.toArray(new Calendar[0]));
//            dialog.show(getSupportFragmentManager(), "Available Times");
//        });

        phoneButton.setOnClickListener(this);
        reason.setOnClickListener(this);
        checkin.setOnClickListener(this);

//        EditText birthday = findViewById(R.id.birthday);
//        birthday.addTextChangedListener(new TextWatcher() {
//            int prevL = 0;
//
//            @Override
//            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
//                prevL = birthday.getText().toString().length();
//            }
//
//            @Override
//            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
//
//            }
//
//            @Override
//            public void afterTextChanged(Editable editable) {
//                int length = editable.length();
//                if ((prevL < length) && (length == 2 || length == 5)) {
//                    editable.append("-");
//                }
//            }
//        });

    }

    public void create_menu(AlertDialog.Builder opts) {

        menu = opts.create();
        menu.show();
    }


    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.checkin) {
            boolean temp = true;
            String date = dateButton.getText().toString().replace("Date: ", "");
            String currentDate = new SimpleDateFormat("MM-d-yyyy", Locale.getDefault()).format(new Date());
            SimpleDateFormat sdf = new SimpleDateFormat("MM-d-yyyy");
            try {
                temp = sdf.parse(currentDate).before(sdf.parse(date));
            } catch (ParseException e) {
                e.printStackTrace();
            }

            if (temp == true){
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
            startActivity(appointmentsIntent);}
            else
                Toast.makeText(ApptRequest.this,"Appointments can't be made for previous days",Toast.LENGTH_LONG).show();
        }

        else if (view.getId() == R.id.backButton)
        {
            Intent prev = new Intent(this, Appointments.class);
            startActivity(prev);
        }

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
        c.set(Calendar.MONTH, month);
        c.set(Calendar.DAY_OF_MONTH, dayOfMonth);

        String currentDateString = DateFormat.getDateInstance(DateFormat.FULL).format(c.getTime());
        TextView textView = findViewById(R.id.dateText);
        dateButton.setText(currentDateString);

        Date date = c.getTime();

        String key = String.format("%d-%d-%d", date.getMonth() + 1, date.getDate(), date.getYear() + 1900);
        textView.setText(String.format("Date: %s", key));


        appointmentManager = new AppointmentManager(key);

        FirebaseDatabase.getInstance().getReference("ClinicHours").child(key).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                appointmentManager.createTimesOpen(snapshot.getValue(Float.class));

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

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}