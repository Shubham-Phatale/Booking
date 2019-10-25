package com.example.booking;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.booking.Database.DBHelper;

public class Login extends AppCompatActivity {

    EditText etemail,etpass;
    Button btn;
    DBHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);

        db = new DBHelper(this);
        etemail = findViewById(R.id.email);
        etpass = findViewById(R.id.pass);
        btn = findViewById(R.id.logbtn);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = etemail.getText().toString().trim();
                String Pass = etemail.getText().toString().trim();
                Boolean checkemailpass = db.emailpass(email,Pass);

                if (checkemailpass == true){
                    Intent i = new Intent(Login.this,MainActivity.class);
                    startActivity(i);
                    finish();
                    Toast.makeText(getApplicationContext(), "Sucessfully Logged in", Toast.LENGTH_SHORT).show();
                }
                else{
                    Toast.makeText(getApplicationContext(), "Wrong Email or Password", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
