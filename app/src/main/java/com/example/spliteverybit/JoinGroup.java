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
String s1;
    String a,b;
DatabaseReference ref,d1,ref1;
    ArrayList<Group_information> x;
String name1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        user= FirebaseAuth.getInstance().getCurrentUser();
        String id=user.getUid();
        setContentView(R.layout.activity_join_group);
        Button joingroup=(Button)findViewById(R.id.join_group);
        group=(EditText)findViewById(R.id.group_name);
        d1 = FirebaseDatabase.getInstance().getReference().child("Users").child(id);
        x=new ArrayList<Group_information>();
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
                 s1=group.getText().toString();
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
                ref1.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        for(DataSnapshot d:dataSnapshot.getChildren())
                        {
                            String p1=d.getValue().toString();
                            Group_information g=new Group_information(p1);
                            x.add(g);
                        }
                        Group_information g=new Group_information(name1);
                        x.add(g);
                        ref1.setValue(x);
                        Toast.makeText(JoinGroup.this, "Group Joined",
                                Toast.LENGTH_SHORT).show();

                    }
                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }

                });



              //  }

            }



        });


    }
}
