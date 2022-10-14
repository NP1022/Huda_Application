package com.example.huda_application;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.StrictMode;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.Date;
import java.util.Properties;

import javax.mail.Address;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;


public class CheckIn extends AppCompatActivity implements View.OnClickListener {

    private EditText fullname, Birthday, email, time;
    private Button checkin;
    private String smtp = "mail.smtp.";
    private String sender_email = "appclinichuda@gmail.com";
    private String sender_password = "ldjtuhzvtetjofld";
    @Override
    protected void onCreate(Bundle savedInstanceState) {


            StrictMode.ThreadPolicy threads = new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(threads);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_check_in);

        fullname = findViewById(R.id.FullName);
        Birthday = findViewById(R.id.birthday);
        email = findViewById(R.id.email_checkin);
        time = findViewById(R.id.appoitmentTime);
        checkin = findViewById(R.id.checkin);

        fullname.setOnClickListener(this);
        Birthday.setOnClickListener(this);
        email.setOnClickListener(this);
        time.setOnClickListener(this);
        checkin.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.checkin) {

            try {
                sendemail();
            } catch (MessagingException e) {
                e.printStackTrace();
            }

            Intent Patients = new Intent(this ,PatientsPage.class);
            startActivity(Patients);

        }


    }

    private void sendemail() throws MessagingException {
        final String sender_username = "appclinichuda@gmail.com";


        String Fullname_text = fullname.getText().toString();
        String birthday_text = Birthday.getText().toString();
        String email_text = email.getText().toString();
        String time_text = time.getText().toString();

        InternetAddress s_sender = new InternetAddress("ali.bilal.said@gmail.com");
        InternetAddress f_from = new InternetAddress(sender_email);
        final String message_text = "Patient: " + Fullname_text + "Birthday: " + birthday_text + " is checking in for appointment at " + time_text + ". The patient Email is: " + email_text + ".";
        Properties settings = Settings(smtp);




        Session pass_auth = Session.getInstance(settings, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {




                return new PasswordAuthentication(sender_email, sender_password);



            }
        });

        Message f_email = sendmimmessage(pass_auth, s_sender , f_from, "order", message_text);

        Transport.send(f_email);


        Toast.makeText(CheckIn.this,"Check-in SuccessFull! Thank You",Toast.LENGTH_LONG).show();
    }

    private Properties Settings(String smtp) {
        String smtp_gmail ="smtp.gmail.com";

        Properties settings = new Properties();


        settings.put(smtp+"auth", "true");

        settings.put(smtp+"starttls.enable", "true");

        settings.put(smtp+"host", smtp_gmail);
        settings.put(smtp+"port", "587");
        return settings;

    }

    private static Message sendmimmessage(Session f_email, InternetAddress s_sender, InternetAddress reciever, String order, String message_text) throws MessagingException {
        Message mimmessage = new MimeMessage(f_email);




        mimmessage.setFrom(s_sender);


        mimmessage.setSentDate(new Date());

        mimmessage.setRecipient(Message.RecipientType.TO,  reciever);

        mimmessage.setText(message_text);

        mimmessage.setSubject("Patient Check-in");


            return mimmessage;

    }
}