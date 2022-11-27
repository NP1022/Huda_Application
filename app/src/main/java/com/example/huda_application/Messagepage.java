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

    private PatientViewAdapter2 viewAdapter;
    private ImageView backbottun;
    private TextView MessageAll;
    private Toolbar toolbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_messagepage);
        RecyclerView recyclerView = findViewById(R.id.usersView11);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        toolbar = findViewById(R.id.Tool_bar2);
        this.setSupportActionBar(toolbar);
        this.getSupportActionBar().setTitle("");
       // if (UserManager.getInstance().isAdmin()) {
            FirebaseDatabase.getInstance().getReference("User").addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    List<User> users = new ArrayList<>();
                    for (DataSnapshot child : snapshot.getChildren()) {
                        User user = FirebaseClient.convertToUser(child);
                        if (user.getUserType() != UserType.ADMIN)
                            users.add(user);
                    }

                    viewAdapter = new PatientViewAdapter2(Messagepage.this, users);
                    recyclerView.setAdapter(viewAdapter);
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {
                }
            });
        //}
        backbottun = findViewById(R.id.backButton_message_main);
        MessageAll = findViewById(R.id.MessageAll);


        backbottun.setOnClickListener(this);
        MessageAll.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        if(v.getId() == R.id.backButton_message_main){
            startActivity(new Intent(this, Adminpanel.class));
        }
        else if(v.getId() == R.id.MessageAll){
            startActivity(new Intent(this, message_all_patients.class));
        }
    }

    private static class PatientViewAdapter2 extends RecyclerView.Adapter<PatientViewHolder> implements View.OnClickListener, Filterable {

        private LayoutInflater inflater;
        private  List<User> users;
        private  List<User> Filteredusers;
        private final Context context;

        public PatientViewAdapter2(Context context, List<User> users) {
            this.context = context;
            this.users = users;
            this.Filteredusers = users;
            this.inflater = LayoutInflater.from(context);

        }

        @NonNull
        @Override
        public PatientViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view = inflater.inflate(R.layout.message_user_item, parent, false);
            return new PatientViewHolder(view);
        }

        @Override
        public void onBindViewHolder(@NonNull PatientViewHolder holder, int position) {
            final int pos = position;
            User user = users.get(position);

            // FirebaseDatabase.getInstance().getReference("User").child(user.getUserId()).addListenerForSingleValueEvent(new ValueEventListener() {
            //     @Override
            //     public void onDataChange(@NonNull DataSnapshot snapshot) {
            //         users.set(pos, FirebaseClient.convertToUser(snapshot));
            //     }

            //     @Override
            //     public void onCancelled(@NonNull DatabaseError error) {
            //     }
            // });

//            List<Appointment> appointments = user.getAppointments();
//            int Pending_count =0;
//            for (int i =0; i< appointments.size(); i ++){
//                if (appointments.get(i).getStatus() == AppointmentStatus.PENDING){
//                    Pending_count++;
//
//                }
//            }
            holder.name.setText(String.format("%s", user.getFirstName()));
            holder.lastname.setText(String.format("%s", user.getLastName()));
            holder.email.setText(user.getEmailAddress());


            holder.MessagePage.setOnClickListener(view -> {
                Intent intent = new Intent(context, MessageSendPage.class);
                intent.putExtra("user", user);
                context.startActivity(intent);
            });
        }

        @Override
        public int getItemCount() {
            return users.size();
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

                    if (charSequence == null || charSequence.length() == 0){

                        filterresults.values = Filteredusers;
                        filterresults.count = Filteredusers.size();
                    }
                    else{
                        String character = charSequence.toString().toLowerCase();

                        List<User> usersresults = new ArrayList<User>();
                        for (User user : users) {
                            String temp = user.getFirstName().toLowerCase()+" "+user.getLastName().toLowerCase();
                            if (temp.toLowerCase().contains(character.toLowerCase())) {

                                usersresults.add(user);
                            }

                        }

                        filterresults.values = usersresults;
                        filterresults.count = usersresults.size();
                    }
                    return filterresults;

                }

                @Override
                protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
                    users = (ArrayList<User>) filterResults.values ;
                    notifyDataSetChanged();
                }
            };
            return filter;
        }
    }

    public static class PatientViewHolder extends RecyclerView.ViewHolder {

        private final TextView name;
        private final TextView email;
        private final TextView lastname;
        private final TextView MessagePage;

        public PatientViewHolder(@NonNull View itemView) {
            super(itemView);
            this.name = itemView.findViewById(R.id.name2);
            this.lastname = itemView.findViewById(R.id.last_name2);
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
        MenuItem menuItem =  menu.findItem(R.id.searchView);
        SearchView searchView = (SearchView) menuItem.getActionView();
        searchView.setMaxWidth(Integer.MAX_VALUE);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
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