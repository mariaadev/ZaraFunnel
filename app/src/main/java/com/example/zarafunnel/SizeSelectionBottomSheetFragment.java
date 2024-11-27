package com.example.zarafunnel;

import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;
import android.util.Log;
import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.core.content.res.ResourcesCompat;

import com.google.android.material.bottomsheet.BottomSheetDialogFragment;
public class SizeSelectionBottomSheetFragment extends BottomSheetDialogFragment {

    private String productName;
    private String productPrice;
    private int productImageResId;
    private String selectedSize = "";

    private Button sizeSmallButton;
    private Button sizeMediumButton;
    private Button sizeLargeButton;

    public static SizeSelectionBottomSheetFragment newInstance(String productName, String productPrice, int productImageResId) {
        SizeSelectionBottomSheetFragment fragment = new SizeSelectionBottomSheetFragment();
        Bundle args = new Bundle();
        args.putString("productName", productName);
        args.putString("productPrice", productPrice);
        args.putInt("productImageResId", productImageResId);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.bottom_sheet_size, container, false);

        // Recuperar los datos
        if (getArguments() != null) {
            productName = getArguments().getString("productName");
            productPrice = getArguments().getString("productPrice");
            productImageResId = getArguments().getInt("productImageResId");

            // Verificar los valores recibidos
            Log.d("SizeSelection", "Datos recibidos - Nombre: " + productName + ", Precio: " + productPrice + ", ID Imagen: " + productImageResId);
        } else {
            Log.d("SizeSelection", "Error: Los datos no fueron pasados correctamente al SizeSelectionBottomSheet.");
        }

        sizeSmallButton = view.findViewById(R.id.sizeSmall);
        sizeMediumButton = view.findViewById(R.id.sizeMedium);
        sizeLargeButton = view.findViewById(R.id.sizeLarge);

        sizeSmallButton.setOnClickListener(v -> {
            selectedSize = "S";
            selectButton(sizeSmallButton);
        });

        sizeMediumButton.setOnClickListener(v -> {
            selectedSize = "M";
            selectButton(sizeMediumButton);
        });

        sizeLargeButton.setOnClickListener(v -> {
            selectedSize = "L";
            selectButton(sizeLargeButton);
        });

        Button selectSizeButton = view.findViewById(R.id.selectSizeButton);
        selectSizeButton.setOnClickListener(v -> {
            if (selectedSize.isEmpty()) {
                Toast.makeText(getActivity(), "Por favor, selecciona una talla", Toast.LENGTH_SHORT).show();
            } else {
                // Crear un nuevo producto con los datos recibidos y la talla seleccionada
                Product selectedProduct = new Product(productName, productPrice, productImageResId, selectedSize);

                // Log para verificar el producto agregado
                Log.d("SizeSelection", "Producto agregado al carrito: " + selectedProduct.toString());

                // Agregar al carrito
                ShoppingCart.addToCart(selectedProduct);

                // Ir a Activity3 y pasar los datos del carrito
                Intent intent = new Intent(getActivity(), Activity3.class);
                startActivity(intent);
                dismiss(); // Cerrar el BottomSheet
            }
        });

        return view;
    }

    private void selectButton(Button selectedButton) {
        Typeface customRegular = ResourcesCompat.getFont(getActivity(), R.font.helvetica_neue_light);
        Typeface customBold = ResourcesCompat.getFont(getActivity(), R.font.helvetica_neue_medium);

        sizeSmallButton.setTypeface(customRegular);
        sizeMediumButton.setTypeface(customRegular);
        sizeLargeButton.setTypeface(customRegular);

        selectedButton.setTypeface(customBold);
    }
}
