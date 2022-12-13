package com.example.colectau_beta;

import android.os.Bundle;
import android.os.Handler;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class ProcesoErrorActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_proceso_error);

        if (getSupportActionBar() != null) getSupportActionBar().hide();

        Bundle extras = getIntent().getExtras();
        //Conexion de variables con la interfaz
        TextView textViewMsjError= findViewById(R.id.textView_msj_error);
        ImageView imageViewError = findViewById(R.id.imageView_proceso_error);
        textViewMsjError.setText(extras.getString("msj"));

        //Agregar animaciones
        Animation animacionImagen = AnimationUtils.loadAnimation(this, R.anim.desplazamiento_logo_launch);
        Animation animacionTexto = AnimationUtils.loadAnimation(this, R.anim.desplazamiento_textos_donativo_exitoso);
        textViewMsjError.setAnimation(animacionTexto);
        imageViewError.setAnimation(animacionImagen);

        new Handler().postDelayed(this::onBackPressed, 3000);
    }
}