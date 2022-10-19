package com.example.huda_application;

import com.google.firebase.database.DatabaseReference;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class DAOPatient
{

    private DatabaseReference dbRef;

    public DAOPatient()
    {
        FirebaseDatabase db = FirebaseDatabase.getInstance();
        dbRef = db.getReference(Patient.class.getSimpleName());
    }

    public Task<Void> add(Patient patient)
    {
        return dbRef.push().setValue(patient);
    }
}

//package com.example.huda_application;
//
//        import com.google.android.gms.tasks.Task;
//        import com.google.firebase.database.DatabaseReference;
//        import com.google.firebase.database.FirebaseDatabase;
//
//public class DAOUser
//{
//    private DatabaseReference dbRef;
//
//    public DAOUser()
//    {
//        FirebaseDatabase db = FirebaseDatabase.getInstance();
//        dbRef = db.getReference(User.class.getSimpleName());
//    }
//
//    public Task<Void> add(User user)
//    {
//        return dbRef.push().setValue(user);
//    }
//
//}
