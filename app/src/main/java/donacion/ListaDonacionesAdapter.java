package donacion;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.colectau_beta.R;
import java.util.ArrayList;
import modelos.Donacion;

@SuppressWarnings("unused")
public class ListaDonacionesAdapter extends RecyclerView.Adapter<ListaDonacionesAdapter.DonacionesViewHolder> implements View.OnClickListener {

    ArrayList<Donacion> listaDonaciones;
    private View.OnClickListener listener;

    public ListaDonacionesAdapter(ArrayList<Donacion> listaDonaciones) {
        this.listaDonaciones = listaDonaciones;
    }

    @NonNull
    @Override
    public DonacionesViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        @SuppressLint("InflateParams") View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_donaciones,null,false);
        view.setOnClickListener(this);
        return new DonacionesViewHolder(view);
    }

    @Override
    public void onBindViewHolder(DonacionesViewHolder holder, int position) {
        holder.idDonacion.setText(String.valueOf((listaDonaciones.get(position).getIdDonacion())));
        holder.nombre.setText((listaDonaciones.get(position).getNombreDonador()));
        holder.primerAp.setText((listaDonaciones.get(position).getPrimerApDonador()));
        holder.segundoAp.setText((listaDonaciones.get(position).getSegundoApDonador()));
        holder.categoria.setText((listaDonaciones.get(position).getCategoria()));
        holder.prometido.setText(String.valueOf((listaDonaciones.get(position).getPrometido())));
        holder.abonado.setText(String.valueOf((listaDonaciones.get(position).getAbonado())));
        holder.fechaAbono.setText((listaDonaciones.get(position).getFechaAbono()));
        holder.fechaLimite.setText((listaDonaciones.get(position).getFechaLimite()));
        holder.formaPago.setText((listaDonaciones.get(position).getFormaPago()));
        holder.plazos.setText(String.valueOf((listaDonaciones.get(position).getPlazos())));
        holder.plazosAbonados.setText(String.valueOf((listaDonaciones.get(position).getPlazosAbonados())));
    }

    @Override
    public int getItemCount() {
        return listaDonaciones.size();
    }

    public void setOnClickListener(View.OnClickListener listener){
        this.listener = listener;
    }

    @Override
    public void onClick(View v) {if(listener != null) listener.onClick(v); }

    public static class DonacionesViewHolder extends RecyclerView.ViewHolder {

        TextView idDonacion, nombre, primerAp, segundoAp, categoria, prometido, abonado, fechaAbono, fechaLimite, formaPago, plazos, plazosAbonados;

        public DonacionesViewHolder(View itemView) {
            super(itemView);
            idDonacion = itemView.findViewById(R.id.rec_id_donacion);
            nombre = itemView.findViewById(R.id.rec_nombre_donacion);
            primerAp = itemView.findViewById(R.id.rec_primerAp_donacion);
            segundoAp = itemView.findViewById(R.id.rec_segundoAp_donacion);
            categoria = itemView.findViewById(R.id.rec_categoria_donacion);
            prometido = itemView.findViewById(R.id.rec_prometido_donacion);
            abonado = itemView.findViewById(R.id.rec_abonado_donacion);
            fechaAbono = itemView.findViewById(R.id.rec_fechaAbono_donacion);
            fechaLimite = itemView.findViewById(R.id.rec_fechaLimite_donacion);
            formaPago = itemView.findViewById(R.id.rec_formaDePago_donacion);
            plazos = itemView.findViewById(R.id.rec_plazos_donacion);
            plazosAbonados = itemView.findViewById(R.id.rec_plazosAbonados_donacion);
        }
    }
}