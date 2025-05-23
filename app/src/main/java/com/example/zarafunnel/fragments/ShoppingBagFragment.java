package com.example.zarafunnel.fragments;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.zarafunnel.R;
import com.example.zarafunnel.screens.ShoppingCartActivity;

public class ShoppingBagFragment extends Fragment {

    public ShoppingBagFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_shopping_bag, container, false);

        Button cartButton = rootView.findViewById(R.id.cart_button);

        cartButton.setOnClickListener(v -> {
           openCartActivity();
        });

        return rootView;
    }

    private void openCartActivity() {
        Intent intent = new Intent(getActivity(), ShoppingCartActivity.class);
        startActivity(intent);
    }
}
