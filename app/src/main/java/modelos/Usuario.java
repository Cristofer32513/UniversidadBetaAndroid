package modelos;

public class Usuario {
    private int idUsuario;
    private int idImage;
    private String nombreUsuario;
    private String correo;
    private String password;

    public Usuario() {
        super();
        this.idUsuario = 0;
        this.idImage = 0;
        this.nombreUsuario = null;
        this.correo = null;
        this.password = null;
    }

    public Usuario(int idUsuario, int idImage, String nombreUsuario, String correo, String password) {
        super();
        this.idImage = idImage;
        this.idUsuario = idUsuario;
        this.nombreUsuario = nombreUsuario;
        this.correo = correo;
        this.password = password;
    }

    public int getIdImage() {
        return idImage;
    }

    public void setIdImage(int idImage) {
        this.idImage = idImage;
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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "Usuario [idUsuario=" + idUsuario +  ", idImage=" + idImage + ", nombreUsuario=" + nombreUsuario +
                ", correo=" + correo + ", contraseña=" + password + "]\n";
    }
}