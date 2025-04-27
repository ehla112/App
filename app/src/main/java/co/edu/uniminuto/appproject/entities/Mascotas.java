package co.edu.uniminuto.appproject.entities;

public class Mascotas {
    private int idMascota;
    private String nombreMascota;
    private String tipoMascota;
    private String edad;
    private String raza;
    private double peso;
    private  int status;
    private int  idDueno;

    public Mascotas(){

    }

    public Mascotas( String nombreMascota, String tipoMascota, String edad, String raza, double peso, int status,int idDueno) {

        this.nombreMascota = nombreMascota;
        this.tipoMascota = tipoMascota;
        this.edad = edad;
        this.raza = raza;
        this.peso = peso;
        this.status = status;
        this.idDueno = idDueno;
    }

    public int getIdDueno() {
        return idDueno;
    }

    public void setIdDueno(int idDueno) {
        this.idDueno = idDueno;
    }

    public int getIdMascota() {
        return idMascota;
    }

    public void setIdMascota(int id) {
        this.idMascota = id;
    }

    public String getNombreMascota() {
        return nombreMascota;
    }

    public void setNombreMascota(String nombreMascota) {
        this.nombreMascota = nombreMascota;
    }

    public String getTipoMascota() {
        return tipoMascota;
    }

    public void setTipoMascota(String tipoMascota) {
        this.tipoMascota = tipoMascota;
    }

    public String getEdad() {
        return edad;
    }

    public void setEdad(String edad) {
        this.edad = edad;
    }

    public String getRaza() {
        return raza;
    }

    public void setRaza(String raza) {
        this.raza = raza;
    }

    public double getPeso() {
        return peso;
    }

    public void setPeso(double peso) {
        this.peso = peso;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("Mascotas{");
        sb.append("idMascota=").append(idMascota);
        sb.append(", nombreMascota='").append(nombreMascota).append('\'');
        sb.append(", tipoMascota='").append(tipoMascota).append('\'');
        sb.append(", edad='").append(edad).append('\'');
        sb.append(", raza='").append(raza).append('\'');
        sb.append(", peso=").append(peso);
        sb.append(", status=").append(status);
        sb.append(", idDueno=").append(idDueno);
        sb.append('}');
        return sb.toString();
    }
}

