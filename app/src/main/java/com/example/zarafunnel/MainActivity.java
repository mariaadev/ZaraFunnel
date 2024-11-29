package com.example.zarafunnel;

import android.content.Intent;
import android.os.Bundle;
import android.widget.EditText;
import androidx.appcompat.app.AppCompatActivity;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import androidx.activity.EdgeToEdge;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import android.util.Log;
import android.widget.FrameLayout;

public class MainActivity extends AppCompatActivity implements OnProductClickListener, BottomNavigationViewFragment.NavigationListener {
    private static final String TAG = "MainActivity";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        if (savedInstanceState == null) {
            // Si no hay estado guardado, agrega el fragmento
            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
            transaction.replace(R.id.fragment_container_shopping_bag, new FragmentImageButton());
            transaction.commit();
        }

        FrameLayout frameLayout = findViewById(R.id.fragment_container_shopping_bag);

        frameLayout.setOnClickListener(v -> {
            Log.d("FrameLayoutClick", "FrameLayout clicked!");
            Intent intent = new Intent(this, Activity3.class);
            startActivity(intent);
        });

        // Obtener focus para que se vea el cursor al entrar a la Activity
        EditText searchBar = findViewById(R.id.searchBar);
        searchBar.requestFocus();

        // Cargar el fragmento ProductListFragment
        if (savedInstanceState == null) {
            ProductListFragment productListFragment = new ProductListFragment();

            // Usar FragmentTransaction para añadir el fragmento al FrameLayout
            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
            transaction.replace(R.id.fragmentContainer, productListFragment);
            transaction.commit();
        }

        // Cargar el fragmento con el BottomNavigationView
        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction()
                .replace(R.id.bottomNavigationViewConatiner, new BottomNavigationViewFragment())
                .commit();

        // Configuración de la ventana para el sistema
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }



    @Override
    public void onNavigationItemSelected(int itemId) {
        if (itemId == R.id.home) {
            //Activity actual
        } else if (itemId == R.id.profile) {
            startActivity(new Intent(this, Activity4.class));
            overridePendingTransition(0, 0); // Animación de transición
            finish();

        } else if (itemId == R.id.bag) {
            startActivity(new Intent(this, Activity3.class));
            overridePendingTransition(0, 0);
            finish();

        }

    }



    @Override
    public void onProductClick(Product product) {
        Log.d(TAG, "Producto seleccionado: " + product.getName());
        Log.d(TAG, "Precio del producto: " + product.getPrice());
        Log.d(TAG, "ID de imagen del producto: " + product.getImageResId());
        // Crear una nueva instancia del BottomSheet y pasar los datos del producto
        ProductDetailsBottomSheet bottomSheet = ProductDetailsBottomSheet.newInstance(
                product.getName(),
                product.getPrice(),
                product.getImageResId()
        );

        // Mostrar el BottomSheet
        bottomSheet.show(getSupportFragmentManager(), "ProductDetailsBottomSheet");
    }
}
