package com.example.colectau_beta;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;

public class Donativo2Activity extends AppCompatActivity {

    private EditText cajaCalle, cajaColonia, cajaMunicipio, cajaCP;
    private Spinner spinnerPais, spinnerEstadoVacio, spinnerEstadoArgentina, spinnerEstadoBolivia,
            spinnerEstadoColombia, spinnerEstadoEcuador, spinnerEstadoMexico, spinnerEstadoVenezuela;
    private Bundle extras;
    private Intent intent;
    private int posicionEstado;
    private String nombreEstado;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_donativo2);

        extras = getIntent().getExtras();
        posicionEstado = extras.getInt("estado");

        cajaCalle = findViewById(R.id.editText_Calle);
        cajaColonia = findViewById(R.id.editText_Colonia);
        cajaMunicipio = findViewById(R.id.editText_Municipio);
        cajaCP = findViewById(R.id.editText_CodigoPostal);
        spinnerEstadoVacio = findViewById(R.id.spinner_EstadoVacio);
        spinnerEstadoArgentina = findViewById(R.id.spinner_EstadoArgentina);
        spinnerEstadoBolivia = findViewById(R.id.spinner_EstadoBolivia);
        spinnerEstadoColombia = findViewById(R.id.spinner_EstadoColombia);
        spinnerEstadoEcuador = findViewById(R.id.spinner_EstadoEcuador);
        spinnerEstadoMexico = findViewById(R.id.spinner_EstadoMexico);
        spinnerEstadoVenezuela = findViewById(R.id.spinner_EstadoVenezuela);
        spinnerPais = findViewById(R.id.spinner_Pais);
        ArrayAdapter<CharSequence> adapterEstadoVacio = ArrayAdapter.createFromResource(this, R.array.spiner_vacio, R.layout.spinner_items_style);
        ArrayAdapter<CharSequence> adapterEstadoArgentina = ArrayAdapter.createFromResource(this, R.array.spiner_argentina, R.layout.spinner_items_style);
        ArrayAdapter<CharSequence> adapterEstadoBolivia = ArrayAdapter.createFromResource(this, R.array.spiner_bolivia, R.layout.spinner_items_style);
        ArrayAdapter<CharSequence> adapterEstadoColombia = ArrayAdapter.createFromResource(this, R.array.spiner_colombia, R.layout.spinner_items_style);
        ArrayAdapter<CharSequence> adapterEstadoEcuador = ArrayAdapter.createFromResource(this, R.array.spiner_ecuador, R.layout.spinner_items_style);
        ArrayAdapter<CharSequence> adapterEstadoMexico = ArrayAdapter.createFromResource(this, R.array.spiner_mexico, R.layout.spinner_items_style);
        ArrayAdapter<CharSequence> adapterEstadoVenezuela = ArrayAdapter.createFromResource(this, R.array.spiner_venezuela, R.layout.spinner_items_style);
        ArrayAdapter<CharSequence> adapterPais = ArrayAdapter.createFromResource(this, R.array.spiner_pais, R.layout.spinner_items_style);
        spinnerEstadoVacio.setAdapter(adapterEstadoVacio);
        spinnerEstadoArgentina.setAdapter(adapterEstadoArgentina);
        spinnerEstadoBolivia.setAdapter(adapterEstadoBolivia);
        spinnerEstadoColombia.setAdapter(adapterEstadoColombia);
        spinnerEstadoEcuador.setAdapter(adapterEstadoEcuador);
        spinnerEstadoMexico.setAdapter(adapterEstadoMexico);
        spinnerEstadoVenezuela.setAdapter(adapterEstadoVenezuela);
        spinnerPais.setAdapter(adapterPais);
        spinnerPais.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {seleccionarSpinnerEstado();}

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {}
        });

        recibirYMostrarDatosEntreIntents();
    }

    private void seleccionarSpinnerEstado() {
        spinnerEstadoVacio.setVisibility(View.GONE);
        spinnerEstadoArgentina.setVisibility(View.GONE);
        spinnerEstadoBolivia.setVisibility(View.GONE);
        spinnerEstadoColombia.setVisibility(View.GONE);
        spinnerEstadoEcuador.setVisibility(View.GONE);
        spinnerEstadoMexico.setVisibility(View.GONE);
        spinnerEstadoVenezuela.setVisibility(View.GONE);

        switch (spinnerPais.getSelectedItem().toString()) {
            case "Argentina":
                spinnerEstadoArgentina.setVisibility(View.VISIBLE);
                spinnerEstadoArgentina.setSelection(posicionEstado);
                break;
            case "Bolivia":
                spinnerEstadoBolivia.setVisibility(View.VISIBLE);
                spinnerEstadoBolivia.setSelection(posicionEstado);
                break;
            case "Colombia":
                spinnerEstadoColombia.setVisibility(View.VISIBLE);
                spinnerEstadoColombia.setSelection(posicionEstado);
                break;
            case "Ecuador":
                spinnerEstadoEcuador.setVisibility(View.VISIBLE);
                spinnerEstadoEcuador.setSelection(posicionEstado);
                break;
            case "México":
                spinnerEstadoMexico.setVisibility(View.VISIBLE);
                spinnerEstadoMexico.setSelection(posicionEstado);
                break;
            case "Venezuela":
                spinnerEstadoVenezuela.setVisibility(View.VISIBLE);
                spinnerEstadoVenezuela.setSelection(posicionEstado);
                break;
            default:
                spinnerEstadoVacio.setVisibility(View.VISIBLE);
                spinnerEstadoVacio.setSelection(posicionEstado);
                break;
        }
    }

    private void recibirYMostrarDatosEntreIntents() {
        if(extras.getString("calle").equals("")) cajaCalle.setText("");
        else cajaCalle.setText(extras.getString("calle"));

        if(extras.getString("colonia").equals("")) cajaColonia.setText("");
        else cajaColonia.setText(extras.getString("colonia"));

        if(extras.getString("municipio").equals("")) cajaMunicipio.setText("");
        else cajaMunicipio.setText(extras.getString("municipio"));

        if(extras.getString("cp").equals("")) cajaCP.setText("");
        else cajaCP.setText(extras.getString("cp"));

        if(extras.getInt("pais") == 0) spinnerPais.setSelection(0);
        else spinnerPais.setSelection(extras.getInt("pais"));

        seleccionarSpinnerEstado();
    }

    private void seleccionarValoresEstado() {
        switch (spinnerPais.getSelectedItem().toString()) {
            case "Argentina":
                posicionEstado = spinnerEstadoArgentina.getSelectedItemPosition();
                nombreEstado = spinnerEstadoArgentina.getSelectedItem().toString();
                break;
            case "Bolivia":
                posicionEstado = spinnerEstadoBolivia.getSelectedItemPosition();
                nombreEstado = spinnerEstadoBolivia.getSelectedItem().toString();
                break;
            case "Colombia":
                posicionEstado = spinnerEstadoColombia.getSelectedItemPosition();
                nombreEstado = spinnerEstadoColombia.getSelectedItem().toString();
                break;
            case "Ecuador":
                posicionEstado = spinnerEstadoEcuador.getSelectedItemPosition();
                nombreEstado = spinnerEstadoEcuador.getSelectedItem().toString();
                break;
            case "México":
                posicionEstado = spinnerEstadoMexico.getSelectedItemPosition();
                nombreEstado = spinnerEstadoMexico.getSelectedItem().toString();
                break;
            case "Venezuela":
                posicionEstado = spinnerEstadoVenezuela.getSelectedItemPosition();
                nombreEstado = spinnerEstadoVenezuela.getSelectedItem().toString();
                break;
            default:
                posicionEstado = spinnerEstadoVacio.getSelectedItemPosition();
                nombreEstado = spinnerEstadoVacio.getSelectedItem().toString();
                break;
        }
    }













    private void cargarDatosIntent(Intent intent) {
        intent.putExtra("nombre", extras.getString("nombre"));
        intent.putExtra("primer_ap", extras.getString("primer_ap"));
        intent.putExtra("segundo_ap", extras.getString("segundo_ap"));
        intent.putExtra("telefono", extras.getString("telefono"));
        intent.putExtra("email", extras.getString("email"));
        intent.putExtra("categoria", extras.getInt("categoria"));
        intent.putExtra("nombre_categoria", extras.getString("nombre_categoria"));
        intent.putExtra("fecha_graduacion", extras.getString("fecha_graduacion"));

        intent.putExtra("calle", cajaCalle.getText().toString());
        intent.putExtra("colonia", cajaColonia.getText().toString());
        intent.putExtra("municipio", cajaMunicipio.getText().toString());
        intent.putExtra("cp", cajaCP.getText().toString());
        seleccionarValoresEstado();
        intent.putExtra("estado", posicionEstado);
        intent.putExtra("nombre_estado", nombreEstado);
        intent.putExtra("pais", spinnerPais.getSelectedItemPosition());
        intent.putExtra("nombre_pais", spinnerPais.getSelectedItem().toString());
    }

    //Para el boton cancelar
    public void atras(View view) {
        extras = getIntent().getExtras();
        intent = new Intent(Donativo2Activity.this, DonativoActivity.class);
        cargarDatosIntent(intent);
        startActivity(intent);
        finish();
    }

    //Para el boton cancelar
    public void siguiente(View view) {
        intent = new Intent(Donativo2Activity.this, Donativo3Activity.class);
        cargarDatosIntent(intent);
        intent.putExtra("pos_cantidad_spinner", 0);
        intent.putExtra("cantidad", "");
        intent.putExtra("banco", 0);
        intent.putExtra("metodo_pago", 0);
        intent.putExtra("numero_tarjeta", "");
        intent.putExtra("vencimiento", "");
        startActivity(intent);
        finish();
    }

    //Para el boton cancelar
    public void cancelar(View view) {
        Intent intent = new Intent(Donativo2Activity.this, LoginActivity.class);
        startActivity(intent);
        finish();
    }
}