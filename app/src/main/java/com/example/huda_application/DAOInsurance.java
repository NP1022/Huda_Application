package com.example.huda_application;

import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class DAOInsurance
{
    private DatabaseReference databaseReference;

    public DAOInsurance()
    {
        FirebaseDatabase db = FirebaseDatabase.getInstance();
        databaseReference = db.getReference(patientInsurance.class.getSimpleName());
    }

    public Task<Void> add(patientInsurance info)
    {
        return databaseReference.push().setValue(info);
    }
}
