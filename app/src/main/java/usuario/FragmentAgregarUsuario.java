package usuario;

import android.content.DialogInterface;
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
import com.example.colectau_beta.R;

public class FragmentAgregarUsuario extends Fragment {

    EditText cajaNombre, cajaCorreo, cajaContraseña1, cajaContraseña2;
    Button btnAgregar, btnCancelar;

    public FragmentAgregarUsuario() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.section_fragment_agregar_usuario, container, false);

        cajaNombre = view.findViewById(R.id.editText_Nombre_AgregarUsuario);
        cajaCorreo = view.findViewById(R.id.editText_Correo_AgregarUsuario);
        cajaContraseña1 = view.findViewById(R.id.editText_Contraseña1_AgregarUsuario);
        cajaContraseña2 = view.findViewById(R.id.editText_Contraseña2_AgregarUsuario);

        btnAgregar = view.findViewById(R.id.button_Agregar_AgregarUsuario);
        btnCancelar = view.findViewById(R.id.button_Cancelar_AgregarUsuario);
        btnAgregar.setOnClickListener(view1 -> {
            if(validarCampos()) {
                Toast.makeText(getContext(), "Agregado", Toast.LENGTH_LONG).show();
                Fragment nuevoFragmento = new FragmentUsuarios();
                FragmentTransaction transaction = getFragmentManager().beginTransaction();
                transaction.replace(R.id.content_frame, nuevoFragmento);
                transaction.commit();
                ((AppCompatActivity) getActivity()).getSupportActionBar().setTitle("Usuarios");
            }
        });
        btnCancelar.setOnClickListener(view1 -> {
            new AlertDialog.Builder(getContext())
                    .setIcon(android.R.drawable.ic_dialog_alert).setTitle("Precaucion")
                    .setMessage("¿Esta seguro de cancelar el registro del usuario?")
                    .setPositiveButton("Si", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            Fragment nuevoFragmento = new FragmentUsuarios();
                            FragmentTransaction transaction = getFragmentManager().beginTransaction();
                            transaction.replace(R.id.content_frame, nuevoFragmento);
                            transaction.commit();
                            ((AppCompatActivity) getActivity()).getSupportActionBar().setTitle("Usuarios");
                        }
                    })
                    .setNegativeButton("No", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            dialogInterface.cancel();
                        }
                    })
                    .show()
            ;
        });

        return view;
    }

    private boolean validarCampos() {
        cajaNombre.setBackgroundResource(R.drawable.borde_cajas_login);
        cajaCorreo.setBackgroundResource(R.drawable.borde_cajas_login);
        cajaContraseña1.setBackgroundResource(R.drawable.borde_cajas_login);
        cajaContraseña2.setBackgroundResource(R.drawable.borde_cajas_login);
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

        if(cajaContraseña1.getText().toString().trim().isEmpty()) {
            cadenaRespuesta.append("- Ingresa una contraseña. \n\n");
            cajaContraseña1.setBackgroundResource(R.drawable.borde_cajas_donativo_error);
            respuesta = false;
        }

        if(cajaContraseña2.getText().toString().trim().isEmpty()) {
            cadenaRespuesta.append("- Confirma tu contraseña. \n\n");
            cajaContraseña2.setBackgroundResource(R.drawable.borde_cajas_donativo_error);
            respuesta = false;
        } else {
            if(!cajaContraseña2.getText().toString().trim().equals(cajaContraseña1.getText().toString().trim())) {
                cadenaRespuesta.append("- Error en confirmacion de contraseña. \n\n");
                cajaContraseña1.setBackgroundResource(R.drawable.borde_cajas_donativo_error);
                cajaContraseña2.setBackgroundResource(R.drawable.borde_cajas_donativo_error);
                respuesta = false;
            }
        }

        if(!respuesta) {
            new AlertDialog.Builder(getContext())
                    .setIcon(android.R.drawable.ic_dialog_alert).setTitle("Errores detectados")
                    .setMessage(cadenaRespuesta)
                    .setPositiveButton("Entendido", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            dialogInterface.cancel();
                        }
                    })
                    .show()
            ;
        }

        return respuesta;
    }
}