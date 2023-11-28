package TraductorPseudoCodigo.Comandos;

import java.util.Stack;

import TraductorPseudoCodigo.Operadores.Operador;

public class OperacionesMatematicas {

    private Stack<String> pilaOperadores = new Stack<String>();
    private Stack<Double> pilaNumeros = new Stack<Double>();

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
        } else {
            pilaNumeros.push(Double.parseDouble(dato));
        }
    }
        
}