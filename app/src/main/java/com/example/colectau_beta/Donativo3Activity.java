package com.example.colectau_beta;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

public class Donativo3Activity extends AppCompatActivity {

    private EditText cajaCantidad, cajaNumeroTarjeta, cajaVencimiento;
    private Spinner spinnerCantidad, spinnerBanco, spinnerMetodoPago;
    private Bundle extras;
    private Intent intent;

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_donativo3);

        if (getSupportActionBar() != null) getSupportActionBar().hide();

        extras = getIntent().getExtras();
        spinnerCantidad = findViewById(R.id.spinner_Cantidad);
        cajaCantidad = findViewById(R.id.editText_Cantidad);
        spinnerBanco = findViewById(R.id.spinner_Banco);
        spinnerMetodoPago = findViewById(R.id.spinner_MetodoDePago);
        cajaNumeroTarjeta = findViewById(R.id.editText_NumeroTarjeta);
        cajaVencimiento = findViewById(R.id.editText_Vencimiento);
        cajaVencimiento.setOnKeyListener((view, i, keyEvent) -> {
            int longitud = cajaVencimiento.getText().toString().trim().length();
            String text = cajaVencimiento.getText().toString().trim();
            if(i != 67) {
                if(longitud == 2) {
                    cajaVencimiento.setText( text + "/");
                    cajaVencimiento.setSelection(cajaVencimiento.getText().toString().trim().length());
                }
            }

            return false;
        });

        ArrayAdapter<CharSequence> adapterCantidad = ArrayAdapter.createFromResource(this, R.array.spiner_cantidad, R.layout.spinner_items_style);
        spinnerCantidad.setAdapter(adapterCantidad);
        spinnerCantidad.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                if (spinnerCantidad.getSelectedItem().toString().equals("Otra Cantidad") ||
                        spinnerCantidad.getSelectedItem().toString().equals("Other Amount")) cajaCantidad.setVisibility(View.VISIBLE);
                else cajaCantidad.setVisibility(View.GONE);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {}
        });
        ArrayAdapter<CharSequence> adapterBanco = ArrayAdapter.createFromResource(this, R.array.spiner_banco, R.layout.spinner_items_style);
        spinnerBanco.setAdapter(adapterBanco);
        ArrayAdapter<CharSequence> adapterMetodoPago = ArrayAdapter.createFromResource(this, R.array.spiner_metodo_pago, R.layout.spinner_items_style);
        spinnerMetodoPago.setAdapter(adapterMetodoPago);

        recibirYMostrarDatosEntreIntents();
    }

    private void recibirYMostrarDatosEntreIntents() {
        if(extras.getInt("pos_cantidad_spinner") == 0) spinnerCantidad.setSelection(0);
        else spinnerCantidad.setSelection(extras.getInt("pos_cantidad_spinner"));

        if(extras.getString("cantidad").equals("")) cajaCantidad.setText("");
        else cajaCantidad.setText(extras.getString("cantidad"));

        if(extras.getInt("banco") == 0) spinnerBanco.setSelection(0);
        else spinnerBanco.setSelection(extras.getInt("banco"));

        if(extras.getInt("metodo_pago") == 0) spinnerMetodoPago.setSelection(0);
        else spinnerMetodoPago.setSelection(extras.getInt("metodo_pago"));

        if(extras.getString("numero_tarjeta").equals("")) cajaNumeroTarjeta.setText("");
        else cajaNumeroTarjeta.setText(extras.getString("numero_tarjeta"));

        if(extras.getString("vencimiento").equals("")) cajaVencimiento.setText("");
        else cajaVencimiento.setText(extras.getString("vencimiento"));
    }

    private boolean validarCampos() {
        spinnerCantidad.setBackgroundResource(R.drawable.borde_cajas_login);
        cajaCantidad.setBackgroundResource(R.drawable.borde_cajas_login);
        spinnerBanco.setBackgroundResource(R.drawable.borde_cajas_login);
        spinnerMetodoPago.setBackgroundResource(R.drawable.borde_cajas_login);
        cajaNumeroTarjeta.setBackgroundResource(R.drawable.borde_cajas_login);
        cajaVencimiento.setBackgroundResource(R.drawable.borde_cajas_login);

        cajaCantidad.setText(cajaCantidad.getText().toString().trim());
        cajaCantidad.setSelection(cajaCantidad.getText().toString().length());
        cajaNumeroTarjeta.setText(cajaNumeroTarjeta.getText().toString().trim());
        cajaNumeroTarjeta.setSelection(cajaNumeroTarjeta.getText().toString().length());
        cajaVencimiento.setText(cajaVencimiento.getText().toString().trim());
        cajaVencimiento.setSelection(cajaVencimiento.getText().toString().length());

        boolean respuesta = true;
        StringBuilder cadenaRespuesta = new StringBuilder();

        if(spinnerCantidad.getSelectedItemPosition() == 0) {
            cadenaRespuesta.append(getString(R.string.seleccione_cantidad)).append("\n\n");
            spinnerCantidad.setBackgroundResource(R.drawable.borde_cajas_donativo_error);
            respuesta = false;
        } else if(spinnerCantidad.getSelectedItemPosition() == 9) {
             if(cajaCantidad.getText().toString().isEmpty()) {
                cadenaRespuesta.append(getString(R.string.ingresa_cantidad)).append("\n\n");
                cajaCantidad.setBackgroundResource(R.drawable.borde_cajas_donativo_error);
                respuesta = false;
            } else if(!cajaCantidad.getText().toString().matches(getString(R.string.regex_solo_numeros))) {
                 cadenaRespuesta.append(getString(R.string.cantidad_solo_digitos)).append("\n\n");
                 cajaCantidad.setBackgroundResource(R.drawable.borde_cajas_donativo_error);
                 respuesta = false;
             } else if(Integer.parseInt(cajaCantidad.getText().toString()) < 10) {
                cadenaRespuesta.append(getString(R.string.cantidad_minima)).append("\n\n");
                cajaCantidad.setBackgroundResource(R.drawable.borde_cajas_donativo_error);
                respuesta = false;
            } else if(Integer.parseInt(cajaCantidad.getText().toString()) > 1000000) {
                cadenaRespuesta.append(getString(R.string.cantidad_maxima)).append("\n\n");
                cajaCantidad.setBackgroundResource(R.drawable.borde_cajas_donativo_error);
                respuesta = false;
            }
        }

        if(spinnerBanco.getSelectedItemPosition() == 0) {
            cadenaRespuesta.append(getString(R.string.seleccione_banco)).append("\n\n");
            spinnerBanco.setBackgroundResource(R.drawable.borde_cajas_donativo_error);
            respuesta = false;
        }

        if(spinnerMetodoPago.getSelectedItemPosition() == 0) {
            cadenaRespuesta.append(getString(R.string.seleccione_metodo_pago)).append("\n\n");
            spinnerMetodoPago.setBackgroundResource(R.drawable.borde_cajas_donativo_error);
            respuesta = false;
        }

        if(cajaNumeroTarjeta.getText().toString().isEmpty()) {
            cadenaRespuesta.append(getString(R.string.ingrese_num_tarjeta)).append("\n\n");
            cajaNumeroTarjeta.setBackgroundResource(R.drawable.borde_cajas_donativo_error);
            respuesta = false;
        } else {
            if(!cajaNumeroTarjeta.getText().toString().matches(getString(R.string.regex_solo_numeros))) {
                cadenaRespuesta.append(getString(R.string.tarjeta_solo_digitos)).append("\n\n");
                cajaNumeroTarjeta.setBackgroundResource(R.drawable.borde_cajas_donativo_error);
                respuesta = false;
            } else if(cajaNumeroTarjeta.getText().toString().length() != 16) {
                cadenaRespuesta.append(getString(R.string.tarjeta_16_digitos)).append("\n\n");
                cajaNumeroTarjeta.setBackgroundResource(R.drawable.borde_cajas_donativo_error);
                respuesta = false;
            }
        }

        if(cajaVencimiento.getText().toString().isEmpty()) {
            cadenaRespuesta.append(getString(R.string.ingresa_vencimiento)).append("\n\n");
            cajaVencimiento.setBackgroundResource(R.drawable.borde_cajas_donativo_error);
            respuesta = false;
        } else {
            if(!cajaVencimiento.getText().toString().matches(getString(R.string.regex_vencimiento)) ||
                    Integer.parseInt(cajaVencimiento.getText().toString().substring(0, 2)) > 12) {
                cadenaRespuesta.append(getString(R.string.vencimiento_invalido)).append("\n\n");
                cajaVencimiento.setBackgroundResource(R.drawable.borde_cajas_donativo_error);
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

        intent.putExtra("calle", extras.getString("calle"));
        intent.putExtra("colonia", extras.getString("colonia"));
        intent.putExtra("municipio", extras.getString("municipio"));
        intent.putExtra("cp", extras.getString("cp"));
        intent.putExtra("estado", extras.getInt("estado"));
        intent.putExtra("nombre_estado", extras.getString("nombre_estado"));
        intent.putExtra("pais", extras.getInt("pais"));
        intent.putExtra("nombre_pais", extras.getString("nombre_pais"));
    }

    //Para el boton cancelar
    public void atras(View view) {
        intent = new Intent(Donativo3Activity.this, Donativo2Activity.class);
        cargarDatosIntent(intent);
        startActivity(intent);
        finish();
    }

    //Para el boton cancelar
    public void siguiente(View view) {
        if(validarCampos()) {
            intent = new Intent(Donativo3Activity.this, Donativo4Activity.class);
            cargarDatosIntent(intent);
            intent.putExtra("pos_cantidad_spinner", spinnerCantidad.getSelectedItemPosition());
            intent.putExtra("cantidad_spinner", spinnerCantidad.getSelectedItem().toString());
            intent.putExtra("cantidad", cajaCantidad.getText().toString());
            intent.putExtra("banco", spinnerBanco.getSelectedItemPosition());
            intent.putExtra("nombre_banco", spinnerBanco.getSelectedItem().toString());
            intent.putExtra("metodo_pago", spinnerMetodoPago.getSelectedItemPosition());
            intent.putExtra("nombre_metodo_pago", spinnerMetodoPago.getSelectedItem().toString());
            intent.putExtra("numero_tarjeta", cajaNumeroTarjeta.getText().toString());
            intent.putExtra("vencimiento", cajaVencimiento.getText().toString());
            startActivity(intent);
            finish();
        }
    }

    //Para el boton cancelar
    public void cancelar(View view) {
        new AlertDialog.Builder(Donativo3Activity.this)
            .setIcon(android.R.drawable.ic_dialog_alert).setTitle(getString(R.string.precaucion))
            .setMessage(getString(R.string.confirmacion_cancelar_donativo))
            .setPositiveButton(getString(R.string.si), (dialogInterface, i) -> {
                Intent intent = new Intent(Donativo3Activity.this, LoginActivity.class);
                startActivity(intent);
                finish();
            })
            .setNegativeButton(getString(R.string.no), (dialogInterface, i) -> dialogInterface.cancel())
            .show();
    }
}