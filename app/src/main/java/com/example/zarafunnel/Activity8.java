package com.example.zarafunnel;

import android.content.Intent;
import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import android.widget.ImageView;
import android.widget.TextView;
import java.util.List;

public class Activity8 extends AppCompatActivity {
    private String name, lastName, email, address, address2, postalCode, phone, region, shippingDate, shippingPrice, paymentMethod;
    private boolean isBusiness;
    private List<Product> cartProducts;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_8);

        Intent intent = getIntent();
        loadIntentData(intent);

        TextView addressName = findViewById(R.id.addressName);
        TextView addressDetails = findViewById(R.id.addressDetails);
        TextView deliveryDateText = findViewById(R.id.deliveryDate);
        TextView shippingDateText = findViewById(R.id.shippingDate);
        ImageView paymentImage = findViewById(R.id.paymentMethodImage);
        TextView paymentMethodText = findViewById(R.id.paymentMethod);

        //Formatear dirección
        String fullAddress = address + "\n" +
                (address2.isEmpty() ? "" : address2 + "\n") +
                postalCode + ", " + region + "\n" +
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
    }
}