package com.example.huda_application;

import static android.content.ContentValues.TAG;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.huda_application.firebase.FirebaseClient;
import com.example.huda_application.user.User;
import com.example.huda_application.user.UserManager;
import com.example.huda_application.user.UserType;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.SimpleTimeZone;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegisterAccount extends AppCompatActivity implements View.OnClickListener
{

    private static final Pattern PASSWORD_PATTERN =
            Pattern.compile("^" +
                    "(?=.*[0-9])" +         //at least 1 digit
                    "(?=.*[a-z])" +         //at least 1 lower case letter
                    "(?=.*[A-Z])" +         //at least 1 upper case letter
                    "(?=.*[!*@#$%^&+=])" +    //at least 1 special character
                    "(?=\\S+$)" +           //no white spaces
                    ".{8,}" +               //at least 8 characters
                    "$");

    private static final Pattern DATE_PATTERN = Pattern.compile(
            "^((0[1-9]|1[012])-(0[1-9]|[12][0-9]|3[01])-(19|2[0-9])[0-9]{2})$"); // date pattern match

    private static Pattern LETTERS_PATTERN = Pattern.compile("^[A-Za-z\\s]{1,}[\\.]{0,1}[A-Za-z\\s]{0,}$"); // Letters pattern match


    FirebaseDatabase rootNode;
    DatabaseReference ref;
    private ImageView backButton;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        mAuth = FirebaseAuth.getInstance();

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_account);
        backButton = (ImageView) findViewById(R.id.backButton);
        backButton.setOnClickListener((View.OnClickListener) this);
        final EditText firstName = findViewById(R.id.firstName);
        final EditText lastName = findViewById(R.id.lastName);
        final EditText email = findViewById(R.id.email);
        final EditText password = findViewById(R.id.password);
        final EditText conPassword = findViewById(R.id.confirmPass);
        final EditText DOb = findViewById(R.id.registerDob);

        DAOUser dao = new DAOUser();

        EditText registerDob = findViewById(R.id.registerDob);
        registerDob.addTextChangedListener(new TextWatcher() {
            int prevL = 0;

            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                prevL = registerDob.getText().toString().length();
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                int length = editable.length();
                if ((prevL < length) && (length == 2 || length == 5)) {
                    editable.append("-");
                }
            }
        });

        Button buttonRegister = findViewById(R.id.registerButton); // set variable for button action

        buttonRegister.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view) // function for register button
            {

                final String firstNameTxt = firstName.getText().toString().trim(); // convert the EditText to a String type
                final String lastNameTxt = lastName.getText().toString().trim();
                final String emailTxt = email.getText().toString().trim();
                final String passwordTxt = password.getText().toString().trim();
                final String conPasswordTxt = conPassword.getText().toString().trim();
                final String dob = DOb.getText().toString().trim();


                if (TextUtils.isEmpty(firstNameTxt)) // check if the firstname and lastname are empty
                {
                    Toast.makeText(RegisterAccount.this,"Please enter first name",Toast.LENGTH_LONG).show();
                    firstName.setError("First Name is required");
                    firstName.requestFocus();
                }
                else if(TextUtils.isEmpty(lastNameTxt))
                {
                    Toast.makeText(RegisterAccount.this,"Please enter last name",Toast.LENGTH_LONG).show();
                    lastName.setError("Last Name is required");
                    lastName.requestFocus();
                }
                else if(!LETTERS_PATTERN.matcher(firstNameTxt).matches()) // Pattern matcher to check if the
                // name does not contain letter
                {
                    Toast.makeText(RegisterAccount.this,"Name must be alphabetic",Toast.LENGTH_LONG).show();
                    firstName.setError("Name format is required");
                    firstName.requestFocus();
                }
                else if(!LETTERS_PATTERN.matcher(lastNameTxt).matches())
                {
                    Toast.makeText(RegisterAccount.this,"Name must be alphabetic",Toast.LENGTH_LONG).show();
                    lastName.setError("Name format is required");
                    lastName.requestFocus();
                }
                else if(TextUtils.isEmpty(emailTxt)) // check if email is empty
                {
                    Toast.makeText(RegisterAccount.this,"Please enter Email",Toast.LENGTH_LONG).show();
                    email.setError("Email is required");
                    email.requestFocus();
                }
                else if(!Patterns.EMAIL_ADDRESS.matcher(emailTxt).matches())
                {
                    Toast.makeText(RegisterAccount.this,"Please re-enter Email",Toast.LENGTH_LONG).show();
                    email.setError("Valid email is required");
                    email.requestFocus();
                }
                else if(TextUtils.isEmpty(passwordTxt))
                {
                    Toast.makeText(RegisterAccount.this,"Please password",Toast.LENGTH_LONG).show();
                    password.setError("Password is required");
                    password.requestFocus();
                }
                else if(!PASSWORD_PATTERN.matcher(passwordTxt).matches())
                {
                    Toast.makeText(RegisterAccount.this,"At least 1 capital letter, 1 lower case letter, 1 number and 1 special character",Toast.LENGTH_LONG).show();
                    password.setError("Password too weak");
                    password.requestFocus();
                }
                else if(TextUtils.isEmpty(conPasswordTxt))
                {
                    Toast.makeText(RegisterAccount.this,"Please confirm password",Toast.LENGTH_LONG).show();
                    conPassword.setError("Password confirmation is required");
                    conPassword.requestFocus();
                }
                else if(!passwordTxt.equals(conPasswordTxt))
                {
                    Toast.makeText(RegisterAccount.this,"Please make passwords match",Toast.LENGTH_LONG).show();
                    conPassword.setError("Password match is required");
                    conPassword.requestFocus();
                    password.clearComposingText();
                    conPassword.clearComposingText();
                }
                else if(TextUtils.isEmpty(dob))
                {
                    Toast.makeText(RegisterAccount.this,"Date of birth cannot be empty",Toast.LENGTH_LONG).show();
                    DOb.setError("Date of birth is required");
                    DOb.requestFocus();
                }
                else if(!DATE_PATTERN.matcher(dob).matches())
                {
                    Toast.makeText(RegisterAccount.this,"Date of birth must be MM-DD-YYYY",Toast.LENGTH_LONG).show();
                    DOb.setError("Date format is required");
                    DOb.requestFocus();
                }
               else if(calcAge(dob) <= 18)
                {
                    Toast.makeText(RegisterAccount.this,"Patient must be 18 or older",Toast.LENGTH_LONG).show();
                    DOb.setError("Minimum age is required");
                    DOb.requestFocus();
                }
                else
                {
                    User user = new User(firstNameTxt,lastNameTxt,emailTxt,dob, UserType.PATIENT);


                    FirebaseAuth mAuth = FirebaseAuth.getInstance();
                    mAuth.createUserWithEmailAndPassword(emailTxt,passwordTxt).addOnCompleteListener(new OnCompleteListener<AuthResult>()
                    {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task)
                        {
                            if(task.isSuccessful())
                            {
                                FirebaseUser mUser = mAuth.getCurrentUser();
                                if (mUser != null)
                                    user.setUserId(mUser.getUid());

                                mUser.sendEmailVerification().addOnSuccessListener(new OnSuccessListener<Void>() // sending verification email to user
                                {
                                    @Override
                                    public void onSuccess(Void unused)
                                    {
                                        FirebaseClient.addUser(user).addOnSuccessListener(suc->
                                        {
                                            Toast.makeText(RegisterAccount.this,"Verification email sent",Toast.LENGTH_LONG).show(); // toast meessage to user
                                            Toast.makeText(RegisterAccount.this,"Please verify your email",Toast.LENGTH_LONG).show(); // toast meessage to user

                                            startActivity(new Intent(RegisterAccount.this,MainActivity.class));
                                        }).addOnFailureListener(er->
                                        {
                                            Toast.makeText(RegisterAccount.this,""+er.getMessage(),Toast.LENGTH_LONG).show();
                                        });

                                    }
                                }).addOnFailureListener(new OnFailureListener()
                                {
                                    @Override
                                    public void onFailure(@NonNull Exception e)
                                    {
                                        Log.d(TAG,"onFailure: Email not sent " + e.getMessage());
                                    }
                                });
                                UserManager.getInstance().setCurrentUser(user);
                            }
                            else
                            {
                                Toast.makeText(RegisterAccount.this,"Registration failed: " + task.getException().getMessage(),Toast.LENGTH_LONG).show();
                            }
                        }
                    });

                }
            }

        });
    }

    @Override
    public void onClick(View view)
    {
        if (view.getId() == R.id.backButton)
        {
            Intent prev = new Intent(this, MainActivity.class);
            startActivity(prev);
        }
    }

    // function to calculate date of birth, that takes a string value of the date of birth
    // in the format of MM-DD-YYYY
    public static long calcAge(String dob)
    {
        SimpleDateFormat sdf1 = new SimpleDateFormat("mm-dd-yyyy");
        Date udob = null;
        try {
            udob = sdf1.parse(dob);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        Date sysdate=new Date();

        long ms=System.currentTimeMillis()-udob.getTime();

        long age = (long)((long)ms/(1000.0*60*60*24*365));

        // String ageStr = String.valueOf(age);

        return age;

    }
}
