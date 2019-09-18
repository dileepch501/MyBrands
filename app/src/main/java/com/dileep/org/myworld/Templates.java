package com.dileep.org.myworld;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.StrictMode;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.dileep.org.myworld.adapters.TemplatesAdapter;
import com.dileep.org.myworld.pojos.TemplatesPojo;
import com.facebook.shimmer.ShimmerFrameLayout;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

public class Templates extends AppCompatActivity {

    RecyclerView recyclerView;
    Toolbar toolbar;
    ProgressDialog progressDialog;
    FirebaseDatabase database;
    DatabaseReference reference;
    TemplatesAdapter adapter;
    ArrayList<TemplatesPojo> templatesPojoArrayList=new ArrayList<>();
    TemplatesPojo templatesPojo;
    Button next;
    ShimmerFrameLayout shimmerFrameLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_templates);
        database=FirebaseDatabase.getInstance();
        reference=database.getReference("templates");
        toolbar=(Toolbar)findViewById(R.id.toolbar);
        recyclerView=(RecyclerView)findViewById(R.id.recycle);
        shimmerFrameLayout=(ShimmerFrameLayout)findViewById(R.id.shimmer_view_container);
        next=(Button)findViewById(R.id.next);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter=new TemplatesAdapter(templatesPojoArrayList,this);
        recyclerView.setAdapter(adapter);
        setSupportActionBar(toolbar);
        toolbar.setNavigationIcon(R.drawable.navigation_back);
        getData();
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });

        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

               // System.out.println("datatest:"+templatesPojoArrayList.get(TemplatesAdapter.clickedPos).getName()+"  msg:"+templatesPojoArrayList.get(TemplatesAdapter.clickedPos).getMsg());
               if (TemplatesAdapter.clickedPos!=null) {
                   Intent intent = new Intent(Templates.this, ContactsMessageSending.class);
                   startActivity(intent);
               }else {
                   Toast.makeText(Templates.this, "Please select template", Toast.LENGTH_SHORT).show();
               }
            }
        });
        StrictMode.ThreadPolicy obj=new StrictMode.ThreadPolicy.Builder().build();
        StrictMode.setThreadPolicy(obj);

    }

    public void sendSms() {
        try {
            // Construct data
            String apiKey = "apikey=" + "3GSbLQ+Jhu4-6PtTkPLyhaLYGgJl7HpfUvuJALKCWB";
            String message = "&message=" + "Gundu Gundu Gundu Gundu Gundu Gundu Gundu Gundu Gundu Gundu Gundu Gundu Gundu Gundu Gundu Gundu Gundu Gundu Gundu Gundu Gundu Gundu Gundu  Gundu Gundu Gundu Gundu Gundu Gundu ";
            String sender = "&sender=" + "TXTLCL";
            String numbers = "&numbers=" + "9059975555";

            // Send data
            HttpURLConnection conn = (HttpURLConnection) new URL("https://api.textlocal.in/send/?").openConnection();
            String data = apiKey + numbers + message + sender;
            conn.setDoOutput(true);
            conn.setRequestMethod("POST");
            conn.setRequestProperty("Content-Length", Integer.toString(data.length()));
            conn.getOutputStream().write(data.getBytes("UTF-8"));
            final BufferedReader rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            final StringBuffer stringBuffer = new StringBuffer();
            String line;
            while ((line = rd.readLine()) != null) {
                stringBuffer.append(line);
            }
            rd.close();

            Toast.makeText(this, stringBuffer.toString(), Toast.LENGTH_SHORT).show();
            //  return stringBuffer.toString();

        } catch (Exception e) {
            System.out.println("Error SMS " + e);
            Toast.makeText(this, e.getMessage(), Toast.LENGTH_SHORT).show();
            // return "Error "+e;
        }



    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        TemplatesAdapter.clickedPos=null;
    }

    private void getData() {
        shimmerFrameLayout.startShimmerAnimation();
        templatesPojo=new TemplatesPojo();
        //datalist=new ArrayList<>();
        //adapter=new UsersAdapter(datalist,this);

        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                templatesPojoArrayList.clear();
                System.out.println("datacame:"+dataSnapshot);
                for (DataSnapshot ds:dataSnapshot.getChildren()){
                    templatesPojo=ds.getValue(TemplatesPojo.class);
                    templatesPojoArrayList.add(templatesPojo);
                }

                shimmerFrameLayout.stopShimmerAnimation();
                shimmerFrameLayout.setVisibility(View.GONE);
                recyclerView.setVisibility(View.VISIBLE);
                adapter.notifyDataSetChanged();

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
               // progressDialog.dismiss();
                Toast.makeText(Templates.this, databaseError.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
     getMenuInflater().inflate(R.menu.temp_menu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.addtemp:
                Intent orlist=new Intent(Templates.this,Addtemplate.class);
                startActivity(orlist);
                return true;

            default:
                return false;
        }
    }
}
