package Aplicacion.FuncionalidadesMenu;

import java.util.List;

import javax.swing.JOptionPane;

import Archivos.Entradas;
import Archivos.ManipulacionDeArchivos;
import Medicamentos.Medicamento;

public class Surtidos {

    private static final String PRIVADA_MEDICAMENTO = "Privada";
    private static final String GOBIERNO_MEDICAMENTO = "Gobierno";
    private static final String SEPARADOR_DE_ATRIBUTOS = ";";
    private static final String FORMATO_DE_ARCHIVOS = ".txt";

    private ManipulacionDeArchivos archivo;
    private final String RUTA_GOBIERNO;
    private final String RUTA_PRIVADAS;
    private String nombreMedicamento;

    public Surtidos(String RUTA_GOBIERNO, String RUTA_PRIVADAS, ManipulacionDeArchivos archivo) {
        this.archivo = archivo;
        this.RUTA_GOBIERNO = RUTA_GOBIERNO;
        this.RUTA_PRIVADAS = RUTA_PRIVADAS;
    }

    public void ejecutar() {
        surtirElMedicamento();        
    }

    private void surtirElMedicamento() {
        int[] totalMedicamentos = buscarElMedicamentoEnInventario();
        int control;
        do {
            int cantidadDeSurtido = Entradas.leerEnteros("Cuánto medicamento tomará?\nDisponible: " + (totalMedicamentos[0] + totalMedicamentos[1]), totalMedicamentos[0] + totalMedicamentos[1], 0);
            if (cantidadDeSurtido <= (totalMedicamentos[0] + totalMedicamentos[1])) {
                restarSurtido(totalMedicamentos, cantidadDeSurtido);
                break;
            }
            JOptionPane.showMessageDialog(null, "No se cuenta con suficiente medicamento de '" + nombreMedicamento + "'");
            control = Entradas.leerEnteros("Desea cambiar el monto de surtido?\n1. Sí\n2. No", 2, 1);
            if (control == 2) {
                break;
            }
        } while (true);
    }

    private int[] buscarElMedicamentoEnInventario() {
        int[] medicinaTotal = new int[2];
        nombreMedicamento = Entradas.leerCadenas("Nombre del medicamento");
        medicinaTotal[0] = archivo.buscarTotalDeProducto(RUTA_GOBIERNO, nombreMedicamento);
        medicinaTotal[1] = archivo.buscarTotalDeProducto(RUTA_PRIVADAS, nombreMedicamento);
        return medicinaTotal;
    }

    private void restarSurtido(int[] totalMedicamento, int cantidadDeSurtido) {
        int surtidoLocal = cantidadDeSurtido;
        surtidoLocal = iteracionSobreAlmacenDeMedicamentos(RUTA_GOBIERNO, surtidoLocal, GOBIERNO_MEDICAMENTO);
        if (surtidoLocal != 0) {
            surtidoLocal = iteracionSobreAlmacenDeMedicamentos(RUTA_PRIVADAS, surtidoLocal, PRIVADA_MEDICAMENTO);
        }
    }

    private int iteracionSobreAlmacenDeMedicamentos(String ruta, int surtidoLocal, String tipoDeMedicamento) {
        List<Medicamento> medicamentos = archivo.leerArchivo(ruta, nombreMedicamento);
        String contenido = "";
        for (Medicamento medicamento : medicamentos) {
            if (quitarMedicamentoSurtidoDelInventario(medicamento, surtidoLocal, contenido, tipoDeMedicamento) == 0) {
                contenido = definirContenidoAEscribirEnArchivo(medicamento, contenido, tipoDeMedicamento);
                return 0;
            }
            surtidoLocal = quitarMedicamentoSurtidoDelInventario(medicamento, surtidoLocal, contenido, tipoDeMedicamento);
            contenido = definirContenidoAEscribirEnArchivo(medicamento, contenido, tipoDeMedicamento);
        }
        manipularArchivoDelMedicamento(ruta, contenido);
        return surtidoLocal;
    }

    private int quitarMedicamentoSurtidoDelInventario(Medicamento medicamento, int surtidos, String contenido, String tipoDeMedicamento) {
        if (medicamento.getExistenciaTotal() >= surtidos) {
            medicamento.setSurtidos(surtidos);
            return 0;
        } else {
            surtidos -= medicamento.getExistenciaTotal();
            medicamento.setSurtidos(medicamento.getExistenciaTotal());
            return surtidos;
        }
    }

    private String definirContenidoAEscribirEnArchivo(Medicamento medicamento, String contenido, String tipoDeMedicamento) {
        String platilla = String.valueOf(medicamento.getClave()) + SEPARADOR_DE_ATRIBUTOS + String.valueOf(medicamento.getFolio()) + SEPARADOR_DE_ATRIBUTOS + medicamento.getNombreMedicamento() + SEPARADOR_DE_ATRIBUTOS
                + String.valueOf(medicamento.getExistencia()) + SEPARADOR_DE_ATRIBUTOS + String.valueOf(medicamento.getSurtidos()) + SEPARADOR_DE_ATRIBUTOS + String.valueOf(medicamento.getExistenciaTotal())
                + SEPARADOR_DE_ATRIBUTOS + medicamento.getFechaDeCaducidad() + SEPARADOR_DE_ATRIBUTOS + tipoDeMedicamento + "\n";
        return contenido + platilla;
    }

    private void manipularArchivoDelMedicamento(String ruta, String contenido) {
        archivo.eleminarArchivos(ruta + "\\" + nombreMedicamento + FORMATO_DE_ARCHIVOS);
        archivo.crearArchivo(ruta + "\\" + nombreMedicamento + FORMATO_DE_ARCHIVOS);
        archivo.escribirArchivo(ruta + "\\" + nombreMedicamento + FORMATO_DE_ARCHIVOS, contenido);
    }
}
