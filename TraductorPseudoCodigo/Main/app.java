package Main;

import java.util.ArrayList;

import Operaciones.Acciones;
import Operaciones.OperacionesMatematicas;
public class app {
    public static void main(String[] args) {
        OperacionesMatematicas op = new OperacionesMatematicas();
        ArrayList<String> datos = LeerArchivo.leerTexto();
        Acciones accion = new Acciones();
        for(String dato : datos){
            accion.accion(dato);
        }

        /* 
        //accion.accion("area = 2 * 3 - 4 / 5 + 6 ^ 7");
        //accion.accion("ImprimeS ('El area es: ', '20')");
        //accion.accion("ImprimeS ('El area es: ', '20')");
        accion.accion("Entero base");
        accion.accion("Entero altura");
        accion.accion("Real area");
        accion.accion("Leer base");
        accion.accion("Leer altura");
        accion.accion("area = ( base + 2 ) * altura / 2");
        accion.accion("ImprimeS ('El area es: ',area)");
        */
    }
}
