package Archivos;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

import Medicamentos.Gobierno;
import Medicamentos.Medicamento;
import Medicamentos.Privadas;

/**
 *Esta clase se encarga de la manipulacion de los archivos como acciones como leerlos, escribirlos, crearlos y eliminarlos
 *
 * 
 * Bugs: Durante el desarrollo los metodos de leerArchivo parecieron dar errores al 
 * momento de regresar los valores, llegan a regresar valores nulos, tener en cuenta en caso de implementarlos
 * leerTodosLosArchivos tiene la solucion de como lidiar con los nulos
 */
public class ManipulacionDeArchivos {
    private String  rutaGobierno = "";
    private String rutaPrivadas="";


    public void setRutaPrivadas () {
        //rutaPrivadas = JOptionPane.showInputDialog(null, "Define la ruta para archivos de privadas");
        rutaPrivadas = "D:\\Archivos Java\\Proyecto Archivos\\Medicamentos Privadas";
    }
    public void setRutaGobierno () {
        rutaGobierno = "D:\\Archivos Java\\Proyecto Archivos\\Medicamentos Gobierno";
        //rutaGobierno = JOptionPane.showInputDialog(null , "Define la ruta para medicamentos de gobierno");
    }

    public String getRutaGobierno() {
        return rutaGobierno;
    }
    public String getRutaPrivadas() {
        return rutaPrivadas;
    }
    /**
     * Se obtienen las rutas de los archivos en un arreglo y se leen 1 en 1
     * @param ruta
     * @param medicamentos Lista de medicamentos registrados
     * @return
     */
    public List<Medicamento> leerTodosLosArchivos (String ruta) {
        File folder = new File(ruta);
        System.out.println(ruta);
        File[] archivos = folder.listFiles();
        List<Medicamento> pivot=null;
        for(File archivo : archivos) {
            System.out.println(archivo.getName());
            pivot = leerArchivo(ruta, archivo.getName()); //Obtenemos la ruta del archivo
        }
        return pivot;
    }

    /**
     * En esta clase obtenemos cuanto hay en total de cada producto
     * @param ruta
     * @param nombreMedicamento
     * @return
     */
    public int buscarTotalDeProducto (String ruta, String nombreMedicamento) {
        int existenciasTotales = 0;
        List<Medicamento> medicamentos = leerArchivo(ruta, nombreMedicamento);
        for(Medicamento medicamento : medicamentos) {
            existenciasTotales += medicamento.getExistenciaTotal(); //Sumamos a existenciasTotales la existencia de cada objeto para obtener el total
        }
        return existenciasTotales;
    }


    /**
     * Este metodo nos proporciona la cantidad de archivos que 
     * hay en un directorio. Esto nos permite asignar las claves 
     * los medicamentos de forma automatica
     * @param ruta Indica la ruta al directorio
     * @return retorna la cantidad de archivos en el directorio
     */
    public int cantidadArchivos (String ruta) {
        File folder = new File(ruta);
        File[] archivos = folder.listFiles();
        if(archivos!=null) {
            return archivos.length;
        } else {
            JOptionPane.showMessageDialog(null, "Este directorio esta vacio");
            return 0;
        }
    }

    /**
     * Crea archivos en la ruta que se le especifique
     * @param ruta ruta para crear el archivo
     */
    public void crearArchivo(String ruta) {
        File archivo = new File(ruta); // Indica la ruta del archivo
        if (!archivo.exists()) {
            PrintWriter salida = null;
            try { // Manejamos la excepción FileNotFoundException para evitar errores en el programa
                salida = new PrintWriter(archivo); // Sirve para crear el archivo en la ruta que le mandamos a la instancia de File
            } catch (FileNotFoundException e) { // Capturamos la excepción FileNotFoundException
                JOptionPane.showMessageDialog(null, "No se ha encontrado el archivo"); // Indicamos el error
            } finally {
                if (salida != null) {
                    salida.close(); // Cerramos salida una vez terminado el proceso para evitar que se corrompa el archivo
                }
            }
        } else {
            JOptionPane.showMessageDialog(null, "El medicamento ya se registro anteriormete, utilice la opcion 'Recibir medicamento' del menu principal");
        }
    }
    

    /**
     * Escribimos dentro de archivos
     * @param ruta indica la ruta donde se encuentra el archivo que deseamos modificar
     * @param contenido el contenido que queremos escribir dentro del archivo
     */
    public void escribirArchivo (String ruta, String contenido){
        File archivo = new File(ruta); //Indica la ruta del archivo
        PrintWriter salida = null;
        try {
            salida =  new PrintWriter(new FileWriter(archivo, true)); //Utilizamos una instancia de PrintWriter para acceder a su metodo println y escribir dentro del archivo
            salida.println(contenido); //Escribimos dentro del archivo con el metodo println de la instancia PrintWriter
        } catch(FileNotFoundException e) { //Capturamos la excepcion FileNotFoundExcepcion
            JOptionPane.showMessageDialog(null, "No se encontro el archivo"); //Indicamos el error
        } catch(IOException e) {
            JOptionPane.showMessageDialog(null, "Error en la escritura de datos");
        } finally {
            if (salida != null) {
                salida.close(); // Cerramos salida una vez terminado el proceso para evitar que se corrompa el archivo
            }
        }
    }//escribitArchivo

    private Medicamento asignarValoresDeMedicamento (String[] datos) {
        Medicamento medicina = null;
        if (datos.length == 8) { //Definimos los valores para cada crear objetos
            int clave = Integer.parseInt(datos[0]);
            int folio = Integer.parseInt(datos[1]);
            String nombreMedicamento = datos[2];
            int existencia = Integer.parseInt(datos[3]);
            int surtidos = Integer.parseInt(datos[4]);
            int existenciaTotal = Integer.parseInt(datos[5]);
            String fechaCaducidad = datos[6];
            String tipo = datos[7];
            if (tipo.equals("Gobierno")) {
                medicina = new Gobierno(clave, folio, nombreMedicamento, existencia, surtidos, existenciaTotal, fechaCaducidad);
            } else  {
                medicina = new Privadas(clave, folio, nombreMedicamento, existencia, surtidos, existenciaTotal, fechaCaducidad);
            } 
            return medicina;
        }//if
        return null;
    }

    /**
     * Lee el ultimo registro del archivo
     * @param ruta Indica la ruta del archivo
     */
    public Medicamento leerArchivo(String ruta) {
        File archivo = new File(ruta);
        BufferedReader lector = null;
        Medicamento medicamento = null;
        try {
            lector = new BufferedReader(new FileReader(archivo));
            String lectura = lector.readLine(); //Lee linea a linea el archivo
            while (lectura != null) {
                String[] datos = lectura.split(";");
                medicamento = asignarValoresDeMedicamento(datos);
                lectura = lector.readLine(); //Actualizamos la lectura a la siguiente linea
            }//While
        } catch (FileNotFoundException e) {
            System.out.println("Es la ruta"+ ruta);
            JOptionPane.showMessageDialog(null, "No se ha encontrado el archivo, asegurese de tener bien escrita la ruta");
        } catch (IOException e) {
            System.out.println("Es la ruta" + ruta);
            JOptionPane.showMessageDialog(null, "No se pudo leer el archivo. intente nuevamente");
        } finally {
            if (lector != null) {
                try {
                    lector.close();
                } catch (IOException e) {
                    JOptionPane.showMessageDialog(null, "Error al cerrar el archivo");
                }
            }
        }
        return medicamento;
    }
    

    /**
     * Sobre carga de leerArchivo que nos permite leer todos los archivos de un directorio
     * @param ruta
     * @param nombreArchivo
     * @return
     */
    public List<Medicamento> leerArchivo(String ruta, String nombreArchivo) {
        File archivo = new File(ruta + "\\" + nombreArchivo);
        BufferedReader lector = null;
        List <Medicamento> medicamento = new ArrayList<>();
        System.out.println(archivo.getAbsolutePath() + "ruta");
        Medicamento medicina = null;
        try {
            lector = new BufferedReader(new FileReader(archivo));
            String lectura = lector.readLine(); //Lee linea a linea el archivo
            while (lectura != null) {
                String[] datos = lectura.split(";"); //Separa los valores cada que encuentra ';'
                medicina = asignarValoresDeMedicamento(datos);
                if(medicina!=null) {
                    medicamento.add(medicina);
                }
                lectura = lector.readLine(); //Actualizamos la lectura
            }
        } catch (FileNotFoundException e) {
            JOptionPane.showMessageDialog(null, "No se ha encontrado el archivo, asegurese de tener bien escrita la ruta y nombre del medicamento");
            System.out.println(e);
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "No se pudo leer el archivo, intente nuevamente");
        } finally {
            if (lector != null) {
                try {
                    lector.close();
                } catch (IOException e) {
                    JOptionPane.showMessageDialog(null, "Error al cerrar el archivo");
                }
            }
        }
        return medicamento;
    }

    
    

    /**
     * Sirve para eliminar lo archivos que ya no necesitemos
     * @param ruta Indica la ruta del archivo
     */
    public void eleminarArchivos (String ruta) {
        File archivo = new File(ruta); //Indica la ruta del archivo
        archivo.delete(); //Elimina el archivo
        System.out.println("Archivo eleminado"); //Indica que el proceso finalizo
    } //eleminarArchivos
}
