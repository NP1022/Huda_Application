package com.example.huda_application;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.FirebaseDatabase;
import com.wdullaer.materialdatetimepicker.date.DatePickerDialog;

import org.w3c.dom.Text;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class CustomizeTime extends AppCompatActivity implements DatePickerDialog.OnDateSetListener , View.OnClickListener{
    private Button dateButton;
    private TextView textView;
    private TextView  submit;
    private EditText amount;
    private ImageView backButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customize_time);
        textView = findViewById(R.id.dateText2);
        amount = findViewById(R.id.appointmentTime2);
        submit = findViewById(R.id.modify);
        dateButton = findViewById(R.id.dateText2);
        backButton = (ImageView) findViewById(R.id.timebackButton);

        backButton.setOnClickListener(this);



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
        amount.setOnClickListener(this);
        submit.setOnClickListener(this);
    }

    @Override
    public void onDateSet(DatePickerDialog view, int year, int monthOfYear, int dayOfMonth) {

            Calendar c = Calendar.getInstance();
            c.set(Calendar.YEAR, year);
            c.set(Calendar.MONTH, monthOfYear);
            c.set(Calendar.DAY_OF_MONTH, dayOfMonth);

            String currentDateString = DateFormat.getDateInstance(DateFormat.FULL).format(c.getTime());
            dateButton.setText(currentDateString);

            Date date = c.getTime();

            String key = String.format("%d-%d-%d", date.getMonth() + 1, date.getDate(), date.getYear() + 1900);
            textView.setText(key);
    }

    @Override
    public void onClick(View v) {

        if(v.getId() == R.id.modify){
            try {
                float hours = Float.parseFloat(amount.getText().toString());
                FirebaseDatabase.getInstance().getReference("ClinicHours").child(textView.getText().toString()).setValue(hours);
                Toast.makeText(CustomizeTime.this,"Hours Of Operation have been changed. ",Toast.LENGTH_SHORT).show();
                startActivity(new Intent(this , Adminpanel.class));

            }catch (NumberFormatException e) {
                //TODO Show dialog telling admins that the entered hours is invalid
            }

        }
        else if (v.getId() == R.id.timebackButton){
            startActivity(new Intent(this , Adminpanel.class));
        }
    }
}