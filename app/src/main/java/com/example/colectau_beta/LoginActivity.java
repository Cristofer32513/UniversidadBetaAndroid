package com.example.colectau_beta;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

public class LoginActivity extends AppCompatActivity {

    private EditText cajaUsuario, cajaPassword;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        if (getSupportActionBar() != null) {
            getSupportActionBar().hide();
        }

        cajaUsuario = findViewById(R.id.editText_NombreUsuario);
        cajaPassword = findViewById(R.id.editText_Contraseña);
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

    public void iniciarSesion(View view) {
        if(validarCampos()) {
            Intent intent = new Intent(LoginActivity.this, MenuActivity.class);
            intent.putExtra("nombre_usuario", cajaUsuario.getText().toString());
            startActivity(intent);
            finish();
        }
    }

    public boolean validarCampos() {
        cajaUsuario.setBackgroundResource(R.drawable.borde_cajas_login);
        cajaPassword.setBackgroundResource(R.drawable.borde_cajas_login);
        boolean respuesta = true;
        StringBuilder cadenaRespuesta = new StringBuilder();

        if(cajaUsuario.getText().toString().trim().isEmpty()) {
            cadenaRespuesta.append(getString(R.string.ingresa_nombre_usuario)+"\n\n");
            cajaUsuario.setBackgroundResource(R.drawable.borde_cajas_donativo_error);
            respuesta = false;
        }
        if(cajaPassword.getText().toString().trim().isEmpty()) {
            cadenaRespuesta.append(getString(R.string.ingresa_contraseña)+"\n\n");
            cajaPassword.setBackgroundResource(R.drawable.borde_cajas_donativo_error);
            respuesta = false;
        }

        if(!respuesta) {
            new AlertDialog.Builder(this)
                .setIcon(android.R.drawable.ic_dialog_alert).setTitle(getString(R.string.errores_detectados))
                .setMessage(cadenaRespuesta)
                .setPositiveButton(getString(R.string.entendido), (dialogInterface, i) -> dialogInterface.cancel()).show()
            ;
        }

        return respuesta;
    }
}