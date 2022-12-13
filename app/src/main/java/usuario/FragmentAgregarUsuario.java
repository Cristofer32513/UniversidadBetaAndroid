package usuario;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import com.android.volley.Request;
import com.android.volley.toolbox.StringRequest;
import com.example.colectau_beta.ProcesandoActivity;
import com.example.colectau_beta.R;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import controlador.BaseVolleyFragment;

public class FragmentAgregarUsuario extends BaseVolleyFragment {

    EditText cajaNombre, cajaCorreo, cajaPassword1, cajaPassword2;
    Button btnAgregar, btnCancelar;

    public FragmentAgregarUsuario() {}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.section_fragment_agregar_usuario, container, false);

        cajaNombre = view.findViewById(R.id.editText_Nombre_AgregarUsuario);
        cajaCorreo = view.findViewById(R.id.editText_Correo_AgregarUsuario);
        cajaPassword1 = view.findViewById(R.id.editText_Contraseña1_AgregarUsuario);
        cajaPassword2 = view.findViewById(R.id.editText_Contraseña2_AgregarUsuario);
        btnAgregar = view.findViewById(R.id.button_Agregar_AgregarUsuario);
        btnCancelar = view.findViewById(R.id.button_Cancelar_AgregarUsuario);
        btnAgregar.setOnClickListener(view1 -> {
            if(validarCampos()) {
                String url = "http://colectaubeta.atwebpages.com/api_altas_usuarios.php";
                StringRequest recuest = new StringRequest(Request.Method.POST, url, response -> {
                    System.out.println("---    -"+response);
                    Intent intent = new Intent(getContext(), ProcesandoActivity.class);
                    if(response.equals("{\"exito\":true,\"mensaje\":\"Insercion correcta\"}")) {
                        //Toast.makeText(getContext(), "Usuario Agregado", Toast.LENGTH_LONG).show();
                        intent.putExtra("ventana_resultado", 1);
                        intent.putExtra("resultado", getString(R.string.confirmacion_usuario_registrado));
                    } else {
                        intent.putExtra("ventana_resultado", 0);
                        intent.putExtra("resultado", getString(R.string.error_registro_user));
                        //mostrarError(getString(R.string.error_agregar_usuario));
                    }
                    intent.putExtra("proceso", getString(R.string.registrando_usuario));
                    startActivity(intent);

                    Fragment nuevoFragmento = new FragmentUsuarios();
                    FragmentTransaction transaction = getParentFragmentManager().beginTransaction();
                    transaction.replace(R.id.content_frame, nuevoFragmento);
                    transaction.commit();
                    Objects.requireNonNull(((AppCompatActivity) requireActivity()).getSupportActionBar()).setTitle(getString(R.string.usuarios));
                }, error -> mostrarError(getString(R.string.falla_api))) {
                    @Override
                    protected Map<String, String> getParams() {
                        Map<String, String> parametros = new HashMap<>();
                        parametros.put("username", cajaNombre.getText().toString());
                        parametros.put("passaword", cajaPassword1.getText().toString());
                        parametros.put("email", cajaCorreo.getText().toString());

                        return parametros;
                    }
                };
                addToQueue(recuest);
            }
        });
        btnCancelar.setOnClickListener(view1 -> new AlertDialog.Builder(requireContext())
                .setIcon(android.R.drawable.ic_dialog_alert).setTitle(getString(R.string.precaucion))
                .setMessage(getString(R.string.confirmacion_cancelar))
                .setPositiveButton(getString(R.string.si), (dialogInterface, i) -> {
                    Fragment nuevoFragmento = new FragmentUsuarios();
                    FragmentTransaction transaction = getParentFragmentManager().beginTransaction();
                    transaction.replace(R.id.content_frame, nuevoFragmento);
                    transaction.commit();
                    Objects.requireNonNull(((AppCompatActivity) requireActivity()).getSupportActionBar()).setTitle(getString(R.string.usuarios));
                })
                .setNegativeButton(getString(R.string.no), (dialogInterface, i) -> dialogInterface.cancel())
                .show());

        return view;
    }

    public void mostrarError(String error) {
        new AlertDialog.Builder(requireContext())
                .setIcon(android.R.drawable.ic_dialog_alert).setTitle(getString(R.string.precaucion))
                .setMessage(error)
                .setPositiveButton(getString(R.string.entendido), (dialogInterface, i) -> dialogInterface.cancel()).show()
        ;
    }

    private boolean validarCampos() {
        cajaNombre.setBackgroundResource(R.drawable.borde_cajas_login);
        cajaCorreo.setBackgroundResource(R.drawable.borde_cajas_login);
        cajaPassword1.setBackgroundResource(R.drawable.borde_cajas_login);
        cajaPassword2.setBackgroundResource(R.drawable.borde_cajas_login);

        cajaNombre.setText(cajaNombre.getText().toString().trim());
        cajaNombre.setSelection(cajaNombre.getText().toString().length());
        cajaCorreo.setText(cajaCorreo.getText().toString().trim());
        cajaCorreo.setSelection(cajaCorreo.getText().toString().length());
        cajaPassword1.setText(cajaPassword1.getText().toString().trim());
        cajaPassword1.setSelection(cajaPassword1.getText().toString().length());
        cajaPassword2.setText(cajaPassword2.getText().toString().trim());
        cajaPassword2.setSelection(cajaPassword2.getText().toString().length());

        boolean respuesta = true;
        StringBuilder cadenaRespuesta = new StringBuilder();
        String regexCorreo = "^(([^<>()\\[\\]\\\\.,;:\\s@”]+(\\.[^<>()\\[\\]\\\\.,;:\\s@”]+)*)|(“.+”))@((\\[[0–9]{1,3}\\.[0–9]{1,3}\\.[0–9]{1,3}\\.[0–9]{1,3}])|(([a-zA-Z\\-0–9]+\\.)+[a-zA-Z]{2,}))$";

        if(cajaNombre.getText().toString().isEmpty()) {
            cadenaRespuesta.append(getString(R.string.ingresa_nombre_usuario)).append("\n\n");
            cajaNombre.setBackgroundResource(R.drawable.borde_cajas_donativo_error);
            respuesta = false;
        }

        if(cajaCorreo.getText().toString().isEmpty()) {
            cadenaRespuesta.append(getString(R.string.ingresa_email)).append("\n\n");
            cajaCorreo.setBackgroundResource(R.drawable.borde_cajas_donativo_error);
            respuesta = false;
        } else {
            if(!cajaCorreo.getText().toString().matches(regexCorreo)) {
                cadenaRespuesta.append(getString(R.string.email_no_valido)).append("\n\n");
                cajaCorreo.setBackgroundResource(R.drawable.borde_cajas_donativo_error);
                respuesta = false;
            }
        }

        if(cajaPassword1.getText().toString().isEmpty()) {
            cadenaRespuesta.append(getString(R.string.ingresa_contraseña)).append("\n\n");
            cajaPassword1.setBackgroundResource(R.drawable.borde_cajas_donativo_error);
            respuesta = false;
        } else if(cajaPassword1.getText().toString().contains(" ") ||
                cajaPassword2.getText().toString().contains(" ")) {
            cadenaRespuesta.append(getString(R.string.no_espacios)).append("\n\n");
            cajaPassword1.setBackgroundResource(R.drawable.borde_cajas_donativo_error);
            cajaPassword2.setBackgroundResource(R.drawable.borde_cajas_donativo_error);
            respuesta = false;
        }

        if(cajaPassword2.getText().toString().isEmpty()) {
            cadenaRespuesta.append(getString(R.string.confirma_contraseña)).append("\n\n");
            cajaPassword2.setBackgroundResource(R.drawable.borde_cajas_donativo_error);
            respuesta = false;
        } else {
            if(!cajaPassword2.getText().toString().equals(cajaPassword1.getText().toString().trim())) {
                cadenaRespuesta.append(getString(R.string.error_confirmacion_contraseña)).append("\n\n");
                cajaPassword1.setBackgroundResource(R.drawable.borde_cajas_donativo_error);
                cajaPassword2.setBackgroundResource(R.drawable.borde_cajas_donativo_error);
                respuesta = false;
            }
        }

        if(!respuesta) {
            new AlertDialog.Builder(requireContext())
                    .setIcon(android.R.drawable.ic_dialog_alert).setTitle(getString(R.string.errores_detectados))
                    .setMessage(cadenaRespuesta)
                    .setPositiveButton(getString(R.string.entendido), (dialogInterface, i) -> dialogInterface.cancel())
                    .show()
            ;
        }

        return respuesta;
    }
}