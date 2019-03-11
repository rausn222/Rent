package com.example.raushan.rent;

import android.app.ProgressDialog;
import android.content.ContentResolver;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.webkit.MimeTypeMap;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.IOException;

public class Main2Activity extends AppCompatActivity {
    private static final int GALLERY_REQUEST = 2;
    private Uri uri = null;
    private ImageButton imageButton;
    private Button sbmt;
    private StorageReference storageReference;
    private FirebaseDatabase database;
    private DatabaseReference databaseReference, databaseReference1;
    ProgressBar progressBar;
    String prop;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        sbmt = (Button)findViewById(R.id.button2);
        progressBar=(ProgressBar)findViewById(R.id.progressbar);
        storageReference = FirebaseStorage.getInstance().getReference();
        databaseReference = database.getInstance().getReference().child("image");
        databaseReference1 = database.getInstance().getReference().child("teamleader");
        final String proptype = getIntent().getStringExtra("proptype2");
        final String cust_name = getIntent().getStringExtra("cust_name2");
        final String prop_name = getIntent().getStringExtra("prop_name2");
        final String cust_num = getIntent().getStringExtra("cust_num2");
        final String altcust_num = getIntent().getStringExtra("alt_num2");
        final String cust_em_site = getIntent().getStringExtra("cust_em_site2");
        final String prop_area = getIntent().getStringExtra("prop_area2");
        final String prop_variant = getIntent().getStringExtra("prop_variant2");
        final String prop_locality = getIntent().getStringExtra("prop_locality2");
        final String prop_size = getIntent().getStringExtra("prop_size2");
        final String prop_fur = getIntent().getStringExtra("prop_fur2");
        final String prop_rent = getIntent().getStringExtra("prop_rent2");
        final String prop_date = getIntent().getStringExtra("prop_date2");
        final String prop_depo = getIntent().getStringExtra("prop_depo2");
        final String prop_brok = getIntent().getStringExtra("prop_brok2");
        final String prop_ten = getIntent().getStringExtra("prop_ten2");
        final String prop_sply = getIntent().getStringExtra("prop_sply2");
        final String prop_av_vac = getIntent().getStringExtra("prop_ava_vac2");
        final String prop_vacn = getIntent().getStringExtra("prop_vacn2");
        final String prop_vac = getIntent().getStringExtra("prop_vac2");
        final String prop_tarrif = getIntent().getStringExtra("prop_tarrif2");
        final String prop_type = getIntent().getStringExtra("prop_type2");
        final String opt = getIntent().getStringExtra("opt");
        final String prop_facilities = getIntent().getStringExtra("prop_facilities2");
        prop = prop_name;
        sbmt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (uri != null) {
                    final ProgressDialog dialog = new ProgressDialog(Main2Activity.this);
                    dialog.setTitle("Uploading Image");
                    dialog.show();
                    dialog.setCancelable(false);
                    StorageReference filePath = storageReference.child("PostImage").child(uri.getLastPathSegment());
                    filePath.putFile(uri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                            dialog.dismiss();
                            final Uri downloadurl = taskSnapshot.getDownloadUrl();
                            Toast.makeText(Main2Activity.this, "Upload Complete", Toast.LENGTH_LONG).show();
                            final DatabaseReference newPost = databaseReference.push();
                            final DatabaseReference newPost1 = databaseReference1.push();
                            if (proptype.equals("Complete Apartment")) {
                                newPost.child("title").setValue(prop_name);
                                newPost.child("proptype").setValue(proptype);
                                newPost.child("custnum").setValue(cust_num);
                                newPost.child("altnum").setValue(altcust_num);
                                newPost.child("email").setValue(cust_em_site);
                                newPost.child("area").setValue(prop_area);
                                newPost.child("variant").setValue(prop_variant);
                                newPost.child("locality").setValue(prop_locality);
                                newPost.child("size").setValue(prop_size);
                                newPost.child("fur").setValue(prop_fur);
                                newPost.child("rent").setValue(prop_rent);
                                newPost.child("date").setValue(prop_date);
                                newPost.child("deposit").setValue(prop_depo);
                                newPost.child("brokerage").setValue(prop_brok);
                                newPost.child("ten").setValue(prop_ten);
                                newPost.child("sply").setValue(prop_sply);
                                newPost.child("desc").setValue(cust_name);
                                newPost.child("status").setValue("Uploaded");
                                newPost.child("image").setValue(downloadurl.toString());
                            }
                            else if (proptype.equals("Sharing Apartment"))
                            {
                                newPost.child("title").setValue(prop_name);
                                newPost.child("proptype").setValue(proptype);
                                newPost.child("custnum").setValue(cust_num);
                                newPost.child("altnum").setValue(altcust_num);
                                newPost.child("email").setValue(cust_em_site);
                                newPost.child("area").setValue(prop_area);
                                newPost.child("variant").setValue(prop_variant);
                                newPost.child("locality").setValue(prop_locality);
                                newPost.child("size").setValue(prop_size);
                                newPost.child("fur").setValue(prop_fur);
                                newPost.child("vac").setValue(prop_vac);
                                newPost.child("ava_vac").setValue(prop_av_vac);
                                newPost.child("rent").setValue(prop_rent);
                                newPost.child("date").setValue(prop_date);
                                newPost.child("deposit").setValue(prop_depo);
                                newPost.child("brokerage").setValue(prop_brok);
                                newPost.child("ten").setValue(prop_ten);
                                newPost.child("sply").setValue(prop_sply);
                                newPost.child("desc").setValue(cust_name);
                                newPost.child("status").setValue("Uploaded");
                                newPost.child("image").setValue(downloadurl.toString());
                            }
                            else if (proptype.equals("Serviced Apartment"))
                            {
                                newPost.child("title").setValue(prop_name);
                                newPost.child("proptype").setValue(proptype);
                                newPost.child("custnum").setValue(cust_num);
                                newPost.child("altnum").setValue(altcust_num);
                                newPost.child("email").setValue(cust_em_site);
                                newPost.child("area").setValue(prop_area);
                                newPost.child("variant").setValue(prop_variant);
                                newPost.child("locality").setValue(prop_locality);
                                newPost.child("size").setValue(prop_size);
                                newPost.child("facilities").setValue(prop_facilities);
                                newPost.child("rent").setValue(prop_rent);
                                newPost.child("sply").setValue(prop_sply);
                                newPost.child("desc").setValue(cust_name);
                                newPost.child("status").setValue("Uploaded");
                                newPost.child("image").setValue(downloadurl.toString());
                            }
                            else if (proptype.equals("PG"))
                            {
                                newPost.child("title").setValue(prop_name);
                                newPost.child("proptype").setValue(proptype);
                                newPost.child("custnum").setValue(cust_num);
                                newPost.child("altnum").setValue(altcust_num);
                                newPost.child("email").setValue(cust_em_site);
                                newPost.child("area").setValue(prop_area);
                                newPost.child("variant").setValue(prop_variant);
                                newPost.child("locality").setValue(prop_locality);
                                newPost.child("vacn").setValue(prop_vacn);
                                newPost.child("facilities").setValue(prop_facilities);
                                newPost.child("vac").setValue(prop_vac);
                                newPost.child("rent").setValue(prop_rent);
                                newPost.child("deposit").setValue(prop_depo);
                                newPost.child("brokerage").setValue(prop_brok);
                                newPost.child("date").setValue(prop_date);
                                newPost.child("ten").setValue(prop_ten);
                                newPost.child("sply").setValue(prop_sply);
                                newPost.child("desc").setValue(cust_name);
                                newPost.child("status").setValue("Uploaded");
                                newPost.child("image").setValue(downloadurl.toString());
                            }
                            else if (proptype.equals("Hotel") || proptype.equals("Home-Stay"))
                            {
                                newPost.child("title").setValue(prop_name);
                                newPost.child("proptype").setValue(proptype);
                                newPost.child("custnum").setValue(cust_num);
                                newPost.child("altnum").setValue(altcust_num);
                                newPost.child("email").setValue(cust_em_site);
                                newPost.child("area").setValue(prop_area);
                                newPost.child("variant").setValue(prop_variant);
                                newPost.child("locality").setValue(prop_locality);
                                newPost.child("facilities").setValue(prop_facilities);
                                newPost.child("tarrif").setValue(prop_tarrif);
                                newPost.child("propertytype").setValue(prop_type);
                                newPost.child("sply").setValue(prop_sply);
                                newPost.child("status").setValue("Uploaded");
                                newPost.child("desc").setValue(cust_name);
                                newPost.child("image").setValue(downloadurl.toString());
                            }
                        }
                    })
                            .addOnFailureListener(new OnFailureListener() {
                                @Override
                                public void onFailure(@NonNull Exception e) {
                                    dialog.dismiss();
                                    Toast.makeText(Main2Activity.this,e.getMessage(),Toast.LENGTH_SHORT).show();
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
                    Toast.makeText(Main2Activity.this,"Please select image",Toast.LENGTH_SHORT).show();
                }
                startActivity(new Intent(Main2Activity.this,MainActivity.class));
            }
        });
    }
    public void imageButtonClicked(View view){
        Intent galleryIntent = new Intent(Intent.ACTION_GET_CONTENT);
        galleryIntent.setType("image/*");
        startActivityForResult(galleryIntent,GALLERY_REQUEST);
    }

    private void examineData() {
        databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if (!dataSnapshot.child("title").equals(prop)) {
                    Toast.makeText(Main2Activity.this, "Data Does Not Exist", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(Main2Activity.this, "Data Exist", Toast.LENGTH_SHORT).show();
                }
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == GALLERY_REQUEST && resultCode == RESULT_OK){
            uri = data.getData();
            imageButton = (ImageButton)findViewById(R.id.imageButton);
            imageButton.setImageURI(uri);
        }
    }
}
