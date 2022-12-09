package usuario;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.example.colectau_beta.R;

public class FragmentEditarUsuario extends Fragment {

    public static final String ARG_SECTION_TITLE = "section_number";
    EditText cajaId, cajaNombre, cajaCorreo, cajaContraseña1, cajaContraseña2;
    Button btnGuardar, btnCancelar, btnEliminar;

    public static FragmentEditarUsuario newInstance(String sectionTitle) {
        FragmentEditarUsuario fragment = new FragmentEditarUsuario();
        Bundle args = new Bundle();
        args.putString(ARG_SECTION_TITLE, sectionTitle);
        fragment.setArguments(args);
        return fragment;
    }

    public FragmentEditarUsuario() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.section_fragment_editar_usuario, container, false);

        cajaId = view.findViewById(R.id.editText_Id_EditarUsuario);
        cajaNombre = view.findViewById(R.id.editText_Nombre_EditarUsuario);
        cajaCorreo = view.findViewById(R.id.editText_Correo_EditarUsuario);
        cajaContraseña1 = view.findViewById(R.id.editText_Contraseña1_EditarUsuario);
        cajaContraseña2 = view.findViewById(R.id.editText_Contraseña2_EditarUsuario);

        cajaId.setText(getArguments().getString("id", "NA"));
        cajaNombre.setText(getArguments().getString("nombre", "NA"));
        cajaCorreo.setText(getArguments().getString("correo", "NA"));
        cajaContraseña1.setText(getArguments().getString("contraseña", "NA"));
        cajaContraseña2.setText(getArguments().getString("contraseña", "NA"));

        btnGuardar = view.findViewById(R.id.button_Guardar_EditarUsuario);
        btnCancelar = view.findViewById(R.id.button_Cancelar_EditarUsuario);
        btnEliminar = view.findViewById(R.id.button_Eliminar_EditarUsuario);
        btnGuardar.setOnClickListener(view1 -> {
            Toast.makeText(getContext(), "Guardado", Toast.LENGTH_LONG).show();
        });
        btnCancelar.setOnClickListener(view1 -> {
            Bundle args2 = new Bundle();
            args2.putString(FragmentUsuarios.ARG_SECTION_TITLE, "Usuarios");
            Fragment nuevoFragmento = new FragmentUsuarios();
            nuevoFragmento.setArguments(args2);
            FragmentTransaction transaction = getFragmentManager().beginTransaction();
            transaction.replace(R.id.content_frame, nuevoFragmento);
            transaction.commit();
            ((AppCompatActivity) getActivity()).getSupportActionBar().setTitle("Usuarios");
        });
        btnEliminar.setOnClickListener(view1 -> {
            Toast.makeText(getContext(), "Eliminado", Toast.LENGTH_LONG).show();
        });

        return view;
    }
}