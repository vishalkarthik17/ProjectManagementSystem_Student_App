package com.example.student_app;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity {

    private Button Login_Button;
    private Button br;
    //private FirebaseAuth mAuth;
    private EditText userid,pword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
      // mAuth = FirebaseAuth.getInstance();

        Login_Button= findViewById(R.id.loginBtn);

        userid=findViewById(R.id.UID);
        pword=findViewById(R.id.PW);


        Login_Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

               /* final String uids= userid.getText().toString().trim();
                final String pws=pword.getText().toString().trim();
                mAuth.signInWithEmailAndPassword(uids,pws).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                      if(task.isSuccessful()&& uids!="" && pws!=""){
                          Intent loginToHome=new Intent(MainActivity.this,Home_Page.class);
                          startActivity(loginToHome);
                          finish();

                      }
                      else
                      {
                          Toast.makeText(MainActivity.this, "Username/Password Dont Match!", Toast.LENGTH_SHORT).show();
                      }
                    }
                });*/

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
