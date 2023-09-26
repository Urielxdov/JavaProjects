package Aplicacion;



import Aplicacion.FuncionalidadesMenu.BuscarMedicamentos;
import Aplicacion.FuncionalidadesMenu.ConsultaTodoElInventario;
import Aplicacion.FuncionalidadesMenu.DefinirInventarioInicial;
import Aplicacion.FuncionalidadesMenu.ListasDeMedicamentos;
import Aplicacion.FuncionalidadesMenu.RecibirMedicamentos;
import Aplicacion.FuncionalidadesMenu.Surtidos;
import Archivos.Entradas;
import Archivos.ManipulacionDeArchivos;

/**
 *
 * @author Zomber
 */
public class Menu {
    private ManipulacionDeArchivos archivo = new ManipulacionDeArchivos();
    private String rutaGobierno;
    private String rutaPrivadas;
    public static void main(String[] args) {
        Menu menu = new Menu();
        menu.iniciar();
    }


    /**
     * Se ponen las rutas donde quieres que vayan los archivos
     */
    private void definirRutas() {
        archivo.setRutaGobierno();
        archivo.setRutaPrivadas();
        rutaGobierno = archivo.getRutaGobierno();
        rutaPrivadas = archivo.getRutaPrivadas();
    }
    

    private void iniciar () throws Entradas {
        definirRutas();
        boolean condition = true;
        int eleccion;
        do {
            eleccion = Entradas.leerEnteros("1.Registrar inventario inicial \n2.Consulta todo el inventario \n3.Surtir una receta \n4.Recibir medicamento \n5.Buscar un medicamento para ver toda la informacion\n6.Lista de medicamentos del gobierno con datos y existencia \n7.ista de medicamentos de instituciones con datos y existencia \n8.Salir", 8, 1);
            switch(eleccion){
                case 1:
                    definirInventarioInicial();
                    break;
                case 2:
                    consultarInventario();
                    break;
                case 3:
                    surtidos();
                    break;
                case 4:
                    recibirMedicamentos();
                    break;
                case 5:
                    buscarUnMedicamento();
                    break;
                case 6:
                    leerListaDeMedicamentos(rutaGobierno);
                    break;
                case 7:
                    leerListaDeMedicamentos(rutaPrivadas);
                    break;
                case 8:
                    condition = false;
                    break;
            }
        } while (condition);
    }//menu


    private void recibirMedicamentos() {
        RecibirMedicamentos recibir = new RecibirMedicamentos(rutaGobierno, rutaPrivadas);
        recibir.ejecutar();
    }


    private void leerListaDeMedicamentos(String ruta) {
        ListasDeMedicamentos buscador = new ListasDeMedicamentos(ruta);
        buscador.ejecutar();
    }


    private void buscarUnMedicamento() {
        BuscarMedicamentos buscador = new BuscarMedicamentos(rutaGobierno, rutaGobierno);
        buscador.ejecutar();
    }


    private void surtidos() {
        Surtidos surtidos = new Surtidos(rutaGobierno, rutaPrivadas, archivo);
        surtidos.ejecutar();
    }

    
    private void consultarInventario() {
        ConsultaTodoElInventario consultarInventario = new ConsultaTodoElInventario(rutaGobierno, rutaPrivadas ,archivo);
        consultarInventario.ejecutar();
    }


    private void definirInventarioInicial() {
        DefinirInventarioInicial definirInventario = new DefinirInventarioInicial(rutaGobierno, rutaPrivadas);
        definirInventario.ejecutar();
    }



    



}
