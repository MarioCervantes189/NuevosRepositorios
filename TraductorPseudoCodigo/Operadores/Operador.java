package Operadores;

// Definición de la enumeración Operador
public enum Operador {
    // Declaración de constantes con valores asociados
    PRIMERO(new String[]{"^"}, 3),
    SEGUNDO(new String[]{"*", "/"}, 2),
    TERCERO(new String[]{"+", "-"}, 1),
    PARENTESIS(new String[]{"(", ")"}, 0);

    // Campos de la enumeración para almacenar operadores y prioridad
    public String[] operadores;
    public int prioridad;

    // Constructor de la enumeración Operador
    Operador(String[] operadores, int prioridad) {
        this.operadores = operadores;
        this.prioridad = prioridad;
    }

    // Método estático para obtener el operador de paréntesis abierto
    public static String getParentesisAbierto() {
        return PARENTESIS.operadores[0];
    }

    // Método estático para obtener el operador de paréntesis cerrado
    public static String getParentesisCerrado() {
        return PARENTESIS.operadores[1];
    }

    // Método estático para obtener la prioridad de un operador dado
    public static int getPrioridad(String operador) {
        for (Operador op : Operador.values()) {
            for (String o : op.operadores) {
                if (o.equals(operador)) {
                    return op.prioridad;
                }
            }
        }
        return 0;
    }

    // Método estático para verificar si un símbolo es un operador
    public static boolean esOperador(String operador) {
        for (Operador op : Operador.values()) {
            for (String o : op.operadores) {
                if (o.equals(operador)) {
                    return true;
                }
            }
        }
        return false;
    }

    // Método estático para verificar si la prioridad de un operador1 es mayor que la de operador2
    public static boolean isMayor(String op1, String op2) {
        return getPrioridad(op1) > getPrioridad(op2);
    }
}
