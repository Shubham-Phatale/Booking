package com.example.booking;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

public class HomeFragment extends Fragment {

    TextView cityname;
    Button Searchbtn;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_home,container,false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        cityname = view.findViewById(R.id.city);
        Searchbtn = view.findViewById(R.id.search);

        Searchbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
             String cname = cityname.getText().toString().trim();

             if (cname.equals("")){
                 cityname.setError("Please enter City Name");
                 Toast.makeText(getActivity(), "Please Enter City Name", Toast.LENGTH_SHORT).show();
             }
             else{
                Intent i = new Intent(getActivity(),searchResults.class);
                startActivity(i);
             }
            }
        });
    }
}
