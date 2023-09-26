package Aplicacion.FuncionalidadesMenu;

import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

import Archivos.Entradas;
import Archivos.ManipulacionDeArchivos;
import Medicamentos.Medicamento;

public class BuscarMedicamentos {
    private final String RUTA_GOBIERNO;
    private final String RUTA_PRIVADA;
    
    List<Medicamento> almacenGobierno = new ArrayList<>();
    List<Medicamento> almacenPrivadas = new ArrayList<>();
    ManipulacionDeArchivos archivos = new ManipulacionDeArchivos();


    public BuscarMedicamentos (String RUTA_GOBIERNO,String RUTA_PRIVADA) {
        this.RUTA_GOBIERNO = RUTA_GOBIERNO;
        this.RUTA_PRIVADA = RUTA_PRIVADA;
    }




    public void ejecutar() {
        int opcionesMenu = Entradas.leerEnteros("1.Buscar por nombre \n2.Buscar por clave \n3.Buscar por folio", 3, 1);
        leerAlmacen();
        switch (opcionesMenu) {
            case 1:
                buscarMedicamentoPorNombre(almacenGobierno);
                buscarMedicamentoPorNombre(almacenPrivadas);
                break;
            case 2:
                buscarMedicamentoPorClave(almacenGobierno);
                buscarMedicamentoPorClave(almacenPrivadas);
                break;
            case 3:
                buscarMedicamentoPorFolio(almacenGobierno);
                buscarMedicamentoPorFolio(almacenPrivadas);
                break;
        }
    }

    private void leerAlmacen () {
        almacenGobierno = archivos.leerTodosLosArchivos(RUTA_GOBIERNO);
        almacenPrivadas = archivos.leerTodosLosArchivos(RUTA_PRIVADA);
    }

    private void plantillaImpresion(Medicamento medicamento) {
        JOptionPane.showMessageDialog(null, "Clave: " + medicamento.getClave() + "\nFolio: " + medicamento.getFolio() + "\nNombre del medicamento: " + medicamento.getNombreMedicamento() + "\nExistencia: " +  medicamento.getExistencia() + "\nSurtidos: " + medicamento.getSurtidos() + "\nExistencia total: " + medicamento.getExistenciaTotal() + "\nFecha de caducidad: " + medicamento.getFechaDeCaducidad() + "\nIntitucion: " + medicamento.getTipoMedicamento());
    }

    private void buscarMedicamentoPorNombre (List<Medicamento> almacen) {
        boolean indicador = true;
        String nombreDelMedicamento = Entradas.leerCadenas("Cual es nombre del medicamento");
        for(Medicamento medicamento : almacen) {
            if(medicamento.getNombreMedicamento().equals(nombreDelMedicamento)) {
                plantillaImpresion(medicamento);
                indicador = false;
                break;
            }
        }
        if(indicador) {
            JOptionPane.showMessageDialog(null, "No se encontro algun medicamento asociado al nombre " + nombreDelMedicamento);
        }
    }

    private void buscarMedicamentoPorClave (List<Medicamento> almacen) {
        boolean indicador = true;
        int clave = Entradas.leerEnteros("Clave del medicamento");
        for(Medicamento medicamento : almacen) {
            if(medicamento.getClave() == clave) {
                plantillaImpresion(medicamento);
                indicador = false;
                break;
            }
        }
        if(indicador) {
            JOptionPane.showMessageDialog(null, "No se encontro algun medicamento asociado a la clave " + clave);
        }
    }

    private void buscarMedicamentoPorFolio (List<Medicamento> almacen) {
        boolean indicador = true;
        int folio = Entradas.leerEnteros("Ingresa el folio del medicamento");
        for(Medicamento medicamento : almacen) {
            if(medicamento.getFolio() == folio) {
                plantillaImpresion(medicamento);
                indicador = false;
                break;
            }
        }
        if(indicador) {
            JOptionPane.showMessageDialog(null, "No se encontro algun medicamento asociado al folio " + folio);
        }
    }

}
