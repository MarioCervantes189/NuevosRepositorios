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
        if (pilaOperadores.isEmpty()) {
            pilaOperadores.push(operador);
        } else {
            if (Operador.isMayor(operador, pilaOperadores.peek())) {
                pilaOperadores.push(operador);
            } else {
                while (!pilaOperadores.isEmpty()) {
                    double num2 = pilaNumeros.pop();
                    double num1 = pilaNumeros.pop();
                    String operadorAux = pilaOperadores.pop();
                    pilaNumeros.push(realizarOperacion(num2, num1, operadorAux));
                }
                pilaOperadores.push(operador);
            }
        }
    }

    public void colocarDatoEnPila(String dato){
        if (Operador.esOperador(dato)) {
            colocarOperadorenPila(dato);
        }else if(isVariableExist(dato)){
            pilaNumeros.push(obtenerValor(dato));
        }
        else {
            System.out.println("Error: " + dato + " no es un operador");
        }
    }

    public void colocarDatoEnPila(double dato){
        pilaNumeros.push(dato);
    }

    public void vaciarPilaOperador(){
        while(!pilaOperadores.isEmpty()){
            double num2 = pilaNumeros.pop();
            double num1 = pilaNumeros.pop();
            String operadorAux = pilaOperadores.pop();
            pilaNumeros.push(realizarOperacion(num2, num1, operadorAux));
        }
    }

    public double obtenerResultado(){
        return pilaNumeros.pop();
    }

    public void inicializarVariables(String tipo, String nombre){
        switch (tipo){
            case "Entero":
                tablasInt.put(nombre, 0.0);
                break;
            case "Real":
                tablasDouble.put(nombre, 0);
                break;
        }
        System.out.println("Error. Tipo de dato no valido");
    }

    public void asignarValor(String nombre, double valor){
        if(tablasInt.containsKey(nombre)){
            tablasInt.put(nombre, valor);
        }else if(tablasDouble.containsKey(nombre)){
            tablasDouble.put(nombre, (int)valor);
        }else{
            System.out.println("Error. Variable no declarada");
        }
    }

    public double obtenerValor(String nombre){
        if(tablasInt.containsKey(nombre)){
            return tablasInt.get(nombre);
        }else if(tablasDouble.containsKey(nombre)){
            return tablasDouble.get(nombre);
        }else{
            System.out.println("Error. Variable no declarada");
            return 0;
        }
    }

    public boolean isVariableExist(String nombre){
        return tablasInt.containsKey(nombre) || tablasDouble.containsKey(nombre);
    }
        
}