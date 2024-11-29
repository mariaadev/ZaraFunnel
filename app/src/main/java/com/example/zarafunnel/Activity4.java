package com.example.zarafunnel;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.fragment.app.FragmentTransaction;

import java.io.Serializable;
import java.util.List;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import android.widget.Button;
import android.widget.ImageButton;
import java.util.ArrayList;

public class Activity4 extends AppCompatActivity {
    private List<Product> cartProducts;
    private ImageButton closeButton;
    private Button buttonGuest;
    private Button buttonRegister;
    private Button buttonLogin;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_4);

        if (savedInstanceState == null) {
            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
            transaction.replace(R.id.fragment_container_shopping_bag, new FragmentImageButton());
            transaction.commit();
        }

        //Recuperar la lista de productos del Intent
        Intent intent = getIntent();
        cartProducts = (List<Product>) intent.getSerializableExtra("cartItems");

        if (cartProducts != null) {
            //Mostrar la lista en un RecyclerView o mostrar los datos del carrito
            Log.d("Activity4", "Productos recibidos: " + cartProducts.size());
        } else {
            Log.d("Activity4", "No se recibieron productos.");
        }

        closeButton = findViewById(R.id.closeButton);
        closeButton.setOnClickListener(v -> startActivity(new Intent(getApplicationContext(), Activity3.class)));

        buttonGuest = findViewById(R.id.buttonGuest);
        buttonGuest.setOnClickListener(v -> {
            //Pasar los productos del carrito a la actividad 5
            Intent intentToActivity5 = new Intent(Activity4.this, Activity5.class);
            intentToActivity5.putExtra("cartItems", (Serializable) cartProducts);
            startActivity(intentToActivity5);
        });

        buttonLogin = findViewById(R.id.buttonLogin);
        buttonLogin.setOnClickListener(v -> {
            Intent intentToActivity5 = new Intent(Activity4.this, Activity5.class);
            intentToActivity5.putExtra("cartItems", (Serializable) cartProducts);
            startActivity(intentToActivity5);
        });

        buttonRegister= findViewById(R.id.buttonRegister);
        buttonRegister.setOnClickListener(v -> {
            Intent intentToActivity5 = new Intent(Activity4.this, Activity5.class);
            intentToActivity5.putExtra("cartItems", (Serializable) cartProducts);
            startActivity(intentToActivity5);
        });


        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }
}