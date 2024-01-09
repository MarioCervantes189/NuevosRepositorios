package Operaciones;

import java.util.Scanner;
import Operaciones.OperacionesMatematicas;

public class Acciones {
    // Se crea una instancia de la clase Scanner para manejar la entrada del usuario
    private Scanner sc = new Scanner(System.in);
    // Se crea una instancia de la clase OperacionesMatematicas
    private OperacionesMatematicas op = new OperacionesMatematicas();

    // Método principal que realiza acciones según las instrucciones proporcionadas en una línea de texto
    public void accion(String linea) {

        // Se obtiene la operación de la línea, la primera palabra antes de un espacio en blanco
        String operacion = linea.split(" ")[0];
        String variable = "";
        boolean saltoLinea = false;
        String restante = "";

        // Se utiliza una estructura switch para manejar diferentes tipos de operaciones
        switch (operacion) {
            // En caso de la operación "Leer"
            case "Leer":
                // Se obtiene el nombre de la variable desde la línea
                variable = linea.split(" ")[1];
                // Se solicita al usuario ingresar un valor para la variable
                System.out.print("Ingrese el valor de " + variable + ": ");
                // Se asigna el valor ingresado a la variable utilizando el objeto 'op' de tipo OperacionesMatematicas
                op.asignarValor(variable, sc.nextDouble());
                break;
        
            // En caso de la operación "ImprimeS"
            case "ImprimeS":
                // Se establece la bandera de salto de línea a verdadero
                saltoLinea = true;
            // En caso de la operación "Imprime" o "ImprimeS"
            case "Imprime":
                // Se extraen los datos entre paréntesis de la línea
                restante = linea.substring(linea.indexOf("(") + 1, linea.indexOf(")"));
                // Se dividen los datos utilizando la coma como delimitador
                String[] datos = restante.split(",");
                // Se recorre el array de datos
                for (int i = 0; i < datos.length; i++) {
                    // Si el dato es una variable existente, se imprime su valor
                    if (op.isVariableExist(datos[i])) {
                        System.out.print(op.obtenerValor(datos[i]));
                    } else {
                        // Si no es una variable, se imprime el dato directamente
                        System.out.print(datos[i].replace("'", ""));
                    }
                }
                // Si hay un salto de línea, se imprime una nueva línea
                if (saltoLinea) {
                    System.out.println();
                }
                break;
        
            // En caso de las operaciones "Entero" o "Real"
            case "Entero":
            case "Real":
                // Se obtiene el nombre de la variable desde la línea
                variable = linea.split(" ")[1];
                // Se inicializa la variable de tipo entero o real utilizando el objeto 'op'
                op.inicializarVariables(operacion, variable);
                break;
        
            // En caso de una operación no reconocida
            default:
                // Se asume una operación aritmética
                variable = linea.split(" ")[0];
                // Se recorren los tokens a partir de la posición 2 en la línea
                for (int i = 2; i < linea.split(" ").length; i++) {
                    // Si el token es un número, se coloca en la pila de operandos
                    if (esNumero(linea.split(" ")[i])) {
                        op.colocarDatoEnPila(Double.parseDouble(linea.split(" ")[i]));
                    } else {
                        // Si no es un número, se coloca en la pila de operadores
                        op.colocarDatoEnPila(linea.split(" ")[i]);
                    }
                }
                // Se realiza la operación y se asigna el resultado a la variable
                op.vaciarPilaOperador();
                op.asignarValor(variable, op.obtenerResultado());
        }
    }

    // Método para verificar si una cadena es un número
    public boolean esNumero(String numero) {
        try {
            Double.parseDouble(numero);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}

