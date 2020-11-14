package com.example.student_app;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
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
    private DatabaseReference abc;
    private DatabaseReference gg;
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

        lv = findViewById(R.id.studentlist);
        lv.setAdapter(adp); //set adapter to listview
        String summa;

        final ArrayList<String> sal = new ArrayList<>(); //store the UID of selected item from the list

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

        //OnClick of List
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override

            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                if (sal.size() < 3) {
                    sal.add(keylist.get(position));
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
                    abc.child(sal.get(1)).child("group_id").setValue(aaaa);
                    abc.child(sal.get(2)).child("group_id").setValue(aaaa);
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
