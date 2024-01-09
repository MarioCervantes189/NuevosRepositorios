package Operaciones;
import java.util.HashMap;
import java.util.Stack;

import Operadores.Operador;


public class OperacionesMatematicas {

    private Stack<String> pilaOperadores = new Stack<String>();
    private Stack<Double> pilaNumeros = new Stack<Double>();

    private HashMap<String, Double> tablasInt = new HashMap<String, Double>();
    private HashMap<String, Integer> tablasDouble = new HashMap<String, Integer>();


    public double realizarOperacion(double num2, double num1, String operador) {
        switch (operador) {
            case "+":
                return num1 + num2;
            case "-":
                return num1 - num2;
            case "/":
                return num1 / num2;
            case "*":
                return num1 * num2;
            case "^":
                return Math.pow(num1, num2);
            default:
                return 0;
        }
    }

    public void colocarOperadorenPila(String operador) {
        // Verifica si la pila de operadores está vacía o si el operador es un paréntesis abierto
        if (pilaOperadores.isEmpty() || operador.equals(Operador.getParentesisAbierto())) {
            // Si es el caso, se coloca el operador en la pila de operadores
            pilaOperadores.push(operador);
        } else {
            // Si el operador no es un paréntesis abierto y la pila no está vacía
            if (operador.equals(Operador.getParentesisCerrado())) {
                // Si el operador es un paréntesis cerrado, se vacía la pila de operadores
                vaciarPilaOperador();
            } else if (Operador.isMayor(operador, pilaOperadores.peek())) {
                // Si el operador tiene mayor precedencia que el operador en la cima de la pila
                pilaOperadores.push(operador);
            } else {
                // Si el operador tiene menor o igual precedencia que el operador en la cima de la pila
                while (!pilaOperadores.isEmpty()) {
                    // Se extraen dos números y un operador de las pilas de números y operadores
                    double num2 = pilaNumeros.pop();
                    double num1 = pilaNumeros.pop();
                    String operadorAux = pilaOperadores.pop();
                    // Se realiza la operación y se coloca el resultado en la pila de números
                    pilaNumeros.push(realizarOperacion(num2, num1, operadorAux));
                }
                // Se coloca el nuevo operador en la pila de operadores
                pilaOperadores.push(operador);
            }
        }
    }

    public void colocarDatoEnPila(String dato) {
        // Verifica si el dato es un operador
        if (Operador.esOperador(dato)) {
            // Si es un operador, llama al método colocarOperadorenPila para manejarlo
            colocarOperadorenPila(dato);
        } else if (isVariableExist(dato)) {
            // Si el dato es una variable existente, coloca su valor en la pila de números
            pilaNumeros.push(obtenerValor(dato));
        } else {
            // Si el dato no es ni un operador ni una variable existente, imprime un mensaje de error
            System.out.println("Error: " + dato + " no es un operador");
        }
    }

    public void colocarDatoEnPila(double dato){
        pilaNumeros.push(dato);
    }

    public void vaciarPilaOperador(){
        while(true){
            // Verifica si la pila de operadores está vacía
            if(pilaOperadores.isEmpty()){
                return;  // Si está vacía, retorna para salir del bucle
            }
            // Verifica si el operador en la cima de la pila es un paréntesis abierto
            else if(pilaOperadores.peek().equals(Operador.getParentesisAbierto())){
                pilaOperadores.pop();  // Si es un paréntesis abierto, se retira de la pila y retorna para salir del bucle
                return;
            }
            else{
                // Si el operador no es un paréntesis abierto, se extraen dos números y un operador de las pilas
                double num2 = pilaNumeros.pop();
                double num1 = pilaNumeros.pop();
                String operadorAux = pilaOperadores.pop();
                // Se realiza la operación y se coloca el resultado en la pila de números
                pilaNumeros.push(realizarOperacion(num2, num1, operadorAux));
            }
        }
    }
    

    public double obtenerResultado(){
        return pilaNumeros.pop();
    }

    public void inicializarVariables(String tipo, String nombre){
        // Inicia un bloque switch basado en el valor de 'tipo'
        switch (tipo){
            // Caso en que 'tipo' es "Entero"
            case "Entero":
                // Asigna 0.0 a la variable 'nombre' en la tabla 'tablasInt'
                tablasInt.put(nombre, 0.0);
                // Sale del switch
                break;
            // Caso en que 'tipo' es "Real"
            case "Real":
                // Asigna 0 a la variable 'nombre' en la tabla 'tablasDouble'
                tablasDouble.put(nombre, 0);
                // Sale del switch
                break;
            // Caso por defecto (si 'tipo' no es ni "Entero" ni "Real")
            default:
                // Imprime un mensaje de error en la consola
                System.out.println("Error. Tipo de dato no válido");
        }
    }
    

    public void asignarValor(String nombre, double valor){
        // Verifica si la variable está en la tabla de variables enteras (tablasInt)
        if(tablasInt.containsKey(nombre)){
            // Si la variable está en tablasInt, actualiza su valor con el nuevo valor
            tablasInt.put(nombre, valor);
        }
        // Si la variable no está en tablasInt, verifica si está en la tabla de variables reales (tablasDouble)
        else if(tablasDouble.containsKey(nombre)){
            // Si la variable está en tablasDouble, actualiza su valor con la parte entera del nuevo valor
            tablasDouble.put(nombre, (int)valor);
        }
        // Si la variable no está en ninguna de las tablas, muestra un mensaje de error
        else{
            System.out.println("Error. Variable no declarada");
        }
    }
    

    public double obtenerValor(String nombre){
        // Verifica si la variable está en la tabla de variables enteras (tablasInt)
        if(tablasInt.containsKey(nombre)){
            // Si la variable está en tablasInt, devuelve su valor
            return tablasInt.get(nombre);
        }
        // Si la variable no está en tablasInt, verifica si está en la tabla de variables reales (tablasDouble)
        else if(tablasDouble.containsKey(nombre)){
            // Si la variable está en tablasDouble, devuelve su valor
            return tablasDouble.get(nombre);
        }
        // Si la variable no está en ninguna de las tablas, muestra un mensaje de error y devuelve 0
        else{
            System.out.println("Error. Variable no declarada");
            return 0;
        }
    }
    

    public boolean isVariableExist(String nombre){
        return tablasInt.containsKey(nombre) || tablasDouble.containsKey(nombre);
    }
        
}