package com.example.zarafunnel;

import android.content.Intent;
import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ScrollView;
import android.widget.TextView;

import java.io.Serializable;
import java.util.List;

public class Activity8 extends AppCompatActivity {
    private String name, lastName, email, address, address2, postalCode, phone, region, shippingDate, shippingPrice, paymentMethod,province;
    private boolean isBusiness;
    private List<Product> cartProducts;
    private Button authorizePaymentButton;
    private TextView productQuantityTextView;
    private RecyclerView productsRecyclerView;
    private ProductAdapter productAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_8);

        if (savedInstanceState == null) {
            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
            transaction.replace(R.id.fragment_container_shopping_bag, new FragmentImageButton());
            transaction.commit();
        }

        Intent intent = getIntent();
        loadIntentData(intent);

        productQuantityTextView = findViewById(R.id.productQuantity);
        //Configurar el RecyclerView
        productsRecyclerView = findViewById(R.id.cartRecyclerView);
        productsRecyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));  // Horizontal para mostrar las imágenes en fila

        //Configurar el adaptador con los productos del carrito
        productAdapter = new ProductAdapter(cartProducts, R.layout.item_product_small);
        productsRecyclerView.setAdapter(productAdapter);

        productQuantityTextView = findViewById(R.id.productQuantity);
        updateProductQuantity();

        //Para poder ver el botón de autorizar pago
        ScrollView scrollView = findViewById(R.id.scrollView);
        scrollView.post(new Runnable() {
            @Override
            public void run() {
                scrollView.fullScroll(ScrollView.FOCUS_DOWN);
            }
        });


        TextView addressName = findViewById(R.id.addressName);
        TextView addressDetails = findViewById(R.id.addressDetails);
        TextView deliveryDateText = findViewById(R.id.deliveryDate);
        TextView shippingDateText = findViewById(R.id.shippingDate);
        ImageView paymentImage = findViewById(R.id.paymentMethodImage);
        TextView paymentMethodText = findViewById(R.id.paymentMethod);

        //Formatear dirección
        String fullAddress = address + "\n" +
                (address2.isEmpty() ? "" : address2 + "\n") +
                postalCode + ", " + province + ", " + region + "\n" +
                phone;

        addressName.setText(name + " " + lastName);
        addressDetails.setText(fullAddress);

        //Mostrar la fecha de entrega y la fecha de envío dependiendo del shippingDate
        if (shippingDate != null) {
            deliveryDateText.setText(shippingDate.toUpperCase());
            shippingDateText.setText("Entrega " + shippingDate.toLowerCase());
        }

        //Según el método de pago escogido
        switch (paymentMethod) {
            case "VISA":
                paymentImage.setImageResource(R.drawable.visa);
                paymentMethodText.setText("VISA");
                break;
            case "MASTERCARD":
                paymentImage.setImageResource(R.drawable.mastercard);
                paymentMethodText.setText("MASTERCARD");
                break;
            case "AMERICAN EXPRESS":
                paymentImage.setImageResource(R.drawable.american_express);
                paymentMethodText.setText("AMERICAN EXPRESS");
                break;
            case "BIZUM":
                paymentImage.setImageResource(R.drawable.bizum);
                paymentMethodText.setText("BIZUM");
                break;
            case "PAYPAL":
                paymentImage.setImageResource(R.drawable.paypal);
                paymentMethodText.setText("PAYPAL");
                break;
            case "AFFINITY":
                paymentImage.setImageResource(R.drawable.affinitycard);
                paymentMethodText.setText("AFFINITY");
                break;
            case "TARJETA REGALO":
                paymentImage.setImageResource(R.drawable.giftcard);
                paymentMethodText.setText("TARJETA REGALO");
                break;
            case "APPLE PAY":
                paymentImage.setImageResource(R.drawable.applepay);
                paymentMethodText.setText("APPLE PAY");
                break;
            default:
                paymentImage.setImageResource(R.drawable.applepay);
                paymentMethodText.setText("Método de pago no seleccionado");
                break;
        }

        findViewById(R.id.backButton).setOnClickListener(view -> finish());

        authorizePaymentButton = findViewById(R.id.authorizePaymentButton);
        authorizePaymentButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                navigateToActivity9();
            }
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
        cartProducts = (List<Product>) intent.getSerializableExtra("cartItems");
        shippingDate = intent.getStringExtra("shippingDate");
        shippingPrice = intent.getStringExtra("shippingPrice");
        paymentMethod = intent.getStringExtra("paymentMethod");
        province = intent.getStringExtra("province");
    }
    private void navigateToActivity9() {
        Intent intentToActivity9 = new Intent(Activity8.this, Activity9.class);

        intentToActivity9.putExtra("paymentMethod", paymentMethod);
        intentToActivity9.putExtra("name", name);
        intentToActivity9.putExtra("lastName", lastName);
        intentToActivity9.putExtra("email", email);
        intentToActivity9.putExtra("address", address);
        intentToActivity9.putExtra("address2", address2);
        intentToActivity9.putExtra("postalCode", postalCode);
        intentToActivity9.putExtra("phone", phone);
        intentToActivity9.putExtra("region", region);
        intentToActivity9.putExtra("isBusiness", isBusiness);
        intentToActivity9.putExtra("cartItems", (Serializable) cartProducts);
        intentToActivity9.putExtra("shippingDate", shippingDate);
        intentToActivity9.putExtra("shippingPrice", shippingPrice);
        intentToActivity9.putExtra("province", province);
        //Iniciar la actividad 9
        startActivity(intentToActivity9);
    }

    private void updateProductQuantity() {
        int totalQuantity = 0;
        if (cartProducts != null) {
            totalQuantity = cartProducts.size();
        }
        String quantityText = totalQuantity + " artículo" + (totalQuantity != 1 ? "s" : "");
        productQuantityTextView.setText(quantityText);
    }

}