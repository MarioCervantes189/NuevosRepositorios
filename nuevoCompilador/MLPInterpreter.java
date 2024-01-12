
import java.util.Scanner;
import java.util.Stack;
import java.util.StringTokenizer;

public class MLPInterpreter {
    private Memory memory;
    private Stack<Object> operandStack;
    private Stack<Character> operatorStack;

    public MLPInterpreter() {
        memory = new Memory();
        operandStack = new Stack<>();
        operatorStack = new Stack<>();
    }

    public void interpretProgram(String program) {
        Scanner scanner = new Scanner(program);

        while (scanner.hasNextLine()) {
            String line = scanner.nextLine().trim();
            interpretLine(line);
        }
    }

    private void interpretLine(String line) {
        if (line.startsWith("entero") || line.startsWith("real") || line.startsWith("cadena")) {
            // Declaración de variables
            interpretVariableDeclaration(line);
        } else if (line.startsWith("leer")) {
            // Operación de entrada
            interpretInputOperation(line);
        } else if (line.startsWith("imprime")) {
            // Operación de salida
            interpretOutputOperation(line);
        } else {
            // Asignación u otra operación
            interpretAssignmentOrOperation(line);
        }
    }

    private void interpretVariableDeclaration(String line) {
        // Formato esperado: tipo nombre
        String[] tokens = line.split("\\s+");
    
        if (tokens.length == 2) {
            String tipo = tokens[0];
            String nombre = tokens[1];
    
            // Verifica si el tipo es válido (entero, real, cadena)
            if (isValidType(tipo)) {
                // Agrega la variable a la tabla de símbolos (Memory)
                Variable variable = new Variable(tipo, null);
                memory.addVariable(nombre, variable);
            } else {
                System.err.println("Error: Tipo de variable no válido en la declaración.");
            }
        } else {
            System.err.println("Error: Formato incorrecto en la declaración de variable.");
        }
    }
    
    private boolean isValidType(String tipo) {
        return tipo.equals("entero") || tipo.equals("real") || tipo.equals("cadena");
    }
    

    private void interpretInputOperation(String line) {
        // Formato esperado: leer nombreVariable
        String[] tokens = line.split("\\s+");
    
        if (tokens.length == 2) {
            String keyword = tokens[0];
            String nombreVariable = tokens[1];
    
            if (keyword.equals("leer")) {
                // Verifica si la variable está declarada
                if (memory.getVariable(nombreVariable) != null) {
                    // Lee el valor desde la entrada estándar
                    Scanner scanner = new Scanner(System.in);
                    System.out.print("Ingrese el valor para " + nombreVariable + ": ");
                    if (scanner.hasNext()) {
                        String input = scanner.next();
                        // Intenta convertir la entrada al tipo de la variable y asignarla
                        assignVariableValue(nombreVariable, input);
                    } else {
                        System.err.println("Error: No se proporcionó un valor válido.");
                    }
                } else {
                    System.err.println("Error: La variable " + nombreVariable + " no está declarada.");
                }
            } else {
                System.err.println("Error: Palabra clave incorrecta en la operación de entrada.");
            }
        } else {
            System.err.println("Error: Formato incorrecto en la operación de entrada.");
        }
    }
    
    private void assignVariableValue(String nombreVariable, String input) {
        // Obtén la variable de la memoria
        Variable variable = memory.getVariable(nombreVariable);
    
        // Asigna el valor según el tipo de la variable
        if (variable.getType().equals("entero")) {
            try {
                int valor = Integer.parseInt(input);
                variable.setValue(valor);
            } catch (NumberFormatException e) {
                System.err.println("Error: Valor no válido para variable de tipo entero.");
            }
        } else if (variable.getType().equals("real")) {
            try {
                double valor = Double.parseDouble(input);
                variable.setValue(valor);
            } catch (NumberFormatException e) {
                System.err.println("Error: Valor no válido para variable de tipo real.");
            }
        } else if (variable.getType().equals("cadena")) {
            variable.setValue(input);
        }
    }
    

    private void interpretOutputOperation(String line) {
        // Formato esperado: imprime expresion
        String[] tokens = line.split("\\s+", 2);
    
        if (tokens.length == 2) {
            String keyword = tokens[0];
            String expresion = tokens[1];
    
            if (keyword.equals("imprime")) {
                // Evalúa la expresión y muestra el resultado
                try {
                    String resultado = evaluateExpression(expresion);
                    System.out.println(resultado);
                } catch (Exception e) {
                    System.err.println("Error al evaluar la expresión: " + e.getMessage());
                }
            } else {
                System.err.println("Error: Palabra clave incorrecta en la operación de salida.");
            }
        } else {
            System.err.println("Error: Formato incorrecto en la operación de salida.");
        }
    }
    
    private String evaluateExpression(String expression) {
    Stack<Double> operandStack = new Stack<>();
    Stack<Character> operatorStack = new Stack<>();

    // Elimina espacios en blanco de la expresión
    expression = expression.replaceAll("\\s", "");

    // Utiliza un StringTokenizer para dividir la expresión en tokens
    StringTokenizer tokenizer = new StringTokenizer(expression, "+-*/^()", true);

    while (tokenizer.hasMoreTokens()) {
        String token = tokenizer.nextToken();

        if (isNumeric(token)) {
            // Si el token es un número, colócalo en la pila de operandos
            operandStack.push(Double.parseDouble(token));
        } else if (isOperator(token.charAt(0))) {
            // Si el token es un operador
            while (!operatorStack.isEmpty() && hasPrecedence(token.charAt(0), operatorStack.peek())) {
                // Realiza operaciones pendientes con operadores de mayor o igual precedencia
                performOperation(operandStack, operatorStack);
            }
            // Coloca el operador actual en la pila de operadores
            operatorStack.push(token.charAt(0));
        } else if (token.equals("(")) {
            // Si el token es un paréntesis de apertura, colócalo en la pila de operadores
            operatorStack.push('(');
        } else if (token.equals(")")) {
            // Si el token es un paréntesis de cierre, realiza operaciones hasta encontrar el paréntesis de apertura
            while (!operatorStack.isEmpty() && operatorStack.peek() != '(') {
                performOperation(operandStack, operatorStack);
            }
            // Elimina el paréntesis de apertura de la pila de operadores
            operatorStack.pop();
        }
    }

    // Realiza operaciones restantes en la pila de operadores
    while (!operatorStack.isEmpty()) {
        performOperation(operandStack, operatorStack);
    }

    // El resultado final debería estar en la cima de la pila de operandos
    if (!operandStack.isEmpty()) {
        return String.valueOf(operandStack.pop());
    } else {
        return "Error: Expresión no válida";
    }
}

private boolean isNumeric(String str) {
    // Verifica si una cadena es numérica
    try {
        Double.parseDouble(str);
        return true;
    } catch (NumberFormatException e) {
        return false;
    }
}

private boolean isOperator(char op) {
    // Verifica si un carácter es un operador
    return op == '+' || op == '-' || op == '*' || op == '/' || op == '^';
}

private boolean hasPrecedence(char op1, char op2) {
    // Verifica la precedencia de dos operadores
    return getPrecedence(op1) >= getPrecedence(op2);
}

private int getPrecedence(char op) {
    // Asigna un valor de precedencia a los operadores
    switch (op) {
        case '^':
            return 3;
        case '*':
        case '/':
            return 2;
        case '+':
        case '-':
            return 1;
        default:
            return 0;
    }
}

private void performOperation(Stack<Double> operandStack, Stack<Character> operatorStack) {
    // Realiza una operación con los operandos y el operador en la cima de las pilas
    char operator = operatorStack.pop();
    double operand2 = operandStack.pop();
    double operand1 = operandStack.pop();

    double result = performOperation(operand1, operand2, operator);
    operandStack.push(result);
}

private double performOperation(double operand1, double operand2, char operator) {
    // Realiza una operación específica
    switch (operator) {
        case '+':
            return operand1 + operand2;
        case '-':
            return operand1 - operand2;
        case '*':
            return operand1 * operand2;
        case '/':
            if (operand2 != 0) {
                return operand1 / operand2;
            } else {
                throw new ArithmeticException("Error: División por cero");
            }
        case '^':
            return Math.pow(operand1, operand2);
        default:
            throw new IllegalArgumentException("Error: Operador no válido");
    }
}

private void interpretAssignmentOrOperation(String line) {
    // Formato esperado: variable = expresion
    String[] tokens = line.split("\\s*=\\s*", 2);

    if (tokens.length == 2) {
        String variableName = tokens[0].trim();
        String expression = tokens[1].trim();

        // Verifica si la variable está declarada
        Variable variable = memory.getVariable(variableName);
        if (variable != null) {
            // Evalúa la expresión y asigna el resultado a la variable
            try {
                String result = evaluateExpression(expression);
                assignVariableValue(variableName, result);
            } catch (Exception e) {
                System.err.println("Error al evaluar la expresión: " + e.getMessage());
            }
        } else {
            System.err.println("Error: La variable " + variableName + " no está declarada.");
        }
    } else {
        System.err.println("Error: Formato incorrecto en la asignación u operación.");
    }
}

}
