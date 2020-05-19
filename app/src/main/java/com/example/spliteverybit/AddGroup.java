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
import java.util.Vector;

public class AddGroup extends AppCompatActivity {
private EditText group_name;
private DatabaseReference d1,ref;
String name1;
FirebaseUser user;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_group);
        Button add=(Button)findViewById(R.id.add);

        group_name=(EditText)findViewById(R.id.groupname);
        user= FirebaseAuth.getInstance().getCurrentUser();
        String id=user.getUid();
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
                d1= FirebaseDatabase.getInstance().getReference("Groups");
                ArrayList<String> name=new ArrayList<String>();
                name.add(name1);
                Group_information form1 = new Group_information(name);
                d1.child(s1).setValue(form1);
                Toast.makeText(AddGroup.this, "Group Added",
                        Toast.LENGTH_SHORT).show();
            }
        });
    }
}