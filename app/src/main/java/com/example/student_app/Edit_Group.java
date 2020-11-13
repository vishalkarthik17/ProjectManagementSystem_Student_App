package com.example.student_app;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Edit_Group extends AppCompatActivity {

    private EditText etitle, emainarea , esubarea;
    private Button upd,bck;
    private FirebaseAuth mAuth;
    private DatabaseReference reff ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit__group);
        etitle=findViewById(R.id.edit_title);
        emainarea=findViewById(R.id.edit_mainarea);
        esubarea=findViewById(R.id.edit_subarea);
        upd=findViewById(R.id.edit_group_button);
        bck=findViewById(R.id.edit_to_home_btn);
        mAuth=FirebaseAuth.getInstance();

        upd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String t=etitle.getText().toString();
                final String ma=emainarea.getText().toString();
                final String sa=esubarea.getText().toString();

                if((!TextUtils.isEmpty(t))&&(!TextUtils.isEmpty(ma))&&(!TextUtils.isEmpty(sa)) ){
                    reff= FirebaseDatabase.getInstance().getReference();
                    Toast.makeText(Edit_Group.this, "Ulla vanchu", Toast.LENGTH_SHORT).show();
                    reff.addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                            String gidd=dataSnapshot.child("Students").child(mAuth.getUid()).child("group_id").getValue().toString();
                            reff.child("Groups").child(gidd).child("title").setValue(t);
                            reff.child("Groups").child(gidd).child("mainarea").setValue(ma);
                            reff.child("Groups").child(gidd).child("subarea").setValue(sa);
                            Intent back=new Intent(Edit_Group.this,Home_Page.class);
                            startActivity(back);
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError databaseError) {

                        }
                    });
                    //gidd=reff.child("Students").child(mAuth.getUid()).child("group_id").toString();
                   // reff.child("Groups").child(gidd).child("title").setValue(t);
                    //reff.child("Groups").child(gidd).child("mainarea").setValue(ma);
                    //reff.child("Groups").child(gidd).child("subarea").setValue(sa);
                    //Intent back=new Intent(Edit_Group.this,Home_Page.class);
                    //startActivity(back);

                }
                else
                {
                    Toast.makeText(Edit_Group.this, "Empty Info", Toast.LENGTH_SHORT).show();
                }
            }
        });

        bck.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent backtohome=new Intent(Edit_Group.this,Home_Page.class);
                startActivity(backtohome);
                finish();
            }
        });
    }
}
