package com.example.spliteverybit;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Pair;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class OnegroupDetails extends AppCompatActivity {
ArrayList<String>v1,v2;
DatabaseReference d1;
public static final String SHARED_PREF="shared_preferences";
Map<String,Integer>m=new HashMap< String,Integer>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_onegroup_details);
        v1=new ArrayList<String>();
        v2=new ArrayList<String>();
        Intent intent = getIntent();
        final String s1= (String) intent.getSerializableExtra("group");
        final Map<String,Integer>m=new HashMap<>();
        d1= FirebaseDatabase.getInstance().getReference("Transactions").child(s1);
        d1.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for(DataSnapshot d:dataSnapshot.getChildren())
                {
                    String s2=d.getKey().toString();
                    String s3=d.child("amount").getValue().toString();
                    Integer x=Integer.parseInt(s3);
                    if(x>0)
                    {
                        v2.add(s2);
                    }
                    else
                    {
                        v1.add(s2);
                    }
                    m.put(s2,x);
                }
                Integer i=0;
                Integer j=0;
                Integer l1=v1.size();
                Integer l2=v2.size();
                while(i!=l1)
                {

                    Integer x1=m.get(v1.get(i));
                    x1=-1*x1;
                    m.put(v1.get(i),x1);
                    i++;
                }
                i=0;
                ArrayList<Pair<Pair<String,String>,Integer>>v3=new ArrayList<Pair<Pair<String,String>,Integer>>();
                Pair<Pair<String,String>,Integer>p1;

                while(i!=l1 && j!=l2)
                {

                    Integer x=m.get(v1.get(i));

                    Integer y=m.get(v2.get(j));
                    if(x>y)
                    {
                        p1= new Pair(new Pair(v1.get(i),v2.get(j)),y);
                        v3.add(p1);
                        j++;

                        m.put(v1.get(i), x-y);
                    }
                    else
                    {
                        p1= new Pair(new Pair(v1.get(i),v2.get(j)),x);
                        v3.add(p1);
                        i++;
                        m.put(v2.get(j),y-x);
                    }
                }
                String s="";
                for(i=0;i<v3.size();i++)
                {
                    s=s+v3.get(i).first.first+"->"+v3.get(i).first.second+"="+ String.valueOf(v3.get(i).second) +"\n";
                }
                TextView t1=(TextView)findViewById(R.id.transactions);
                t1.setText(s);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }

        });



    }
}
