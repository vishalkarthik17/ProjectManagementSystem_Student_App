package com.example.student_app;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.UUID;

public class Request_Budget extends AppCompatActivity {
    private Button bb,pr;
    private EditText bud,budDesc;
    private FirebaseAuth mAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_request__budget);
        getSupportActionBar().setTitle("Request Budget");

        bud=findViewById(R.id.bud);
        budDesc=findViewById(R.id.budDesc);
        mAuth=FirebaseAuth.getInstance();

        pr=findViewById(R.id.placeReqBud);
        pr.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final DatabaseReference abc= FirebaseDatabase.getInstance().getReference();
                abc.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        String ggg=snapshot.child("Students").child(mAuth.getUid()).child("group_id").getValue().toString();
                        String gUnik = ggg + UUID.randomUUID().toString();
                        abc.child("Budget").child(gUnik).child("group_id").setValue(ggg);
                        abc.child("Budget").child(gUnik).child("budget").setValue(bud.getText().toString());
                        abc.child("Budget").child(gUnik).child("budDescription").setValue(budDesc.getText().toString());
                        abc.child("Budget").child(gUnik).child("status").setValue("N");
                        Intent backToHome=new Intent(Request_Budget.this,Home_Page.class);
                        startActivity(backToHome);
                        finish();
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });
            }
        });

        bb=findViewById(R.id.backBtn);
        bb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent ToHome=new Intent(Request_Budget.this,Home_Page.class);
                startActivity(ToHome);
            }
        });
    }
    @Override
    public void onBackPressed() {
        Intent ToHome=new Intent(Request_Budget.this,Home_Page.class);
        startActivity(ToHome);
    }
}
