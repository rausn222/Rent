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

public class Option2 extends AppCompatActivity {
    Spinner size,furnishing,vacancy,p_tenant;
    EditText vacancies,rent,deposit,brokerage,s_notes;
    TextView d;
    Calendar podate;
    int day,month,year;
    String size2,furnishing2,vacancy2,p_tenant2,vacanct2,rent2,deposit2,brokerage2,spplynotes,date2;
    Button smt2;
    int vac,re,dep,brok;
    String proptype,cust_name,prop_name,cust_num,altcust_num,cust_em_site,prop_area,prop_variant,prop_locality,times,uristring;
    private StorageReference storageReference;
    private FirebaseDatabase database;
    private Uri uri = null;
    private DatabaseReference databaseReference,databaseReference1;
    ProgressBar progressBar2;
    Uri downloadurl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_option2);
        size = (Spinner)findViewById(R.id.spinner8);
        furnishing = (Spinner)findViewById(R.id.spinner9);
        vacancy = (Spinner)findViewById(R.id.spinner10);
        p_tenant = (Spinner)findViewById(R.id.spinner11);
        vacancies = (EditText)findViewById(R.id.vacancies_id);
        rent = (EditText)findViewById(R.id.rent_id);
        deposit = (EditText)findViewById(R.id.deposit_id);
        brokerage = (EditText)findViewById(R.id.brokerage_id);
        s_notes = (EditText)findViewById(R.id.splynotes2_id);
        d = (TextView)findViewById(R.id.date2_id);
        smt2 = (Button) findViewById(R.id.opt2smt);
        progressBar2 = (ProgressBar)findViewById(R.id.op2progressbar);
        storageReference = FirebaseStorage.getInstance().getReference();
        databaseReference = database.getInstance().getReference().child("image");
        databaseReference1 = database.getInstance().getReference().child("teamleader");
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
        size.setFocusable(true);
        size.setFocusableInTouchMode(true);
        size.requestFocus();
        podate = Calendar.getInstance();
        day = podate.get(Calendar.DAY_OF_MONTH);
        month = podate.get(Calendar.MONTH);
        year = podate.get(Calendar.YEAR);
        month = month+1;
        d.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatePickerDialog datePickerDialog = new DatePickerDialog(Option2.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                        monthOfYear = monthOfYear+1;
                        d.setText(dayOfMonth+"/"+monthOfYear+"/"+year);
                    }
                },year, month, day);
                datePickerDialog.show();
            }
        });
        smt2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                size2 = size.getSelectedItem().toString();
                furnishing2 = furnishing.getSelectedItem().toString();
                vacancy2 = vacancy.getSelectedItem().toString();
                p_tenant2 = p_tenant.getSelectedItem().toString();
                vacanct2 = vacancies.getText().toString();
                rent2 = rent.getText().toString();
                deposit2 = deposit.getText().toString();
                brokerage2 = brokerage.getText().toString();
                spplynotes = s_notes.getText().toString();
                date2 = d.getText().toString();
                if(size2.equals("Select size of property"))
                {
                    Toast.makeText(Option2.this,"Please select size of property.",Toast.LENGTH_SHORT).show();
                }
                else if (furnishing2.equals("Select furnishing level"))
                {
                    Toast.makeText(Option2.this,"Please select furnishing level of property.",Toast.LENGTH_SHORT).show();
                }
                else if (vacancy2.equals("Select available vacancy"))
                {
                    Toast.makeText(Option2.this,"Please select vacancy of property.",Toast.LENGTH_SHORT).show();
                }
                else if (vacanct2.equals(""))
                {
                    Toast.makeText(Option2.this,"Please enter vacancy of property.",Toast.LENGTH_SHORT).show();
                }
                else if (!vacanct2.equals(""))
                {
                    vac = Integer.parseInt(vacanct2);
                    if (vac<=0)
                    {
                        Toast.makeText(Option2.this,"Please enter valid vacancy of property.",Toast.LENGTH_SHORT).show();
                    }
                    else if (rent2.equals(""))
                    {
                        Toast.makeText(Option2.this,"Please enter rent of property.",Toast.LENGTH_SHORT).show();
                    }
                    else if (!rent2.equals(""))
                    {
                        re = Integer.parseInt(rent2);
                        if (re<=1)
                        {
                            Toast.makeText(Option2.this,"Please enter valid rent of property.",Toast.LENGTH_SHORT).show();
                        }
                        else if (date2.equals("  Available From"))
                        {
                            Toast.makeText(Option2.this,"Please enter date.",Toast.LENGTH_SHORT).show();
                        }
                        else if (deposit2.equals(""))
                        {
                            Toast.makeText(Option2.this,"Please enter deposit for property.",Toast.LENGTH_SHORT).show();
                        }
                        else if (!deposit2.equals(""))
                        {
                            dep = Integer.parseInt(deposit2);
                            if (dep<=1)
                            {
                                Toast.makeText(Option2.this,"Please enter valid deposit of property.",Toast.LENGTH_SHORT).show();
                            }
                            else if (brokerage2.equals(""))
                            {
                                Toast.makeText(Option2.this,"Please enter brokerage of property.",Toast.LENGTH_SHORT).show();
                            }
                            else if (!brokerage2.equals("0"))
                            {
                                brok = Integer.parseInt(brokerage2);
                                if (brok<=0)
                                {
                                    Toast.makeText(Option2.this,"Please enter valid brokerage of property.",Toast.LENGTH_SHORT).show();
                                }
                                else if (p_tenant2.equals("Select preferred tenant"))
                                {
                                    Toast.makeText(Option2.this,"Please select tenant for property.",Toast.LENGTH_SHORT).show();
                                }
                                else if (spplynotes.equals("")){
                                    Toast.makeText(Option2.this,"Please enter supply notes",Toast.LENGTH_SHORT).show();
                                }
                                else {
                                    uri =Uri.parse(uristring);
                                    AlertDialog alertDialog = new AlertDialog.Builder(Option2.this).create();
                                    alertDialog.setTitle("Brokerage Alert");
                                    alertDialog.setMessage("Property involves brokerage!");
                                    alertDialog.setButton("OK", new DialogInterface.OnClickListener(){
                                        public void onClick(DialogInterface dialog, int which) {
                                            if (uri != null) {
                                                final ProgressDialog dialog1 = new ProgressDialog(Option2.this);
                                                dialog1.setTitle("Uploading Data");
                                                dialog1.show();
                                                dialog1.setCancelable(false);
                                                StorageReference filePath = storageReference.child("PostImage").child(uri.getLastPathSegment());
                                                filePath.putFile(uri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                                                    @Override
                                                    public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                                                        downloadurl = taskSnapshot.getDownloadUrl();
                                                        dialog1.dismiss();
                                                        if (vacanct2.equals("Single Occupancy"))
                                                        {
                                                            if (furnishing2.equals("Fully Furnished")) {
                                                                if (prop_area.equals("Airoli") || prop_area.equals("Ghansoli") || prop_area.equals("Panvel")) {
                                                                    if (re > 7500) {
                                                                        uploadLeader();
                                                                    } else if (re <= 7500) {
                                                                        uploadData();
                                                                    }
                                                                } else if (prop_area.equals("Amboli")) {
                                                                    if (re > 13000) {
                                                                        uploadLeader();
                                                                    } else if (re <= 13000) {
                                                                        uploadData();
                                                                    }
                                                                } else if (prop_area.equals("Andheri E") || prop_area.equals("Andheri W") || prop_area.equals("Goregaon E") || prop_area.equals("Khar W") || prop_area.equals("Khar E") || prop_area.equals("Kurla E") || prop_area.equals("Kurla W") || prop_area.equals("Malad W")) {
                                                                    if (re > 16000) {
                                                                        uploadLeader();
                                                                    } else if (re <= 16000) {
                                                                        uploadData();
                                                                    }
                                                                } else if (prop_area.equals("Bandra E") || prop_area.equals("Bandra W") || prop_area.equals("Fort") || prop_area.equals("Sion E") || prop_area.equals("Sion W") || prop_area.equals("Worli")) {
                                                                    if (re > 20000) {
                                                                        uploadLeader();
                                                                    } else if (re <= 20000) {
                                                                        uploadData();
                                                                    }
                                                                } else if (prop_area.equals("Bhandup E") || prop_area.equals("Bhandup W") || prop_area.equals("Kandivali E") || prop_area.equals("Kandivali W") || prop_area.equals("Malad E")) {
                                                                    if (re > 14000) {
                                                                        uploadLeader();
                                                                    } else if (re <= 14000) {
                                                                        uploadData();
                                                                    }
                                                                } else if (prop_area.equals("Boisar")) {
                                                                    if (re > 9500) {
                                                                        uploadLeader();
                                                                    } else if (re <= 9500) {
                                                                        uploadData();
                                                                    }
                                                                } else if (prop_area.equals("Borivali E") || prop_area.equals("Borivali W") || prop_area.equals("Kharegaon")) {
                                                                    if (re > 12000) {
                                                                        uploadLeader();
                                                                    } else if (re <= 12000) {
                                                                        uploadData();
                                                                    }
                                                                } else if (prop_area.equals("Chembur") || prop_area.equals("Goregaon W")) {
                                                                    if (re > 18000) {
                                                                        uploadLeader();
                                                                    } else if (re <= 18000) {
                                                                        uploadData();
                                                                    }
                                                                } else if (prop_area.equals("Church Gate")) {
                                                                    if (re > 25000) {
                                                                        uploadLeader();
                                                                    } else if (re <= 25000) {
                                                                        uploadData();
                                                                    }
                                                                } else if (prop_area.equals("Colaba")) {
                                                                    if (re > 26000) {
                                                                        uploadLeader();
                                                                    } else if (re <= 26000) {
                                                                        uploadData();
                                                                    }
                                                                } else if (prop_area.equals("Dadar E") || prop_area.equals("Dadar W") || prop_area.equals("Prabhadevi")) {
                                                                    if (re > 23000) {
                                                                        uploadLeader();
                                                                    } else if (re <= 23000) {
                                                                        uploadData();
                                                                    }
                                                                } else if (prop_area.equals("Ghatkopar E") || prop_area.equals("Ghatkopar W") || prop_area.equals("Wadala E") || prop_area.equals("Wadala W")) {
                                                                    if (re > 17000) {
                                                                        uploadLeader();
                                                                    } else if (re <= 17000) {
                                                                        uploadData();
                                                                    }
                                                                } else if (prop_area.equals("Govandi E") || prop_area.equals("Govandi W") || prop_area.equals("Kopar Khairane") || prop_area.equals("Mire Road E") || prop_area.equals("Mire Road W") || prop_area.equals("Thane E") || prop_area.equals("Thane W")) {
                                                                    if (re > 10000) {
                                                                        uploadLeader();
                                                                    } else if (re <= 10000) {
                                                                        uploadData();
                                                                    }
                                                                } else if (prop_area.equals("Jogeshwari E") || prop_area.equals("Jogeshwari W") || prop_area.equals("Kalina") || prop_area.equals("Santa Cruz E") || prop_area.equals("Santa Cruz W") || prop_area.equals("Vile Parle E") || prop_area.equals("Vile Parle W")) {
                                                                    if (re > 15000) {
                                                                        uploadLeader();
                                                                    } else if (re <= 15000) {
                                                                        uploadData();
                                                                    }
                                                                } else if (prop_area.equals("Lower Parel") || prop_area.equals("Mahalaxmi") || prop_area.equals("Parel") || prop_area.equals("Powai")) {
                                                                    if (re > 22000) {
                                                                        uploadLeader();
                                                                    } else if (re <= 22000) {
                                                                        uploadData();
                                                                    }
                                                                } else if (prop_area.equals("Mahim E") || prop_area.equals("Mahim W") || prop_area.equals("Vikhroli E") || prop_area.equals("Vikhroli W")) {
                                                                    if (re > 19000) {
                                                                        uploadLeader();
                                                                    } else if (re <= 19000) {
                                                                        uploadData();
                                                                    }
                                                                } else if (prop_area.equals("Mulund E") || prop_area.equals("Mulund W")) {
                                                                    if (re > 11000) {
                                                                        uploadLeader();
                                                                    } else if (re <= 11000) {
                                                                        uploadData();
                                                                    }
                                                                } else if (prop_area.equals("Vashi")) {
                                                                    if (re > 9000) {
                                                                        uploadLeader();
                                                                    } else if (re <= 9000) {
                                                                        uploadData();
                                                                    }
                                                                }
                                                            }
                                                            else {
                                                                if (prop_area.equals("Airoli") || prop_area.equals("Ghansoli") || prop_area.equals("Panvel")) {
                                                                    if (re > 3500) {
                                                                        uploadLeader();
                                                                    } else if (re <= 3500) {
                                                                        uploadData();
                                                                    }
                                                                } else if (prop_area.equals("Amboli")) {
                                                                    if (re > 9000) {
                                                                        uploadLeader();
                                                                    } else if (re <= 9000) {
                                                                        uploadData();
                                                                    }
                                                                } else if (prop_area.equals("Andheri E") || prop_area.equals("Andheri W") || prop_area.equals("Goregaon E") || prop_area.equals("Khar W") || prop_area.equals("Khar E") || prop_area.equals("Kurla E") || prop_area.equals("Kurla W") || prop_area.equals("Malad W")) {
                                                                    if (re > 12000) {
                                                                        uploadLeader();
                                                                    } else if (re <= 12000) {
                                                                        uploadData();
                                                                    }
                                                                } else if (prop_area.equals("Bandra E") || prop_area.equals("Bandra W") || prop_area.equals("Fort") || prop_area.equals("Sion E") || prop_area.equals("Sion W") || prop_area.equals("Worli")) {
                                                                    if (re > 16000) {
                                                                        uploadLeader();
                                                                    } else if (re <= 16000) {
                                                                        uploadData();
                                                                    }
                                                                } else if (prop_area.equals("Bhandup E") || prop_area.equals("Bhandup W") || prop_area.equals("Kandivali E") || prop_area.equals("Kandivali W") || prop_area.equals("Malad E")) {
                                                                    if (re > 10000) {
                                                                        uploadLeader();
                                                                    } else if (re <= 10000) {
                                                                        uploadData();
                                                                    }
                                                                } else if (prop_area.equals("Boisar")) {
                                                                    if (re > 5500) {
                                                                        uploadLeader();
                                                                    } else if (re <= 5500) {
                                                                        uploadData();
                                                                    }
                                                                } else if (prop_area.equals("Borivali E") || prop_area.equals("Borivali W") || prop_area.equals("Kharegaon")) {
                                                                    if (re > 8000) {
                                                                        uploadLeader();
                                                                    } else if (re <= 8000) {
                                                                        uploadData();
                                                                    }
                                                                } else if (prop_area.equals("Chembur") || prop_area.equals("Goregaon W")) {
                                                                    if (re > 14000) {
                                                                        uploadLeader();
                                                                    } else if (re <= 14000) {
                                                                        uploadData();
                                                                    }
                                                                } else if (prop_area.equals("Church Gate")) {
                                                                    if (re > 21000) {
                                                                        uploadLeader();
                                                                    } else if (re <= 21000) {
                                                                        uploadData();
                                                                    }
                                                                } else if (prop_area.equals("Colaba")) {
                                                                    if (re > 22000) {
                                                                        uploadLeader();
                                                                    } else if (re <= 22000) {
                                                                        uploadData();
                                                                    }
                                                                } else if (prop_area.equals("Dadar E") || prop_area.equals("Dadar W") || prop_area.equals("Prabhadevi")) {
                                                                    if (re > 19000) {
                                                                        uploadLeader();
                                                                    } else if (re <= 19000) {
                                                                        uploadData();
                                                                    }
                                                                } else if (prop_area.equals("Ghatkopar E") || prop_area.equals("Ghatkopar W") || prop_area.equals("Wadala E") || prop_area.equals("Wadala W")) {
                                                                    if (re > 13000) {
                                                                        uploadLeader();
                                                                    } else if (re <= 13000) {
                                                                        uploadData();
                                                                    }
                                                                } else if (prop_area.equals("Govandi E") || prop_area.equals("Govandi W") || prop_area.equals("Kopar Khairane") || prop_area.equals("Mire Road E") || prop_area.equals("Mire Road W") || prop_area.equals("Thane E") || prop_area.equals("Thane W")) {
                                                                    if (re > 6000) {
                                                                        uploadLeader();
                                                                    } else if (re <= 6000) {
                                                                        uploadData();
                                                                    }
                                                                } else if (prop_area.equals("Jogeshwari E") || prop_area.equals("Jogeshwari W") || prop_area.equals("Kalina") || prop_area.equals("Santa Cruz E") || prop_area.equals("Santa Cruz W") || prop_area.equals("Vile Parle E") || prop_area.equals("Vile Parle W")) {
                                                                    if (re > 11000) {
                                                                        uploadLeader();
                                                                    } else if (re <= 11000) {
                                                                        uploadData();
                                                                    }
                                                                } else if (prop_area.equals("Lower Parel") || prop_area.equals("Mahalaxmi") || prop_area.equals("Parel") || prop_area.equals("Powai")) {
                                                                    if (re > 18000) {
                                                                        uploadLeader();
                                                                    } else if (re <= 18000) {
                                                                        uploadData();
                                                                    }
                                                                } else if (prop_area.equals("Mahim E") || prop_area.equals("Mahim W") || prop_area.equals("Vikhroli E") || prop_area.equals("Vikhroli W")) {
                                                                    if (re > 15000) {
                                                                        uploadLeader();
                                                                    } else if (re <= 15000) {
                                                                        uploadData();
                                                                    }
                                                                } else if (prop_area.equals("Mulund E") || prop_area.equals("Mulund W")) {
                                                                    if (re > 7000) {
                                                                        uploadLeader();
                                                                    } else if (re <= 7000) {
                                                                        uploadData();
                                                                    }
                                                                } else if (prop_area.equals("Vashi")) {
                                                                    if (re > 5000) {
                                                                        uploadLeader();
                                                                    } else if (re <= 5000) {
                                                                        uploadData();
                                                                    }
                                                                }
                                                            }
                                                        }
                                                        else if (vacanct2.equals("Double Occupancy")) {
                                                            if (furnishing2.equals("Fully Furnished")) {
                                                                if (prop_area.equals("Airoli") || prop_area.equals("Ghansoli") || prop_area.equals("Panvel")) {
                                                                    if (re > 3000) {
                                                                        uploadLeader();
                                                                    } else if (re <= 3000) {
                                                                        uploadData();
                                                                    }
                                                                } else if (prop_area.equals("Amboli")) {
                                                                    if (re > 5500) {
                                                                        uploadLeader();
                                                                    } else if (re <= 5500) {
                                                                        uploadData();
                                                                    }
                                                                } else if (prop_area.equals("Andheri E") || prop_area.equals("Andheri W") || prop_area.equals("Goregaon E") || prop_area.equals("Khar W") || prop_area.equals("Khar E") || prop_area.equals("Kurla E") || prop_area.equals("Kurla W") || prop_area.equals("Malad W")) {
                                                                    if (re > 7000) {
                                                                        uploadLeader();
                                                                    } else if (re <= 7000) {
                                                                        uploadData();
                                                                    }
                                                                } else if (prop_area.equals("Bandra E") || prop_area.equals("Bandra W") || prop_area.equals("Fort") || prop_area.equals("Sion E") || prop_area.equals("Sion W") || prop_area.equals("Worli")) {
                                                                    if (re > 9000) {
                                                                        uploadLeader();
                                                                    } else if (re <= 9000) {
                                                                        uploadData();
                                                                    }
                                                                } else if (prop_area.equals("Bhandup E") || prop_area.equals("Bhandup W") || prop_area.equals("Kandivali E") || prop_area.equals("Kandivali W") || prop_area.equals("Malad E")) {
                                                                    if (re > 6000) {
                                                                        uploadLeader();
                                                                    } else if (re <= 6000) {
                                                                        uploadData();
                                                                    }
                                                                } else if (prop_area.equals("Boisar")) {
                                                                    if (re > 3500) {
                                                                        uploadLeader();
                                                                    } else if (re <= 3500) {
                                                                        uploadData();
                                                                    }
                                                                } else if (prop_area.equals("Borivali E") || prop_area.equals("Borivali W") || prop_area.equals("Kharegaon")) {
                                                                    if (re > 5000) {
                                                                        uploadLeader();
                                                                    } else if (re <= 5000) {
                                                                        uploadData();
                                                                    }
                                                                } else if (prop_area.equals("Chembur") || prop_area.equals("Goregaon W")) {
                                                                    if (re > 8000) {
                                                                        uploadLeader();
                                                                    } else if (re <= 8000) {
                                                                        uploadData();
                                                                    }
                                                                } else if (prop_area.equals("Church Gate")) {
                                                                    if (re > 11500) {
                                                                        uploadLeader();
                                                                    } else if (re <= 11500) {
                                                                        uploadData();
                                                                    }
                                                                } else if (prop_area.equals("Colaba")) {
                                                                    if (re > 12000) {
                                                                        uploadLeader();
                                                                    } else if (re <= 12000) {
                                                                        uploadData();
                                                                    }
                                                                } else if (prop_area.equals("Dadar E") || prop_area.equals("Dadar W") || prop_area.equals("Prabhadevi")) {
                                                                    if (re > 10500) {
                                                                        uploadLeader();
                                                                    } else if (re <= 10500) {
                                                                        uploadData();
                                                                    }
                                                                } else if (prop_area.equals("Ghatkopar E") || prop_area.equals("Ghatkopar W") || prop_area.equals("Wadala E") || prop_area.equals("Wadala W")) {
                                                                    if (re > 7500) {
                                                                        uploadLeader();
                                                                    } else if (re <= 7500) {
                                                                        uploadData();
                                                                    }
                                                                } else if (prop_area.equals("Govandi E") || prop_area.equals("Govandi W") || prop_area.equals("Kopar Khairane") || prop_area.equals("Mire Road E") || prop_area.equals("Mire Road W") || prop_area.equals("Thane E") || prop_area.equals("Thane W")) {
                                                                    if (re > 4000) {
                                                                        uploadLeader();
                                                                    } else if (re <= 4000) {
                                                                        uploadData();
                                                                    }
                                                                } else if (prop_area.equals("Jogeshwari E") || prop_area.equals("Jogeshwari W") || prop_area.equals("Kalina") || prop_area.equals("Santa Cruz E") || prop_area.equals("Santa Cruz W") || prop_area.equals("Vile Parle E") || prop_area.equals("Vile Parle W")) {
                                                                    if (re > 6500) {
                                                                        uploadLeader();
                                                                    } else if (re <= 6500) {
                                                                        uploadData();
                                                                    }
                                                                } else if (prop_area.equals("Lower Parel") || prop_area.equals("Mahalaxmi") || prop_area.equals("Parel") || prop_area.equals("Powai")) {
                                                                    if (re > 10000) {
                                                                        uploadLeader();
                                                                    } else if (re <= 10000) {
                                                                        uploadData();
                                                                    }
                                                                } else if (prop_area.equals("Mahim E") || prop_area.equals("Mahim W") || prop_area.equals("Vikhroli E") || prop_area.equals("Vikhroli W")) {
                                                                    if (re > 8500) {
                                                                        uploadLeader();
                                                                    } else if (re <= 8500) {
                                                                        uploadData();
                                                                    }
                                                                } else if (prop_area.equals("Mulund E") || prop_area.equals("Mulund W")) {
                                                                    if (re > 4500) {
                                                                        uploadLeader();
                                                                    } else if (re <= 4500) {
                                                                        uploadData();
                                                                    }
                                                                } else if (prop_area.equals("Vashi")) {
                                                                    if (re > 3500) {
                                                                        uploadLeader();
                                                                    } else if (re <= 3500) {
                                                                        uploadData();
                                                                    }
                                                                }
                                                            }
                                                            else {
                                                                if (prop_area.equals("Airoli") || prop_area.equals("Ghansoli") || prop_area.equals("Panvel")) {
                                                                    if (re > 2500) {
                                                                        uploadLeader();
                                                                    } else if (re <= 2500) {
                                                                        uploadData();
                                                                    }
                                                                } else if (prop_area.equals("Amboli")) {
                                                                    if (re > 4000) {
                                                                        uploadLeader();
                                                                    } else if (re <= 4000) {
                                                                        uploadData();
                                                                    }
                                                                } else if (prop_area.equals("Andheri E") || prop_area.equals("Andheri W") || prop_area.equals("Goregaon E") || prop_area.equals("Khar W") || prop_area.equals("Khar E") || prop_area.equals("Kurla E") || prop_area.equals("Kurla W") || prop_area.equals("Malad W")) {
                                                                    if (re > 5500) {
                                                                        uploadLeader();
                                                                    } else if (re <= 5500) {
                                                                        uploadData();
                                                                    }
                                                                } else if (prop_area.equals("Bandra E") || prop_area.equals("Bandra W") || prop_area.equals("Fort") || prop_area.equals("Sion E") || prop_area.equals("Sion W") || prop_area.equals("Worli")) {
                                                                    if (re > 7500) {
                                                                        uploadLeader();
                                                                    } else if (re <= 7500) {
                                                                        uploadData();
                                                                    }
                                                                } else if (prop_area.equals("Bhandup E") || prop_area.equals("Bhandup W") || prop_area.equals("Kandivali E") || prop_area.equals("Kandivali W") || prop_area.equals("Malad E")) {
                                                                    if (re > 4500) {
                                                                        uploadLeader();
                                                                    } else if (re <= 4500) {
                                                                        uploadData();
                                                                    }
                                                                } else if (prop_area.equals("Boisar")) {
                                                                    if (re > 2500) {
                                                                        uploadLeader();
                                                                    } else if (re <= 2500) {
                                                                        uploadData();
                                                                    }
                                                                } else if (prop_area.equals("Borivali E") || prop_area.equals("Borivali W") || prop_area.equals("Kharegaon")) {
                                                                    if (re > 3500) {
                                                                        uploadLeader();
                                                                    } else if (re <= 3500) {
                                                                        uploadData();
                                                                    }
                                                                } else if (prop_area.equals("Chembur") || prop_area.equals("Goregaon W")) {
                                                                    if (re > 6500) {
                                                                        uploadLeader();
                                                                    } else if (re <= 6500) {
                                                                        uploadData();
                                                                    }
                                                                } else if (prop_area.equals("Church Gate")) {
                                                                    if (re > 10000) {
                                                                        uploadLeader();
                                                                    } else if (re <= 10000) {
                                                                        uploadData();
                                                                    }
                                                                } else if (prop_area.equals("Colaba")) {
                                                                    if (re > 10500) {
                                                                        uploadLeader();
                                                                    } else if (re <= 10500) {
                                                                        uploadData();
                                                                    }
                                                                } else if (prop_area.equals("Dadar E") || prop_area.equals("Dadar W") || prop_area.equals("Prabhadevi")) {
                                                                    if (re > 9000) {
                                                                        uploadLeader();
                                                                    } else if (re <= 9000) {
                                                                        uploadData();
                                                                    }
                                                                } else if (prop_area.equals("Ghatkopar E") || prop_area.equals("Ghatkopar W") || prop_area.equals("Wadala E") || prop_area.equals("Wadala W")) {
                                                                    if (re > 6000) {
                                                                        uploadLeader();
                                                                    } else if (re <= 6000) {
                                                                        uploadData();
                                                                    }
                                                                } else if (prop_area.equals("Govandi E") || prop_area.equals("Govandi W") || prop_area.equals("Kopar Khairane") || prop_area.equals("Mire Road E") || prop_area.equals("Mire Road W") || prop_area.equals("Thane E") || prop_area.equals("Thane W")) {
                                                                    if (re > 2500) {
                                                                        uploadLeader();
                                                                    } else if (re <= 2500) {
                                                                        uploadData();
                                                                    }
                                                                } else if (prop_area.equals("Jogeshwari E") || prop_area.equals("Jogeshwari W") || prop_area.equals("Kalina") || prop_area.equals("Santa Cruz E") || prop_area.equals("Santa Cruz W") || prop_area.equals("Vile Parle E") || prop_area.equals("Vile Parle W")) {
                                                                    if (re > 5000) {
                                                                        uploadLeader();
                                                                    } else if (re <= 5000) {
                                                                        uploadData();
                                                                    }
                                                                } else if (prop_area.equals("Lower Parel") || prop_area.equals("Mahalaxmi") || prop_area.equals("Parel") || prop_area.equals("Powai")) {
                                                                    if (re > 8500) {
                                                                        uploadLeader();
                                                                    } else if (re <= 8500) {
                                                                        uploadData();
                                                                    }
                                                                } else if (prop_area.equals("Mahim E") || prop_area.equals("Mahim W") || prop_area.equals("Vikhroli E") || prop_area.equals("Vikhroli W")) {
                                                                    if (re > 7000) {
                                                                        uploadLeader();
                                                                    } else if (re <= 7000) {
                                                                        uploadData();
                                                                    }
                                                                } else if (prop_area.equals("Mulund E") || prop_area.equals("Mulund W")) {
                                                                    if (re > 3000) {
                                                                        uploadLeader();
                                                                    } else if (re <= 3000) {
                                                                        uploadData();
                                                                    }
                                                                } else if (prop_area.equals("Vashi")) {
                                                                    if (re > 2500) {
                                                                        uploadLeader();
                                                                    } else if (re <= 2500) {
                                                                        uploadData();
                                                                    }
                                                                }
                                                            }
                                                        }
                                                        else {
                                                            uploadData();
                                                        }
                                                        Toast.makeText(Option2.this, "Upload Complete", Toast.LENGTH_LONG).show();
                                                        if(times.equals("One"))
                                                        {
                                                            startActivity(new Intent(Option2.this,MainActivity.class));
                                                        }
                                                        else if (times.equals("Two"))
                                                        {
                                                            Intent i = new Intent(Option2.this,Option2.class);
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
                                                            Intent i = new Intent(Option2.this,Option2.class);
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
                                                            Intent i = new Intent(Option2.this,Option2.class);
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
                                                            Intent i = new Intent(Option2.this,Option2.class);
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
                                                            Intent i = new Intent(Option2.this,Option2.class);
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
                                                            Intent i = new Intent(Option2.this,Option2.class);
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
                                                            Intent i = new Intent(Option2.this,Option2.class);
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
                                                                dialog1.dismiss();
                                                                Toast.makeText(Option2.this,e.getMessage(),Toast.LENGTH_SHORT).show();
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
                                                Toast.makeText(Option2.this,"Please select image",Toast.LENGTH_SHORT).show();
                                            }
                                        }
                                    });
                                    alertDialog.show();
                                }
                            }
                            else if (brokerage2.equals("0"))
                            {
                                if (p_tenant2.equals("Select preferred tenant"))
                                {
                                    Toast.makeText(Option2.this,"Please select tenant for property.",Toast.LENGTH_SHORT).show();
                                }
                                else if (spplynotes.equals("")){
                                    Toast.makeText(Option2.this,"Please enter supply notes",Toast.LENGTH_SHORT).show();
                                }
                                else {
                                    uri =Uri.parse(uristring);
                                    if (uri != null) {
                                        final ProgressDialog dialog1 = new ProgressDialog(Option2.this);
                                        dialog1.setTitle("Uploading Data");
                                        dialog1.show();
                                        StorageReference filePath = storageReference.child("PostImage").child(uri.getLastPathSegment());
                                        filePath.putFile(uri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                                            @Override
                                            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                                                dialog1.dismiss();
                                                if (vacanct2.equals("Single Occupancy"))
                                                {
                                                    if (furnishing2.equals("Fully Furnished")) {
                                                        if (prop_area.equals("Airoli") || prop_area.equals("Ghansoli") || prop_area.equals("Panvel")) {
                                                            if (re > 7500) {
                                                                uploadLeader();
                                                            } else if (re <= 7500) {
                                                                uploadData();
                                                            }
                                                        } else if (prop_area.equals("Amboli")) {
                                                            if (re > 13000) {
                                                                uploadLeader();
                                                            } else if (re <= 13000) {
                                                                uploadData();
                                                            }
                                                        } else if (prop_area.equals("Andheri E") || prop_area.equals("Andheri W") || prop_area.equals("Goregaon E") || prop_area.equals("Khar W") || prop_area.equals("Khar E") || prop_area.equals("Kurla E") || prop_area.equals("Kurla W") || prop_area.equals("Malad W")) {
                                                            if (re > 16000) {
                                                                uploadLeader();
                                                            } else if (re <= 16000) {
                                                                uploadData();
                                                            }
                                                        } else if (prop_area.equals("Bandra E") || prop_area.equals("Bandra W") || prop_area.equals("Fort") || prop_area.equals("Sion E") || prop_area.equals("Sion W") || prop_area.equals("Worli")) {
                                                            if (re > 20000) {
                                                                uploadLeader();
                                                            } else if (re <= 20000) {
                                                                uploadData();
                                                            }
                                                        } else if (prop_area.equals("Bhandup E") || prop_area.equals("Bhandup W") || prop_area.equals("Kandivali E") || prop_area.equals("Kandivali W") || prop_area.equals("Malad E")) {
                                                            if (re > 14000) {
                                                                uploadLeader();
                                                            } else if (re <= 14000) {
                                                                uploadData();
                                                            }
                                                        } else if (prop_area.equals("Boisar")) {
                                                            if (re > 9500) {
                                                                uploadLeader();
                                                            } else if (re <= 9500) {
                                                                uploadData();
                                                            }
                                                        } else if (prop_area.equals("Borivali E") || prop_area.equals("Borivali W") || prop_area.equals("Kharegaon")) {
                                                            if (re > 12000) {
                                                                uploadLeader();
                                                            } else if (re <= 12000) {
                                                                uploadData();
                                                            }
                                                        } else if (prop_area.equals("Chembur") || prop_area.equals("Goregaon W")) {
                                                            if (re > 18000) {
                                                                uploadLeader();
                                                            } else if (re <= 18000) {
                                                                uploadData();
                                                            }
                                                        } else if (prop_area.equals("Church Gate")) {
                                                            if (re > 25000) {
                                                                uploadLeader();
                                                            } else if (re <= 25000) {
                                                                uploadData();
                                                            }
                                                        } else if (prop_area.equals("Colaba")) {
                                                            if (re > 26000) {
                                                                uploadLeader();
                                                            } else if (re <= 26000) {
                                                                uploadData();
                                                            }
                                                        } else if (prop_area.equals("Dadar E") || prop_area.equals("Dadar W") || prop_area.equals("Prabhadevi")) {
                                                            if (re > 23000) {
                                                                uploadLeader();
                                                            } else if (re <= 23000) {
                                                                uploadData();
                                                            }
                                                        } else if (prop_area.equals("Ghatkopar E") || prop_area.equals("Ghatkopar W") || prop_area.equals("Wadala E") || prop_area.equals("Wadala W")) {
                                                            if (re > 17000) {
                                                                uploadLeader();
                                                            } else if (re <= 17000) {
                                                                uploadData();
                                                            }
                                                        } else if (prop_area.equals("Govandi E") || prop_area.equals("Govandi W") || prop_area.equals("Kopar Khairane") || prop_area.equals("Mire Road E") || prop_area.equals("Mire Road W") || prop_area.equals("Thane E") || prop_area.equals("Thane W")) {
                                                            if (re > 10000) {
                                                                uploadLeader();
                                                            } else if (re <= 10000) {
                                                                uploadData();
                                                            }
                                                        } else if (prop_area.equals("Jogeshwari E") || prop_area.equals("Jogeshwari W") || prop_area.equals("Kalina") || prop_area.equals("Santa Cruz E") || prop_area.equals("Santa Cruz W") || prop_area.equals("Vile Parle E") || prop_area.equals("Vile Parle W")) {
                                                            if (re > 15000) {
                                                                uploadLeader();
                                                            } else if (re <= 15000) {
                                                                uploadData();
                                                            }
                                                        } else if (prop_area.equals("Lower Parel") || prop_area.equals("Mahalaxmi") || prop_area.equals("Parel") || prop_area.equals("Powai")) {
                                                            if (re > 22000) {
                                                                uploadLeader();
                                                            } else if (re <= 22000) {
                                                                uploadData();
                                                            }
                                                        } else if (prop_area.equals("Mahim E") || prop_area.equals("Mahim W") || prop_area.equals("Vikhroli E") || prop_area.equals("Vikhroli W")) {
                                                            if (re > 19000) {
                                                                uploadLeader();
                                                            } else if (re <= 19000) {
                                                                uploadData();
                                                            }
                                                        } else if (prop_area.equals("Mulund E") || prop_area.equals("Mulund W")) {
                                                            if (re > 11000) {
                                                                uploadLeader();
                                                            } else if (re <= 11000) {
                                                                uploadData();
                                                            }
                                                        } else if (prop_area.equals("Vashi")) {
                                                            if (re > 9000) {
                                                                uploadLeader();
                                                            } else if (re <= 9000) {
                                                                uploadData();
                                                            }
                                                        }
                                                    }
                                                    else {
                                                        if (prop_area.equals("Airoli") || prop_area.equals("Ghansoli") || prop_area.equals("Panvel")) {
                                                            if (re > 3500) {
                                                                uploadLeader();
                                                            } else if (re <= 3500) {
                                                                uploadData();
                                                            }
                                                        } else if (prop_area.equals("Amboli")) {
                                                            if (re > 9000) {
                                                                uploadLeader();
                                                            } else if (re <= 9000) {
                                                                uploadData();
                                                            }
                                                        } else if (prop_area.equals("Andheri E") || prop_area.equals("Andheri W") || prop_area.equals("Goregaon E") || prop_area.equals("Khar W") || prop_area.equals("Khar E") || prop_area.equals("Kurla E") || prop_area.equals("Kurla W") || prop_area.equals("Malad W")) {
                                                            if (re > 12000) {
                                                                uploadLeader();
                                                            } else if (re <= 12000) {
                                                                uploadData();
                                                            }
                                                        } else if (prop_area.equals("Bandra E") || prop_area.equals("Bandra W") || prop_area.equals("Fort") || prop_area.equals("Sion E") || prop_area.equals("Sion W") || prop_area.equals("Worli")) {
                                                            if (re > 16000) {
                                                                uploadLeader();
                                                            } else if (re <= 16000) {
                                                                uploadData();
                                                            }
                                                        } else if (prop_area.equals("Bhandup E") || prop_area.equals("Bhandup W") || prop_area.equals("Kandivali E") || prop_area.equals("Kandivali W") || prop_area.equals("Malad E")) {
                                                            if (re > 10000) {
                                                                uploadLeader();
                                                            } else if (re <= 10000) {
                                                                uploadData();
                                                            }
                                                        } else if (prop_area.equals("Boisar")) {
                                                            if (re > 5500) {
                                                                uploadLeader();
                                                            } else if (re <= 5500) {
                                                                uploadData();
                                                            }
                                                        } else if (prop_area.equals("Borivali E") || prop_area.equals("Borivali W") || prop_area.equals("Kharegaon")) {
                                                            if (re > 8000) {
                                                                uploadLeader();
                                                            } else if (re <= 8000) {
                                                                uploadData();
                                                            }
                                                        } else if (prop_area.equals("Chembur") || prop_area.equals("Goregaon W")) {
                                                            if (re > 14000) {
                                                                uploadLeader();
                                                            } else if (re <= 14000) {
                                                                uploadData();
                                                            }
                                                        } else if (prop_area.equals("Church Gate")) {
                                                            if (re > 21000) {
                                                                uploadLeader();
                                                            } else if (re <= 21000) {
                                                                uploadData();
                                                            }
                                                        } else if (prop_area.equals("Colaba")) {
                                                            if (re > 22000) {
                                                                uploadLeader();
                                                            } else if (re <= 22000) {
                                                                uploadData();
                                                            }
                                                        } else if (prop_area.equals("Dadar E") || prop_area.equals("Dadar W") || prop_area.equals("Prabhadevi")) {
                                                            if (re > 19000) {
                                                                uploadLeader();
                                                            } else if (re <= 19000) {
                                                                uploadData();
                                                            }
                                                        } else if (prop_area.equals("Ghatkopar E") || prop_area.equals("Ghatkopar W") || prop_area.equals("Wadala E") || prop_area.equals("Wadala W")) {
                                                            if (re > 13000) {
                                                                uploadLeader();
                                                            } else if (re <= 13000) {
                                                                uploadData();
                                                            }
                                                        } else if (prop_area.equals("Govandi E") || prop_area.equals("Govandi W") || prop_area.equals("Kopar Khairane") || prop_area.equals("Mire Road E") || prop_area.equals("Mire Road W") || prop_area.equals("Thane E") || prop_area.equals("Thane W")) {
                                                            if (re > 6000) {
                                                                uploadLeader();
                                                            } else if (re <= 6000) {
                                                                uploadData();
                                                            }
                                                        } else if (prop_area.equals("Jogeshwari E") || prop_area.equals("Jogeshwari W") || prop_area.equals("Kalina") || prop_area.equals("Santa Cruz E") || prop_area.equals("Santa Cruz W") || prop_area.equals("Vile Parle E") || prop_area.equals("Vile Parle W")) {
                                                            if (re > 11000) {
                                                                uploadLeader();
                                                            } else if (re <= 11000) {
                                                                uploadData();
                                                            }
                                                        } else if (prop_area.equals("Lower Parel") || prop_area.equals("Mahalaxmi") || prop_area.equals("Parel") || prop_area.equals("Powai")) {
                                                            if (re > 18000) {
                                                                uploadLeader();
                                                            } else if (re <= 18000) {
                                                                uploadData();
                                                            }
                                                        } else if (prop_area.equals("Mahim E") || prop_area.equals("Mahim W") || prop_area.equals("Vikhroli E") || prop_area.equals("Vikhroli W")) {
                                                            if (re > 15000) {
                                                                uploadLeader();
                                                            } else if (re <= 15000) {
                                                                uploadData();
                                                            }
                                                        } else if (prop_area.equals("Mulund E") || prop_area.equals("Mulund W")) {
                                                            if (re > 7000) {
                                                                uploadLeader();
                                                            } else if (re <= 7000) {
                                                                uploadData();
                                                            }
                                                        } else if (prop_area.equals("Vashi")) {
                                                            if (re > 5000) {
                                                                uploadLeader();
                                                            } else if (re <= 5000) {
                                                                uploadData();
                                                            }
                                                        }
                                                    }
                                                }
                                                else if (vacanct2.equals("Double Occupancy")) {
                                                    if (furnishing2.equals("Fully Furnished")) {
                                                        if (prop_area.equals("Airoli") || prop_area.equals("Ghansoli") || prop_area.equals("Panvel")) {
                                                            if (re > 3000) {
                                                                uploadLeader();
                                                            } else if (re <= 3000) {
                                                                uploadData();
                                                            }
                                                        } else if (prop_area.equals("Amboli")) {
                                                            if (re > 5500) {
                                                                uploadLeader();
                                                            } else if (re <= 5500) {
                                                                uploadData();
                                                            }
                                                        } else if (prop_area.equals("Andheri E") || prop_area.equals("Andheri W") || prop_area.equals("Goregaon E") || prop_area.equals("Khar W") || prop_area.equals("Khar E") || prop_area.equals("Kurla E") || prop_area.equals("Kurla W") || prop_area.equals("Malad W")) {
                                                            if (re > 7000) {
                                                                uploadLeader();
                                                            } else if (re <= 7000) {
                                                                uploadData();
                                                            }
                                                        } else if (prop_area.equals("Bandra E") || prop_area.equals("Bandra W") || prop_area.equals("Fort") || prop_area.equals("Sion E") || prop_area.equals("Sion W") || prop_area.equals("Worli")) {
                                                            if (re > 9000) {
                                                                uploadLeader();
                                                            } else if (re <= 9000) {
                                                                uploadData();
                                                            }
                                                        } else if (prop_area.equals("Bhandup E") || prop_area.equals("Bhandup W") || prop_area.equals("Kandivali E") || prop_area.equals("Kandivali W") || prop_area.equals("Malad E")) {
                                                            if (re > 6000) {
                                                                uploadLeader();
                                                            } else if (re <= 6000) {
                                                                uploadData();
                                                            }
                                                        } else if (prop_area.equals("Boisar")) {
                                                            if (re > 3500) {
                                                                uploadLeader();
                                                            } else if (re <= 3500) {
                                                                uploadData();
                                                            }
                                                        } else if (prop_area.equals("Borivali E") || prop_area.equals("Borivali W") || prop_area.equals("Kharegaon")) {
                                                            if (re > 5000) {
                                                                uploadLeader();
                                                            } else if (re <= 5000) {
                                                                uploadData();
                                                            }
                                                        } else if (prop_area.equals("Chembur") || prop_area.equals("Goregaon W")) {
                                                            if (re > 8000) {
                                                                uploadLeader();
                                                            } else if (re <= 8000) {
                                                                uploadData();
                                                            }
                                                        } else if (prop_area.equals("Church Gate")) {
                                                            if (re > 11500) {
                                                                uploadLeader();
                                                            } else if (re <= 11500) {
                                                                uploadData();
                                                            }
                                                        } else if (prop_area.equals("Colaba")) {
                                                            if (re > 12000) {
                                                                uploadLeader();
                                                            } else if (re <= 12000) {
                                                                uploadData();
                                                            }
                                                        } else if (prop_area.equals("Dadar E") || prop_area.equals("Dadar W") || prop_area.equals("Prabhadevi")) {
                                                            if (re > 10500) {
                                                                uploadLeader();
                                                            } else if (re <= 10500) {
                                                                uploadData();
                                                            }
                                                        } else if (prop_area.equals("Ghatkopar E") || prop_area.equals("Ghatkopar W") || prop_area.equals("Wadala E") || prop_area.equals("Wadala W")) {
                                                            if (re > 7500) {
                                                                uploadLeader();
                                                            } else if (re <= 7500) {
                                                                uploadData();
                                                            }
                                                        } else if (prop_area.equals("Govandi E") || prop_area.equals("Govandi W") || prop_area.equals("Kopar Khairane") || prop_area.equals("Mire Road E") || prop_area.equals("Mire Road W") || prop_area.equals("Thane E") || prop_area.equals("Thane W")) {
                                                            if (re > 4000) {
                                                                uploadLeader();
                                                            } else if (re <= 4000) {
                                                                uploadData();
                                                            }
                                                        } else if (prop_area.equals("Jogeshwari E") || prop_area.equals("Jogeshwari W") || prop_area.equals("Kalina") || prop_area.equals("Santa Cruz E") || prop_area.equals("Santa Cruz W") || prop_area.equals("Vile Parle E") || prop_area.equals("Vile Parle W")) {
                                                            if (re > 6500) {
                                                                uploadLeader();
                                                            } else if (re <= 6500) {
                                                                uploadData();
                                                            }
                                                        } else if (prop_area.equals("Lower Parel") || prop_area.equals("Mahalaxmi") || prop_area.equals("Parel") || prop_area.equals("Powai")) {
                                                            if (re > 10000) {
                                                                uploadLeader();
                                                            } else if (re <= 10000) {
                                                                uploadData();
                                                            }
                                                        } else if (prop_area.equals("Mahim E") || prop_area.equals("Mahim W") || prop_area.equals("Vikhroli E") || prop_area.equals("Vikhroli W")) {
                                                            if (re > 8500) {
                                                                uploadLeader();
                                                            } else if (re <= 8500) {
                                                                uploadData();
                                                            }
                                                        } else if (prop_area.equals("Mulund E") || prop_area.equals("Mulund W")) {
                                                            if (re > 4500) {
                                                                uploadLeader();
                                                            } else if (re <= 4500) {
                                                                uploadData();
                                                            }
                                                        } else if (prop_area.equals("Vashi")) {
                                                            if (re > 3500) {
                                                                uploadLeader();
                                                            } else if (re <= 3500) {
                                                                uploadData();
                                                            }
                                                        }
                                                    }
                                                    else {
                                                        if (prop_area.equals("Airoli") || prop_area.equals("Ghansoli") || prop_area.equals("Panvel")) {
                                                            if (re > 2500) {
                                                                uploadLeader();
                                                            } else if (re <= 2500) {
                                                                uploadData();
                                                            }
                                                        } else if (prop_area.equals("Amboli")) {
                                                            if (re > 4000) {
                                                                uploadLeader();
                                                            } else if (re <= 4000) {
                                                                uploadData();
                                                            }
                                                        } else if (prop_area.equals("Andheri E") || prop_area.equals("Andheri W") || prop_area.equals("Goregaon E") || prop_area.equals("Khar W") || prop_area.equals("Khar E") || prop_area.equals("Kurla E") || prop_area.equals("Kurla W") || prop_area.equals("Malad W")) {
                                                            if (re > 5500) {
                                                                uploadLeader();
                                                            } else if (re <= 5500) {
                                                                uploadData();
                                                            }
                                                        } else if (prop_area.equals("Bandra E") || prop_area.equals("Bandra W") || prop_area.equals("Fort") || prop_area.equals("Sion E") || prop_area.equals("Sion W") || prop_area.equals("Worli")) {
                                                            if (re > 7500) {
                                                                uploadLeader();
                                                            } else if (re <= 7500) {
                                                                uploadData();
                                                            }
                                                        } else if (prop_area.equals("Bhandup E") || prop_area.equals("Bhandup W") || prop_area.equals("Kandivali E") || prop_area.equals("Kandivali W") || prop_area.equals("Malad E")) {
                                                            if (re > 4500) {
                                                                uploadLeader();
                                                            } else if (re <= 4500) {
                                                                uploadData();
                                                            }
                                                        } else if (prop_area.equals("Boisar")) {
                                                            if (re > 2500) {
                                                                uploadLeader();
                                                            } else if (re <= 2500) {
                                                                uploadData();
                                                            }
                                                        } else if (prop_area.equals("Borivali E") || prop_area.equals("Borivali W") || prop_area.equals("Kharegaon")) {
                                                            if (re > 3500) {
                                                                uploadLeader();
                                                            } else if (re <= 3500) {
                                                                uploadData();
                                                            }
                                                        } else if (prop_area.equals("Chembur") || prop_area.equals("Goregaon W")) {
                                                            if (re > 6500) {
                                                                uploadLeader();
                                                            } else if (re <= 6500) {
                                                                uploadData();
                                                            }
                                                        } else if (prop_area.equals("Church Gate")) {
                                                            if (re > 10000) {
                                                                uploadLeader();
                                                            } else if (re <= 10000) {
                                                                uploadData();
                                                            }
                                                        } else if (prop_area.equals("Colaba")) {
                                                            if (re > 10500) {
                                                                uploadLeader();
                                                            } else if (re <= 10500) {
                                                                uploadData();
                                                            }
                                                        } else if (prop_area.equals("Dadar E") || prop_area.equals("Dadar W") || prop_area.equals("Prabhadevi")) {
                                                            if (re > 9000) {
                                                                uploadLeader();
                                                            } else if (re <= 9000) {
                                                                uploadData();
                                                            }
                                                        } else if (prop_area.equals("Ghatkopar E") || prop_area.equals("Ghatkopar W") || prop_area.equals("Wadala E") || prop_area.equals("Wadala W")) {
                                                            if (re > 6000) {
                                                                uploadLeader();
                                                            } else if (re <= 6000) {
                                                                uploadData();
                                                            }
                                                        } else if (prop_area.equals("Govandi E") || prop_area.equals("Govandi W") || prop_area.equals("Kopar Khairane") || prop_area.equals("Mire Road E") || prop_area.equals("Mire Road W") || prop_area.equals("Thane E") || prop_area.equals("Thane W")) {
                                                            if (re > 2500) {
                                                                uploadLeader();
                                                            } else if (re <= 2500) {
                                                                uploadData();
                                                            }
                                                        } else if (prop_area.equals("Jogeshwari E") || prop_area.equals("Jogeshwari W") || prop_area.equals("Kalina") || prop_area.equals("Santa Cruz E") || prop_area.equals("Santa Cruz W") || prop_area.equals("Vile Parle E") || prop_area.equals("Vile Parle W")) {
                                                            if (re > 5000) {
                                                                uploadLeader();
                                                            } else if (re <= 5000) {
                                                                uploadData();
                                                            }
                                                        } else if (prop_area.equals("Lower Parel") || prop_area.equals("Mahalaxmi") || prop_area.equals("Parel") || prop_area.equals("Powai")) {
                                                            if (re > 8500) {
                                                                uploadLeader();
                                                            } else if (re <= 8500) {
                                                                uploadData();
                                                            }
                                                        } else if (prop_area.equals("Mahim E") || prop_area.equals("Mahim W") || prop_area.equals("Vikhroli E") || prop_area.equals("Vikhroli W")) {
                                                            if (re > 7000) {
                                                                uploadLeader();
                                                            } else if (re <= 7000) {
                                                                uploadData();
                                                            }
                                                        } else if (prop_area.equals("Mulund E") || prop_area.equals("Mulund W")) {
                                                            if (re > 3000) {
                                                                uploadLeader();
                                                            } else if (re <= 3000) {
                                                                uploadData();
                                                            }
                                                        } else if (prop_area.equals("Vashi")) {
                                                            if (re > 2500) {
                                                                uploadLeader();
                                                            } else if (re <= 2500) {
                                                                uploadData();
                                                            }
                                                        }
                                                    }
                                                }
                                                else {
                                                    uploadData();
                                                }
                                                Toast.makeText(Option2.this, "Upload Complete", Toast.LENGTH_LONG).show();
                                                if(times.equals("One"))
                                                {
                                                    startActivity(new Intent(Option2.this,MainActivity.class));
                                                }
                                                else if (times.equals("Two"))
                                                {
                                                    Intent i = new Intent(Option2.this,Option2.class);
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
                                                    Intent i = new Intent(Option2.this,Option2.class);
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
                                                    Intent i = new Intent(Option2.this,Option2.class);
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
                                                    Intent i = new Intent(Option2.this,Option2.class);
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
                                                    Intent i = new Intent(Option2.this,Option2.class);
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
                                                    Intent i = new Intent(Option2.this,Option2.class);
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
                                                    Intent i = new Intent(Option2.this,Option2.class);
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
                                                        dialog1.dismiss();
                                                        Toast.makeText(Option2.this,e.getMessage(),Toast.LENGTH_SHORT).show();
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
                                        Toast.makeText(Option2.this,"Please select image",Toast.LENGTH_SHORT).show();
                                    }
                                }
                            }
                        }
                    }
                }
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
            newPost1.child("size").setValue(size2);
            newPost1.child("fur").setValue(furnishing2);
            newPost1.child("vac").setValue(vacanct2);
            newPost1.child("ava_vac").setValue(vacancy2);
            newPost1.child("rent").setValue(rent2);
            newPost1.child("date").setValue(date2);
            newPost1.child("deposit").setValue(deposit2);
            newPost1.child("brokerage").setValue(brokerage2);
            newPost1.child("ten").setValue(p_tenant2);
            newPost1.child("sply").setValue(spplynotes);
            newPost1.child("desc").setValue(cust_name);
            newPost1.child("status").setValue("Uploaded");
            newPost1.child("image").setValue(downloadurl.toString());
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
            newPost.child("size").setValue(size2);
            newPost.child("fur").setValue(furnishing2);
            newPost.child("vac").setValue(vacanct2);
            newPost.child("ava_vac").setValue(vacancy2);
            newPost.child("rent").setValue(rent2);
            newPost.child("date").setValue(date2);
            newPost.child("deposit").setValue(deposit2);
            newPost.child("brokerage").setValue(brokerage2);
            newPost.child("ten").setValue(p_tenant2);
            newPost.child("sply").setValue(spplynotes);
            newPost.child("desc").setValue(cust_name);
            newPost.child("status").setValue("Uploaded");
            newPost.child("image").setValue(downloadurl.toString());
    }
}