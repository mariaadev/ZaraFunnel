package com.example.zarafunnel;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;
import java.util.List;

public class Activity9 extends AppCompatActivity {
    private String name, lastName, email, address, address2, postalCode, phone, region, shippingDate, shippingPrice, paymentMethod;
    private boolean isBusiness;
    private List<Product> cartProducts;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_9);

        //Recuperar dades de l'ntent
        Intent intent = getIntent();
        loadIntentData(intent);

        //Configurar el botó de continuar comprant
        Button continueShoppingButton = findViewById(R.id.continueShoppingButton);
        continueShoppingButton.setOnClickListener(v -> {
            Intent intentMainActivity = new Intent(Activity9.this, MainActivity.class);
            ShoppingCart.clearCart();
            startActivity(intentMainActivity);
            finish();
        });
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }
    private void loadIntentData(Intent intent) {
        name = intent.getStringExtra("name");
        lastName = intent.getStringExtra("lastName");
        email = intent.getStringExtra("email");
        address = intent.getStringExtra("address");
        address2 = intent.getStringExtra("address2");
        postalCode = intent.getStringExtra("postalCode");
        phone = intent.getStringExtra("phone");
        region = intent.getStringExtra("region");
        isBusiness = intent.getBooleanExtra("isBusiness", false);
        shippingDate = intent.getStringExtra("shippingDate");
        shippingPrice = intent.getStringExtra("shippingPrice");
        paymentMethod = intent.getStringExtra("paymentMethod");
        cartProducts = (List<Product>) intent.getSerializableExtra("cartItems");
    }
}