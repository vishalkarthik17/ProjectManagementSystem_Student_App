package com.example.student_app;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.UUID;

public class Select_Team extends AppCompatActivity {

    private Button proceed, reselec;
    private ListView lv;
    private TextView one[] = new TextView[3];
    private ArrayList<String> keylist = new ArrayList<>();//contains uid of all users in the listview
    private ArrayList<String> al = new ArrayList<>(); //arraylist linked to adapter
    GroupClasss grp;
    ReviewGroup rg1;
    ReviewGroup rg2;
    ReviewGroup rg3;
    private DatabaseReference abc;
    private DatabaseReference gg;
    private DatabaseReference rr;
    FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select__team);
        getSupportActionBar().setTitle("Select Team");
        mAuth = FirebaseAuth.getInstance();
        one[0] = findViewById(R.id.spinner1); //TextBox Links
        one[1] = findViewById(R.id.spinner2);
        one[2] = findViewById(R.id.spinner3);
        proceed = findViewById(R.id.toHomeBtn);
        reselec=findViewById(R.id.reselect);
        final ArrayAdapter<String> adp = new ArrayAdapter<String>(Select_Team.this, android.R.layout.simple_list_item_1, al);//List Adapter,with al set
        abc = FirebaseDatabase.getInstance().getReference().child("Students");
        gg = FirebaseDatabase.getInstance().getReference().child("Groups");
        rr=FirebaseDatabase.getInstance().getReference().child("Review");

        lv = findViewById(R.id.studentlist);
        lv.setAdapter(adp); //set adapter to listview
        String summa;



        //To display students with groupid NA in the list
        abc.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                String toDisplay = "";
                for (DataSnapshot ds : dataSnapshot.getChildren()) {
                    toDisplay = "";
                    if (!ds.getKey().contains(mAuth.getUid()) && (ds.child("group_id").getValue().toString()).equals("NA")) {
                        toDisplay = ds.child("student_name").getValue().toString();
                        toDisplay += "\n" + ds.child("student_id").getValue().toString();
                        toDisplay +=" , Skills : "+ds.child("skill").getValue().toString();
                        al.add(toDisplay);
                        keylist.add(ds.getKey());
                        adp.notifyDataSetChanged();
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        final ArrayList<String> sal = new ArrayList<>(); //store the UID of selected item from the list
        //OnClick of List
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override

            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                if (sal.size() < 3) {
                    sal.add(keylist.get((position)+sal.size()));
                    one[sal.size() - 1].setText(al.get(position));
                    al.remove(position);
                    adp.notifyDataSetChanged();

                } else
                    Toast.makeText(Select_Team.this, "Max Users Added", Toast.LENGTH_SHORT).show();


            }
        });

        proceed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (sal.size() == 3) {
                    String aaaa = UUID.randomUUID().toString();
                    abc.child(sal.get(0)).child("group_id").setValue(aaaa);
                    Log.e("sal0",sal.get(0));

                    abc.child(sal.get(1)).child("group_id").setValue(aaaa);
                    Log.e("sal1",sal.get(1));

                    abc.child(sal.get(2)).child("group_id").setValue(aaaa);
                    Log.e("sal2",sal.get(2));

                    abc.child(mAuth.getUid()).child("group_id").setValue(aaaa);
                    grp=new GroupClasss();
                    String gid=aaaa;
                    String faculid="NA";
                    String title="NA";
                    String mainar="NA";
                    String subar="NA";
                    grp.setGroupid(gid);
                    grp.setFacultyid(faculid);
                    grp.setTitle(title);
                    grp.setMainarea(mainar);
                    grp.setSubarea(subar);
                    gg.child(aaaa).setValue(grp);//Here is the problem

                    rg1=new ReviewGroup();
                    rg2=new ReviewGroup();
                    rg3=new ReviewGroup();


                        String compkey=gid+"1";
                        rg1.setReviewno("1");
                        rg1.setMarks("NA");
                        rg1.setRemark("NA");
                        rg1.setReviewDate("NA");
                        rg1.setGroupID(gid);
                        rg1.setInstructions("NA");
                        rr.child(compkey).setValue(rg1);

                    compkey=gid+"2";
                    rg2.setReviewno("2");
                    rg2.setMarks("NA");
                    rg2.setRemark("NA");
                    rg2.setReviewDate("NA");
                    rg2.setGroupID(gid);
                    rg2.setInstructions("NA");
                    rr.child(compkey).setValue(rg2);


                    compkey=gid+"3";
                    rg3.setReviewno("3");
                    rg3.setMarks("NA");
                    rg3.setRemark("NA");
                    rg3.setReviewDate("NA");
                    rg3.setGroupID(gid);
                    rg3.setInstructions("NA");
                    rr.child(compkey).setValue(rg3);


                    Intent GoToHomeDa=new Intent(Select_Team.this,Home_Page.class);
                    startActivity(GoToHomeDa);


                } else {
                    Toast.makeText(Select_Team.this, "Not Sufficient Number of Users", Toast.LENGTH_SHORT).show();
                }


            }
        });


        //reselec.findViewById(R.id.reselect);
        reselec.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent reload=new Intent(Select_Team.this,Select_Team.class);
                startActivity(reload);
            }
        });
    }
}
