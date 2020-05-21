package com.example.spliteverybit;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class AddTransactions extends AppCompatActivity {
EditText givename,takename,amount1;
    public static final String SHARED_PREF="shared_preferences";
    Button add1;
    String s1,s2;
DatabaseReference d1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_transactions);
        givename=(EditText)findViewById(R.id.give_name);
        takename=(EditText)findViewById(R.id.take_name);
        amount1=(EditText)findViewById(R.id.amount);
        add1=(Button)findViewById(R.id.add);
        d1=FirebaseDatabase.getInstance().getReference("Name");


        add1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String give=givename.getText().toString();
                final String take=takename.getText().toString();
                String amount2 = amount1.getText().toString();
                d1.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        s1=dataSnapshot.child(give).getValue().toString();
                        s2=dataSnapshot.child(take).getValue().toString();
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });

                SharedPreferences sharedPreferences=getSharedPreferences(SHARED_PREF,MODE_PRIVATE);
                String amount_give=sharedPreferences.getString(s1,"1");
                String amount_take=sharedPreferences.getString(s2,"1");
                Integer x=Integer.parseInt(amount_give);
                Integer y=Integer.parseInt(amount_take);
                Integer z=Integer.parseInt(amount2);
                x=x-z;
                y=y+z;
                SharedPreferences.Editor editor=sharedPreferences.edit();
                editor.putString(s1, String.valueOf(x));
                editor.putString(s2, String.valueOf(y));

            }
        });


    }
}
