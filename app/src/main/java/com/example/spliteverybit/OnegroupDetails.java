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
        String s1= (String) intent.getSerializableExtra("group");
        d1= FirebaseDatabase.getInstance().getReference("Groups").child(s1);
        d1.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for(DataSnapshot d:dataSnapshot.getChildren())
                {
                    String s2=d.child("id").getValue().toString();
                    SharedPreferences sharedPreferences=getSharedPreferences(SHARED_PREF,MODE_PRIVATE);
                    String amount_take=sharedPreferences.getString(s2,"1");
                    if(Integer.parseInt(amount_take)>0)
                    {
                        v2.add(s2);
                    }
                    else
                    {
                        v1.add(s2);
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }

        });


            Integer i=0;
        Integer j=0;
        Integer l1=v1.size();
        Integer l2=v2.size();
        while(i!=l1)
        {
            SharedPreferences sharedPreferences=getSharedPreferences(SHARED_PREF,MODE_PRIVATE);
            String amount_take=sharedPreferences.getString(v1.get(i),"1");
            Integer x1=Integer.parseInt(amount_take);
            x1=-1*x1;
            SharedPreferences.Editor editor=sharedPreferences.edit();
            editor.putString(v1.get(i), String.valueOf(x1));
            i++;
        }
        i=0;
        ArrayList<Pair<Pair<String,String>,Integer>>v3=new ArrayList<Pair<Pair<String,String>,Integer>>();
        Pair<Pair<String,String>,Integer>p1;

        while(i!=l1 && j!=l2)
        {
            SharedPreferences sharedPreferences=getSharedPreferences(SHARED_PREF,MODE_PRIVATE);
            String amount_take=sharedPreferences.getString(v1.get(i),"1");
            Integer x=Integer.parseInt(amount_take);
            String amount_take1=sharedPreferences.getString(v2.get(j),"1");
            Integer y=Integer.parseInt(amount_take1);
            if(x>y)
            {
                p1= new Pair(new Pair(v1.get(i),v2.get(j)),y);
                v3.add(p1);
                j++;
                SharedPreferences.Editor editor=sharedPreferences.edit();
                editor.putString(v1.get(i), String.valueOf(x-y));
            }
            else
            {
                p1= new Pair(new Pair(v1.get(i),v2.get(j)),x);
                v3.add(p1);
                i++;
                SharedPreferences.Editor editor=sharedPreferences.edit();
                editor.putString(v2.get(j), String.valueOf(y-x));
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
}
