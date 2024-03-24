package letrasfuentes;

/**
 *
 * @author ivanCadena
 */
public enum Colores {
    //constantes
    Rojo(255,0,0),
    Verde(0,255,0),
    Azul(0,0,255),
    Amarillo(255,255,0),
    Morado(255,0,255),
    Gris(90,90,90);
    
    //atributos
    private int rojo;
    private int verde;
    private int azul;
    
    //constructor
    private Colores(int a, int b, int c){
        rojo = a;
        verde = b;
        azul = c;
    }
    
    //getters

    public int getRojo() {
        return rojo;
    }

    public int getVerde() {
        return verde;
    }

    public int getAzul() {
        return azul;
    }
    
    
    //toString
    @Override
    public String toString() {
        return this.name();
    }
    
}
