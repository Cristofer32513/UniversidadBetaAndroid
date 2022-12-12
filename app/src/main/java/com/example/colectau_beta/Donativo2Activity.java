package com.example.colectau_beta;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

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

        if (getSupportActionBar() != null) {
            getSupportActionBar().hide();
        }

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
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                seleccionarSpinnerEstado();
                posicionEstado = 0;
            }

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

    private boolean validarCampos() {
        cajaCalle.setBackgroundResource(R.drawable.borde_cajas_login);
        cajaColonia.setBackgroundResource(R.drawable.borde_cajas_login);
        cajaMunicipio.setBackgroundResource(R.drawable.borde_cajas_login);
        cajaCP.setBackgroundResource(R.drawable.borde_cajas_login);
        spinnerPais.setBackgroundResource(R.drawable.borde_cajas_login);
        spinnerEstadoVacio.setBackgroundResource(R.drawable.borde_cajas_login);
        spinnerEstadoArgentina.setBackgroundResource(R.drawable.borde_cajas_login);
        spinnerEstadoBolivia.setBackgroundResource(R.drawable.borde_cajas_login);
        spinnerEstadoColombia.setBackgroundResource(R.drawable.borde_cajas_login);
        spinnerEstadoEcuador.setBackgroundResource(R.drawable.borde_cajas_login);
        spinnerEstadoMexico.setBackgroundResource(R.drawable.borde_cajas_login);
        spinnerEstadoVenezuela.setBackgroundResource(R.drawable.borde_cajas_login);
        boolean respuesta = true;
        StringBuilder cadenaRespuesta = new StringBuilder();

        if(cajaCalle.getText().toString().trim().isEmpty()) {
            cadenaRespuesta.append(getString(R.string.ingresa_calle)).append("\n\n");
            cajaCalle.setBackgroundResource(R.drawable.borde_cajas_donativo_error);
            respuesta = false;
        } else {
            if(!cajaCalle.getText().toString().trim().matches(getString(R.string.regex_solo_letras))) {
                cadenaRespuesta.append(getString(R.string.calle_solo_letras)).append("\n\n");
                cajaCalle.setBackgroundResource(R.drawable.borde_cajas_donativo_error);
                respuesta = false;
            }
        }

        if(cajaColonia.getText().toString().trim().isEmpty()) {
            cadenaRespuesta.append(getString(R.string.ingresa_colonia)).append("\n\n");
            cajaColonia.setBackgroundResource(R.drawable.borde_cajas_donativo_error);
            respuesta = false;
        } else {
            if(!cajaColonia.getText().toString().trim().matches(getString(R.string.regex_solo_letras))) {
                cadenaRespuesta.append(getString(R.string.colonia_solo_letras)).append("\n\n");
                cajaColonia.setBackgroundResource(R.drawable.borde_cajas_donativo_error);
                respuesta = false;
            }
        }

        if(cajaMunicipio.getText().toString().trim().isEmpty()) {
            cadenaRespuesta.append(getString(R.string.ingresa_municipio)).append("\n\n");
            cajaMunicipio.setBackgroundResource(R.drawable.borde_cajas_donativo_error);
            respuesta = false;
        } else {
            if(!cajaMunicipio.getText().toString().trim().matches(getString(R.string.regex_solo_letras))) {
                cadenaRespuesta.append(getString(R.string.municipio_solo_letras)).append("\n\n");
                cajaMunicipio.setBackgroundResource(R.drawable.borde_cajas_donativo_error);
                respuesta = false;
            }
        }

        if(cajaCP.getText().toString().trim().isEmpty()) {
            cadenaRespuesta.append(getString(R.string.ingresa_cp)).append("\n\n");
            cajaCP.setBackgroundResource(R.drawable.borde_cajas_donativo_error);
            respuesta = false;
        } else {
            if(!cajaCP.getText().toString().trim().matches(getString(R.string.regex_solo_numeros))) {
                cadenaRespuesta.append(getString(R.string.cp_solo_digitos)).append("\n\n");
                cajaCP.setBackgroundResource(R.drawable.borde_cajas_donativo_error);
                respuesta = false;
            } else if(cajaCP.getText().toString().trim().length() != 5) {
                cadenaRespuesta.append(getString(R.string.cp_5_digitos)).append("\n\n");
                cajaCP.setBackgroundResource(R.drawable.borde_cajas_donativo_error);
                respuesta = false;
            }
        }

        if(spinnerPais.getSelectedItemPosition() == 0) {
            cadenaRespuesta.append(getString(R.string.seleccione_pais)).append("\n\n");
            spinnerPais.setBackgroundResource(R.drawable.borde_cajas_donativo_error);
            respuesta = false;
        } else if(spinnerPais.getSelectedItemPosition() == 1) {
            if(spinnerEstadoArgentina.getSelectedItemPosition() == 0) {
                cadenaRespuesta.append(getString(R.string.seleccione_estado)).append("\n\n");
                spinnerEstadoArgentina.setBackgroundResource(R.drawable.borde_cajas_donativo_error);
                respuesta = false;
            }
        } else if(spinnerPais.getSelectedItemPosition() == 2) {
            if(spinnerEstadoBolivia.getSelectedItemPosition() == 0) {
                cadenaRespuesta.append(getString(R.string.seleccione_estado)).append("\n\n");
                spinnerEstadoBolivia.setBackgroundResource(R.drawable.borde_cajas_donativo_error);
                respuesta = false;
            }
        } else if(spinnerPais.getSelectedItemPosition() == 3) {
            if(spinnerEstadoColombia.getSelectedItemPosition() == 0) {
                cadenaRespuesta.append(getString(R.string.seleccione_estado)).append("\n\n");
                spinnerEstadoColombia.setBackgroundResource(R.drawable.borde_cajas_donativo_error);
                respuesta = false;
            }
        } else if(spinnerPais.getSelectedItemPosition() == 4) {
            if(spinnerEstadoEcuador.getSelectedItemPosition() == 0) {
                cadenaRespuesta.append(getString(R.string.seleccione_estado)).append("\n\n");
                spinnerEstadoEcuador.setBackgroundResource(R.drawable.borde_cajas_donativo_error);
                respuesta = false;
            }
        } else if(spinnerPais.getSelectedItemPosition() == 5) {
            if(spinnerEstadoMexico.getSelectedItemPosition() == 0) {
                cadenaRespuesta.append(getString(R.string.seleccione_estado)).append("\n\n");
                spinnerEstadoMexico.setBackgroundResource(R.drawable.borde_cajas_donativo_error);
                respuesta = false;
            }
        } else if(spinnerPais.getSelectedItemPosition() == 6) {
            if(spinnerEstadoVenezuela.getSelectedItemPosition() == 0) {
                cadenaRespuesta.append(getString(R.string.seleccione_estado)).append("\n\n");
                spinnerEstadoVenezuela.setBackgroundResource(R.drawable.borde_cajas_donativo_error);
                respuesta = false;
            }
        }

        if(!respuesta) {
            new AlertDialog.Builder(this)
                .setIcon(android.R.drawable.ic_dialog_alert).setTitle(getString(R.string.errores_detectados))
                .setMessage(cadenaRespuesta)
                .setPositiveButton(getString(R.string.entendido), (dialogInterface, i) -> dialogInterface.cancel())
                .show()
            ;
        }

        return respuesta;
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
        if(validarCampos()) {
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
    }

    //Para el boton cancelar
    public void cancelar(View view) {
        new AlertDialog.Builder(getApplicationContext())
                .setIcon(android.R.drawable.ic_dialog_alert).setTitle(getString(R.string.precaucion))
                .setMessage(getString(R.string.confirmacion_cancelar_donativo))
                .setPositiveButton(getString(R.string.si), (dialogInterface, i) -> {
                    Intent intent = new Intent(Donativo2Activity.this, LoginActivity.class);
                    startActivity(intent);
                    finish();
                })
                .setNegativeButton(getString(R.string.no), (dialogInterface, i) -> dialogInterface.cancel())
                .show();
    }
}