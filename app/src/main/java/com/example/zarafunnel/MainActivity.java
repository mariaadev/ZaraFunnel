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
            //Si no hi ha estat guardat, afegir el fragment
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

        //Obtenir el focus per a que es vegi el cursor al entrar a l'Activity
        EditText searchBar = findViewById(R.id.searchBar);
        searchBar.requestFocus();

        //Carregar el fragment ProductListFragment
        if (savedInstanceState == null) {
            ProductListFragment productListFragment = new ProductListFragment();

            //Utilitzar el FragmentTransaction per afegir el fragment al FrameLayout
            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
            transaction.replace(R.id.fragmentContainer, productListFragment);
            transaction.commit();
        }

        //Carregar el fragment amb el BottomNavigationView
        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction()
                .replace(R.id.bottomNavigationViewConatiner, new BottomNavigationViewFragment())
                .commit();

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
        //Crear una nova instància del BottomSheet i passar les dades del producte
        ProductDetailsBottomSheet bottomSheet = ProductDetailsBottomSheet.newInstance(
                product.getName(),
                product.getPrice(),
                product.getImageResId()
        );

        //Mostrar el BottomSheet
        bottomSheet.show(getSupportFragmentManager(), "ProductDetailsBottomSheet");
    }
}
