package Medicamentos;



public class Gobierno extends Medicamento {
    String ruta, fechaDeCaducidad, tipoMedicamento;
    int existencia, surtidos, existenciaTotal;

    public Gobierno(int clave, int folio, String nombreMedicamento, int existencia, int surtidos, int existenciaTotal, String fechaDeCaducidad) 
    
    
    {
        super(clave, folio, nombreMedicamento);
        this.existencia = existencia;
        this.surtidos = surtidos;
        this.existenciaTotal = existenciaTotal;
        this.fechaDeCaducidad = fechaDeCaducidad;
        this.tipoMedicamento = "Gobierno";
    }
    @Override
    public String getRuta() {
        return ruta;
    }
    @Override
    public String getFechaDeCaducidad() {
        return fechaDeCaducidad;
    }
    @Override
    public String getTipoMedicamento() {
        return tipoMedicamento;
    }
    @Override
    public int getExistencia() {
        return existencia;
    }
    @Override
    public int getSurtidos() {
        return surtidos;
    }
    @Override
    public int getExistenciaTotal() {
        return existenciaTotal;
    }
    @Override
    public void setRuta(String ruta) {
        this.ruta = ruta;
    }
    @Override
    public void setSurtidos(int surtidos) {
        this.surtidos += surtidos;
        setExistenciaTotal(this.surtidos);
    }
    @Override
    public void setExistenciaTotal(int surtidos) {
        this.existenciaTotal = this.existenciaTotal- surtidos;
    }

    


}
