package com.example.zarafunnel;

import android.media.Image;
import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.RadioButton;
import android.widget.Toast;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.io.Serializable;
import java.util.List;


public class Activity6 extends AppCompatActivity {
    TextView textView;
    private RecyclerView productsRecyclerView;
    private ProductAdapter productAdapter;
    private List<Product> cartProducts;
    private ImageButton backButton;
    private TextView addressTextView;
    Button continueButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_6);

        // Recuperar los datos pasados en el Intent
        Intent intent = getIntent();
        String name = intent.getStringExtra("name");
        String lastName = intent.getStringExtra("lastName");
        String email = intent.getStringExtra("email");
        String address = intent.getStringExtra("address");
        String address2 = intent.getStringExtra("address2");
        String postalCode = intent.getStringExtra("postalCode");
        String phone = intent.getStringExtra("phone");
        String region = intent.getStringExtra("region");
        String province = getIntent().getStringExtra("province");

        boolean isBusiness = intent.getBooleanExtra("isBusiness", false);
        cartProducts = (List<Product>) intent.getSerializableExtra("cartItems");

        if (cartProducts != null) {
            for (Product product : cartProducts) {
                Log.d("Activity6", "Producto: " + product.getName());
            }
        }

        addressTextView = findViewById(R.id.addressTextView);
        if (address != null) {
            addressTextView.setText(address);
        }

        //Configurar el RecyclerView
        productsRecyclerView = findViewById(R.id.productsRecyclerView);
        productsRecyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));  // Horizontal para mostrar las imágenes en fila

        //Configurar el adaptador con los productos del carrito
        productAdapter = new ProductAdapter(cartProducts, R.layout.item_product_small);
        productsRecyclerView.setAdapter(productAdapter);

        backButton = findViewById(R.id.backButton);
        backButton.setOnClickListener(v -> startActivity(new Intent(getApplicationContext(), Activity5.class)));

        RadioButton optionFriday = findViewById(R.id.optionFriday);
        RadioButton optionTuesday = findViewById(R.id.optionTuesday);

    //Configura los listeners para manejar las selecciones de los radio buttons
        optionFriday.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (optionFriday.isChecked()) {
                    optionTuesday.setChecked(false);
                }
            }
        });

        optionTuesday.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (optionTuesday.isChecked()) {
                    optionFriday.setChecked(false);

                }
            }
        });

        continueButton = findViewById(R.id.continueButton);

        continueButton.setOnClickListener(v -> {

            String selectedDate = null;
            String selectedPrice = null;

            if (optionFriday.isChecked()) {
                selectedDate = "VIERNES 15 DE NOVIEMBRE";
                selectedPrice = "3,95 EUR";
            } else if (optionTuesday.isChecked()) {
                selectedDate = "JUEVES 14 DE NOVIEMBRE";
                selectedPrice = "4,95 EUR";
            }

            if (selectedDate != null && selectedPrice != null) {
                Intent intentToActivity7 = new Intent(Activity6.this, Activity7.class);
                intentToActivity7.putExtra("name", name);
                intentToActivity7.putExtra("lastName", lastName);
                intentToActivity7.putExtra("email", email);
                intentToActivity7.putExtra("address", address);
                intentToActivity7.putExtra("address2", address2);
                intentToActivity7.putExtra("postalCode", postalCode);
                intentToActivity7.putExtra("phone", phone);
                intentToActivity7.putExtra("region", region);
                intentToActivity7.putExtra("isBusiness", isBusiness);
                intentToActivity7.putExtra("province", province);
                intentToActivity7.putExtra("cartItems", (Serializable) cartProducts);
                intentToActivity7.putExtra("shippingDate", selectedDate); //Fecha seleccionada
                intentToActivity7.putExtra("shippingPrice", selectedPrice); //Precio de envío seleccionado

                startActivity(intentToActivity7);
            } else {
                Toast.makeText(Activity6.this, "Por favor, selecciona una opción de envío.", Toast.LENGTH_SHORT).show();
            }
        });
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }
}