package com.dileep.org.myworld;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class LogingActivity extends AppCompatActivity {

    EditText phonnumber;

    FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loging);

        mAuth = FirebaseAuth.getInstance();
        phonnumber = (EditText) findViewById(R.id.phonnuber);

        FirebaseUser currentuser = mAuth.getCurrentUser();
        if (currentuser == null) {
         /*   Intent login=new Intent(getApplicationContext(),MainActivity.class);
            startActivity(login);
            finish();*/
        } else {
            Intent homeintent = new Intent(LogingActivity.this, MainActivity.class);
            homeintent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(homeintent);

        }



        findViewById(R.id.buttonContinue).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String mobile = phonnumber.getText().toString().trim();

                if (mobile.isEmpty() || mobile.length() < 10) {
                    phonnumber.setError("Enter a valid mobile");
                    phonnumber.requestFocus();
                    return;
                }

                Intent intent = new Intent(LogingActivity.this, VerifyPhoneActivity.class);
                intent.putExtra("mobile", mobile);
                startActivity(intent);
            }
        });


    }


    @Override
    protected void onStart() {
        super.onStart();


    }
}