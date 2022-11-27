package com.example.huda_application;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.ListView;

import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.huda_application.firebase.FirebaseClient;
import com.example.huda_application.user.Appointment;
import com.example.huda_application.user.AppointmentStatus;
import com.example.huda_application.user.User;
import com.example.huda_application.user.UserManager;
import com.example.huda_application.user.UserType;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class AdminPage extends AppCompatActivity implements View.OnClickListener {

    private PatientViewAdapter viewAdapter;                                 // Class Used to show all the patients in the database for the admin to check for appointments
    private ImageView backbutton;                                           // The class will Display all the patients with a button next to it that shows appointments and
                                                                            // How many pending appointments are there.
    private Toolbar toolbar;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_page);

        toolbar = findViewById(R.id.Tool_bar);
        this.setSupportActionBar(toolbar);                                   // Toolbar used to hold the search filter in the application
        this.getSupportActionBar().setTitle("");


        RecyclerView recyclerView = findViewById(R.id.usersView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));                   // Setting up the recycler view for the application

            FirebaseDatabase.getInstance().getReference("User").addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    List<User> users = new ArrayList<>();
                    for (DataSnapshot child : snapshot.getChildren()) {                                   // Firebase call to get all the updated changes to the users that happen
                                                                                                            // pass the Users object to the appointment Adapter to display the Patients
                        User user = FirebaseClient.convertToUser(child);
                        if (user.getUserType() != UserType.ADMIN)
                            users.add(user);
                    }

                    viewAdapter = new PatientViewAdapter(AdminPage.this, users);                     // Create the view Adapter to display the patients
                    recyclerView.setAdapter(viewAdapter);
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {
                }
            });

        backbutton = findViewById(R.id.backButton_9);
        backbutton.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if(v.getId() == R.id.backButton_9)
            startActivity(new Intent(this , Adminpanel.class)); // On click for the back button
    }

    private static class PatientViewAdapter extends RecyclerView.Adapter<PatientViewHolder> implements View.OnClickListener, Filterable {

        private LayoutInflater inflater;                                           //Variables used by adapter to show the items in the recycler view in the application.
        private  List<User> users;                                                 // Variables users are used for the User list and the filtered for the search view in the application.
        private  List<User> Filteredusers;
        private final Context context;

        public PatientViewAdapter(Context context, List<User> users) {
            this.context = context;
            this.users = users;                                                     // Constructor used to set the variables equal to the parameters for the recycler adapter
            this.Filteredusers = users;
            this.inflater = LayoutInflater.from(context);
        }

        @NonNull
        @Override
        public PatientViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view = inflater.inflate(R.layout.admin_user_list_item, parent, false);      // over ride method to check the view of the Adapter for the recycle view

            return new PatientViewHolder(view);
        }

        @Override
        public void onBindViewHolder(@NonNull PatientViewHolder holder, int position) {
            final int pos = position;
            User user = users.get(position);
            List<Appointment> appointments = user.getAppointments();                     // Holder for each Item that is used in the recycle view
                                                                                            // of page which use the holder object
            int Pending_count =0;
            for (int i =0; i< appointments.size(); i ++){
                if (appointments.get(i).getStatus() == AppointmentStatus.PENDING){
                    Pending_count++;

                }
            }
            holder.name.setText(String.format("%s", user.getFirstName()));
            holder.lastname.setText(String.format("%s", user.getLastName()));           // Set the user information into the Item that is displayed in the recycler View
            holder.email.setText(user.getEmailAddress());
            holder.appointments.setText("Appointments "+ Pending_count);

            holder.appointments.setOnClickListener(view -> {
                Intent intent = new Intent(context, UserAppointment.class);             // Appointments button that is used to redirect to the appointments page
                intent.putExtra("user", user);
                context.startActivity(intent);
            });
        }

        @Override
        public int getItemCount() {
            return users.size();
        }                                   // return the size of the list that is displayed as the users

        @Override
        public int getItemViewType(int position) {
            return position;
        }

        @Override
        public void onClick(View v) {

        }

        @Override
        public Filter getFilter() {
            Filter filter = new Filter() {
                @Override
                protected FilterResults performFiltering(CharSequence charSequence) {
                    FilterResults filterresults = new FilterResults();
                                                                                                                  // Filter for the Search for the search view on the page the sequence that is
                                                                                                                    // inputed is the will be compared with the list first and last name.
                    if (charSequence == null || charSequence.length() == 0){

                        filterresults.values = Filteredusers;
                        filterresults.count = Filteredusers.size();
                    }
                    else{
                        String character = charSequence.toString().toLowerCase();                                   // The filter will take out any patient name that doesnot contain what is being searched for

                        List<User> usersresults = new ArrayList<User>();
                        for (User user : users) {
                            String temp = user.getFirstName().toLowerCase()+" "+user.getLastName().toLowerCase();      // takes the user first and last name to compare to the sequence
                            if (temp.toLowerCase().contains(character.toLowerCase())) {

                                usersresults.add(user);
                            }

                        }

                        filterresults.values = usersresults;              // takes the filtered list to display it again for the admin
                        filterresults.count = usersresults.size();
                    }
                    return filterresults;

                }

                @Override
                protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
                    users = (ArrayList<User>) filterResults.values ;                        // Returns the results of the filter for the view
                    notifyDataSetChanged();
                }
            };
            return filter;
        }
    }

    public static class PatientViewHolder extends RecyclerView.ViewHolder {

        private final TextView name;
        private final TextView email;                             // Holder variables to set equal to the position that is
                                                                  // found on the UI of the application
        private final TextView lastname;
        private final TextView appointments;

        public PatientViewHolder(@NonNull View itemView) {
            super(itemView);                                                          // Holder variables to set equal to the position that is found on the UI of the
                                                                                      // application Each one of the holder variables will be connected to
                                                                                      //the Item list that is used in the recycle view of the application
            this.name = itemView.findViewById(R.id.name);
            this.lastname = itemView.findViewById(R.id.last_name);                   //Setting the holder variables equal to the ID of the item in the UI
            this.email = itemView.findViewById(R.id.email);
            this.appointments = itemView.findViewById(R.id.Appointments_button);

        }

        public TextView getName() {
            return name;
        }

        public TextView getEmail() {
            return email;
        }

        public TextView getLastname() {
            return lastname;
        }

        public TextView getAppointments() {
            return appointments;
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.search ,menu);                             // Funtion to created the search view for the search filter
        MenuItem menuItem =  menu.findItem(R.id.searchView);                        // Takes the created Search view and assigns it to the ID
        SearchView searchView = (SearchView) menuItem.getActionView();
        searchView.setMaxWidth(Integer.MAX_VALUE);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return true;
            }
                                                                                    // Two functions to check once the text is changes and is not changed
            @Override
            public boolean onQueryTextChange(String newText) {
                viewAdapter.getFilter().filter(newText);


                return true;
            }
        });

        return super.onCreateOptionsMenu(menu);
    }
}