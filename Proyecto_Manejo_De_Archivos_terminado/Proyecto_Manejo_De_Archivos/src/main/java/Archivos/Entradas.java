package Archivos;

import javax.swing.JOptionPane;

/**
 * Esta es una clase personalizada para validar las entradas que utilizamos en el programa
 */
public class Entradas extends RuntimeException {
    public Entradas (String argumento){
        super(argumento); //Envia a la superclase el String necesario para armar el objeto
    }
    static public void validarCadenas(String cadena) throws Entradas{ //Advierte que puede lanzar una Entradas
        if("".equals(cadena.trim()) || cadena.isEmpty()){ //Evalua que la cadena no este vacia
            throw new Entradas("La cadena no puede ir vacia"); //Envia un mensaje de error en caso de que la cadena este vacia
        } 
    }
    static public void validarParametrosEnteros ( int numero, int valorMayor, int valorMenor) throws Entradas { //Advierte que puede lanzar una Entradas
        if(numero<valorMenor || numero>valorMayor) { //Evalua que el numero no se mayor o menor a los parametros establecidos
            throw new Entradas("No cumple con los parametros establecidos"); //Lanza un mensaje de error en caso de cumpliur la condicion
        }
    }    
    
    static public int leerEnteros(String argumento) {
        int numero; //Int para devolover un valor
        while(true) {
            try { //Se intenta obtener un numero apartir del usuario y retornarlo
                numero = Integer.parseInt(JOptionPane.showInputDialog(null, argumento)); 
                return numero;
            } catch (NumberFormatException e) { //En caso de no introducir el numero se arroja una NumberFormatException
                JOptionPane.showMessageDialog(null, "Solo se aceptan numeros en este campo"); //Indica el error
            }
        }
    }

    static public int leerEnteros(String argumento, int valorMayor, int valorMenor) throws Entradas{ //Advierte de la excepcion que se puede lanzar
        int numero; //Almace el valor a devolver
        while(true) {
            try {
                numero = leerEnteros(argumento); //Manda a llamar a LeerEnteros para reutilizar codigo
                Entradas.validarParametrosEnteros(numero, valorMayor, valorMenor); //Manda a llamar valirParametrosEnteros para validar que todo este bien, en caso contrario lanza una Entradas
                return numero; //Retorna el numero
            } catch (Entradas e) { //Captura la Entradas
                JOptionPane.showMessageDialog(null, "Error " + e.getMessage().toLowerCase()); //Indica el error
            }
        }

    }
    static public String leerCadenas (String argumento) throws Entradas { //Advierte de la excepocion Entradas
        String cadena; //Almacena el valor a devolver
        while(true) {
            try {
                cadena = JOptionPane.showInputDialog(null, argumento); //Captura el String
                Entradas.validarCadenas(cadena); //Manda a llamar validarCadenas para verificar que la cadena no este vacia, caso contrario lanza una Entradas
                return cadena;
            } catch (Entradas e) { //Captura la excepcion
                JOptionPane.showMessageDialog(null, "Error " + e.getMessage().toLowerCase()); //Indica el error
            }
        }
    }
}
