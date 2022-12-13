package com.example.colectau_beta;

import androidx.appcompat.app.AppCompatActivity;

import android.animation.ObjectAnimator;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ProgressBar;
import android.widget.TextView;

public class ProcesandoDonativoActivity extends AppCompatActivity {

    private Bundle extras;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_procesando_donativo);

        if (getSupportActionBar() != null) getSupportActionBar().hide();

        extras = getIntent().getExtras();
        //Conexion de variables con la interfaz
        ProgressBar barraProgreso = findViewById(R.id.progressBar_ProcesandoDonativo);
        TextView textViewProcesandoDonativo = findViewById(R.id.textView_ProcesandoDonativo);

        //Agregar animaciones
        Animation animacionTexto = AnimationUtils.loadAnimation(this, R.anim.desplazamiento_textos_launch);
        ObjectAnimator.ofInt(barraProgreso, "progress", 100).setDuration(4000).start();
        textViewProcesandoDonativo.setAnimation(animacionTexto);

        new Handler().postDelayed(() -> {
            Intent intent;
            if(extras.getInt("ventana_resultado") == 1) intent = new Intent(ProcesandoDonativoActivity.this, DonativoExitosoActivity.class);
            else intent = new Intent(ProcesandoDonativoActivity.this, DonativoErrorActivity.class);
            startActivity(intent);
            finish();
        }, 4000);
    }
}