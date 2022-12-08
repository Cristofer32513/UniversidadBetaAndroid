package usuario;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.colectau_beta.R;

import java.util.ArrayList;

import modelos.Usuario;

public class FragmentUsuarios extends Fragment {

    public static final String ARG_SECTION_TITLE = "section_number";
    EditText cajaBuscar;
    ArrayList<Usuario> listaUsuarios;
    RecyclerView recyclerViewUsuarios;

    /**
     * Crea una instancia prefabricada de {@link FragmentUsuarios}
     *
     * @param sectionTitle Título usado en el contenido
     * @return Instancia dle fragmento
     */
    public static FragmentUsuarios newInstance(String sectionTitle) {
        FragmentUsuarios fragment = new FragmentUsuarios();
        Bundle args = new Bundle();
        args.putString(ARG_SECTION_TITLE, sectionTitle);
        fragment.setArguments(args);
        return fragment;
    }

    public FragmentUsuarios() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.section_fragment_usuarios, container, false);

        cajaBuscar = view.findViewById(R.id.caja_busqueda_usuario);

        listaUsuarios = new ArrayList<>();

        recyclerViewUsuarios = (RecyclerView) view.findViewById(R.id.recyclerview_usuarios);
        recyclerViewUsuarios.setLayoutManager(new LinearLayoutManager(getContext()));

        LlenarLista();

        ListaUsuariosAdapter adapter=new ListaUsuariosAdapter(listaUsuarios);
        /*adapter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                BaseDeDatos bd = new BaseDeDatos(ActivityConsultaUsuarios.this);
                Usuario us = bd.obtenerUsuario(listaUsuarios.get(recyclerViewUsuarios.getChildAdapterPosition(v)).getIdEmpleado());

                Intent i = new Intent(ActivityConsultaUsuarios.this, ActivityEditarUsuario.class);
                i.putExtra("enable", false);
                i.putExtra("id", us.getIdEmpleado());
                i.putExtra("usuario", us.getUsuario());
                i.putExtra("contraseña", us.getContraseña());
                startActivity(i);
                finish();
            }
        });
         */
        recyclerViewUsuarios.setAdapter(adapter);


        return view;
    }

    private void LlenarLista() {
        listaUsuarios.add(new Usuario(1, "user1", "correo1", "contraseña1"));
        listaUsuarios.add(new Usuario(2, "user2", "correo2", "contraseña2"));
        listaUsuarios.add(new Usuario(3, "user3", "correo3", "contraseña1"));
        listaUsuarios.add(new Usuario(4, "user4", "correo4", "contraseña4"));
        listaUsuarios.add(new Usuario(5, "user5", "correo5", "contraseña5"));
        listaUsuarios.add(new Usuario(6, "user6", "correo6", "contraseña6"));
    }

}