package com.dileep.org.myworld;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.app.ProgressDialog;
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

import org.w3c.dom.Text;

import java.util.Random;

public class Mobiles extends AppCompatActivity {

    Toolbar toolbar;
    EditText imei,modelnumber,brand,amountedit;
    TextInputLayout imeilayout,modelnumberlayout,brandlayout,amounteditlayout;
    Button submit;
    ProgressDialog progressDialog;
    FirebaseDatabase database,detailesDb;
    DatabaseReference reference,detailesRef;
    Mobilepojo mobilepojo;
    Getmobilespjo getmobilespjo;
    int no;
    boolean error=false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mobiles);
        database=FirebaseDatabase.getInstance();
        reference=database.getReference("sales/mobiles");
        detailesDb=FirebaseDatabase.getInstance();
        detailesRef=detailesDb.getReference("salesdetailes");

        toolbar=(Toolbar) findViewById(R.id.toolbar);
        submit=(Button)findViewById(R.id.submit);
        imei=(EditText)findViewById(R.id.imei);
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

    private void addSale() {

         if(!imei()|!brand()|!modelnum()|!amount()){
             return;
         }else {

             Random random=new Random();
             no=random.nextInt();
             final String id=imei.getText().toString()+no;
            /* progressDialog = new ProgressDialog(Mobiles.this);
             progressDialog.setMessage("Please Wait...");
             progressDialog.show();*/
            mobilepojo=new Mobilepojo();
            mobilepojo.setImei(imei.getText().toString());
            mobilepojo.setBrand(brand.getText().toString());
            mobilepojo.setModelnumber(modelnumber.getText().toString());
            mobilepojo.setAmount(amountedit.getText().toString());
            mobilepojo.setId(id);
            mobilepojo.setSalesmanname("dileepdsp");
            mobilepojo.setSalesmanId("7729958791");
            getmobilespjo=new Getmobilespjo();
            getmobilespjo.setId(id);
            getmobilespjo.setName("dileepdsp");



             reference.addValueEventListener(new ValueEventListener() {
                 @Override
                 public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                     System.out.println("usersdata" + dataSnapshot);
                     reference.child(id).setValue(mobilepojo);
                     detailesRef.child(mobilepojo.getSalesmanId()).child("mobilesales").child(id).setValue(getmobilespjo);



                    /* if (progressDialog.isShowing()) {
                         progressDialog.dismiss();
                     }*/
                     error=false;
                 }

                 @Override
                 public void onCancelled(@NonNull DatabaseError databaseError) {
                     progressDialog.dismiss();
                     Toast.makeText(Mobiles.this, databaseError.getMessage(), Toast.LENGTH_SHORT).show();
                     error=true;
                 }
             });

             if (error==false) {
                 Toast.makeText(this, "successfully done", Toast.LENGTH_SHORT).show();
                 finish();
             }
         }


    }

    private boolean imei() {
        String userPhone = imei.getText().toString().trim();

        if (userPhone.isEmpty()) {
            imeilayout.setError("Field can't be empty");
            return false;
        }  else {
            imeilayout.setError(null);
            return true;
        }
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
}
