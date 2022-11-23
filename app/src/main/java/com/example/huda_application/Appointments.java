package com.example.huda_application;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
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

import com.example.huda_application.appointment.AppointmentManager;
import com.example.huda_application.firebase.FirebaseClient;
import com.example.huda_application.user.Appointment;
import com.example.huda_application.user.AppointmentStatus;
import com.example.huda_application.user.User;
import com.example.huda_application.user.UserManager;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;



import java.util.List;
import java.util.Locale;
import java.util.Properties;
import java.util.stream.Collectors;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class Appointments extends AppCompatActivity implements View.OnClickListener {

    private AppointmentViewAdapter viewAdapter;
    private ImageView backButton;
    private String smtp = "mail.smtp.";
    private String sender_email = "appclinichuda@gmail.com";
    private String sender_password = "ldjtuhzvtetjofld";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        int SDK_INT = android.os.Build.VERSION.SDK_INT;
        setContentView(R.layout.activity_appointments);
        if (SDK_INT > 8)
        {
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder()
                    .permitAll().build();
            StrictMode.setThreadPolicy(policy);


        }

        RecyclerView recyclerView = findViewById(R.id.appointmentsView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        FirebaseDatabase.getInstance().getReference("User").child(UserManager.getInstance().getCurrentUser().getUserId())
                .addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        User user = FirebaseClient.convertToUser(snapshot);
                        viewAdapter = new AppointmentViewAdapter(Appointments.this, user);
                        recyclerView.setAdapter(viewAdapter);
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {
                    }
                });

        AppCompatButton createButton = findViewById(R.id.create);

        createButton.setOnClickListener(view -> {
            Intent createAppointmentIntent = new Intent(this, ApptRequest.class);
            startActivity(createAppointmentIntent);

        });

        backButton = findViewById(R.id.backButton);
        backButton.setOnClickListener(this);


    }

    @Override
    public void onClick(View view) {
        if(view.getId() == R.id.backButton)
        {

            Intent Main = new Intent(this ,MainApplication.class);
            startActivity(Main);

        }
    }


    private class AppointmentViewAdapter extends RecyclerView.Adapter<Appointments.AppointmentViewHolder> {

        private LayoutInflater inflater;
        private List<Appointment> appointments;
        private User user;

        public AppointmentViewAdapter(Context context, User user) {
            this.inflater = LayoutInflater.from(context);
            this.user = user;
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                appointments = user.getAppointments().stream()
                        .filter(appointment -> appointment.getStatus() != AppointmentStatus.CANCELED).collect(Collectors.toList());
            }
        }

        @NonNull
        @Override
        public AppointmentViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view = inflater.inflate(R.layout.appointment_list_item, parent, false);
            return new AppointmentViewHolder(view);
        }

        @Override
        public void onBindViewHolder(@NonNull AppointmentViewHolder holder, int position) {
            Appointment appointment = appointments.get(position);
            holder.time.setText(appointment.getTime());
            holder.date.setText(appointment.getDate());
            holder.status.setText(appointment.getStatus().name().toLowerCase());

            holder.checkedInText.setVisibility(View.GONE);
            if (appointment.getStatus() != AppointmentStatus.PENDING) {
                holder.cancel.setVisibility(View.GONE);
            }

            if (appointment.getStatus() != AppointmentStatus.APPROVED || appointment.isCheckedIn()) {
                holder.checkInButton.setVisibility(View.GONE);

            }

            if (appointment.isCheckedIn()) {
                holder.checkedInText.setVisibility(View.VISIBLE);
            }

            holder.cancel.setOnClickListener(view -> {
                for (Appointment userAppointment : user.getAppointments()) {
                    if (userAppointment == appointment) {
                        userAppointment.setStatus(AppointmentStatus.CANCELED);
                        AppointmentManager.deleteAppointment(appointment.getDate(),appointment.getTime());
                    }

                }

                appointments.get(position).setStatus(AppointmentStatus.CANCELED);
                holder.status.setText(appointment.getStatus().name().toLowerCase());
                FirebaseClient.updateUser(user);
                UserManager.getInstance().setCurrentUser(user);
            });

            holder.checkInButton.setOnClickListener(view -> {
                String currentDate = new SimpleDateFormat("MM-d-yyyy", Locale.getDefault()).format(new Date());
                System.out.println(currentDate);

                String currentTime = new SimpleDateFormat("h:mm a", Locale.getDefault()).format(new Date());

                System.out.println(currentTime);


                System.out.println(appointment.getTime().toUpperCase());
                String appointment_time = appointment.getTime().toUpperCase();

                System.out.println(appointment.getDate());
                if(appointment.getDate().equals(currentDate))
                {
                    if(currentTime.substring(currentTime.length()-2).equals(appointment_time.substring(appointment_time.length()-2))){
                        String startTime = currentTime.substring(0,4);
                        String endTime = appointment_time.substring(0,4);
                        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
                        Date d1 = null;
                        try {
                            d1 = sdf.parse(startTime);
                        } catch (ParseException e) {
                            e.printStackTrace();
                        }
                        Date d2 = null;
                        try {
                            d2 = sdf.parse(endTime);
                        } catch (ParseException e) {
                            e.printStackTrace();
                        }
                        long elapsed = d2.getTime() - d1.getTime();
                        System.out.println(elapsed);
                        if (elapsed < 1500000) {

                        }

                    }

                    appointments.get(position).setCheckedIn(true);
                    holder.cancel.setVisibility(View.GONE);
                    holder.checkInButton.setVisibility(View.GONE);
                    holder.checkedInText.setVisibility(View.VISIBLE);
                    FirebaseClient.updateUser(user);
                    UserManager.getInstance().setCurrentUser(user);
                    try {
                        sendemail(appointment.getDate(), appointment.getTime() );
                    } catch (MessagingException e) {
                        e.printStackTrace();
                    }
                }
                else
                    Toast.makeText(Appointments.this,"Check-in on allowed on the same date! ",Toast.LENGTH_LONG).show();
            });
        }

        @Override
        public int getItemCount() {
            return appointments.size();
        }
    }

    private static class AppointmentViewHolder extends RecyclerView.ViewHolder {

        private final TextView time;
        private final TextView date;
        private final TextView status;
        private final TextView checkedInText;
        private final AppCompatButton cancel;
        private final AppCompatButton checkInButton;

        public AppointmentViewHolder(@NonNull View itemView) {
            super(itemView);
            this.time = itemView.findViewById(R.id.time);
            this.date = itemView.findViewById(R.id.appointmentDate);
            this.status = itemView.findViewById(R.id.Status);
            this.checkedInText = itemView.findViewById(R.id.checkedInText);
            this.cancel = itemView.findViewById(R.id.cancelAppointment);
            this.checkInButton = itemView.findViewById(R.id.checkInAppointment);
        }

        public TextView getDate() {
            return date;
        }

        public TextView getTime() {
            return time;
        }

        public TextView getStatus() {
            return status;
        }

        public AppCompatButton getCancel() {
            return cancel;
        }

        public AppCompatButton getCheckInButton() {
            return checkInButton;
        }

        public TextView getCheckedInText() {
            return checkedInText;
        }

    }
    private  void sendemail(String Date, String Time ) throws MessagingException {
        final String sender_username = "appclinichuda@gmail.com";


        String Fullname_text = UserManager.getInstance().getCurrentUser().getFirstName() + " " + UserManager.getInstance().getCurrentUser().getLastName();
        String birthday_text = UserManager.getInstance().getCurrentUser().getBirthday();
        String email_text = UserManager.getInstance().getCurrentUser().getEmailAddress();


        InternetAddress s_sender = new InternetAddress(sender_email);
        InternetAddress reciever = new InternetAddress("ali.bilal.said@gmail.com");
        final String message_text = "Dear Admin, " + ",\n\n"+ "Patient: " + Fullname_text + " Birthday: " + birthday_text + " is checking in for their appointment"+".\n\n" + "Appointment Date: " + Date+ "\nAppointment Time: " + Time+"\n\nThank You" ;
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