package com.dileep.org.myworld;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.dileep.org.myworld.pojos.TemplatesPojo;
import com.facebook.shimmer.ShimmerFrameLayout;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Random;

public class Addtemplate extends AppCompatActivity {

    FirebaseDatabase database;
    DatabaseReference reference;
    EditText name,message,email;
    Button submit;
    Toolbar toolbar;
    int no;
    boolean error=false;
    TextInputLayout namelayout,phonelayout;
    TemplatesPojo templatesPojo;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addtemplate);

        database=FirebaseDatabase.getInstance();
        reference=database.getReference("templates");

        name=(EditText)findViewById(R.id.name);
        message=(EditText)findViewById(R.id.message);
        submit=(Button)findViewById(R.id.submit);
        toolbar=(Toolbar) findViewById(R.id.toolbar);
        toolbar.setNavigationIcon(R.drawable.navigation_back);
        namelayout=(TextInputLayout)findViewById(R.id.namelayout);
        phonelayout=(TextInputLayout)findViewById(R.id.phonelayout);


        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                insertData();
            }
        });
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
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

        String emailInput = message.getText().toString().trim();

        if (emailInput.isEmpty()) {
            phonelayout.setError("Field can't be empty");
            return false;
        } else if (!(emailInput.length() >= 3) || !(emailInput.length() <= 100)) {
            phonelayout.setError("Message length should be min 3 and max 100");
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

          /*  progressDialog = new ProgressDialog(AddNewCustomer.this);
            progressDialog.setMessage("Please Wait...");
            progressDialog.show();*/
            templatesPojo = new TemplatesPojo();
            templatesPojo.setName(name.getText().toString());
            templatesPojo.setMsg(message.getText().toString());
            String productname=name.getText().toString();
            productname=productname.trim().replace(" ", "");
            templatesPojo.setTemid(productname + no);



            reference.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                    System.out.println("templatesdata" + dataSnapshot);
                    reference.child(templatesPojo.getTemid()).setValue(templatesPojo);
                    // progressDialog.dismiss();
                    error=false;
                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {
                    //  progressDialog.dismiss();
                    error=true;
                    Toast.makeText(Addtemplate.this, "Data inserted Failed", Toast.LENGTH_SHORT).show();
                }
            });

            if (error==false) {

                Toast.makeText(this, "Template added successfully", Toast.LENGTH_SHORT).show();
                finish();

            }


        }

    }

}
