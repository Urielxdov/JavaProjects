package Aplicacion.FuncionalidadesMenu;

import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

import Archivos.Entradas;
import Archivos.ManipulacionDeArchivos;
import Medicamentos.Medicamento;

public class RecibirMedicamentos {
    private static final String ATRIBUTO_PRIVADA = ";Privada";
    private static final String ATRIBUTO_GOBIERNO = ";Gobierno";
    private static final String SEPARADOR_DE_ATRIBUTOS = ";"; //Separa los atributos de los objetos para su uso futuro
    private static final String FORMATO_ARCHIVO = ".txt";
    private final String RUTA_ARCHIVOS_GOBIERNO;
    private final String RUTA_ARCHIVOS_PRIVADAS;
    private ManipulacionDeArchivos archivo = new ManipulacionDeArchivos();
    private List<Medicamento> almacen = new ArrayList<>();




    public RecibirMedicamentos(String RUTA_ARCHIVOS_GOBIERNO, String RUTA_ARCHIVOS_PRIVADAS) {
        this.RUTA_ARCHIVOS_GOBIERNO = RUTA_ARCHIVOS_GOBIERNO;
        this.RUTA_ARCHIVOS_PRIVADAS = RUTA_ARCHIVOS_PRIVADAS;
    }

    public void ejecutar() {
        String rutaSeleccionada = seleccionarRuta();
        leerAlmacen(rutaSeleccionada);
        int opcionesMenu = Entradas.leerEnteros("1.Buscar por clave \n2.Buscar por folio \n3.Buscar por nombre", 3, 1);
        switch(opcionesMenu) {
            case 1:
                buscarMedicamentoPorClave(rutaSeleccionada);
                break;
            case 2:
                buscarMedicamentoPorFolio(rutaSeleccionada);
                break;
            case 3:
                buscarMedicamentoPorNombre(rutaSeleccionada);
                break;
            }
    }

    private String seleccionarRuta () {
        int opcionesRuta = Entradas.leerEnteros("El medicamento recibido es de instituciones \n1.De Gobierno \n2.Privadas", 2, 1);
        if(opcionesRuta == 1) {
            return RUTA_ARCHIVOS_GOBIERNO;
        } 
        return RUTA_ARCHIVOS_PRIVADAS;
    }

    private void leerAlmacen (String rutaSeleccionada) {
        almacen = archivo.leerTodosLosArchivos(rutaSeleccionada);
    }

    private void buscarMedicamentoPorNombre (String rutaSeleccionada) {
        boolean indicador = true;
        String nombreMedicamento = Entradas.leerCadenas("Nombre del medicamento");
        for(Medicamento medicamento : almacen) {
            if(medicamento.getNombreMedicamento().equals(nombreMedicamento)) {
                almacen = archivo.leerArchivo(rutaSeleccionada, nombreMedicamento);
                indicador=false;
                break;
            }
        }
        if(indicador) {
            JOptionPane.showMessageDialog(null, "No se encontro algun medicamento asociado al nombre " + nombreMedicamento);
        } else {
            registrarEntregaDeMedicamento(almacen.get(0));
        }
    }

    private void buscarMedicamentoPorClave (String rutaSeleccionada) {
        boolean indicador = true;
        int clave = Entradas.leerEnteros("Clave del medicamento");
        for(Medicamento medicamento : almacen) {
            if(medicamento.getClave() == clave) {
                almacen = archivo.leerArchivo(rutaSeleccionada, medicamento.getNombreMedicamento());
                indicador=false;
                break;
            }
        }
        if(indicador) {
            JOptionPane.showMessageDialog(null, "No se encontro algun medicamento asociado a la clave " + clave);
        } else {
            registrarEntregaDeMedicamento(almacen.get(0));
        }
        
    }

    private void buscarMedicamentoPorFolio (String rutaSeleccionada) {
        boolean indicador = true;
        int folio = Entradas.leerEnteros("Folio del medicamento");
        for(Medicamento medicamento : almacen) {
            if(medicamento.getFolio() == folio) {
                almacen = archivo.leerArchivo(rutaSeleccionada, medicamento.getNombreMedicamento());
                indicador=false;
                break;
            }
        }
        if(indicador) {
            JOptionPane.showMessageDialog(null, "No se encontro algun medicamento asociado a la clave " + folio);
        } else {
            registrarEntregaDeMedicamento(almacen.get(0));
        }
    }

    private void registrarEntregaDeMedicamento (Medicamento medicamento) {
        int clave = medicamento.getClave();
        int folio = medicamento.getFolio();
        String nombreMedicamento = medicamento.getNombreMedicamento();
        int existencia = Entradas.leerEnteros("Cuanto " + nombreMedicamento + " llego?");
        int existenciaTotal = existencia;
        int surtidos = 0;
        String fechaDeCaducidad = Entradas.leerCadenas("Introduce la fecha de caducidad");
        confirmarRegistroDeMedicamento(clave, folio, existencia, nombreMedicamento, fechaDeCaducidad, surtidos, existenciaTotal);
    }

    private void confirmarRegistroDeMedicamento(int clave, int folio, int existencia, String nombreMedicamento, String fechaCaducidad, int surtidos, int existenciaTotal) throws Entradas {
        int opcionContinuar = Entradas.leerEnteros(
            "Los datos del medicamento son los siguientes, desea hacer el registro?\n" +
            "Clave: " + clave + "\n" +
            "Folio: " + folio + "\n" +
            "Existencia: " + existencia + "\n" +
            "Nombre del medicamento: " + nombreMedicamento + "\n" +
            "Fecha de caducidad: " + fechaCaducidad, 2, 1);

        if (opcionContinuar == 1) {
            crearPlantillaDeRegistro(clave, folio, nombreMedicamento, existencia, surtidos, existenciaTotal, fechaCaducidad);
        }
    }

    private void crearPlantillaDeRegistro(int clave, int folio, String nombreMedicamento, int existencia, int surtidos, int existenciaTotal, String fechaDeCaducidad) throws Entradas {
        int origenMedicamento = Entradas.leerEnteros("El medicamento recibido es\n1. Gobierno\n2. Privada", 2, 1);
        String contenido = String.valueOf(clave) + SEPARADOR_DE_ATRIBUTOS +
                String.valueOf(folio) + SEPARADOR_DE_ATRIBUTOS +
                nombreMedicamento + SEPARADOR_DE_ATRIBUTOS +
                String.valueOf(existencia) + SEPARADOR_DE_ATRIBUTOS +
                String.valueOf(surtidos) + SEPARADOR_DE_ATRIBUTOS +
                String.valueOf(existenciaTotal) + SEPARADOR_DE_ATRIBUTOS +
                fechaDeCaducidad;

        registrarMedicamento(clave, folio, nombreMedicamento, existencia, surtidos, existenciaTotal, fechaDeCaducidad, origenMedicamento, contenido);
    }

    private void registrarMedicamento(int clave, int folio, String nombreMedicamento, int existencia, int surtidos, int existenciaTotal, String fechaDeCaducidad, int origenMedicamento, String contenido) {
        if (origenMedicamento == 1) {
            contenido += ATRIBUTO_GOBIERNO;
            archivo.escribirArchivo(RUTA_ARCHIVOS_GOBIERNO + "\\" + nombreMedicamento + FORMATO_ARCHIVO, contenido); //Almacena en la ruta de gobierno
        } else {
            contenido += ATRIBUTO_PRIVADA;
            archivo.escribirArchivo(RUTA_ARCHIVOS_PRIVADAS + "\\" + nombreMedicamento + FORMATO_ARCHIVO, contenido); //Almacena en la ruta de privadas
        }
    }



}
