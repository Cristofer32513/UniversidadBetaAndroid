package com.example.colectau_beta;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
    }

    public void donar(View view) {
        Intent intent = new Intent(LoginActivity.this, DonativoActivity.class);
        intent.putExtra("nombre", "");
        intent.putExtra("primer_ap", "");
        intent.putExtra("segundo_ap", "");
        intent.putExtra("telefono", "");
        intent.putExtra("email", "");
        intent.putExtra("categoria", 0);
        intent.putExtra("fecha_graduacion", "");
        startActivity(intent);
        finish();
    }
}