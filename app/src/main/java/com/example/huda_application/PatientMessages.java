package com.example.huda_application;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.huda_application.firebase.FirebaseClient;
import com.example.huda_application.user.Appointment;
import com.example.huda_application.user.AppointmentStatus;
import com.example.huda_application.user.Message;
import com.example.huda_application.user.User;
import com.example.huda_application.user.UserManager;
import com.example.huda_application.user.UserType;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class PatientMessages extends AppCompatActivity implements View.OnClickListener{

    private PatientViewAdapter viewAdapter;                         // This class will be used to display the message to the patient of the application
    private ImageView backbottun;                                   // The class will take the user object which has the list of messages inside of it
    @Override                                                       // the messages are displayed in the recyclerview items
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Saved_language();
        setContentView(R.layout.activity_patient_messages);
        RecyclerView recyclerView = findViewById(R.id.usersView5);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        for(int i=0; i< UserManager.getInstance().getCurrentUser().getMessages().size(); i++){

            UserManager.getInstance().getCurrentUser().getMessages().get(i).setRead(true);                          // This would change the message from not seen to read in the application

        }
        FirebaseClient.updateUser(UserManager.getInstance().getCurrentUser());                                      // Update the current user for the application

        viewAdapter =  new PatientViewAdapter(this, UserManager.getInstance().getCurrentUser());
        recyclerView.setAdapter(viewAdapter);
        backbottun = findViewById(R.id.backButton_message_patient);                                                 // Back button for the application
        backbottun.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.backButton_message_patient)
            startActivity(new Intent(this , MainApplication.class));
    }

    private static class PatientViewAdapter extends RecyclerView.Adapter<PatientMessages.PatientViewHolder> implements View.OnClickListener {

        private LayoutInflater inflater;
        private final User user;                                                // Adapter used to show all the messages for the user as items
        private final Context context;

        public PatientViewAdapter(Context context, User user) {
            this.context = context;
            this.user = user;                                                   // THe constructor will take the user object as the input
            this.inflater = LayoutInflater.from(context);
        }

        @NonNull
        @Override
        public PatientViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view = inflater.inflate(R.layout.patient_message_item, parent, false);
            return new PatientViewHolder(view);                                                     // this will create the on holder for the adapter
        }

        @Override
        public void onBindViewHolder(@NonNull PatientViewHolder holder, int position) {
            Message message = user.getMessages().get(position);
            holder.date.setText(message.getDate());                                                 // The binder to store the messages inside for the items in the recycle
            holder.message.setText(message.getMessage());
        }

        @Override
        public int getItemCount() {
            return user.getMessages().size();
        }                       // Return the amount of messages in the user messages

        @Override
        public void onClick(View v) {

        }
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
    public static class PatientViewHolder extends RecyclerView.ViewHolder {

        private final TextView date;
        private final TextView message;

        public PatientViewHolder(@NonNull View itemView) {
            super(itemView);
            this.date = itemView.findViewById(R.id.DateMessage);
            this.message = itemView.findViewById(R.id.Message3);            // Holder to hold the date and message for the items

        }

        public TextView getDate() {
            return date;
        }

        public TextView getMessage() {
            return message;
        }
    }
}
