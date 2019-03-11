package com.example.raushan.rent;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Customer_Info extends AppCompatActivity {
    EditText ed1,ed2,ed3,ed4,ed5;
    int i=1;
    Button b;
    String cust_name,prop_name,cust_num,altcust_num,cust_email;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer__info);
        ed1 = (EditText)findViewById(R.id.editText4);
        final String proptype = getIntent().getStringExtra("proptype1");
        ed2 = (EditText)findViewById(R.id.editText5);
        ed3 = (EditText)findViewById(R.id.cno);
        ed4 = (EditText)findViewById(R.id.editText8);
        ed5 = (EditText)findViewById(R.id.editText9);
        b = (Button)findViewById(R.id.button5);
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cust_name = ed1.getText().toString();
                prop_name=ed2.getText().toString();
                cust_num=ed3.getText().toString();
                altcust_num=ed4.getText().toString();
                cust_email=ed5.getText().toString();
                isValidUrl(cust_email);
                String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
                if (cust_name.isEmpty())
                {
                    ed1.setError("Please Enter Customer's Name here");
                    ed1.requestFocus();
                }
                else if(cust_num.isEmpty())
                {
                    ed3.setError("Please Enter Number");
                    ed3.requestFocus();
                }
                else if(cust_num.length()!=10)
                {
                    ed3.setError("Please Enter valid Number");
                    ed3.requestFocus();
                }
                else if (!cust_email.matches(emailPattern) && !(i==0))
                {
                    ed5.setError("Enter a valid email or website");
                    ed5.requestFocus();
                }
                else if(!altcust_num.equals(""))
                {
                    if (altcust_num.length()!=10)
                    {
                        ed4.setError("Enter a valid phone");
                    }
                    else {
                        Intent i = new Intent(Customer_Info.this,Step4.class);
                        i.putExtra("proptype2",proptype);
                        i.putExtra("cust_name2",cust_name);
                        i.putExtra("prop_name2",prop_name);
                        i.putExtra("cust_num2",cust_num);
                        i.putExtra("alt_num2",altcust_num);
                        i.putExtra("cust_em_site2",cust_email);
                        startActivity(i);
                    }
                }
                else{
                    Intent i = new Intent(Customer_Info.this,Step4.class);
                    i.putExtra("proptype2",proptype);
                    i.putExtra("cust_name2",cust_name);
                    i.putExtra("prop_name2",prop_name);
                    i.putExtra("cust_num2",cust_num);
                    i.putExtra("alt_num2",altcust_num);
                    i.putExtra("cust_em_site2",cust_email);
                    startActivity(i);
                }
            }
        });
    }
    private void isValidUrl(String url) {
        Pattern p = Patterns.WEB_URL;
        Matcher m = p.matcher(url);
        if(m.matches())
            i=0;
        else
            i=1;
    }
}