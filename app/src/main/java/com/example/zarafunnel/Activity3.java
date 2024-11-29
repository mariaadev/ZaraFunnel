package com.example.zarafunnel;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.util.Log;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import android.widget.TextView;
import android.widget.ImageView;
import android.view.View;


public class Activity3 extends AppCompatActivity implements BottomNavigationViewFragment.NavigationListener {

    private RecyclerView cartRecyclerView;
    private CartAdapter cartAdapter;
    private List<Product> cartProducts;
    private Button btn_buy;
    private RecyclerView recyclerView;
    private TextView emptyCartMessage;
    private TextView emptyCartCaption;
    private ImageView emptyCartIcon;
    private ImageButton closeButton;
    private Button cartButton;
    private static final String TAG = "Activity3";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_3);

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


        //Inicializar
        recyclerView = findViewById(R.id.recyclerViewCart);
        emptyCartMessage = findViewById(R.id.emptyCartMessage);
        emptyCartCaption = findViewById(R.id.emptyCartCaption);
        emptyCartIcon = findViewById(R.id.emptyCartIcon);
        btn_buy = findViewById(R.id.btn_buy);


        // Cargar el fragmento con el BottomNavigationView
        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction()
                .replace(R.id.bottomNavigationViewConatiner, new BottomNavigationViewFragment())
                .commit();

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


        //Configurar el adaptador
        cartAdapter = new CartAdapter(this, cartProducts);
        cartRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        cartRecyclerView.setAdapter(cartAdapter);


        cartButton = findViewById(R.id.cartButton);

        if (cartButton != null) {
            String cartText = "Carrito (" + cartProducts.size() + ")";
            cartButton.setText(cartText);
        }
        updateCartView();

        //Configurar el botón de finalizar compra
        btn_buy.setOnClickListener(v -> {
            // Crear un nuevo Intent para pasar a la pantalla de pago
            Intent paymentIntent = new Intent(Activity3.this, Activity4.class);

            // Pasar la lista de productos al siguiente Activity usando putSerializable
            paymentIntent.putExtra("cartItems", (Serializable) cartProducts);

            // Iniciar la actividad de pago
            startActivity(paymentIntent);
        });

        closeButton = findViewById(R.id.closeButton);
        closeButton.setOnClickListener(v -> startActivity(new Intent(getApplicationContext(), MainActivity.class)));

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }


    private void updateCartView() {
        if (cartProducts.isEmpty()) {
            //Mostrar el mensaje y el ícono de la cesta vacía
            emptyCartMessage.setVisibility(View.VISIBLE);
            emptyCartCaption.setVisibility(View.VISIBLE);
            emptyCartIcon.setVisibility(View.VISIBLE);
            recyclerView.setVisibility(View.GONE); //Ocultar el RecyclerView si está vacío
            btn_buy.setVisibility(View.GONE); //Ocultar el botón de finalizar compra
        } else {
            //Mostrar el RecyclerView y el botón si hay productos
            emptyCartMessage.setVisibility(View.GONE);
            emptyCartIcon.setVisibility(View.GONE);
            emptyCartCaption.setVisibility(View.GONE);
            recyclerView.setVisibility(View.VISIBLE); //Mostrar el RecyclerView
            btn_buy.setVisibility(View.VISIBLE); //Mostrar el botón de finalizar compra
        }
    }
    @Override
    public void onNavigationItemSelected(int itemId) {
        if (itemId == R.id.home) {
            startActivity(new Intent(this, MainActivity.class));
            overridePendingTransition(0, 0);
            finish();
        } else if (itemId == R.id.profile) {
            startActivity(new Intent(this, Activity4.class));
            overridePendingTransition(0, 0);
            finish();
        } else if (itemId == R.id.bag) {
            //actividad actual

        }

    }
}