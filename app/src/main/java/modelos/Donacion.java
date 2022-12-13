package modelos;

import androidx.annotation.NonNull;

@SuppressWarnings("unused")
public class Donacion {
    private int idDonacion;
    private String nombreDonador;
    private String primerApDonador;
    private String segundoApDonador;
    private String categoria;
    private int prometido;
    private int abonado;
    private String fechaAbono;
    private String fechaLimite;
    private String formaPago;
    private int plazos;
    private int plazosAbonados;

    public Donacion() {
        super();
        this.idDonacion = 0;
        this.nombreDonador = "";
        this.primerApDonador = "";
        this.segundoApDonador = "";
        this.categoria = "";
        this.prometido = 0;
        this.abonado = 0;
        this.fechaAbono = "";
        this.fechaLimite = "";
        this.formaPago = "";
        this.plazos = 0;
        this.plazosAbonados = 0;
    }

    public Donacion(int idDonacion, String nombreDonador, String primerApDonador, String segundoApDonador, String categoria, int prometido, int abonado, String fechaAbono, String fechaLimite, String formaPago, int plazos, int plazosAbonados) {
        this.idDonacion = idDonacion;
        this.nombreDonador = nombreDonador;
        this.primerApDonador = primerApDonador;
        this.segundoApDonador = segundoApDonador;
        this.categoria = categoria;
        this.prometido = prometido;
        this.abonado = abonado;
        this.fechaAbono = fechaAbono;
        this.fechaLimite = fechaLimite;
        this.formaPago = formaPago;
        this.plazos = plazos;
        this.plazosAbonados = plazosAbonados;
    }

    public int getIdDonacion() {
        return idDonacion;
    }

    public void setIdDonacion(int idDonacion) {
        this.idDonacion = idDonacion;
    }

    public String getNombreDonador() {
        return nombreDonador;
    }

    public void setNombreDonador(String nombreDonador) {
        this.nombreDonador = nombreDonador;
    }

    public String getPrimerApDonador() {
        return primerApDonador;
    }

    public void setPrimerApDonador(String primerApDonador) {
        this.primerApDonador = primerApDonador;
    }

    public String getSegundoApDonador() {
        return segundoApDonador;
    }

    public void setSegundoApDonador(String segundoApDonador) {
        this.segundoApDonador = segundoApDonador;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public int getPrometido() {
        return prometido;
    }

    public void setPrometido(int prometido) {
        this.prometido = prometido;
    }

    public int getAbonado() {
        return abonado;
    }

    public void setAbonado(int abonado) {
        this.abonado = abonado;
    }

    public String getFechaAbono() {
        return fechaAbono;
    }

    public void setFechaAbono(String fechaAbono) {
        this.fechaAbono = fechaAbono;
    }

    public String getFechaLimite() {
        return fechaLimite;
    }

    public void setFechaLimite(String fechaLimite) {
        this.fechaLimite = fechaLimite;
    }

    public String getFormaPago() {
        return formaPago;
    }

    public void setFormaPago(String formaPago) {
        this.formaPago = formaPago;
    }

    public int getPlazos() {
        return plazos;
    }

    public void setPlazos(int plazos) {
        this.plazos = plazos;
    }

    public int getPlazosAbonados() {
        return plazosAbonados;
    }

    public void setPlazosAbonados(int plazosAbonados) {
        this.plazosAbonados = plazosAbonados;
    }

    @NonNull
    @Override
    public String toString() {
        return "Donacion [" + "idDonacion=" + idDonacion + ", nombreDonador='" + nombreDonador +
                ", primerApDonador='" + primerApDonador + ", segundoApDonador='" + segundoApDonador +
                ", categoria='" + categoria + ", prometido=" + prometido + ", abonado=" + abonado +
                ", fechaAbono='" + fechaAbono + ", fechaLimite='" + fechaLimite + ", formaPago='" + formaPago +
                ", plazos=" + plazos + ", plazosAbonados=" + plazosAbonados + ']';
    }
}