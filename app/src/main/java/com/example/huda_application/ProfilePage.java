package com.example.huda_application;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import android.Manifest;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.huda_application.firebase.FirebaseClient;
import com.example.huda_application.user.User;
import com.example.huda_application.user.UserManager;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import de.hdodenhof.circleimageview.CircleImageView;

public class ProfilePage extends AppCompatActivity implements View.OnClickListener {
    private TextView header;
    private TextView firstName;
    private TextView email;
    private TextView lastName;
    private TextView DOB;
    private ImageView backbutton;
    private Button updatePass, signOut;
    private FirebaseAuth mAuth;
    private FloatingActionButton upload;
    private CircleImageView profilePicture;
    private static final int PERMISSION_CODE = 1000;
    private static final int IMAGE_CAPTURE_CODE = 1001;

    Uri image_uri;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_page);

        header = findViewById(R.id.tv_heading);
        firstName = findViewById(R.id.et_first_name);
        lastName = findViewById(R.id.et_last_name);
        email = findViewById(R.id.et_email);
        DOB = findViewById(R.id.et_date_of_birth);
        upload = findViewById(R.id.uploadPicture);
        profilePicture = findViewById(R.id.profile_image);
        signOut = findViewById(R.id.logoutButton);
        updatePass = findViewById(R.id.updatePassword);
        backbutton = findViewById(R.id.backButton_10);
        backbutton.setOnClickListener(this);
        signOut.setOnClickListener(this);
        updatePass.setOnClickListener(this);

        User user = UserManager.getInstance().getCurrentUser();
        header.setText(String.format("%s", "Welcome, " + user.getFirstName() + "!"));
        firstName.setText(String.format("%s", user.getFirstName()));
        lastName.setText(String.format("%s", user.getLastName()));
        email.setText(String.format("%s", user.getEmailAddress()));
        DOB.setText(String.format("%s", user.getBirthday()));

        mAuth = FirebaseAuth.getInstance();
        FirebaseUser fbUser = mAuth.getCurrentUser();

        upload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                    if (checkSelfPermission(Manifest.permission.CAMERA) ==
                            PackageManager.PERMISSION_DENIED ||
                            checkSelfPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE) ==
                                    PackageManager.PERMISSION_DENIED) {
                        String[] permission = {Manifest.permission.CAMERA, Manifest.permission.WRITE_EXTERNAL_STORAGE};
                        requestPermissions(permission, PERMISSION_CODE);
                    } else {
                        openCamera();
                    }
                } else {

                }
            }
        });
    }
//        upload.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//                getImageFromAlbum();
//            }
//        });
//
//public class GraphicsView extends View {
//    private static final String QUOTE = "Welcome, Masrur!";
//    private Path circle;
//    private Paint cPaint;
//    private Paint tPaint;
//
//    public GraphicsView(Context context) {
//        super(context);
//
//        int color = Color.argb(127, 255, 0, 255);
//
//        circle = new Path();
//        circle.addCircle(230, 350, 150, Path.Direction.CW);
//
//        cPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
//        cPaint.setStyle(Paint.Style.STROKE);
//        cPaint.setColor(Color.LTGRAY);
//        cPaint.setStrokeWidth(3);
//
//        tPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
//        tPaint.setStyle(Paint.Style.FILL_AND_STROKE);
//        tPaint.setColor(Color.BLACK);
//        tPaint.setTextSize(50);
//    }
//
//    @Override
//    protected void onDraw(Canvas canvas) {
//        canvas.drawTextOnPath(QUOTE, circle, 485, 20, tPaint);
//    }
//}

    private void openCamera() {
        ContentValues values = new ContentValues();
        values.put(MediaStore.Images.Media.TITLE, "New Picture");
        values.put(MediaStore.Images.Media.DESCRIPTION, "From Camera");
        image_uri = getContentResolver().insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, values);
        Intent camera = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        camera.putExtra(MediaStore.EXTRA_OUTPUT, image_uri);
        // startActivityForResult(camera, IMAGE_CAPTURE_CODE);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        switch (requestCode) {
            case PERMISSION_CODE: {
                if (grantResults.length > 0 && grantResults[0] ==
                        PackageManager.PERMISSION_GRANTED) {
                    openCamera();
                } else {
                    Toast.makeText(this, "Permission denied.", Toast.LENGTH_SHORT).show();
                }
            }
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == RESULT_OK) {
            upload.setImageURI(image_uri);
        }
    }
//        private void getImageFromAlbum(){
//        try {
//            Intent i = new Intent(Intent.ACTION_PICK,
//                    android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
//            startActivity(i, RESULT_LOAD_IMAGE);
//        } catch(Exception exp){
//            Log.i("Error",exp.toString());
//        }
//    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.backButton_10) {
            startActivity(new Intent(this, MainApplication.class));
        } else if (v.getId() == R.id.logoutButton) {
            UserManager.getInstance().removeCurrentUser();
            mAuth.signOut();
            startActivity(new Intent(this, MainActivity.class));
        } else if (v.getId() == R.id.updatePassword) {
            startActivity(new Intent(this, updatePassword.class));
        }
}



}
