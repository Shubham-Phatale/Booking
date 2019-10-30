package com.example.booking;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationManager;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.os.Looper;
import android.provider.MediaStore;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.FileProvider;
import androidx.fragment.app.Fragment;

import com.example.booking.Database.DBHelper;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationCallback;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationResult;
import com.google.android.gms.location.LocationServices;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class profileFragment extends Fragment {

    FusedLocationProviderClient client;
    Location location;
    LocationCallback callback;
    LocationRequest request;
    private LocationManager locationManager;
    TextView txtloc;
    DBHelper db;
    TextView name,age,number;
    ImageView imageView;
    Uri photoURI;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_profile,container,false);
        db = new DBHelper(getContext());
        txtloc = v.findViewById(R.id.userlocation) ;
        name = v.findViewById(R.id.username);
        age = v.findViewById(R.id.userage);
        imageView = v.findViewById(R.id.image);

        number = v.findViewById(R.id.usermobno);
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


        SharedPreferences sp = getActivity().getSharedPreferences("Shub",Context.MODE_PRIVATE);

        ArrayList values;
        values = db.getuserinfo(sp.getString("emailid", ""));
        if(values.size() != 0) {
            for (int i = 0; i < values.size(); i = i + 3) {
                name.setText((String) values.get(i));
                age.setText((String) values.get(i+1));
                number.setText((String) values.get(i+2));
            }
        }
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dispatchTakePictureIntent();
            }
        });

        return v ;
    }

    private void convertAdress(double latitude, double longitude) {

        Geocoder geocoder = new Geocoder(getActivity());
        try {
            List<Address> add = geocoder.getFromLocation(latitude, longitude, 1);
            Address ad = add.get(0);
            String address = ad.getAddressLine(0);
            txtloc.setText(address);
        } catch (Exception e) {

        }
    }
    private File createImageFile() throws IOException {
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        String imageFileName = "JPEG_" + timeStamp + "_";
        File storageDir = getActivity().getExternalFilesDir(Environment.DIRECTORY_PICTURES);
        File image = File.createTempFile(imageFileName, ".jpg", storageDir);
        String currentPhotoPath = image.getAbsolutePath();
        return image;
    }

    private void dispatchTakePictureIntent() {
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if (takePictureIntent.resolveActivity(getContext().getPackageManager()) != null) {
            File photoFile = null;
            try {
                photoFile = createImageFile();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
            if (photoFile != null) {
                photoURI = FileProvider.getUriForFile(getContext(), "com.example.android.file_provider", photoFile);
                takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, photoURI);
                startActivityForResult(takePictureIntent, 0);
            }
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        //Bundle extras = data.getExtras();
        //Bitmap imageBitmap = (Bitmap) extras.get("data");
        imageView.setImageURI(photoURI);
        try {
            Bitmap bitmap = MediaStore.Images.Media.getBitmap(getActivity().getContentResolver(), photoURI);
        }
        catch (IOException e){
            e.printStackTrace();
        }
    }
}
