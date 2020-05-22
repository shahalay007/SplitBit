package com.example.spliteverybit;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
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

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class AddGroup extends AppCompatActivity {
private EditText group_name;
private DatabaseReference d1,ref,d2,d3;
String name1;
FirebaseUser user;
ArrayList<Group_information> x;
    public static final String SHARED_PREF="shared_preferences";
    public static final String TEXT="text";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_group);
        Button add=(Button)findViewById(R.id.add);

        group_name=(EditText)findViewById(R.id.groupname);
        user= FirebaseAuth.getInstance().getCurrentUser();
        final String id=user.getUid();

        ref = FirebaseDatabase.getInstance().getReference().child("Users").child(id);

        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                name1 = dataSnapshot.child("name").getValue().toString();
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String s1=group_name.getText().toString();

                d1= FirebaseDatabase.getInstance().getReference("Groups").child(s1);
                d2 = FirebaseDatabase.getInstance().getReference().child("Transactions").child(s1).child(user.getUid());
                DatabaseReference reg2 = d1.push();
                DatabaseReference reg3=d2.push();
                String key1=d2.push().getKey().toString();
                Map<Object,String> mp =new HashMap<>();
                Map<Object,String>mp1=new HashMap<>();

                mp.put("id",user.getUid().toString());
                mp1.put("amount".toString(),"0");
                reg2.setValue(mp).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if(task.isComplete()){
                            Toast.makeText(AddGroup.this, "Successfully added", Toast.LENGTH_SHORT).show();
                        }else{
                            Toast.makeText(AddGroup.this, task.getException().toString(), Toast.LENGTH_SHORT).show();
                        }
                    }
                });
                d2.setValue(mp1).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if(task.isComplete()){
                            //Toast.makeText(AddGroup.this, "Successfully added", Toast.LENGTH_SHORT).show();
                        }else{
                            Toast.makeText(AddGroup.this, task.getException().toString(), Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        });
    }

}