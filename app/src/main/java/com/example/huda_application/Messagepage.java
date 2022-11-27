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
public class Messagepage extends AppCompatActivity implements View.OnClickListener{

    private PatientViewAdapter2 viewAdapter;                             // Class Used to show all the patients in the database for the admin to check for appointments
                                                                         // The class will Display all the patients with a button next to it that shows Message and
                                                                         // Redirect to send message to individual patients for the application
    private ImageView backbottun;
    private TextView MessageAll;
    private Toolbar toolbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_messagepage);
        RecyclerView recyclerView = findViewById(R.id.usersView11);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));               // Toolbar used to hold the search filter in the application


        toolbar = findViewById(R.id.Tool_bar2);
        this.setSupportActionBar(toolbar);                                                  // Setting up the recycler view for the application
        this.getSupportActionBar().setTitle("");


            FirebaseDatabase.getInstance().getReference("User").addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    List<User> users = new ArrayList<>();
                    for (DataSnapshot child : snapshot.getChildren()) {                             // Firebase call to get all the updated changes to the users that happen

                        User user = FirebaseClient.convertToUser(child);
                        if (user.getUserType() != UserType.ADMIN)
                            users.add(user);                                 // pass the Users object to the appointment Adapter to display the Patients
                                                                             //Create the view Adapter to display the patients
                    }

                    viewAdapter = new PatientViewAdapter2(Messagepage.this, users);   // Calling the Adapter function for the recycler view
                    recyclerView.setAdapter(viewAdapter);
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {
                }
            });


        backbottun = findViewById(R.id.backButton_message_main);
        MessageAll = findViewById(R.id.MessageAll);

                                                                        // Buttons that take to the message all page and the individual message page for the patient
        backbottun.setOnClickListener(this);
        MessageAll.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        if(v.getId() == R.id.backButton_message_main){
            startActivity(new Intent(this, Adminpanel.class));
        }                                                                                // Buttons that take to the message all page and the individual message page for the patient
        else if(v.getId() == R.id.MessageAll){
            startActivity(new Intent(this, message_all_patients.class));
        }
    }

    private static class PatientViewAdapter2 extends RecyclerView.Adapter<PatientViewHolder> implements View.OnClickListener, Filterable {

        private LayoutInflater inflater;                                        //Variables used by adapter to show the items in the recycler view in the application.

        private  List<User> users;
        private  List<User> Filteredusers;
        private final Context context;

        public PatientViewAdapter2(Context context, List<User> users) {             // Variables users are used for the User list and the filtered for the search view in the application.
                                                                                    // Constructor used to set the variables equal to the parameters for the recycler adapter
            this.context = context;
            this.users = users;
            this.Filteredusers = users;
            this.inflater = LayoutInflater.from(context);

        }

        @NonNull
        @Override
        public PatientViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view = inflater.inflate(R.layout.message_user_item, parent, false);        // over ride method to check the view of the Adapter for the recycle view
            return new PatientViewHolder(view);
        }

        @Override
        public void onBindViewHolder(@NonNull PatientViewHolder holder, int position) {
            final int pos = position;                                                   // Holder for each Item that is used in the recycle view
                                                                                        // of page which use the holder object
            User user = users.get(position);

            holder.name.setText(String.format("%s", user.getFirstName()));               // Holder variables to set equal to the position that is found on the UI of the
                                                                                          // application Each one of the holder variables will be connected to
                                                                                          //the Item list that is used in the recycle view of the application
            holder.lastname.setText(String.format("%s", user.getLastName()));
            holder.email.setText(user.getEmailAddress());


            holder.MessagePage.setOnClickListener(view -> {
                Intent intent = new Intent(context, MessageSendPage.class);         // Message button on the Item to redirect to the message page for the patient
                intent.putExtra("user", user);
                context.startActivity(intent);
            });
        }

        @Override
        public int getItemCount() {
            return users.size();
        }                          // return the size of the list that is displayed as the users

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
                protected FilterResults performFiltering(CharSequence charSequence) {              // Filter for the Search for the search view on the page


                    FilterResults filterresults = new FilterResults();

                    if (charSequence == null || charSequence.length() == 0){                      // The filter will take out any patient name that doesnot contain what is being searched for

                        filterresults.values = Filteredusers;
                        filterresults.count = Filteredusers.size();
                    }
                    else{
                        String character = charSequence.toString().toLowerCase();

                        List<User> usersresults = new ArrayList<User>();                            // takes the user first and last name to compare to the sequence
                        for (User user : users) {
                            String temp = user.getFirstName().toLowerCase()+" "+user.getLastName().toLowerCase();
                            if (temp.toLowerCase().contains(character.toLowerCase())) {

                                usersresults.add(user);
                            }

                        }

                        filterresults.values = usersresults;
                        filterresults.count = usersresults.size();                              // takes the filtered list to display it again for the admin
                    }
                    return filterresults;

                }

                @Override
                protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
                    users = (ArrayList<User>) filterResults.values ;                // Returns the results of the filter for the view
                    notifyDataSetChanged();
                }
            };
            return filter;
        }
    }

    public static class PatientViewHolder extends RecyclerView.ViewHolder {

        private final TextView name;                                               // Holder variables to set equal to the position that is
                                                                                    // found on the UI of the application
        private final TextView email;
        private final TextView lastname;
        private final TextView MessagePage;

        public PatientViewHolder(@NonNull View itemView) {
            super(itemView);
            this.name = itemView.findViewById(R.id.name2);
            this.lastname = itemView.findViewById(R.id.last_name2);             //Setting the holder variables equal to the ID of the item in the UI
            this.email = itemView.findViewById(R.id.email2);
            this.MessagePage = itemView.findViewById(R.id.Message_button);

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
            return MessagePage;
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.search ,menu);
        MenuItem menuItem =  menu.findItem(R.id.searchView);                         // Funtion to created the search view for the search filter

        SearchView searchView = (SearchView) menuItem.getActionView();
        searchView.setMaxWidth(Integer.MAX_VALUE);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override                                                               // Takes the created Search view and assigns it to the ID
                                                                                    // Two functions to check once the text is changes and is not changed
            public boolean onQueryTextSubmit(String query) {
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                viewAdapter.getFilter().filter(newText);

                return true;
            }
        });

        return super.onCreateOptionsMenu(menu);
    }
}