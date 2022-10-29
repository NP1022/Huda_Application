package com.example.huda_application;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.huda_application.firebase.FirebaseClient;
import com.example.huda_application.user.User;

import java.util.List;

public class AdminPage extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_page);
        ListView listview = findViewById(R.id.listview);

        if (User.isAdmin()) {
            List<User> users = FirebaseClient.getAllUsers();

            ListAdapter adapter = new UserArrayAdapter(this, users);
            listview.setAdapter(adapter);

            listview.setOnItemClickListener((parent, view, position, id) -> {
                
            });
        }
    }

    private static class UserArrayAdapter extends ArrayAdapter<User> {

        public UserArrayAdapter(Context context, List<User> users) {
            super(context, R.layout.admin_user_list_item, users);
        }

        @NonNull
        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            User user = getItem(position);

            if (convertView == null) {
                convertView = LayoutInflater.from(getContext()).inflate(R.layout.admin_user_list_item, parent, false);
            }

            TextView nameView = convertView.findViewById(R.id.name);
            TextView emailView = convertView.findViewById(R.id.email);

            nameView.setText(String.format("%s %s", user.getFirstName(), user.getLastName()));
            emailView.setText(user.getEmailAddress());

            return super.getView(position, convertView, parent);
        }
    }
}