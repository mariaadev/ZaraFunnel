package com.example.zarafunnel.fragments;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.zarafunnel.screens.MainActivity;
import com.example.zarafunnel.R;
import com.example.zarafunnel.screens.ShoppingCartActivity;
import com.example.zarafunnel.screens.InicioRegistroActivity;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class BottomNavigationViewFragment extends Fragment {
    //Configurar el BottomNavigationView
    BottomNavigationView bottomNavigationView;
    public interface NavigationListener {
        void onNavigationItemSelected(int itemId);
    }

    private NavigationListener navigationListener;


    public BottomNavigationViewFragment() {
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        if (context instanceof NavigationListener) {
            navigationListener = (NavigationListener) context;
        } else {
            throw new ClassCastException(context.toString() + " se debe implementar NavigationListener");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        //Inflar el disseny per aquest fragment
        return inflater.inflate(R.layout.fragment_bottom_navigation_view, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        //Configurar el BottomNavigationView
        bottomNavigationView = view.findViewById(R.id.bottomNavigationView);

        //Configurar l'element seleccionat per defecto
        bottomNavigationView.setSelectedItemId(R.id.home);

        //Configurar listener de navegació
        bottomNavigationView.setOnItemSelectedListener(item -> {
            if (navigationListener != null) {
                navigationListener.onNavigationItemSelected(item.getItemId());
                return true;
            }
            return false;
        });

        //Seleccionar l'ítem segons l'actividad actual
        if (getActivity() instanceof MainActivity) {
            bottomNavigationView.setSelectedItemId(R.id.home);
        } else if (getActivity() instanceof InicioRegistroActivity) {
            bottomNavigationView.setSelectedItemId(R.id.profile);
        } else if (getActivity() instanceof ShoppingCartActivity) {
            bottomNavigationView.setSelectedItemId(R.id.bag);
        }


    }



}
