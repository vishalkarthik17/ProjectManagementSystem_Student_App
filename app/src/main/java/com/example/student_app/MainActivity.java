package com.example.student_app;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    private Button Login_Button;
    private Button br;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Login_Button= findViewById(R.id.loginBtn);

        Login_Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent loginToHome=new Intent(MainActivity.this,Home_Page.class);
                startActivity(loginToHome);
                finish();
            }
        });

        br=findViewById(R.id.branch);
        br.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent loginToHome=new Intent(MainActivity.this,Role_Skill.class);
                startActivity(loginToHome);
                finish();
            }
        });
    }
}
