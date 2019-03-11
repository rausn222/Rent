package com.example.raushan.rent;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.StorageReference;
import com.squareup.picasso.Picasso;

public class ViewProperty extends AppCompatActivity {
    private String post_key,post_image = null;
    private DatabaseReference mDatabase;
    private ImageView singlePostImage;
    TextView t1,t2,t3,t4,t5,t6,t7,t8,t9,t10,t11,t12,t13,t14,t15,t16,t17,t18,t19;
    Button bt;
    String post_id1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_property);
        post_key = getIntent().getExtras().getString("PostId");
        post_image = getIntent().getExtras().getString("PostImage");
        mDatabase = FirebaseDatabase.getInstance().getReference().child("image");
        singlePostImage = (ImageView)findViewById(R.id.singleImageVie);
        t1 = (TextView)findViewById(R.id.singleTitl);
        t2 = (TextView)findViewById(R.id.singleDes);
        t3 = (TextView)findViewById(R.id.textView12);
        t4 = (TextView)findViewById(R.id.textView13);
        t5 = (TextView)findViewById(R.id.textView14);
        t6 = (TextView)findViewById(R.id.textView15);
        t7 = (TextView)findViewById(R.id.textView16);
        t8 = (TextView)findViewById(R.id.textView17);
        t9 = (TextView)findViewById(R.id.textView18);
        t10 = (TextView)findViewById(R.id.textView19);
        t11 = (TextView)findViewById(R.id.textView20);
        t12 = (TextView)findViewById(R.id.textView21);
        t13 = (TextView)findViewById(R.id.textView22);
        t14 = (TextView)findViewById(R.id.textView23);
        t15 = (TextView)findViewById(R.id.textView24);
        t16 = (TextView)findViewById(R.id.textView25);
        t17 = (TextView)findViewById(R.id.textView26);
        t18 = (TextView)findViewById(R.id.textView27);
        t19 = (TextView)findViewById(R.id.textView28);
        bt = (Button)findViewById(R.id.button3);
        bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(ViewProperty.this,Main3Activity.class);
                i.putExtra("key",post_key);
                startActivity(i);
            }
        });
        mDatabase.child(post_key).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                String post_tite = (String)dataSnapshot.child("title").getValue();
                String post_desc = (String)dataSnapshot.child("desc").getValue();
                String post_image = (String)dataSnapshot.child("image").getValue();
                String proprty_type = (String)dataSnapshot.child("proptype").getValue();
                String cust_num = (String)dataSnapshot.child("custnum").getValue();
                String altcust_num = (String)dataSnapshot.child("altnum").getValue();
                String cust_em_site = (String)dataSnapshot.child("email").getValue();
                String prop_area = (String)dataSnapshot.child("area").getValue();
                String prop_varaint = (String)dataSnapshot.child("variant").getValue();
                String prop_locality = (String)dataSnapshot.child("locality").getValue();
                Picasso.with(ViewProperty.this).load(post_image).into(singlePostImage);
                t1.setText(post_tite);
                t2.setText(post_desc);
                t3.setText(cust_num);
                if (altcust_num.equals(""))
                {
                    t4.setVisibility(View.GONE);
                }
                t4.setText(altcust_num);
                t5.setText(cust_em_site);
                t6.setText(proprty_type);
                t7.setText(prop_area);
                t8.setText(prop_varaint);
                t9.setText(prop_locality);
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
                    t10.setText(prop_size);
                    t11.setText(prop_fur);
                    t12.setText(prop_rent);
                    t13.setText(ava_date);
                    t14.setText(deposit);
                    t15.setText(brokerage);
                    t16.setText(tenant);
                    t17.setText(sply);
                    t18.setVisibility(View.GONE);
                    t19.setVisibility(View.GONE);
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
                    t10.setText(prop_size);
                    t11.setText(prop_fur);
                    t12.setText(prop_vac);
                    t13.setText(prop_ava_vac);
                    t14.setText(prop_rent);
                    t15.setText(ava_date);
                    t16.setText(deposit);
                    t17.setText(brokerage);
                    t18.setText(tenant);
                    t19.setText(sply);
                }
                else if (proprty_type.equals("Serviced Apartment"))
                {
                    String prop_size = (String)dataSnapshot.child("size").getValue();
                    String prop_facilities = (String)dataSnapshot.child("facilities").getValue();
                    String prop_rent = (String)dataSnapshot.child("rent").getValue();
                    String sply = (String)dataSnapshot.child("sply").getValue();
                    t10.setText(prop_size);
                    t11.setText(prop_facilities);
                    t12.setText(prop_rent);
                    t13.setText(sply);
                    t14.setVisibility(View.GONE);
                    t15.setVisibility(View.GONE);
                    t16.setVisibility(View.GONE);
                    t17.setVisibility(View.GONE);
                    t18.setVisibility(View.GONE);
                    t19.setVisibility(View.GONE);
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
                    t10.setText(prop_vacancy);
                    t11.setText(prop_facilities);
                    t12.setText(prop_vac);
                    t13.setText(prop_rent);
                    t14.setText(deposit);
                    t15.setText(brokerage);
                    t16.setText(ava_date);
                    t17.setText(tenant);
                    t18.setText(sply);
                    t19.setVisibility(View.GONE);
                }
                else if (proprty_type.equals("Hotel") || proprty_type.equals("Home-Stay"))
                {
                    String prop_facilities = (String)dataSnapshot.child("facilities").getValue();
                    String prop_tarrif = (String)dataSnapshot.child("tarrif").getValue();
                    String prop_type = (String)dataSnapshot.child("propertytype").getValue();
                    String sply = (String)dataSnapshot.child("sply").getValue();
                    t10.setText(prop_facilities);
                    t11.setText(prop_tarrif);
                    t12.setText(prop_type);
                    t13.setText(sply);
                    t14.setVisibility(View.GONE);
                    t15.setVisibility(View.GONE);
                    t16.setVisibility(View.GONE);
                    t17.setVisibility(View.GONE);
                    t18.setVisibility(View.GONE);
                    t19.setVisibility(View.GONE);
                }
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }
}