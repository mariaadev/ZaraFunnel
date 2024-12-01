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


        //Inicialitzar
        recyclerView = findViewById(R.id.recyclerViewCart);
        emptyCartMessage = findViewById(R.id.emptyCartMessage);
        emptyCartCaption = findViewById(R.id.emptyCartCaption);
        emptyCartIcon = findViewById(R.id.emptyCartIcon);
        btn_buy = findViewById(R.id.btn_buy);


        //Carregar el fragment amb el BottomNavigationView
        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction()
                .replace(R.id.bottomNavigationViewConatiner, new BottomNavigationViewFragment())
                .commit();

        //Obtenir les dades del producte
        Intent intent = getIntent();
        Product selectedProduct = (Product) intent.getSerializableExtra("selectedProduct");
        if (selectedProduct != null) {
            Log.d("Activity3", "Producto recibido: " + selectedProduct.toString());
        }
        cartRecyclerView = findViewById(R.id.recyclerViewCart);



        //Obtenir els productes de la cistella
        cartProducts = ShoppingCart.getCart();
        Log.d(TAG, "Productos obtenidos del carrito: " + (cartProducts != null ? cartProducts.size() : "null"));
        //Verificar que la lista de productes no es null
        if (cartProducts == null) {
            cartProducts = new ArrayList<>();
        }


        //Configurar l'adaptador
        cartAdapter = new CartAdapter(this, cartProducts);
        cartRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        cartRecyclerView.setAdapter(cartAdapter);


        cartButton = findViewById(R.id.cartButton);

        if (cartButton != null) {
            String cartText = "Carrito (" + cartProducts.size() + ")";
            cartButton.setText(cartText);
        }
        updateCartView();

        //Configurar el botó de finalitzar compra
        btn_buy.setOnClickListener(v -> {
            Intent paymentIntent = new Intent(Activity3.this, Activity4.class);

            //Pasar la lista de productes a la següent Activity utilitzant putSerializable
            paymentIntent.putExtra("cartItems", (Serializable) cartProducts);
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
            //Mostrar el missatge i l'icona de cistella buida
            emptyCartMessage.setVisibility(View.VISIBLE);
            emptyCartCaption.setVisibility(View.VISIBLE);
            emptyCartIcon.setVisibility(View.VISIBLE);
            //Ocultar Recycler view i botó de finalitzar compra
            recyclerView.setVisibility(View.GONE);
            btn_buy.setVisibility(View.GONE);
        } else {
            //Si hi ha productes a la cistella mostrar recycler view
            emptyCartMessage.setVisibility(View.GONE);
            emptyCartIcon.setVisibility(View.GONE);
            emptyCartCaption.setVisibility(View.GONE);
            recyclerView.setVisibility(View.VISIBLE);
            btn_buy.setVisibility(View.VISIBLE);
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