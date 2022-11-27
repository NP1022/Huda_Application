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
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder()
                    .permitAll().build();
            StrictMode.setThreadPolicy(policy);


        }
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_appointment);

        Intent intent = getIntent();
        User user = (User) intent.getSerializableExtra("user");

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
                viewAdapter = new AppointmentViewAdapter(UserAppointment.this, updatedUser);
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
            startActivity(new Intent(UserAppointment.this , AdminPage.class));
    }

    private class AppointmentViewAdapter extends RecyclerView.Adapter<AppointmentViewHolder>
    {

        private LayoutInflater inflater;
        private final User user;

        public AppointmentViewAdapter(Context context, User user)
        {
            this.user = user;
            this.inflater = LayoutInflater.from(context);
        }

        @NonNull
        @Override
        public AppointmentViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view = inflater.inflate(R.layout.appointment_user_item, parent, false);
            return new AppointmentViewHolder(view);
        }

        @Override
        public void onBindViewHolder(@NonNull AppointmentViewHolder holder, int position) {
            Appointment appointment = user.getAppointments().get(position);
            holder.time.setText(appointment.getTime());
            holder.date.setText(appointment.getDate());
            holder.status.setText(appointment.getStatus().name().toLowerCase());
            holder.Reason.setText(appointment.getReason());
            holder.checkedIntimeText.setText(appointment.getCheckedInTime());

            holder.checkedIn.setVisibility(View.GONE);
            holder.checkedInText.setVisibility(View.GONE);
            if(appointment.getStatus() == AppointmentStatus.APPROVED)
            {
                holder.approve.setVisibility(View.GONE);
                holder.deny.setVisibility(View.GONE);


                holder.checkedIn.setText(appointment.isCheckedIn() ? "Yes" : "No");
                holder.checkedIn.setVisibility(View.VISIBLE);
                holder.checkedInText.setVisibility(View.VISIBLE);
            }


            if(appointment.getStatus() == AppointmentStatus.DENIED )
            {
                holder.approve.setVisibility(View.GONE);
                holder.deny.setVisibility(View.GONE);

            }
            if(appointment.getStatus() == AppointmentStatus.PENDING )
            {
                holder.approve.setVisibility(View.VISIBLE);
                holder.deny.setVisibility(View.VISIBLE);

            }
            if(appointment.getStatus() == AppointmentStatus.CANCELED )
            {
                holder.approve.setVisibility(View.GONE);
                holder.deny.setVisibility(View.GONE);
            }
            if(!appointment.isCheckedIn() )
            {
                holder.checkedIntime.setVisibility(View.GONE);
                holder.checkedIntimeText.setVisibility(View.GONE);

            }

            holder.approve.setOnClickListener(listener ->
            {
                user.getAppointments().get(position).setStatus(AppointmentStatus.APPROVED);
                SimpleDateFormat date = new SimpleDateFormat("MM-d-yyyy HH:mm z");

                String currentDateAndTime = date.format(new Date());
                user.getAppointments().get(position).setAdminActionTime(currentDateAndTime);
                FirebaseClient.updateUser(user);
                holder.approve.setVisibility(View.GONE);
                holder.deny.setVisibility(View.GONE);
                holder.checkedIn.setText(appointment.isCheckedIn() ? "Yes" : "No");
                holder.checkedIn.setVisibility(View.VISIBLE);
                holder.checkedInText.setVisibility(View.VISIBLE);


                holder.status.setText(appointment.getStatus().name().toLowerCase());

                try
                {
                    sendemail(user.getAppointments().get(position).getDate(), user.getAppointments().get(position).getTime() , "approved");
                } catch (MessagingException e) {
                    e.printStackTrace();
                }
                Toast.makeText(UserAppointment.this,"Appointment has been successfully accepted",Toast.LENGTH_LONG).show();
            });

            holder.deny.setOnClickListener(listener ->
            {
                user.getAppointments().get(position).setStatus(AppointmentStatus.DENIED);
                SimpleDateFormat date2 = new SimpleDateFormat("MM-d-yyyy HH:mm z");

                String currentDateAndTime2 = date2.format(new Date());
                user.getAppointments().get(position).setAdminActionTime(currentDateAndTime2);
                FirebaseClient.updateUser(user);

                holder.approve.setVisibility(View.GONE);
                holder.deny.setVisibility(View.GONE);
                holder.status.setText(appointment.getStatus().name().toLowerCase());
                try
                {
                    sendemail(user.getAppointments().get(position).getDate(), user.getAppointments().get(position).getTime() , "denied");
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
        }
    }

    public static class AppointmentViewHolder extends RecyclerView.ViewHolder {

        private final TextView time;
        private final TextView date;
        private final TextView status;
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
            approve = itemView.findViewById(R.id.Approve2);
            deny = itemView.findViewById(R.id.decline2);
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
    {
        final String sender_username = "appclinichuda@gmail.com";

        String Fullname_text = updatedUser.getFirstName() + " " + updatedUser.getLastName();
        String birthday_text = updatedUser.getBirthday();
        String email_text = updatedUser.getEmailAddress();


        InternetAddress s_sender = new InternetAddress(sender_email);
        InternetAddress reciever = new InternetAddress(email_text);
        final String message_text = "Dear "+ Fullname_text + ",\n\n"+ "Appointment for Patient: " + Fullname_text + " Birthday: " + birthday_text + " has been "+Status+".\n\n" + "Appointment Date: " + Date+ "\nAppointment Time: " + Time+"\n\nFor any Questions please Contact Us at (313) 865-8446" ;
        Properties settings = Settings(smtp);




        Session pass_auth = Session.getInstance(settings, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {




                return new PasswordAuthentication(sender_email, sender_password);



            }
        });

        Message f_email = sendmimmessage(pass_auth, s_sender , reciever, "order", message_text);

        Transport.send(f_email);



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

        mimmessage.setSubject("Patient Appointment Status");


        return mimmessage;

    }
}