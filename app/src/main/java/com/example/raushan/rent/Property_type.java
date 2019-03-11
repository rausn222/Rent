package com.example.raushan.rent;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

public class Property_type extends AppCompatActivity {
    private Spinner sp1;
    Button b1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_property_type);
        sp1 = (Spinner)findViewById(R.id.spinner);
        b1 = (Button)findViewById(R.id.button5);
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String s=sp1.getSelectedItem().toString();
                if(s.equals("Select type of property"))
                {
                    Toast.makeText(Property_type.this,"Please select a type",Toast.LENGTH_SHORT).show();
                }
                else {
                    Intent i = new Intent(Property_type.this,Customer_Info.class);
                    i.putExtra("proptype1",s);
                    startActivity(i);
                }
            }
        });
    }
}
