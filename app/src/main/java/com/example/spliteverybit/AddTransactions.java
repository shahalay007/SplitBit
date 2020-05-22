package com.example.spliteverybit;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;
import java.util.Map;

public class AddTransactions extends AppCompatActivity {
EditText givename,takename,amount1,groupname;
    public static final String SHARED_PREF="shared_preferences";
    Button add1;
    String s1,s2;
    Integer x,y;
DatabaseReference d1,d2,d3,ref1,ref2,d0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_transactions);
        givename=(EditText)findViewById(R.id.give_name);
        takename=(EditText)findViewById(R.id.take_name);
        groupname=(EditText)findViewById(R.id.gname);
        amount1=(EditText)findViewById(R.id.amount);
        add1=(Button)findViewById(R.id.add);
        d0=FirebaseDatabase.getInstance().getReference("Name");
        d1=FirebaseDatabase.getInstance().getReference("Name");
        d2=FirebaseDatabase.getInstance().getReference("Transactions");
        d3=FirebaseDatabase.getInstance().getReference("Transactions");
        add1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String give=givename.getText().toString();
                final String take=takename.getText().toString();
                final String amount2 = amount1.getText().toString();
                final String gr_name=groupname.getText().toString();
                d0.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        s1=dataSnapshot.child(give).getValue().toString();
                        s2=dataSnapshot.child(take).getValue().toString();
                        DatabaseReference reg=d2.child(gr_name).child(s1);
                        DatabaseReference reg1=d3.child(gr_name).child(s2);

                        reg.addListenerForSingleValueEvent(new ValueEventListener() {
                            @Override
                            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                                x=Integer.parseInt(dataSnapshot.child("amount").getValue().toString());
                                x=x-Integer.parseInt(amount2);
                                Map<Object,String>mp1=new HashMap<>();
                                mp1.put("amount",Integer.toString(x));
                                ref1 = FirebaseDatabase.getInstance().getReference().child("Transactions").child(gr_name).child(s1);
                                ref1.setValue(mp1).addOnCompleteListener(new OnCompleteListener<Void>() {
                                    @Override
                                    public void onComplete(@NonNull Task<Void> task) {
                                        if(task.isComplete()){
                                            //Toast.makeText(AddGroup.this, "Successfully added", Toast.LENGTH_SHORT).show();
                                        }else{
                                            // Toast.makeText(AddGroup.this, task.getException().toString(), Toast.LENGTH_SHORT).show();
                                        }
                                    }
                                });
                            }

                            @Override
                            public void onCancelled(@NonNull DatabaseError databaseError) {

                            }
                        });
                        reg1.addListenerForSingleValueEvent(new ValueEventListener() {
                            @Override
                            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                                y=Integer.parseInt(dataSnapshot.child("amount").getValue().toString());
                                y=y+Integer.parseInt(amount2);
                                Map<Object,String>mp2=new HashMap<>();
                                mp2.put("amount",Integer.toString(y));
                                ref2 = FirebaseDatabase.getInstance().getReference().child("Transactions").child(gr_name).child(s2);
                                ref2.setValue(mp2).addOnCompleteListener(new OnCompleteListener<Void>() {
                                    @Override
                                    public void onComplete(@NonNull Task<Void> task) {
                                        if(task.isComplete()){
                                            //Toast.makeText(AddGroup.this, "Successfully added", Toast.LENGTH_SHORT).show();
                                        }else{
                                            // Toast.makeText(AddGroup.this, task.getException().toString(), Toast.LENGTH_SHORT).show();
                                        }
                                    }
                                });

                            }

                            @Override
                            public void onCancelled(@NonNull DatabaseError databaseError) {

                            }
                        });


                    }
                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });






            }
        });


    }
}
