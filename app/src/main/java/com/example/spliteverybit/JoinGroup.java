package com.example.spliteverybit;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Pair;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class JoinGroup extends AppCompatActivity {
private EditText group;
boolean flag=false;
FirebaseUser user;
    String a,b;
DatabaseReference ref,d1,ref1;
ArrayList<Pair<String,Integer>> name;
    ArrayList<String> x;
String name1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        user= FirebaseAuth.getInstance().getCurrentUser();
        String id=user.getUid();
        setContentView(R.layout.activity_join_group);
        Button joingroup=(Button)findViewById(R.id.join_group);
        group=(EditText)findViewById(R.id.groupname);
        d1 = FirebaseDatabase.getInstance().getReference().child("Users").child(id);
        x=new ArrayList<String>();
        d1.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                name1 = dataSnapshot.child("name").getValue().toString();
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
       ref= FirebaseDatabase.getInstance().getReference("Groups");

        joingroup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String s1=group.getText().toString();
               /* ref.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        String s2=dataSnapshot.getKey();
                        if(s2==s1) {
                            flag = true;
                            ref1= FirebaseDatabase.getInstance().getReference("Groups").child(s1);
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });
                if(!flag)
                {
                    Toast.makeText(JoinGroup.this, "No group found", Toast.LENGTH_SHORT).show();
                }*/
              //  else
               // {
                ref1= FirebaseDatabase.getInstance().getReference("Groups").child(s1);

                    ref1.addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                            for(DataSnapshot dss:dataSnapshot.getChildren())
                            {
                                    String p1=dss.getValue().toString();
                                    x.add(p1);
                            }
                        }
                        @Override
                        public void onCancelled(@NonNull DatabaseError databaseError) {

                        }
                    });

                    x.add(name1);
                    Group_information form1 = new Group_information(x);
                    ref1.setValue(form1);
                    Toast.makeText(JoinGroup.this, "Group Joined",
                            Toast.LENGTH_SHORT).show();
              //  }

            }



        });
    }
}
