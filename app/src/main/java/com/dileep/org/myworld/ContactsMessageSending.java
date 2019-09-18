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
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import com.dileep.org.myworld.adapters.SendMessageAdapter;
import com.facebook.shimmer.ShimmerFrameLayout;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class ContactsMessageSending extends AppCompatActivity {

    ArrayList<String> listspin=new ArrayList<>();
    Spinner spinner;
    RecyclerView recyclerView;
    ShimmerFrameLayout shimmerFrameLayout;
    Users users;
    ArrayList<Users> usersArrayList=new ArrayList<>();
    FirebaseDatabase database;
    DatabaseReference reference;
    SendMessageAdapter adapter;
    Button send;
    Toolbar toolbar;

    String api="http://hindit.co.in/API/pushsms.aspx?loginID=$login_Id$&password=$password$&mobile=98********&text=$message$&senderid=******&route_id=1&Unicode=0&IP=x.x.x.x";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        database=FirebaseDatabase.getInstance();
        reference=database.getReference("customers");
        setContentView(R.layout.activity_contacts_message_sending);
        spinner=(Spinner)findViewById(R.id.dashspinner);
        send=(Button)findViewById(R.id.send);
        toolbar=(Toolbar)findViewById(R.id.toolbar);
        shimmerFrameLayout=(ShimmerFrameLayout)findViewById(R.id.shimmer_view_container);
        recyclerView=(RecyclerView)findViewById(R.id.contacts);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter=new SendMessageAdapter(usersArrayList,this);
        recyclerView.setAdapter(adapter);

        toolbar.setNavigationIcon(R.drawable.navigation_back);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
        loadspinner();
        getData();

        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(ContactsMessageSending.this,TestMessage.class);
                startActivity(intent);
               // Toast.makeText(ContactsMessageSending.this, "Under Implementation", Toast.LENGTH_SHORT).show();
            }
        });

    }

    private void loadspinner() {

        listspin.add("All contacts");
        listspin.add("First 10");
        listspin.add("First 50");
        listspin.add("First 100");
        listspin.add("First 150");
        listspin.add("First 200");
        listspin.add("First 500");

        ArrayAdapter<String> repeatadapter = new ArrayAdapter<String>(ContactsMessageSending.this, android.R.layout.simple_spinner_dropdown_item, listspin);
        spinner.setAdapter(repeatadapter);

        switch (spinner.getSelectedItemPosition()){
            case 0:
                adapter.Allselection();
                break;
            case 1:
                adapter.Firstten();
                break;
            case 2:
                adapter.Firstfifty();
                break;
            case 3:
                adapter.Firsthun();
                break;
            case 4:
                adapter.Firstonefifty();
                break;
            case 5:
                adapter.Firsttwohun();
                break;
            case 6:
                adapter.Firstfivehun();
            default:
                break;
        }

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long l) {

                switch (position){
                    case 0:
                        adapter.Allselection();
                        break;
                    case 1:
                        adapter.Firstten();
                        break;
                    case 2:
                        adapter.Firstfifty();
                        break;
                    case 3:
                        adapter.Firsthun();
                        break;
                    case 4:
                        adapter.Firstonefifty();
                        break;
                    case 5:
                        adapter.Firsttwohun();
                        break;
                    case 6:
                        adapter.Firstfivehun();
                        default:
                            break;
                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }

    private void getData() {

        users=new Users();
      //  usersArrayList=new ArrayList<>();

        shimmerFrameLayout.startShimmerAnimation();

        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                usersArrayList.clear();
                System.out.println("datacame:"+dataSnapshot);
                for (DataSnapshot ds:dataSnapshot.getChildren()){
                    users=ds.getValue(Users.class);
                    usersArrayList.add(users);
                }

                shimmerFrameLayout.stopShimmerAnimation();
                shimmerFrameLayout.setVisibility(View.GONE);
                recyclerView.setVisibility(View.VISIBLE);
                spinner.setVisibility(View.VISIBLE);
                adapter.notifyDataSetChanged();


            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                shimmerFrameLayout.stopShimmerAnimation();
                Toast.makeText(ContactsMessageSending.this, databaseError.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}
