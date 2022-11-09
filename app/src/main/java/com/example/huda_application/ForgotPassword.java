package com.example.huda_application;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;

public class ForgotPassword extends AppCompatActivity implements View.OnClickListener
{

    private EditText email;
    private Button resetBtn;
    private ImageView backButton;

    FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_password);
        backButton = (ImageView) findViewById(R.id.backButton);
        backButton.setOnClickListener((View.OnClickListener) this);

         email = findViewById(R.id.enterEmail);
        resetBtn = findViewById(R.id.sendReset);

        mAuth =FirebaseAuth.getInstance();

        resetBtn.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                String emailStr = email.getText().toString().trim();
                if(emailStr.isEmpty())
                {
                    email.setError("Email is Required!");
                    email.requestFocus();
                    return;
                }
                else if(!Patterns.EMAIL_ADDRESS.matcher(emailStr).matches()) {
                    email.setError("Please provide valid email!");
                    email.requestFocus();
                    return;
                }
                else
                {

                    mAuth.sendPasswordResetEmail(emailStr).addOnCompleteListener(new OnCompleteListener<Void>()
                    {
                        @Override
                        public void onComplete(@NonNull Task<Void> task)
                        {
                            if(task.isSuccessful())
                            {
                                Toast.makeText(ForgotPassword.this,"Check your email to reset password",Toast.LENGTH_LONG).show();
                            }
                            else
                            {
                                Toast.makeText(ForgotPassword.this,"Account does not exist",Toast.LENGTH_LONG).show();
                            }
                        }
                    });
                }
            }
        });
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.backButton) {
            Intent prev = new Intent(this, MainActivity.class);
            startActivity(prev);
        }
    }

}