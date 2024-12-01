package com.example.zarafunnel;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.util.Log;
import android.widget.FrameLayout;

import java.io.Serializable;
import java.util.List;

public class Activity7 extends AppCompatActivity {

    private String name, lastName, email, address, address2, postalCode, phone, region, shippingDate, shippingPrice, shippingMethod, province;
    private boolean isBusiness;
    private List<Product> cartProducts;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_7);

        if (savedInstanceState == null) {
            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
            transaction.replace(R.id.fragment_container_shopping_bag, new FragmentImageButton());
            transaction.commit();
        }

        FrameLayout frameLayout = findViewById(R.id.fragment_container_shopping_bag);

        frameLayout.setOnClickListener(v -> {
            Intent intent = new Intent(this, Activity3.class);
            startActivity(intent);
        });


        //Recuperar les dades de l'Intent anterior
        Intent intent = getIntent();
        loadIntentData(intent);


        //Configuració de listeners per cada botó
        setupClickListener(R.id.visaOption, "VISA");
        setupClickListener(R.id.mastercardOption, "MASTERCARD");
        setupClickListener(R.id.americanExpressOption, "AMERICAN EXPRESS");
        setupClickListener(R.id.bizumOption, "BIZUM");
        setupClickListener(R.id.paypalOption, "PAYPAL");
        setupClickListener(R.id.affinityOption, "AFFINITY");
        setupClickListener(R.id.giftCardOption, "TARJETA REGALO");
        setupClickListener(R.id.applePayOption, "APPLE PAY");

        findViewById(R.id.backButton).setOnClickListener(view -> finish());
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
        cartProducts = (List<Product>) intent.getSerializableExtra("cartItems");
        shippingDate = intent.getStringExtra("shippingDate");
        shippingPrice = intent.getStringExtra("shippingPrice");
        province = intent.getStringExtra("province");
    }
    private void setupClickListener(int buttonId, String paymentMethod) {
        findViewById(buttonId).setOnClickListener(view -> navigateToActivity8(paymentMethod));
    }
    private void navigateToActivity8(String paymentMethod) {
        Intent intentToActivity8 = new Intent(Activity7.this, Activity8.class);

        //Afegir totes les dades al Intent
        intentToActivity8.putExtra("paymentMethod", paymentMethod);
        intentToActivity8.putExtra("name", name);
        intentToActivity8.putExtra("lastName", lastName);
        intentToActivity8.putExtra("email", email);
        intentToActivity8.putExtra("address", address);
        intentToActivity8.putExtra("address2", address2);
        intentToActivity8.putExtra("postalCode", postalCode);
        intentToActivity8.putExtra("phone", phone);
        intentToActivity8.putExtra("region", region);
        intentToActivity8.putExtra("isBusiness", isBusiness);
        intentToActivity8.putExtra("cartItems", (Serializable) cartProducts);
        intentToActivity8.putExtra("shippingDate", shippingDate);
        intentToActivity8.putExtra("shippingPrice", shippingPrice);
        intentToActivity8.putExtra("shippingMethod", shippingMethod);
        intentToActivity8.putExtra("province", province);

        startActivity(intentToActivity8);
    }

}