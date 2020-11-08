package com.example.student_app;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class View_Approved extends AppCompatActivity {
    private Button bb;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view__approved);
        getSupportActionBar().setTitle("View Approved Requests");
        bb=findViewById(R.id.backBtn);
        bb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent ToHome=new Intent(View_Approved.this,Home_Page.class);
                startActivity(ToHome);
            }
        });
    }
    @Override
    public void onBackPressed() {
        Intent ToHome=new Intent(View_Approved.this,Home_Page.class);
        startActivity(ToHome);
    }
}
