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
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.colectau_beta.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.Random;

import modelos.Usuario;

public class FragmentAgregarUsuario extends Fragment {

    public static final String ARG_SECTION_TITLE = "section_number";
    EditText cajaNombre, cajaCorreo, cajaContraseña1, cajaContraseña2;
    Button btnAgregar, btnCancelar;

    public static FragmentAgregarUsuario newInstance(String sectionTitle) {
        FragmentAgregarUsuario fragment = new FragmentAgregarUsuario();
        Bundle args = new Bundle();
        args.putString(ARG_SECTION_TITLE, sectionTitle);
        fragment.setArguments(args);
        return fragment;
    }

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
            Toast.makeText(getContext(), "Agregado", Toast.LENGTH_LONG).show();
        });
        btnCancelar.setOnClickListener(view1 -> {
            Bundle args2 = new Bundle();
            args2.putString(FragmentUsuarios.ARG_SECTION_TITLE, "Usuarios");
            Fragment nuevoFragmento = FragmentUsuarios.newInstance("Usuarios");
            nuevoFragmento.setArguments(args2);
            FragmentTransaction transaction = getFragmentManager().beginTransaction();
            transaction.replace(R.id.content_frame, nuevoFragmento);
            transaction.commit();
            ((AppCompatActivity) getActivity()).getSupportActionBar().setTitle("Usuarios");
        });

        return view;
    }
}