package com.example.student_app;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Next_Review extends AppCompatActivity {
    private Button bb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_next__review);

        getSupportActionBar().setTitle("Next Review");

        bb=findViewById(R.id.backBtn);
        bb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent ToHome=new Intent(Next_Review.this,Home_Page.class);
                startActivity(ToHome);
            }
        });

    }

    @Override
    public void onBackPressed() {
        Intent ToHome=new Intent(Next_Review.this,Home_Page.class);
        startActivity(ToHome);
    }


}
