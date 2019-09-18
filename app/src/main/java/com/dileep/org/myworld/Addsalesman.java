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

import com.dileep.org.myworld.pojos.Salesaddpojo;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Random;

public class Addsalesman extends AppCompatActivity {

    FirebaseDatabase database;
    DatabaseReference reference;
    EditText name,phone,email;
    Button insert;
    Users users;
    int no;
    ProgressDialog progressDialog;
    TextInputLayout namelayout,phonelayout;
    androidx.appcompat.widget.Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addsalesman);


        database=FirebaseDatabase.getInstance();
        reference=database.getReference("salesmans");
        name=(EditText)findViewById(R.id.name);
        phone=(EditText)findViewById(R.id.phone);
        insert=(Button) findViewById(R.id.insert);
        toolbar=(Toolbar) findViewById(R.id.toolbar);
        toolbar.setNavigationIcon(R.drawable.navigation_back);
        namelayout=(TextInputLayout)findViewById(R.id.namelayout);
        phonelayout=(TextInputLayout)findViewById(R.id.phonelayout);



        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
        insert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                insertData();
            }
        });



    }

    private boolean validateEmail() {
        String emailInput = name.getText().toString().trim();

        if (emailInput.isEmpty()) {
            namelayout.setError("Field can't be empty");
            return false;
        } else if (!(emailInput.length() >=3) || !(emailInput.length() <=30)) {
            namelayout.setError("Username length should be min 3 and max 30");
            return false;
        }
        else {
            namelayout.setError(null);
            return true;
        }
    }

    private boolean validatePhone() {
        String userPhone = phone.getText().toString().trim();

        if (userPhone.isEmpty()) {
            phonelayout.setError("Field can't be empty");
            return false;
        } else if (!(userPhone.length() == 10)) {
            phonelayout.setError("number should be 10 digits");
            return false;
        } else {
            phonelayout.setError(null);
            return true;
        }
    }

    private void insertData() {

        Random random=new Random();
        no=random.nextInt();
        System.out.println("random:"+no);

        if (!validateEmail() | !validatePhone()) {
            return;
        }else {

            progressDialog = new ProgressDialog(Addsalesman.this);
            progressDialog.setMessage("Please Wait...");
            progressDialog.show();
            users = new Users();
            users.setName(name.getText().toString());
            users.setPhone(phone.getText().toString());


      /*      reference.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                    System.out.println("usersdata" + dataSnapshot);
                    reference.child("User" + no).setValue(users);
                    progressDialog.dismiss();
                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {
                    progressDialog.dismiss();
                    Toast.makeText(Addsalesman.this, "Data inserted Failed", Toast.LENGTH_SHORT).show();
                }
            });*/
            reference.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                    System.out.println("usersdata" + dataSnapshot);
                    reference.child(phone.getText().toString()).setValue(users);
                   // reference.child(id).setValue(mobilepojo);
                   // reference.setValue(phone.getText().toString());



                    /* if (progressDialog.isShowing()) {
                         progressDialog.dismiss();
                     }*/
                  //  error=false;
                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {
                    //progressDialog.dismiss();
                    Toast.makeText(Addsalesman.this, databaseError.getMessage(), Toast.LENGTH_SHORT).show();
                   // error=true;
                }
            });
            Toast.makeText(this, "Salesman created successfully", Toast.LENGTH_SHORT).show();
            finish();


        }

    }
}
