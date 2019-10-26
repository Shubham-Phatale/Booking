package com.example.booking;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.os.Looper;
import android.provider.MediaStore;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.booking.Database.DBHelper;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationCallback;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationResult;
import com.google.android.gms.location.LocationServices;

import java.util.ArrayList;
import java.util.List;

public class profileFragment extends Fragment {

    FusedLocationProviderClient client;
    Location location;
    LocationCallback callback;
    LocationRequest request;
    private LocationManager locationManager;
    TextView txtloc;
    DBHelper db;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_profile,container,false);
        db = new DBHelper(getContext());
        txtloc = v.findViewById(R.id.userlocation) ;
        client = LocationServices.getFusedLocationProviderClient(getActivity());
        callback = new LocationCallback(){

            @Override
            public void onLocationResult(LocationResult locationResult) {
                location = locationResult.getLocations().get(0);
                Log.i("SHUB","Locarion =>" + location.getLatitude() + "," + location.getLatitude());
                double lat = location.getLatitude();
                double longi = location.getLongitude();
                txtloc.setText("Location:\n\n" + lat + "," +longi);

                convertAdress(location.getLatitude(),location.getLongitude());
                super.onLocationResult(locationResult);
            }

        };
        LocationRequest request = new LocationRequest();
        request.setFastestInterval(3000);
        request.setInterval(5000);
        request.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);

        client.requestLocationUpdates(request,callback, Looper.myLooper());

        return v ;
    }

    private void convertAdress(double latitude, double longitude) {

        Geocoder geocoder = new Geocoder(getActivity());
        try {
            List<Address> add = geocoder.getFromLocation(latitude,longitude,1);
            Address ad = add.get(0);
            String address = ad.getAddressLine(0);
            txtloc.setText(address);
        }catch (Exception e){

        }
    }


}

