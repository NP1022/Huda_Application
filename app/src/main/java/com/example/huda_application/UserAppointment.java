package com.example.huda_application;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.StrictMode;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.huda_application.firebase.FirebaseClient;
import com.example.huda_application.user.Appointment;
import com.example.huda_application.user.AppointmentStatus;
import com.example.huda_application.user.User;
import com.example.huda_application.user.UserType;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.SimpleDateFormat;
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

import java.util.ArrayList;
import java.util.List;

public class UserAppointment extends AppCompatActivity implements View.OnClickListener {
                                                                                // Appointments page for the Admin of the application which includes the recycle view
                                                                                // The Appointments page will show the appointments for the Current patient
                                                                                // and allow then to Approve or Deny an appointment
    private AppointmentViewAdapter viewAdapter;
    private ImageView backbutton;
    private User updatedUser;
    private String smtp = "mail.smtp.";
    private String sender_email = "appclinichuda@gmail.com";
    private String sender_password = "ldjtuhzvtetjofld";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        int SDK_INT = android.os.Build.VERSION.SDK_INT;
        if (SDK_INT > 8)
        {
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder()               // Use of more than 1 Thread in the application
                    .permitAll().build();
            StrictMode.setThreadPolicy(policy);


        }
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_appointment);

        Intent intent = getIntent();
        User user = (User) intent.getSerializableExtra("user");                    //setting up recycle view equal to the recycle view in the UI
                                                                                                    // and that recycle view will be passed into the adapater

        TextView name = findViewById(R.id.firstName);


        name.setText(String.format("%s %s", user.getFirstName(), user.getLastName()));


        RecyclerView recyclerView = findViewById(R.id.appointmentsView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);
        backbutton = findViewById(R.id.backButton_8);
        FirebaseDatabase.getInstance().getReference("User").child(user.getUserId()).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                updatedUser = FirebaseClient.convertToUser(snapshot);
                viewAdapter = new AppointmentViewAdapter(UserAppointment.this, updatedUser);      // Firebase call to get all the updated changes to the user that is logg
                                                                                                                    // pass the User object to the appointment Adapter to display the appointments
                recyclerView.setAdapter(viewAdapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error)
            {

            }
        });
        backbutton.setOnClickListener(this);
    }

    @Override
    public void onClick(View v)
    {
        if (v.getId() == R.id.backButton_8)
            startActivity(new Intent(UserAppointment.this , AdminPage.class));      // Back buttons for the application
    }

    private class AppointmentViewAdapter extends RecyclerView.Adapter<AppointmentViewHolder>
    {

        private LayoutInflater inflater;                                         // Variables used by adapter to show the items in the recycler view in the application.

        private final User user;

        public AppointmentViewAdapter(Context context, User user)
        {
            this.user = user;
            this.inflater = LayoutInflater.from(context);                       // Constructor used to set the variables equal to the parameters for the recycler adapter
        }

        @NonNull
        @Override
        public AppointmentViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view = inflater.inflate(R.layout.appointment_user_item, parent, false);// over ride method to check the view of the Adapter for the recycle view

            return new AppointmentViewHolder(view);
        }

        @Override
        public void onBindViewHolder(@NonNull AppointmentViewHolder holder, int position) {
            Appointment appointment = user.getAppointments().get(position);
            holder.time.setText(appointment.getTime());
            holder.date.setText(appointment.getDate());                                             // Holder for each Item that is used in the recycle view of page which use the holder object
            holder.status.setText(appointment.getStatus().name().toLowerCase());
            holder.Reason.setText(appointment.getReason());
            holder.checkedIntimeText.setText(appointment.getCheckedInTime());

            holder.checkedIn.setVisibility(View.GONE);
            holder.checkedInText.setVisibility(View.GONE);
            if(appointment.getStatus() == AppointmentStatus.APPROVED)
            {
                holder.approve.setVisibility(View.GONE);
                holder.deny.setVisibility(View.GONE);                                   // If status is approved the following buttons will be gone from the view and
                                                                                        // the check-in text will show in the application


                holder.checkedIn.setText(appointment.isCheckedIn() ? "Yes" : "No");
                holder.checkedIn.setVisibility(View.VISIBLE);
                holder.checkedInText.setVisibility(View.VISIBLE);
            }


            if(appointment.getStatus() == AppointmentStatus.DENIED )            // If Status is denied the approve and deny button will be gone
            {
                holder.approve.setVisibility(View.GONE);
                holder.deny.setVisibility(View.GONE);

            }
            if(appointment.getStatus() == AppointmentStatus.PENDING )           // If status is pending the approve and denied will show
            {
                holder.approve.setVisibility(View.VISIBLE);
                holder.deny.setVisibility(View.VISIBLE);

            }
            if(appointment.getStatus() == AppointmentStatus.CANCELED )
            {                                                                   // if status is canceled the approve and deny will be gone from the Item of the Item
                holder.approve.setVisibility(View.GONE);
                holder.deny.setVisibility(View.GONE);
            }
            if(!appointment.isCheckedIn() )
            {
                holder.checkedIntime.setVisibility(View.GONE);                  // if the appointment is checked in then the checkin time text is gone
                holder.checkedIntimeText.setVisibility(View.GONE);              // Also the checkin text with the time is shown in the application

            }

            holder.approve.setOnClickListener(listener ->
            {
                user.getAppointments().get(position).setStatus(AppointmentStatus.APPROVED);
                SimpleDateFormat date = new SimpleDateFormat("MM-d-yyyy HH:mm z");

                String currentDateAndTime = date.format(new Date());
                user.getAppointments().get(position).setAdminActionTime(currentDateAndTime);        // Gets the current time that is used and then stores it with the appointment
                FirebaseClient.updateUser(user);                                                    // that is being made for and then it updates the User in the database


                holder.approve.setVisibility(View.GONE);
                holder.deny.setVisibility(View.GONE);                                               // The Approve and deny button will be set to gone from the application
                holder.checkedIn.setText(appointment.isCheckedIn() ? "Yes" : "No");                 // It was show if the patient is checkin in or no depending on the status
                holder.checkedIn.setVisibility(View.VISIBLE);
                holder.checkedInText.setVisibility(View.VISIBLE);


                holder.status.setText(appointment.getStatus().name().toLowerCase());

                try
                {
                    sendemail(user.getAppointments().get(position).getDate(), user.getAppointments().get(position).getTime() , "approved"); // Used to send the email to the patient after the patient approves the application
                } catch (MessagingException e) {
                    e.printStackTrace();
                }
                Toast.makeText(UserAppointment.this,"Appointment has been successfully accepted",Toast.LENGTH_LONG).show();
            });

            holder.deny.setOnClickListener(listener ->
            {
                user.getAppointments().get(position).setStatus(AppointmentStatus.DENIED);
                SimpleDateFormat date2 = new SimpleDateFormat("MM-d-yyyy HH:mm z");

                String currentDateAndTime2 = date2.format(new Date());                                  // If the Admin declines the appointment the current time
                                                                                                        // for the appointment will be stored to show the patient that it was denied
                user.getAppointments().get(position).setAdminActionTime(currentDateAndTime2);
                FirebaseClient.updateUser(user);

                holder.approve.setVisibility(View.GONE);                                                // The visitbility of the approve and deny will be gone from the
                                                                                                        // view and the status of the appointment will change in the Item
                holder.deny.setVisibility(View.GONE);
                holder.status.setText(appointment.getStatus().name().toLowerCase());
                try
                {
                    sendemail(user.getAppointments().get(position).getDate(), user.getAppointments().get(position).getTime() , "denied"); // The call for send email function in the application
                }
                catch (MessagingException e)
                {
                    e.printStackTrace();
                }
                Toast.makeText(UserAppointment.this,"Appointment has been denied",Toast.LENGTH_LONG).show();
            });
        }

        @Override
        public int getItemCount() {
            return user.getAppointments().size();
        }                                           // returns the amount of items in the adapter that is being called

        @Override
        public int getItemViewType(int position) {
            return position;
        }
    }

    public static class AppointmentViewHolder extends RecyclerView.ViewHolder {

        private final TextView time;
        private final TextView date;
        private final TextView status;                                                  // Holder variables to set equal to the position that is found on the UI of the
                                                                                        // application Each one of the holder variables will be connected to
                                                                                        //the Item list that is used in the recycle view of the application

        private final TextView Reason;
        private final TextView checkedIn;
        private final TextView checkedInText;

        private final AppCompatButton approve;
        private final AppCompatButton deny;
        private final TextView checkedIntime;
        private final TextView checkedIntimeText;

        public AppointmentViewHolder(@NonNull View itemView) {
            super(itemView);
            time = itemView.findViewById(R.id.time2);
            date = itemView.findViewById(R.id.appointmentDate2);
            status = itemView.findViewById(R.id.Status2);
            Reason = itemView.findViewById(R.id.Reason2);
            approve = itemView.findViewById(R.id.Approve2);                             //Setting the holder variables equal to the ID of the item in the UI
            deny = itemView.findViewById(R.id.decline2);                                // Each of the Items are in the recycle view Item
            checkedIn = itemView.findViewById(R.id.checkedIn2);
            checkedInText = itemView.findViewById(R.id.checkedInText2);
            checkedIntimeText = itemView.findViewById(R.id.CheckinTimeText2);
            checkedIntime = itemView.findViewById(R.id.CheckinTime2);
        }

        public TextView getTime() {
            return time;
        }

        public TextView getDate() {
            return date;
        }

        public TextView getStatus() {
            return status;
        }

        public TextView getReason() {
            return Reason;
        }

        public TextView getCheckedIn() {
            return checkedIn;
        }

        public TextView getCheckedInText() {
            return checkedInText;
        }
    }
    private  void sendemail(String Date, String Time , String Status) throws MessagingException
    {                                                                                               // Sending an email function for the applciation which uses the set email that the clinic
                                                                                                    // uses also it takes the message of the email with the reciever email which is taken from the user
        final String sender_username = "appclinichuda@gmail.com";

        String Fullname_text = updatedUser.getFirstName() + " " + updatedUser.getLastName();
        String birthday_text = updatedUser.getBirthday();                                       // Used variables to get the current user information
        String email_text = updatedUser.getEmailAddress();


        InternetAddress s_sender = new InternetAddress(sender_email);
        InternetAddress reciever = new InternetAddress(email_text);                             // Message that is being sent to the clinic
        final String message_text = "Dear "+ Fullname_text + ",\n\n"+ "Appointment for Patient: " + Fullname_text + " Birthday: " + birthday_text + " has been "+Status+".\n\n" + "Appointment Date: " + Date+ "\nAppointment Time: " + Time+"\n\nFor any Questions please Contact Us at (313) 865-8446" ;
        Properties settings = Settings(smtp);




        Session pass_auth = Session.getInstance(settings, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                                                                                        // Authentication of the email and password for the email that is being used to email

                return new PasswordAuthentication(sender_email, sender_password);

            }
        });

        Message f_email = sendmimmessage(pass_auth, s_sender , reciever, "order", message_text);

        Transport.send(f_email);



    }

    private Properties Settings(String smtp) {
        String smtp_gmail ="smtp.gmail.com";

        Properties settings = new Properties();


        settings.put(smtp+"auth", "true");              // settings for email sending with the port being used

        settings.put(smtp+"starttls.enable", "true");

        settings.put(smtp+"host", smtp_gmail);
        settings.put(smtp+"port", "587");
        return settings;

    }

    private static Message sendmimmessage(Session f_email, InternetAddress s_sender, InternetAddress reciever, String order, String message_text) throws MessagingException {
        Message mimmessage = new MimeMessage(f_email);




        mimmessage.setFrom(s_sender);


        mimmessage.setSentDate(new Date());                                             // mim message function to send the function used gmail to send the emails the function will take the text of the
                                                                                        // message and also the sender email with the session that the authentication happens with

        mimmessage.setRecipient(Message.RecipientType.TO,  reciever);

        mimmessage.setText(message_text);

        mimmessage.setSubject("Patient Appointment Status");


        return mimmessage;

    }
}