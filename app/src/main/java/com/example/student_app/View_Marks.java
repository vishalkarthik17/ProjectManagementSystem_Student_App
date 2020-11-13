package com.example.student_app;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class View_Marks extends AppCompatActivity {
    private Button bb;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view__marks);

        getSupportActionBar().setTitle("View Marks");

        bb=findViewById(R.id.backBtn);
        bb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent ToHome=new Intent(View_Marks.this,Home_Page.class);
                startActivity(ToHome);
            }
        });

        }
    @Override
    public void onBackPressed() {
        Intent ToHome=new Intent(View_Marks.this,Home_Page.class);
        startActivity(ToHome);
    }
}
