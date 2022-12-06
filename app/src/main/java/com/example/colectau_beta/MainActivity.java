package com.example.colectau_beta;

import androidx.appcompat.app.AppCompatActivity;

import android.animation.ObjectAnimator;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_main);

        //Conexion de variables con la interfaz
        ProgressBar barraProgreso = findViewById(R.id.progressBar_ProcesandoDonativo);
        TextView textViewColecta = findViewById(R.id.textView_colecta);
        TextView textViewUniversidad = findViewById(R.id.textView_universidad);
        ImageView imageViewLogoLanding = findViewById(R.id.imageView_logoLaunch);

        //Agregar animaciones
        Animation animacionLogo = AnimationUtils.loadAnimation(this, R.anim.desplazamiento_logo_launch);
        Animation animacionTexto = AnimationUtils.loadAnimation(this, R.anim.desplazamiento_textos_launch);
        ObjectAnimator.ofInt(barraProgreso, "progress", 100).setDuration(4000).start();
        textViewColecta.setAnimation(animacionTexto);
        textViewUniversidad.setAnimation(animacionTexto);
        imageViewLogoLanding.setAnimation(animacionLogo);

        new Handler().postDelayed(() -> {
            Intent intent = new Intent(MainActivity.this, LoginActivity.class);
            startActivity(intent);
            finish();
        }, 4000);
    }
}