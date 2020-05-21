package com.example.spliteverybit;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class ShowMembers extends AppCompatActivity {
private EditText show_text;
private  Button show;
private String ans;
private  TextView t1;
DatabaseReference d1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_members);
        show=(Button)findViewById(R.id.show1);
        t1=(TextView)findViewById(R.id.textView5);
        show_text=(EditText)findViewById(R.id.g);
        ans="";
        show.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String s1=show_text.getText().toString();
                d1= FirebaseDatabase.getInstance().getReference("Groups").child(s1);
                d1.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        for(DataSnapshot d:dataSnapshot.getChildren())
                        {
                            String s2=d.child("id").getValue().toString();
                            ans=ans+s2+"\n";
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }

                });
                t1.setText(ans);
            }
        });
    }

}
