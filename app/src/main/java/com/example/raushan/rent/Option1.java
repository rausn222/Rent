package com.example.raushan.rent;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.util.Calendar;

public class Option1 extends AppCompatActivity {
    Spinner sp1,sp2,sp3;
    EditText ed1,ed2,ed3,ed4;
    String s1,s2,s3,s4,s5,s6,s7,s8;
    int rent,deposit,brokerage;
    Calendar mCalender;
    int day,month,year;
    TextView tv;
    Button b1;
    int count;
    String proptype,cust_name,prop_name,cust_num,altcust_num,cust_em_site,prop_area,prop_variant,prop_locality,times,uristring;
    private StorageReference storageReference;
    private FirebaseDatabase database;
    private Uri uri = null;
    private DatabaseReference databaseReference,databaseReference1;
    ProgressBar progressBar;
    Uri downloadurl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_option1);
        sp1 = (Spinner)findViewById(R.id.spinner4);
        sp2 = (Spinner)findViewById(R.id.spinner5);
        sp3 = (Spinner)findViewById(R.id.spinner7);
        b1 = (Button)findViewById(R.id.cobb);
        ed1 = (EditText)findViewById(R.id.editText7);
        ed2 = (EditText)findViewById(R.id.editText10);
        tv = (TextView)findViewById(R.id.textView4);
        progressBar = (ProgressBar)findViewById(R.id.op1progressbar);
        storageReference = FirebaseStorage.getInstance().getReference();
        databaseReference = database.getInstance().getReference().child("image");
        databaseReference1 = database.getInstance().getReference().child("teamleader");
        final DatabaseReference newPost = databaseReference.push();
        final DatabaseReference newPost1 = databaseReference1.push();
        proptype = getIntent().getStringExtra("proptype2");
        cust_name = getIntent().getStringExtra("cust_name2");
        prop_name = getIntent().getStringExtra("prop_name2");
        cust_num = getIntent().getStringExtra("cust_num2");
        altcust_num = getIntent().getStringExtra("alt_num2");
        cust_em_site = getIntent().getStringExtra("cust_em_site2");
        prop_area = getIntent().getStringExtra("prop_area2");
        prop_variant = getIntent().getStringExtra("prop_variant2");
        prop_locality = getIntent().getStringExtra("prop_locality2");
        times = getIntent().getStringExtra("times2");
        uristring = getIntent().getStringExtra("uri1");
        Toast.makeText(Option1.this,uristring,Toast.LENGTH_SHORT).show();
        mCalender = Calendar.getInstance();
        day = mCalender.get(Calendar.DAY_OF_MONTH);
        month = mCalender.get(Calendar.MONTH);
        year = mCalender.get(Calendar.YEAR);
        month = month+1;
        ed3 = (EditText)findViewById(R.id.editText11);
        ed4 = (EditText)findViewById(R.id.editText8);
        sp1.setFocusable(true);
        sp1.setFocusableInTouchMode(true);
        sp1.requestFocus();
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                s1 = sp1.getSelectedItem().toString();
                s2 = sp2.getSelectedItem().toString();
                s6 = sp3.getSelectedItem().toString();
                s3 = ed1.getText().toString();
                s4 = ed2.getText().toString();
                s5 = ed3.getText().toString();
                s7 = ed4.getText().toString();
                s8 = tv.getText().toString();
                if(s1.equals("Select size of property"))
                {
                    Toast.makeText(Option1.this,"Please select size of property.",Toast.LENGTH_SHORT).show();
                }
                else if (s2.equals("Select furnishing level"))
                {
                    Toast.makeText(Option1.this,"Please select furnishing level of property.",Toast.LENGTH_SHORT).show();
                }
                else if (s3.equals(""))
                {
                    Toast.makeText(Option1.this,"Please enter rent.",Toast.LENGTH_SHORT).show();
                }
                else if (!s3.equals(""))
                {
                    rent = Integer.parseInt(s3);
                    if (rent<=1)
                    {
                        Toast.makeText(Option1.this,"Please enter rent above 1.",Toast.LENGTH_SHORT).show();
                    }
                    else if (s8.equals("  Available From"))
                    {
                        Toast.makeText(Option1.this,"Please select date.",Toast.LENGTH_SHORT).show();
                    }
                    else if (s4.equals(""))
                    {
                        Toast.makeText(Option1.this,"Please enter deposit amount.",Toast.LENGTH_SHORT).show();
                    }
                    else if (!s4.equals(""))
                    {
                        deposit = Integer.parseInt(s4);
                        if (deposit<=1)
                        {
                            Toast.makeText(Option1.this,"Please enter deposit above 1.",Toast.LENGTH_SHORT).show();
                        }
                        else if (s5.equals(""))
                        {
                            Toast.makeText(Option1.this,"Please enter brokerage.",Toast.LENGTH_SHORT).show();
                        }
                        else if (!s5.equals("0"))
                        {
                            brokerage = Integer.parseInt(s5);
                            if (brokerage<=0)
                            {
                                Toast.makeText(Option1.this,"Please enter brokerage above 0.",Toast.LENGTH_SHORT).show();
                            }
                            else if (s6.equals("Select preferred tenant"))
                            {
                                Toast.makeText(Option1.this,"Please select preferred tenant.",Toast.LENGTH_SHORT).show();
                            }
                            else if (s7.equals("")){
                                Toast.makeText(Option1.this,"Please enter supply notes",Toast.LENGTH_SHORT).show();
                            }
                            else {
                                uri =Uri.parse(uristring);
                                AlertDialog alertDialog = new AlertDialog.Builder(Option1.this).create();
                                alertDialog.setTitle("Brokerage Alert");
                                alertDialog.setMessage("Property involves brokerage!");
                                alertDialog.setButton("OK", new DialogInterface.OnClickListener(){
                                    public void onClick(final DialogInterface dialog, int which) {
                                        if (uri != null) {
                                            final ProgressDialog dialog1 = new ProgressDialog(Option1.this);
                                            dialog1.setTitle("Uploading Data");
                                            dialog1.show();
                                            StorageReference filePath = storageReference.child("PostImage").child(uri.getLastPathSegment());
                                            filePath.putFile(uri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                                                @Override
                                                public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                                                    dialog1.dismiss();
                                                    downloadurl = taskSnapshot.getDownloadUrl();
                                                    if (s1.equals("One BHK")) {
                                                        if (s2.equals("Fully Furnished")) {
                                                            if (prop_area.equals("Airoli") || prop_area.equals("Ghansoli") || prop_area.equals("Panvel")) {
                                                                if (rent > 7500) {
                                                                    uploadLeader();
                                                                } else if (rent <= 7500) {
                                                                    uploadData();
                                                                }
                                                            } else if (prop_area.equals("Amboli")) {
                                                                if (rent > 13000) {
                                                                    uploadLeader();
                                                                } else if (rent <= 13000) {
                                                                    uploadData();
                                                                }
                                                            } else if (prop_area.equals("Andheri E") || prop_area.equals("Andheri W") || prop_area.equals("Goregaon E") || prop_area.equals("Khar W") || prop_area.equals("Khar E") || prop_area.equals("Kurla E") || prop_area.equals("Kurla W") || prop_area.equals("Malad W")) {
                                                                if (rent > 16000) {
                                                                    uploadLeader();
                                                                } else if (rent <= 16000) {
                                                                    uploadData();
                                                                }
                                                            } else if (prop_area.equals("Bandra E") || prop_area.equals("Bandra W") || prop_area.equals("Fort") || prop_area.equals("Sion E") || prop_area.equals("Sion W") || prop_area.equals("Worli")) {
                                                                if (rent > 20000) {
                                                                    uploadLeader();
                                                                } else if (rent <= 20000) {
                                                                    uploadData();
                                                                }
                                                            } else if (prop_area.equals("Bhandup E") || prop_area.equals("Bhandup W") || prop_area.equals("Kandivali E") || prop_area.equals("Kandivali W") || prop_area.equals("Malad E")) {
                                                                if (rent > 14000) {
                                                                    uploadLeader();
                                                                } else if (rent <= 14000) {
                                                                    uploadData();
                                                                }
                                                            } else if (prop_area.equals("Boisar")) {
                                                                if (rent > 9500) {
                                                                    uploadLeader();
                                                                } else if (rent <= 9500) {
                                                                    uploadData();
                                                                }
                                                            } else if (prop_area.equals("Borivali E") || prop_area.equals("Borivali W") || prop_area.equals("Kharegaon")) {
                                                                if (rent > 12000) {
                                                                    uploadLeader();
                                                                } else if (rent <= 12000) {
                                                                    uploadData();
                                                                }
                                                            } else if (prop_area.equals("Chembur") || prop_area.equals("Goregaon W")) {
                                                                if (rent > 18000) {
                                                                    uploadLeader();
                                                                } else if (rent <= 18000) {
                                                                    uploadData();
                                                                }
                                                            } else if (prop_area.equals("Church Gate")) {
                                                                if (rent > 25000) {
                                                                    uploadLeader();
                                                                } else if (rent <= 25000) {
                                                                    uploadData();
                                                                }
                                                            } else if (prop_area.equals("Colaba")) {
                                                                if (rent > 26000) {
                                                                    uploadLeader();
                                                                } else if (rent <= 26000) {
                                                                    uploadData();
                                                                }
                                                            } else if (prop_area.equals("Dadar E") || prop_area.equals("Dadar W") || prop_area.equals("Prabhadevi")) {
                                                                if (rent > 23000) {
                                                                    uploadLeader();
                                                                } else if (rent <= 23000) {
                                                                    uploadData();
                                                                }
                                                            } else if (prop_area.equals("Ghatkopar E") || prop_area.equals("Ghatkopar W") || prop_area.equals("Wadala E") || prop_area.equals("Wadala W")) {
                                                                if (rent > 17000) {
                                                                    uploadLeader();
                                                                } else if (rent <= 17000) {
                                                                    uploadData();
                                                                }
                                                            } else if (prop_area.equals("Govandi E") || prop_area.equals("Govandi W") || prop_area.equals("Kopar Khairane") || prop_area.equals("Mire Road E") || prop_area.equals("Mire Road W") || prop_area.equals("Thane E") || prop_area.equals("Thane W")) {
                                                                if (rent > 10000) {
                                                                    uploadLeader();
                                                                } else if (rent <= 10000) {
                                                                    uploadData();
                                                                }
                                                            } else if (prop_area.equals("Jogeshwari E") || prop_area.equals("Jogeshwari W") || prop_area.equals("Kalina") || prop_area.equals("Santa Cruz E") || prop_area.equals("Santa Cruz W") || prop_area.equals("Vile Parle E") || prop_area.equals("Vile Parle W")) {
                                                                if (rent > 15000) {
                                                                    uploadLeader();
                                                                } else if (rent <= 15000) {
                                                                    uploadData();
                                                                }
                                                            } else if (prop_area.equals("Lower Parel") || prop_area.equals("Mahalaxmi") || prop_area.equals("Parel") || prop_area.equals("Powai")) {
                                                                if (rent > 22000) {
                                                                    uploadLeader();
                                                                } else if (rent <= 22000) {
                                                                    uploadData();
                                                                }
                                                            } else if (prop_area.equals("Mahim E") || prop_area.equals("Mahim W") || prop_area.equals("Vikhroli E") || prop_area.equals("Vikhroli W")) {
                                                                if (rent > 19000) {
                                                                    uploadLeader();
                                                                } else if (rent <= 19000) {
                                                                    uploadData();
                                                                }
                                                            } else if (prop_area.equals("Mulund E") || prop_area.equals("Mulund W")) {
                                                                if (rent > 11000) {
                                                                    uploadLeader();
                                                                } else if (rent <= 11000) {
                                                                    uploadData();
                                                                }
                                                            } else if (prop_area.equals("Vashi")) {
                                                                if (rent > 9000) {
                                                                    uploadLeader();
                                                                } else if (rent <= 9000) {
                                                                    uploadData();
                                                                }
                                                            }
                                                        } else {
                                                            if (prop_area.equals("Airoli") || prop_area.equals("Ghansoli") || prop_area.equals("Panvel")) {
                                                                if (rent > 3500) {
                                                                    uploadLeader();
                                                                } else if (rent <= 3500){
                                                                    uploadData();
                                                                }
                                                            } else if (prop_area.equals("Amboli")) {
                                                                if (rent > 9000) {
                                                                    uploadLeader();
                                                                } else if (rent <= 9000) {
                                                                    uploadData();
                                                                }
                                                            } else if (prop_area.equals("Andheri E") || prop_area.equals("Andheri W") || prop_area.equals("Goregaon E") || prop_area.equals("Khar W") || prop_area.equals("Khar E") || prop_area.equals("Kurla E") || prop_area.equals("Kurla W") || prop_area.equals("Malad W")) {
                                                                if (rent > 12000) {
                                                                    uploadLeader();
                                                                } else if (rent <= 12000) {
                                                                    uploadData();
                                                                }
                                                            } else if (prop_area.equals("Bandra E") || prop_area.equals("Bandra W") || prop_area.equals("Fort") || prop_area.equals("Sion E") || prop_area.equals("Sion W") || prop_area.equals("Worli")) {
                                                                if (rent > 16000) {
                                                                    uploadLeader();
                                                                } else if (rent <= 16000) {
                                                                    uploadData();
                                                                }
                                                            } else if (prop_area.equals("Bhandup E") || prop_area.equals("Bhandup W") || prop_area.equals("Kandivali E") || prop_area.equals("Kandivali W") || prop_area.equals("Malad E")) {
                                                                if (rent > 10000) {
                                                                    uploadLeader();
                                                                } else if (rent <= 10000) {
                                                                    uploadData();
                                                                }
                                                            } else if (prop_area.equals("Boisar")) {
                                                                if (rent > 5500) {
                                                                    uploadLeader();
                                                                } else if (rent <= 5500) {
                                                                    uploadData();
                                                                }
                                                            } else if (prop_area.equals("Borivali E") || prop_area.equals("Borivali W") || prop_area.equals("Kharegaon")) {
                                                                if (rent > 8000) {
                                                                    uploadLeader();
                                                                } else if (rent <= 8000) {
                                                                    uploadData();
                                                                }
                                                            } else if (prop_area.equals("Chembur") || prop_area.equals("Goregaon W")) {
                                                                if (rent > 14000) {
                                                                    uploadLeader();
                                                                } else if (rent <= 14000) {
                                                                    uploadData();
                                                                }
                                                            } else if (prop_area.equals("Church Gate")) {
                                                                if (rent > 21000) {
                                                                    uploadLeader();
                                                                } else if (rent <= 21000) {
                                                                    uploadData();
                                                                }
                                                            } else if (prop_area.equals("Colaba")) {
                                                                if (rent > 22000) {
                                                                    uploadLeader();
                                                                } else if (rent <= 22000) {
                                                                    uploadData();
                                                                }
                                                            } else if (prop_area.equals("Dadar E") || prop_area.equals("Dadar W") || prop_area.equals("Prabhadevi")) {
                                                                if (rent > 19000) {
                                                                    uploadLeader();
                                                                } else if (rent <= 19000) {
                                                                    uploadData();
                                                                }
                                                            } else if (prop_area.equals("Ghatkopar E") || prop_area.equals("Ghatkopar W") || prop_area.equals("Wadala E") || prop_area.equals("Wadala W")) {
                                                                if (rent > 13000) {
                                                                    uploadLeader();
                                                                } else if (rent <= 13000) {
                                                                    uploadData();
                                                                }
                                                            } else if (prop_area.equals("Govandi E") || prop_area.equals("Govandi W") || prop_area.equals("Kopar Khairane") || prop_area.equals("Mire Road E") || prop_area.equals("Mire Road W") || prop_area.equals("Thane E") || prop_area.equals("Thane W")) {
                                                                if (rent > 6000) {
                                                                    uploadLeader();
                                                                } else if (rent <= 6000) {
                                                                    uploadData();
                                                                }
                                                            } else if (prop_area.equals("Jogeshwari E") || prop_area.equals("Jogeshwari W") || prop_area.equals("Kalina") || prop_area.equals("Santa Cruz E") || prop_area.equals("Santa Cruz W") || prop_area.equals("Vile Parle E") || prop_area.equals("Vile Parle W")) {
                                                                if (rent > 11000) {
                                                                    uploadLeader();
                                                                } else if (rent <= 11000) {
                                                                    uploadData();
                                                                }
                                                            } else if (prop_area.equals("Lower Parel") || prop_area.equals("Mahalaxmi") || prop_area.equals("Parel") || prop_area.equals("Powai")) {
                                                                if (rent > 18000) {
                                                                    uploadLeader();
                                                                } else if (rent <= 18000) {
                                                                    uploadData();
                                                                }
                                                            } else if (prop_area.equals("Mahim E") || prop_area.equals("Mahim W") || prop_area.equals("Vikhroli E") || prop_area.equals("Vikhroli W")) {
                                                                if (rent > 15000) {
                                                                    uploadLeader();
                                                                } else if (rent <= 15000) {
                                                                    uploadData();
                                                                }
                                                            } else if (prop_area.equals("Mulund E") || prop_area.equals("Mulund W")) {
                                                                if (rent > 7000) {
                                                                    uploadLeader();
                                                                } else if (rent <= 7000) {
                                                                    uploadData();
                                                                }
                                                            } else if (prop_area.equals("Vashi")) {
                                                                if (rent > 5000) {
                                                                    uploadLeader();
                                                                } else if (rent <= 5000) {
                                                                    uploadData();
                                                                }
                                                            }
                                                        }
                                                    } else if (s1.equals("Two BHK")) {
                                                        if (s2.equals("Fully Furnished")) {
                                                            if (prop_area.equals("Airoli") || prop_area.equals("Ghansoli") || prop_area.equals("Panvel")) {
                                                                if (rent > 19000) {
                                                                    uploadLeader();
                                                                } else if (rent <= 19000) {
                                                                    uploadData();
                                                                }
                                                            } else if (prop_area.equals("Amboli")) {
                                                                if (rent > 30000) {
                                                                    uploadLeader();
                                                                } else if (rent <= 30000) {
                                                                    uploadData();
                                                                }
                                                            } else if (prop_area.equals("Andheri E") || prop_area.equals("Andheri W") || prop_area.equals("Goregaon E") || prop_area.equals("Khar W") || prop_area.equals("Khar E") || prop_area.equals("Kurla E") || prop_area.equals("Kurla W") || prop_area.equals("Malad W")) {
                                                                if (rent > 36000) {
                                                                    uploadLeader();
                                                                } else if (rent <= 36000) {
                                                                    uploadData();
                                                                }
                                                            } else if (prop_area.equals("Bandra E") || prop_area.equals("Bandra W") || prop_area.equals("Fort") || prop_area.equals("Sion E") || prop_area.equals("Sion W") || prop_area.equals("Worli")) {
                                                                if (rent > 44000) {
                                                                    uploadLeader();
                                                                } else if (rent <= 44000) {
                                                                    uploadData();
                                                                }
                                                            } else if (prop_area.equals("Bhandup E") || prop_area.equals("Bhandup W") || prop_area.equals("Kandivali E") || prop_area.equals("Kandivali W") || prop_area.equals("Malad E")) {
                                                                if (rent > 32000) {
                                                                    uploadLeader();
                                                                } else if (rent <= 32000) {
                                                                    uploadData();
                                                                }
                                                            } else if (prop_area.equals("Boisar")) {
                                                                if (rent > 23000) {
                                                                    uploadLeader();
                                                                } else if (rent <= 23000) {
                                                                    uploadData();
                                                                }
                                                            } else if (prop_area.equals("Borivali E") || prop_area.equals("Borivali W") || prop_area.equals("Kharegaon")) {
                                                                if (rent > 28000) {
                                                                    uploadLeader();
                                                                } else if (rent <= 28000) {
                                                                    uploadData();
                                                                }
                                                            } else if (prop_area.equals("Chembur") || prop_area.equals("Goregaon W")) {
                                                                if (rent > 40000) {
                                                                    uploadLeader();
                                                                } else if (rent <= 40000) {
                                                                    uploadData();
                                                                }
                                                            } else if (prop_area.equals("Church Gate")) {
                                                                if (rent > 54000) {
                                                                    uploadLeader();
                                                                } else if (rent <= 54000) {
                                                                    uploadData();
                                                                }
                                                            } else if (prop_area.equals("Colaba")) {
                                                                if (rent > 56000) {
                                                                    uploadLeader();
                                                                } else if (rent <= 56000) {
                                                                    uploadData();
                                                                }
                                                            } else if (prop_area.equals("Dadar E") || prop_area.equals("Dadar W") || prop_area.equals("Prabhadevi")) {
                                                                if (rent > 50000) {
                                                                    uploadLeader();
                                                                } else if (rent <= 50000) {
                                                                    uploadData();
                                                                }
                                                            } else if (prop_area.equals("Ghatkopar E") || prop_area.equals("Ghatkopar W") || prop_area.equals("Wadala E") || prop_area.equals("Wadala W")) {
                                                                if (rent > 38000) {
                                                                    uploadLeader();
                                                                } else if (rent <= 38000) {
                                                                    uploadData();
                                                                }
                                                            } else if (prop_area.equals("Govandi E") || prop_area.equals("Govandi W") || prop_area.equals("Kopar Khairane") || prop_area.equals("Mire Road E") || prop_area.equals("Mire Road W") || prop_area.equals("Thane E") || prop_area.equals("Thane W")) {
                                                                if (rent > 24000) {
                                                                    uploadLeader();
                                                                } else if (rent <= 24000) {
                                                                    uploadData();
                                                                }
                                                            } else if (prop_area.equals("Jogeshwari E") || prop_area.equals("Jogeshwari W") || prop_area.equals("Kalina") || prop_area.equals("Santa Cruz E") || prop_area.equals("Santa Cruz W") || prop_area.equals("Vile Parle E") || prop_area.equals("Vile Parle W")) {
                                                                if (rent > 34000) {
                                                                    uploadLeader();
                                                                } else if (rent <= 34000) {
                                                                    uploadData();
                                                                }
                                                            } else if (prop_area.equals("Lower Parel") || prop_area.equals("Mahalaxmi") || prop_area.equals("Parel") || prop_area.equals("Powai")) {
                                                                if (rent > 48000) {
                                                                    uploadLeader();
                                                                } else if (rent <= 48000) {
                                                                    uploadData();
                                                                }
                                                            } else if (prop_area.equals("Mahim E") || prop_area.equals("Mahim W") || prop_area.equals("Vikhroli E") || prop_area.equals("Vikhroli W")) {
                                                                if (rent > 42000) {
                                                                    uploadLeader();
                                                                } else if (rent <= 42000) {
                                                                    uploadData();
                                                                }
                                                            } else if (prop_area.equals("Mulund E") || prop_area.equals("Mulund W")) {
                                                                if (rent > 26000) {
                                                                    uploadLeader();
                                                                } else if (rent <= 26000) {
                                                                    uploadData();
                                                                }
                                                            } else if (prop_area.equals("Vashi")) {
                                                                if (rent > 22000) {
                                                                    uploadLeader();
                                                                } else if (rent <= 22000) {
                                                                    uploadData();
                                                                }
                                                            }

                                                        } else {
                                                            if (prop_area.equals("Airoli") || prop_area.equals("Ghansoli") || prop_area.equals("Panvel")) {
                                                                if (rent > 15000) {
                                                                    uploadLeader();
                                                                } else if (rent <= 15000) {
                                                                    uploadData();
                                                                }
                                                            } else if (prop_area.equals("Amboli")) {
                                                                if (rent > 26000) {
                                                                    uploadLeader();
                                                                } else if (rent <= 26000) {
                                                                    uploadData();
                                                                }
                                                            } else if (prop_area.equals("Andheri E") || prop_area.equals("Andheri W") || prop_area.equals("Goregaon E") || prop_area.equals("Khar W") || prop_area.equals("Khar E") || prop_area.equals("Kurla E") || prop_area.equals("Kurla W") || prop_area.equals("Malad W")) {
                                                                if (rent > 32000) {
                                                                    uploadLeader();
                                                                } else if (rent <= 32000) {
                                                                    uploadData();
                                                                }
                                                            } else if (prop_area.equals("Bandra E") || prop_area.equals("Bandra W") || prop_area.equals("Fort") || prop_area.equals("Sion E") || prop_area.equals("Sion W") || prop_area.equals("Worli")) {
                                                                if (rent > 40000) {
                                                                    uploadLeader();
                                                                } else if (rent <= 40000) {
                                                                    uploadData();
                                                                }
                                                            } else if (prop_area.equals("Bhandup E") || prop_area.equals("Bhandup W") || prop_area.equals("Kandivali E") || prop_area.equals("Kandivali W") || prop_area.equals("Malad E")) {
                                                                if (rent > 28000) {
                                                                    uploadLeader();
                                                                } else if (rent <= 28000) {
                                                                    uploadData();
                                                                }
                                                            } else if (prop_area.equals("Boisar")) {
                                                                if (rent > 19000) {
                                                                    uploadLeader();
                                                                } else if (rent <= 19000) {
                                                                    uploadData();
                                                                }
                                                            } else if (prop_area.equals("Borivali E") || prop_area.equals("Borivali W") || prop_area.equals("Kharegaon")) {
                                                                if (rent > 24000) {
                                                                    uploadLeader();
                                                                } else if (rent <= 24000) {
                                                                    uploadData();
                                                                }
                                                            } else if (prop_area.equals("Chembur") || prop_area.equals("Goregaon W")) {
                                                                if (rent > 36000) {
                                                                    uploadLeader();
                                                                } else if (rent <= 36000) {
                                                                    uploadData();
                                                                }
                                                            } else if (prop_area.equals("Church Gate")) {
                                                                if (rent > 50000) {
                                                                    uploadLeader();
                                                                } else if (rent <= 50000) {
                                                                    uploadData();
                                                                }
                                                            } else if (prop_area.equals("Colaba")) {
                                                                if (rent > 52000) {
                                                                    uploadLeader();
                                                                } else if (rent <= 52000) {
                                                                    uploadData();
                                                                }
                                                            } else if (prop_area.equals("Dadar E") || prop_area.equals("Dadar W") || prop_area.equals("Prabhadevi")) {
                                                                if (rent > 46000) {
                                                                    uploadLeader();
                                                                } else if (rent <= 46000) {
                                                                    uploadData();
                                                                }
                                                            } else if (prop_area.equals("Ghatkopar E") || prop_area.equals("Ghatkopar W") || prop_area.equals("Wadala E") || prop_area.equals("Wadala W")) {
                                                                if (rent > 34000) {
                                                                    uploadLeader();
                                                                } else if (rent <= 34000) {
                                                                    uploadData();
                                                                }
                                                            } else if (prop_area.equals("Govandi E") || prop_area.equals("Govandi W") || prop_area.equals("Kopar Khairane") || prop_area.equals("Mire Road E") || prop_area.equals("Mire Road W") || prop_area.equals("Thane E") || prop_area.equals("Thane W")) {
                                                                if (rent > 20000) {
                                                                    uploadLeader();
                                                                } else if (rent <= 20000) {
                                                                    uploadData();
                                                                }
                                                            } else if (prop_area.equals("Jogeshwari E") || prop_area.equals("Jogeshwari W") || prop_area.equals("Kalina") || prop_area.equals("Santa Cruz E") || prop_area.equals("Santa Cruz W") || prop_area.equals("Vile Parle E") || prop_area.equals("Vile Parle W")) {
                                                                if (rent > 30000) {
                                                                    uploadLeader();
                                                                } else if (rent <= 30000) {
                                                                    uploadData();
                                                                }
                                                            } else if (prop_area.equals("Lower Parel") || prop_area.equals("Mahalaxmi") || prop_area.equals("Parel") || prop_area.equals("Powai")) {
                                                                if (rent > 44000) {
                                                                    uploadLeader();
                                                                } else if (rent <= 44000) {
                                                                    uploadData();
                                                                }
                                                            } else if (prop_area.equals("Mahim E") || prop_area.equals("Mahim W") || prop_area.equals("Vikhroli E") || prop_area.equals("Vikhroli W")) {
                                                                if (rent > 38000) {
                                                                    uploadLeader();
                                                                } else if (rent <= 38000) {
                                                                    uploadData();
                                                                }
                                                            } else if (prop_area.equals("Mulund E") || prop_area.equals("Mulund W")) {
                                                                if (rent > 22000) {
                                                                    uploadLeader();
                                                                } else if (rent <= 22000) {
                                                                    uploadData();
                                                                }
                                                            } else if (prop_area.equals("Vashi")) {
                                                                if (rent > 18000) {
                                                                    uploadLeader();
                                                                } else if (rent <= 18000) {
                                                                    uploadData();
                                                                }
                                                            }
                                                        }
                                                    } else if (s1.equals("Three BHK")) {
                                                        if (s2.equals("Fully Furnished")) {
                                                            if (prop_area.equals("Airoli") || prop_area.equals("Ghansoli") || prop_area.equals("Panvel")) {
                                                                if (rent > 28500) {
                                                                    uploadLeader();
                                                                } else if (rent <= 28500) {
                                                                    uploadData();
                                                                }
                                                            } else if (prop_area.equals("Amboli")) {
                                                                if (rent > 45000) {
                                                                    uploadLeader();
                                                                } else if (rent <= 45000) {
                                                                    uploadData();
                                                                }
                                                            } else if (prop_area.equals("Andheri E") || prop_area.equals("Andheri W") || prop_area.equals("Goregaon E") || prop_area.equals("Khar W") || prop_area.equals("Khar E") || prop_area.equals("Kurla E") || prop_area.equals("Kurla W") || prop_area.equals("Malad W")) {
                                                                if (rent > 54000) {
                                                                    uploadLeader();
                                                                } else if (rent <= 54000) {
                                                                    uploadData();
                                                                }
                                                            } else if (prop_area.equals("Bandra E") || prop_area.equals("Bandra W") || prop_area.equals("Fort") || prop_area.equals("Sion E") || prop_area.equals("Sion W") || prop_area.equals("Worli")) {
                                                                if (rent > 66000) {
                                                                    uploadLeader();
                                                                } else if (rent <= 66000) {
                                                                    uploadData();
                                                                }
                                                            } else if (prop_area.equals("Bhandup E") || prop_area.equals("Bhandup W") || prop_area.equals("Kandivali E") || prop_area.equals("Kandivali W") || prop_area.equals("Malad E")) {
                                                                if (rent > 48000) {
                                                                    uploadLeader();
                                                                } else if (rent <= 48000) {
                                                                    uploadData();
                                                                }
                                                            } else if (prop_area.equals("Boisar")) {
                                                                if (rent > 34500) {
                                                                    uploadLeader();
                                                                } else if (rent <= 34500) {
                                                                    uploadData();
                                                                }
                                                            } else if (prop_area.equals("Borivali E") || prop_area.equals("Borivali W") || prop_area.equals("Kharegaon")) {
                                                                if (rent > 42000) {
                                                                    uploadLeader();
                                                                } else if (rent <= 42000) {
                                                                    uploadData();
                                                                }
                                                            } else if (prop_area.equals("Chembur") || prop_area.equals("Goregaon W")) {
                                                                if (rent > 60000) {
                                                                    uploadLeader();
                                                                } else if (rent <= 60000) {
                                                                    uploadData();
                                                                }
                                                            } else if (prop_area.equals("Church Gate")) {
                                                                if (rent > 81000) {
                                                                    uploadLeader();
                                                                } else if (rent <= 81000) {
                                                                    uploadData();
                                                                }
                                                            } else if (prop_area.equals("Colaba")) {
                                                                if (rent > 84000) {
                                                                    uploadLeader();
                                                                } else if (rent <= 84000) {
                                                                    uploadData();
                                                                }
                                                            } else if (prop_area.equals("Dadar E") || prop_area.equals("Dadar W") || prop_area.equals("Prabhadevi")) {
                                                                if (rent > 75000) {
                                                                    uploadLeader();
                                                                } else if (rent <= 75000) {
                                                                    uploadData();
                                                                }
                                                            } else if (prop_area.equals("Ghatkopar E") || prop_area.equals("Ghatkopar W") || prop_area.equals("Wadala E") || prop_area.equals("Wadala W")) {
                                                                if (rent > 57000) {
                                                                    uploadLeader();
                                                                } else if (rent <= 57000) {
                                                                    uploadData();
                                                                }
                                                            } else if (prop_area.equals("Govandi E") || prop_area.equals("Govandi W") || prop_area.equals("Kopar Khairane") || prop_area.equals("Mire Road E") || prop_area.equals("Mire Road W") || prop_area.equals("Thane E") || prop_area.equals("Thane W")) {
                                                                if (rent > 36000) {
                                                                    uploadLeader();
                                                                } else if (rent <= 36000) {
                                                                    uploadData();
                                                                }
                                                            } else if (prop_area.equals("Jogeshwari E") || prop_area.equals("Jogeshwari W") || prop_area.equals("Kalina") || prop_area.equals("Santa Cruz E") || prop_area.equals("Santa Cruz W") || prop_area.equals("Vile Parle E") || prop_area.equals("Vile Parle W")) {
                                                                if (rent > 51000) {
                                                                    uploadLeader();
                                                                } else if (rent <= 51000) {
                                                                    uploadData();
                                                                }
                                                            } else if (prop_area.equals("Lower Parel") || prop_area.equals("Mahalaxmi") || prop_area.equals("Parel") || prop_area.equals("Powai")) {
                                                                if (rent > 72000) {
                                                                    uploadLeader();
                                                                } else if (rent <= 72000) {
                                                                    uploadData();
                                                                }
                                                            } else if (prop_area.equals("Mahim E") || prop_area.equals("Mahim W") || prop_area.equals("Vikhroli E") || prop_area.equals("Vikhroli W")) {
                                                                if (rent > 63000) {
                                                                    uploadLeader();
                                                                } else if (rent <= 63000) {
                                                                    uploadData();
                                                                }
                                                            } else if (prop_area.equals("Mulund E") || prop_area.equals("Mulund W")) {
                                                                if (rent > 39000) {
                                                                    uploadLeader();
                                                                } else if (rent <= 39000) {
                                                                    uploadData();
                                                                }
                                                            } else if (prop_area.equals("Vashi")) {
                                                                if (rent > 33000) {
                                                                    uploadLeader();
                                                                } else if (rent <= 33000) {
                                                                    uploadData();
                                                                }
                                                            }
                                                        } else {
                                                            if (prop_area.equals("Airoli") || prop_area.equals("Ghansoli") || prop_area.equals("Panvel")) {
                                                                if (rent > 22500) {
                                                                    uploadLeader();
                                                                } else if (rent <= 22500) {
                                                                    uploadData();
                                                                }
                                                            } else if (prop_area.equals("Amboli")) {
                                                                if (rent > 39000) {
                                                                    uploadLeader();
                                                                } else if (rent <= 39000) {
                                                                    uploadData();
                                                                }
                                                            } else if (prop_area.equals("Andheri E") || prop_area.equals("Andheri W") || prop_area.equals("Goregaon E") || prop_area.equals("Khar W") || prop_area.equals("Khar E") || prop_area.equals("Kurla E") || prop_area.equals("Kurla W") || prop_area.equals("Malad W")) {
                                                                if (rent > 48000) {
                                                                    uploadLeader();
                                                                } else if (rent <= 12000) {
                                                                    uploadData();
                                                                }
                                                            } else if (prop_area.equals("Bandra E") || prop_area.equals("Bandra W") || prop_area.equals("Fort") || prop_area.equals("Sion E") || prop_area.equals("Sion W") || prop_area.equals("Worli")) {
                                                                if (rent > 60000) {
                                                                    uploadLeader();
                                                                } else if (rent <= 60000) {
                                                                    uploadData();
                                                                }
                                                            } else if (prop_area.equals("Bhandup E") || prop_area.equals("Bhandup W") || prop_area.equals("Kandivali E") || prop_area.equals("Kandivali W") || prop_area.equals("Malad E")) {
                                                                if (rent > 42000) {
                                                                    uploadLeader();
                                                                } else if (rent <= 42000) {
                                                                    uploadData();
                                                                }
                                                            } else if (prop_area.equals("Boisar")) {
                                                                if (rent > 28500) {
                                                                    uploadLeader();
                                                                } else if (rent <= 28500) {
                                                                    uploadData();
                                                                }
                                                            } else if (prop_area.equals("Borivali E") || prop_area.equals("Borivali W") || prop_area.equals("Kharegaon")) {
                                                                if (rent > 36000) {
                                                                    uploadLeader();
                                                                } else if (rent <= 36000) {
                                                                    uploadData();
                                                                }
                                                            } else if (prop_area.equals("Chembur") || prop_area.equals("Goregaon W")) {
                                                                if (rent > 54000) {
                                                                    uploadLeader();
                                                                } else if (rent <= 54000) {
                                                                    uploadData();
                                                                }
                                                            } else if (prop_area.equals("Church Gate")) {
                                                                if (rent > 75000) {
                                                                    uploadLeader();
                                                                } else if (rent <= 75000) {
                                                                    uploadData();
                                                                }
                                                            } else if (prop_area.equals("Colaba")) {
                                                                if (rent > 78000) {
                                                                    uploadLeader();
                                                                } else if (rent <= 78000) {
                                                                    uploadData();
                                                                }
                                                            } else if (prop_area.equals("Dadar E") || prop_area.equals("Dadar W") || prop_area.equals("Prabhadevi")) {
                                                                if (rent > 69000) {
                                                                    uploadLeader();
                                                                } else if (rent <= 69000) {
                                                                    uploadData();
                                                                }
                                                            } else if (prop_area.equals("Ghatkopar E") || prop_area.equals("Ghatkopar W") || prop_area.equals("Wadala E") || prop_area.equals("Wadala W")) {
                                                                if (rent > 51000) {
                                                                    uploadLeader();
                                                                } else if (rent <= 51000) {
                                                                    uploadData();
                                                                }
                                                            } else if (prop_area.equals("Govandi E") || prop_area.equals("Govandi W") || prop_area.equals("Kopar Khairane") || prop_area.equals("Mire Road E") || prop_area.equals("Mire Road W") || prop_area.equals("Thane E") || prop_area.equals("Thane W")) {
                                                                if (rent > 30000) {
                                                                    uploadLeader();
                                                                } else if (rent <= 30000) {
                                                                    uploadData();
                                                                }
                                                            } else if (prop_area.equals("Jogeshwari E") || prop_area.equals("Jogeshwari W") || prop_area.equals("Kalina") || prop_area.equals("Santa Cruz E") || prop_area.equals("Santa Cruz W") || prop_area.equals("Vile Parle E") || prop_area.equals("Vile Parle W")) {
                                                                if (rent > 45000) {
                                                                    uploadLeader();
                                                                } else if (rent <= 45000) {
                                                                    uploadData();
                                                                }
                                                            } else if (prop_area.equals("Lower Parel") || prop_area.equals("Mahalaxmi") || prop_area.equals("Parel") || prop_area.equals("Powai")) {
                                                                if (rent > 66000) {
                                                                    uploadLeader();
                                                                } else if (rent <= 66000) {
                                                                    uploadData();
                                                                }
                                                            } else if (prop_area.equals("Mahim E") || prop_area.equals("Mahim W") || prop_area.equals("Vikhroli E") || prop_area.equals("Vikhroli W")) {
                                                                if (rent > 57000) {
                                                                    uploadLeader();
                                                                } else if (rent <= 57000) {
                                                                    uploadData();
                                                                }
                                                            } else if (prop_area.equals("Mulund E") || prop_area.equals("Mulund W")) {
                                                                if (rent > 33000) {
                                                                    uploadLeader();
                                                                } else if (rent <= 33000) {
                                                                    uploadData();
                                                                }
                                                            } else if (prop_area.equals("Vashi")) {
                                                                if (rent > 27000) {
                                                                    uploadLeader();
                                                                } else if (rent <= 27000) {
                                                                    uploadData();
                                                                }
                                                            }
                                                        }
                                                    } else {
                                                        uploadData();
                                                    }
                                                    Toast.makeText(Option1.this, "Upload Complete", Toast.LENGTH_LONG).show();
                                                    if (times.equals("One")) {
                                                        startActivity(new Intent(Option1.this, MainActivity.class));
                                                    } else if (times.equals("Two")) {
                                                        Intent i = new Intent(Option1.this, Option1.class);
                                                        i.putExtra("proptype2", proptype);
                                                        i.putExtra("cust_name2", cust_name);
                                                        i.putExtra("prop_name2", prop_name);
                                                        i.putExtra("cust_num2", cust_num);
                                                        i.putExtra("alt_num2", altcust_num);
                                                        i.putExtra("cust_em_site2", cust_em_site);
                                                        i.putExtra("prop_area2", prop_area);
                                                        i.putExtra("prop_variant2", prop_variant);
                                                        i.putExtra("prop_locality2", prop_locality);
                                                        i.putExtra("times2", "One");
                                                        i.putExtra("uri1", uristring);
                                                        startActivity(i);
                                                    } else if (times.equals("Three")) {
                                                        Intent i = new Intent(Option1.this, Option1.class);
                                                        i.putExtra("proptype2", proptype);
                                                        i.putExtra("cust_name2", cust_name);
                                                        i.putExtra("prop_name2", prop_name);
                                                        i.putExtra("cust_num2", cust_num);
                                                        i.putExtra("alt_num2", altcust_num);
                                                        i.putExtra("cust_em_site2", cust_em_site);
                                                        i.putExtra("prop_area2", prop_area);
                                                        i.putExtra("prop_variant2", prop_variant);
                                                        i.putExtra("prop_locality2", prop_locality);
                                                        i.putExtra("times2", "Two");
                                                        i.putExtra("uri1", uristring);
                                                        startActivity(i);
                                                    } else if (times.equals("Four")) {
                                                        Intent i = new Intent(Option1.this, Option1.class);
                                                        i.putExtra("proptype2", proptype);
                                                        i.putExtra("cust_name2", cust_name);
                                                        i.putExtra("prop_name2", prop_name);
                                                        i.putExtra("cust_num2", cust_num);
                                                        i.putExtra("alt_num2", altcust_num);
                                                        i.putExtra("cust_em_site2", cust_em_site);
                                                        i.putExtra("prop_area2", prop_area);
                                                        i.putExtra("prop_variant2", prop_variant);
                                                        i.putExtra("prop_locality2", prop_locality);
                                                        i.putExtra("times2", "Three");
                                                        i.putExtra("uri1", uristring);
                                                        startActivity(i);
                                                    } else if (times.equals("Five")) {
                                                        Intent i = new Intent(Option1.this, Option1.class);
                                                        i.putExtra("proptype2", proptype);
                                                        i.putExtra("cust_name2", cust_name);
                                                        i.putExtra("prop_name2", prop_name);
                                                        i.putExtra("cust_num2", cust_num);
                                                        i.putExtra("alt_num2", altcust_num);
                                                        i.putExtra("cust_em_site2", cust_em_site);
                                                        i.putExtra("prop_area2", prop_area);
                                                        i.putExtra("prop_variant2", prop_variant);
                                                        i.putExtra("prop_locality2", prop_locality);
                                                        i.putExtra("times2", "Four");
                                                        i.putExtra("uri1", uristring);
                                                        startActivity(i);
                                                    } else if (times.equals("Six")) {
                                                        Intent i = new Intent(Option1.this, Option1.class);
                                                        i.putExtra("proptype2", proptype);
                                                        i.putExtra("cust_name2", cust_name);
                                                        i.putExtra("prop_name2", prop_name);
                                                        i.putExtra("cust_num2", cust_num);
                                                        i.putExtra("alt_num2", altcust_num);
                                                        i.putExtra("cust_em_site2", cust_em_site);
                                                        i.putExtra("prop_area2", prop_area);
                                                        i.putExtra("prop_variant2", prop_variant);
                                                        i.putExtra("prop_locality2", prop_locality);
                                                        i.putExtra("times2", "Five");
                                                        i.putExtra("uri1", uristring);
                                                        startActivity(i);
                                                    } else if (times.equals("Seven")) {
                                                        Intent i = new Intent(Option1.this, Option1.class);
                                                        i.putExtra("proptype2", proptype);
                                                        i.putExtra("cust_name2", cust_name);
                                                        i.putExtra("prop_name2", prop_name);
                                                        i.putExtra("cust_num2", cust_num);
                                                        i.putExtra("alt_num2", altcust_num);
                                                        i.putExtra("cust_em_site2", cust_em_site);
                                                        i.putExtra("prop_area2", prop_area);
                                                        i.putExtra("prop_variant2", prop_variant);
                                                        i.putExtra("prop_locality2", prop_locality);
                                                        i.putExtra("times2", "Six");
                                                        i.putExtra("uri1", uristring);
                                                        startActivity(i);
                                                    } else if (times.equals("Eight")) {
                                                        Intent i = new Intent(Option1.this, Option1.class);
                                                        i.putExtra("proptype2", proptype);
                                                        i.putExtra("cust_name2", cust_name);
                                                        i.putExtra("prop_name2", prop_name);
                                                        i.putExtra("cust_num2", cust_num);
                                                        i.putExtra("alt_num2", altcust_num);
                                                        i.putExtra("cust_em_site2", cust_em_site);
                                                        i.putExtra("prop_area2", prop_area);
                                                        i.putExtra("prop_variant2", prop_variant);
                                                        i.putExtra("prop_locality2", prop_locality);
                                                        i.putExtra("times2", "Seven");
                                                        i.putExtra("uri1", uristring);
                                                        startActivity(i);
                                                    }
                                                }
                                            }).addOnFailureListener(new OnFailureListener() {
                                                        @Override
                                                        public void onFailure(@NonNull Exception e) {
                                                            dialog1.dismiss();
                                                            Toast.makeText(Option1.this,e.getMessage(),Toast.LENGTH_SHORT).show();
                                                        }
                                                    }).addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
                                                @Override
                                                public void onProgress(UploadTask.TaskSnapshot taskSnapshot) {
                                                    double progress =(100 * taskSnapshot.getBytesTransferred())/taskSnapshot.getTotalByteCount();
                                                    dialog1.setMessage("Uploaded "+(int)progress+"%");
                                                }
                                            });
                                        }
                                        else {
                                            Toast.makeText(Option1.this,"Please select image",Toast.LENGTH_SHORT).show();
                                        }
                                    }
                                });
                                alertDialog.show();
                            }
                        }
                        else if (s5.equals("0"))
                        {
                            if (s6.equals("Select preferred tenant"))
                            {
                                Toast.makeText(

                                        Option1.this,"Please select preferred tenant.",Toast.LENGTH_SHORT).show();
                            }
                            else if (s7.equals("")){
                                Toast.makeText(Option1.this,"Please enter supply notes",Toast.LENGTH_SHORT).show();
                            }
                            else {
                                uri =Uri.parse(uristring);
                                if (uri != null) {
                                    final ProgressDialog dialog = new ProgressDialog(Option1.this);
                                    dialog.setTitle("Uploading Data.");
                                    dialog.show();
                                    StorageReference filePath = storageReference.child("PostImage").child(uri.getLastPathSegment());
                                    filePath.putFile(uri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                                        @Override
                                        public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                                            dialog.dismiss();
                                            downloadurl = taskSnapshot.getDownloadUrl();
                                            downloadurl = taskSnapshot.getDownloadUrl();
                                            if (s1.equals("One BHK")) {
                                                if (s2.equals("Fully Furnished")) {
                                                    if (prop_area.equals("Airoli") || prop_area.equals("Ghansoli") || prop_area.equals("Panvel")) {
                                                        if (rent > 7500) {
                                                            uploadLeader();
                                                        } else if (rent <= 7500) {
                                                            uploadData();
                                                        }
                                                    } else if (prop_area.equals("Amboli")) {
                                                        if (rent > 13000) {
                                                            uploadLeader();
                                                        } else if (rent <= 13000) {
                                                            uploadData();
                                                        }
                                                    } else if (prop_area.equals("Andheri E") || prop_area.equals("Andheri W") || prop_area.equals("Goregaon E") || prop_area.equals("Khar W") || prop_area.equals("Khar E") || prop_area.equals("Kurla E") || prop_area.equals("Kurla W") || prop_area.equals("Malad W")) {
                                                        if (rent > 16000) {
                                                            uploadLeader();
                                                        } else if (rent <= 16000) {
                                                            uploadData();
                                                        }
                                                    } else if (prop_area.equals("Bandra E") || prop_area.equals("Bandra W") || prop_area.equals("Fort") || prop_area.equals("Sion E") || prop_area.equals("Sion W") || prop_area.equals("Worli")) {
                                                        if (rent > 20000) {
                                                            uploadLeader();
                                                        } else if (rent <= 20000) {
                                                            uploadData();
                                                        }
                                                    } else if (prop_area.equals("Bhandup E") || prop_area.equals("Bhandup W") || prop_area.equals("Kandivali E") || prop_area.equals("Kandivali W") || prop_area.equals("Malad E")) {
                                                        if (rent > 14000) {
                                                            uploadLeader();
                                                        } else if (rent <= 14000) {
                                                            uploadData();
                                                        }
                                                    } else if (prop_area.equals("Boisar")) {
                                                        if (rent > 9500) {
                                                            uploadLeader();
                                                        } else if (rent <= 9500) {
                                                            uploadData();
                                                        }
                                                    } else if (prop_area.equals("Borivali E") || prop_area.equals("Borivali W") || prop_area.equals("Kharegaon")) {
                                                        if (rent > 12000) {
                                                            uploadLeader();
                                                        } else if (rent <= 12000) {
                                                            uploadData();
                                                        }
                                                    } else if (prop_area.equals("Chembur") || prop_area.equals("Goregaon W")) {
                                                        if (rent > 18000) {
                                                            uploadLeader();
                                                        } else if (rent <= 18000) {
                                                            uploadData();
                                                        }
                                                    } else if (prop_area.equals("Church Gate")) {
                                                        if (rent > 25000) {
                                                            uploadLeader();
                                                        } else if (rent <= 25000) {
                                                            uploadData();
                                                        }
                                                    } else if (prop_area.equals("Colaba")) {
                                                        if (rent > 26000) {
                                                            uploadLeader();
                                                        } else if (rent <= 26000) {
                                                            uploadData();
                                                        }
                                                    } else if (prop_area.equals("Dadar E") || prop_area.equals("Dadar W") || prop_area.equals("Prabhadevi")) {
                                                        if (rent > 23000) {
                                                            uploadLeader();
                                                        } else if (rent <= 23000) {
                                                            uploadData();
                                                        }
                                                    } else if (prop_area.equals("Ghatkopar E") || prop_area.equals("Ghatkopar W") || prop_area.equals("Wadala E") || prop_area.equals("Wadala W")) {
                                                        if (rent > 17000) {
                                                            uploadLeader();
                                                        } else if (rent <= 17000) {
                                                            uploadData();
                                                        }
                                                    } else if (prop_area.equals("Govandi E") || prop_area.equals("Govandi W") || prop_area.equals("Kopar Khairane") || prop_area.equals("Mire Road E") || prop_area.equals("Mire Road W") || prop_area.equals("Thane E") || prop_area.equals("Thane W")) {
                                                        if (rent > 10000) {
                                                            uploadLeader();
                                                        } else if (rent <= 10000) {
                                                            uploadData();
                                                        }
                                                    } else if (prop_area.equals("Jogeshwari E") || prop_area.equals("Jogeshwari W") || prop_area.equals("Kalina") || prop_area.equals("Santa Cruz E") || prop_area.equals("Santa Cruz W") || prop_area.equals("Vile Parle E") || prop_area.equals("Vile Parle W")) {
                                                        if (rent > 15000) {
                                                            uploadLeader();
                                                        } else if (rent <= 15000) {
                                                            uploadData();
                                                        }
                                                    } else if (prop_area.equals("Lower Parel") || prop_area.equals("Mahalaxmi") || prop_area.equals("Parel") || prop_area.equals("Powai")) {
                                                        if (rent > 22000) {
                                                            uploadLeader();
                                                        } else if (rent <= 22000) {
                                                            uploadData();
                                                        }
                                                    } else if (prop_area.equals("Mahim E") || prop_area.equals("Mahim W") || prop_area.equals("Vikhroli E") || prop_area.equals("Vikhroli W")) {
                                                        if (rent > 19000) {
                                                            uploadLeader();
                                                        } else if (rent <= 19000) {
                                                            uploadData();
                                                        }
                                                    } else if (prop_area.equals("Mulund E") || prop_area.equals("Mulund W")) {
                                                        if (rent > 11000) {
                                                            uploadLeader();
                                                        } else if (rent <= 11000) {
                                                            uploadData();
                                                        }
                                                    } else if (prop_area.equals("Vashi")) {
                                                        if (rent > 9000) {
                                                            uploadLeader();
                                                        } else if (rent <= 9000) {
                                                            uploadData();
                                                        }
                                                    }
                                                } else {
                                                    if (prop_area.equals("Airoli") || prop_area.equals("Ghansoli") || prop_area.equals("Panvel")) {
                                                        if (rent > 3500) {
                                                            uploadLeader();
                                                        } else if (rent <= 3500){
                                                            uploadData();
                                                        }
                                                    } else if (prop_area.equals("Amboli")) {
                                                        if (rent > 9000) {
                                                            uploadLeader();
                                                        } else if (rent <= 9000) {
                                                            uploadData();
                                                        }
                                                    } else if (prop_area.equals("Andheri E") || prop_area.equals("Andheri W") || prop_area.equals("Goregaon E") || prop_area.equals("Khar W") || prop_area.equals("Khar E") || prop_area.equals("Kurla E") || prop_area.equals("Kurla W") || prop_area.equals("Malad W")) {
                                                        if (rent > 12000) {
                                                            uploadLeader();
                                                        } else if (rent <= 12000) {
                                                            uploadData();
                                                        }
                                                    } else if (prop_area.equals("Bandra E") || prop_area.equals("Bandra W") || prop_area.equals("Fort") || prop_area.equals("Sion E") || prop_area.equals("Sion W") || prop_area.equals("Worli")) {
                                                        if (rent > 16000) {
                                                            uploadLeader();
                                                        } else if (rent <= 16000) {
                                                            uploadData();
                                                        }
                                                    } else if (prop_area.equals("Bhandup E") || prop_area.equals("Bhandup W") || prop_area.equals("Kandivali E") || prop_area.equals("Kandivali W") || prop_area.equals("Malad E")) {
                                                        if (rent > 10000) {
                                                            uploadLeader();
                                                        } else if (rent <= 10000) {
                                                            uploadData();
                                                        }
                                                    } else if (prop_area.equals("Boisar")) {
                                                        if (rent > 5500) {
                                                            uploadLeader();
                                                        } else if (rent <= 5500) {
                                                            uploadData();
                                                        }
                                                    } else if (prop_area.equals("Borivali E") || prop_area.equals("Borivali W") || prop_area.equals("Kharegaon")) {
                                                        if (rent > 8000) {
                                                            uploadLeader();
                                                        } else if (rent <= 8000) {
                                                            uploadData();
                                                        }
                                                    } else if (prop_area.equals("Chembur") || prop_area.equals("Goregaon W")) {
                                                        if (rent > 14000) {
                                                            uploadLeader();
                                                        } else if (rent <= 14000) {
                                                            uploadData();
                                                        }
                                                    } else if (prop_area.equals("Church Gate")) {
                                                        if (rent > 21000) {
                                                            uploadLeader();
                                                        } else if (rent <= 21000) {
                                                            uploadData();
                                                        }
                                                    } else if (prop_area.equals("Colaba")) {
                                                        if (rent > 22000) {
                                                            uploadLeader();
                                                        } else if (rent <= 22000) {
                                                            uploadData();
                                                        }
                                                    } else if (prop_area.equals("Dadar E") || prop_area.equals("Dadar W") || prop_area.equals("Prabhadevi")) {
                                                        if (rent > 19000) {
                                                            uploadLeader();
                                                        } else if (rent <= 19000) {
                                                            uploadData();
                                                        }
                                                    } else if (prop_area.equals("Ghatkopar E") || prop_area.equals("Ghatkopar W") || prop_area.equals("Wadala E") || prop_area.equals("Wadala W")) {
                                                        if (rent > 13000) {
                                                            uploadLeader();
                                                        } else if (rent <= 13000) {
                                                            uploadData();
                                                        }
                                                    } else if (prop_area.equals("Govandi E") || prop_area.equals("Govandi W") || prop_area.equals("Kopar Khairane") || prop_area.equals("Mire Road E") || prop_area.equals("Mire Road W") || prop_area.equals("Thane E") || prop_area.equals("Thane W")) {
                                                        if (rent > 6000) {
                                                            uploadLeader();
                                                        } else if (rent <= 6000) {
                                                            uploadData();
                                                        }
                                                    } else if (prop_area.equals("Jogeshwari E") || prop_area.equals("Jogeshwari W") || prop_area.equals("Kalina") || prop_area.equals("Santa Cruz E") || prop_area.equals("Santa Cruz W") || prop_area.equals("Vile Parle E") || prop_area.equals("Vile Parle W")) {
                                                        if (rent > 11000) {
                                                            uploadLeader();
                                                        } else if (rent <= 11000) {
                                                            uploadData();
                                                        }
                                                    } else if (prop_area.equals("Lower Parel") || prop_area.equals("Mahalaxmi") || prop_area.equals("Parel") || prop_area.equals("Powai")) {
                                                        if (rent > 18000) {
                                                            uploadLeader();
                                                        } else if (rent <= 18000) {
                                                            uploadData();
                                                        }
                                                    } else if (prop_area.equals("Mahim E") || prop_area.equals("Mahim W") || prop_area.equals("Vikhroli E") || prop_area.equals("Vikhroli W")) {
                                                        if (rent > 15000) {
                                                            uploadLeader();
                                                        } else if (rent <= 15000) {
                                                            uploadData();
                                                        }
                                                    } else if (prop_area.equals("Mulund E") || prop_area.equals("Mulund W")) {
                                                        if (rent > 7000) {
                                                            uploadLeader();
                                                        } else if (rent <= 7000) {
                                                            uploadData();
                                                        }
                                                    } else if (prop_area.equals("Vashi")) {
                                                        if (rent > 5000) {
                                                            uploadLeader();
                                                        } else if (rent <= 5000) {
                                                            uploadData();
                                                        }
                                                    }
                                                }
                                            } else if (s1.equals("Two BHK")) {
                                                if (s2.equals("Fully Furnished")) {
                                                    if (prop_area.equals("Airoli") || prop_area.equals("Ghansoli") || prop_area.equals("Panvel")) {
                                                        if (rent > 19000) {
                                                            uploadLeader();
                                                        } else if (rent <= 19000) {
                                                            uploadData();
                                                        }
                                                    } else if (prop_area.equals("Amboli")) {
                                                        if (rent > 30000) {
                                                            uploadLeader();
                                                        } else if (rent <= 30000) {
                                                            uploadData();
                                                        }
                                                    } else if (prop_area.equals("Andheri E") || prop_area.equals("Andheri W") || prop_area.equals("Goregaon E") || prop_area.equals("Khar W") || prop_area.equals("Khar E") || prop_area.equals("Kurla E") || prop_area.equals("Kurla W") || prop_area.equals("Malad W")) {
                                                        if (rent > 36000) {
                                                            uploadLeader();
                                                        } else if (rent <= 36000) {
                                                            uploadData();
                                                        }
                                                    } else if (prop_area.equals("Bandra E") || prop_area.equals("Bandra W") || prop_area.equals("Fort") || prop_area.equals("Sion E") || prop_area.equals("Sion W") || prop_area.equals("Worli")) {
                                                        if (rent > 44000) {
                                                            uploadLeader();
                                                        } else if (rent <= 44000) {
                                                            uploadData();
                                                        }
                                                    } else if (prop_area.equals("Bhandup E") || prop_area.equals("Bhandup W") || prop_area.equals("Kandivali E") || prop_area.equals("Kandivali W") || prop_area.equals("Malad E")) {
                                                        if (rent > 32000) {
                                                            uploadLeader();
                                                        } else if (rent <= 32000) {
                                                            uploadData();
                                                        }
                                                    } else if (prop_area.equals("Boisar")) {
                                                        if (rent > 23000) {
                                                            uploadLeader();
                                                        } else if (rent <= 23000) {
                                                            uploadData();
                                                        }
                                                    } else if (prop_area.equals("Borivali E") || prop_area.equals("Borivali W") || prop_area.equals("Kharegaon")) {
                                                        if (rent > 28000) {
                                                            uploadLeader();
                                                        } else if (rent <= 28000) {
                                                            uploadData();
                                                        }
                                                    } else if (prop_area.equals("Chembur") || prop_area.equals("Goregaon W")) {
                                                        if (rent > 40000) {
                                                            uploadLeader();
                                                        } else if (rent <= 40000) {
                                                            uploadData();
                                                        }
                                                    } else if (prop_area.equals("Church Gate")) {
                                                        if (rent > 54000) {
                                                            uploadLeader();
                                                        } else if (rent <= 54000) {
                                                            uploadData();
                                                        }
                                                    } else if (prop_area.equals("Colaba")) {
                                                        if (rent > 56000) {
                                                            uploadLeader();
                                                        } else if (rent <= 56000) {
                                                            uploadData();
                                                        }
                                                    } else if (prop_area.equals("Dadar E") || prop_area.equals("Dadar W") || prop_area.equals("Prabhadevi")) {
                                                        if (rent > 50000) {
                                                            uploadLeader();
                                                        } else if (rent <= 50000) {
                                                            uploadData();
                                                        }
                                                    } else if (prop_area.equals("Ghatkopar E") || prop_area.equals("Ghatkopar W") || prop_area.equals("Wadala E") || prop_area.equals("Wadala W")) {
                                                        if (rent > 38000) {
                                                            uploadLeader();
                                                        } else if (rent <= 38000) {
                                                            uploadData();
                                                        }
                                                    } else if (prop_area.equals("Govandi E") || prop_area.equals("Govandi W") || prop_area.equals("Kopar Khairane") || prop_area.equals("Mire Road E") || prop_area.equals("Mire Road W") || prop_area.equals("Thane E") || prop_area.equals("Thane W")) {
                                                        if (rent > 24000) {
                                                            uploadLeader();
                                                        } else if (rent <= 24000) {
                                                            uploadData();
                                                        }
                                                    } else if (prop_area.equals("Jogeshwari E") || prop_area.equals("Jogeshwari W") || prop_area.equals("Kalina") || prop_area.equals("Santa Cruz E") || prop_area.equals("Santa Cruz W") || prop_area.equals("Vile Parle E") || prop_area.equals("Vile Parle W")) {
                                                        if (rent > 34000) {
                                                            uploadLeader();
                                                        } else if (rent <= 34000) {
                                                            uploadData();
                                                        }
                                                    } else if (prop_area.equals("Lower Parel") || prop_area.equals("Mahalaxmi") || prop_area.equals("Parel") || prop_area.equals("Powai")) {
                                                        if (rent > 48000) {
                                                            uploadLeader();
                                                        } else if (rent <= 48000) {
                                                            uploadData();
                                                        }
                                                    } else if (prop_area.equals("Mahim E") || prop_area.equals("Mahim W") || prop_area.equals("Vikhroli E") || prop_area.equals("Vikhroli W")) {
                                                        if (rent > 42000) {
                                                            uploadLeader();
                                                        } else if (rent <= 42000) {
                                                            uploadData();
                                                        }
                                                    } else if (prop_area.equals("Mulund E") || prop_area.equals("Mulund W")) {
                                                        if (rent > 26000) {
                                                            uploadLeader();
                                                        } else if (rent <= 26000) {
                                                            uploadData();
                                                        }
                                                    } else if (prop_area.equals("Vashi")) {
                                                        if (rent > 22000) {
                                                            uploadLeader();
                                                        } else if (rent <= 22000) {
                                                            uploadData();
                                                        }
                                                    }

                                                } else {
                                                    if (prop_area.equals("Airoli") || prop_area.equals("Ghansoli") || prop_area.equals("Panvel")) {
                                                        if (rent > 15000) {
                                                            uploadLeader();
                                                        } else if (rent <= 15000) {
                                                            uploadData();
                                                        }
                                                    } else if (prop_area.equals("Amboli")) {
                                                        if (rent > 26000) {
                                                            uploadLeader();
                                                        } else if (rent <= 26000) {
                                                            uploadData();
                                                        }
                                                    } else if (prop_area.equals("Andheri E") || prop_area.equals("Andheri W") || prop_area.equals("Goregaon E") || prop_area.equals("Khar W") || prop_area.equals("Khar E") || prop_area.equals("Kurla E") || prop_area.equals("Kurla W") || prop_area.equals("Malad W")) {
                                                        if (rent > 32000) {
                                                            uploadLeader();
                                                        } else if (rent <= 32000) {
                                                            uploadData();
                                                        }
                                                    } else if (prop_area.equals("Bandra E") || prop_area.equals("Bandra W") || prop_area.equals("Fort") || prop_area.equals("Sion E") || prop_area.equals("Sion W") || prop_area.equals("Worli")) {
                                                        if (rent > 40000) {
                                                            uploadLeader();
                                                        } else if (rent <= 40000) {
                                                            uploadData();
                                                        }
                                                    } else if (prop_area.equals("Bhandup E") || prop_area.equals("Bhandup W") || prop_area.equals("Kandivali E") || prop_area.equals("Kandivali W") || prop_area.equals("Malad E")) {
                                                        if (rent > 28000) {
                                                            uploadLeader();
                                                        } else if (rent <= 28000) {
                                                            uploadData();
                                                        }
                                                    } else if (prop_area.equals("Boisar")) {
                                                        if (rent > 19000) {
                                                            uploadLeader();
                                                        } else if (rent <= 19000) {
                                                            uploadData();
                                                        }
                                                    } else if (prop_area.equals("Borivali E") || prop_area.equals("Borivali W") || prop_area.equals("Kharegaon")) {
                                                        if (rent > 24000) {
                                                            uploadLeader();
                                                        } else if (rent <= 24000) {
                                                            uploadData();
                                                        }
                                                    } else if (prop_area.equals("Chembur") || prop_area.equals("Goregaon W")) {
                                                        if (rent > 36000) {
                                                            uploadLeader();
                                                        } else if (rent <= 36000) {
                                                            uploadData();
                                                        }
                                                    } else if (prop_area.equals("Church Gate")) {
                                                        if (rent > 50000) {
                                                            uploadLeader();
                                                        } else if (rent <= 50000) {
                                                            uploadData();
                                                        }
                                                    } else if (prop_area.equals("Colaba")) {
                                                        if (rent > 52000) {
                                                            uploadLeader();
                                                        } else if (rent <= 52000) {
                                                            uploadData();
                                                        }
                                                    } else if (prop_area.equals("Dadar E") || prop_area.equals("Dadar W") || prop_area.equals("Prabhadevi")) {
                                                        if (rent > 46000) {
                                                            uploadLeader();
                                                        } else if (rent <= 46000) {
                                                            uploadData();
                                                        }
                                                    } else if (prop_area.equals("Ghatkopar E") || prop_area.equals("Ghatkopar W") || prop_area.equals("Wadala E") || prop_area.equals("Wadala W")) {
                                                        if (rent > 34000) {
                                                            uploadLeader();
                                                        } else if (rent <= 34000) {
                                                            uploadData();
                                                        }
                                                    } else if (prop_area.equals("Govandi E") || prop_area.equals("Govandi W") || prop_area.equals("Kopar Khairane") || prop_area.equals("Mire Road E") || prop_area.equals("Mire Road W") || prop_area.equals("Thane E") || prop_area.equals("Thane W")) {
                                                        if (rent > 20000) {
                                                            uploadLeader();
                                                        } else if (rent <= 20000) {
                                                            uploadData();
                                                        }
                                                    } else if (prop_area.equals("Jogeshwari E") || prop_area.equals("Jogeshwari W") || prop_area.equals("Kalina") || prop_area.equals("Santa Cruz E") || prop_area.equals("Santa Cruz W") || prop_area.equals("Vile Parle E") || prop_area.equals("Vile Parle W")) {
                                                        if (rent > 30000) {
                                                            uploadLeader();
                                                        } else if (rent <= 30000) {
                                                            uploadData();
                                                        }
                                                    } else if (prop_area.equals("Lower Parel") || prop_area.equals("Mahalaxmi") || prop_area.equals("Parel") || prop_area.equals("Powai")) {
                                                        if (rent > 44000) {
                                                            uploadLeader();
                                                        } else if (rent <= 44000) {
                                                            uploadData();
                                                        }
                                                    } else if (prop_area.equals("Mahim E") || prop_area.equals("Mahim W") || prop_area.equals("Vikhroli E") || prop_area.equals("Vikhroli W")) {
                                                        if (rent > 38000) {
                                                            uploadLeader();
                                                        } else if (rent <= 38000) {
                                                            uploadData();
                                                        }
                                                    } else if (prop_area.equals("Mulund E") || prop_area.equals("Mulund W")) {
                                                        if (rent > 22000) {
                                                            uploadLeader();
                                                        } else if (rent <= 22000) {
                                                            uploadData();
                                                        }
                                                    } else if (prop_area.equals("Vashi")) {
                                                        if (rent > 18000) {
                                                            uploadLeader();
                                                        } else if (rent <= 18000) {
                                                            uploadData();
                                                        }
                                                    }
                                                }
                                            } else if (s1.equals("Three BHK")) {
                                                if (s2.equals("Fully Furnished")) {
                                                    if (prop_area.equals("Airoli") || prop_area.equals("Ghansoli") || prop_area.equals("Panvel")) {
                                                        if (rent > 28500) {
                                                            uploadLeader();
                                                        } else if (rent <= 28500) {
                                                            uploadData();
                                                        }
                                                    } else if (prop_area.equals("Amboli")) {
                                                        if (rent > 45000) {
                                                            uploadLeader();
                                                        } else if (rent <= 45000) {
                                                            uploadData();
                                                        }
                                                    } else if (prop_area.equals("Andheri E") || prop_area.equals("Andheri W") || prop_area.equals("Goregaon E") || prop_area.equals("Khar W") || prop_area.equals("Khar E") || prop_area.equals("Kurla E") || prop_area.equals("Kurla W") || prop_area.equals("Malad W")) {
                                                        if (rent > 54000) {
                                                            uploadLeader();
                                                        } else if (rent <= 54000) {
                                                            uploadData();
                                                        }
                                                    } else if (prop_area.equals("Bandra E") || prop_area.equals("Bandra W") || prop_area.equals("Fort") || prop_area.equals("Sion E") || prop_area.equals("Sion W") || prop_area.equals("Worli")) {
                                                        if (rent > 66000) {
                                                            uploadLeader();
                                                        } else if (rent <= 66000) {
                                                            uploadData();
                                                        }
                                                    } else if (prop_area.equals("Bhandup E") || prop_area.equals("Bhandup W") || prop_area.equals("Kandivali E") || prop_area.equals("Kandivali W") || prop_area.equals("Malad E")) {
                                                        if (rent > 48000) {
                                                            uploadLeader();
                                                        } else if (rent <= 48000) {
                                                            uploadData();
                                                        }
                                                    } else if (prop_area.equals("Boisar")) {
                                                        if (rent > 34500) {
                                                            uploadLeader();
                                                        } else if (rent <= 34500) {
                                                            uploadData();
                                                        }
                                                    } else if (prop_area.equals("Borivali E") || prop_area.equals("Borivali W") || prop_area.equals("Kharegaon")) {
                                                        if (rent > 42000) {
                                                            uploadLeader();
                                                        } else if (rent <= 42000) {
                                                            uploadData();
                                                        }
                                                    } else if (prop_area.equals("Chembur") || prop_area.equals("Goregaon W")) {
                                                        if (rent > 60000) {
                                                            uploadLeader();
                                                        } else if (rent <= 60000) {
                                                            uploadData();
                                                        }
                                                    } else if (prop_area.equals("Church Gate")) {
                                                        if (rent > 81000) {
                                                            uploadLeader();
                                                        } else if (rent <= 81000) {
                                                            uploadData();
                                                        }
                                                    } else if (prop_area.equals("Colaba")) {
                                                        if (rent > 84000) {
                                                            uploadLeader();
                                                        } else if (rent <= 84000) {
                                                            uploadData();
                                                        }
                                                    } else if (prop_area.equals("Dadar E") || prop_area.equals("Dadar W") || prop_area.equals("Prabhadevi")) {
                                                        if (rent > 75000) {
                                                            uploadLeader();
                                                        } else if (rent <= 75000) {
                                                            uploadData();
                                                        }
                                                    } else if (prop_area.equals("Ghatkopar E") || prop_area.equals("Ghatkopar W") || prop_area.equals("Wadala E") || prop_area.equals("Wadala W")) {
                                                        if (rent > 57000) {
                                                            uploadLeader();
                                                        } else if (rent <= 57000) {
                                                            uploadData();
                                                        }
                                                    } else if (prop_area.equals("Govandi E") || prop_area.equals("Govandi W") || prop_area.equals("Kopar Khairane") || prop_area.equals("Mire Road E") || prop_area.equals("Mire Road W") || prop_area.equals("Thane E") || prop_area.equals("Thane W")) {
                                                        if (rent > 36000) {
                                                            uploadLeader();
                                                        } else if (rent <= 36000) {
                                                            uploadData();
                                                        }
                                                    } else if (prop_area.equals("Jogeshwari E") || prop_area.equals("Jogeshwari W") || prop_area.equals("Kalina") || prop_area.equals("Santa Cruz E") || prop_area.equals("Santa Cruz W") || prop_area.equals("Vile Parle E") || prop_area.equals("Vile Parle W")) {
                                                        if (rent > 51000) {
                                                            uploadLeader();
                                                        } else if (rent <= 51000) {
                                                            uploadData();
                                                        }
                                                    } else if (prop_area.equals("Lower Parel") || prop_area.equals("Mahalaxmi") || prop_area.equals("Parel") || prop_area.equals("Powai")) {
                                                        if (rent > 72000) {
                                                            uploadLeader();
                                                        } else if (rent <= 72000) {
                                                            uploadData();
                                                        }
                                                    } else if (prop_area.equals("Mahim E") || prop_area.equals("Mahim W") || prop_area.equals("Vikhroli E") || prop_area.equals("Vikhroli W")) {
                                                        if (rent > 63000) {
                                                            uploadLeader();
                                                        } else if (rent <= 63000) {
                                                            uploadData();
                                                        }
                                                    } else if (prop_area.equals("Mulund E") || prop_area.equals("Mulund W")) {
                                                        if (rent > 39000) {
                                                            uploadLeader();
                                                        } else if (rent <= 39000) {
                                                            uploadData();
                                                        }
                                                    } else if (prop_area.equals("Vashi")) {
                                                        if (rent > 33000) {
                                                            uploadLeader();
                                                        } else if (rent <= 33000) {
                                                            uploadData();
                                                        }
                                                    }
                                                } else {
                                                    if (prop_area.equals("Airoli") || prop_area.equals("Ghansoli") || prop_area.equals("Panvel")) {
                                                        if (rent > 22500) {
                                                            uploadLeader();
                                                        } else if (rent <= 22500) {
                                                            uploadData();
                                                        }
                                                    } else if (prop_area.equals("Amboli")) {
                                                        if (rent > 39000) {
                                                            uploadLeader();
                                                        } else if (rent <= 39000) {
                                                            uploadData();
                                                        }
                                                    } else if (prop_area.equals("Andheri E") || prop_area.equals("Andheri W") || prop_area.equals("Goregaon E") || prop_area.equals("Khar W") || prop_area.equals("Khar E") || prop_area.equals("Kurla E") || prop_area.equals("Kurla W") || prop_area.equals("Malad W")) {
                                                        if (rent > 48000) {
                                                            uploadLeader();
                                                        } else if (rent <= 12000) {
                                                            uploadData();
                                                        }
                                                    } else if (prop_area.equals("Bandra E") || prop_area.equals("Bandra W") || prop_area.equals("Fort") || prop_area.equals("Sion E") || prop_area.equals("Sion W") || prop_area.equals("Worli")) {
                                                        if (rent > 60000) {
                                                            uploadLeader();
                                                        } else if (rent <= 60000) {
                                                            uploadData();
                                                        }
                                                    } else if (prop_area.equals("Bhandup E") || prop_area.equals("Bhandup W") || prop_area.equals("Kandivali E") || prop_area.equals("Kandivali W") || prop_area.equals("Malad E")) {
                                                        if (rent > 42000) {
                                                            uploadLeader();
                                                        } else if (rent <= 42000) {
                                                            uploadData();
                                                        }
                                                    } else if (prop_area.equals("Boisar")) {
                                                        if (rent > 28500) {
                                                            uploadLeader();
                                                        } else if (rent <= 28500) {
                                                            uploadData();
                                                        }
                                                    } else if (prop_area.equals("Borivali E") || prop_area.equals("Borivali W") || prop_area.equals("Kharegaon")) {
                                                        if (rent > 36000) {
                                                            uploadLeader();
                                                        } else if (rent <= 36000) {
                                                            uploadData();
                                                        }
                                                    } else if (prop_area.equals("Chembur") || prop_area.equals("Goregaon W")) {
                                                        if (rent > 54000) {
                                                            uploadLeader();
                                                        } else if (rent <= 54000) {
                                                            uploadData();
                                                        }
                                                    } else if (prop_area.equals("Church Gate")) {
                                                        if (rent > 75000) {
                                                            uploadLeader();
                                                        } else if (rent <= 75000) {
                                                            uploadData();
                                                        }
                                                    } else if (prop_area.equals("Colaba")) {
                                                        if (rent > 78000) {
                                                            uploadLeader();
                                                        } else if (rent <= 78000) {
                                                            uploadData();
                                                        }
                                                    } else if (prop_area.equals("Dadar E") || prop_area.equals("Dadar W") || prop_area.equals("Prabhadevi")) {
                                                        if (rent > 69000) {
                                                            uploadLeader();
                                                        } else if (rent <= 69000) {
                                                            uploadData();
                                                        }
                                                    } else if (prop_area.equals("Ghatkopar E") || prop_area.equals("Ghatkopar W") || prop_area.equals("Wadala E") || prop_area.equals("Wadala W")) {
                                                        if (rent > 51000) {
                                                            uploadLeader();
                                                        } else if (rent <= 51000) {
                                                            uploadData();
                                                        }
                                                    } else if (prop_area.equals("Govandi E") || prop_area.equals("Govandi W") || prop_area.equals("Kopar Khairane") || prop_area.equals("Mire Road E") || prop_area.equals("Mire Road W") || prop_area.equals("Thane E") || prop_area.equals("Thane W")) {
                                                        if (rent > 30000) {
                                                            uploadLeader();
                                                        } else if (rent <= 30000) {
                                                            uploadData();
                                                        }
                                                    } else if (prop_area.equals("Jogeshwari E") || prop_area.equals("Jogeshwari W") || prop_area.equals("Kalina") || prop_area.equals("Santa Cruz E") || prop_area.equals("Santa Cruz W") || prop_area.equals("Vile Parle E") || prop_area.equals("Vile Parle W")) {
                                                        if (rent > 45000) {
                                                            uploadLeader();
                                                        } else if (rent <= 45000) {
                                                            uploadData();
                                                        }
                                                    } else if (prop_area.equals("Lower Parel") || prop_area.equals("Mahalaxmi") || prop_area.equals("Parel") || prop_area.equals("Powai")) {
                                                        if (rent > 66000) {
                                                            uploadLeader();
                                                        } else if (rent <= 66000) {
                                                            uploadData();
                                                        }
                                                    } else if (prop_area.equals("Mahim E") || prop_area.equals("Mahim W") || prop_area.equals("Vikhroli E") || prop_area.equals("Vikhroli W")) {
                                                        if (rent > 57000) {
                                                            uploadLeader();
                                                        } else if (rent <= 57000) {
                                                            uploadData();
                                                        }
                                                    } else if (prop_area.equals("Mulund E") || prop_area.equals("Mulund W")) {
                                                        if (rent > 33000) {
                                                            uploadLeader();
                                                        } else if (rent <= 33000) {
                                                            uploadData();
                                                        }
                                                    } else if (prop_area.equals("Vashi")) {
                                                        if (rent > 27000) {
                                                            uploadLeader();
                                                        } else if (rent <= 27000) {
                                                            uploadData();
                                                        }
                                                    }
                                                }
                                            } else {
                                                uploadData();
                                            }
                                            Toast.makeText(Option1.this, "Upload Complete", Toast.LENGTH_LONG).show();
                                            if(times.equals("One"))
                                            {
                                                startActivity(new Intent(Option1.this,MainActivity.class));
                                            }
                                            else if (times.equals("Two"))
                                            {
                                                Intent i = new Intent(Option1.this,Option1.class);
                                                i.putExtra("proptype2",proptype);
                                                i.putExtra("cust_name2",cust_name);
                                                i.putExtra("prop_name2",prop_name);
                                                i.putExtra("cust_num2",cust_num);
                                                i.putExtra("alt_num2",altcust_num);
                                                i.putExtra("cust_em_site2",cust_em_site);
                                                i.putExtra("prop_area2",prop_area);
                                                i.putExtra("prop_variant2",prop_variant);
                                                i.putExtra("prop_locality2",prop_locality);
                                                i.putExtra("times2","One");
                                                i.putExtra("uri1",uristring);
                                                startActivity(i);
                                            }
                                            else if (times.equals("Three"))
                                            {
                                                Intent i = new Intent(Option1.this,Option1.class);
                                                i.putExtra("proptype2",proptype);
                                                i.putExtra("cust_name2",cust_name);
                                                i.putExtra("prop_name2",prop_name);
                                                i.putExtra("cust_num2",cust_num);
                                                i.putExtra("alt_num2",altcust_num);
                                                i.putExtra("cust_em_site2",cust_em_site);
                                                i.putExtra("prop_area2",prop_area);
                                                i.putExtra("prop_variant2",prop_variant);
                                                i.putExtra("prop_locality2",prop_locality);
                                                i.putExtra("times2","Two");
                                                i.putExtra("uri1",uristring);
                                                startActivity(i);
                                            }
                                            else if (times.equals("Four"))
                                            {
                                                Intent i = new Intent(Option1.this,Option1.class);
                                                i.putExtra("proptype2",proptype);
                                                i.putExtra("cust_name2",cust_name);
                                                i.putExtra("prop_name2",prop_name);
                                                i.putExtra("cust_num2",cust_num);
                                                i.putExtra("alt_num2",altcust_num);
                                                i.putExtra("cust_em_site2",cust_em_site);
                                                i.putExtra("prop_area2",prop_area);
                                                i.putExtra("prop_variant2",prop_variant);
                                                i.putExtra("prop_locality2",prop_locality);
                                                i.putExtra("times2","Three");
                                                i.putExtra("uri1",uristring);
                                                startActivity(i);
                                            }
                                            else if (times.equals("Five"))
                                            {
                                                Intent i = new Intent(Option1.this,Option1.class);
                                                i.putExtra("proptype2",proptype);
                                                i.putExtra("cust_name2",cust_name);
                                                i.putExtra("prop_name2",prop_name);
                                                i.putExtra("cust_num2",cust_num);
                                                i.putExtra("alt_num2",altcust_num);
                                                i.putExtra("cust_em_site2",cust_em_site);
                                                i.putExtra("prop_area2",prop_area);
                                                i.putExtra("prop_variant2",prop_variant);
                                                i.putExtra("prop_locality2",prop_locality);
                                                i.putExtra("times2","Four");
                                                i.putExtra("uri1",uristring);
                                                startActivity(i);
                                            }
                                            else if (times.equals("Six"))
                                            {
                                                Intent i = new Intent(Option1.this,Option1.class);
                                                i.putExtra("proptype2",proptype);
                                                i.putExtra("cust_name2",cust_name);
                                                i.putExtra("prop_name2",prop_name);
                                                i.putExtra("cust_num2",cust_num);
                                                i.putExtra("alt_num2",altcust_num);
                                                i.putExtra("cust_em_site2",cust_em_site);
                                                i.putExtra("prop_area2",prop_area);
                                                i.putExtra("prop_variant2",prop_variant);
                                                i.putExtra("prop_locality2",prop_locality);
                                                i.putExtra("times2","Five");
                                                i.putExtra("uri1",uristring);
                                                startActivity(i);
                                            }
                                            else if (times.equals("Seven"))
                                            {
                                                Intent i = new Intent(Option1.this,Option1.class);
                                                i.putExtra("proptype2",proptype);
                                                i.putExtra("cust_name2",cust_name);
                                                i.putExtra("prop_name2",prop_name);
                                                i.putExtra("cust_num2",cust_num);
                                                i.putExtra("alt_num2",altcust_num);
                                                i.putExtra("cust_em_site2",cust_em_site);
                                                i.putExtra("prop_area2",prop_area);
                                                i.putExtra("prop_variant2",prop_variant);
                                                i.putExtra("prop_locality2",prop_locality);
                                                i.putExtra("times2","Six");
                                                i.putExtra("uri1",uristring);
                                                startActivity(i);
                                            }
                                            else if (times.equals("Eight"))
                                            {
                                                Intent i = new Intent(Option1.this,Option1.class);
                                                i.putExtra("proptype2",proptype);
                                                i.putExtra("cust_name2",cust_name);
                                                i.putExtra("prop_name2",prop_name);
                                                i.putExtra("cust_num2",cust_num);
                                                i.putExtra("alt_num2",altcust_num);
                                                i.putExtra("cust_em_site2",cust_em_site);
                                                i.putExtra("prop_area2",prop_area);
                                                i.putExtra("prop_variant2",prop_variant);
                                                i.putExtra("prop_locality2",prop_locality);
                                                i.putExtra("times2","Seven");
                                                i.putExtra("uri1",uristring);
                                                startActivity(i);
                                            }
                                        }
                                    })
                                            .addOnFailureListener(new OnFailureListener() {
                                                @Override
                                                public void onFailure(@NonNull Exception e) {
                                                    dialog.dismiss();
                                                    Toast.makeText(Option1.this,e.getMessage(),Toast.LENGTH_SHORT).show();
                                                }
                                            }).addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
                                        @Override
                                        public void onProgress(UploadTask.TaskSnapshot taskSnapshot) {
                                            double progress =(100 * taskSnapshot.getBytesTransferred())/taskSnapshot.getTotalByteCount();
                                            dialog.setMessage("Uploaded "+(int)progress+"%");
                                        }
                                    });
                                }
                                else {
                                    Toast.makeText(Option1.this,"Please select image",Toast.LENGTH_SHORT).show();
                                }
                            }
                        }
                    }
                }
            }
        });
        tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatePickerDialog datePickerDialog = new DatePickerDialog(Option1.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                        monthOfYear = monthOfYear+1;
                        tv.setText(dayOfMonth+"/"+monthOfYear+"/"+year);
                    }
                },year, month, day);
                datePickerDialog.show();
            }
        });
    }
    void uploadLeader(){
        databaseReference1 = database.getInstance().getReference().child("teamleader");
        final DatabaseReference newPost1 = databaseReference1.push();
        newPost1.child("title").setValue(prop_name);
        newPost1.child("proptype").setValue(proptype);
        newPost1.child("custnum").setValue(cust_num);
        newPost1.child("altnum").setValue(altcust_num);
        newPost1.child("email").setValue(cust_em_site);
        newPost1.child("area").setValue(prop_area);
        newPost1.child("variant").setValue(prop_variant);
        newPost1.child("locality").setValue(prop_locality);
        newPost1.child("size").setValue(s1);
        newPost1.child("fur").setValue(s2);
        newPost1.child("rent").setValue(s3);
        newPost1.child("date").setValue(s8);
        newPost1.child("deposit").setValue(s4);
        newPost1.child("brokerage").setValue(s5);
        newPost1.child("ten").setValue(s6);
        newPost1.child("sply").setValue(s7);
        newPost1.child("desc").setValue(cust_name);
        newPost1.child("status").setValue("Uploaded");
        newPost1.child("image").setValue(downloadurl.toString());
        Toast.makeText(Option1.this, "Upload Complete", Toast.LENGTH_LONG).show();
    }
    void uploadData(){
        databaseReference = database.getInstance().getReference().child("image");
        final DatabaseReference newPost = databaseReference.push();
        newPost.child("title").setValue(prop_name);
        newPost.child("proptype").setValue(proptype);
        newPost.child("custnum").setValue(cust_num);
        newPost.child("altnum").setValue(altcust_num);
        newPost.child("email").setValue(cust_em_site);
        newPost.child("area").setValue(prop_area);
        newPost.child("variant").setValue(prop_variant);
        newPost.child("locality").setValue(prop_locality);
        newPost.child("size").setValue(s1);
        newPost.child("fur").setValue(s2);
        newPost.child("rent").setValue(s3);
        newPost.child("date").setValue(s8);
        newPost.child("deposit").setValue(s4);
        newPost.child("brokerage").setValue(s5);
        newPost.child("ten").setValue(s6);
        newPost.child("sply").setValue(s7);
        newPost.child("desc").setValue(cust_name);
        newPost.child("status").setValue("Uploaded");
        newPost.child("image").setValue(downloadurl.toString());
        Toast.makeText(Option1.this, "Upload Complete", Toast.LENGTH_LONG).show();
    }
}
