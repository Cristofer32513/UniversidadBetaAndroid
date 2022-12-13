package com.example.colectau_beta;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class ProcesoExitosoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_proceso_exitoso);

        if (getSupportActionBar() != null) {
            getSupportActionBar().hide();
        }

        Bundle extras = getIntent().getExtras();

        //Conexion de variables con la interfaz
        TextView textViewprocesoExitoso= findViewById(R.id.textView_mjs_correcto);
        ImageView imageViewExitoso = findViewById(R.id.imageView_proceso_correcto);
        textViewprocesoExitoso.setText(extras.getString("msj"));

        //Agregar animaciones
        Animation animacionImagen = AnimationUtils.loadAnimation(this, R.anim.desplazamiento_logo_launch);
        Animation animacionTexto = AnimationUtils.loadAnimation(this, R.anim.desplazamiento_textos_donativo_exitoso);
        textViewprocesoExitoso.setAnimation(animacionTexto);
        imageViewExitoso.setAnimation(animacionImagen);

        new Handler().postDelayed(() -> {
            //Intent intent = new Intent(ProcesoExitosoActivity.this, LoginActivity.class);
            //startActivity(intent);
            onBackPressed();
        }, 3000);
    }
}