/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Medicamentos;

/**
 *
 * @author Zomber
 */
public abstract class Medicamento {
    private final int clave, folio;
    private String nombreMedicamento;
    
    public Medicamento(int clave, int folio, String nombreMedicamento){
        this.clave=clave;
        this.folio=folio;
        this.nombreMedicamento=nombreMedicamento;
    }
    
    

    public int getClave() {
        return clave;
    }


    public int getFolio() {
        return folio;
    }



    public String getNombreMedicamento() {
        return nombreMedicamento;
    }

    public void setNombreMedicamento(String nombreMedicamento) {
        this.nombreMedicamento = nombreMedicamento;
    }
      
    public abstract String getRuta();
    public abstract String getFechaDeCaducidad();
    public abstract String getTipoMedicamento();
    public abstract int getExistencia();
    public abstract int getSurtidos();
    public abstract int getExistenciaTotal();
    public abstract void setRuta(String ruta);
    public abstract void setSurtidos(int surtidos);
    public abstract void setExistenciaTotal(int surtidos);

    
    
}
