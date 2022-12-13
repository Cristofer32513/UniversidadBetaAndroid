package usuario;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.colectau_beta.R;
import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;
import modelos.Usuario;

public class ListaUsuariosAdapter extends RecyclerView.Adapter<ListaUsuariosAdapter.UsuariosViewHolder> implements View.OnClickListener {

    ArrayList<Usuario> listaUsuarios;
    private View.OnClickListener listener;

    public ListaUsuariosAdapter(ArrayList<Usuario> listaUsuarios) {this.listaUsuarios = listaUsuarios;}

    @NonNull
    @Override
    public UsuariosViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        @SuppressLint("InflateParams") View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_usuarios,null,false);
        view.setOnClickListener(this);
        return new UsuariosViewHolder(view);
    }

    @Override
    public void onBindViewHolder(UsuariosViewHolder holder, int position) {
        holder.idUsuario.setText(String.valueOf((listaUsuarios.get(position).getIdUsuario())));
        holder.image.setImageResource(listaUsuarios.get(position).getIdImage());
        holder.nombreUsuario.setText((listaUsuarios.get(position).getNombreUsuario()));
        holder.correo.setText((listaUsuarios.get(position).getCorreo()));
    }

    @Override
    public int getItemCount() {
        return listaUsuarios.size();
    }

    public void setOnClickListener(View.OnClickListener listener){
        this.listener = listener;
    }

    @Override
    public void onClick(View v) {if(listener != null) listener.onClick(v); }

    public static class UsuariosViewHolder extends RecyclerView.ViewHolder {

        TextView idUsuario, nombreUsuario, correo;
        CircleImageView image;

        public UsuariosViewHolder(View itemView) {
            super(itemView);
            idUsuario = itemView.findViewById(R.id.rec_id_usuario);
            image = itemView.findViewById(R.id.rec_imagen_usuario);
            nombreUsuario = itemView.findViewById(R.id.rec_nombre_usuario);
            correo = itemView.findViewById(R.id.rec_correo);
        }
    }
}