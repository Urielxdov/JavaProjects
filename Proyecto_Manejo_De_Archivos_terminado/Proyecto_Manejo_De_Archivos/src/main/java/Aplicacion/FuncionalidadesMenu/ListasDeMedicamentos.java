package Aplicacion.FuncionalidadesMenu;

import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;


import Archivos.ManipulacionDeArchivos;
import Medicamentos.Medicamento;




public class ListasDeMedicamentos {
    private final String RUTA;
    
    List<Medicamento> almacen = new ArrayList<>();
    ManipulacionDeArchivos archivos = new ManipulacionDeArchivos();


    public ListasDeMedicamentos (String RUTA) {
        this.RUTA = RUTA;
    }


    public void ejecutar() {
        leerAlmacen();
        leerArreglo();
    }

    private void leerAlmacen () {
        almacen = archivos.leerTodosLosArchivos(RUTA);
    }

    private void leerArreglo() {
        for(Medicamento medicamento : almacen) {
            JOptionPane.showMessageDialog(null, "Clave: " + medicamento.getClave() + "\nFolio: " + medicamento.getFolio() + "\nNombre del medicamento: " + medicamento.getNombreMedicamento() + "\nExistencia: " +  medicamento.getExistencia() + "\nSurtidos: " + medicamento.getSurtidos() + "\nExistencia total: " + medicamento.getExistenciaTotal() + "\nFecha de caducidad: " + medicamento.getFechaDeCaducidad() + "\nIntitucion: " + medicamento.getTipoMedicamento());
        }
    }


}
