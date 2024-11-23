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
import androidx.fragment.app.FragmentTransaction;
import android.util.Log;
public class MainActivity extends AppCompatActivity implements OnProductClickListener {
    private static final String TAG = "MainActivity";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        BottomNavigationView bottomNavigationView = findViewById(R.id.bottomNavigationView);

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

        bottomNavigationView.setSelectedItemId(R.id.home);
        bottomNavigationView.setOnItemSelectedListener(item -> {
            int itemId = item.getItemId();
            if (itemId == R.id.home) {
                return true;
            } else if (itemId == R.id.profile) {
                startActivity(new Intent(getApplicationContext(), Activity2.class));
                overridePendingTransition(0, 0);
                finish();
                return true;
            } else if (itemId == R.id.bag) {
                startActivity(new Intent(getApplicationContext(), Activity3.class));
                overridePendingTransition(0, 0);
                finish();
                return true;
            }
            return false;
        });

        // Configuración de la ventana para el sistema
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
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
