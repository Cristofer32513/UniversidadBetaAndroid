package com.example.colectau_beta;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.TextView;

public class Donativo4Activity extends AppCompatActivity {

    private TextView textViewCantidadPlazos;
    private Spinner spinnerPlazos;
    private RadioButton radioButtonSiPlazos, radioButtonNoPlazos, radioButtonNoComprobante, radioButtonSiComprobante;
    private CheckBox checkBoxTerminos;
    private Bundle extras;
    private Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_donativo4);

        extras = getIntent().getExtras();

        radioButtonNoPlazos = findViewById(R.id.radioButton_NoPlazos);
        radioButtonSiPlazos = findViewById(R.id.radioButton_SiPlazos);
        radioButtonNoComprobante = findViewById(R.id.radioButton_NoComprobante);
        radioButtonSiComprobante = findViewById(R.id.radioButton_SiComprobante);
        textViewCantidadPlazos = findViewById(R.id.textView_CantidadPlazos);
        spinnerPlazos = findViewById(R.id.spinner_Plazos);
        ArrayAdapter<CharSequence> adapterPlazos = ArrayAdapter.createFromResource(this, R.array.spiner_mensualidades, R.layout.spinner_items_style);
        spinnerPlazos.setAdapter(adapterPlazos);
        checkBoxTerminos = findViewById(R.id.checkBox_Terminos);
        textViewCantidadPlazos.setVisibility(View.GONE);
        spinnerPlazos.setVisibility(View.GONE);
    }


    @SuppressLint("NonConstantResourceId")
    public void onRadioButtonClicked(View view) {
        boolean checked = ((RadioButton) view).isChecked();

        // Check which radio button was clicked
        switch(view.getId()) {
            case R.id.radioButton_NoPlazos:
                if (checked) {
                    textViewCantidadPlazos.setVisibility(View.GONE);
                    spinnerPlazos.setVisibility(View.GONE);
                }
                break;
            case R.id.radioButton_SiPlazos:
                if (checked) {
                    textViewCantidadPlazos.setVisibility(View.VISIBLE);
                    spinnerPlazos.setVisibility(View.VISIBLE);
                }
                break;
            case R.id.radioButton_NoComprobante:
                if (checked)
                    // Ninjas rule
                break;
            case R.id.radioButton_SiComprobante:
                if (checked)
                    // Ninjas rule
                break;
        }
    }


    private boolean validarCampos() {
        spinnerPlazos.setBackgroundResource(R.drawable.borde_cajas_login);
        checkBoxTerminos.setBackgroundResource(R.drawable.sin_borde);
        boolean respuesta = true;
        StringBuilder cadenaRespuesta = new StringBuilder();

        if(radioButtonSiPlazos.isChecked()) {
            if(spinnerPlazos.getSelectedItemPosition() == 0) {
                cadenaRespuesta.append("- Seleccione una cantidad de plazos. \n\n");
                spinnerPlazos.setBackgroundResource(R.drawable.borde_cajas_donativo_error);
                respuesta = false;
            }
        }

        if(!checkBoxTerminos.isChecked()) {
            cadenaRespuesta.append("- Acepte los terminos y condiciones. \n\n");
            checkBoxTerminos.setBackgroundResource(R.drawable.borde_cajas_donativo_error);
            respuesta = false;
        }

        if(!respuesta) {
            new AlertDialog.Builder(this)
                    .setIcon(android.R.drawable.ic_dialog_alert).setTitle("Errores detectados")
                    .setMessage(cadenaRespuesta)
                    .setPositiveButton("Entendido", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            dialogInterface.cancel();
                        }
                    })
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

        intent.putExtra("pos_cantidad_spinner", extras.getInt("pos_cantidad_spinner"));
        intent.putExtra("cantidad_spinner", extras.getString("cantidad_spinner"));
        intent.putExtra("cantidad", extras.getString("cantidad"));
        intent.putExtra("banco", extras.getInt("banco"));
        intent.putExtra("nombre_banco", extras.getString("nombre_banco"));
        intent.putExtra("metodo_pago", extras.getInt("metodo_pago"));
        intent.putExtra("nombre_metodo_pago", extras.getString("nombre_metodo_pago"));
        intent.putExtra("numero_tarjeta", extras.getString("numero_tarjeta"));
        intent.putExtra("vencimiento", extras.getString("vencimiento"));
    }

    //Para el boton cancelar
    public void atras(View view) {
        intent = new Intent(Donativo4Activity.this, Donativo3Activity.class);
        cargarDatosIntent(intent);
        startActivity(intent);
        finish();
    }

    //Para el boton cancelar
    public void enviar(View view) {
        if(validarCampos()) {
            intent = new Intent(Donativo4Activity.this, ProcesandoDonativoActivity.class);
            cargarDatosIntent(intent);
            intent.putExtra("ventana_resultado", 1);
            startActivity(intent);
            finish();
        }
    }

    //Para el boton cancelar
    public void cancelar(View view) {
        Intent intent = new Intent(Donativo4Activity.this, LoginActivity.class);
        startActivity(intent);
        finish();
    }
}