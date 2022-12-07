package com.example.colectau_beta;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

public class DonativoErrorActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_donativo_error);

        if (getSupportActionBar() != null) {
            getSupportActionBar().hide();
        }

        //Conexion de variables con la interfaz
        TextView textViewDonativoError= findViewById(R.id.textView_DonativoError);
        ImageView imageViewDonativoError = findViewById(R.id.imageView_DonativoError);

        //Agregar animaciones
        Animation animacionImagen = AnimationUtils.loadAnimation(this, R.anim.desplazamiento_logo_launch);
        Animation animacionTexto = AnimationUtils.loadAnimation(this, R.anim.desplazamiento_textos_donativo_exitoso);
        textViewDonativoError.setAnimation(animacionTexto);
        imageViewDonativoError.setAnimation(animacionImagen);

        new Handler().postDelayed(() -> {
            Intent intent = new Intent(DonativoErrorActivity.this, LoginActivity.class);
            startActivity(intent);
            finish();
        }, 5000);
    }
}