package donacion;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.Toast;
import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.example.colectau_beta.R;
import java.util.ArrayList;
import controlador.BaseVolleyFragment;
import modelos.Donacion;

public class FragmentDonaciones extends BaseVolleyFragment {

    Spinner spinnerBuscar;
    EditText cajaBuscar;
    ImageButton btnBuscar;
    ArrayList<Donacion> listaDonaciones;
    RecyclerView recyclerViewDonaciones;

    public FragmentDonaciones() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.section_fragment_donaciones, container, false);

        spinnerBuscar = view.findViewById(R.id.spinner_BuscarDonativo);
        cajaBuscar = view.findViewById(R.id.caja_busqueda_donativo);
        btnBuscar = view.findViewById(R.id.btn_buscar_donativo);
        ArrayAdapter<CharSequence> adapterBuscar = ArrayAdapter.createFromResource(getContext(), R.array.spiner_buscar_donativo, R.layout.spinner_items_style);
        spinnerBuscar.setAdapter(adapterBuscar);

        btnBuscar.setOnClickListener(view1 -> {
            if(validarCampos()) {
                Toast.makeText(getContext(), "Buscando...", Toast.LENGTH_LONG).show();
            }
        });

        listaDonaciones = new ArrayList<>();

        recyclerViewDonaciones = (RecyclerView) view.findViewById(R.id.recyclerview_donativos);
        recyclerViewDonaciones.setLayoutManager(new LinearLayoutManager(getContext()));

        LlenarLista();

        ListaDonacionesAdapter adapter=new ListaDonacionesAdapter(listaDonaciones);
        recyclerViewDonaciones.setAdapter(adapter);

        return view;
    }

    private void LlenarLista() {
        listaDonaciones.add(new Donacion(1, "don1", "don1", "don1", "don1", 1, 1, "don1", "don1", "don1", 1, 1));
        listaDonaciones.add(new Donacion(2, "don2", "don2", "don2", "don2", 2, 2, "don2", "don2", "don2", 2, 2));
        listaDonaciones.add(new Donacion(3, "don3", "don3", "don3", "don3", 3, 3, "don3", "don3", "don3", 3, 3));
        listaDonaciones.add(new Donacion(4, "don4", "don4", "don4", "don4", 4, 4, "don4", "don4", "don4", 4, 4));
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