
package Model;


public class Examen {
    private int  idExamen;
    private String nomExamen;  
    private String DescExamen;
    private double costo;

    public void setIdExamen(int idExamen) {
        this.idExamen = idExamen;
    }
  
    public int getIdExamen() {
        return idExamen;
    }
    
    public void setNomExamen(String nomExamen) {
        this.nomExamen = nomExamen;
    }
    
     public String getNomExamen() {
        return nomExamen;
    }
     
     public String getDescExamen() {
        return DescExamen;
    }

    public void setDescExamen(String DescExamen) {
        this.DescExamen = DescExamen;
    }

    public void setCosto(double costo) {
        this.costo = costo;
    }
    
    public double getCosto() {
        return costo;
    }

}
