package com.example.huda_application;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class NewLogin extends AppCompatActivity implements View.OnClickListener {

    TextView login, signUp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_login);

        login = (TextView) findViewById(R.id.loginPage);
        signUp = (TextView) findViewById(R.id.registerPage);

        login.setOnClickListener((View.OnClickListener) this);
        signUp.setOnClickListener((View.OnClickListener) this);
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.loginPage) {
            Intent LoginPage = new Intent(this, MainActivity.class);
            startActivity(LoginPage);
        } else if (view.getId() == R.id.registerPage) {
            Intent RegPage = new Intent(this, RegisterAccount.class);
            startActivity(RegPage);
        }
    }
}