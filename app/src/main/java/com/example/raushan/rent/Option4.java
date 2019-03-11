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

import java.util.ArrayList;
import java.util.Calendar;

public class Option4 extends AppCompatActivity {
    Button btn41,btn42;
    EditText ed41,ed42,ed43,ed44,ed45;
    TextView textView41,textView42;
    Spinner sp41,sp42;
    Calendar podate4;
    int day4,month4,year4;
    int vac4,re4,dep4,brok4;
    String[] listItems4;
    String sp1,sp2,sp3,sp4,sp5,sp6,sp7,sp8,sp9;
    boolean[] checkedItems4;
    ArrayList<Integer> mUserItems4 = new ArrayList<>();

    private StorageReference storageReference;
    private FirebaseDatabase database;
    private Uri uri = null;
    private DatabaseReference databaseReference;
    ProgressBar progressBar2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_option4);
        btn41 = (Button)findViewById(R.id.facilities4);
        btn42 = (Button)findViewById(R.id.opt4smt);
        ed41 = (EditText)findViewById(R.id.rate42);
        ed42 = (EditText)findViewById(R.id.rate4);
        ed43 = (EditText)findViewById(R.id.deposit4);
        ed44 = (EditText)findViewById(R.id.brokerage4);
        ed45 = (EditText)findViewById(R.id.supplynotes4);
        sp41 = (Spinner)findViewById(R.id.size4);
        sp42 = (Spinner)findViewById(R.id.pref4);
        textView41 = (TextView)findViewById(R.id.textView54);
        textView42 = (TextView)findViewById(R.id.available4);
        progressBar2 = (ProgressBar)findViewById(R.id.op2progressbar);
        storageReference = FirebaseStorage.getInstance().getReference();
        databaseReference = database.getInstance().getReference().child("image");
        final String proptype = getIntent().getStringExtra("proptype2");
        final String cust_name = getIntent().getStringExtra("cust_name2");
        final String prop_name = getIntent().getStringExtra("prop_name2");
        final String cust_num = getIntent().getStringExtra("cust_num2");
        final String altcust_num = getIntent().getStringExtra("alt_num2");
        final String cust_em_site = getIntent().getStringExtra("cust_em_site2");
        final String prop_area = getIntent().getStringExtra("prop_area2");
        final String prop_variant = getIntent().getStringExtra("prop_variant2");
        final String prop_locality = getIntent().getStringExtra("prop_locality2");
        final String times = getIntent().getStringExtra("times2");
        final String uristring = getIntent().getStringExtra("uri1");
        sp41.setFocusable(true);
        sp41.setFocusableInTouchMode(true);
        sp41.requestFocus();
        podate4 = Calendar.getInstance();
        day4 = podate4.get(Calendar.DAY_OF_MONTH);
        month4 = podate4.get(Calendar.MONTH);
        year4 = podate4.get(Calendar.YEAR);
        month4 = month4+1;
        listItems4 = getResources().getStringArray(R.array.facilities_list);
        checkedItems4 = new boolean[listItems4.length];
        textView42.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatePickerDialog datePickerDialog = new DatePickerDialog(Option4.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                        monthOfYear = monthOfYear+1;
                        textView42.setText(dayOfMonth+"/"+monthOfYear+"/"+year);
                    }
                },year4, month4, day4);
                datePickerDialog.show();
            }
        });

        btn41.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder mBuilder = new AlertDialog.Builder(Option4.this);
                mBuilder.setTitle(R.string.dialog_title);
                mBuilder.setMultiChoiceItems(listItems4, checkedItems4, new DialogInterface.OnMultiChoiceClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int position, boolean isChecked) {
                        if(isChecked){
                            if(!mUserItems4.contains(position)){
                                mUserItems4.add(position);
                            }
                            else {
                                mUserItems4.remove(position);
                            }
                        }
                    }
                });
                mBuilder.setCancelable(false);
                mBuilder.setPositiveButton(R.string.ok_label, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int which) {
                        String item = "";
                        for (int i = 0;i<mUserItems4.size();i++)
                        {
                            item =item + listItems4[mUserItems4.get(i)];
                            if(i!=mUserItems4.size() -1){
                                item= item+", ";
                            }
                        }
                        textView41.setText(item);
                    }
                });
                mBuilder.setNegativeButton(R.string.dismiss_label, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
                mBuilder.setNeutralButton(R.string.clear_label, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        for(int i = 0;i<checkedItems4.length;i++)
                        {
                            checkedItems4[i]=false;
                            mUserItems4.clear();
                            textView41.setText("");
                        }
                    }
                });
                AlertDialog mDialog = mBuilder.create();
                mBuilder.show();
            }
        });
        btn42.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sp1 = sp41.getSelectedItem().toString();
                sp2 = textView41.getText().toString();
                sp3 = ed41.getText().toString();
                sp4 = ed42.getText().toString();
                sp5 = ed43.getText().toString();
                sp6 = ed44.getText().toString();
                sp7 = textView42.getText().toString();
                sp8 = sp42.getSelectedItem().toString();
                sp9 = ed45.getText().toString();
                if (sp1.equals("Select available vacancy"))
                {
                    Toast.makeText(Option4.this,"Please select vacancy for property.",Toast.LENGTH_SHORT).show();
                }
                else if (sp2.equals("Please select by clicking on button"))
                {
                    Toast.makeText(Option4.this,"Please select facilities for property.",Toast.LENGTH_SHORT).show();
                }
                else if (sp3.equals(""))
                {
                    Toast.makeText(Option4.this,"Please enter vacancies for property.",Toast.LENGTH_SHORT).show();
                }
                else if (!sp3.equals(""))
                {
                    vac4 = Integer.parseInt(sp3);
                    if (vac4<=0)
                    {
                        Toast.makeText(Option4.this,"Please enter valid vacancies for property.",Toast.LENGTH_SHORT).show();
                    }
                    else if (sp4.equals(""))
                    {
                        Toast.makeText(Option4.this,"Please enter rent for property.",Toast.LENGTH_SHORT).show();
                    }
                    else if (!sp4.equals(""))
                    {
                        re4 = Integer.parseInt(sp4);
                        if (re4<=1) {
                            Toast.makeText(Option4.this, "Please enter valid rent for property.", Toast.LENGTH_SHORT).show();
                        }
                        else if (sp5.equals(""))
                        {
                            Toast.makeText(Option4.this,"Please enter deposit for property.",Toast.LENGTH_SHORT).show();
                        }
                        else if (!sp5.equals(""))
                        {
                            dep4 =Integer.parseInt(sp5);
                            if (dep4<=1) {
                                Toast.makeText(Option4.this, "Please enter valid deposit for property.", Toast.LENGTH_SHORT).show();
                            }
                            else if (sp6.equals(""))
                            {
                                Toast.makeText(Option4.this, "Please enter brokerage for property.", Toast.LENGTH_SHORT).show();
                            }
                            else if (!sp6.equals("0"))
                            {
                                brok4 = Integer.parseInt(sp6);
                                if (brok4<=0) {
                                    Toast.makeText(Option4.this, "Please enter valid brokerage for property.", Toast.LENGTH_SHORT).show();
                                }
                                else if (sp7.equals("Select available date"))
                                {
                                    Toast.makeText(Option4.this,"Please select date for property.",Toast.LENGTH_SHORT).show();
                                }
                                else if (sp8.equals("Select preferred tenant"))
                                {
                                    Toast.makeText(Option4.this,"Please select preferred tenant for property.",Toast.LENGTH_SHORT).show();
                                }
                                else if (sp9.equals("")){
                                    Toast.makeText(Option4.this,"Please enter supply notes",Toast.LENGTH_SHORT).show();
                                }
                                else {
                                    AlertDialog alertDialog = new AlertDialog.Builder(Option4.this).create();
                                    alertDialog.setTitle("Brokerage Alert");
                                    alertDialog.setMessage("Property involves brokerage!");
                                    alertDialog.setButton("OK", new DialogInterface.OnClickListener(){
                                        public void onClick(DialogInterface dialog, int which) {
                                            uri =Uri.parse(uristring);
                                            if (uri != null) {
                                                final ProgressDialog dialog1 = new ProgressDialog(Option4.this);
                                                dialog1.setTitle("Uploading Data");
                                                dialog1.show();
                                                StorageReference filePath = storageReference.child("PostImage").child(uri.getLastPathSegment());
                                                filePath.putFile(uri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                                                    @Override
                                                    public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                                                        dialog1.dismiss();
                                                        final Uri downloadurl = taskSnapshot.getDownloadUrl();
                                                        final DatabaseReference newPost = databaseReference.push();
                                                        newPost.child("title").setValue(prop_name);
                                                        newPost.child("proptype").setValue(proptype);
                                                        newPost.child("custnum").setValue(cust_num);
                                                        newPost.child("altnum").setValue(altcust_num);
                                                        newPost.child("email").setValue(cust_em_site);
                                                        newPost.child("area").setValue(prop_area);
                                                        newPost.child("variant").setValue(prop_variant);
                                                        newPost.child("locality").setValue(prop_locality);
                                                        newPost.child("vacn").setValue(sp1);
                                                        newPost.child("facilities").setValue(sp2);
                                                        newPost.child("vac").setValue(sp3);
                                                        newPost.child("rent").setValue(sp4);
                                                        newPost.child("deposit").setValue(sp5);
                                                        newPost.child("brokerage").setValue(sp6);
                                                        newPost.child("date").setValue(sp7);
                                                        newPost.child("ten").setValue(sp8);
                                                        newPost.child("sply").setValue(sp9);
                                                        newPost.child("desc").setValue(cust_name);
                                                        newPost.child("status").setValue("Uploaded");
                                                        newPost.child("image").setValue(downloadurl.toString());
                                                        Toast.makeText(Option4.this, "Upload Complete", Toast.LENGTH_LONG).show();
                                                        if(times.equals("One"))
                                                        {
                                                            startActivity(new Intent(Option4.this,MainActivity.class));
                                                        }
                                                        else if (times.equals("Two"))
                                                        {
                                                            Intent i = new Intent(Option4.this,Option4.class);
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
                                                            Intent i = new Intent(Option4.this,Option4.class);
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
                                                            Intent i = new Intent(Option4.this,Option4.class);
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
                                                            Intent i = new Intent(Option4.this,Option4.class);
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
                                                            Intent i = new Intent(Option4.this,Option4.class);
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
                                                            Intent i = new Intent(Option4.this,Option4.class);
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
                                                            Intent i = new Intent(Option4.this,Option4.class);
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
                                                                Toast.makeText(Option4.this,e.getMessage(),Toast.LENGTH_SHORT).show();
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
                                                Toast.makeText(Option4.this,"Please select image",Toast.LENGTH_SHORT).show();
                                            }
                                        }
                                    });
                                    alertDialog.show();
                                }
                            }
                            else if (sp6.equals("0"))
                            {
                                if (sp7.equals("  Available From"))
                                {
                                    Toast.makeText(Option4.this,"Please select date for property.",Toast.LENGTH_SHORT).show();
                                }
                                else if (sp8.equals("Select preferred tenant"))
                                {
                                    Toast.makeText(Option4.this,"Please select preferred tenant for property.",Toast.LENGTH_SHORT).show();
                                }
                                else if (sp9.equals("")){
                                    Toast.makeText(Option4.this,"Please enter supply notes",Toast.LENGTH_SHORT).show();
                                }
                                else {
                                    uri =Uri.parse(uristring);
                                    if (uri != null) {
                                        final ProgressDialog dialog1 = new ProgressDialog(Option4.this);
                                        dialog1.setTitle("Uploading Data");
                                        dialog1.show();
                                        StorageReference filePath = storageReference.child("PostImage").child(uri.getLastPathSegment());
                                        filePath.putFile(uri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                                            @Override
                                            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                                                dialog1.dismiss();
                                                final Uri downloadurl = taskSnapshot.getDownloadUrl();
                                                final DatabaseReference newPost = databaseReference.push();
                                                newPost.child("title").setValue(prop_name);
                                                newPost.child("proptype").setValue(proptype);
                                                newPost.child("custnum").setValue(cust_num);
                                                newPost.child("altnum").setValue(altcust_num);
                                                newPost.child("email").setValue(cust_em_site);
                                                newPost.child("area").setValue(prop_area);
                                                newPost.child("variant").setValue(prop_variant);
                                                newPost.child("locality").setValue(prop_locality);
                                                newPost.child("vacn").setValue(sp1);
                                                newPost.child("facilities").setValue(sp2);
                                                newPost.child("vac").setValue(sp3);
                                                newPost.child("rent").setValue(sp4);
                                                newPost.child("deposit").setValue(sp5);
                                                newPost.child("brokerage").setValue(sp6);
                                                newPost.child("date").setValue(sp7);
                                                newPost.child("ten").setValue(sp8);
                                                newPost.child("sply").setValue(sp9);
                                                newPost.child("desc").setValue(cust_name);
                                                newPost.child("status").setValue("Uploaded");
                                                newPost.child("image").setValue(downloadurl.toString());
                                                Toast.makeText(Option4.this, "Upload Complete", Toast.LENGTH_LONG).show();
                                                startActivity(new Intent(Option4.this,MainActivity.class));
                                            }
                                        })
                                                .addOnFailureListener(new OnFailureListener() {
                                                    @Override
                                                    public void onFailure(@NonNull Exception e) {
                                                        dialog1.dismiss();
                                                        Toast.makeText(Option4.this,e.getMessage(),Toast.LENGTH_SHORT).show();
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
                                        Toast.makeText(Option4.this,"Please select image",Toast.LENGTH_SHORT).show();
                                    }
                                }
                            }
                        }
                    }
                }
            }
        });
    }
}