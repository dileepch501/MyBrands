package com.dileep.org.myworld;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class Allusers extends AppCompatActivity {

    RecyclerView listview;
    Toolbar toolbar;
    Button create;
    UsersAdapter adapter;
    Users users;
    ProgressDialog progressDialog;
    ArrayList<Users> datalist=new ArrayList<>();
    FirebaseDatabase database;
    DatabaseReference reference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_allusers);

        database=FirebaseDatabase.getInstance();
        reference=database.getReference("customers");
        listview=(RecyclerView)findViewById(R.id.list);
        toolbar=(Toolbar)findViewById(R.id.toolbar);
        create=(Button)findViewById(R.id.createDetailes);
        listview.setHasFixedSize(true);
        toolbar.setNavigationIcon(R.drawable.navigation_back);

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });

        listview.setLayoutManager(new LinearLayoutManager(this));

        getData();
        create.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent createintent=new Intent(Allusers.this,AddNewCustomer.class);
                startActivity(createintent);
            }
        });

    }
    private void getData() {
        progressDialog=new ProgressDialog(Allusers.this);
        progressDialog.setMessage("Please Wait...");
        progressDialog.show();
        users=new Users();
        datalist=new ArrayList<>();
        adapter=new UsersAdapter(datalist,this);

        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                datalist.clear();
                System.out.println("datacame:"+dataSnapshot);
                for (DataSnapshot ds:dataSnapshot.getChildren()){
                    users=ds.getValue(Users.class);
                    datalist.add(users);
                }
                progressDialog.dismiss();
                listview.setAdapter(adapter);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                progressDialog.dismiss();
            }
        });
    }
}
