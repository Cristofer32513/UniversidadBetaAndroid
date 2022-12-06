package com.example.colectau_beta;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

public class DonativoExitosoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_donativo_exitoso);

        //Conexion de variables con la interfaz
        TextView textViewDonativoExitoso= findViewById(R.id.textView_DonativoError);
        ImageView imageViewDonativoExitoso = findViewById(R.id.imageView_DonativoError);

        //Agregar animaciones
        Animation animacionImagen = AnimationUtils.loadAnimation(this, R.anim.desplazamiento_logo_launch);
        Animation animacionTexto = AnimationUtils.loadAnimation(this, R.anim.desplazamiento_textos_donativo_exitoso);
        textViewDonativoExitoso.setAnimation(animacionTexto);
        imageViewDonativoExitoso.setAnimation(animacionImagen);

        new Handler().postDelayed(() -> {
            Intent intent = new Intent(DonativoExitosoActivity.this, LoginActivity.class);
            startActivity(intent);
            finish();
        }, 5000);
    }
}