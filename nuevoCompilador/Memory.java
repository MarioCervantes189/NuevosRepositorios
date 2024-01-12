import java.util.HashMap;

public class Memory {
    private HashMap<String, Variable> variables;

    public Memory() {
        variables = new HashMap<>();
    }

    public void addVariable(String name, Variable variable) {
        variables.put(name, variable);
    }

    public Variable getVariable(String name) {
        return variables.get(name);
    }
}
