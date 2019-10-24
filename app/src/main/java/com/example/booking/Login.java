package com.example.booking;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Login extends AppCompatActivity {

    EditText etemail,etpass;
    Button btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);

        etemail = findViewById(R.id.email);
        etpass = findViewById(R.id.pass);
        btn = findViewById(R.id.logbtn);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = etemail.getText().toString().trim();
                String Pass = etemail.getText().toString().trim();

                if(email.equals("")|| Pass.equals("")){
                    if (email.equals(""))
                        etemail.setError("Enter Email");
                    if (Pass.equals(""))
                        etpass.setError("Enter Password");
                    Toast.makeText(getApplicationContext(), "Fill all the details", Toast.LENGTH_SHORT).show();
                }
                else if (!Patterns.EMAIL_ADDRESS.matcher(email).matches())
                    etemail.setError("Invalid Email Id");
                else if (Pass.length()<6)
                    etpass.setError("Password should be more than 6 Characters");
                else{
                    Intent i = new Intent(Login.this,MainActivity.class);
                    startActivity(i);
                    finish();
                }
            }
        });
    }
}
