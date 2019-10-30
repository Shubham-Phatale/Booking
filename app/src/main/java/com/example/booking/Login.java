package com.example.booking;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.booking.Database.DBHelper;

public class Login extends AppCompatActivity {

    EditText etemail,etpass;
    Button btn;
    DBHelper db;
    CheckBox cb;
    TextView forgetpass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);

        db = new DBHelper(this);
        etemail = findViewById(R.id.email);
        etpass = findViewById(R.id.pass);
        btn = findViewById(R.id.logbtn);
        cb = findViewById(R.id.check);
        forgetpass = findViewById(R.id.forget);

        SharedPreferences sharedPreferences;
        final SharedPreferences.Editor editor;
        sharedPreferences= getSharedPreferences("Shub" , MODE_PRIVATE);
        if(sharedPreferences.getString("signedin", "").equals("true")){
            Intent i = new Intent(Login.this, MainActivity.class);
            startActivity(i);
        }
        editor = sharedPreferences.edit();

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = etemail.getText().toString().trim();
                String Pass = etpass.getText().toString().trim();
                Boolean checkemailpass = db.emailpass(email,Pass);

                if (checkemailpass){
                    Intent i = new Intent(Login.this,MainActivity.class);
                    startActivity(i);
                    finish();
                    if (cb.isChecked()){
                        editor.putString("signedin", "true");
                        editor.putString("emailid",email);
                        editor.putString("pass",Pass);
                        editor.apply();
                    }
                    Toast.makeText(getApplicationContext(), "Sucessfully Logged in", Toast.LENGTH_SHORT).show();
                }
                else{
                    Toast.makeText(getApplicationContext(), "Wrong Email or Password", Toast.LENGTH_SHORT).show();
                }
            }
        });
        if (sharedPreferences.contains("email")){
            etemail.setText(sharedPreferences.getString("emailid",""));
        }
        if (sharedPreferences.contains("Pass")){
            etpass.setText(sharedPreferences.getString("pass",""));
        }
    }
}
