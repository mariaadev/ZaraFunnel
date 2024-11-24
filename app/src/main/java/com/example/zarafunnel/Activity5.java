package com.example.zarafunnel;

import android.content.Intent;
import android.content.res.ColorStateList;
import android.os.Bundle;
import android.util.Log;
import android.widget.CompoundButton;
import android.widget.ImageButton;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.appcompat.widget.SwitchCompat;
public class Activity5 extends AppCompatActivity {

    private ImageButton backButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);

        setContentView(R.layout.activity_5);
        backButton = findViewById(R.id.backButton);
        backButton.setOnClickListener(v -> startActivity(new Intent(getApplicationContext(), Activity4.class)));


        SwitchCompat switchCompat =   findViewById(R.id.customThumbSmallSwitch);
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
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }
}