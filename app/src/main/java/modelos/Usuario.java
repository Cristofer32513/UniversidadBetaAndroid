package modelos;

public class Usuario {
    private int idUsuario;
    private String nombreUsuario;
    private String correo;
    private String contraseña;

    public Usuario() {
        super();
        this.idUsuario = 0;
        this.nombreUsuario = null;
        this.correo = null;
        this.contraseña = null;
    }

    public Usuario(int idUsuario, String nombreUsuario, String correo, String contraseña) {
        super();
        this.idUsuario = idUsuario;
        this.nombreUsuario = nombreUsuario;
        this.correo = correo;
        this.contraseña = contraseña;
    }

    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getNombreUsuario() {
        return nombreUsuario;
    }

    public void setNombreUsuario(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getContraseña() {
        return contraseña;
    }

    public void setContraseña(String contraseña) {
        this.contraseña = contraseña;
    }
    
    @Override
    public String toString() {
        return "Usuario [idUsuario=" + idUsuario + ", nombreUsuario=" + nombreUsuario +
                ", correo=" + correo + ", contraseña=" + contraseña + "]";
    }
}