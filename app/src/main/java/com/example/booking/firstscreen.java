package com.example.booking;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class firstscreen extends AppCompatActivity {
    Button btnlog,btnreg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.firstscreen);

        btnlog = findViewById(R.id.logbtn);
        btnreg = findViewById(R.id.regbtn);

        btnlog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i =new Intent(firstscreen.this, Login.class);
                startActivity(i);
                finish();
            }
        });

        btnreg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(firstscreen.this, Registration.class);
                startActivity(i);
                finish();
            }
        });

    }
}
