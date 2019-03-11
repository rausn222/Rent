package com.example.raushan.rent;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

public class Step4 extends AppCompatActivity {
    private Spinner area,variants;
    private Button bb;
    private EditText editText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_step4);
        area = (Spinner)findViewById(R.id.spinner2);
        variants = (Spinner)findViewById(R.id.spinner3);
        editText = (EditText)findViewById(R.id.editText6);
        final String proptype = getIntent().getStringExtra("proptype2");
        final String cust_name = getIntent().getStringExtra("cust_name2");
        final String prop_name = getIntent().getStringExtra("prop_name2");
        final String cust_num = getIntent().getStringExtra("cust_num2");
        final String altcust_num = getIntent().getStringExtra("alt_num2");
        final String cust_em_site = getIntent().getStringExtra("cust_em_site2");
        bb= (Button)findViewById(R.id.button6);
        area.setFocusable(true);
        area.setFocusableInTouchMode(true);
        area.requestFocus();
        bb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String sarea = area.getSelectedItem().toString();
                final String svariant = variants.getSelectedItem().toString();
                final String slocality = editText.getText().toString();
                if(sarea.equals("Select area of property"))
                {
                    Toast.makeText(Step4.this,"Please select type of area",Toast.LENGTH_SHORT).show();
                }
                else if(slocality.equals(""))
                {
                    Toast.makeText(Step4.this,"Please enter locality",Toast.LENGTH_SHORT).show();
                }
                else if (svariant.equals("Select no of variants of property"))
                {
                    Toast.makeText(Step4.this,"Please select no of variant",Toast.LENGTH_SHORT).show();
                }
                else
                {
                    Intent i =new Intent(Step4.this, ImageUpload.class);
                    i.putExtra("proptype2",proptype);
                    i.putExtra("cust_name2",cust_name);
                    i.putExtra("prop_name2",prop_name);
                    i.putExtra("cust_num2",cust_num);
                    i.putExtra("alt_num2",altcust_num);
                    i.putExtra("cust_em_site2",cust_em_site);
                    i.putExtra("prop_area2",sarea);
                    i.putExtra("prop_variant2",svariant);
                    i.putExtra("prop_locality2",slocality);
                    startActivity(i);
                }
            }
        });
    }
}
