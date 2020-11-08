package com.example.student_app;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

public class Role_Skill extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    private Spinner sp;
    private EditText et;
    private Button nxt;
    String choice="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_role__skill);
        sp=findViewById(R.id.role);

        ArrayAdapter<CharSequence> adp = ArrayAdapter.createFromResource(this,R.array.role,android.R.layout.simple_spinner_item);
        adp.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sp.setAdapter(adp);
        sp.setOnItemSelectedListener(this);

        nxt=findViewById(R.id.roleNextBtn);
        et= findViewById(R.id.Expertise);
        nxt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String txt_exp= et.getText().toString();
                if(TextUtils.isEmpty(txt_exp) || TextUtils.isEmpty(choice))
                {
                    Toast.makeText(Role_Skill.this, "Empty Credentials", Toast.LENGTH_SHORT).show();
                }
                else {
                    if (choice.equals("Leader")) {
                        Intent select_team = new Intent(Role_Skill.this, Select_Team.class);
                        startActivity(select_team);
                        finish();
                    } else {
                        Intent VeetukuPoo = new Intent(Role_Skill.this, Home_Page.class);
                        startActivity(VeetukuPoo);
                        finish();
                    }
                }

            }
        });


    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
         choice = parent.getItemAtPosition(position).toString();
        Toast.makeText(getApplicationContext(),choice,Toast.LENGTH_LONG).show();
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}
