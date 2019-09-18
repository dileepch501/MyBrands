package com.dileep.org.myworld;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.zip.Inflater;

public class MainActivity extends AppCompatActivity {

    Toolbar toolbar;
    LinearLayout addcutomer,viewall,sales,bulksms;
    FirebaseAuth mAuth;


    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem menuItem) {
        switch (menuItem.getItemId()) {
         /*   case R.id.orderList:
                Intent orlist=new Intent(Home.this,OrderListActivity.class);
                startActivity(orlist);
                return true;*/
            case R.id.logout:
                logout();
                return true;
            default:
                return false;
        }
    }

    private void logout() {
        FirebaseAuth.getInstance().signOut();
        Intent homeintent = new Intent(MainActivity.this, LogingActivity.class);
        homeintent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(homeintent);


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.inner_menu,menu);
        return true;

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mAuth = FirebaseAuth.getInstance();

        toolbar=(Toolbar)findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        addcutomer=(LinearLayout)findViewById(R.id.addcustomer);
        viewall=(LinearLayout)findViewById(R.id.viewall);
        sales=(LinearLayout)findViewById(R.id.sales);
        bulksms=(LinearLayout)findViewById(R.id.bulksms);

        bulksms.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent bulksms=new Intent(MainActivity.this,Templates.class);
                startActivity(bulksms);
            }
        });
        addcutomer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent addcustomer=new Intent(MainActivity.this,AddNewCustomer.class);
                startActivity(addcustomer);
            }
        });
        viewall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent viewall=new Intent(MainActivity.this,Allusers.class);
                startActivity(viewall);
            }
        });
        sales.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent sales=new Intent(MainActivity.this,MainSales.class);
                startActivity(sales);
            }
        });



    }

    @Override
    protected void onStart() {
        super.onStart();

        FirebaseUser currentuser = mAuth.getCurrentUser();
        if (currentuser == null) {
            Intent homeintent = new Intent(MainActivity.this, LogingActivity.class);
            homeintent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(homeintent);
         /*   Intent login=new Intent(getApplicationContext(),MainActivity.class);
            startActivity(login);
            finish();*/
        } else {


        }
    }
}
