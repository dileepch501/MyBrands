package com.dileep.org.myworld;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Random;

public class AccessoriesActivity extends AppCompatActivity {

    Toolbar toolbar;
    EditText imei,modelnumber,brand,amountedit;
    TextInputLayout imeilayout,modelnumberlayout,brandlayout,amounteditlayout;
    Button submit;
    ProgressDialog progressDialog;
    FirebaseDatabase database,detailesDb;
    DatabaseReference reference,detailesRef;
    Accepojodil accepojo;
    Getaccpojo getaccpojo;
    int no;
    boolean error=false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_accessories);
        database=FirebaseDatabase.getInstance();
        reference=database.getReference("sales/accessories");
        detailesDb=FirebaseDatabase.getInstance();
        detailesRef=detailesDb.getReference("salesdetailes");
        toolbar=(Toolbar) findViewById(R.id.toolbar);
        submit=(Button)findViewById(R.id.submit);
       /* imei=(EditText)findViewById(R.id.imei);*/
        brand=(EditText)findViewById(R.id.brand);
        modelnumber=(EditText)findViewById(R.id.modelno);
        amountedit=(EditText)findViewById(R.id.amount);
        imeilayout=(TextInputLayout)findViewById(R.id.imeilayout);
        modelnumberlayout=(TextInputLayout)findViewById(R.id.modenollayout);
        brandlayout=(TextInputLayout)findViewById(R.id.brandlayout);
        amounteditlayout=(TextInputLayout)findViewById(R.id.amountlayout);
        toolbar.setNavigationIcon(R.drawable.navigation_back);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addSale();
            }
        });

    }
    private boolean brand() {
        String userPhone = brand.getText().toString().trim();

        if (userPhone.isEmpty()) {
            brandlayout.setError("Field can't be empty");
            return false;
        }  else {
            brandlayout.setError(null);
            return true;
        }
    }
    private boolean modelnum() {
        String userPhone = modelnumber.getText().toString().trim();

        if (userPhone.isEmpty()) {
            modelnumberlayout.setError("Field can't be empty");
            return false;
        }  else {
            modelnumberlayout.setError(null);
            return true;
        }
    }
    private boolean amount() {
        String userPhone = amountedit.getText().toString().trim();

        if (userPhone.isEmpty()) {
            amounteditlayout.setError("Field can't be empty");
            return false;
        }  else {
            amounteditlayout.setError(null);
            return true;
        }
    }

    private void addSale() {

        if(!brand()|!modelnum()|!amount()){
            return;
        }else {

            Random random=new Random();
            no=random.nextInt();
           final String id=String.valueOf(no);
          /*  progressDialog = new ProgressDialog(AccessoriesActivity.this);
            progressDialog.setMessage("Please Wait...");
            progressDialog.show();*/
            accepojo=new Accepojodil();
         //   mobilepojo.setImei(imei.getText().toString());
            accepojo.setBrand(brand.getText().toString());
            accepojo.setModelnumber(modelnumber.getText().toString());
            accepojo.setAmount(amountedit.getText().toString());
            accepojo.setId(id);
            accepojo.setSalesmanname("dileepdsp");
            accepojo.setSalesmanId("7729958791");
            getaccpojo=new Getaccpojo();
            getaccpojo.setId(id);
            getaccpojo.setName("dileepdsp");
            reference.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                    System.out.println("usersdata" + dataSnapshot);
                    reference.child(id).setValue(accepojo);
                    detailesRef.child(accepojo.getSalesmanId()).child("accsales").child(id).setValue(getaccpojo);

                   /* if (progressDialog.isShowing()) {
                        progressDialog.dismiss();
                    }*/
                    error=false;
                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {
                    progressDialog.dismiss();
                    Toast.makeText(AccessoriesActivity.this, databaseError.getMessage(), Toast.LENGTH_SHORT).show();
                    error=true;
                }
            });

            if (error==false) {
                Toast.makeText(this, "successfully done", Toast.LENGTH_SHORT).show();
                finish();
            }
        }


    }

}
