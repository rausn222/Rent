package com.example.raushan.rent;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class EditProperty extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_property);
        String post_key = getIntent().getExtras().getString("PostId");
    }
}
