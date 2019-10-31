package com.example.booking;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {

    BottomNavigationView bottomnavi;
    Fragment selectfrag = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    bottomnavi = findViewById(R.id.bn);
    selectfrag = new HomeFragment();
    getSupportFragmentManager().beginTransaction().replace(R.id.fc,selectfrag).commit();




        bottomnavi.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
            switch (menuItem.getItemId()){
                case R.id.nav_home:
                    selectfrag = new HomeFragment();
                    break;
                case R.id.nav_book:
                    selectfrag = new HelpFragment();
                    break;
                case R.id.nav_profile:
                    selectfrag = new profileFragment();
                    break;
            }
            getSupportFragmentManager().beginTransaction().replace(R.id.fc,selectfrag).commit();

            return true;
        }
    });
    }
}
