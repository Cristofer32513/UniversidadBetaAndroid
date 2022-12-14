package donacion;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.toolbox.StringRequest;
import com.example.colectau_beta.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import controlador.BaseVolleyFragment;
import modelos.Donacion;

public class FragmentDonaciones extends BaseVolleyFragment {

    Spinner spinnerBuscar;
    EditText cajaBuscar;
    ImageButton btnBuscar;
    ArrayList<Donacion> listaDonaciones;
    RecyclerView recyclerViewDonaciones;
    String indicacionCaja;

    public FragmentDonaciones() {}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.section_fragment_donaciones, container, false);

        listaDonaciones = new ArrayList<>();
        recyclerViewDonaciones = view.findViewById(R.id.recyclerview_donativos);
        spinnerBuscar = view.findViewById(R.id.spinner_BuscarDonativo);
        cajaBuscar = view.findViewById(R.id.caja_busqueda_donativo);
        cajaBuscar.setHint(R.string.selecciona_filtro2);
        btnBuscar = view.findViewById(R.id.btn_buscar_donativo);
        ArrayAdapter<CharSequence> adapterBuscar = ArrayAdapter.createFromResource(getContext(), R.array.spiner_buscar_donativo, R.layout.spinner_items_style);
        spinnerBuscar.setAdapter(adapterBuscar);
        spinnerBuscar.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                cajaBuscar.setBackgroundResource(R.drawable.borde_cajas_login);
                seleccionarHintCaja(i);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {}
        });

        btnBuscar.setOnClickListener(view1 -> {
            if(validarCampos()) {
                if(spinnerBuscar.getSelectedItemPosition() == 1) LlenarListaNombre();
                else if(spinnerBuscar.getSelectedItemPosition() == 2) LlenarListaPrimerAp();
                else if(spinnerBuscar.getSelectedItemPosition() == 3) LlenarListaSegundoAp();
                else if(spinnerBuscar.getSelectedItemPosition() == 4) LlenarListaCategoria();
                else if(spinnerBuscar.getSelectedItemPosition() == 5) LlenarListaMetodoPago();
            }
        });

        LlenarLista();

        return view;
    }

    private void seleccionarHintCaja(int i) {
        switch (i) {
            case 1:
                cajaBuscar.setHint(getString(R.string.nombre));
                indicacionCaja = getString(R.string.ingresa_un_nombre);
                break;
            case 2:
                cajaBuscar.setHint(getString(R.string.primer_ap));
                indicacionCaja = getString(R.string.ingresa_primer_apellido);
                break;
            case 3:
                cajaBuscar.setHint(getString(R.string.segundo_ap));
                indicacionCaja = getString(R.string.ingresa_segundo_ap);
                break;
            case 4:
                cajaBuscar.setHint(getString(R.string.categoria));
                indicacionCaja = getString(R.string.ingresa_categoria);
                break;
            case 5:
                cajaBuscar.setHint(getString(R.string.metodo_de_pago));
                indicacionCaja = getString(R.string.ingresa_metodo_pago);
                break;
            default:
                cajaBuscar.setHint(R.string.selecciona_filtro2);
                indicacionCaja = getString(R.string.selecciona_filtro);
                break;
        }
    }

    private void LlenarLista() {
        listaDonaciones.clear();
        String url = "http://colectaubeta.atwebpages.com/API/api_consultas_donaciones.php";
        StringRequest recuest = new StringRequest(Request.Method.GET, url, response -> {
            try {
                JSONArray json = new JSONArray(response);
                Toast.makeText(getContext(), json.length() + getString(R.string.donaciones_encontradas), Toast.LENGTH_LONG).show();
                for (int i=0; i<json.length();i++) {
                    JSONObject jsonData = json.getJSONObject(i);
                    String iddonaciones = jsonData.getString("iddonaciones");
                    String nombre = jsonData.getString("nombre");
                    String ap1 = jsonData.getString("ap1");
                    String ap2 = jsonData.getString("ap2");
                    String categ = jsonData.getString("categ");
                    String prometido = jsonData.getString("prometido");
                    String abonado = jsonData.getString("abonado");
                    String fechabono = jsonData.getString("fechabono");
                    String fechalimite = jsonData.getString("fechalimite");
                    String formapago = jsonData.getString("formapago");
                    String plazos = jsonData.getString("plazos");
                    String plazos_abonados = jsonData.getString("plazos_abonados");

                    Donacion donacion = new Donacion(Integer.parseInt(iddonaciones), nombre, ap1, ap2,
                            categ, Integer.parseInt(prometido), Integer.parseInt(abonado), fechabono,
                            fechalimite, formapago, Integer.parseInt(plazos), Integer.parseInt(plazos_abonados));

                    agregar(donacion);
                }
                mostrar();
            } catch (JSONException e) {e.printStackTrace();}
        }, error -> mostrarError(getString(R.string.falla_api)));
        addToQueue(recuest);


        /*listaDonaciones.add(new Donacion(1, "don1", "don1", "don1", "don1", 1, 1, "don1", "don1", "don1", 1, 1));
        listaDonaciones.add(new Donacion(2, "don2", "don2", "don2", "don2", 2, 2, "don2", "don2", "don2", 2, 2));
        listaDonaciones.add(new Donacion(3, "don3", "don3", "don3", "don3", 3, 3, "don3", "don3", "don3", 3, 3));
        listaDonaciones.add(new Donacion(4, "don4", "don4", "don4", "don4", 4, 4, "don4", "don4", "don4", 4, 4));
         */
    }

    private void LlenarListaNombre() {
        listaDonaciones.clear();
        String url = "http://colectaubeta.atwebpages.com/API/api_consultas_donaciones_nombre.php";
        StringRequest recuest = new StringRequest(Request.Method.POST, url, response -> {
            try {
                JSONArray json = new JSONArray(response);
                Toast.makeText(getContext(), json.length() + getString(R.string.coincidencias_encontradas), Toast.LENGTH_LONG).show();
                for (int i=0; i<json.length();i++) {
                    JSONObject jsonData = json.getJSONObject(i);
                    String iddonaciones = jsonData.getString("iddonaciones");
                    String nombre = jsonData.getString("nombre");
                    String ap1 = jsonData.getString("ap1");
                    String ap2 = jsonData.getString("ap2");
                    String categ = jsonData.getString("categ");
                    String prometido = jsonData.getString("prometido");
                    String abonado = jsonData.getString("abonado");
                    String fechabono = jsonData.getString("fechabono");
                    String fechalimite = jsonData.getString("fechalimite");
                    String formapago = jsonData.getString("formapago");
                    String plazos = jsonData.getString("plazos");
                    String plazos_abonados = jsonData.getString("plazos_abonados");

                    Donacion donacion = new Donacion(Integer.parseInt(iddonaciones), nombre, ap1, ap2,
                            categ, Integer.parseInt(prometido), Integer.parseInt(abonado), fechabono,
                            fechalimite, formapago, Integer.parseInt(plazos), Integer.parseInt(plazos_abonados));

                    agregar(donacion);
                }
                mostrar();
            } catch (JSONException e) {e.printStackTrace();}
        }, error -> mostrarError(getString(R.string.falla_api))) {
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> parametros = new HashMap<>();
                parametros.put("nombre", cajaBuscar.getText().toString());

                return parametros;
            }
        };
        addToQueue(recuest);
    }

    private void LlenarListaPrimerAp() {
        listaDonaciones.clear();
        String url = "http://colectaubeta.atwebpages.com/API/api_consultas_donaciones_ap1.php";
        StringRequest recuest = new StringRequest(Request.Method.POST, url, response -> {
            try {
                JSONArray json = new JSONArray(response);
                Toast.makeText(getContext(), json.length() + getString(R.string.coincidencias_encontradas), Toast.LENGTH_LONG).show();
                for (int i=0; i<json.length();i++) {
                    JSONObject jsonData = json.getJSONObject(i);
                    String iddonaciones = jsonData.getString("iddonaciones");
                    String nombre = jsonData.getString("nombre");
                    String ap1 = jsonData.getString("ap1");
                    String ap2 = jsonData.getString("ap2");
                    String categ = jsonData.getString("categ");
                    String prometido = jsonData.getString("prometido");
                    String abonado = jsonData.getString("abonado");
                    String fechabono = jsonData.getString("fechabono");
                    String fechalimite = jsonData.getString("fechalimite");
                    String formapago = jsonData.getString("formapago");
                    String plazos = jsonData.getString("plazos");
                    String plazos_abonados = jsonData.getString("plazos_abonados");

                    Donacion donacion = new Donacion(Integer.parseInt(iddonaciones), nombre, ap1, ap2,
                            categ, Integer.parseInt(prometido), Integer.parseInt(abonado), fechabono,
                            fechalimite, formapago, Integer.parseInt(plazos), Integer.parseInt(plazos_abonados));

                    agregar(donacion);
                }
                mostrar();
            } catch (JSONException e) {e.printStackTrace();}
        }, error -> mostrarError(getString(R.string.falla_api))) {
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> parametros = new HashMap<>();
                parametros.put("ap1", cajaBuscar.getText().toString());

                return parametros;
            }
        };
        addToQueue(recuest);
    }

    private void LlenarListaSegundoAp() {
        listaDonaciones.clear();
        String url = "http://colectaubeta.atwebpages.com/API/api_consultas_donaciones_ap2.php";
        StringRequest recuest = new StringRequest(Request.Method.POST, url, response -> {
            try {
                JSONArray json = new JSONArray(response);
                Toast.makeText(getContext(), json.length() + getString(R.string.coincidencias_encontradas), Toast.LENGTH_LONG).show();
                for (int i=0; i<json.length();i++) {
                    JSONObject jsonData = json.getJSONObject(i);
                    String iddonaciones = jsonData.getString("iddonaciones");
                    String nombre = jsonData.getString("nombre");
                    String ap1 = jsonData.getString("ap1");
                    String ap2 = jsonData.getString("ap2");
                    String categ = jsonData.getString("categ");
                    String prometido = jsonData.getString("prometido");
                    String abonado = jsonData.getString("abonado");
                    String fechabono = jsonData.getString("fechabono");
                    String fechalimite = jsonData.getString("fechalimite");
                    String formapago = jsonData.getString("formapago");
                    String plazos = jsonData.getString("plazos");
                    String plazos_abonados = jsonData.getString("plazos_abonados");

                    Donacion donacion = new Donacion(Integer.parseInt(iddonaciones), nombre, ap1, ap2,
                            categ, Integer.parseInt(prometido), Integer.parseInt(abonado), fechabono,
                            fechalimite, formapago, Integer.parseInt(plazos), Integer.parseInt(plazos_abonados));

                    agregar(donacion);
                }
                mostrar();
            } catch (JSONException e) {e.printStackTrace();}
        }, error -> mostrarError(getString(R.string.falla_api))) {
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> parametros = new HashMap<>();
                parametros.put("ap2", cajaBuscar.getText().toString());

                return parametros;
            }
        };
        addToQueue(recuest);
    }

    private void LlenarListaCategoria() {
        listaDonaciones.clear();
        String url = "http://colectaubeta.atwebpages.com/API/api_consultas_donaciones_categoria.php";
        StringRequest recuest = new StringRequest(Request.Method.POST, url, response -> {
            try {
                JSONArray json = new JSONArray(response);
                Toast.makeText(getContext(), json.length() + getString(R.string.coincidencias_encontradas), Toast.LENGTH_LONG).show();
                for (int i=0; i<json.length();i++) {
                    JSONObject jsonData = json.getJSONObject(i);
                    String iddonaciones = jsonData.getString("iddonaciones");
                    String nombre = jsonData.getString("nombre");
                    String ap1 = jsonData.getString("ap1");
                    String ap2 = jsonData.getString("ap2");
                    String categ = jsonData.getString("categ");
                    String prometido = jsonData.getString("prometido");
                    String abonado = jsonData.getString("abonado");
                    String fechabono = jsonData.getString("fechabono");
                    String fechalimite = jsonData.getString("fechalimite");
                    String formapago = jsonData.getString("formapago");
                    String plazos = jsonData.getString("plazos");
                    String plazos_abonados = jsonData.getString("plazos_abonados");

                    Donacion donacion = new Donacion(Integer.parseInt(iddonaciones), nombre, ap1, ap2,
                            categ, Integer.parseInt(prometido), Integer.parseInt(abonado), fechabono,
                            fechalimite, formapago, Integer.parseInt(plazos), Integer.parseInt(plazos_abonados));

                    agregar(donacion);
                }
                mostrar();
            } catch (JSONException e) {e.printStackTrace();}
        }, error -> mostrarError(getString(R.string.falla_api))) {
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> parametros = new HashMap<>();
                parametros.put("categ", cajaBuscar.getText().toString());

                return parametros;
            }
        };
        addToQueue(recuest);
    }

    private void LlenarListaMetodoPago() {
        listaDonaciones.clear();
        String url = "http://colectaubeta.atwebpages.com/API/api_consultas_donaciones_metodo_pago.php";
        StringRequest recuest = new StringRequest(Request.Method.POST, url, response -> {
            try {
                JSONArray json = new JSONArray(response);
                Toast.makeText(getContext(), json.length() + getString(R.string.coincidencias_encontradas), Toast.LENGTH_LONG).show();
                for (int i=0; i<json.length();i++) {
                    JSONObject jsonData = json.getJSONObject(i);
                    String iddonaciones = jsonData.getString("iddonaciones");
                    String nombre = jsonData.getString("nombre");
                    String ap1 = jsonData.getString("ap1");
                    String ap2 = jsonData.getString("ap2");
                    String categ = jsonData.getString("categ");
                    String prometido = jsonData.getString("prometido");
                    String abonado = jsonData.getString("abonado");
                    String fechabono = jsonData.getString("fechabono");
                    String fechalimite = jsonData.getString("fechalimite");
                    String formapago = jsonData.getString("formapago");
                    String plazos = jsonData.getString("plazos");
                    String plazos_abonados = jsonData.getString("plazos_abonados");

                    Donacion donacion = new Donacion(Integer.parseInt(iddonaciones), nombre, ap1, ap2,
                            categ, Integer.parseInt(prometido), Integer.parseInt(abonado), fechabono,
                            fechalimite, formapago, Integer.parseInt(plazos), Integer.parseInt(plazos_abonados));

                    agregar(donacion);
                }
                mostrar();
            } catch (JSONException e) {e.printStackTrace();}
        }, error -> mostrarError(getString(R.string.falla_api))) {
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> parametros = new HashMap<>();
                parametros.put("formapago", cajaBuscar.getText().toString());

                return parametros;
            }
        };
        addToQueue(recuest);
    }

    public void mostrarError(String error) {
        new AlertDialog.Builder(requireContext())
                .setIcon(android.R.drawable.ic_dialog_alert).setTitle(getString(R.string.precaucion))
                .setMessage(error)
                .setPositiveButton(getString(R.string.entendido), (dialogInterface, i) -> dialogInterface.cancel()).show()
        ;
    }

    private void agregar(Donacion donacion) {
        listaDonaciones.add(donacion);
    }

    private void mostrar() {
        System.out.println(listaDonaciones.toString());
        recyclerViewDonaciones.setLayoutManager(new LinearLayoutManager(getContext()));
        ListaDonacionesAdapter adapter=new ListaDonacionesAdapter(listaDonaciones);
        /*adapter.setOnClickListener(v -> {
            Bundle args = new Bundle();
            args.putString("id", listaDonaciones.get(recyclerViewDonaciones.getChildAdapterPosition(v)).getIdDonacion()+"");
            args.putString("nombre", listaDonaciones.get(recyclerViewDonaciones.getChildAdapterPosition(v)).getNombreDonador());
            args.putString("ap1", listaDonaciones.get(recyclerViewDonaciones.getChildAdapterPosition(v)).getPrimerApDonador());
            args.putString("ap2", listaDonaciones.get(recyclerViewDonaciones.getChildAdapterPosition(v)).getSegundoApDonador());
            args.putString("categoria", listaDonaciones.get(recyclerViewDonaciones.getChildAdapterPosition(v)).getCategoria());
            args.putString("prometido", listaDonaciones.get(recyclerViewDonaciones.getChildAdapterPosition(v)).getPrometido()+"");
            args.putString("abonado", listaDonaciones.get(recyclerViewDonaciones.getChildAdapterPosition(v)).getAbonado()+"");
            args.putString("fechaAbono", listaDonaciones.get(recyclerViewDonaciones.getChildAdapterPosition(v)).getFechaAbono());
            args.putString("fechaLimite", listaDonaciones.get(recyclerViewDonaciones.getChildAdapterPosition(v)).getFechaLimite());
            args.putString("formaPago", listaDonaciones.get(recyclerViewDonaciones.getChildAdapterPosition(v)).getFormaPago());
            args.putString("plazos", listaDonaciones.get(recyclerViewDonaciones.getChildAdapterPosition(v)).getPlazos()+"");
            args.putString("plazosAbonados", listaDonaciones.get(recyclerViewDonaciones.getChildAdapterPosition(v)).getPlazosAbonados()+"");

            Fragment nuevoFragmento = new FragmentEditarUsuario();
            nuevoFragmento.setArguments(args);
            FragmentTransaction transaction = getParentFragmentManager().beginTransaction();
            transaction.replace(R.id.content_frame, nuevoFragmento);
            transaction.commit();
            Objects.requireNonNull(((AppCompatActivity) requireActivity()).getSupportActionBar()).setTitle(R.string.editar_usuario);
        }); */
        recyclerViewDonaciones.setAdapter(adapter);
    }

    private boolean validarCampos() {
        cajaBuscar.setBackgroundResource(R.drawable.borde_cajas_login);
        spinnerBuscar.setBackgroundResource(R.drawable.borde_cajas_login);

        cajaBuscar.setText(cajaBuscar.getText().toString().trim());
        cajaBuscar.setSelection(cajaBuscar.getText().toString().length());

        boolean respuesta = true;
        StringBuilder cadenaRespuesta = new StringBuilder();

        if(spinnerBuscar.getSelectedItemPosition() == 0) {
            cadenaRespuesta.append(getString(R.string.selecciona_filtro)).append("\n\n");
            spinnerBuscar.setBackgroundResource(R.drawable.borde_cajas_donativo_error);
            respuesta = false;
        } else {
            if (cajaBuscar.getText().toString().isEmpty()) {
                cadenaRespuesta.append(indicacionCaja).append("\n\n");
                cajaBuscar.setBackgroundResource(R.drawable.borde_cajas_donativo_error);
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