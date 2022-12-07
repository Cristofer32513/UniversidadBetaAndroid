package com.example.colectau_beta;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {

    private EditText cajaUsuario, cajaContraseña;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        if (getSupportActionBar() != null) {
            getSupportActionBar().hide();
        }

        cajaUsuario = findViewById(R.id.editText_NombreUsuario);
        cajaContraseña = findViewById(R.id.editText_Contraseña);
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
        cajaContraseña.setBackgroundResource(R.drawable.borde_cajas_login);
        boolean respuesta = true;
        StringBuilder cadenaRespuesta = new StringBuilder();

        if(cajaUsuario.getText().toString().trim().isEmpty()) {
            cadenaRespuesta.append("- Ingresa un nombre de usuario. \n\n");
            cajaUsuario.setBackgroundResource(R.drawable.borde_cajas_donativo_error);
            respuesta = false;
        }
        if(cajaContraseña.getText().toString().trim().isEmpty()) {
            cadenaRespuesta.append("- Ingresa una contraseña. \n\n");
            cajaContraseña.setBackgroundResource(R.drawable.borde_cajas_donativo_error);
            respuesta = false;
        }

        if(!respuesta) {
            new AlertDialog.Builder(this)
                .setIcon(android.R.drawable.ic_dialog_alert).setTitle("Errores detectados")
                .setMessage(cadenaRespuesta)
                .setPositiveButton("Entendido", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.cancel();
                    }
                }).show()
            ;
        }

        return respuesta;
    }
}