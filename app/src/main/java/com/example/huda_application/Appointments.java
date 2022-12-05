package com.example.huda_application;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.StrictMode;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
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
                                                                                        // Appointments page for the patient of the application which includes the recycle view
                                                                                        // The Appointments page will show the appointments for the Current patient and allow then to cancel or check-in for an appointment
    private AppointmentViewAdapter2 viewAdapter;
    private ImageView backButton;
    private String smtp = "mail.smtp.";
    private String sender_email = "appclinichuda@gmail.com";
    private String sender_password = "ldjtuhzvtetjofld";
    private Button callClinic;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        int SDK_INT = android.os.Build.VERSION.SDK_INT;

        if (SDK_INT > 8)
        {                                                                                           // Use of more than 1 Thread in the application the following permits the use of more than 1 thread
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder()
                    .permitAll().build();
            StrictMode.setThreadPolicy(policy);
        }
        super.onCreate(savedInstanceState);
        Saved_language();

        setContentView(R.layout.activity_appointments);

        RecyclerView recyclerView = findViewById(R.id.appointmentsView);                           //setting up recycle view equal to the recycle view in the UI
                                                                                                    // and that recycle view will be passed into the adapater
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        FirebaseDatabase.getInstance().getReference("User").child(UserManager.getInstance().getCurrentUser().getUserId())
                .addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        User user = FirebaseClient.convertToUser(snapshot);                              // Firebase call to get all the updated changes to the user that is logged into the application
                        viewAdapter = new AppointmentViewAdapter2(Appointments.this, user);       // pass the User object to the appointment Adapter to display the appointments
                        recyclerView.setAdapter(viewAdapter);
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {
                    }
                });

        AppCompatButton createButton = findViewById(R.id.create);

        createButton.setOnClickListener(view -> {
            Intent createAppointmentIntent = new Intent(this, ApptRequest.class);               // create button used to take the patient to the appointment request page
            startActivity(createAppointmentIntent);

        });

        backButton = findViewById(R.id.backButton);
        backButton.setOnClickListener(this);
                                                                    // Back and call clinic buttons for the application
        callClinic = findViewById(R.id.callClinic2);
        callClinic.setOnClickListener(this);


    }

    private void picklanguage(String l)
    {
        SharedPreferences.Editor Saver = getSharedPreferences("langauge", MODE_MULTI_PROCESS).edit();
        Locale language_option =  new Locale(l);
        DisplayMetrics metrics =  getBaseContext().getResources().getDisplayMetrics();                  // Picks the locale after the language is picked from the dialog
        language_swtich(l, metrics, language_option);

        Saver.putString("prev_language" ,l);
        Saver.apply();
    }
    public void language_swtich(String l , DisplayMetrics m , Locale lang) {

        Locale.setDefault(lang);

        Configuration page = new Configuration();                                                   // Switch the language
        page.locale = lang;

        getBaseContext().getResources().updateConfiguration(page, m);

    }


    public void Saved_language(){
        SharedPreferences saved_language =getSharedPreferences("langauge", MODE_MULTI_PROCESS);
        picklanguage(saved_language.getString("prev_language" , ""));
        // choose the saved language from the application
    }
    @Override
    public void onClick(View view) {
        if(view.getId() == R.id.backButton)
        {
            Intent Main = new Intent(this ,MainApplication.class);
            startActivity(Main);
        }
        else if(view.getId() == R.id.callClinic2)                   // On click for the back button and the call clinic button
        {
            Intent HUDACall = new Intent(Intent.ACTION_DIAL);
            HUDACall.setData(Uri.parse("tel:3138658446"));
            startActivity(HUDACall);
        }
    }


    private class AppointmentViewAdapter2 extends RecyclerView.Adapter<AppointmentViewHolder2> {

        private LayoutInflater inflater;
        private List<Appointment> appointments;
        private User user;                                                               // Variables used by adapter to show the items in the recycler view in the application.
        private final Context context;

        public AppointmentViewAdapter2(Context context, User user) {
            this.context= context;

            this.user = user;                                                                   // Constructor used to set the variables equal to the parameters for the recycler adapter
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                appointments = user.getAppointments().stream()
                        .filter(appointment -> appointment.getStatus() != AppointmentStatus.CANCELED).collect(Collectors.toList());     // Filer to take out the appointments that are canceled from the application
            }
            this.inflater = LayoutInflater.from(context);
        }

        @NonNull
        @Override
        public AppointmentViewHolder2 onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view = inflater.inflate(R.layout.appointment_list_item, parent, false);            // over ride method to check the view of the Adapter for the recycle view

            return new AppointmentViewHolder2(view);
        }

        @Override
        public void onBindViewHolder(@NonNull AppointmentViewHolder2 holder, int position) {
            Appointment appointment = appointments.get(position);
            holder.time.setText(appointment.getTime());
            holder.date.setText(appointment.getDate());                                                          // Holder for each Item that is used in the recycle view of page which use the holder object
            holder.status.setText(appointment.getStatus().name().toLowerCase());
            holder.response.setText(appointment.getAdminActionTime());

            holder.checkedInText.setVisibility(View.GONE);
            if (appointment.getStatus() != AppointmentStatus.PENDING) {
                holder.cancel.setVisibility(View.GONE);
            }                                                                                                     //Checking the appointment status and setting the visibility of the items on the recycle view of the application
            if(appointment.getStatus() == AppointmentStatus.DENIED){
                holder.responsestatic.setText("Denied:");
            }
            if (appointment.getStatus() != AppointmentStatus.APPROVED && appointment.getStatus() != AppointmentStatus.DENIED) {
                holder.response.setVisibility(View.GONE);
                holder.responsestatic.setVisibility(View.GONE);
            }
            if (appointment.getStatus() != AppointmentStatus.PENDING) {                             //if the appointment is pending the cancel button will be gone
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

                }                                                                                        // Once an appointment is canceled the status of the appointment changes
                                                                                                         // The User is then updated in the database using the update user function

                appointments.get(position).setStatus(AppointmentStatus.CANCELED);
                holder.status.setText(appointment.getStatus().name().toLowerCase());
                FirebaseClient.updateUser(user);
                UserManager.getInstance().setCurrentUser(user);
            });

            holder.checkInButton.setOnClickListener(view -> {
                String currentDate = new SimpleDateFormat("MM-d-yyyy", Locale.getDefault()).format(new Date());
                System.out.println(currentDate);

                String currentTime = new SimpleDateFormat("h:mm a", Locale.getDefault()).format(new Date());
                                                                                                                        // Check-in Button for the application to check  if the appointment date is equal to current date
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
                            e.printStackTrace();                                                    // Code to check if the the appointment is on the same date to check-in into the appointment
                                                                                                    // If the check-in date is the same as the appointment date then the code for the checkin will execute
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
                    SimpleDateFormat date = new SimpleDateFormat("HH:mm z");

                    String currentDateAndTime = date.format(new Date());

                    appointments.get(position).setCheckedInTime(currentDateAndTime);
                    appointments.get(position).setCheckedIn(true);

                    holder.cancel.setVisibility(View.GONE);
                    holder.checkInButton.setVisibility(View.GONE);                                             // Once the Checkin button is clicked the button will be gone and the text for
                                                                                                               // checkin will show also the user will be updated in firebase using the update user function that is created

                    holder.checkedInText.setVisibility(View.VISIBLE);
                    FirebaseClient.updateUser(user);
                    UserManager.getInstance().setCurrentUser(user);                                           // The email to the admin will be used with the time and date that is brought from the appointment
                    try {
                        sendemail(appointment.getDate(), appointment.getTime() );
                    } catch (MessagingException e) {
                        e.printStackTrace();
                    }
                }
                else
                    Toast.makeText(Appointments.this,"Check-in is only allowed on the same day! ",Toast.LENGTH_LONG).show();
            });
        }

        @Override
        public int getItemCount() {
            return appointments.size();
        }
        @Override
        public int getItemViewType(int position) {
            return position;
        }
    }

    private static class AppointmentViewHolder2 extends RecyclerView.ViewHolder  {

        private final TextView time;
        private final TextView date;
        private final TextView status;                                          // Holder variables to set equal to the position that is found on the UI of the application
        private final TextView checkedInText;
        private final AppCompatButton cancel;
        private final AppCompatButton checkInButton;
        private final TextView responsestatic;
        private final TextView response;

        public AppointmentViewHolder2(@NonNull View itemView) {
            super(itemView);

            this.time = itemView.findViewById(R.id.time);                                        // Holder variables to set equal to the position that is found on the UI of the
                                                                                                 // application Each one of the holder variables will be connected to
                                                                                                 //the Item list that is used in the recycle view of the application
            this.date = itemView.findViewById(R.id.appointmentDate);
            this.status = itemView.findViewById(R.id.Status);
            this.checkedInText = itemView.findViewById(R.id.checkedInText);                         //Setting the holder variables equal to the ID of the item in the UI
            this.cancel = itemView.findViewById(R.id.cancelAppointment);
            this.checkInButton = itemView.findViewById(R.id.checkInAppointment);
            this.responsestatic = itemView.findViewById(R.id.responsestatic);
            this.response = itemView.findViewById(R.id.response);
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
                                                                                         // Sending an email function for the applciation which uses the set email that the clinic
                                                                                        // uses also it takes the message of the email with the reciever email which is taken from the user
        final String sender_username = "appclinichuda@gmail.com";                       // Send email function which will email the clinic that the patient is checking into an appointment


        String Fullname_text = UserManager.getInstance().getCurrentUser().getFirstName() + " " + UserManager.getInstance().getCurrentUser().getLastName();
        String birthday_text = UserManager.getInstance().getCurrentUser().getBirthday();                    // Used variables to get the current user information
        String email_text = UserManager.getInstance().getCurrentUser().getEmailAddress();


        InternetAddress s_sender = new InternetAddress(sender_email);
        InternetAddress reciever = new InternetAddress("ali.bilal.said@gmail.com");                 // Message that is being sent to the clinic
        final String message_text = "Dear Admin, " + ",\n\n"+ "Patient: " + Fullname_text + " Birthday: " + birthday_text + " is checking in for their appointment"+".\n\n" + "Appointment Date: " + Date+ "\nAppointment Time: " + Time+"\n\nThank You" ;
        Properties settings = Settings(smtp);

                                                                                                    // Used properties to send the email to the clinic


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

                                                                // settings for email sending with the port being used
        settings.put(smtp+"auth", "true");

        settings.put(smtp+"starttls.enable", "true");

        settings.put(smtp+"host", smtp_gmail);
        settings.put(smtp+"port", "587");
        return settings;

    }

    private static Message sendmimmessage(Session f_email, InternetAddress s_sender, InternetAddress reciever, String order, String message_text) throws MessagingException {
        Message mimmessage = new MimeMessage(f_email);




        mimmessage.setFrom(s_sender);

                                                                        // mim message function to send the function used gmail to send the emails the function will take the text of the
                                                                         // message and also the sender email with the session that the authentication happens with
        mimmessage.setSentDate(new Date());

        mimmessage.setRecipient(Message.RecipientType.TO,  reciever);

        mimmessage.setText(message_text);

        mimmessage.setSubject("Patient Appointment Status");


        return mimmessage;

    }
}