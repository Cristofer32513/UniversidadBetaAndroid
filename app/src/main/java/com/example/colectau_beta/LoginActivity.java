package com.example.colectau_beta;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.StringRequest;
import java.util.HashMap;
import java.util.Map;
import controlador.VolleySingleton;

public class LoginActivity extends AppCompatActivity {

    private EditText cajaUsuario, cajaPassword;
    private VolleySingleton volley;
    private RequestQueue fRequestQueue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        if (getSupportActionBar() != null) {
            getSupportActionBar().hide();
        }

        cajaUsuario = findViewById(R.id.editText_NombreUsuario);
        cajaPassword = findViewById(R.id.editText_Contraseña);

        volley = VolleySingleton.getInstance(getApplicationContext());
        fRequestQueue = volley.getRequestQueue();
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
            String url = "http://colectaubeta.atwebpages.com/api_validar_user.php";
            StringRequest recuest = new StringRequest(Request.Method.POST, url, response -> {
                System.out.println("---    -"+response);
                //if(response.equals("{\"exito\":true,\"mensaje\":\"Entrale alv\"}")) {
                if(true) {
                    Intent intent = new Intent(LoginActivity.this, MenuActivity.class);
                    intent.putExtra("nombre_usuario", cajaUsuario.getText().toString());
                    intent.putExtra("correo_usuario", "falta hacer esto!!");
                    startActivity(intent);
                    finish();
                } else { mostrarError(getString(R.string.error_usuario_contraseña));}
            }, error -> mostrarError(getString(R.string.falla_api))) {
                @Override
                protected Map<String, String> getParams() {
                    Map<String, String> parametros = new HashMap<>();
                    parametros.put("username", cajaUsuario.getText().toString());
                    parametros.put("passaword", cajaPassword.getText().toString());

                    return parametros;
                }
            };
            recuest.setTag(this);
            if (fRequestQueue == null)
                fRequestQueue = volley.getRequestQueue();
            recuest.setRetryPolicy(new DefaultRetryPolicy(
                    60000, 3, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
            fRequestQueue.add(recuest);
        }
    }

    public boolean validarCampos() {
        cajaUsuario.setBackgroundResource(R.drawable.borde_cajas_login);
        cajaPassword.setBackgroundResource(R.drawable.borde_cajas_login);
        cajaUsuario.setText(cajaUsuario.getText().toString().trim());
        cajaPassword.setText(cajaPassword.getText().toString().trim());

        boolean respuesta = true;
        StringBuilder cadenaRespuesta = new StringBuilder();

        if(cajaUsuario.getText().toString().isEmpty()) {
            cadenaRespuesta.append(getString(R.string.ingresa_nombre_usuario)).append("\n\n");
            cajaUsuario.setBackgroundResource(R.drawable.borde_cajas_donativo_error);
            respuesta = false;
        }
        if(cajaPassword.getText().toString().isEmpty()) {
            cadenaRespuesta.append(getString(R.string.ingresa_contraseña)).append("\n\n");
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

    public void mostrarError(String error) {
        new AlertDialog.Builder(this)
                .setIcon(android.R.drawable.ic_dialog_alert).setTitle(getString(R.string.precaucion))
                .setMessage(error)
                .setPositiveButton(getString(R.string.entendido), (dialogInterface, i) -> dialogInterface.cancel()).show()
        ;
    }
}