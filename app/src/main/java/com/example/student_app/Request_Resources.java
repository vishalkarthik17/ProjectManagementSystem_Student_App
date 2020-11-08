package com.example.student_app;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Request_Resources extends AppCompatActivity {
    private Button bb;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_request__resources);
        bb=findViewById(R.id.backBtn);
        bb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent ToHome=new Intent(Request_Resources.this,Home_Page.class);
                startActivity(ToHome);
            }
        });
    }
    @Override
    public void onBackPressed() {
        Intent ToHome=new Intent(Request_Resources.this,Home_Page.class);
        startActivity(ToHome);
    }
}
