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


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        BottomNavigationView bottomNavigationView = findViewById(R.id.bottomNavigationView);

        //obtener focus para que se vea el cursor al entrar a la Activity
        EditText searchBar = findViewById(R.id.searchBar);
        searchBar.requestFocus();
        //Cargar el fragmento
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.fragmentContainer, new ProductListFragment())
                .commit();
        //Cargar el fragmento ProductListFragment dentro del FrameLayout
        if (savedInstanceState == null) {
            //Crear una nueva instancia del ProductListFragment
            ProductListFragment productListFragment = new ProductListFragment();

            //Usar FragmentTransaction para añadir el fragmento al FrameLayout
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



        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }
}