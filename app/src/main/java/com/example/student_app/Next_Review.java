package com.example.student_app;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

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

public class Next_Review extends AppCompatActivity {
    private Button bb;
    private TextView m1,d1,r1,m2,d2,r2,m3,d3,r3;
    private DatabaseReference aaa;
    private FirebaseAuth mAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_next__review);

        getSupportActionBar().setTitle("Next Review");


        m1=findViewById(R.id.reviewMark1);
        d1=findViewById(R.id.reviewDate1);
        r1=findViewById(R.id.reviewRemarks1);

        m2=findViewById(R.id.reviewMark2);
        d2=findViewById(R.id.reviewDate2);
        r2=findViewById(R.id.reviewRemarks2);

        m3=findViewById(R.id.reviewMark3);
        d3=findViewById(R.id.reviewDate3);
        r3=findViewById(R.id.reviewRemarks3);

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
                d1.setText(snapshot.child("Review").child(rr1).child("reviewDate").getValue().toString());
                r1.setText(snapshot.child("Review").child(rr1).child("remark").getValue().toString());

                m2.setText(snapshot.child("Review").child(rr2).child("marks").getValue().toString());
                d2.setText(snapshot.child("Review").child(rr2).child("reviewDate").getValue().toString());
                r2.setText(snapshot.child("Review").child(rr2).child("remark").getValue().toString());

                m3.setText(snapshot.child("Review").child(rr3).child("marks").getValue().toString());
                d3.setText(snapshot.child("Review").child(rr3).child("reviewDate").getValue().toString());
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
