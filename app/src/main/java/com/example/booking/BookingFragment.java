package com.example.booking;

import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.MediaController;
import android.widget.VideoView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class BookingFragment extends Fragment {


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        VideoView videov;
        View rootView = inflater.inflate(R.layout.activity_main2, container, false);
        videov = rootView.findViewById(R.id.videoView);
        MediaController mc = new MediaController(getContext());
        String path = "android.resource://" + getContext().getPackageName() + "/" + R.raw.video;
        videov.setVideoURI(Uri.parse(path));
        videov.setMediaController(mc);
        return rootView;
    }
}
