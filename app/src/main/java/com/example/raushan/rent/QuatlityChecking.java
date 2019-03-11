package com.example.raushan.rent;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class QuatlityChecking extends AppCompatActivity {
    private DatabaseReference databaseReference1;
    private FirebaseDatabase database;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quatlity_checking);
        upload();
    }
    void upload(){
        databaseReference1 = database.getInstance().getReference().child("teamleader");
        final DatabaseReference newPost1 = databaseReference1.push();
        newPost1.child("title").setValue("1");
        newPost1.child("proptype").setValue("2");
        newPost1.child("status").setValue("Uploaded");
    }
}
