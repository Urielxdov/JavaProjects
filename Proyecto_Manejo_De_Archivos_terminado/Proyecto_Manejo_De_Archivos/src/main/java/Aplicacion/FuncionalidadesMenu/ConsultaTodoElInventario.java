package Aplicacion.FuncionalidadesMenu;

import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

import Archivos.ManipulacionDeArchivos;
import Medicamentos.Medicamento;

public class ConsultaTodoElInventario {
    private ManipulacionDeArchivos archivo;
    private List <Medicamento> almacenGobierno = new ArrayList<>();
    private List <Medicamento> almacenPrivadas = new ArrayList<>();
    private final String RUTA_GOBIERNO;
    private final String RUTA_PRIVADAS;

    public ConsultaTodoElInventario(String RUTA_GOBIERNO, String RUTA_PRIVADAS, ManipulacionDeArchivos archivo) {
        this.archivo = archivo;
        this.RUTA_GOBIERNO = RUTA_GOBIERNO;
        this.RUTA_PRIVADAS = RUTA_PRIVADAS;
    }

    /**
     * Ejecuta los procesos necesarios
     */
    public  void ejecutar () {
        this.almacenGobierno = archivo.leerTodosLosArchivos(RUTA_GOBIERNO);
        this.almacenPrivadas = archivo.leerTodosLosArchivos(RUTA_PRIVADAS);
        JOptionPane.showMessageDialog(null, "Inventario de instituciones de gobierno");
        leerArreglo(almacenGobierno);
        JOptionPane.showMessageDialog(null, "Inventario de instituciones privadas");
        leerArreglo(almacenPrivadas);

    }


    /**
     * Lee el arreglo que recibe de almacen
     */
    private void leerArreglo(List <Medicamento> almacen) {
        for(Medicamento medicamento : almacen) {
            JOptionPane.showMessageDialog(null, "Clave: " + medicamento.getClave() + "\nFolio: " + medicamento.getFolio() + "\nNombre del medicamento: " + medicamento.getNombreMedicamento() + "\nExistencia: " +  medicamento.getExistencia() + "\nSurtidos: " + medicamento.getSurtidos() + "\nExistencia total: " + medicamento.getExistenciaTotal() + "\nFecha de caducidad: " + medicamento.getFechaDeCaducidad() + "\nIntitucion: " + medicamento.getTipoMedicamento());
        }
    }
}
