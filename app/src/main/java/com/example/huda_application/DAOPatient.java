package com.example.huda_application;

import com.example.huda_application.user.PatientFormData;
import com.google.firebase.database.DatabaseReference;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class DAOPatient
{

    private DatabaseReference dbRef;

    public DAOPatient()
    {
        FirebaseDatabase db = FirebaseDatabase.getInstance();                      // Function used to get teh reference of the patient in the database as the user for the application
        dbRef = db.getReference(PatientFormData.class.getSimpleName());
    }

    public Task<Void> add(PatientFormData patient)
    {
        return dbRef.push().setValue(patient);
    }
}


