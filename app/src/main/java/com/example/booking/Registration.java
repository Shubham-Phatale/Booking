package com.example.booking;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.booking.Database.DBHelper;

public class Registration extends AppCompatActivity {
    DBHelper db;
    EditText edtname,edtemail,edtage,edtgender,edtpass,edtcnfpass;
    Button regbtn,logbtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.registration);

        db = new DBHelper(this);
        edtname = findViewById(R.id.name);
        edtemail = findViewById(R.id.email);
        edtage = findViewById(R.id.age);
        edtgender = findViewById(R.id.Mobno);
        edtpass = findViewById(R.id.password);
        edtcnfpass = findViewById(R.id.cnfpass);
        regbtn = findViewById(R.id.btnreg);
        logbtn = findViewById(R.id.btnlog);

        regbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = edtname.getText().toString().trim();
                String email = edtemail.getText().toString().trim();
                String age = edtage.getText().toString().trim();
                String gender = edtgender.getText().toString().trim();
                String pass = edtpass.getText().toString().trim();
                String cnfpass = edtcnfpass.getText().toString().trim();

                if(name.equals("")||email.equals("")||age.equals("")||gender.equals("")||pass.equals("")||cnfpass.equals("")){
                    Toast.makeText(getApplicationContext(), "All Fields are Required", Toast.LENGTH_SHORT).show();
                }
                else if (!pass.equals(cnfpass)){
                    Toast.makeText(getApplicationContext(), "Passord Donot Match", Toast.LENGTH_SHORT).show();
                }
                else{
                    Boolean check = db.checkid(email);
                    if (check == true){
                        Toast.makeText(getApplicationContext(), "ID already Exists", Toast.LENGTH_SHORT).show();
                    }
                    else{
                        Boolean insert = db.insert(name,email,age,gender,pass);
                        if (insert == true){
                            Toast.makeText(getApplicationContext(), "Registered Sucessfully", Toast.LENGTH_SHORT).show();
                            Log.i("SHUB","Values Entered Sucessfully");
                        }
                    }
                }
            }
        });
        logbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(Registration.this, Login.class);
                startActivity(i);
                finish();
            }
        });

    }
}
