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

public class Final_Review_Details extends AppCompatActivity {

    private TextView dt,ins,r1,r2,r3,fm;
    private Button bb;
    private DatabaseReference aaa;
    private FirebaseAuth mAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_final__review__details);
        getSupportActionBar().setTitle("Final Review Details");

        dt=findViewById(R.id.finDate);
        ins=findViewById(R.id.FinIns);
        r1=findViewById(R.id.rem1);
        r2=findViewById(R.id.rem2);
        r3=findViewById(R.id.rem3);
        fm=findViewById(R.id.FinalMarks);

        mAuth= FirebaseAuth.getInstance();
        aaa= FirebaseDatabase.getInstance().getReference();

        aaa.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                if(!(snapshot.child("Students").child(mAuth.getUid()).child("group_id").getValue().toString()).equals("NA")){


                    String gid=snapshot.child("Students").child(mAuth.getUid()).child("group_id").getValue().toString();
                    gid=gid+"Final";

                    fm.setText(snapshot.child("Review").child(gid).child("finalMark").getValue().toString());
                    r1.setText(snapshot.child("Review").child(gid).child("remarkHead").getValue().toString());
                    r2.setText(snapshot.child("Review").child(gid).child("remarkMember1").getValue().toString());
                    r3.setText(snapshot.child("Review").child(gid).child("remarkMember2").getValue().toString());
                    dt.setText(snapshot.child("Review").child(gid).child("reviewDate").getValue().toString());
                    ins.setText(snapshot.child("Review").child(gid).child("instructions").getValue().toString());
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        bb=findViewById(R.id.backBtn);
        bb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent ToHome=new Intent(Final_Review_Details.this,Home_Page.class);
                startActivity(ToHome);
            }
        });


    }

    @Override
    public void onBackPressed() {
        Intent ToHome=new Intent(Final_Review_Details.this,Home_Page.class);
        startActivity(ToHome);
    }
}
