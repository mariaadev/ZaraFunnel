package com.example.zarafunnel;

import android.content.Intent;
import android.content.res.ColorStateList;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageButton;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.appcompat.widget.SwitchCompat;
import androidx.fragment.app.FragmentTransaction;

import java.io.Serializable;
import java.util.List;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;

public class Activity5 extends AppCompatActivity {

    private ImageButton backButton;
    private EditText inputName, inputLastName, inputEmail, inputAdress, inputAdress2, inputPostalCode, inputPhone, inputRegion;
    private Button saveButton;
    private SwitchCompat switchCompat;
    private List<Product> cartProducts;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);

        setContentView(R.layout.activity_5);

        if (savedInstanceState == null) {
            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
            transaction.replace(R.id.fragment_container_shopping_bag, new FragmentImageButton());
            transaction.commit();
        }

        // Inicializar las vistas
        inputName = findViewById(R.id.inputName);
        inputLastName = findViewById(R.id.inputLastName);
        inputEmail = findViewById(R.id.inputEmail);
        inputAdress = findViewById(R.id.inputAdress);
        inputAdress2 = findViewById(R.id.inputAdress2);
        inputPostalCode = findViewById(R.id.inputPostalCode);
        inputPhone = findViewById(R.id.inputPhone);
        inputRegion = findViewById(R.id.inputRegion);
        switchCompat = findViewById(R.id.customThumbSmallSwitch);
        saveButton = findViewById(R.id.saveButton);
        backButton = findViewById(R.id.backButton);

        // Recuperar los productos del carrito que se enviaron desde Activity4
        Intent intent = getIntent();
        cartProducts = (List<Product>) intent.getSerializableExtra("cartItems");

        if (cartProducts != null) {
            Log.d("Activity5", "Productos recibidos: " + cartProducts.size());
        } else {
            Log.d("Activity5", "No se recibieron productos.");
        }

        // Obtener el AutoCompleteTextView
        AutoCompleteTextView menuDropdown = findViewById(R.id.menu_dropdown);

        // Obtener el array de provincias desde los recursos
        String[] provinces = getResources().getStringArray(R.array.provinces);

        // Crear un ArrayAdapter y asociarlo al AutoCompleteTextView
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_dropdown_item_1line, provinces);
        menuDropdown.setAdapter(adapter);

        backButton.setOnClickListener(v -> startActivity(new Intent(getApplicationContext(), Activity4.class)));

        switchCompat =  findViewById(R.id.customThumbSmallSwitch);
        //Si el switch existe
        if (switchCompat != null) {
            switchCompat.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    if (isChecked) {
                        //Activado
                        switchCompat.setThumbTintList(ColorStateList.valueOf(getResources().getColor(R.color.thumb_active_color)));
                        switchCompat.setTrackTintList(ColorStateList.valueOf(getResources().getColor(R.color.track_active_color)));
                    } else {
                        //Desactivado
                        switchCompat.setThumbTintList(ColorStateList.valueOf(getResources().getColor(R.color.thumb_inactive_color)));
                        switchCompat.setTrackTintList(ColorStateList.valueOf(getResources().getColor(R.color.track_inactive_color)));
                    }
                }
            });
        }

        // Establecer el Listener para el botón Guardar
        saveButton.setOnClickListener(v -> {
            // Obtener los valores de los campos
            String name = inputName.getText().toString();
            String lastName = inputLastName.getText().toString();
            String email = inputEmail.getText().toString();
            String address = inputAdress.getText().toString();
            String address2 = inputAdress2.getText().toString();
            String postalCode = inputPostalCode.getText().toString();
            String phone = inputPhone.getText().toString();
            String region = inputRegion.getText().toString();
            boolean isBusiness = switchCompat.isChecked();
            String province = menuDropdown.getText().toString();
            // Crear el Intent para pasar los datos a la siguiente actividad
            Intent intentToActivity6 = new Intent(Activity5.this, Activity6.class);
            intentToActivity6.putExtra("name", name);
            intentToActivity6.putExtra("lastName", lastName);
            intentToActivity6.putExtra("email", email);
            intentToActivity6.putExtra("address", address);
            intentToActivity6.putExtra("address2", address2);
            intentToActivity6.putExtra("postalCode", postalCode);
            intentToActivity6.putExtra("phone", phone);
            intentToActivity6.putExtra("region", region);
            intentToActivity6.putExtra("isBusiness", isBusiness);
            intentToActivity6.putExtra("cartItems", (Serializable) cartProducts);
            intentToActivity6.putExtra("province", province);
            // Iniciar la siguiente actividad
            startActivity(intentToActivity6);
        });
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }
}