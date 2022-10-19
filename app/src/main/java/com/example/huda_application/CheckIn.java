package com.example.huda_application;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.StrictMode;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.Date;
import java.util.Properties;
import java.util.regex.Pattern;

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


public class CheckIn extends AppCompatActivity implements View.OnClickListener
{

    private static final Pattern DATE_PATTERN = Pattern.compile(
            "^((0[1-9]|1[012])-(0[1-9]|[12][0-9]|3[01])-(19|2[0-9])[0-9]{2})$"); // date pattern match

    private static final Pattern TIME_PATTERN = Pattern.compile("(1[012]|[1-9]):[0-5][0-9](\\s)?(?i)(am|pm)");

    private EditText fullname, Birthday, email, time;
    private Button checkin;
    private String smtp = "mail.smtp.";
    private String sender_email = "appclinichuda@gmail.com";
    private String sender_password = "ldjtuhzvtetjofld";
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
            StrictMode.ThreadPolicy threads = new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(threads);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_check_in);

        fullname = findViewById(R.id.FullName);
        Birthday = findViewById(R.id.birthday);
        email = findViewById(R.id.email_checkin);
        time = findViewById(R.id.appointmentTime);
        checkin = findViewById(R.id.checkin);

        fullname.setOnClickListener(this);
        Birthday.setOnClickListener(this);
        email.setOnClickListener(this);
        time.setOnClickListener(this);
        checkin.setOnClickListener(this);

    }

    @Override
    public void onClick(View view)
    {
        if (view.getId() == R.id.checkin)
        {
            try
            {
                sendemail();
            } catch (MessagingException e)
            {
                e.printStackTrace();
            }

            Intent Patients = new Intent(this ,PatientsPage.class);
            startActivity(Patients);
        }
    }

    private void sendemail() throws MessagingException
    {
        final String sender_username = "appclinichuda@gmail.com";

        String fullNameTxt = fullname.getText().toString();
        String birthdayTxt = Birthday.getText().toString();
        String emailTxt = email.getText().toString();
        String timeTxt = time.getText().toString();

        if(TextUtils.isEmpty(fullNameTxt) || fullNameTxt.length() > 30)
        {
            Toast.makeText(CheckIn.this,"Full name cannot be empty",Toast.LENGTH_LONG).show();
            fullname.setError("Name is required");
            fullname.requestFocus();
        }
        else if(TextUtils.isEmpty(birthdayTxt) || !DATE_PATTERN.matcher(birthdayTxt).matches())
        {
            Toast.makeText(CheckIn.this,"Birthday cannot be empty",Toast.LENGTH_LONG).show();
            Birthday.setError("Birthday is required");
            Birthday.requestFocus();
        }
        else if(TextUtils.isEmpty(emailTxt) || !Patterns.EMAIL_ADDRESS.matcher(emailTxt).matches())
        {
            Toast.makeText(CheckIn.this,"Email cannot be empty",Toast.LENGTH_LONG).show();
            email.setError("Email is required");
            email.requestFocus();
        }
        else if(TextUtils.isEmpty(timeTxt) || !TIME_PATTERN.matcher(timeTxt).matches())
        {
            Toast.makeText(CheckIn.this,"Time cannot be empty",Toast.LENGTH_LONG).show();
            time.setError("Time is required");
            time.requestFocus();
        }
        else
        {
            InternetAddress s_sender = new InternetAddress(sender_email);
            InternetAddress receiver = new InternetAddress("ali.bilal.said@gmail.com");
            final String message_text = "Patient: " + fullNameTxt + "Birthday: " + birthdayTxt + " is checking in for appointment at " + timeTxt + ". The patient Email is: " + emailTxt + ".";
            Properties settings = Settings(smtp);


            Session pass_auth = Session.getInstance(settings, new Authenticator() {
                @Override
                protected PasswordAuthentication getPasswordAuthentication() {
                    return new PasswordAuthentication(sender_email, sender_password);
                }
            });

            Message f_email = sendmimmessage(pass_auth, s_sender , receiver, "order", message_text);

            Transport.send(f_email);


            Toast.makeText(CheckIn.this,"Check-in SuccessFull! Thank You",Toast.LENGTH_LONG).show();
        }

    }

    private Properties Settings(String smtp)
    {
        String smtp_gmail ="smtp.gmail.com";

        Properties settings = new Properties();

        settings.put(smtp+"auth", "true");

        settings.put(smtp+"starttls.enable", "true");

        settings.put(smtp+"host", smtp_gmail);
        settings.put(smtp+"port", "587");
        return settings;

    }

    private static Message sendmimmessage(Session f_email, InternetAddress s_sender, InternetAddress receiver, String order, String message_text) throws MessagingException {
        Message mimmessage = new MimeMessage(f_email);

        mimmessage.setFrom(s_sender);

        mimmessage.setSentDate(new Date());

        mimmessage.setRecipient(Message.RecipientType.TO,  receiver);

        mimmessage.setText(message_text);

        mimmessage.setSubject("Patient Check-in");

            return mimmessage;
    }
}