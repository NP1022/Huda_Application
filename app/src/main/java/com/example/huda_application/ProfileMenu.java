//package com.example.huda_application;
//
//import android.os.Bundle;
//import android.view.LayoutInflater;
//import android.view.Menu;
//import android.view.View;
//import android.widget.TextView;
//import android.widget.Toast;
//
//import androidx.appcompat.app.AppCompatActivity;
//import androidx.drawerlayout.widget.DrawerLayout;
//import androidx.navigation.NavController;
//import androidx.navigation.Navigation;
//import androidx.navigation.ui.AppBarConfiguration;
//import androidx.navigation.ui.NavigationUI;
//
//import com.example.huda_application.databinding.ActivityMainApplicationBinding;
//import com.example.huda_application.databinding.ActivityMainBinding;
//import com.example.huda_application.databinding.NewProfileMenuBinding;
//import com.example.huda_application.user.User;
//import com.example.huda_application.user.UserManager;
//import com.google.android.material.navigation.NavigationView;
//import com.google.android.material.snackbar.Snackbar;
//
//public class ProfileMenu extends AppCompatActivity {
//
//    private AppBarConfiguration mAppBarConfiguration;
//    private NewProfileMenuBinding binding;
//    private TextView name;
//    private TextView email;
//    private TextView DOB;
//    private TextView toast;
//    NavigationView profileMenu;
//    View headerView;
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//
//        binding = NewProfileMenuBinding.inflate(getLayoutInflater());
//        // binding = ActivityMainApplicationBinding.inflate(getLayoutInflater());
//        setContentView(binding.getRoot());
//
//        setSupportActionBar(binding.appBarProfileMenu.toolbar);
//        binding.appBarProfileMenu.fab.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
//            }
//        });
//        DrawerLayout drawer = binding.drawerLayout;
//        NavigationView navigationView = binding.navView;
//        // Passing each menu ID as a set of Ids because each
//        // menu should be considered as top level destinations.
//        mAppBarConfiguration = new AppBarConfiguration.Builder(
//                R.id.nav_home, R.id.nav_gallery, R.id.nav_slideshow)
//                .setOpenableLayout(drawer)
//                .build();
//        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_profile_menu);
//        NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);
//        NavigationUI.setupWithNavController(navigationView, navController);
//
//        navigationView = (NavigationView) findViewById(R.id.nav_view);
//        navigationView.setNavigationItemSelectedListener((NavigationView.OnNavigationItemSelectedListener) this);
//        headerView = navigationView.getHeaderView(0);
//        name = headerView.findViewById(R.id.profile_user_name);
//        email = headerView.findViewById(R.id.profile_user_email);
//        DOB = headerView.findViewById(R.id.profile_user_dob);
//
//        User user = UserManager.getInstance().getCurrentUser();
//        name.setText(String.format("%s", user.getFirstName() + " " + user.getLastName()));
//        email.setText(String.format("%s", user.getEmailAddress()));
//        DOB.setText(String.format("%s", user.getBirthday()));
//
//        toast = findViewById(R.id.toastButton);
//        toast.setOnClickListener(this);
//
//        FirebaseDatabase.getInstance().getReference("User").child(UserManager.getInstance().getCurrentUser().getUserId())
//                .addValueEventListener(new ValueEventListener() {
//                    @Override
//                    public void onDataChange(@NonNull DataSnapshot snapshot) {
//                        User user = FirebaseClient.convertToUser(snapshot);
//                        user = snapshot.getValue(User.class);
//                        name.setText(String.format("%s", user.getFirstName() + " " + user.getLastName()));
//                        email.setText(String.format("%s", user.getEmailAddress()));
//                        DOB.setText(String.format("%s", user.getBirthday()));
//                    }
//
//                    @Override
//                    public void onCancelled(@NonNull DatabaseError error) {
//                    }
//                });
//    }
//
//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        // Inflate the menu; this adds items to the action bar if it is present.
//        getMenuInflater().inflate(R.menu.profile_menu, menu);
//        return true;
//    }
//
//    @Override
//    public boolean onSupportNavigateUp() {
//        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_profile_menu);
//        return NavigationUI.navigateUp(navController, mAppBarConfiguration)
//                || super.onSupportNavigateUp();
//    }
//
////    @Override
////    public void onClick(View v) {
////        if (v.getId() == R.id.toastButton) {
////            Toast.makeText(this, name + " " + email + " " + DOB, Toast.LENGTH_SHORT).show();
////        }
////    }
//}