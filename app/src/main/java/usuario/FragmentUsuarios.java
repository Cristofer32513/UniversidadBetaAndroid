package usuario;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.colectau_beta.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.Random;

import modelos.Usuario;

public class FragmentUsuarios extends Fragment {

    public static final String ARG_SECTION_TITLE = "section_number";
    EditText cajaBuscar;
    ArrayList<Usuario> listaUsuarios;
    RecyclerView recyclerViewUsuarios;
    FloatingActionButton buttonAdd;

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

        buttonAdd = view.findViewById(R.id.button_Add);
        buttonAdd.setOnClickListener(view1 -> {
            Toast.makeText(getContext(), "Add", Toast.LENGTH_LONG).show();

        });


        return view;
    }

    private void LlenarLista() {
        listaUsuarios.add(new Usuario(1, seleccionarImagenAleatoria(),"user1", "correo1", "contraseña1"));
        listaUsuarios.add(new Usuario(2, seleccionarImagenAleatoria(),"user2", "correo2", "contraseña2"));
        listaUsuarios.add(new Usuario(3, seleccionarImagenAleatoria(),"user3", "correo3", "contraseña1"));
        listaUsuarios.add(new Usuario(4, seleccionarImagenAleatoria(),"user4", "correo4", "contraseña4"));
        listaUsuarios.add(new Usuario(5, seleccionarImagenAleatoria(),"user5", "correo5", "contraseña5"));
        listaUsuarios.add(new Usuario(6, seleccionarImagenAleatoria(),"user6", "correo6", "contraseña6"));
    }

    private int seleccionarImagenAleatoria() {
        switch (new Random().nextInt(5)) {
            case 0: return R.drawable.perfil1;
            case 1: return R.drawable.perfil2;
            case 2: return R.drawable.perfil3;
            case 3: return R.drawable.perfil4;
            default: return R.drawable.perfil5;
        }
    }

}