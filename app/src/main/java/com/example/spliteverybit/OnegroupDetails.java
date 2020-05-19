package com.example.spliteverybit;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Pair;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Map;

public class OnegroupDetails extends AppCompatActivity {
ArrayList<String>v1,v2;
Map<String,Integer>m;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_onegroup_details);
        v1=new ArrayList<String>();
        v2=new ArrayList<String>();
        m.put("alay",-7);
        m.put("aanya",2);
        m.put("mummy",2);
        m.put("papa",4);
        m.put("dada",5);
        m.put("ba",-6);
        v1.add("alay");
        v1.add("ba");
        v2.add("aanya");
        v2.add("mummy");
        v2.add("dada");
        v2.add("papa");
        Integer i=0;
        Integer j=0;
        Integer l1=v1.size();
        Integer l2=v2.size();
        while(i!=l1)
        {
            m.put(v1.get(i),-1*m.get(v1.get(i)));
            i++;
        }
        i=0;
        ArrayList<Pair<Pair<String,String>,Integer>>v3=new ArrayList<Pair<Pair<String,String>,Integer>>();
        Pair<Pair<String,String>,Integer>p1;

        while(i!=l1 && j!=l2)
        {
            int x=m.get(v1.get(i));
            int y=m.get(v2.get(j));
            if(x>y)
            {
                p1= new Pair(new Pair(v1.get(i),v2.get(j)),y);
                v3.add(p1);
                j++;
                m.put(v1.get(i),m.get(v1.get(i))-y);
            }
            else
            {
                p1= new Pair(new Pair(v1.get(i),v2.get(j)),x);
                v3.add(p1);
                i++;
                m.put(v2.get(j),m.get(v2.get(j))-x);
            }
        }
        String s="";
        for(i=0;i<v3.size();i++)
        {
            s=v3.get(i).first.first+"->"+v3.get(i).first.second+"="+ String.valueOf(v3.get(i).second) +"\n";
        }
        TextView t1=(TextView)findViewById(R.id.transactions);
        t1.setText(s);
    }
}
