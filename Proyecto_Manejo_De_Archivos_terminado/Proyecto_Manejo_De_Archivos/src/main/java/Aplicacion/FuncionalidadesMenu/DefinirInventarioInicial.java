package Aplicacion.FuncionalidadesMenu;

import java.io.File;

import javax.swing.JOptionPane;

import Archivos.Entradas;



import Archivos.ManipulacionDeArchivos;

public class DefinirInventarioInicial {
    private static final String ATRIBUTO_PRIVADA = ";Privada";
    private static final String ATRIBUTO_GOBIERNO = ";Gobierno";
    private static final String SEPARADOR_DE_ATRIBUTOS = ";"; //Separa los atributos de los objetos para su uso futuro
    private static final String FORMATO_ARCHIVO = ".txt";
    private final String RUTA_ARCHIVOS_GOBIERNO;
    private final String RUTA_ARCHIVOS_PRIVADAS;
    private ManipulacionDeArchivos archivo = new ManipulacionDeArchivos();




    public DefinirInventarioInicial(String RUTA_ARCHIVOS_GOBIERNO, String RUTA_ARCHIVOS_PRIVADAS) {
        this.RUTA_ARCHIVOS_GOBIERNO = RUTA_ARCHIVOS_GOBIERNO;
        this.RUTA_ARCHIVOS_PRIVADAS = RUTA_ARCHIVOS_PRIVADAS;
    }


    public void ejecutar() throws Entradas {
        int opcionContinuar;
        do {
            solicitarAtributosDelMedicamento();
            opcionContinuar = Entradas.leerEnteros("Desea continuar agregando medicamentos? \n1.Si \n2.No", 2, 1);
        } while (opcionContinuar != 2);
    }



    
    private void solicitarAtributosDelMedicamento() {
        int clave = archivo.cantidadArchivos(RUTA_ARCHIVOS_GOBIERNO);
        int folio = Entradas.leerEnteros("Introduzca el folio del medicamento");
        int existencia = Entradas.leerEnteros("Cuánto medicamento inicial hay?");
        String nombreMedicamento = Entradas.leerCadenas("Introduzca el nombre del medicamento");
        int existenciaTotal = existencia;
        String fechaCaducidad = Entradas.leerCadenas("Introduzca la fecha de caducidad del medicamento");
        int surtidos = 0;
        confirmarRegistroDeMedicamento(clave, folio, existencia, nombreMedicamento, fechaCaducidad, surtidos, existenciaTotal);
    }


    private void confirmarRegistroDeMedicamento(int clave, int folio, int existencia, String nombreMedicamento, String fechaCaducidad, int surtidos, int existenciaTotal) throws Entradas {
        int opcionContinuar = Entradas.leerEnteros(
            "Los datos del medicamento son los siguientes, desea hacer el registro?\n" +
            "Clave: " + clave + "\n" +
            "Folio: " + folio + "\n" +
            "Existencia: " + existencia + "\n" +
            "Nombre del medicamento: " + nombreMedicamento.toLowerCase() + "\n" +
            "Fecha de caducidad: " + fechaCaducidad, 2, 1);

        if (opcionContinuar == 1) {
            crearPlantillaDeRegistro(clave, folio, nombreMedicamento, existencia, surtidos, existenciaTotal, fechaCaducidad);
        }
    }



    private void crearPlantillaDeRegistro(int clave, int folio, String nombreMedicamento, int existencia, int surtidos, int existenciaTotal, String fechaDeCaducidad) throws Entradas {
        String origenMedicamento = Entradas.leerEnteros("El medicamento recibido es\n1. Gobierno\n2. Privada", 2, 1) == 1 ? RUTA_ARCHIVOS_GOBIERNO : RUTA_ARCHIVOS_PRIVADAS;
        String contenido = String.valueOf(clave) + SEPARADOR_DE_ATRIBUTOS +
                String.valueOf(folio) + SEPARADOR_DE_ATRIBUTOS +
                nombreMedicamento.toLowerCase() + SEPARADOR_DE_ATRIBUTOS +
                String.valueOf(existencia) + SEPARADOR_DE_ATRIBUTOS +
                String.valueOf(surtidos) + SEPARADOR_DE_ATRIBUTOS +
                String.valueOf(existenciaTotal) + SEPARADOR_DE_ATRIBUTOS +
                fechaDeCaducidad;
        registrarMedicamento(clave, folio, nombreMedicamento, existencia, surtidos, existenciaTotal, fechaDeCaducidad, origenMedicamento, contenido);
    }

    private boolean verificarExistenciaDelArchivo (String nombreMedicamento, String ruta) {
        File archivo = new File(ruta + "\\" + nombreMedicamento + FORMATO_ARCHIVO);
        if(archivo.exists()) {
            System.out.println("Existo");
            return false;
        }
        System.out.println("No existo");
        return true;
    }


    private void registrarMedicamento(int clave, int folio, String nombreMedicamento, int existencia, int surtidos, int existenciaTotal, String fechaDeCaducidad, String origenMedicamento, String contenido) {
        boolean verificacionDeExistencia = verificarExistenciaDelArchivo(nombreMedicamento, origenMedicamento);
        if(verificacionDeExistencia){
            creacionDeArchivos(nombreMedicamento, clave, folio);
            System.out.println("Parace que todo bien");
            contenido += ATRIBUTO_GOBIERNO;
            archivo.escribirArchivo(origenMedicamento + "\\" + nombreMedicamento + FORMATO_ARCHIVO, contenido);
        } else {
            JOptionPane.showMessageDialog(null, "El medicamento ya se encuentra registrado, usa la opcion 'Recibir medicamentos' del menu principal");
        }
    }




    private void creacionDeArchivos(String nombreMedicamento, int clave, int folio) {
        String identificadores = String.valueOf(clave) + SEPARADOR_DE_ATRIBUTOS + String.valueOf(folio) + SEPARADOR_DE_ATRIBUTOS + nombreMedicamento + SEPARADOR_DE_ATRIBUTOS + 0 + SEPARADOR_DE_ATRIBUTOS + 0 + SEPARADOR_DE_ATRIBUTOS + 0 + SEPARADOR_DE_ATRIBUTOS + 0;
        archivo.crearArchivo(RUTA_ARCHIVOS_GOBIERNO + "\\" + nombreMedicamento + FORMATO_ARCHIVO);
        archivo.crearArchivo(RUTA_ARCHIVOS_PRIVADAS + "\\" + nombreMedicamento + FORMATO_ARCHIVO);
        archivo.escribirArchivo(RUTA_ARCHIVOS_GOBIERNO + "\\" + nombreMedicamento + FORMATO_ARCHIVO, identificadores + ATRIBUTO_GOBIERNO);
        archivo.escribirArchivo(RUTA_ARCHIVOS_PRIVADAS + "\\" + nombreMedicamento + FORMATO_ARCHIVO, identificadores + ATRIBUTO_PRIVADA);        
    }


}
