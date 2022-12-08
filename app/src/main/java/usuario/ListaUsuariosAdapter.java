package usuario;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.colectau_beta.R;

import java.util.ArrayList;
import modelos.Usuario;

public class ListaUsuariosAdapter extends RecyclerView.Adapter<ListaUsuariosAdapter.UsuariosViewHolder> implements View.OnClickListener {

    ArrayList<Usuario> listaUsuarios;
    private View.OnClickListener listener;

    public ListaUsuariosAdapter(ArrayList<Usuario> listaUsuarios) {
        this.listaUsuarios = listaUsuarios;
    }

    @Override
    public UsuariosViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_usuarios,null,false);
        view.setOnClickListener(this);
        return new UsuariosViewHolder(view);
    }

    @Override
    public void onBindViewHolder(UsuariosViewHolder holder, int position) {
        holder.idUsuario.setText(String.valueOf((listaUsuarios.get(position).getIdUsuario())));
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
    public void onClick(View v) {
        if(listener != null){
            listener.onClick(v);
        }
    }

    public class UsuariosViewHolder extends RecyclerView.ViewHolder {

        TextView idUsuario, nombreUsuario, correo, contraseña;

        public UsuariosViewHolder(View itemView) {
            super(itemView);
            idUsuario = (TextView) itemView.findViewById(R.id.rec_id_usuario);
            nombreUsuario = (TextView) itemView.findViewById(R.id.rec_nombre_usuario);
            correo = (TextView) itemView.findViewById(R.id.rec_correo);
        }
    }
}