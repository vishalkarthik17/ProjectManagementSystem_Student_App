package com.example.student_app;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Select_Team extends AppCompatActivity {

    private Button proceed;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select__team);
        getSupportActionBar().setTitle("Select Team");
        proceed=findViewById(R.id.toHomeBtn);
        proceed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent loginToHome=new Intent(Select_Team.this,Home_Page.class);
                startActivity(loginToHome);
                finish();
            }
        });
    }
}
