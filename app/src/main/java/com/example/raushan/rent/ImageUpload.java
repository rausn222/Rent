package com.example.raushan.rent;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ProgressBar;

public class ImageUpload extends AppCompatActivity {
    private static final int GALLERY_REQUEST = 2;
    private Uri uri = null;
    private ImageButton imageButton;
    private Button sbmt;
    String times;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_upload);
        final String proptype = getIntent().getStringExtra("proptype2");
        final String cust_name = getIntent().getStringExtra("cust_name2");
        final String prop_name = getIntent().getStringExtra("prop_name2");
        final String cust_num = getIntent().getStringExtra("cust_num2");
        final String altcust_num = getIntent().getStringExtra("alt_num2");
        final String cust_em_site = getIntent().getStringExtra("cust_em_site2");
        final String prop_area = getIntent().getStringExtra("prop_area2");
        final String prop_variant = getIntent().getStringExtra("prop_variant2");
        final String prop_locality = getIntent().getStringExtra("prop_locality2");
        times=prop_variant;
        sbmt = (Button)findViewById(R.id.imageupload);
        sbmt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String ur=uri.toString();
                if(proptype.equals("Complete Apartment")) {
                    Intent i = new Intent(ImageUpload.this,Option1.class);
                    i.putExtra("proptype2",proptype);
                    i.putExtra("cust_name2",cust_name);
                    i.putExtra("prop_name2",prop_name);
                    i.putExtra("cust_num2",cust_num);
                    i.putExtra("alt_num2",altcust_num);
                    i.putExtra("cust_em_site2",cust_em_site);
                    i.putExtra("prop_area2",prop_area);
                    i.putExtra("prop_variant2",prop_variant);
                    i.putExtra("prop_locality2",prop_locality);
                    i.putExtra("times2",times);
                    i.putExtra("uri1",ur);
                    startActivity(i);
                }
                else if(proptype.equals("Sharing Apartment"))
                {
                    Intent i = new Intent(ImageUpload.this,Option2.class);
                    i.putExtra("proptype2",proptype);
                    i.putExtra("cust_name2",cust_name);
                    i.putExtra("prop_name2",prop_name);
                    i.putExtra("cust_num2",cust_num);
                    i.putExtra("alt_num2",altcust_num);
                    i.putExtra("cust_em_site2",cust_em_site);
                    i.putExtra("prop_area2",prop_area);
                    i.putExtra("prop_variant2",prop_variant);
                    i.putExtra("prop_locality2",prop_locality);
                    i.putExtra("times2",times);
                    i.putExtra("uri1",ur);
                    startActivity(i);
                }
                else if(proptype.equals("Serviced Apartment"))
                {
                    Intent i = new Intent(ImageUpload.this,Option3.class);
                    i.putExtra("proptype2",proptype);
                    i.putExtra("cust_name2",cust_name);
                    i.putExtra("prop_name2",prop_name);
                    i.putExtra("cust_num2",cust_num);
                    i.putExtra("alt_num2",altcust_num);
                    i.putExtra("cust_em_site2",cust_em_site);
                    i.putExtra("prop_area2",prop_area);
                    i.putExtra("prop_variant2",prop_variant);
                    i.putExtra("prop_locality2",prop_locality);
                    i.putExtra("times2",times);
                    i.putExtra("uri1",ur);
                    startActivity(i);
                }
                else if(proptype.equals("PG"))
                {
                    Intent i = new Intent(ImageUpload.this,Option4.class);
                    i.putExtra("proptype2",proptype);
                    i.putExtra("cust_name2",cust_name);
                    i.putExtra("prop_name2",prop_name);
                    i.putExtra("cust_num2",cust_num);
                    i.putExtra("alt_num2",altcust_num);
                    i.putExtra("cust_em_site2",cust_em_site);
                    i.putExtra("prop_area2",prop_area);
                    i.putExtra("prop_variant2",prop_variant);
                    i.putExtra("prop_locality2",prop_locality);
                    i.putExtra("times2",times);
                    i.putExtra("uri1",ur);
                    startActivity(i);
                }
                else if(proptype.equals("Hotel") || proptype.equals("Home-Stay"))
                {
                    Intent i = new Intent(ImageUpload.this,Option5.class);
                    i.putExtra("proptype2",proptype);
                    i.putExtra("cust_name2",cust_name);
                    i.putExtra("prop_name2",prop_name);
                    i.putExtra("cust_num2",cust_num);
                    i.putExtra("alt_num2",altcust_num);
                    i.putExtra("cust_em_site2",cust_em_site);
                    i.putExtra("prop_area2",prop_area);
                    i.putExtra("prop_variant2",prop_variant);
                    i.putExtra("prop_locality2",prop_locality);
                    i.putExtra("times2",times);
                    i.putExtra("uri1",ur);
                    startActivity(i);
                }
            }
        });
    }
    public void imageButtonClicked(View view){
        Intent galleryIntent = new Intent(Intent.ACTION_GET_CONTENT);
        galleryIntent.setType("image/*");
        startActivityForResult(galleryIntent,GALLERY_REQUEST);
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
