package com.example.zarafunnel;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

import androidx.fragment.app.Fragment;

public class FragmentImageButton extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_image_button, container, false);

        ImageButton imageButton = view.findViewById(R.id.myImageButton);
        imageButton.setClickable(false);

        return view;
    }
}
