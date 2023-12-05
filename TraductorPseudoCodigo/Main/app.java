package Main;

import Operaciones.Acciones;
import Operaciones.OperacionesMatematicas;
public class app {
    public static void main(String[] args) {
        OperacionesMatematicas op = new OperacionesMatematicas();
        Acciones accion = new Acciones();
        accion.accion("area = 2 * 3 - 4 / 5 + 6 ^ 7");

    }
}
