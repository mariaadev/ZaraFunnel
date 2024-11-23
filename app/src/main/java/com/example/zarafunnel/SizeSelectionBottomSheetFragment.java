package com.example.zarafunnel;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.google.android.material.bottomsheet.BottomSheetDialogFragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.google.android.material.bottomsheet.BottomSheetDialogFragment;

public class SizeSelectionBottomSheetFragment extends BottomSheetDialogFragment {

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.bottom_sheet_size, container, false);

        // Obtener los botones de talla
        Button sizeSmallButton = view.findViewById(R.id.sizeSmall);
        Button sizeMediumButton = view.findViewById(R.id.sizeMedium);
        Button sizeLargeButton = view.findViewById(R.id.sizeLarge);

        // Definir un OnClickListener para cada botón
        sizeSmallButton.setOnClickListener(v -> {
            showSelectedSizeToast("S");
        });

        sizeMediumButton.setOnClickListener(v -> {
            showSelectedSizeToast("M");
        });

        sizeLargeButton.setOnClickListener(v -> {
            showSelectedSizeToast("L");
        });

        // Botón para confirmar la selección
        Button selectSizeButton = view.findViewById(R.id.selectSizeButton);
        selectSizeButton.setOnClickListener(v -> {
            Toast.makeText(getActivity(), "Talla seleccionada", Toast.LENGTH_SHORT).show();
            dismiss();
        });

        return view;
    }

    // Método auxiliar para mostrar el tamaño seleccionado
    private void showSelectedSizeToast(String size) {
        Toast.makeText(getActivity(), "Talla seleccionada: " + size, Toast.LENGTH_SHORT).show();
    }
}
