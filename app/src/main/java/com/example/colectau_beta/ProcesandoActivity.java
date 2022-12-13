package com.example.colectau_beta;

import android.animation.ObjectAnimator;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class ProcesandoActivity extends AppCompatActivity {

    private Bundle extras;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_procesando);

        if (getSupportActionBar() != null) getSupportActionBar().hide();

        extras = getIntent().getExtras();
        //Conexion de variables con la interfaz
        ProgressBar barraProgreso = findViewById(R.id.progressBar_Procesando);
        TextView textViewProcesando = findViewById(R.id.textView_Procesando);
        textViewProcesando.setText(extras.getString("proceso"));

        //Agregar animaciones
        Animation animacionTexto = AnimationUtils.loadAnimation(this, R.anim.desplazamiento_textos_launch);
        ObjectAnimator.ofInt(barraProgreso, "progress", 100).setDuration(2500).start();
        textViewProcesando.setAnimation(animacionTexto);

        new Handler().postDelayed(() -> {
            Intent intent;
            if(extras.getInt("ventana_resultado") == 1)intent = new Intent(ProcesandoActivity.this, ProcesoExitosoActivity.class);
            else intent = new Intent(ProcesandoActivity.this, ProcesoErrorActivity.class);
            intent.putExtra("msj", extras.getString("resultado"));
            startActivity(intent);
            finish();
        }, 2500);
    }
}