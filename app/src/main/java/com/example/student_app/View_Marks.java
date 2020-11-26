package com.example.student_app;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class View_Marks extends AppCompatActivity {
    private TextView m1,r1,m2,r2,m3,r3;
    private Button bb;
    private DatabaseReference aaa;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view__marks);

        getSupportActionBar().setTitle("View Marks");

        m1=findViewById(R.id.mark1);
        r1=findViewById(R.id.remark1);

        m2=findViewById(R.id.mark2);
        r2=findViewById(R.id.remark2);

        m3=findViewById(R.id.mark3);
        r3=findViewById(R.id.remark3);

        mAuth = FirebaseAuth.getInstance();
        aaa = FirebaseDatabase.getInstance().getReference();

        aaa.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                String gid=snapshot.child("Students").child(mAuth.getUid()).child("group_id").getValue().toString();
                String rr1=gid+"1";
                String rr2=gid+"2";
                String rr3=gid+"3";

                m1.setText(snapshot.child("Review").child(rr1).child("marks").getValue().toString());
                r1.setText(snapshot.child("Review").child(rr1).child("remark").getValue().toString());

                m2.setText(snapshot.child("Review").child(rr2).child("marks").getValue().toString());
                r2.setText(snapshot.child("Review").child(rr2).child("remark").getValue().toString());

                m3.setText(snapshot.child("Review").child(rr3).child("marks").getValue().toString());
                r3.setText(snapshot.child("Review").child(rr3).child("remark").getValue().toString());
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

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
