package com.example.booking;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.content.ClipData;
import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {

    BottomNavigationView bottomnavi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    bottomnavi = findViewById(R.id.bn);


    bottomnavi.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
            Fragment selectfrag = null;
            switch (menuItem.getItemId()){
                case R.id.nav_home:
                    selectfrag = new HomeFragment();
                    break;
                case R.id.nav_book:
                    selectfrag = new BookingFragment();
                    break;
                case R.id.nav_profile:
                    selectfrag = new profileFragment();
                    break;
            }
            return false;
        }
    });
    }
}
