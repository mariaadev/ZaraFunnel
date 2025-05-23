package com.example.zarafunnel.screens;

import android.content.Intent;
import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.fragment.app.FragmentTransaction;

import java.io.Serializable;
import java.util.List;

import com.example.zarafunnel.fragments.FragmentImageButton;
import com.example.zarafunnel.models.Product;
import com.example.zarafunnel.R;

import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageButton;

public class InicioRegistroActivity extends AppCompatActivity {
    private List<Product> cartProducts;
    private ImageButton closeButton;
    private Button buttonGuest;
    private Button buttonRegister;
    private Button buttonLogin;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.inicio_registro_activity);

        if (savedInstanceState == null) {
            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
            transaction.replace(R.id.fragment_container_shopping_bag, new FragmentImageButton());
            transaction.commit();
        }

        FrameLayout frameLayout = findViewById(R.id.fragment_container_shopping_bag);

        frameLayout.setOnClickListener(v -> {
            Intent intent = new Intent(this, ShoppingCartActivity.class);
            startActivity(intent);
        });


        //Recuperar la lista de productos del Intent
        Intent intent = getIntent();
        cartProducts = (List<Product>) intent.getSerializableExtra("cartItems");

        closeButton = findViewById(R.id.closeButton);
        closeButton.setOnClickListener(v -> startActivity(new Intent(getApplicationContext(), ShoppingCartActivity.class)));

        buttonGuest = findViewById(R.id.buttonGuest);
        buttonGuest.setOnClickListener(v -> {
            //Pasar els productos de la cistella a la actividad 5
            Intent intentToActivity5 = new Intent(InicioRegistroActivity.this, FormularioRegistroActivity.class);
            intentToActivity5.putExtra("cartItems", (Serializable) cartProducts);
            startActivity(intentToActivity5);
        });

        buttonLogin = findViewById(R.id.buttonLogin);
        buttonLogin.setOnClickListener(v -> {
            Intent intentToActivity5 = new Intent(InicioRegistroActivity.this, FormularioRegistroActivity.class);
            intentToActivity5.putExtra("cartItems", (Serializable) cartProducts);
            startActivity(intentToActivity5);
        });

        buttonRegister= findViewById(R.id.buttonRegister);
        buttonRegister.setOnClickListener(v -> {
            Intent intentToActivity5 = new Intent(InicioRegistroActivity.this, FormularioRegistroActivity.class);
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