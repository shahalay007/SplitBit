package com.example.spliteverybit;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class GroupStatus extends AppCompatActivity {
    public EditText group;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_group_status);
        Button b1=(Button)findViewById(R.id.see_details);
        group=(EditText)findViewById(R.id.group_search);
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String s1=group.getText().toString();
                Intent intent = new Intent(GroupStatus.this,OnegroupDetails.class);
                intent.putExtra("group",s1);
                startActivity(intent);
            }
        });
    }
}
