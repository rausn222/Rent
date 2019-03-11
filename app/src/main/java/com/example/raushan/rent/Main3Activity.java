package com.example.raushan.rent;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Main3Activity extends AppCompatActivity {
    String post_key1;
    private DatabaseReference mDatabase;
    EditText ed1,ed2,ed3,ed4,ed5,ed6,ed7,ed8,ed9,ed10,ed11,ed12,ed13,ed14,ed15,ed16,ed17,ed18,ed19;
    Button btn;
    String proprty_type;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);
        post_key1 = getIntent().getExtras().getString("key");
        mDatabase = FirebaseDatabase.getInstance().getReference().child("image");
        ed1 = (EditText)findViewById(R.id.editprop1);
        ed2 = (EditText)findViewById(R.id.editprop2);
        ed3 = (EditText)findViewById(R.id.editprop3);
        ed4 = (EditText)findViewById(R.id.editprop4);
        ed5 = (EditText)findViewById(R.id.editprop5);
        ed6 = (EditText)findViewById(R.id.editprop6);
        ed7 = (EditText)findViewById(R.id.editprop7);
        ed8 = (EditText)findViewById(R.id.editprop8);
        ed9 = (EditText)findViewById(R.id.editprop9);
        ed10 = (EditText)findViewById(R.id.editprop10);
        ed11 = (EditText)findViewById(R.id.editprop11);
        ed12 = (EditText)findViewById(R.id.editprop12);
        ed13 = (EditText)findViewById(R.id.editprop13);
        ed14 = (EditText)findViewById(R.id.editprop14);
        ed15 = (EditText)findViewById(R.id.editprop15);
        ed16 = (EditText)findViewById(R.id.editprop16);
        ed17 = (EditText)findViewById(R.id.editprop17);
        ed18 = (EditText)findViewById(R.id.editprop18);
        ed19 = (EditText)findViewById(R.id.editprop19);
        btn = (Button)findViewById(R.id.button4);
        mDatabase.child(post_key1).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                String post_tite = (String)dataSnapshot.child("title").getValue();
                String post_desc = (String)dataSnapshot.child("desc").getValue();
                proprty_type = (String)dataSnapshot.child("proptype").getValue();
                String cust_num = (String)dataSnapshot.child("custnum").getValue();
                String altcust_num = (String)dataSnapshot.child("altnum").getValue();
                String cust_em_site = (String)dataSnapshot.child("email").getValue();
                String prop_area = (String)dataSnapshot.child("area").getValue();
                String prop_varaint = (String)dataSnapshot.child("variant").getValue();
                String prop_locality = (String)dataSnapshot.child("locality").getValue();
                ed1.setText(post_tite);
                ed2.setText(post_desc);
                ed3.setText(cust_num);
                ed4.setText(altcust_num);
                ed5.setText(cust_em_site);
                ed6.setText(proprty_type);
                ed7.setText(prop_area);
                ed8.setText(prop_varaint);
                ed9.setText(prop_locality);
                if (proprty_type.equals("Complete Apartment"))
                {
                    String prop_size = (String)dataSnapshot.child("size").getValue();
                    String prop_fur = (String)dataSnapshot.child("fur").getValue();
                    String prop_rent = (String)dataSnapshot.child("rent").getValue();
                    String ava_date = (String)dataSnapshot.child("date").getValue();
                    String deposit = (String)dataSnapshot.child("deposit").getValue();
                    String brokerage = (String)dataSnapshot.child("brokerage").getValue();
                    String tenant = (String)dataSnapshot.child("ten").getValue();
                    String sply = (String)dataSnapshot.child("sply").getValue();
                    ed10.setText(prop_size);
                    ed11.setText(prop_fur);
                    ed12.setText(prop_rent);
                    ed13.setText(ava_date);
                    ed14.setText(deposit);
                    ed15.setText(brokerage);
                    ed16.setText(tenant);
                    ed17.setText(sply);
                    ed18.setVisibility(View.GONE);
                    ed19.setVisibility(View.GONE);
                }
                else if (proprty_type.equals("Sharing Apartment"))
                {
                    String prop_size = (String)dataSnapshot.child("size").getValue();
                    String prop_fur = (String)dataSnapshot.child("fur").getValue();
                    String prop_vac = (String)dataSnapshot.child("vac").getValue();
                    String prop_ava_vac = (String)dataSnapshot.child("ava_vac").getValue();
                    String prop_rent = (String)dataSnapshot.child("rent").getValue();
                    String ava_date = (String)dataSnapshot.child("date").getValue();
                    String deposit = (String)dataSnapshot.child("deposit").getValue();
                    String brokerage = (String)dataSnapshot.child("brokerage").getValue();
                    String tenant = (String)dataSnapshot.child("ten").getValue();
                    String sply = (String)dataSnapshot.child("sply").getValue();
                    ed10.setText(prop_size);
                    ed11.setText(prop_fur);
                    ed12.setText(prop_vac);
                    ed13.setText(prop_ava_vac);
                    ed14.setText(prop_rent);
                    ed15.setText(ava_date);
                    ed16.setText(deposit);
                    ed17.setText(brokerage);
                    ed18.setText(tenant);
                    ed19.setText(sply);
                }
                else if (proprty_type.equals("Serviced Apartment"))
                {
                    String prop_size = (String)dataSnapshot.child("size").getValue();
                    String prop_facilities = (String)dataSnapshot.child("facilities").getValue();
                    String prop_rent = (String)dataSnapshot.child("rent").getValue();
                    String sply = (String)dataSnapshot.child("sply").getValue();
                    ed10.setText(prop_size);
                    ed11.setText(prop_facilities);
                    ed12.setText(prop_rent);
                    ed13.setText(sply);
                    ed14.setVisibility(View.GONE);
                    ed15.setVisibility(View.GONE);
                    ed16.setVisibility(View.GONE);
                    ed17.setVisibility(View.GONE);
                    ed18.setVisibility(View.GONE);
                    ed19.setVisibility(View.GONE);
                }
                else if (proprty_type.equals("PG"))
                {
                    String prop_vacancy = (String)dataSnapshot.child("vacn").getValue();
                    String prop_facilities = (String)dataSnapshot.child("facilities").getValue();
                    String prop_vac = (String)dataSnapshot.child("vac").getValue();
                    String prop_rent = (String)dataSnapshot.child("rent").getValue();
                    String deposit = (String)dataSnapshot.child("deposit").getValue();
                    String brokerage = (String)dataSnapshot.child("brokerage").getValue();
                    String ava_date = (String)dataSnapshot.child("date").getValue();
                    String tenant = (String)dataSnapshot.child("ten").getValue();
                    String sply = (String)dataSnapshot.child("sply").getValue();
                    ed10.setText(prop_vacancy);
                    ed11.setText(prop_facilities);
                    ed12.setText(prop_vac);
                    ed13.setText(prop_rent);
                    ed14.setText(deposit);
                    ed15.setText(brokerage);
                    ed16.setText(ava_date);
                    ed17.setText(tenant);
                    ed18.setText(sply);
                    ed19.setVisibility(View.GONE);
                }
                else if (proprty_type.equals("Hotel") || proprty_type.equals("Home-Stay")) {
                    String prop_facilities = (String) dataSnapshot.child("facilities").getValue();
                    String prop_tarrif = (String) dataSnapshot.child("tarrif").getValue();
                    String prop_type = (String) dataSnapshot.child("propertytype").getValue();
                    String sply = (String) dataSnapshot.child("sply").getValue();
                    ed10.setText(prop_facilities);
                    ed11.setText(prop_tarrif);
                    ed12.setText(prop_type);
                    ed13.setText(sply);
                    ed14.setVisibility(View.GONE);
                    ed15.setVisibility(View.GONE);
                    ed16.setVisibility(View.GONE);
                    ed17.setVisibility(View.GONE);
                    ed18.setVisibility(View.GONE);
                    ed19.setVisibility(View.GONE);
                }
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {
            }
        });
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String str1 = ed1.getText().toString();
                String str2 = ed2.getText().toString();
                String str3 = ed3.getText().toString();
                String str4 = ed4.getText().toString();
                String str5 = ed5.getText().toString();
                String str6 = ed6.getText().toString();
                String str7 = ed7.getText().toString();
                String str8 = ed8.getText().toString();
                String str9 = ed9.getText().toString();
                String str10 = ed10.getText().toString();
                String str11 = ed11.getText().toString();
                String str12 = ed12.getText().toString();
                String str13 = ed13.getText().toString();
                String str14 = ed14.getText().toString();
                String str15 = ed15.getText().toString();
                String str16 = ed16.getText().toString();
                String str17 = ed17.getText().toString();
                String str18 = ed18.getText().toString();
                String str19 = ed19.getText().toString();
                mDatabase.child(post_key1).child("title").setValue(str1);
                mDatabase.child(post_key1).child("desc").setValue(str2);
                mDatabase.child(post_key1).child("custnum").setValue(str3);
                mDatabase.child(post_key1).child("altnum").setValue(str4);
                mDatabase.child(post_key1).child("email").setValue(str5);
                mDatabase.child(post_key1).child("proptype").setValue(str6);
                mDatabase.child(post_key1).child("area").setValue(str7);
                mDatabase.child(post_key1).child("variant").setValue(str8);
                mDatabase.child(post_key1).child("locality").setValue(str9);
                if (proprty_type.equals("Complete Apartment"))
                {
                    mDatabase.child(post_key1).child("size").setValue(str10);
                    mDatabase.child(post_key1).child("fur").setValue(str11);
                    mDatabase.child(post_key1).child("rent").setValue(str12);
                    mDatabase.child(post_key1).child("date").setValue(str13);
                    mDatabase.child(post_key1).child("deposit").setValue(str14);
                    mDatabase.child(post_key1).child("brokerage").setValue(str15);
                    mDatabase.child(post_key1).child("ten").setValue(str16);
                    mDatabase.child(post_key1).child("sply").setValue(str17);
                }
                else if (proprty_type.equals("Sharing Apartment"))
                {
                    mDatabase.child(post_key1).child("size").setValue(str10);
                    mDatabase.child(post_key1).child("fur").setValue(str11);
                    mDatabase.child(post_key1).child("vac").setValue(str12);
                    mDatabase.child(post_key1).child("ava_vac").setValue(str13);
                    mDatabase.child(post_key1).child("rent").setValue(str14);
                    mDatabase.child(post_key1).child("date").setValue(str15);
                    mDatabase.child(post_key1).child("deposit").setValue(str16);
                    mDatabase.child(post_key1).child("brokerage").setValue(str17);
                    mDatabase.child(post_key1).child("ten").setValue(str18);
                    mDatabase.child(post_key1).child("sply").setValue(str19);
                }
                else if (proprty_type.equals("Serviced Apartment"))
                {
                    mDatabase.child(post_key1).child("size").setValue(str10);
                    mDatabase.child(post_key1).child("facilities").setValue(str11);
                    mDatabase.child(post_key1).child("rent").setValue(str12);
                    mDatabase.child(post_key1).child("sply").setValue(str13);
                }
                else if (proprty_type.equals("PG"))
                {
                    mDatabase.child(post_key1).child("vacn").setValue(str10);
                    mDatabase.child(post_key1).child("facilities").setValue(str11);
                    mDatabase.child(post_key1).child("vac").setValue(str12);
                    mDatabase.child(post_key1).child("rent").setValue(str13);
                    mDatabase.child(post_key1).child("deposit").setValue(str14);
                    mDatabase.child(post_key1).child("brokerage").setValue(str15);
                    mDatabase.child(post_key1).child("date").setValue(str16);
                    mDatabase.child(post_key1).child("ten").setValue(str17);
                    mDatabase.child(post_key1).child("sply").setValue(str18);
                }
                else if (proprty_type.equals("Hotel") || proprty_type.equals("Home-Stay"))
                {
                    mDatabase.child(post_key1).child("facilities").setValue(str10);
                    mDatabase.child(post_key1).child("tarrif").setValue(str11);
                    mDatabase.child(post_key1).child("propertytype").setValue(str12);
                    mDatabase.child(post_key1).child("sply").setValue(str13);
                }
                startActivity(new Intent(Main3Activity.this,MainActivity.class));
            }
        });
    }
}
