package com.example.booking;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

public class Result extends AppCompatActivity {

    LinearLayout b1,b2;
    TextView s1,s2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.result);

        b1 = findViewById(R.id.bus1);
        b2 = findViewById(R.id.bus2);
        s1 = findViewById(R.id.seat1);
        s2 = findViewById(R.id.seat2);

        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(Result.this , selectseat.class);
                startActivity(i);
            }
        });
        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(Result.this, selectseat.class);
                startActivity(i);
            }
        });
    }
}
