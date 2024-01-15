import java.util.HashMap;
import java.util.Map;

public class Memory {
    private Map<String, Variable> variables;

    public Memory() {
        variables = new HashMap<>();
    }

    public void addVariable(String name, Variable variable) {
        variables.put(name, variable);
    }

    public Variable getVariable(String name) {
        return variables.get(name);
    }

    public Map<String, Variable> getVariables() {
        return variables;
    }

    public String replaceVariables(String expression) {
        for (Map.Entry<String, Variable> entry : variables.entrySet()) {
            String variableName = entry.getKey();
            Variable variable = entry.getValue();

            // Reemplaza la variable en la expresión por su valor si no es nulo
            String variableValue = (variable.getValue() != 0) ? String.valueOf(variable.getValue()) : "null";

            // Utiliza expresiones regulares para encontrar la variable en la expresión y reemplazarla
            expression = expression.replaceAll("\\b" + variableName + "\\b(?![^\\\"]*\\\")", variableValue);
        }

        return expression;
    }
      
}
