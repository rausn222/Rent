package com.example.raushan.rent;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
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

public class Option5 extends AppCompatActivity {
    Button btn51, btn52;
    EditText ed51, ed52, ed53;
    TextView textView51;
    String[] listItems5;
    boolean[] checkedItems5;
    String st51, st52, st53, st54;
    int re5;
    ArrayList<Integer> mUserItems5 = new ArrayList<>();

    private StorageReference storageReference;
    private FirebaseDatabase database;
    private Uri uri = null;
    private DatabaseReference databaseReference;
    ProgressBar progressBar2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_option5);
        btn51 = (Button) findViewById(R.id.facilities5);
        btn52 = (Button) findViewById(R.id.opt5smt);
        textView51 = (TextView) findViewById(R.id.textView55);
        ed51 = (EditText) findViewById(R.id.rent5);
        ed52 = (EditText) findViewById(R.id.vacancy5);
        ed53 = (EditText) findViewById(R.id.supplynotes5);
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
        listItems5 = getResources().getStringArray(R.array.facilities_list);
        btn51.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder mBuilder = new AlertDialog.Builder(Option5.this);
                mBuilder.setTitle(R.string.dialog_title);
                mBuilder.setMultiChoiceItems(listItems5, checkedItems5, new DialogInterface.OnMultiChoiceClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int position, boolean isChecked) {
                        if (isChecked) {
                            if (!mUserItems5.contains(position)) {
                                mUserItems5.add(position);
                            } else {
                                mUserItems5.remove(position);
                            }
                        }
                    }
                });
                mBuilder.setCancelable(false);
                mBuilder.setPositiveButton(R.string.ok_label, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int which) {
                        String item = "";
                        for (int i = 0; i < mUserItems5.size(); i++) {
                            item = item + listItems5[mUserItems5.get(i)];
                            if (i != mUserItems5.size() - 1) {
                                item = item + ", ";
                            }
                        }
                        textView51.setText(item);
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
                        for (int i = 0; i < checkedItems5.length; i++) {
                            checkedItems5[i] = false;
                            mUserItems5.clear();
                            textView51.setText("");
                        }
                    }
                });
                AlertDialog mDialog = mBuilder.create();
                mBuilder.show();
            }
        });
        btn52.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                st51 = textView51.getText().toString();
                st52 = ed51.getText().toString();
                st53 = ed52.getText().toString();
                st54 = ed53.getText().toString();
                if (st51.equals("Please select by clicking on button")) {
                    Toast.makeText(Option5.this, "Please select facilities for property.", Toast.LENGTH_SHORT).show();
                } else if (st52.equals("")) {
                    Toast.makeText(Option5.this, "Please enter tariff for property.", Toast.LENGTH_SHORT).show();
                } else if (!st52.equals("")) {
                    re5 = Integer.parseInt(st52);
                    if (re5 <= 1) {
                        Toast.makeText(Option5.this, "Please enter valid tariff for property.", Toast.LENGTH_SHORT).show();
                    } else if (st53.equals("")) {
                        Toast.makeText(Option5.this, "Please enter room type for property.", Toast.LENGTH_SHORT).show();
                    }
                    else if (st54.equals("")){
                        Toast.makeText(Option5.this,"Please enter supply notes",Toast.LENGTH_SHORT).show();
                    }
                    else {
                        uri = Uri.parse(uristring);
                        if (uri != null) {
                            final ProgressDialog dialog1 = new ProgressDialog(Option5.this);
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
                                    newPost.child("facilities").setValue(st51);
                                    newPost.child("tarrif").setValue(st52);
                                    newPost.child("propertytype").setValue(st53);
                                    newPost.child("sply").setValue(st54);
                                    newPost.child("status").setValue("Uploaded");
                                    newPost.child("desc").setValue(cust_name);
                                    newPost.child("image").setValue(downloadurl.toString());
                                    Toast.makeText(Option5.this, "Upload Complete", Toast.LENGTH_LONG).show();
                                    if(times.equals("One"))
                                    {
                                        startActivity(new Intent(Option5.this,MainActivity.class));
                                    }
                                    else if (times.equals("Two"))
                                    {
                                        Intent i = new Intent(Option5.this,Option3.class);
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
                                        Intent i = new Intent(Option5.this,Option5.class);
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
                                        Intent i = new Intent(Option5.this,Option5.class);
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
                                        Intent i = new Intent(Option5.this,Option5.class);
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
                                        Intent i = new Intent(Option5.this,Option5.class);
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
                                        Intent i = new Intent(Option5.this,Option5.class);
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
                                        Intent i = new Intent(Option5.this,Option5.class);
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
                                            Toast.makeText(Option5.this,e.getMessage(),Toast.LENGTH_SHORT).show();
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
                            Toast.makeText(Option5.this,"Please select image",Toast.LENGTH_SHORT).show();
                        }
                    }
                }
            }
        });
    }
}