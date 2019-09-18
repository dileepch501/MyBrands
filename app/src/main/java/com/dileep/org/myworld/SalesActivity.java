package com.dileep.org.myworld;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

public class SalesActivity extends AppCompatActivity {

    Toolbar toolbar;
    LinearLayout mobiles,accessories;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sales);
        mobiles=(LinearLayout)findViewById(R.id.mobiles);
        accessories=(LinearLayout)findViewById(R.id.accessories);
        toolbar=(Toolbar) findViewById(R.id.toolbar);
        toolbar.setNavigationIcon(R.drawable.navigation_back);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });

        mobiles.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent mobiles=new Intent(SalesActivity.this,Mobiles.class);
                startActivity(mobiles);
            }
        });
        accessories.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent accessories=new Intent(SalesActivity.this,AccessoriesActivity.class);
                startActivity(accessories);
            }
        });
    }
}
