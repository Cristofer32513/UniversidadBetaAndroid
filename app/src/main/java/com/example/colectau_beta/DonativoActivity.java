package com.example.colectau_beta;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import java.util.Calendar;

public class DonativoActivity extends AppCompatActivity {

    private EditText cajaNombre, cajaPrimerAp, cajaSegundoAp, cajaTelefono, cajaCorreo;
    private Spinner spinnerCategoria;
    private Button btnFecha;
    private DatePickerDialog selectorFecha;
    private Bundle extras;

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_donativo);

        if (getSupportActionBar() != null) {
            getSupportActionBar().hide();
        }

        extras = getIntent().getExtras();

        cajaNombre = findViewById(R.id.editText_Nombre);
        cajaPrimerAp = findViewById(R.id.editText_PrimerAp);
        cajaSegundoAp = findViewById(R.id.editText_SegundoAp);
        cajaTelefono = findViewById(R.id.editText_Telefono);
        cajaCorreo = findViewById(R.id.editText_Email);
        spinnerCategoria = findViewById(R.id.spinner_Categoria);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.spiner_categoria, R.layout.spinner_items_style);
        spinnerCategoria.setAdapter(adapter);
        spinnerCategoria.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                if (spinnerCategoria.getSelectedItem().toString().equals("Graduado") ||
                        spinnerCategoria.getSelectedItem().toString().equals("Graduate")) btnFecha.setVisibility(View.VISIBLE);
                else btnFecha.setVisibility(View.INVISIBLE);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {}
        });
        initSelectorDate();
        btnFecha = findViewById(R.id.button_SelectorFecha);
        btnFecha.setText(getString(R.string.seleccione_fecha_graduacion));

        recibirYMostrarDatosEntreIntents();
    }

    @SuppressLint("SetTextI18n")
    private void recibirYMostrarDatosEntreIntents() {
        if(extras.getString("nombre").equals("")) cajaNombre.setText("");
        else cajaNombre.setText(extras.getString("nombre"));

        if(extras.getString("primer_ap").equals("")) cajaPrimerAp.setText("");
        else cajaPrimerAp.setText(extras.getString("primer_ap"));

        if(extras.getString("segundo_ap").equals("")) cajaSegundoAp.setText("");
        else cajaSegundoAp.setText(extras.getString("segundo_ap"));

        if(extras.getString("telefono").equals("")) cajaTelefono.setText("");
        else cajaTelefono.setText(extras.getString("telefono"));

        if(extras.getString("email").equals("")) cajaCorreo.setText("");
        else cajaCorreo.setText(extras.getString("email"));

        if(extras.getInt("categoria") == 0) spinnerCategoria.setSelection(0);
        else spinnerCategoria.setSelection(extras.getInt("categoria"));

        if(extras.getString("fecha_graduacion").equals("")) btnFecha.setText(getString(R.string.seleccione_fecha_graduacion));
        else btnFecha.setText(extras.getString("fecha_graduacion"));
    }

    //Para la seleccion de la fecha
    private void initSelectorDate() {
        DatePickerDialog.OnDateSetListener dateSetListener = (datePicker, year, mes, dia) -> btnFecha.setText(convertirCadenaADate(dia, mes+1, year));
        Calendar calendar = Calendar.getInstance();
        int dia = calendar.get(Calendar.DAY_OF_MONTH);
        int mes = calendar.get(Calendar.MONTH);
        int year = calendar.get(Calendar.YEAR);
        int styleDialog = com.google.android.material.R.style.Theme_Material3_Light_DialogWhenLarge;
        selectorFecha = new DatePickerDialog(this, styleDialog, dateSetListener, year, mes, dia);
        selectorFecha.getDatePicker().setMaxDate(System.currentTimeMillis());
    }
    //Para la seleccion de la fecha
    private String convertirCadenaADate(int dia, int mes, int year) {
        return  dia + "/" + obtenerFormatoMes(mes) + "/" + year;
    }
    //Para la seleccion de la fecha
    private String obtenerFormatoMes(int mes) {
        if(mes == 1) return "01";
        else if(mes == 2) return "02";
        else if(mes == 3) return "03";
        else if(mes == 4) return "04";
        else if(mes == 5) return "05";
        else if(mes == 6) return "06";
        else if(mes == 7) return "07";
        else if(mes == 8) return "08";
        else if(mes == 9) return "09";
        else if(mes == 10) return "10";
        else if(mes == 11) return "11";
        else if(mes == 12) return "10";
        else return "01";
    }
    //Para la seleccion de la fecha
    public void abrirSelectorFecha(View view) {
        selectorFecha.show();
    }

    private boolean validarCampos() {
        cajaNombre.setBackgroundResource(R.drawable.borde_cajas_login);
        cajaPrimerAp.setBackgroundResource(R.drawable.borde_cajas_login);
        cajaSegundoAp.setBackgroundResource(R.drawable.borde_cajas_login);
        cajaTelefono.setBackgroundResource(R.drawable.borde_cajas_login);
        cajaCorreo.setBackgroundResource(R.drawable.borde_cajas_login);
        spinnerCategoria.setBackgroundResource(R.drawable.borde_cajas_login);
        btnFecha.setBackgroundResource(R.drawable.borde_cajas_login);
        boolean respuesta = true;
        StringBuilder cadenaRespuesta = new StringBuilder();

        String regexCorreo = "^(([^<>()\\[\\]\\\\.,;:\\s@”]+(\\.[^<>()\\[\\]\\\\.,;:\\s@”]+)*)|(“.+”))@((\\[[0–9]{1,3}\\.[0–9]{1,3}\\.[0–9]{1,3}\\.[0–9]{1,3}])|(([a-zA-Z\\-0–9]+\\.)+[a-zA-Z]{2,}))$";

        if(cajaNombre.getText().toString().trim().isEmpty()) {
            cadenaRespuesta.append(getString(R.string.ingresa_un_nombre)).append("\n\n");
            cajaNombre.setBackgroundResource(R.drawable.borde_cajas_donativo_error);
            respuesta = false;
        } else {
            if(!cajaNombre.getText().toString().trim().matches(getString(R.string.regex_solo_letras))) {
                cadenaRespuesta.append(getString(R.string.nombre_solo_letras)).append("\n\n");
                cajaNombre.setBackgroundResource(R.drawable.borde_cajas_donativo_error);
                respuesta = false;
            }
        }

        if(cajaPrimerAp.getText().toString().trim().isEmpty()) {
            cadenaRespuesta.append(getString(R.string.ingresa_primer_apellido)).append("\n\n");
            cajaPrimerAp.setBackgroundResource(R.drawable.borde_cajas_donativo_error);
            respuesta = false;
        } else {
            if(!cajaPrimerAp.getText().toString().trim().matches(getString(R.string.regex_solo_letras))) {
                cadenaRespuesta.append(getString(R.string.primer_ap_solo_letras)).append("\n\n");
                cajaPrimerAp.setBackgroundResource(R.drawable.borde_cajas_donativo_error);
                respuesta = false;
            }
        }

        if(cajaSegundoAp.getText().toString().trim().isEmpty()) {
            cadenaRespuesta.append(getString(R.string.ingresa_segundo_ap)).append("\n\n");
            cajaSegundoAp.setBackgroundResource(R.drawable.borde_cajas_donativo_error);
            respuesta = false;
        } else {
            if(!cajaSegundoAp.getText().toString().trim().matches(getString(R.string.regex_solo_letras))) {
                cadenaRespuesta.append(getString(R.string.segundo_ap_solo_letras)).append("\n\n");
                cajaSegundoAp.setBackgroundResource(R.drawable.borde_cajas_donativo_error);
                respuesta = false;
            }
        }

        if(cajaTelefono.getText().toString().trim().isEmpty()) {
            cadenaRespuesta.append(getString(R.string.ingresa_numero_telefono)).append("\n\n");
            cajaTelefono.setBackgroundResource(R.drawable.borde_cajas_donativo_error);
            respuesta = false;
        } else {
            if(!cajaTelefono.getText().toString().trim().matches(getString(R.string.regex_solo_numeros))) {
                cadenaRespuesta.append(getString(R.string.telefono_solo_numero)).append("\n\n");
                cajaTelefono.setBackgroundResource(R.drawable.borde_cajas_donativo_error);
                respuesta = false;
            } else if(cajaTelefono.getText().toString().trim().length() != 10) {
                cadenaRespuesta.append(getString(R.string.telefono_10_digitos)).append("\n\n");
                cajaTelefono.setBackgroundResource(R.drawable.borde_cajas_donativo_error);
                respuesta = false;
            }
        }

        if(cajaCorreo.getText().toString().trim().isEmpty()) {
            cadenaRespuesta.append(getString(R.string.ingresa_email)).append("\n\n");
            cajaCorreo.setBackgroundResource(R.drawable.borde_cajas_donativo_error);
            respuesta = false;
        } else {
            if(!cajaCorreo.getText().toString().trim().matches(regexCorreo)) {
                cadenaRespuesta.append(getString(R.string.email_no_valido)).append("\n\n");
                cajaCorreo.setBackgroundResource(R.drawable.borde_cajas_donativo_error);
                respuesta = false;
            }
        }

        if(spinnerCategoria.getSelectedItemPosition() == 0) {
            cadenaRespuesta.append(getString(R.string.seleccione_categoria)).append("\n\n");
            spinnerCategoria.setBackgroundResource(R.drawable.borde_cajas_donativo_error);
            respuesta = false;
        } else if(spinnerCategoria.getSelectedItemPosition() == 2) {
            if(btnFecha.getText().toString().equals(getString(R.string.seleccione_fecha_graduacion))) {
                cadenaRespuesta.append(getString(R.string.seleccione_fecha_graduacion)
                        .substring(1, getString(R.string.seleccione_fecha_graduacion).length() - 1)).append("\n\n");
                btnFecha.setBackgroundResource(R.drawable.borde_cajas_donativo_error);
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
        intent.putExtra("nombre", cajaNombre.getText().toString());
        intent.putExtra("primer_ap", cajaPrimerAp.getText().toString());
        intent.putExtra("segundo_ap", cajaSegundoAp.getText().toString());
        intent.putExtra("telefono", cajaTelefono.getText().toString());
        intent.putExtra("email", cajaCorreo.getText().toString());
        intent.putExtra("categoria", spinnerCategoria.getSelectedItemPosition());
        intent.putExtra("nombre_categoria", spinnerCategoria.getSelectedItem().toString());
        intent.putExtra("fecha_graduacion", btnFecha.getText().toString());

    }

    //Para el boton cancelar
    public void cancelar(View view) {
        Intent intent = new Intent(DonativoActivity.this, LoginActivity.class);
        startActivity(intent);
        finish();
    }

    //Para el boton siguiente
    public void siguiente(View view) {
        if(validarCampos()) {
            Intent intent = new Intent(DonativoActivity.this, Donativo2Activity.class);
            cargarDatosIntent(intent);
            intent.putExtra("calle", "");
            intent.putExtra("colonia", "");
            intent.putExtra("municipio", "");
            intent.putExtra("cp", "");
            intent.putExtra("estado", 0);
            intent.putExtra("pais", 0);
            startActivity(intent);
            finish();
        }
    }
}