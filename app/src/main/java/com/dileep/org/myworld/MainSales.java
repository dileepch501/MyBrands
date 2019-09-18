package com.dileep.org.myworld;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.facebook.shimmer.ShimmerFrameLayout;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class MainSales extends AppCompatActivity {

    RecyclerView salesdcycle;
    FirebaseDatabase database, detailesDb,databaseac,salesmandb;
    DatabaseReference reference, detailesRef,referenceac,salesmanref;
    ArrayList<Salesdetailespojo> salesdetailespojoArrayList = new ArrayList<>();
    SalesDetailesAdpater adpater;
    ShimmerFrameLayout shimmerFrameLayout;
    ArrayList<Getmobilespjo> getmobilespjoArrayList = new ArrayList<>();
    Getmobilespjo getmobilespjo;
    Mobilepojo mobilepojo;
    Accepojodil accepojo;
    Users userspojo;
    long totalamount,mobileamount=0,accamount=0;
    ArrayList<Mobilepojo> mobilepojoArrayList=new ArrayList<>();
    ArrayList<Accepojodil> accepojoArrayList=new ArrayList<>();
    ArrayList<Users> salesmanArralist=new ArrayList<>();
    Toolbar toolbar;


    TextView totalsale,totalamounttxt;
    String salesmanid;
    long mobilecount, account;

    Button sales, accessories;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_sales);
        sales = (Button) findViewById(R.id.sales);
        accessories = (Button) findViewById(R.id.accessories);
        salesdcycle = (RecyclerView) findViewById(R.id.salesdcycle);
        totalsale=(TextView)findViewById(R.id.totalsale);
        totalamounttxt=(TextView)findViewById(R.id.totalamount);
        shimmerFrameLayout = (ShimmerFrameLayout) findViewById(R.id.shimmer_view_container);
        salesdcycle.setHasFixedSize(true);
        salesdcycle.setNestedScrollingEnabled(false);
        salesdcycle.setLayoutManager(new LinearLayoutManager(MainSales.this));
        adpater = new SalesDetailesAdpater(salesdetailespojoArrayList,salesmanArralist, MainSales.this);
        salesdcycle.setAdapter(adpater);
        toolbar=(Toolbar)findViewById(R.id.toolbar);
        detailesDb = FirebaseDatabase.getInstance();
        detailesRef = detailesDb.getReference("salesdetailes");

        database=FirebaseDatabase.getInstance();
        reference=database.getReference("sales/mobiles");

        databaseac=FirebaseDatabase.getInstance();
        referenceac=database.getReference("sales/accessories");

        salesmandb=FirebaseDatabase.getInstance();
        salesmanref=salesmandb.getReference("salesmans");
        toolbar.setNavigationIcon(R.drawable.navigation_back);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });


        getData();
        getTotal();

        sales.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent salesintent = new Intent(MainSales.this, Addsalesman.class);
                startActivity(salesintent);
            }
        });
        accessories.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent accessoriesintent = new Intent(MainSales.this, SalesActivity.class);
                startActivityForResult(accessoriesintent,501);

            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode==501){
            System.out.println("updating::::");
           // getData();
        }

    }

    private void getTotal() {

            // progressBar.setVisibility(View.VISIBLE);
           // shimmerFrameLayout.startShimmerAnimation();
           //shimmerFrameLayout.setAngle(ShimmerFrameLayout.MaskAngle.CW_180);

            reference.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {


                    mobilepojoArrayList.clear();

                    System.out.println("datacame:" + dataSnapshot);
                    for (DataSnapshot ds : dataSnapshot.getChildren()) {
                        mobilepojo = ds.getValue(Mobilepojo.class);
                        mobilepojoArrayList.add(mobilepojo);
                    }

                    referenceac.addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                            accepojoArrayList.clear();

                            for (DataSnapshot ds : dataSnapshot.getChildren()) {
                                accepojo = ds.getValue(Accepojodil.class);
                                accepojoArrayList.add(accepojo);
                            }

                            totalsale.setText(String.valueOf(mobilepojoArrayList.size()+accepojoArrayList.size()));
                            gettotalamount();
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError databaseError) {

                        }
                    });
                 //   shimmerFrameLayout.stopShimmerAnimation();
                 //   shimmerFrameLayout.setVisibility(View.GONE);
                  //  recyclerView.setVisibility(View.VISIBLE);

                    //progressBar.setVisibility(View.GONE);
                    // progressDialog.dismiss();
                    //listview.setAdapter(adapter);
                    // products_total.setText(String.valueOf(uploadProductPojoList.size()));

                  //  adapter.notifyDataSetChanged();




                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {
                   // progressBar.setVisibility(View.GONE);
                    Toast.makeText(MainSales.this, databaseError.getMessage(), Toast.LENGTH_SHORT).show();
                }
            });




    }

    private void gettotalamount() {

        for (int i=0;i<mobilepojoArrayList.size();i++){

          mobileamount=mobilecount+Long.parseLong(  mobilepojoArrayList.get(i).getAmount());

        }
        for (int j=0;j<accepojoArrayList.size();j++){
            accamount=accamount+Long.parseLong(accepojoArrayList.get(j).getAmount());
        }

        totalamounttxt.setText(String.valueOf(mobileamount+accamount));

    }

    private void getData() {


        /*progressDialog=new ProgressDialog(MainSales.this);
        progressDialog.setMessage("Please Wait...");
        progressDialog.show();*/
        // users=new Users();
        //  datalist=new ArrayList<>();
        //  adapter=new UsersAdapter(datalist,this);

       // getmobilespjoArrayList.clear();

        shimmerFrameLayout.startShimmerAnimation();

        salesmanref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                salesmanArralist.clear();
                for (DataSnapshot ds:dataSnapshot.getChildren()){
                    userspojo=ds.getValue(Users.class);
                    salesmanArralist.add(userspojo);
                }

                adpater.notifyDataSetChanged();

                detailesRef.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        salesdetailespojoArrayList.clear();
                        getmobilespjoArrayList.clear();
                        System.out.println("datacame:" + dataSnapshot);
                        for (DataSnapshot ds : dataSnapshot.getChildren()) {

                            salesmanid = ds.getKey();
                            System.out.println("mobiledata" + ds);
                            System.out.println("accsalescount access" + salesmanid + " " + ds.child("accsales").getChildrenCount());
                            System.out.println("accsalescount mobiles" + salesmanid + " " + ds.child("mobilesales").getChildrenCount());
                            System.out.println("accsalescount mobiles" + salesmanid + " " + ds.child("mobilesales").getValue(Getmobilespjo.class));

                            getmobilespjo = ds.child("mobilesales").getValue(Getmobilespjo.class);
                            getmobilespjoArrayList.add(getmobilespjo);

                            Salesdetailespojo salesdetailespojo = new Salesdetailespojo();
                            salesdetailespojo.setSalesmanid(salesmanid);
                            salesdetailespojo.setAccesscount(ds.child("accsales").getChildrenCount());
                            salesdetailespojo.setMobilescount(ds.child("mobilesales").getChildrenCount());
                            salesdetailespojoArrayList.add(salesdetailespojo);


                        }
                        shimmerFrameLayout.stopShimmerAnimation();
                        shimmerFrameLayout.setVisibility(View.GONE);
                        salesdcycle.setVisibility(View.VISIBLE);
                        adpater.notifyDataSetChanged();

                        for (int i=0;i<getmobilespjoArrayList.size();i++){
                            System.out.println("titalsaledata:"+getmobilespjoArrayList.get(i).getName()+" "+getmobilespjoArrayList.get(i).getId());
                        }

                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {
                        //   progressDialog.dismiss();
                    }
                });

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });


    }
}
