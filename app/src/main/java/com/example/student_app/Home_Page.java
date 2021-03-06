package com.example.student_app;

import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import android.util.Log;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.core.view.GravityCompat;
import androidx.appcompat.app.ActionBarDrawerToggle;

import android.view.MenuItem;

import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import androidx.drawerlayout.widget.DrawerLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.Menu;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.UUID;

public class Home_Page extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private TextView namee;
    private TextView stuid;
    private TextView course;
    private Button editt;
    private TextView gtitle,gmainarea,gsubarea,gfaculty;
    private FirebaseAuth mAuth;
    private DatabaseReference abc;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home__page);
        Toolbar toolbar = findViewById(R.id.toolbar);
        mAuth=FirebaseAuth.getInstance();

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
        navigationView.setNavigationItemSelectedListener(this);
        namee=findViewById(R.id.home_name);
        stuid=findViewById(R.id.home_studentid);
        course=findViewById(R.id.home_course);
        editt=findViewById(R.id.edit_group_details);
        gtitle=findViewById(R.id.home_title);
        gmainarea=findViewById(R.id.home_mainarea);
        gsubarea=findViewById(R.id.home_subarea);
        gfaculty=findViewById(R.id.home_facultyid);

        abc= FirebaseDatabase.getInstance().getReference();
        abc.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                //Display the Student Details from db
                namee.setText(dataSnapshot.child("Students").child(mAuth.getUid()).child("student_name").getValue().toString());
                stuid.setText(dataSnapshot.child("Students").child(mAuth.getUid()).child("student_id").getValue().toString());
                course.setText(dataSnapshot.child("Students").child(mAuth.getUid()).child("course").getValue().toString());


                //if students group id is not NA then display the groupid from db to TextViews
                if( !((dataSnapshot.child("Students").child(mAuth.getUid()).child("group_id").getValue().toString()).equals("NA")) )
                {
                    String find=dataSnapshot.child("Students").child(mAuth.getUid()).child("group_id").getValue().toString();
                    gtitle.setText(dataSnapshot.child("Groups").child(find).child("title").getValue().toString());
                    gmainarea.setText(dataSnapshot.child("Groups").child(find).child("mainarea").getValue().toString());
                    gsubarea.setText(dataSnapshot.child("Groups").child(find).child("subarea").getValue().toString());
                    gfaculty.setText(dataSnapshot.child("Groups").child(find).child("facultyid").getValue().toString());

                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });





        //Edit Group Details Button OnClick
        editt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                abc.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        //Only Accisible to Group Leader
                        if((dataSnapshot.child("Students").child(mAuth.getUid()).child("role").getValue().toString()).equals("Leader")){
                            Intent editgroup=new Intent(Home_Page.this,Edit_Group.class);
                            startActivity(editgroup);
                        }
                        else
                        {
                            Toast.makeText(Home_Page.this, "Only Leader Can Edit", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });

            }
        });
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.home__page, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }



    //Onclick on the Drawer elements
    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_home) {
            // Handle the camera action
        } else if (id == R.id.nav_nxtReview) {
            Intent ToHome=new Intent(Home_Page.this,Next_Review.class);
            startActivity(ToHome);


        } else if (id == R.id.nav_viewMarks) {
            Intent ToNextReview=new Intent(Home_Page.this,View_Marks.class);
            startActivity(ToNextReview);

        } else if (id == R.id.nav_reqRes) {
            //ToCheck Whether Leader or Member
            abc.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    if((snapshot.child("Students").child(mAuth.getUid()).child("role").getValue().toString()).equals("Leader")){
                        Intent ToRequestResources=new Intent(Home_Page.this,Request_Resources.class);
                        startActivity(ToRequestResources);
                    }
                    else{
                        Toast.makeText(Home_Page.this, "Only Leader Can Request", Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                }
            });


        } else if (id == R.id.nav_reqBud) {
            //ToCheck Whether Leader or Member
            abc.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    if((snapshot.child("Students").child(mAuth.getUid()).child("role").getValue().toString()).equals("Leader")){
                        Intent ToRequestBudget=new Intent(Home_Page.this,Request_Budget.class);
                        startActivity(ToRequestBudget);
                    }
                    else{
                        Toast.makeText(Home_Page.this, "Only Leader Can Request", Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                }
            });


        } else if (id == R.id.nav_viewApp) {
            Intent ToViewApp=new Intent(Home_Page.this,View_Approved.class);
            startActivity(ToViewApp);

        }

        else if (id == R.id.nav_viewApp2) {
            Intent ToViewApp=new Intent(Home_Page.this,View_Approved_2.class);
            startActivity(ToViewApp);

        }
        else if (id == R.id.nav_Final_Review_Details) {
            Intent ToViewApp=new Intent(Home_Page.this,Final_Review_Details.class);
            startActivity(ToViewApp);

        }
        else if (id == R.id.SignOut) {
            mAuth.signOut();
            Intent ToViewApp=new Intent(Home_Page.this,MainActivity.class);
            startActivity(ToViewApp);

        }


        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;


    }
}
