package com.example.huda_application;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.example.huda_application.firebase.FirebaseClient;
import com.example.huda_application.user.Appointment;
import com.example.huda_application.user.AppointmentStatus;
import com.example.huda_application.user.UserManager;

import java.util.regex.Pattern;

public class CheckIn extends AppCompatActivity implements View.OnClickListener {

    private static final Pattern DATE_PATTERN = Pattern.compile(
            "^((0[1-9]|1[012])-(0[1-9]|[12][0-9]|3[01])-(19|2[0-9])[0-9]{2})$"); // date pattern match

    private static final Pattern TIME_PATTERN = Pattern.compile("(1[012]|[1-9]):[0-5][0-9](\\s)?(?i)(am|pm)");

    private EditText date, birthday, reason, time;
    private Button checkin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_check_in);

        date = findViewById(R.id.FullName);
        birthday = findViewById(R.id.birthday);
        reason = findViewById(R.id.email_checkin);
        time = findViewById(R.id.appointmentTime);
        checkin = findViewById(R.id.checkin);

        date.setOnClickListener(this);
        birthday.setOnClickListener(this);
        reason.setOnClickListener(this);
        time.setOnClickListener(this);
        checkin.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.checkin) {
            
            Appointment appointment = new Appointment(
                    time.getText().toString(),
                    date.getText().toString(),
                    reason.getText().toString(),
                    AppointmentStatus.PENDING
            );

            UserManager.getInstance().getCurrentUser().addAppointment(appointment);
            FirebaseClient.updateUser(UserManager.getInstance().getCurrentUser());
            Intent appointmentsIntent = new Intent(this, Appointments.class);
            startActivity(appointmentsIntent);
        }
    }
}