package com.example.spliteverybit;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Pair;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class JoinGroup extends AppCompatActivity {
private EditText group;
boolean flag=false;
DatabaseReference d2;
FirebaseUser user;
    public static final String SHARED_PREF="shared_preferences";
String s1;
    String a,b;
DatabaseReference ref,d1,ref1;
    ArrayList<Group_information> x;
String name1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        user= FirebaseAuth.getInstance().getCurrentUser();
        final String id=user.getUid();
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

                ref1= FirebaseDatabase.getInstance().getReference("Groups").child(s1);
                DatabaseReference reg2 = ref1.push();
                d2 = FirebaseDatabase.getInstance().getReference().child("Transactions").child(s1).child(user.getUid());
                DatabaseReference reg3=d2.push();
                Map<Object,String> mp =new HashMap<>();
                Map<Object,String> mp1 =new HashMap<>();
                mp.put("id",user.getUid().toString());
                mp1.put("amount".toString(),"0");
                reg2.setValue(mp).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if(task.isComplete()){
                            Toast.makeText(JoinGroup.this, "Successfully added", Toast.LENGTH_SHORT).show();
                        }else{
                            Toast.makeText(JoinGroup.this, task.getException().toString(), Toast.LENGTH_SHORT).show();
                        }
                    }
                });
                d2.setValue(mp1).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if(task.isComplete()){
                           // Toast.makeText(JoinGroup.this, "Successfully added", Toast.LENGTH_SHORT).show();
                        }else{
                            Toast.makeText(JoinGroup.this, task.getException().toString(), Toast.LENGTH_SHORT).show();
                        }
                    }
                });
                /*ref1.addListenerForSingleValueEvent(new ValueEventListener() {
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

                });*/



                //  }

            }



        });


    }
}
