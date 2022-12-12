package usuario;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.example.colectau_beta.R;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import controlador.BaseVolleyFragment;

public class FragmentEditarUsuario extends BaseVolleyFragment {

    EditText cajaId, cajaNombre, cajaCorreo, cajaPassword1, cajaPassword2;
    Button btnGuardar, btnCancelar, btnEliminar;

    public FragmentEditarUsuario() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.section_fragment_editar_usuario, container, false);

        cajaId = view.findViewById(R.id.editText_Id_EditarUsuario);
        cajaNombre = view.findViewById(R.id.editText_Nombre_EditarUsuario);
        cajaCorreo = view.findViewById(R.id.editText_Correo_EditarUsuario);
        cajaPassword1 = view.findViewById(R.id.editText_Contraseña1_EditarUsuario);
        cajaPassword2 = view.findViewById(R.id.editText_Contraseña2_EditarUsuario);

        assert getArguments() != null;
        cajaId.setText(getArguments().getString("id", "NA"));
        cajaNombre.setText(getArguments().getString("nombre", "NA"));
        cajaCorreo.setText(getArguments().getString("correo", "NA"));
        cajaPassword1.setText(getArguments().getString("contraseña", "NA"));
        cajaPassword2.setText(getArguments().getString("contraseña", "NA"));

        btnGuardar = view.findViewById(R.id.button_Guardar_EditarUsuario);
        btnCancelar = view.findViewById(R.id.button_Cancelar_EditarUsuario);
        btnEliminar = view.findViewById(R.id.button_Eliminar_EditarUsuario);
        btnGuardar.setOnClickListener(view1 -> {
            if(validarCampos()) {
                String url = "http://colectaubeta.atwebpages.com/api_cambios_usuarios.php";
                StringRequest recuest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        //Aqui se da retroalimentacion.
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(getContext(), "Error Api",Toast.LENGTH_SHORT).show();
                    }
                }) {
                    @Override
                    protected Map<String, String> getParams() {
                        Map<String, String> parametros = new HashMap<String, String>();
                        parametros.put("id", cajaId.getText().toString());
                        parametros.put("username", cajaNombre.getText().toString());
                        parametros.put("passaword", cajaPassword1.getText().toString());
                        parametros.put("email", cajaCorreo.getText().toString());

                        return parametros;
                    }
                };
                addToQueue(recuest);



                Toast.makeText(getContext(), "Guardado", Toast.LENGTH_LONG).show();
                Fragment nuevoFragmento = new FragmentUsuarios();
                FragmentTransaction transaction = getParentFragmentManager().beginTransaction();
                transaction.replace(R.id.content_frame, nuevoFragmento);
                transaction.commit();
                Objects.requireNonNull(((AppCompatActivity) requireActivity()).getSupportActionBar()).setTitle("Usuarios");
            }
        });
        btnCancelar.setOnClickListener(view1 -> new AlertDialog.Builder(requireContext())
                .setIcon(android.R.drawable.ic_dialog_alert).setTitle("Precaucion")
                .setMessage("¿Esta seguro de cancelar el registro del usuario?")
                .setPositiveButton("Si", (dialogInterface, i) -> {
                    Fragment nuevoFragmento = new FragmentUsuarios();
                    FragmentTransaction transaction = getParentFragmentManager().beginTransaction();
                    transaction.replace(R.id.content_frame, nuevoFragmento);
                    transaction.commit();
                    Objects.requireNonNull(((AppCompatActivity) requireActivity()).getSupportActionBar()).setTitle("Usuarios");
                })
                .setNegativeButton("No", (dialogInterface, i) -> dialogInterface.cancel())
                .show());
        btnEliminar.setOnClickListener(view1 -> new AlertDialog.Builder(requireContext())
                .setIcon(android.R.drawable.ic_dialog_alert).setTitle("Precaucion")
                .setMessage("¿Esta seguro de eliminar el usuario?")
                .setPositiveButton("Si", (dialogInterface, i) -> {
                    Toast.makeText(getContext(), "Eliminado", Toast.LENGTH_LONG).show();
                    Fragment nuevoFragmento = new FragmentUsuarios();
                    FragmentTransaction transaction = getParentFragmentManager().beginTransaction();
                    transaction.replace(R.id.content_frame, nuevoFragmento);
                    transaction.commit();
                    Objects.requireNonNull(((AppCompatActivity) requireActivity()).getSupportActionBar()).setTitle("Usuarios");
                })
                .setNegativeButton("No", (dialogInterface, i) -> dialogInterface.cancel())
                .show());

        return view;
    }

    private boolean validarCampos() {
        cajaNombre.setBackgroundResource(R.drawable.borde_cajas_login);
        cajaCorreo.setBackgroundResource(R.drawable.borde_cajas_login);
        cajaPassword1.setBackgroundResource(R.drawable.borde_cajas_login);
        cajaPassword2.setBackgroundResource(R.drawable.borde_cajas_login);
        boolean respuesta = true;
        StringBuilder cadenaRespuesta = new StringBuilder();

        String regexCorreo = "^(([^<>()\\[\\]\\\\.,;:\\s@”]+(\\.[^<>()\\[\\]\\\\.,;:\\s@”]+)*)|(“.+”))@((\\[[0–9]{1,3}\\.[0–9]{1,3}\\.[0–9]{1,3}\\.[0–9]{1,3}])|(([a-zA-Z\\-0–9]+\\.)+[a-zA-Z]{2,}))$";

        if(cajaNombre.getText().toString().trim().isEmpty()) {
            cadenaRespuesta.append("- Ingresa un nombre de usuario. \n\n");
            cajaNombre.setBackgroundResource(R.drawable.borde_cajas_donativo_error);
            respuesta = false;
        }

        if(cajaCorreo.getText().toString().trim().isEmpty()) {
            cadenaRespuesta.append("- Ingresa un email. \n\n");
            cajaCorreo.setBackgroundResource(R.drawable.borde_cajas_donativo_error);
            respuesta = false;
        } else {
            if(!cajaCorreo.getText().toString().trim().matches(regexCorreo)) {
                cadenaRespuesta.append("- Email no valido. \n\n");
                cajaCorreo.setBackgroundResource(R.drawable.borde_cajas_donativo_error);
                respuesta = false;
            }
        }

        if(cajaPassword1.getText().toString().trim().isEmpty()) {
            cadenaRespuesta.append("- Ingresa una contraseña. \n\n");
            cajaPassword1.setBackgroundResource(R.drawable.borde_cajas_donativo_error);
            respuesta = false;
        }

        if(cajaPassword2.getText().toString().trim().isEmpty()) {
            cadenaRespuesta.append("- Confirma tu contraseña. \n\n");
            cajaPassword2.setBackgroundResource(R.drawable.borde_cajas_donativo_error);
            respuesta = false;
        } else {
            if(!cajaPassword2.getText().toString().trim().equals(cajaPassword1.getText().toString().trim())) {
                cadenaRespuesta.append("- Error en confirmacion de contraseña. \n\n");
                cajaPassword1.setBackgroundResource(R.drawable.borde_cajas_donativo_error);
                cajaPassword2.setBackgroundResource(R.drawable.borde_cajas_donativo_error);
                respuesta = false;
            }
        }

        if(!respuesta) {
            new AlertDialog.Builder(requireContext())
                    .setIcon(android.R.drawable.ic_dialog_alert).setTitle("Errores detectados")
                    .setMessage(cadenaRespuesta)
                    .setPositiveButton("Entendido", (dialogInterface, i) -> dialogInterface.cancel())
                    .show()
            ;
        }

        return respuesta;
    }
}