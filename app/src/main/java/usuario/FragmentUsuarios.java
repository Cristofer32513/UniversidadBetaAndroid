package usuario;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.example.colectau_beta.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import java.util.ArrayList;
import java.util.Objects;
import java.util.Random;
import controlador.BaseVolleyFragment;
import modelos.Usuario;

public class FragmentUsuarios extends BaseVolleyFragment {

    EditText cajaBuscar;
    ImageButton btnBuscar;
    ArrayList<Usuario> listaUsuarios;
    RecyclerView recyclerViewUsuarios;
    FloatingActionButton buttonAdd;

    public FragmentUsuarios() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.section_fragment_usuarios, container, false);

        cajaBuscar = view.findViewById(R.id.caja_busqueda_usuario);
        btnBuscar = view.findViewById(R.id.btn_buscar_usuario);
        btnBuscar.setOnClickListener(view1 -> {
            if(validarCampos()) {
                Toast.makeText(getContext(), "Buscando...", Toast.LENGTH_LONG).show();
            }
        });

        listaUsuarios = new ArrayList<>();

        recyclerViewUsuarios = (RecyclerView) view.findViewById(R.id.recyclerview_usuarios);
        recyclerViewUsuarios.setLayoutManager(new LinearLayoutManager(getContext()));

        LlenarLista();

        ListaUsuariosAdapter adapter=new ListaUsuariosAdapter(listaUsuarios);
        adapter.setOnClickListener(v -> {
            Bundle args = new Bundle();
            args.putString("id", listaUsuarios.get(recyclerViewUsuarios.getChildAdapterPosition(v)).getIdUsuario()+"");
            args.putString("nombre", listaUsuarios.get(recyclerViewUsuarios.getChildAdapterPosition(v)).getNombreUsuario());
            args.putString("correo", listaUsuarios.get(recyclerViewUsuarios.getChildAdapterPosition(v)).getCorreo());
            args.putString("contraseña", listaUsuarios.get(recyclerViewUsuarios.getChildAdapterPosition(v)).getPassword());

            Fragment nuevoFragmento = new FragmentEditarUsuario();
            nuevoFragmento.setArguments(args);
            FragmentTransaction transaction = getParentFragmentManager().beginTransaction();
            transaction.replace(R.id.content_frame, nuevoFragmento);
            transaction.commit();
            Objects.requireNonNull(((AppCompatActivity) requireActivity()).getSupportActionBar()).setTitle("Editar Usuario");
        });
        recyclerViewUsuarios.setAdapter(adapter);

        buttonAdd = view.findViewById(R.id.button_Add);
        buttonAdd.setOnClickListener(view1 -> {
            Fragment nuevoFragmento = new FragmentAgregarUsuario();
            FragmentTransaction transaction = getParentFragmentManager().beginTransaction();
            transaction.replace(R.id.content_frame, nuevoFragmento);
            transaction.commit();
            Objects.requireNonNull(((AppCompatActivity) requireActivity()).getSupportActionBar()).setTitle("Agregar Usuario");
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

    private boolean validarCampos() {
        cajaBuscar.setBackgroundResource(R.drawable.borde_cajas_login);
        boolean respuesta = true;
        StringBuilder cadenaRespuesta = new StringBuilder();

        if(cajaBuscar.getText().toString().trim().isEmpty()) {
            cadenaRespuesta.append("- Ingresa un nombre de usuario. \n\n");
            cajaBuscar.setBackgroundResource(R.drawable.borde_cajas_donativo_error);
            respuesta = false;
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