package com.example.zarafunnel;

import android.content.res.ColorStateList;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;

import com.google.android.material.bottomsheet.BottomSheetDialogFragment;

public class SizeSelectionBottomSheetFragment extends BottomSheetDialogFragment {

    // Variable para almacenar la talla seleccionada
    private String selectedSize = "";
    // Obtener los botones de talla
    Button sizeSmallButton ;
    Button sizeMediumButton;
    Button sizeLargeButton ;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflar la vista del bottom sheet
        View view = inflater.inflate(R.layout.bottom_sheet_size, container, false);

        // Obtener los botones de talla
        sizeSmallButton = view.findViewById(R.id.sizeSmall);
        sizeMediumButton = view.findViewById(R.id.sizeMedium);
        sizeLargeButton = view.findViewById(R.id.sizeLarge);

        // Definir un OnClickListener para cada botón de talla
        sizeSmallButton.setOnClickListener(v -> {
            selectedSize = "S"; // Establecer la talla seleccionada
            showSelectedSizeToast("S");
            selectButton(sizeSmallButton);
        });

        sizeMediumButton.setOnClickListener(v -> {
            selectedSize = "M"; // Establecer la talla seleccionada
            showSelectedSizeToast("M");
            selectButton(sizeMediumButton);
        });

        sizeLargeButton.setOnClickListener(v -> {
            selectedSize = "L"; // Establecer la talla seleccionada
            showSelectedSizeToast("L");
            selectButton(sizeLargeButton);
        });


            // Botón para confirmar la selección
            Button selectSizeButton = view.findViewById(R.id.selectSizeButton);
            selectSizeButton.setOnClickListener(v -> {
                if (selectedSize.isEmpty()) {
                    // Si no se ha seleccionado una talla, mostrar un mensaje de error
                    Toast.makeText(getActivity(), "Por favor, selecciona una talla", Toast.LENGTH_SHORT).show();
                } else {
                    dismiss(); // Cerrar el bottom sheet
                }
            });

            return view;
        }

    // Método auxiliar para cambiar el color del botón seleccionado
    private void selectButton(Button selectedButton) {
        // Reiniciar color de todos los botones
        sizeSmallButton.setBackgroundTintList(ColorStateList.valueOf(ContextCompat.getColor(getActivity(), android.R.color.black)));       ;
        sizeMediumButton.setBackgroundTintList(ColorStateList.valueOf(ContextCompat.getColor(getActivity(), android.R.color.black)));
        sizeLargeButton.setBackgroundTintList(ColorStateList.valueOf(ContextCompat.getColor(getActivity(), android.R.color.black)));

        // Cambiar color del botón seleccionado
        selectedButton.setBackgroundTintList(ColorStateList.valueOf(ContextCompat.getColor(getActivity(), android.R.color.holo_blue_light)));  // O el color que elijas
    }

    // Método auxiliar para mostrar el tamaño seleccionado
    private void showSelectedSizeToast(String size) {
        Toast.makeText(getActivity(), "Talla seleccionada: " + size, Toast.LENGTH_SHORT).show();
    }
}
