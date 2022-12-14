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
import com.android.volley.Request;
import com.android.volley.toolbox.StringRequest;
import com.example.colectau_beta.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
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

    public FragmentUsuarios() { }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.section_fragment_usuarios, container, false);

        listaUsuarios = new ArrayList<>();
        recyclerViewUsuarios = view.findViewById(R.id.recyclerview_usuarios);
        cajaBuscar = view.findViewById(R.id.caja_busqueda_usuario);
        btnBuscar = view.findViewById(R.id.btn_buscar_usuario);
        btnBuscar.setOnClickListener(view1 -> {
            if(validarCampos()) {
                //PONER API BUSQUEDA POR USERNAME
                listaUsuarios.clear();
                String url = "http://colectaubeta.atwebpages.com/API/api_consultas_usuarios_username.php";
                StringRequest recuest = new StringRequest(Request.Method.POST, url, response -> {
                    try {
                        JSONArray json = new JSONArray(response);
                        Toast.makeText(getContext(), json.length() + getString(R.string.coincidencias_encontradas), Toast.LENGTH_LONG).show();
                        for (int i=0; i<json.length();i++) {
                            JSONObject jsonData = json.getJSONObject(i);
                            String iduser = jsonData.getString("iduser");
                            String username = jsonData.getString("username");
                            String password = jsonData.getString("passaword");
                            String email = jsonData.getString("email");

                            Usuario user = new Usuario(Integer.parseInt(iduser),
                                    seleccionarImagenAleatoria(), username, email, password);

                            agregar(user);
                        }
                        mostrar();
                    } catch (JSONException e) {e.printStackTrace();}
                }, error -> mostrarError(getString(R.string.falla_api))) {
                    @Override
                    protected Map<String, String> getParams() {
                        Map<String, String> parametros = new HashMap<>();
                        parametros.put("username", cajaBuscar.getText().toString());

                        return parametros;
                    }
                };
                addToQueue(recuest);
            }
        });

        LlenarLista();

        buttonAdd = view.findViewById(R.id.button_Add);
        buttonAdd.setOnClickListener(view1 -> {
            Fragment nuevoFragmento = new FragmentAgregarUsuario();
            FragmentTransaction transaction = getParentFragmentManager().beginTransaction();
            transaction.replace(R.id.content_frame, nuevoFragmento);
            transaction.commit();
            Objects.requireNonNull(((AppCompatActivity) requireActivity()).getSupportActionBar()).setTitle(getString(R.string.agregar_usuario));
        });

        return view;
    }

    private void LlenarLista() {
        listaUsuarios.clear();
        String url = "http://colectaubeta.atwebpages.com/API/api_consultas_usuarios.php";
        StringRequest recuest = new StringRequest(Request.Method.GET, url, response -> {
            try {
                JSONArray json = new JSONArray(response);
                Toast.makeText(getContext(), json.length() + getString(R.string.usuarios_encontrados), Toast.LENGTH_LONG).show();
                for (int i=0; i<json.length();i++) {
                    JSONObject jsonData = json.getJSONObject(i);
                    String iduser = jsonData.getString("iduser");
                    String username = jsonData.getString("username");
                    String password = jsonData.getString("passaword");
                    String email = jsonData.getString("email");

                    Usuario user = new Usuario(Integer.parseInt(iduser),
                            seleccionarImagenAleatoria(), username, email, password);

                    agregar(user);
                }
                mostrar();
            } catch (JSONException e) {e.printStackTrace();}
        }, error -> mostrarError(getString(R.string.falla_api)));
        addToQueue(recuest);

        // listaUsuarios.add(new Usuario(1, seleccionarImagenAleatoria(),"user1", "correo1", "contrase??a1"));
        // listaUsuarios.add(new Usuario(2, seleccionarImagenAleatoria(),"user2", "correo2", "contrase??a2"));
        // listaUsuarios.add(new Usuario(3, seleccionarImagenAleatoria(),"user3", "correo3", "contrase??a1"));
        // listaUsuarios.add(new Usuario(4, seleccionarImagenAleatoria(),"user4", "correo4", "contrase??a4"));
        // listaUsuarios.add(new Usuario(5, seleccionarImagenAleatoria(),"user5", "correo5", "contrase??a5"));
        // listaUsuarios.add(new Usuario(6, seleccionarImagenAleatoria(),"user6", "correo6", "contrase??a6"));
    }

    public void mostrarError(String error) {
        new AlertDialog.Builder(requireContext())
            .setIcon(android.R.drawable.ic_dialog_alert).setTitle(getString(R.string.precaucion))
            .setMessage(error)
            .setPositiveButton(getString(R.string.entendido), (dialogInterface, i) -> dialogInterface.cancel()).show()
        ;
    }

    private void agregar(Usuario user) {
        listaUsuarios.add(user);
    }

    private void mostrar() {
        System.out.println(listaUsuarios.toString());
        recyclerViewUsuarios.setLayoutManager(new LinearLayoutManager(getContext()));
        ListaUsuariosAdapter adapter=new ListaUsuariosAdapter(listaUsuarios);
        adapter.setOnClickListener(v -> {
            Bundle args = new Bundle();
            args.putString("id", listaUsuarios.get(recyclerViewUsuarios.getChildAdapterPosition(v)).getIdUsuario()+"");
            args.putString("nombre", listaUsuarios.get(recyclerViewUsuarios.getChildAdapterPosition(v)).getNombreUsuario());
            args.putString("correo", listaUsuarios.get(recyclerViewUsuarios.getChildAdapterPosition(v)).getCorreo());
            args.putString("contrase??a", listaUsuarios.get(recyclerViewUsuarios.getChildAdapterPosition(v)).getPassword());

            Fragment nuevoFragmento = new FragmentEditarUsuario();
            nuevoFragmento.setArguments(args);
            FragmentTransaction transaction = getParentFragmentManager().beginTransaction();
            transaction.replace(R.id.content_frame, nuevoFragmento);
            transaction.commit();
            Objects.requireNonNull(((AppCompatActivity) requireActivity()).getSupportActionBar()).setTitle(R.string.editar_usuario);
        });
        recyclerViewUsuarios.setAdapter(adapter);
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
        String cadenaRespuesta = "";

        if(cajaBuscar.getText().toString().trim().isEmpty()) {
            cadenaRespuesta = (getString(R.string.ingresa_nombre_usuario)) + "\n\n";
            cajaBuscar.setBackgroundResource(R.drawable.borde_cajas_donativo_error);
            respuesta = false;
        }

        if(!respuesta) mostrarError(cadenaRespuesta);

        return respuesta;
    }
}