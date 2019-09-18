package com.dileep.org.myworld;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.dileep.org.myworld.adapters.Accessadapter;
import com.dileep.org.myworld.adapters.SalesinfoAdapter;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class SalesmanInfo extends AppCompatActivity {

    String id,name;
    Toolbar toolbar;
    FirebaseDatabase database, detailesDb,databaseac,salesmandb;
    DatabaseReference reference, detailesRef,referenceac,salesmanref;
    ArrayList<Mobilepojo> mobilepojoArrayList=new ArrayList<>();
    Mobilepojo mobilepojo;
    ArrayList<Accepojodil> accepojoArrayList=new ArrayList<>();
    Accepojodil accepojodil;
    Accessadapter accessadapter;

    SalesinfoAdapter mobileadaper;

RecyclerView accview,mobileview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_salesman_info);
        accview=(RecyclerView)findViewById(R.id.accview);
        toolbar=(Toolbar)findViewById(R.id.toolbar);
        mobileview=(RecyclerView)findViewById(R.id.mobileview);
        mobileview.setNestedScrollingEnabled(false);
        mobileview.setHasFixedSize(true);
        mobileview.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        mobileadaper=new SalesinfoAdapter(mobilepojoArrayList,SalesmanInfo.this);
        mobileview.setAdapter(mobileadaper);
        accview.setHasFixedSize(true);
        accview.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        accview.setNestedScrollingEnabled(false);
        accessadapter=new Accessadapter(accepojoArrayList,SalesmanInfo.this);
        accview.setAdapter(accessadapter);
        toolbar.setNavigationIcon(R.drawable.navigation_back);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });

        Bundle bundle= getIntent().getExtras();
        if (bundle!=null){
            id=bundle.getString("id");
            System.out.println("bundleextras:"+id);
            name=bundle.getString("name");

            toolbar.setTitle(name);

            getDetailes(id);

        }

    }

    private void getDetailes(String id) {

        database= FirebaseDatabase.getInstance();
        reference=database.getReference("sales/mobiles");
        databaseac=FirebaseDatabase.getInstance();
        referenceac=database.getReference("sales/accessories");

        Query mobilequery=reference.orderByChild("salesmanId").equalTo(id);
        Query accessquery=referenceac.orderByChild("salesmanId").equalTo(id);
        mobilequery.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                System.out.println("thequerydata:"+dataSnapshot);
                mobilepojoArrayList.clear();
                for (DataSnapshot ds:dataSnapshot.getChildren()){
                    mobilepojo=ds.getValue(Mobilepojo.class);
                    mobilepojoArrayList.add(mobilepojo);
                }

                mobileadaper.notifyDataSetChanged();


            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        accessquery.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                System.out.println("accessquery:"+dataSnapshot);
                accepojoArrayList.clear();
                for (DataSnapshot ds: dataSnapshot.getChildren()){

                    accepojodil=ds.getValue(Accepojodil.class);
                    accepojoArrayList.add(accepojodil);
                }

                accessadapter.notifyDataSetChanged();


            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }
}
