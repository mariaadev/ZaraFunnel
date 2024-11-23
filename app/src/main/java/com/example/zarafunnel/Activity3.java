package com.example.zarafunnel;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.util.Log;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class Activity3 extends AppCompatActivity {

    private RecyclerView cartRecyclerView;
    private CartAdapter cartAdapter;
    private List<Product> cartProducts;
    private Button btn_buy;

    private static final String TAG = "Activity3";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_3);

        btn_buy = findViewById(R.id.btn_buy);

        // Obtener los datos del producto
        Intent intent = getIntent();
        Product selectedProduct = (Product) intent.getSerializableExtra("selectedProduct");
        if (selectedProduct != null) {
            Log.d("Activity3", "Producto recibido: " + selectedProduct.toString());
            // Mostrar el producto en los views correspondientes
        }
        cartRecyclerView = findViewById(R.id.recyclerViewCart);

        // Obtener los productos del carrito
        cartProducts = ShoppingCart.getCart(); // Esto obtiene los productos que has agregado
        Log.d(TAG, "Productos obtenidos del carrito: " + (cartProducts != null ? cartProducts.size() : "null"));
        // Verificar si la lista de productos no es null
        if (cartProducts == null) {
            Log.d(TAG, "La lista de productos está vacía o es null, creando nueva lista");
            cartProducts = new ArrayList<>();
        }

        // Configurar el adaptador
        cartAdapter = new CartAdapter(this, cartProducts);
        cartRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        cartRecyclerView.setAdapter(cartAdapter);

        // Configurar el botón de finalizar compra
        btn_buy.setOnClickListener(v -> {
            // Crear un nuevo Intent para pasar a la pantalla de pago
            Intent paymentIntent = new Intent(Activity3.this, Activity4.class);

            // Pasar la lista de productos al siguiente Activity usando putSerializable
            paymentIntent.putExtra("cartItems", (Serializable) cartProducts);

            // Iniciar la actividad de pago
            startActivity(paymentIntent);
        });
        BottomNavigationView bottomNavigationView = findViewById(R.id.bottomNavigationView);

        bottomNavigationView.setSelectedItemId(R.id.bag);
        bottomNavigationView.setOnItemSelectedListener(item -> {
            int itemId = item.getItemId();
            if (itemId == R.id.home) {
                startActivity(new Intent(getApplicationContext(), MainActivity.class));
                overridePendingTransition(0, 0);
                finish();
                return true;
            } else if (itemId == R.id.profile) {
                startActivity(new Intent(getApplicationContext(), Activity2.class));
                overridePendingTransition(0, 0);
                finish();
                return true;
            } else if (itemId == R.id.bag) {
                return true;
            }
            return false;
        });

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }
}